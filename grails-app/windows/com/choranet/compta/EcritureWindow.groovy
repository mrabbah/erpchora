
package com.choranet.compta
    
import com.choranet.commun.SuperWindow
import com.choranet.commun.SuperService
import com.choranet.commun.ChoraClientInfo
import com.choranet.gesticom.Paiement
import com.choranet.gesticom.LigneProduit
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
 * Ecriture Window Object
 **/
class EcritureWindow extends SuperWindow {
    /**
     * Service pour la gestion de l'objet Ecriture
     **/
    def ecritureService
    
    /**
     * Service jasper pour la generation des rapports
     **/
    def jasperService
    /**
     * Logger de la class EcritureWindow
     **/
    private Log logger = LogFactory.getLog(EcritureWindow.class)
    
    /**
     * liste de devise
     **/	
    def devises	
    /**
     * devise  selectionne
     **/
    def deviseSelected
                
    /**
     * liste de paiement
     **/
    def paiements	
    /**
     * paiement  selectionne
     **/
    def paiementSelected	
                
    /**
     * liste de ligneProduit
     **/
    def ligneProduits	
    /**
     * ligneProduit  selectionne
     **/
    def ligneProduitSelected	
                
    /**
     * liste de compteComptable
     **/	
    def compteComptables	
    /**
     * compteComptable  selectionne
     **/
    def compteComptableSelected
                
    /**
     * liste de periode
     **/	
    def periodes	
    /**
     * periode  selectionne
     **/
    def periodeSelected
                
    /**
     * Constructeur
     **/
    public EcritureWindow () {
        super(Ecriture.class)
    }  

    protected SuperService getService() {
        return this.ecritureService
    }
    
    /**
     * Generation du rapport pdf
     **/
    def genererRapportPdf() {
        String nomsociete = session.societe.raisonSociale
        String titrerapport = "Rapport des Ecritures"
        def listeObjetsExporter = getObjetsToExport()
        def reportDef = new JasperReportDef(name:'rapport_des_Ecritures.jrxml',
            fileFormat:JasperExportFormat.PDF_FORMAT,
            reportData : listeObjetsExporter,
            parameters : [  'nomsociete':nomsociete, 'titrerapport':titrerapport ]
        )
        def bit = jasperService.generateReport(reportDef).toByteArray()
        def nom_fichier = "rapport_des_Ecritures.pdf"
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
        String titrerapport = "Rapport des Ecritures"
        def listeObjetsExporter = getObjetsToExport()
        def reportDef = new JasperReportDef(name:'rapport_des_Ecritures.jrxml',
            fileFormat:JasperExportFormat.XLS_FORMAT,
            reportData : listeObjetsExporter,
            parameters : [  'nomsociete':nomsociete, 'titrerapport':titrerapport ]
        )
        def bit = jasperService.generateReport(reportDef).toByteArray()
        def nom_fichier = "rapport_des_Ecritures.xls"
        Filedownload.save(bit, "application/file", nom_fichier);
        
    }
    
    
    /**
     * Fonction qui gere l'initialisation des listes d'associations au niveau du constructeur
     **/
    def initialiserAssociation() {
        
        devises = Devise.list()		
        if(devises.size() > 0)
        deviseSelected = devises.get(0)
        else
        deviseSelected = null
                    
        paiements = Paiement.list()		
        if(paiements.size() > 0)
        paiementSelected = paiements.get(0)
        else
        paiementSelected = null
                    
        ligneProduits = LigneProduit.list()		
        if(ligneProduits.size() > 0)
        ligneProduitSelected = ligneProduits.get(0)
        else
        ligneProduitSelected = null
                    
        compteComptables = CompteComptable.list()		
        if(compteComptables.size() > 0)
        compteComptableSelected = compteComptables.get(0)
        else
        compteComptableSelected = null
                    
        periodes = Periode.list()		
        if(periodes.size() > 0)
        periodeSelected = periodes.get(0)
        else
        periodeSelected = null
                    
    }
    /**
     * Fonction qui permet de re-initaliser l'association au niveau de l'interface
     * @param del si c'est une reinitionalisation apres une suppression ou non
     **/
    def reinitialiserAssociation(del) {
        	
        if(del) {
            devises = Devise.list()
        }	
        if(devises.size() > 0)
        deviseSelected = devises.get(0)
        else
        deviseSelected = null
                    
        if(del) {
            paiements = Paiement.list()
        }		
        if(paiements.size() > 0)
        paiementSelected = paiements.get(0)
        else
        paiementSelected = null
                    
        if(del) {
            ligneProduits = LigneProduit.list()
        }		
        if(ligneProduits.size() > 0)
        ligneProduitSelected = ligneProduits.get(0)
        else
        ligneProduitSelected = null
                    	
        if(del) {
            compteComptables = CompteComptable.list()
        }	
        if(compteComptables.size() > 0)
        compteComptableSelected = compteComptables.get(0)
        else
        compteComptableSelected = null
                    	
        if(del) {
            periodes = Periode.list()
        }	
        if(periodes.size() > 0)
        periodeSelected = periodes.get(0)
        else
        periodeSelected = null
                    
    }
    /**
     * Fonction qui copie la valeur de l'association e l'element courant
     **/
    def actualiserValeurAssociation() {
        		
        objet.devise = deviseSelected
        if(devises.size() > 0) {
            def binderdevise = new AnnotateDataBinder(this.getFellow("codevises"))
            deviseSelected = devises.get(0)
            binderdevise.loadAll()
        }
        else
        deviseSelected = null
                    		
        objet.paiement = paiementSelected
        if(paiements.size() > 0) {
            def binderpaiement = new AnnotateDataBinder(this.getFellow("copaiements"))
            paiementSelected = paiements.get(0)
            binderpaiement.loadAll()
        }
        else
        paiementSelected = null
                    		
        objet.ligneProduit = ligneProduitSelected
        if(ligneProduits.size() > 0) {
            def binderligneProduit = new AnnotateDataBinder(this.getFellow("coligneProduits"))
            ligneProduitSelected = ligneProduits.get(0)
            binderligneProduit.loadAll()
        }
        else
        ligneProduitSelected = null
                    		
        objet.compteComptable = compteComptableSelected
        if(compteComptables.size() > 0) {
            def bindercompteComptable = new AnnotateDataBinder(this.getFellow("cocompteComptables"))
            compteComptableSelected = compteComptables.get(0)
            bindercompteComptable.loadAll()
        }
        else
        compteComptableSelected = null
                    		
        objet.periode = periodeSelected
        if(periodes.size() > 0) {
            def binderperiode = new AnnotateDataBinder(this.getFellow("coperiodes"))
            periodeSelected = periodes.get(0)
            binderperiode.loadAll()
        }
        else
        periodeSelected = null
                    
    }
    /**
     * Fonction qui fait la liaison entre l'association l'element selectionne et la liste dans le crud
     **/
    def afficherValeurAssociation() {
        		
        def binderdevise = new AnnotateDataBinder(this.getFellow("codevises"))
        deviseSelected = devises.find{ it.id == Ecriture.findById(objet.id).devise.id }
        binderdevise.loadAll()
                    						
        def binderpaiement = new AnnotateDataBinder(this.getFellow("copaiements"))
        paiementSelected = paiements.find{ it.id == Ecriture.findById(objet.id).paiement.id }
        binderpaiement.loadAll()
                    						
        def binderligneProduit = new AnnotateDataBinder(this.getFellow("coligneProduits"))
        ligneProduitSelected = ligneProduits.find{ it.id == Ecriture.findById(objet.id).ligneProduit.id }
        binderligneProduit.loadAll()
                    		
        def bindercompteComptable = new AnnotateDataBinder(this.getFellow("cocompteComptables"))
        compteComptableSelected = compteComptables.find{ it.id == Ecriture.findById(objet.id).compteComptable.id }
        bindercompteComptable.loadAll()
                    		
        def binderperiode = new AnnotateDataBinder(this.getFellow("coperiodes"))
        periodeSelected = periodes.find{ it.id == Ecriture.findById(objet.id).periode.id }
        binderperiode.loadAll()
                    
    }
}

