
package com.choranet.compta
    

import org.zkoss.zul.*
import org.zkoss.zkplus.databind.*
import org.apache.commons.logging.Log 
import org.apache.commons.logging.LogFactory
import org.codehaus.groovy.grails.plugins.jasper.JasperExportFormat
import org.codehaus.groovy.grails.plugins.jasper.JasperReportDef

import org.zkoss.util.media.AMedia;
import org.zkoss.util.media.Media;
import java.util.HashMap;
import java.util.Map;
import org.zkoss.zk.ui.Executions
import com.choranet.commun.SuperWindow
import com.choranet.commun.SuperService
import com.choranet.commun.ChoraClientInfo
import org.zkoss.zk.ui.event.Events
import org.zkoss.zk.ui.event.Event

/**
 * RegimeTVA Window Object
 **/
class RegimeTVAWindow extends SuperWindow {
    /**
     * Service pour la gestion de l'objet RegimeTVA
     **/
    def regimeTVAService
    
    /**
     * Service jasper pour la generation des rapports
     **/
    def jasperService
    /**
     * Logger de la class RegimeTVAWindow
     **/
    private Log logger = LogFactory.getLog(RegimeTVAWindow.class)
    
    
    def isUpdate
    /**
     * Constructeur
     **/
    public RegimeTVAWindow () {
        super(RegimeTVA.class, 10)
    }  

    protected SuperService getService() {
        return this.regimeTVAService
    }
    
    /**
     * Generation du rapport pdf
     **/
    def genererRapportPdf() {
        String nomsociete = session.societe.raisonSociale
        String titrerapport = "Rapport des RegimeTVAs"
        def listeObjetsExporter = getObjetsToExport()
        def reportDef = new JasperReportDef(name:'rapport_des_RegimeTVAs.jrxml',
            fileFormat:JasperExportFormat.PDF_FORMAT,
            reportData : listeObjetsExporter,
            parameters : [  'nomsociete':nomsociete, 'titrerapport':titrerapport ]
        )
        def bit = jasperService.generateReport(reportDef).toByteArray()
        def nom_fichier = "rapport_des_RegimeTVAs.pdf"
        //Filedownload.save(bit, "application/pdf", nom_fichier);
        Media amedia = new AMedia(nom_fichier, "pdf", "application/pdf", bit);
        Map map = new HashMap();
        map.put("content", amedia);
        Executions.createComponents("/zul/util/pdfviewer.zul", null, map).doModal();
    }
    /**
     * Generation du rapport excel
     **/
    def genererRapportExcel() {
        String nomsociete = session.societe.raisonSociale
        String titrerapport = "Rapport des RegimeTVAs"
        def listeObjetsExporter = getObjetsToExport()
        def reportDef = new JasperReportDef(name:'rapport_des_RegimeTVAs.jrxml',
            fileFormat:JasperExportFormat.XLS_FORMAT,
            reportData : listeObjetsExporter,
            parameters : [  'nomsociete':nomsociete, 'titrerapport':titrerapport ]
        )
        def bit = jasperService.generateReport(reportDef).toByteArray()
        def nom_fichier = "rapport_des_RegimeTVAs.xls"
        Filedownload.save(bit, "application/file", nom_fichier);
        
    }
    
    /**
     *  Cette fonction est appeliée lorsque un élement de la liste est selectionné
     **/
    def select() { 
        if(ispopup) {
            def objetdistant = arg.get("objetdistant")
            def nomattribut = arg.get("nomattribut")
            objetdistant."$nomattribut" = objetSelected
            arg.get("composant").value = objetSelected
            Event closeEvent = new Event( "onClose", this, null ) ;
            Events.postEvent( closeEvent ) ;
        } else {
            isUpdate = true
            objet = objetSelected
            rafraichirField()
        }
    }
    
    def addOrUpdate(){
        if (isUpdate) {
            this.update()
            isUpdate = false
        } else {
            this.add()
        }
    }
    
    def add() {
        try {     
            getService().save(objet)
        } catch(Exception ex) {
            logger.error "Error: ${ex.message}", ex
            //Messagebox.show("Echec lors de la transaction\n" + ex.message, "Erreur", Messagebox.OK, Messagebox.ERROR)
            Messagebox.show("Ce régime TVA est déjà supporté \n", "A Savoir", Messagebox.OK, Messagebox.ERROR)
        } finally {
            objet = clazz.newInstance()
            rafraichirField()
            rafraichirList()
        }
        
    }
    
    def update() {
        try {
            getService().update(objet)
        }
        catch(Exception e) {
            logger.error "Error: ${e.message}", e
            //Messagebox.show("Problème lors de la mise à jour", "Erreur", Messagebox.OK, Messagebox.ERROR)
            Messagebox.show("Ce régime TVA est déjà supporté \n", "A Savoir", Messagebox.OK, Messagebox.ERROR)
        }
        finally {
            objet = clazz.newInstance()
            rafraichirField()
            rafraichirList()
        }
       
    }
    
    def delete() {
        try{
            getService().delete(objet)
        }
        catch(Exception e) {
            Messagebox.show("Ce régime TVA est déjà associé à un produit\n", "A Savoir", Messagebox.OK, Messagebox.ERROR)
        }
        
        objet = clazz.newInstance()
        rafraichirField()
        rafraichirList()
    }
}
