
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
 * LocationOutil Window Object
 **/
class LocationOutilWindow extends SuperWindow {
    /**
     * Service pour la gestion de l'objet LocationOutil
     **/
    def locationOutilService
    
    /**
     * Service jasper pour la generation des rapports
     **/
    def jasperService
    /**
     * Logger de la class LocationOutilWindow
     **/
    private Log logger = LogFactory.getLog(LocationOutilWindow.class)
    
    /**
     * liste de loueur
     **/	
    def loueurs	
    /**
     * loueur  selectionn�
     **/
    def loueurSelected
                
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
     * liste de outilLouable
     **/	
    def outilLouables	
    /**
     * outilLouable  selectionn�
     **/
    def outilLouableSelected
                
    def projetService
    /**
     * Constructeur
     **/
    public LocationOutilWindow (projetService) {
        super(LocationOutil.class)
        this.projetService = projetService
        specialeInitialisation()
    }  

    def specialeInitialisation() {
        
        outilLouables = OutilLouable.list()
        
        loueurs = Loueur.list()	
        
        if(outilLouables.size() > 0) {
            outilLouableSelected = outilLouables.get(0)
            if(loueurs.size() > 0) {
                loueurSelected = loueurs.find{ it.id == outilLouableSelected.loueur.id }
            }
            objet.prixHeureLocation = outilLouableSelected.prixHeureLocation
        }        
        else {
            outilLouableSelected = null
            if(loueurs.size() > 0)
            loueurSelected = loueurs.get(0)
            else
            loueurSelected = null
        }        
                            
        projetsFilter = Projet.list()		
        projets = projetService.getProjetsNonTerminer()	
        if(projets.size() > 0)
        projetSelected = projets.get(0)
        else
        projetSelected = null
                            
    }
    
    def outilLouableSelectionner() {
        if(loueurs.size() > 0) {
            loueurSelected = loueurs.find{ it.id == outilLouableSelected.loueur.id }
        }
        objet.prixHeureLocation = outilLouableSelected.prixHeureLocation
        new AnnotateDataBinder(this.getFellow("coloueurs")).loadAll()
        new AnnotateDataBinder(this.getFellow("fieldPrixHeureLocation")).loadAll()        
    }
    
    def add() {
        super.add()
        outilLouableSelectionner()
    }
    
    def delete() {
        super.delete()
        outilLouableSelectionner()
        actualiserListProjets()
    }
    
    def update() {
        super.update()
        outilLouableSelectionner()
        actualiserListProjets()
    }
    
    def cancel() {
        super.cancel()
        outilLouableSelectionner()
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
    protected SuperService getService() {
        return this.locationOutilService
    }
    
    /**
     * Generation du rapport pdf
     **/
    def genererRapportPdf() {
        String nomsociete = session.societe.raisonSociale
        String titrerapport = "Rapport des LocationOutils"
        def listeObjetsExporter = getObjetsToExport()
        def reportDef = new JasperReportDef(name:'rapport_des_LocationOutils.jrxml',
            fileFormat:JasperExportFormat.PDF_FORMAT,
            reportData : listeObjetsExporter,
            parameters : [  'nomsociete':nomsociete, 'titrerapport':titrerapport ]
        )
        def bit = jasperService.generateReport(reportDef).toByteArray()
        def nom_fichier = "rapport_des_LocationOutils.pdf"
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
        String titrerapport = "Rapport des LocationOutils"
        def listeObjetsExporter = getObjetsToExport()
        def reportDef = new JasperReportDef(name:'rapport_des_LocationOutils.jrxml',
            fileFormat:JasperExportFormat.XLS_FORMAT,
            reportData : listeObjetsExporter,
            parameters : [  'nomsociete':nomsociete, 'titrerapport':titrerapport ]
        )
        def bit = jasperService.generateReport(reportDef).toByteArray()
        def nom_fichier = "rapport_des_LocationOutils.xls"
        Filedownload.save(bit, "application/file", nom_fichier);
        
    }
            
    /**
     * Fonction qui copie la valeur de l'association � l'�l�ment courant
     **/
    def actualiserValeurAssociation() {
        objet.loueur = loueurSelected
        objet.projet = projetSelected
        objet.outilLouable = outilLouableSelected
    }
    /**
     * Fonction qui fait la liaison entre l'association l'�l�ment selectionn� et la liste dans le crud
     **/
    def afficherValeurAssociation() {
        		
        def binderloueur = new AnnotateDataBinder(this.getFellow("coloueurs"))
        loueurSelected = loueurs.find{ it.id == LocationOutil.findById(objet.id).loueur.id }
        binderloueur.loadAll()
             
        projets.add(objetSelected.projet)         
        def binderprojet = new AnnotateDataBinder(this.getFellow("coprojets"))
        projetSelected = projets.find{ it.id == LocationOutil.findById(objet.id).projet.id }
        binderprojet.loadAll()
                    		
        def binderoutilLouable = new AnnotateDataBinder(this.getFellow("cooutilLouables"))
        outilLouableSelected = outilLouables.find{ it.id == LocationOutil.findById(objet.id).outilLouable.id }
        binderoutilLouable.loadAll()
                    
    }
}

