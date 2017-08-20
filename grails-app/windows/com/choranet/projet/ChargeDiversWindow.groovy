
package com.choranet.projet
    

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

/**
 * ChargeDivers Window Object
 **/
class ChargeDiversWindow extends SuperWindow {
    /**
     * Service pour la gestion de l'objet ChargeDivers
     **/
    def chargeDiversService
    
    /**
     * Service jasper pour la generation des rapports
     **/
    def jasperService
    /**
     * Logger de la class ChargeDiversWindow
     **/
    private Log logger = LogFactory.getLog(ChargeDiversWindow.class)
    
    /**
     * liste de projet
     **/	
    def projets	
    def projetsFilter	
    /**
     * projet  selectionn�
     **/
    def projetSelected
    
    def projetService
                
    /**
     * Constructeur
     **/
    public ChargeDiversWindow (projetService) {
        super(ChargeDivers.class)
        this.projetService = projetService
        specialeInitialisation()
    }  

    def update() {
        super.update()
        actualiserListProjets()
    }
    
    def delete() {
        super.delete()
        actualiserListProjets()
    }
    def cancel() {
        super.cancel()
        actualiserListProjets()
    }
    
    def actualiserListProjets() {
        projets = projetService.getProjetsNonTerminer()
        if(projets.size() > 0)
        projetSelected = projets.get(0)
        else
        projetSelected = null
        new AnnotateDataBinder(this.getFellow("coprojets")).loadAll()
        
    }
    
    def specialeInitialisation() {
        projetsFilter = Projet.list()		
        projets = projetService.getProjetsNonTerminer()		
        if(projets.size() > 0)
        projetSelected = projets.get(0)
        else
        projetSelected = null
    }
    protected SuperService getService() {
        return this.chargeDiversService
    }
    
    /**
     * Generation du rapport pdf
     **/
    def genererRapportPdf() {
        String nomsociete = session.societe.raisonSociale
        String titrerapport = "Rapport des ChargeDiverss"
        def listeObjetsExporter = getObjetsToExport()
        def reportDef = new JasperReportDef(name:'rapport_des_ChargeDiverss.jrxml',
            fileFormat:JasperExportFormat.PDF_FORMAT,
            reportData : listeObjetsExporter,
            parameters : [  'nomsociete':nomsociete, 'titrerapport':titrerapport ]
        )
        def bit = jasperService.generateReport(reportDef).toByteArray()
        def nom_fichier = "rapport_des_ChargeDiverss.pdf"
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
        String titrerapport = "Rapport des ChargeDiverss"
        def listeObjetsExporter = getObjetsToExport()
        def reportDef = new JasperReportDef(name:'rapport_des_ChargeDiverss.jrxml',
            fileFormat:JasperExportFormat.XLS_FORMAT,
            reportData : listeObjetsExporter,
            parameters : [  'nomsociete':nomsociete, 'titrerapport':titrerapport ]
        )
        def bit = jasperService.generateReport(reportDef).toByteArray()
        def nom_fichier = "rapport_des_ChargeDiverss.xls"
        Filedownload.save(bit, "application/file", nom_fichier);
        
    }
    
    /**
     * Fonction qui copie la valeur de l'association � l'�l�ment courant
     **/
    def actualiserValeurAssociation() {        		
        objet.projet = projetSelected        
    }
    /**
     * Fonction qui fait la liaison entre l'association l'�l�ment selectionn� et la liste dans le crud
     **/
    def afficherValeurAssociation() {
        		
        projets.add(objetSelected.projet)
        def binderprojet = new AnnotateDataBinder(this.getFellow("coprojets"))
        projetSelected = projets.find{ it.id == ChargeDivers.findById(objet.id).projet.id }
        binderprojet.loadAll()
                    
    }
}

