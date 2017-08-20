
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
 * Tache Window Object
 **/
class TacheWindow extends SuperWindow {
    /**
     * Service pour la gestion de l'objet Tache
     **/
    def tacheService
    
    /**
     * Service jasper pour la generation des rapports
     **/
    def jasperService
    /**
     * Logger de la class TacheWindow
     **/
    private Log logger = LogFactory.getLog(TacheWindow.class)
    
                /**
                 * liste de predecesseur
                 **/
                def predecesseur
                /**
                 * predecesseur selectionn�
                 **/
                def predecesseurSelected
                
                /**
                 * liste de phase
                 **/	
                def phases	
                /**
                 * phase  selectionn�
                 **/
                def phaseSelected
                
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
    public TacheWindow () {
        super(Tache.class)
    }  

    protected SuperService getService() {
        return this.tacheService
    }
    
    /**
     * Generation du rapport pdf
     **/
    def genererRapportPdf() {
        String nomsociete = session.societe.raisonSociale
        String titrerapport = "Rapport des Taches"
        def listeObjetsExporter = getObjetsToExport()
        def reportDef = new JasperReportDef(name:'rapport_des_Taches.jrxml',
            fileFormat:JasperExportFormat.PDF_FORMAT,
            reportData : listeObjetsExporter,
            parameters : [  'nomsociete':nomsociete, 'titrerapport':titrerapport ]
        )
        def bit = jasperService.generateReport(reportDef).toByteArray()
        def nom_fichier = "rapport_des_Taches.pdf"
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
        String titrerapport = "Rapport des Taches"
        def listeObjetsExporter = getObjetsToExport()
        def reportDef = new JasperReportDef(name:'rapport_des_Taches.jrxml',
            fileFormat:JasperExportFormat.XLS_FORMAT,
            reportData : listeObjetsExporter,
            parameters : [  'nomsociete':nomsociete, 'titrerapport':titrerapport ]
        )
        def bit = jasperService.generateReport(reportDef).toByteArray()
        def nom_fichier = "rapport_des_Taches.xls"
        Filedownload.save(bit, "application/file", nom_fichier);
        
    }
    
    
    /**
     * Fonction qui g�re l'initialisation des listes d'associations au niveau du constructeur
     **/
    def initialiserAssociation() {
        
                    predecesseur = Predecesseuroso.list()
                    predecesseurSelected = null// = new ArrayList()
                    
                    phases = Phase.list()		
                    if(phases.size() > 0)
                    phaseSelected = phases.get(0)
                    else
                    phaseSelected = null
                    
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
			predecesseur = Predecesseuroso.list()
                    }
                    this.getFellow("lstpredecesseur").clearSelection()
                    predecesseurSelected = null// = new ArrayList()
                    	
                    if(del) {
			phases = Phase.list()
                    }	
                    if(phases.size() > 0)
                    phaseSelected = phases.get(0)
                    else
                    phaseSelected = null
                    	
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
        	
                    objet.predecesseur = predecesseurSelected
                    this.getFellow("lstpredecesseur").clearSelection()
                    predecesseurSelected = null// = new ArrayList()
                    		
                    objet.phase = phaseSelected
                    if(phases.size() > 0) {
			def binderphase = new AnnotateDataBinder(this.getFellow("cophases"))
			phaseSelected = phases.get(0)
			binderphase.loadAll()
                    }
                    else
                    phaseSelected = null
                    		
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
        
                    def binderpredecesseur = new AnnotateDataBinder(this.getFellow("lstpredecesseur"))
                    predecesseurSelected = objetSelected.predecesseur
                    binderpredecesseur.loadAll()		
                    		
                    def binderphase = new AnnotateDataBinder(this.getFellow("cophases"))
                    phaseSelected = phases.find{ it.id == Tache.findById(objet.id).phase.id }
                    binderphase.loadAll()
                    		
                    def binderemploye = new AnnotateDataBinder(this.getFellow("coemployes"))
                    employeSelected = employes.find{ it.id == Tache.findById(objet.id).employe.id }
                    binderemploye.loadAll()
                    
    }
}

