
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
import com.choranet.gesticom.BonLivraison
import com.choranet.stock.Produit
import com.choranet.commun.ChoraClientInfo

/**
 * AffectationProduit Window Object
 **/
class AffectationProduitWindow extends SuperWindow {
    /**
     * Service pour la gestion de l'objet AffectationProduit
     **/
    def affectationProduitService
    
    /**
     * Service jasper pour la generation des rapports
     **/
    def jasperService
    /**
     * Logger de la class AffectationProduitWindow
     **/
    private Log logger = LogFactory.getLog(AffectationProduitWindow.class)
    
    /**
     * liste de bonLivraison
     **/	
    def bonLivraisons	
    /**
     * bonLivraison  selectionn�
     **/
    def bonLivraisonSelected
    
    def projetsFiltre
    def projetFiltreSelected    
    /**
     * liste de projet
     **/	
    def projets	
    /**
     * projet  selectionn�
     **/
    def projetSelected
                
    /**
     * liste de produit
     **/	
    def produits	
    /**
     * produit  selectionn�
     **/
    def produitSelected
            
    def projetService
    /**
     * Constructeur
     **/
    public AffectationProduitWindow (projetService) {
        super(AffectationProduit.class)
        this.projetService = projetService
        
        this.specialeInitialisation()
    }  
    
    def specialeInitialisation() {

        projetsFiltre = Projet.list()
        
        projets = projetService.getProjetsNonTerminer()
        if(projets.size() > 0)
        projetSelected = projets.get(0)
        else
        projetSelected = null
        
        bonLivraisons = BonLivraison.findAllByType("ACHAT")		        
        
        produits = Produit.list()		
        if(produits.size() > 0) {
            produitSelected = produits.get(0)
            objet.prixUnitaire = produitSelected.prixAchat
        }        
        else
        produitSelected = null
        
    }
    
    protected SuperService getService() {
        return this.affectationProduitService
    }
    
    /**
     * Generation du rapport pdf
     **/
    def genererRapportPdf() {
        String nomsociete = session.societe.raisonSociale
        String titrerapport = "Rapport des AffectationProduits"
        def listeObjetsExporter = getObjetsToExport()
        def reportDef = new JasperReportDef(name:'rapport_des_AffectationProduits.jrxml',
            fileFormat:JasperExportFormat.PDF_FORMAT,
            reportData : listeObjetsExporter,
            parameters : [  'nomsociete':nomsociete, 'titrerapport':titrerapport ]
        )
        def bit = jasperService.generateReport(reportDef).toByteArray()
        def nom_fichier = "rapport_des_AffectationProduits.pdf"
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
        String titrerapport = "Rapport des AffectationProduits"
        def listeObjetsExporter = getObjetsToExport()
        def reportDef = new JasperReportDef(name:'rapport_des_AffectationProduits.jrxml',
            fileFormat:JasperExportFormat.XLS_FORMAT,
            reportData : listeObjetsExporter,
            parameters : [  'nomsociete':nomsociete, 'titrerapport':titrerapport ]
        )
        def bit = jasperService.generateReport(reportDef).toByteArray()
        def nom_fichier = "rapport_des_AffectationProduits.xls"
        Filedownload.save(bit, "application/file", nom_fichier);
        
    }
    
    def produitChanger() {
        objet.prixUnitaire = produitSelected.prixAchat
        new AnnotateDataBinder(this.getFellow("fieldPrixUnitaire")).loadAll()
    }
    
    def blChoisi() {
        def prix = objet.prixUnitaire
        def chargerBl = true        
        if(chargerBl) {
            for(lp in bonLivraisonSelected.ligneProduits) {
                if(lp.produit.code.equals(produitSelected.code)) {
                    prix = lp.prixDeduit;
                }
            }
            objet.prixUnitaire = prix
            new AnnotateDataBinder(this.getFellow("fieldPrixUnitaire")).loadAll()
        }
    }
    
    /**
     * Fonction qui permet de r�-initaliser l'association au niveau de l'interface
     * @param del si c'est une r�initionalisation apr�s une suppression ou non
     **/
    def reinitialiserAssociation(del) {
        	                
        bonLivraisonSelected = null
                    
        projets = projetService.getProjetsNonTerminer()

        if(projets.size() > 0)
        projetSelected = projets.get(0)
        else
        projetSelected = null
                    	        
        if(produits.size() > 0)
        produitSelected = produits.get(0)
        else
        produitSelected = null
                    
    }
    
    def select() {                    
        objet = objetSelected	
        afficherValeurAssociation()
        //article.lock()  //Ne peut etre utilisï¿½ que pour le base de donnï¿½e qui accepte le veruillage des enregisterments
        super.rafraichirField()
        activerBoutons(true)
    }
    
    def rafraichirField() {        
        if(produitSelected != null) {
            objet.prixUnitaire = produitSelected.prixAchat            
        }
        this.getFellows().each { co ->
            if(co.getId() != null && co.getId().startsWith("field")) {
                def binder = new AnnotateDataBinder(co)
                binder.loadAll()
            }
        }               
    }
    
    /**
     * Fonction qui copie la valeur de l'association � l'�l�ment courant
     **/
    def actualiserValeurAssociation() {
        		
        objet.bonLivraison = bonLivraisonSelected
        
        bonLivraisonSelected = null
                    		
        objet.projet = projetSelected
        if(projets.size() > 0) {
            def binderprojet = new AnnotateDataBinder(this.getFellow("coprojets"))
            projetSelected = projets.get(0)
            binderprojet.loadAll()
        }
        else
        projetSelected = null
                    		
        objet.produit = produitSelected
        if(produits.size() > 0) {
            def binderproduit = new AnnotateDataBinder(this.getFellow("coproduits"))
            produitSelected = produits.get(0)            
            binderproduit.loadAll()
        }
        else
        produitSelected = null
                    
    }
    /**
     * Fonction qui fait la liaison entre l'association l'�l�ment selectionn� et la liste dans le crud
     **/
    def afficherValeurAssociation() {
        AffectationProduit.withTransaction {
            def index = 0;
            for(pj in projets) {
                if(pj.id == objet.projet.id) {
                    projetSelected = pj;
                    break;
                }
                index++
            }
            this.getFellow("coprojets").setSelectedIndex(index)
            
            index = 0;
            for(pd in produits) {
                if(pd.id == objet.produit.id) {
                    produitSelected = pd;
                    break;
                }
                index++
            }
            this.getFellow("coproduits").setSelectedIndex(index)
                    		
            if(objet.bonLivraison != null) {
                index = 0;
                for(bl in bonLivraisons) {
                    if(bl.id == objet.bonLivraison.id) {
                        bonLivraisonSelected = bl
                        break;
                    }
                    index++;
                }
                this.getFellow("cobonLivraisons").setSelectedIndex(index)
            } else {
                bonLivraisonSelected = null
                this.getFellow("cobonLivraisons").setSelectedItem(null)
            }
        }
    }
}

