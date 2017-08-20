
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
 * OutilLouable Window Object
 **/
class OutilLouableWindow extends SuperWindow {
    /**
     * Service pour la gestion de l'objet OutilLouable
     **/
    def outilLouableService
    
    /**
     * Service jasper pour la generation des rapports
     **/
    def jasperService
    /**
     * Logger de la class OutilLouableWindow
     **/
    private Log logger = LogFactory.getLog(OutilLouableWindow.class)
    
                /**
                 * liste de loueur
                 **/	
                def loueurs	
                /**
                 * loueur  selectionn�
                 **/
                def loueurSelected
                
    /**
     * Constructeur
     **/
    public OutilLouableWindow () {
        super(OutilLouable.class)
    }  

    protected SuperService getService() {
        return this.outilLouableService
    }
    
    /**
     * Generation du rapport pdf
     **/
    def genererRapportPdf() {
        String nomsociete = session.societe.raisonSociale
        String titrerapport = "Rapport des OutilLouables"
        def listeObjetsExporter = getObjetsToExport()
        def reportDef = new JasperReportDef(name:'rapport_des_OutilLouables.jrxml',
            fileFormat:JasperExportFormat.PDF_FORMAT,
            reportData : listeObjetsExporter,
            parameters : [  'nomsociete':nomsociete, 'titrerapport':titrerapport ]
        )
        def bit = jasperService.generateReport(reportDef).toByteArray()
        def nom_fichier = "rapport_des_OutilLouables.pdf"
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
        String titrerapport = "Rapport des OutilLouables"
        def listeObjetsExporter = getObjetsToExport()
        def reportDef = new JasperReportDef(name:'rapport_des_OutilLouables.jrxml',
            fileFormat:JasperExportFormat.XLS_FORMAT,
            reportData : listeObjetsExporter,
            parameters : [  'nomsociete':nomsociete, 'titrerapport':titrerapport ]
        )
        def bit = jasperService.generateReport(reportDef).toByteArray()
        def nom_fichier = "rapport_des_OutilLouables.xls"
        Filedownload.save(bit, "application/file", nom_fichier);
        
    }
    
    
    /**
     * Fonction qui g�re l'initialisation des listes d'associations au niveau du constructeur
     **/
    def initialiserAssociation() {
        
                    loueurs = Loueur.list()		
                    if(loueurs.size() > 0)
                    loueurSelected = loueurs.get(0)
                    else
                    loueurSelected = null
                    
    }
    /**
     * Fonction qui permet de r�-initaliser l'association au niveau de l'interface
     * @param del si c'est une r�initionalisation apr�s une suppression ou non
     **/
    def reinitialiserAssociation(del) {
        	
                    if(del) {
			loueurs = Loueur.list()
                    }	
                    if(loueurs.size() > 0)
                    loueurSelected = loueurs.get(0)
                    else
                    loueurSelected = null
                    
    }
    /**
     * Fonction qui copie la valeur de l'association � l'�l�ment courant
     **/
    def actualiserValeurAssociation() {
        		
                    objet.loueur = loueurSelected
                    if(loueurs.size() > 0) {
			def binderloueur = new AnnotateDataBinder(this.getFellow("coloueurs"))
			loueurSelected = loueurs.get(0)
			binderloueur.loadAll()
                    }
                    else
                    loueurSelected = null
                    
    }
    /**
     * Fonction qui fait la liaison entre l'association l'�l�ment selectionn� et la liste dans le crud
     **/
    def afficherValeurAssociation() {
        		
                    def binderloueur = new AnnotateDataBinder(this.getFellow("coloueurs"))
                    loueurSelected = loueurs.find{ it.id == OutilLouable.findById(objet.id).loueur.id }
                    binderloueur.loadAll()
                    
    }
}

