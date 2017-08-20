
package com.choranet.stock
    

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
 * Entrepot Window Object
 **/
class EntrepotWindow extends SuperWindow {
    /**
     * Service pour la gestion de l'objet Entrepot
     **/
    def entrepotService
    
    /**
     * Service jasper pour la generation des rapports
     **/
    def jasperService
    /**
     * Logger de la class EntrepotWindow
     **/
    private Log logger = LogFactory.getLog(EntrepotWindow.class)
    
    def isUpdate
    
    def modeValorisationSelected
    
    /**
     * Constructeur
     **/
    public EntrepotWindow () {
        super(Entrepot.class)
    }  

    protected SuperService getService() {
        return this.entrepotService
    }
    
    /**
     * Generation du rapport pdf
     **/
    def genererRapportPdf() {
        String nomsociete = session.societe.raisonSociale
        String titrerapport = "Rapport des Entrepots"
        def listeObjetsExporter = getObjetsToExport()
        def reportDef = new JasperReportDef(name:'rapport_des_Entrepots.jrxml',
            fileFormat:JasperExportFormat.PDF_FORMAT,
            reportData : listeObjetsExporter,
            parameters : [  'nomsociete':nomsociete, 'titrerapport':titrerapport ]
        )
        def bit = jasperService.generateReport(reportDef).toByteArray()
        def nom_fichier = "rapport_des_Entrepots.pdf"
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
        String titrerapport = "Rapport des Entrepots"
        def listeObjetsExporter = getObjetsToExport()
        def reportDef = new JasperReportDef(name:'rapport_des_Entrepots.jrxml',
            fileFormat:JasperExportFormat.XLS_FORMAT,
            reportData : listeObjetsExporter,
            parameters : [  'nomsociete':nomsociete, 'titrerapport':titrerapport ]
        )
        def bit = jasperService.generateReport(reportDef).toByteArray()
        def nom_fichier = "rapport_des_Entrepots.xls"
        Filedownload.save(bit, "application/file", nom_fichier);
        
    }
    
   
    /**
     * Fonction qui copie la valeur de l'association � l'�l�ment courant
     **/
    def actualiserValeurAssociation() {
        
        objet.modeValorisation = modeValorisationSelected
        def bindermodeValorisation = new AnnotateDataBinder(this.getFellow("coModeValorisation"))
        //modeValorisationSelected = modeValorisation.get(0)
        bindermodeValorisation.loadAll()
    }
    /**
     * Fonction qui fait la liaison entre l'association l'�l�ment selectionn� et la liste dans le crud
     **/
    def afficherValeurAssociation() {
        def bindermodeValorisation = new AnnotateDataBinder(this.getFellow("coModeValorisation"))
        modeValorisationSelected = objet.modeValorisation
        bindermodeValorisation.loadAll()
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
            afficherValeurAssociation()
            rafraichirField()
        }
    }
    
    def addOrUpdate(){
        actualiserValeurAssociation()
        if (isUpdate) {
            this.update()
            isUpdate = false
        } else {
            this.add()
        }
    }
 
}

