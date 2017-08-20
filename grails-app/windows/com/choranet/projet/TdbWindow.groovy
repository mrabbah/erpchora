
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
import com.choranet.gesticom.LigneProduitService

/**
 *
 * @author kamal
 */
class TdbWindow extends Window {
    def projetService
    
    def ligneProduitService
    def listeProjetsEncours
    def chargeMontantProjet
    def etatCharges
    
    def projets
    def projetSelected
    
    def existProjet = false
    def existProjetEncours = false
    def existCharges = false 
    
    def chargesAffectationOutils 
    def chargesAffectationProduits 
    def produitsBonCommande
    def chargesChargeDiverss 
    def chargesEmployess 
    def chargesLocationOutils 
    def chargesSoustraitances 
    
    /**
     * Logger de la class ClientWindow
     **/
    private Log logger = LogFactory.getLog(TdbWindow.class)
	
    /**
     * Constructeur
     **/
    public TdbWindow (projetService){
        
        this.projetService = projetService
        listeProjetsEncours = projetService.getProjetsNonTerminer()
       
        if (listeProjetsEncours != null && listeProjetsEncours.size() > 0){
            existProjetEncours = true
        }
        
        def listCinqDerniersProjets = projetService.getProjetsNonTerminerCinqDerniers()
        
        chargeMontantProjet = new SimpleCategoryModel() 
        if (listCinqDerniersProjets != null && listCinqDerniersProjets.size() > 0){
            existProjet = true
            listCinqDerniersProjets.each {
                chargeMontantProjet.setValue("Charge réelle",it.nom,it.chargeReelle);
                chargeMontantProjet.setValue("Montant BC",it.nom,it.montantBonCommande);
            }
        }
        
        projets = Projet.list()
    }
    
    def getDetailsProjet(){
        logger.debug("chargement des affectation d outil")
        def sommeChargesAffectationOutil = projetService.getSommeChargesAffectationOutil(projetSelected)
        logger.debug("chargement des affectation de produits")
        def sommeChargesAffectationProduit = projetService.getSommeChargesAffectationProduit(projetSelected)
        logger.debug("chargement des charges divers")
        def sommeChargesChargeDivers = projetService.getSommeChargesChargeDivers(projetSelected)
        logger.debug("chargment charges employés")
        def sommeChargesEmployes = projetService.getSommeChargesEmployes(projetSelected)
        logger.debug("chargement des location d outil")
        def sommeChargesLocationOutil = projetService.getSommeChargesLocationOutil(projetSelected)
        logger.debug("chargement de la soutraitance")
        def sommeChargesSoustraitance =  projetService.getSommeChargesSoustraitance(projetSelected)
        logger.debug("affichage des détails des charges")
        if (sommeChargesAffectationOutil+sommeChargesAffectationProduit+sommeChargesChargeDivers+sommeChargesEmployes+sommeChargesLocationOutil+sommeChargesSoustraitance > 0 ){
            logger.debug("chargement du graphe")
            existCharges = true 
            etatCharges = new SimplePieModel()
            etatCharges.setValue("Affectation outils",sommeChargesAffectationOutil) 
            etatCharges.setValue("Affectation produits",sommeChargesAffectationProduit) 
            etatCharges.setValue("Employés",sommeChargesEmployes) 
            etatCharges.setValue("Location outils",sommeChargesLocationOutil) 
            etatCharges.setValue("Sous-traitance",sommeChargesSoustraitance) 
            etatCharges.setValue("Divers",sommeChargesChargeDivers) 
        
            new AnnotateDataBinder(this.getFellow("etatCharges3d")).loadAll()
            logger.debug("list charges affectation outil")
            chargesAffectationOutils = projetService.getChargesAffectationOutil(projetSelected)
            logger.debug("list charges affectation produit")
            chargesAffectationProduits = projetService.getChargesAffectationProduit(projetSelected)
            logger.debug("liste des charges divers")
            chargesChargeDiverss = projetService.getChargesChargeDivers(projetSelected)
            logger.debug("liste des charges employes")
            chargesEmployess = projetService.getChargesEmployes(projetSelected)
            logger.debug("liste des location d outil")
            chargesLocationOutils = projetService.getChargesLocationOutil(projetSelected)
            logger.debug("liste de la soutraitance")
            chargesSoustraitances =  projetService.getChargesSoustraitance(projetSelected)  
            //produitsBonCommande = ligneProduitService.getProduitsQuantitesBonCommande(projetSelected.bonCommande)
            if(projetSelected.bonCommande != null) {
                produitsBonCommande = projetSelected.bonCommande.ligneProduits
            } else {
                produitsBonCommande = []
            }
            logger.debug("association des données et de l'interface")
            new AnnotateDataBinder(this.getFellow("coprojets")).loadAll()
            new AnnotateDataBinder(this.getFellow("coprojetscomp")).loadAll()
            new AnnotateDataBinder(this.getFellow("lstchargesAffectationOutils")).loadAll()
            new AnnotateDataBinder(this.getFellow("lstchargesAffectationProduits")).loadAll()
            new AnnotateDataBinder(this.getFellow("lstchargesChargeDiverss")).loadAll()
            new AnnotateDataBinder(this.getFellow("lstchargesEmployess")).loadAll()
            new AnnotateDataBinder(this.getFellow("lstchargesLocationOutils")).loadAll()
            new AnnotateDataBinder(this.getFellow("lstchargesSoustraitances")).loadAll()
            new AnnotateDataBinder(this.getFellow("lstchargesAffectationProduitsComp")).loadAll()
            new AnnotateDataBinder(this.getFellow("lstchargesPdtBonCommande")).loadAll()
            
        }

    }
    
}


