
package com.choranet.rh
    

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
import com.choranet.projet.Projet

/**
 * FichePresence Window Object
 **/
class FichePresenceWindow extends SuperWindow {
    /**
     * Service pour la gestion de l'objet FichePresence
     **/
    def fichePresenceService
    
    /**
     * Service jasper pour la generation des rapports
     **/
    def jasperService
    /**
     * Logger de la class FichePresenceWindow
     **/
    private Log logger = LogFactory.getLog(FichePresenceWindow.class)
    
    /**
     * liste de projet
     **/	
    def projets	
    def projetsFilter	
    /**
     * projet  selectionn�
     **/
    def projetSelected
                
    /**
     * liste de employe
     **/	
    def employes	
    /**
     * employe  selectionn�
     **/
    def employeSelected
    
    def projetService
                
    /**
     * Constructeur
     **/
    public FichePresenceWindow (projetService) {
        super(FichePresence.class)
        this.projetService = projetService
        specialeInitialisation()
    }  

    def specialeInitialisation() {
        projetsFilter = Projet.list()		
        projets = projetService.getProjetsNonTerminer()
        if(projets.size() > 0)
        projetSelected = projets.get(0)
        else
        projetSelected = null
                    
        employes = Employe.list()		
        if(employes.size() > 0) {
            employeSelected = employes.get(0)
            objet.tauxHoraire = employeSelected.tauxHoraire
        }
        else
        employeSelected = null
        
        
    }
    
    def add() {
        super.add()
        employeChanger()
    }
    def cancel() {
        super.cancel()
        employeChanger()
        actualiserListProjets()
    }
    def update() {
        super.update()
        employeChanger()
        actualiserListProjets()
    }
    def delete() {
        super.delete()
        employeChanger()
        actualiserListProjets()
    }
    def employeChanger() {
        if(employeSelected != null) {
            objet.tauxHoraire = employeSelected.tauxHoraire
            new AnnotateDataBinder(this.getFellow("fieldTauxHoraire")).loadAll()
        }
    }
    def actualiserListProjets() {
        projets = projetService.getProjetsNonTerminer()
        if(projets.size() > 0)
        projetSelected = projets.get(0)
        else
        projetSelected = null
        new AnnotateDataBinder(this.getFellow("coprojets")).loadAll()
        
    }
    protected SuperService getService() {
        return this.fichePresenceService
    }
    
    /**
     * Generation du rapport pdf
     **/
    def genererRapportPdf() {
        String nomsociete = session.societe.raisonSociale
        String titrerapport = "Rapport des FichePresences"
        def listeObjetsExporter = getObjetsToExport()
        def reportDef = new JasperReportDef(name:'rapport_des_FichePresences.jrxml',
            fileFormat:JasperExportFormat.PDF_FORMAT,
            reportData : listeObjetsExporter,
            parameters : [  'nomsociete':nomsociete, 'titrerapport':titrerapport ]
        )
        def bit = jasperService.generateReport(reportDef).toByteArray()
        def nom_fichier = "rapport_des_FichePresences.pdf"
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
        String titrerapport = "Rapport des FichePresences"
        def listeObjetsExporter = getObjetsToExport()
        def reportDef = new JasperReportDef(name:'rapport_des_FichePresences.jrxml',
            fileFormat:JasperExportFormat.XLS_FORMAT,
            reportData : listeObjetsExporter,
            parameters : [  'nomsociete':nomsociete, 'titrerapport':titrerapport ]
        )
        def bit = jasperService.generateReport(reportDef).toByteArray()
        def nom_fichier = "rapport_des_FichePresences.xls"
        Filedownload.save(bit, "application/file", nom_fichier);
        
    }
   
    /**
     * Fonction qui copie la valeur de l'association � l'�l�ment courant
     **/
    def actualiserValeurAssociation() {
        objet.projet = projetSelected
        objet.employe = employeSelected
    }
    /**
     * Fonction qui fait la liaison entre l'association l'�l�ment selectionn� et la liste dans le crud
     **/
    def afficherValeurAssociation() {
        
        projets.add(objetSelected.projet)        
        def binderprojet = new AnnotateDataBinder(this.getFellow("coprojets"))
        projetSelected = projets.find{ it.id == FichePresence.findById(objet.id).projet.id }
        binderprojet.loadAll()
                    		
        def binderemploye = new AnnotateDataBinder(this.getFellow("coemployes"))
        employeSelected = employes.find{ it.id == FichePresence.findById(objet.id).employe.id }
        binderemploye.loadAll()
                    
    }
}

