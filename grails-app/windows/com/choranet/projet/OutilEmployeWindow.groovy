
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
import com.choranet.rh.Employe                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                  

/**
 * OutilEmploye Window Object
 **/
class OutilEmployeWindow extends SuperWindow {
    /**
     * Service pour la gestion de l'objet OutilEmploye
     **/
    def outilEmployeService
    
    /**
     * Service jasper pour la generation des rapports
     **/
    def jasperService
    /**
     * Logger de la class OutilEmployeWindow
     **/
    private Log logger = LogFactory.getLog(OutilEmployeWindow.class)
    
                /**
                 * liste de employe
                 **/	
                def employes	
                /**
                 * employe  selectionn�
                 **/
                def employeSelected
                
    /**
     * Constructeur
     **/
    public OutilEmployeWindow () {
        super(OutilEmploye.class)
    }  

    protected SuperService getService() {
        return this.outilEmployeService
    }
    
    /**
     * Generation du rapport pdf
     **/
    def genererRapportPdf() {
        String nomsociete = session.societe.raisonSociale
        String titrerapport = "Rapport des OutilEmployes"
        def listeObjetsExporter = getObjetsToExport()
        def reportDef = new JasperReportDef(name:'rapport_des_OutilEmployes.jrxml',
            fileFormat:JasperExportFormat.PDF_FORMAT,
            reportData : listeObjetsExporter,
            parameters : [  'nomsociete':nomsociete, 'titrerapport':titrerapport ]
        )
        def bit = jasperService.generateReport(reportDef).toByteArray()
        def nom_fichier = "rapport_des_OutilEmployes.pdf"
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
        String titrerapport = "Rapport des OutilEmployes"
        def listeObjetsExporter = getObjetsToExport()
        def reportDef = new JasperReportDef(name:'rapport_des_OutilEmployes.jrxml',
            fileFormat:JasperExportFormat.XLS_FORMAT,
            reportData : listeObjetsExporter,
            parameters : [  'nomsociete':nomsociete, 'titrerapport':titrerapport ]
        )
        def bit = jasperService.generateReport(reportDef).toByteArray()
        def nom_fichier = "rapport_des_OutilEmployes.xls"
        Filedownload.save(bit, "application/file", nom_fichier);
        
    }
    
    
    /**
     * Fonction qui g�re l'initialisation des listes d'associations au niveau du constructeur
     **/
    def initialiserAssociation() {
        
                    employes = Employe.list()		
                    if(employes.size() > 0)
                    employeSelected = employes.get(0)
                    else
                    employeSelected = null
                    
    }
    /**
     * Fonction qui permet de r�-initaliser l'association au niveau de l'interface
     * @param del si c'est une r�initionalisation apr�s une suppression ou non
     **/
    def reinitialiserAssociation(del) {
        	
                    if(del) {
			employes = Employe.list()
                    }	
                    if(employes.size() > 0)
                    employeSelected = employes.get(0)
                    else
                    employeSelected = null
                    
    }
    /**
     * Fonction qui copie la valeur de l'association � l'�l�ment courant
     **/
    def actualiserValeurAssociation() {
        		
                    objet.employe = employeSelected
                    if(employes.size() > 0) {
			def binderemploye = new AnnotateDataBinder(this.getFellow("coemployes"))
			employeSelected = employes.get(0)
			binderemploye.loadAll()
                    }
                    else
                    employeSelected = null
                    
    }
    /**
     * Fonction qui fait la liaison entre l'association l'�l�ment selectionn� et la liste dans le crud
     **/
    def afficherValeurAssociation() {
        		
                    def binderemploye = new AnnotateDataBinder(this.getFellow("coemployes"))
                    employeSelected = employes.find{ it.id == OutilEmploye.findById(objet.id).employe.id }
                    binderemploye.loadAll()
                    
    }
}

