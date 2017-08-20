
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
import com.choranet.gesticom.BonCommande
import com.choranet.commun.ChoraClientInfo
import com.choranet.rh.Employe

/**
 * Projet Window Object
 **/
class ProjetWindow extends SuperWindow {
    /**
     * Service pour la gestion de l'objet Projet
     **/
    def projetService
    
    /**
     * Service jasper pour la generation des rapports
     **/
    def jasperService
    /**
     * Logger de la class ProjetWindow
     **/
    private Log logger = LogFactory.getLog(ProjetWindow.class)
    
    /**
     * liste de calendrier
     **/	
    def calendriers	
    /**
     * calendrier  selectionn�
     **/
    def calendrierSelected
                
    /**
     * liste de chefChantier
     **/	
    def chefChantiers	
    /**
     * chefChantier  selectionn�
     **/
    def chefChantierSelected
                
    /**
     * liste de bonCommande
     **/
    def bonCommandes	
    /**
     * bonCommande  selectionn�
     **/
    def bonCommandeSelected	
                
    /**
     * Constructeur
     **/
    public ProjetWindow () {
        super(Projet.class)
    }  
    
    protected SuperService getService() {
        return this.projetService
    }
    
    /**
     * Generation du rapport pdf
     **/
    def genererRapportPdf() {
        String nomsociete = session.societe.raisonSociale
        String titrerapport = "Rapport des Projets"
        def listeObjetsExporter = getObjetsToExport()
        def reportDef = new JasperReportDef(name:'rapport_des_Projets.jrxml',
            fileFormat:JasperExportFormat.PDF_FORMAT,
            reportData : listeObjetsExporter,
            parameters : [  'nomsociete':nomsociete, 'titrerapport':titrerapport ]
        )
        def bit = jasperService.generateReport(reportDef).toByteArray()
        def nom_fichier = "rapport_des_Projets.pdf"
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
        String titrerapport = "Rapport des Projets"
        def listeObjetsExporter = getObjetsToExport()
        def reportDef = new JasperReportDef(name:'rapport_des_Projets.jrxml',
            fileFormat:JasperExportFormat.XLS_FORMAT,
            reportData : listeObjetsExporter,
            parameters : [  'nomsociete':nomsociete, 'titrerapport':titrerapport ]
        )
        def bit = jasperService.generateReport(reportDef).toByteArray()
        def nom_fichier = "rapport_des_Projets.xls"
        Filedownload.save(bit, "application/file", nom_fichier);
        
    }
    def rafraichirField() {
        objet.chargeReelle = 0.0
        this.getFellows().each { co ->
            if(co.getId() != null && co.getId().startsWith("field")) {
                def binder = new AnnotateDataBinder(co)
                binder.loadAll()
            }
        }               
    }
    
    /**
     * Fonction qui g�re l'initialisation des listes d'associations au niveau du constructeur
     **/
    def initialiserAssociation() {
        objet.chargeReelle = 0.0
        objet.avancementReel = 0.0
        calendriers = Calendrier.list()		
        if(calendriers.size() > 0)
        calendrierSelected = calendriers.get(0)
        else
        calendrierSelected = null
                    
        chefChantiers = Employe.list()		
        if(chefChantiers.size() > 0)
        chefChantierSelected = chefChantiers.get(0)
        else
        chefChantierSelected = null
                    
        bonCommandes = BonCommande.findAllByType('VENTE')	        
        bonCommandeSelected = null
                    
    }
    /**
     * Fonction qui permet de r�-initaliser l'association au niveau de l'interface
     * @param del si c'est une r�initionalisation apr�s une suppression ou non
     **/
    def reinitialiserAssociation(del) {
        /*	
        if(calendriers.size() > 0)
        calendrierSelected = calendriers.get(0)
        else
        calendrierSelected = null
                    	
        if(chefChantiers.size() > 0)
        chefChantierSelected = chefChantiers.get(0)
        else
        chefChantierSelected = null
         */            
        bonCommandeSelected = null
                    
    }
    /**
     * Fonction qui copie la valeur de l'association � l'�l�ment courant
     **/
    def actualiserValeurAssociation() {
        logger.debug("calendrier selectionné : " + calendrierSelected)	
        if(calendrierSelected != null) {
            objet.calendrier = Calendrier.findByNom(calendrierSelected.nom)            
        }
        /*if(calendriers.size() > 0) {
        def bindercalendrier = new AnnotateDataBinder(this.getFellow("cocalendriers"))
        calendrierSelected = calendriers.get(0)
        bindercalendrier.loadAll()
        }
        else
        calendrierSelected = null*/
        logger.debug("chef de chantier selecitonné : " + chefChantierSelected)            		
        if(chefChantierSelected != null) {
            objet.chefChantier = Employe.findByNumero(chefChantierSelected.numero)            
        }
        /*if(chefChantiers.size() > 0) {
            def binderchefChantier = new AnnotateDataBinder(this.getFellow("cochefChantiers"))
            chefChantierSelected = chefChantiers.get(0)
            binderchefChantier.loadAll()
        }
        else
        chefChantierSelected = null*/
        if(bonCommandeSelected != null) {
            objet.bonCommande = BonCommande.findByNumBC(bonCommandeSelected.numBC)            
        } else {
            objet.bonCommande = null
        }            		
        bonCommandeSelected = null
                    
    }
    /**
     * Fonction qui fait la liaison entre l'association l'�l�ment selectionn� et la liste dans le crud
     **/
    def afficherValeurAssociation() {
        //Projet.withTransaction {
            logger.debug("chargement du calendrier")		
            def index = 0;
            for(cal in calendriers) {
                if(cal.id == objet.calendrier.id) {
                    calendrierSelected = cal
                    break;
                }
                index++
            }
            logger.debug("Calendrier : " + calendrierSelected)
            this.getFellow("cocalendriers").setSelectedIndex(index)
            index = 0;
            logger.debug("chargemnet du chef de chantier")       		
            for(ch in chefChantiers) {
                if(ch.id == objet.chefChantier.id) {
                    chefChantierSelected = ch
                    break;
                }
                index++
            }
            logger.debug("Chef de chantier : " + chefChantierSelected)
            this.getFellow("cochefChantiers").setSelectedIndex(index)
            logger.debug("chargement du bon de commande")  
            if(objet.bonCommande != null) {
                index = 0;
                for(bc in bonCommandes) {
                    if(bc.id == objet.bonCommande.id) {
                        bonCommandeSelected = bc
                        break;
                    }
                    index++
                }
                logger.debug("BC : " + bonCommandeSelected)
                this.getFellow("cobonCommandes").setSelectedIndex(index)
            } else {
                bonCommandeSelected = null
                this.getFellow("cobonCommandes").setSelectedItem(null)
            }    
        //}
    }
    
    def bonCommandeSelectionner() {
        if(bonCommandeSelected != null) {
            objet.montantBonCommande = BonCommande.findByNumBC(bonCommandeSelected.numBC).trans_totalht
            new AnnotateDataBinder(this.getFellow("fieldMontantBonCommande")).loadAll()
        }else {
            objet.montantBonCommande = 0.0
        }        
    }
}

