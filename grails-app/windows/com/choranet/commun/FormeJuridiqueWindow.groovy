
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
 * FormeJuridique Window Object
 **/
class FormeJuridiqueWindow extends SuperWindow {
    /**
     * Service pour la gestion de l'objet FormeJuridique
     **/
    def formeJuridiqueService
    
    /**
     * Service jasper pour la generation des rapports
     **/
    def jasperService
    /**
     * Logger de la class FormeJuridiqueWindow
     **/
    private Log logger = LogFactory.getLog(FormeJuridiqueWindow.class)
    
    def isUpdate
    
    /**
     * Constructeur
     **/
    public FormeJuridiqueWindow () {
        super(FormeJuridique.class)
    }  

    protected SuperService getService() {
        return this.formeJuridiqueService
    }
    
    /**
     * Generation du rapport pdf
     **/
    def genererRapportPdf() {
        String nomsociete = session.societe.raisonSociale
        String titrerapport = "Rapport des FormeJuridiques"
        def listeObjetsExporter = getObjetsToExport()
        def reportDef = new JasperReportDef(name:'rapport_des_FormeJuridiques.jrxml',
            fileFormat:JasperExportFormat.PDF_FORMAT,
            reportData : listeObjetsExporter,
            parameters : [  'nomsociete':nomsociete, 'titrerapport':titrerapport ]
        )
        def bit = jasperService.generateReport(reportDef).toByteArray()
        def nom_fichier = "rapport_des_FormeJuridiques.pdf"
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
        String titrerapport = "Rapport des FormeJuridiques"
        def listeObjetsExporter = getObjetsToExport()
        def reportDef = new JasperReportDef(name:'rapport_des_FormeJuridiques.jrxml',
            fileFormat:JasperExportFormat.XLS_FORMAT,
            reportData : listeObjetsExporter,
            parameters : [  'nomsociete':nomsociete, 'titrerapport':titrerapport ]
        )
        def bit = jasperService.generateReport(reportDef).toByteArray()
        def nom_fichier = "rapport_des_FormeJuridiques.xls"
        Filedownload.save(bit, "application/file", nom_fichier);
        
    }
    
    def select() {  
        isUpdate = true
        objet = objetSelected
        rafraichirField()
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
            Messagebox.show("La nouvelle entré est déjà supportée par le système\n", "Ajout non disponible", Messagebox.OK, Messagebox.ERROR)
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
            Messagebox.show("La nouvelle valeur est dèjà mentionnée pour une forme existante", "Mise à jour non disponible", Messagebox.OK, Messagebox.ERROR)
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
            Messagebox.show("La présente forme est déjà associée à un partenaire\n", "Suppression non disponible", Messagebox.OK, Messagebox.ERROR)
        }
        objet = clazz.newInstance()
        rafraichirField()
        rafraichirList()
    }
}

