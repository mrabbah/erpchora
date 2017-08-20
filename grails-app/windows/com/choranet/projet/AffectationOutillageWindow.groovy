
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
 * AffectationOutillage Window Object
 **/
class AffectationOutillageWindow extends SuperWindow {
    /**
     * Service pour la gestion de l'objet AffectationOutillage
     **/
    def affectationOutillageService
    
    /**
     * Service jasper pour la generation des rapports
     **/
    def jasperService
    /**
     * Logger de la class AffectationOutillageWindow
     **/
    private Log logger = LogFactory.getLog(AffectationOutillageWindow.class)
    
    /**
     * liste de projet
     **/	
    def projets	
    /**
     * projet  selectionn�
     **/
    def projetSelected
                
    /**
     * liste de outillageCollectif
     **/	
    def outillageCollectifs	
    def outillageCollectifsFilter	
    /**
     * outillageCollectif  selectionn�
     **/
    def outillageCollectifSelected
                
    /**
     * liste de employe
     **/	
    def employes	
    /**
     * employe  selectionn�
     **/
    def employeSelected
                
    def projetService
    def projetsFilter
    /**
     * Constructeur
     **/
    public AffectationOutillageWindow (projetService, affectationOutillageService) {
        super(AffectationOutillage.class)
    
        this.projetService = projetService
        this.affectationOutillageService = affectationOutillageService
        specialeInitialisation()
    }  

    def specialeInitialisation() {
        projetsFilter = Projet.list()
        projets = projetService.getProjetsNonTerminer()	
        if(projets.size() > 0)
        projetSelected = projets.get(0)
        else
        projetSelected = null
                    
        outillageCollectifsFilter = OutillageCollectif.list()
        outillageCollectifs = affectationOutillageService.getOutillageNonAffecter()
        if(outillageCollectifs.size() > 0)
        outillageCollectifSelected = outillageCollectifs.get(0)
        else
        outillageCollectifSelected = null
                    
        employes = Employe.list()		
        if(employes.size() > 0)
        employeSelected = employes.get(0)
        else
        employeSelected = null
    }
    
    protected SuperService getService() {
        return this.affectationOutillageService
    }
    
    /**
     * Generation du rapport pdf
     **/
    def genererRapportPdf() {
        String nomsociete = session.societe.raisonSociale
        String titrerapport = "Rapport des AffectationOutillages"
        def listeObjetsExporter = getObjetsToExport()
        def reportDef = new JasperReportDef(name:'rapport_des_AffectationOutillages.jrxml',
            fileFormat:JasperExportFormat.PDF_FORMAT,
            reportData : listeObjetsExporter,
            parameters : [  'nomsociete':nomsociete, 'titrerapport':titrerapport ]
        )
        def bit = jasperService.generateReport(reportDef).toByteArray()
        def nom_fichier = "rapport_des_AffectationOutillages.pdf"
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
        String titrerapport = "Rapport des AffectationOutillages"
        def listeObjetsExporter = getObjetsToExport()
        def reportDef = new JasperReportDef(name:'rapport_des_AffectationOutillages.jrxml',
            fileFormat:JasperExportFormat.XLS_FORMAT,
            reportData : listeObjetsExporter,
            parameters : [  'nomsociete':nomsociete, 'titrerapport':titrerapport ]
        )
        def bit = jasperService.generateReport(reportDef).toByteArray()
        def nom_fichier = "rapport_des_AffectationOutillages.xls"
        Filedownload.save(bit, "application/file", nom_fichier);
        
    }
    
    def cancel() {
        super.cancel()
        actualiserListOutillages()
        actualiserListProjets()
    }
    def delete() {
        super.delete()
        actualiserListOutillages()
        actualiserListProjets()
    }
    def update() {
        super.update()
        actualiserListOutillages()
        actualiserListProjets()
    }
    def add() {
        super.add()
        actualiserListOutillages()
    }
    def actualiserListOutillages() {
        outillageCollectifs = affectationOutillageService.getOutillageNonAffecter()
        if(outillageCollectifs.size() > 0) {
            outillageCollectifSelected = outillageCollectifs.get(0)
        }
        else
        outillageCollectifSelected = null
        
        new AnnotateDataBinder(this.getFellow("cooutillageCollectifs")).loadAll()
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
        		
        objet.projet = projetSelected        
        objet.outillageCollectif = outillageCollectifSelected        
        objet.employe = employeSelected
    }
    /**
     * Fonction qui fait la liaison entre l'association l'�l�ment selectionn� et la liste dans le crud
     **/
    def afficherValeurAssociation() {
        AffectationOutillage.withTransaction {
            outillageCollectifs = [objet.outillageCollectif]
            Combobox cbo = this.getFellow("cooutillageCollectifs");
            cbo.setModel(new BindingListModelList(outillageCollectifs, false))
            outillageCollectifSelected = objet.outillageCollectif
            cbo.setSelectedIndex(0)
           
            def index = 0;
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

