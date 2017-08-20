
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
 * CategorieProduit Window Object
 **/
class CategorieProduitWindow extends SuperWindow {
    /**
     * Service pour la gestion de l'objet CategorieProduit
     **/
    def categorieProduitService
    
    /**
     * Service jasper pour la generation des rapports
     **/
    def jasperService
    /**
     * Logger de la class CategorieProduitWindow
     **/
    private Log logger = LogFactory.getLog(CategorieProduitWindow.class)
                
    def isUpdate
    
    //def basicName = null
    
    def excelImporterService
    
    /**
     * Constructeur
     **/
    public CategorieProduitWindow () {
        super(CategorieProduit.class)
    }  

    protected SuperService getService() {
        return this.categorieProduitService
    }
    
    def importation(media) {
        String resultat = excelImporterService.importerCategoriesProduits(media)
        Messagebox.show(resultat, "Notification" ,  Messagebox.OK, Messagebox.INFORMATION)
        rafraichirList()
    }
    /**
     * Generation du rapport pdf
     **/
    def genererRapportPdf() {
        String nomsociete = session.societe.raisonSociale
        String titrerapport = "Rapport des CategorieProduits"
        def listeObjetsExporter = getObjetsToExport()
        def reportDef = new JasperReportDef(name:'rapport_des_CategorieProduits.jrxml',
            fileFormat:JasperExportFormat.PDF_FORMAT,
            reportData : listeObjetsExporter,
            parameters : [  'nomsociete':nomsociete, 'titrerapport':titrerapport ]
        )
        def bit = jasperService.generateReport(reportDef).toByteArray()
        def nom_fichier = "rapport_des_CategorieProduits.pdf"
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
        String titrerapport = "Rapport des CategorieProduits"
        def listeObjetsExporter = getObjetsToExport()
        def reportDef = new JasperReportDef(name:'rapport_des_CategorieProduits.jrxml',
            fileFormat:JasperExportFormat.XLS_FORMAT,
            reportData : listeObjetsExporter,
            parameters : [  'nomsociete':nomsociete, 'titrerapport':titrerapport ]
        )
        def bit = jasperService.generateReport(reportDef).toByteArray()
        def nom_fichier = "rapport_des_CategorieProduits.xls"
        Filedownload.save(bit, "application/file", nom_fichier);
        
    }
    
    def add() {
        super.add()
    }
    
    def update() {
        super.update()
    }
    
    def delete() {
        super.delete()
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
            //getCategorieFullNameForUpdate()
            this.update()
            isUpdate = false
        } else {
            this.add()
        }
    }        
    
}

