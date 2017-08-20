
package com.choranet.compta
 
import com.choranet.commun.SuperWindow
import com.choranet.commun.SuperService
import com.choranet.commun.ChoraClientInfo
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
 * Periode Window Object
 **/
class PeriodeWindow extends SuperWindow {
    /**
     * Service pour la gestion de l'objet Periode
     **/
    def periodeService
    
    /**
     * Service jasper pour la generation des rapports
     **/
    def jasperService
    /**
     * Logger de la class PeriodeWindow
     **/
    private Log logger = LogFactory.getLog(PeriodeWindow.class)
    
    /**
     * liste de exercice
     **/	
    def exercices	
    /**
     * exercice  selectionne
     **/
    def exerciceSelected
                
    /**
     * Constructeur
     **/
    public PeriodeWindow () {
        super(Periode.class)
    }  

    protected SuperService getService() {
        return this.periodeService
    }
    
    /**
     * Generation du rapport pdf
     **/
    def genererRapportPdf() {
        String nomsociete = session.societe.raisonSociale
        String titrerapport = "Rapport des Periodes"
        def listeObjetsExporter = getObjetsToExport()
        def reportDef = new JasperReportDef(name:'rapport_des_Periodes.jrxml',
            fileFormat:JasperExportFormat.PDF_FORMAT,
            reportData : listeObjetsExporter,
            parameters : [  'nomsociete':nomsociete, 'titrerapport':titrerapport ]
        )
        def bit = jasperService.generateReport(reportDef).toByteArray()
        def nom_fichier = "rapport_des_Periodes.pdf"
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
        String titrerapport = "Rapport des Periodes"
        def listeObjetsExporter = getObjetsToExport()
        def reportDef = new JasperReportDef(name:'rapport_des_Periodes.jrxml',
            fileFormat:JasperExportFormat.XLS_FORMAT,
            reportData : listeObjetsExporter,
            parameters : [  'nomsociete':nomsociete, 'titrerapport':titrerapport ]
        )
        def bit = jasperService.generateReport(reportDef).toByteArray()
        def nom_fichier = "rapport_des_Periodes.xls"
        Filedownload.save(bit, "application/file", nom_fichier);
        
    }
    
    
    /**
     * Fonction qui gere l'initialisation des listes d'associations au niveau du constructeur
     **/
    def initialiserAssociation() {
        
        exercices = Exercice.list()		
        if(exercices.size() > 0)
        exerciceSelected = exercices.get(0)
        else
        exerciceSelected = null
                    
    }
    /**
     * Fonction qui permet de re-initaliser l'association au niveau de l'interface
     * @param del si c'est une reinitionalisation apres une suppression ou non
     **/
    def reinitialiserAssociation(del) {
        	
        if(del) {
            exercices = Exercice.list()
        }	
        if(exercices.size() > 0)
        exerciceSelected = exercices.get(0)
        else
        exerciceSelected = null
                    
    }
    /**
     * Fonction qui copie la valeur de l'association e l'element courant
     **/
    def actualiserValeurAssociation() {
        		
        objet.exercice = exerciceSelected
        if(exercices.size() > 0) {
            def binderexercice = new AnnotateDataBinder(this.getFellow("coexercices"))
            exerciceSelected = exercices.get(0)
            binderexercice.loadAll()
        }
        else
        exerciceSelected = null
                    
    }
    /**
     * Fonction qui fait la liaison entre l'association l'element selectionne et la liste dans le crud
     **/
    def afficherValeurAssociation() {
        		
        def binderexercice = new AnnotateDataBinder(this.getFellow("coexercices"))
        exerciceSelected = exercices.find{ it.id == Periode.findById(objet.id).exercice.id }
        binderexercice.loadAll()
                    
    }
}

