
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
 * AffectationOutil Window Object
 **/
class AffectationOutilWindow extends SuperWindow {
    /**
     * Service pour la gestion de l'objet AffectationOutil
     **/
    def affectationOutilService
    
    /**
     * Service jasper pour la generation des rapports
     **/
    def jasperService
    /**
     * Logger de la class AffectationOutilWindow
     **/
    private Log logger = LogFactory.getLog(AffectationOutilWindow.class)
    
    /**
     * liste de outilEmploye
     **/	
    def outilEmployes	
    /**
     * outilEmploye  selectionn�
     **/
    def outilEmployeSelected
                
    /**
     * liste de projet
     **/	
    def projets	
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
    def projetsFiltre
    def projetFiltreSelected  
    
    /**
     * Constructeur
     **/
    public AffectationOutilWindow (projetService) {
        super(AffectationOutil.class)
        this.projetService = projetService
        
        this.specialeInitialisation()
    }  

    def outilSelectionner() {
        employeSelected = employes.find{ it.id == outilEmployeSelected.employe.id }
        new AnnotateDataBinder(this.getFellow("coemployes")).loadAll()
        objet.coutHoraire = outilEmployeSelected.coutHoraire
        new AnnotateDataBinder(this.getFellow("fieldCoutHoraire")).loadAll()
    }
    def specialeInitialisation() {
        
        projetsFiltre = Projet.list()
        
        employes = Employe.list()
        outilEmployes = OutilEmploye.list()		
        if(outilEmployes.size() > 0) {
            outilEmployeSelected = outilEmployes.get(0)
            employeSelected = employes.find{ it.id == outilEmployeSelected.employe.id }
            objet.coutHoraire = outilEmployeSelected.coutHoraire
        } else {           
            if(employes.size() > 0) {
                employeSelected = employes.get(0)
            }            
        }
        
        projets = projetService.getProjetsNonTerminer()
        if(projets.size() > 0)
        projetSelected = projets.get(0)                                 		
        
    }
    
    protected SuperService getService() {
        return this.affectationOutilService
    }
    
    /**
     * Generation du rapport pdf
     **/
    def genererRapportPdf() {
        String nomsociete = session.societe.raisonSociale
        String titrerapport = "Rapport des AffectationOutils"
        def listeObjetsExporter = getObjetsToExport()
        def reportDef = new JasperReportDef(name:'rapport_des_AffectationOutils.jrxml',
            fileFormat:JasperExportFormat.PDF_FORMAT,
            reportData : listeObjetsExporter,
            parameters : [  'nomsociete':nomsociete, 'titrerapport':titrerapport ]
        )
        def bit = jasperService.generateReport(reportDef).toByteArray()
        def nom_fichier = "rapport_des_AffectationOutils.pdf"
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
        String titrerapport = "Rapport des AffectationOutils"
        def listeObjetsExporter = getObjetsToExport()
        def reportDef = new JasperReportDef(name:'rapport_des_AffectationOutils.jrxml',
            fileFormat:JasperExportFormat.XLS_FORMAT,
            reportData : listeObjetsExporter,
            parameters : [  'nomsociete':nomsociete, 'titrerapport':titrerapport ]
        )
        def bit = jasperService.generateReport(reportDef).toByteArray()
        def nom_fichier = "rapport_des_AffectationOutils.xls"
        Filedownload.save(bit, "application/file", nom_fichier);
        
    }
    
    /**
     * Fonction qui permet de r�-initaliser l'association au niveau de l'interface
     * @param del si c'est une r�initionalisation apr�s une suppression ou non
     **/
    def reinitialiserAssociation(del) {
        if(outilEmployeSelected != null) {
            employeSelected = employes.find{ it.id == outilEmployeSelected.employe.id }
            objet.coutHoraire = outilEmployeSelected.coutHoraire
        }
    }
    

    def add() {
        super.add()
        if(outilEmployeSelected != null) {
            objet.coutHoraire = outilEmployeSelected.coutHoraire
            new AnnotateDataBinder(this.getFellow("fieldCoutHoraire")).loadAll()
        }
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
    /**
     * Fonction qui copie la valeur de l'association � l'�l�ment courant
     **/
    def actualiserValeurAssociation() {
        		
        objet.outilEmploye = outilEmployeSelected
        objet.employe = employeSelected
        
        if(outilEmployes.size() > 0) {
            def binderoutilEmploye = new AnnotateDataBinder(this.getFellow("cooutilEmployes"))
            outilEmployeSelected = outilEmployes.get(0)
            binderoutilEmploye.loadAll()            
            employeSelected = employes.find{ it.id == outilEmployeSelected.employe.id }
            new AnnotateDataBinder(this.getFellow("coemployes")).loadAll()            
        } else {
            outilEmployeSelected = null
            if(employes.size() > 0) {
                def binderemploye = new AnnotateDataBinder(this.getFellow("coemployes"))
                employeSelected = employes.get(0)
                binderemploye.loadAll()
            }
            else
            employeSelected = null
        }
                    		
        objet.projet = projetSelected
        if(projets.size() > 0) {
            def binderprojet = new AnnotateDataBinder(this.getFellow("coprojets"))
            projetSelected = projets.get(0)
            binderprojet.loadAll()
        }
        else
        projetSelected = null
                    
    }
    /**
     * Fonction qui fait la liaison entre l'association l'�l�ment selectionn� et la liste dans le crud
     **/
    def afficherValeurAssociation() {
        AffectationOutil.withTransaction {
            def index = 0;
            for(oe in outilEmployes) {
                if(oe.id == objet.outilEmploye.id) {
                    outilEmployeSelected = oe
                    break;
                }
                index++
            }
            this.getFellow("cooutilEmployes").setSelectedIndex(index)
            
            index = 0;
            for(em in employes) {
                if(em.id == objet.employe.id) {
                    employeSelected = em
                    break;
                }
                index++
            }
            logger.debug("Employe : " + employeSelected + " position : " + index)
            this.getFellow("coemployes").setSelectedIndex(index)
            
            projets = [objet.projet]
            Combobox cb = this.getFellow("coprojets");
            cb.setModel(new BindingListModelList(projets, false))
            projetSelected = objet.projet
            this.getFellow("coprojets").setSelectedIndex(0)
             		
        }
    }
}

