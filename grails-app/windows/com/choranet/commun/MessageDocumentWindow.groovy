
    package com.choranet.commun
    

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

/**
 * MessageDocument Window Object
 **/
class MessageDocumentWindow extends SuperWindow {
    /**
     * Service pour la gestion de l'objet MessageDocument
     **/
    def messageDocumentService
    
    /**
     * Service jasper pour la generation des rapports
     **/
    def jasperService
    /**
     * Logger de la class MessageDocumentWindow
     **/
    private Log logger = LogFactory.getLog(MessageDocumentWindow.class)
    
    /**
     * Constructeur
     **/
    public MessageDocumentWindow () {
        super(MessageDocument.class)
    }  

    protected SuperService getService() {
        return this.messageDocumentService
    }
    
    /**
     * Generation du rapport pdf
     **/
    def genererRapportPdf() {
        String nomsociete = session.societe.raisonSociale
        String titrerapport = "Rapport des MessageDocuments"
        def listeObjetsExporter = getObjetsToExport()
        def reportDef = new JasperReportDef(name:'rapport_des_MessageDocuments.jrxml',
            fileFormat:JasperExportFormat.PDF_FORMAT,
            reportData : listeObjetsExporter,
            parameters : [  'nomsociete':nomsociete, 'titrerapport':titrerapport ]
        )
        def bit = jasperService.generateReport(reportDef).toByteArray()
        def nom_fichier = "rapport_des_MessageDocuments.pdf"
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
        String titrerapport = "Rapport des MessageDocuments"
        def listeObjetsExporter = getObjetsToExport()
        def reportDef = new JasperReportDef(name:'rapport_des_MessageDocuments.jrxml',
            fileFormat:JasperExportFormat.XLS_FORMAT,
            reportData : listeObjetsExporter,
            parameters : [  'nomsociete':nomsociete, 'titrerapport':titrerapport ]
        )
        def bit = jasperService.generateReport(reportDef).toByteArray()
        def nom_fichier = "rapport_des_MessageDocuments.xls"
        Filedownload.save(bit, "application/file", nom_fichier);
        
    }
    
    def activerBoutons(visible) {
        this.getFellow("btnUpdate").visible = visible
        this.getFellow("btnCancel").visible = true
        this.getFellow("westPanel").open = visible        
    }

    def update() {
        logger.debug(this.getFellow("fieldEntete").value)
        objet.entete = this.getFellow("fieldEntete").value
        logger.debug(objet.entete)
        logger.debug(this.getFellow("fieldPied").value)
        objet.pied = this.getFellow("fieldPied").value
        logger.debug(objet.pied)
        super.update()
    }
}

