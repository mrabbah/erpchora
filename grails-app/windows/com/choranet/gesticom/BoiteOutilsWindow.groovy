/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.choranet.gesticom
import org.zkoss.zul.*
import org.zkoss.zkplus.databind.*
import org.apache.commons.logging.Log 
import org.apache.commons.logging.LogFactory
import org.codehaus.groovy.grails.plugins.jasper.JasperExportFormat
import org.codehaus.groovy.grails.plugins.jasper.JasperReportDef
import org.springframework.web.context.request.RequestContextHolder

import org.zkoss.util.media.AMedia;
import org.zkoss.util.media.Media;
import java.util.HashMap;
import java.util.Map;
import org.zkoss.zk.ui.Executions
import org.hibernate.ScrollableResults
import com.choranet.commun.SuperWindow
import com.choranet.commun.SuperService
import com.choranet.commun.ChoraClientInfo
import com.choranet.gesticom.util.*
import com.choranet.stock.Securitestock

/**
 *
 * @author kamal
 */
class BoiteOutilsWindow extends Window {
    /**
     * Service pour la gestion de l'objet
     **/
    def partenaireService
    def livraisonService
    def paiementService
    def bonCommandeService
    def jasperService
    def securitestockService
    def mouvementStockService
     
    def produits 
    def produitPerissables
    def produit
    def produitevolution
    def clients
    def client
    def listeannees
    def annee 
    def blacklists
    def listeMeilleursFournisseurs
    def bonCommandes = new ArrayList<BonCommande>()
    def partenaires
    def partenaireSelected
    Date dateDebutPaiement 
    Date dateFinPaiement
    def session
    
    def produitPerissableSelected 
    def prixLiquidationProduitSelected
    def listExpirationProduit = new ArrayList<Securitestock>()
    
    def boiteOutilsService
    /**
     *
     * Logger de la class ProduitWindow
     **/
    private Log logger = LogFactory.getLog(ProduitsVendusAchetesWindow.class)
    
    /**
     * Constructeur
     **/
    public BoiteOutilsWindow(produitService,partenaireService,livraisonService,securitestockService,mouvementStockService) {
        this.partenaireService = partenaireService
        this.livraisonService = livraisonService
        this.securitestockService = securitestockService
        this.mouvementStockService = mouvementStockService
        session = RequestContextHolder.currentRequestAttributes().getSession()
        produits = produitService.list()
        produit = null
        produitevolution = null
        clients = partenaireService.getClientsNonBlacklist()
        blacklists = partenaireService.getBlackLists()
        listeannees = livraisonService.getlisteAnnees()
        bonCommandes = BonCommande.list()
        partenaires = Partenaire.list()
        
        produitPerissableSelected = null
        produitPerissables = produitService.getProduitPerissables()
        
//        filtre.partenaire = null
//        filtre.numBC = null
//        filtre.date = null
//        filtre.livree = null
//        filtre.paye = null
//        def map
//        if(attributsAFiltrer == null) {
//            map = getService().filtrer(clazz, filtre, sortedHeader, sortedDirection, ofs, 12)
//        } else {
//            map = getService().filtrer(clazz, filtre, attributsAFiltrer, sortedHeader, sortedDirection, ofs, 12)
//        }
//        tailleListe = map["tailleListe"]
//        listeObjets = map["listeObjets"]
        
    }
//    protected SuperService getService() {
//        return this.boiteOutilsService
//    }
    
    def getlisteMeilleursFournisseurs() {
        listeMeilleursFournisseurs = livraisonService.getlistePrixFournisseur("ACHAT",produit.code)
        new AnnotateDataBinder(this.getFellow("colisteMeilleursFournisseurs")).loadAll() 
    }
    
    def getExpirationsProduitsParDuree(duree_limite_expiration){
        listExpirationProduit = securitestockService.getEtatsStockPourProduitPerissable(produitPerissableSelected, duree_limite_expiration)
        prixLiquidationProduitSelected  = mouvementStockService.getPrixLiquidationUnitaire(produitPerissableSelected, duree_limite_expiration)
        new AnnotateDataBinder(this.getFellow("lstligneProduitsPerissable")).loadAll() 
    }
    
    def addToBlacklist() 
    {
        if (client != null){ 
            client.estBlacklist = true
            partenaireService.update(client)
            clients = partenaireService.getClientsNonBlacklist()
            blacklists = partenaireService.getBlackLists()
            new AnnotateDataBinder(this.getFellow("coClients")).loadAll()
            new AnnotateDataBinder(this.getFellow("coblacklists")).loadAll()
        }
    }
    
    def genererRapportPaiements(){
        def objet
  
        def paiements = paiementService.getPaiementsByPartenaireEtType(partenaireSelected,dateDebutPaiement,dateFinPaiement)
        def paiementsDebit = paiementService.getPaiementsByPartenaireEtType(partenaireSelected,dateDebutPaiement,dateFinPaiement,'DEBIT')
        def paiementsCredit = paiementService.getPaiementsByPartenaireEtType(partenaireSelected,dateDebutPaiement,dateFinPaiement,'CREDIT')
        def BCAchat
        def BCVente     
        Double TotalPC = 0 
        Double TotalPD = 0
        Double TotalBCV = 0
        Double TotalBCA = 0
        if  (paiements && paiements.size() >0 ){
            BCVente = bonCommandeService.getBonCommandeByPaiement(paiements,'VENTE')
            BCAchat = bonCommandeService.getBonCommandeByPaiement(paiements,'ACHAT') 
            
            TotalPC = paiementService.getSumPaiementsByPartenaireEtType(partenaireSelected,dateDebutPaiement,dateFinPaiement,'CREDIT')
            TotalPD = paiementService.getSumPaiementsByPartenaireEtType(partenaireSelected,dateDebutPaiement,dateFinPaiement,'DEBIT')
            
            if (BCAchat && BCAchat.size()>0){
                for (bc in BCAchat){
                    TotalBCA += bc.trans_totalttc  
                }
                
            }
            
            if (BCVente && BCVente.size()>0){
                for (bc in BCVente){
                    TotalBCV += bc.trans_totalttc  
                }
                
            }
        }
        println BCVente
        println BCAchat
        

        def paiementSubReport = Utilitaire.getPaiementSubReport(getClass())        
        def BCSubReport = Utilitaire.getBCSubReport(getClass())

        
        String nomsociete = session.societe.raisonSociale
        String titrerapport = "Rapport des paiements"
        def reportDef = new JasperReportDef(name:'rapport_des_Paiements_par_partenaire_date.jasper',
            fileFormat:JasperExportFormat.PDF_FORMAT,
            reportData : [objet],
            parameters : ['nomsociete':nomsociete, 'titrerapport':titrerapport,'PaiementCreditSubReport' : paiementSubReport, 'PaiementDebitSubReport': paiementSubReport,'PaiementCredit' : paiementsCredit,'PaiementDebit' : paiementsDebit,'BCAchatSubReport':BCSubReport, 'BCVenteSubReport': BCSubReport, 'BCAchat' : BCAchat,'BCVente' : BCVente, 'TotalPD': TotalPD, 'TotalPC':TotalPC,'TotalBCA' : TotalBCA, 'TotalBCV' : TotalBCV]
        )
        def bit = jasperService.generateReport(reportDef).toByteArray()
        def nom_fichier = "rapport_des_Paiements_" + partenaireSelected + ".pdf"
        //Filedownload.save(bit, "application/pdf", nom_fichier);
        Media amedia = new AMedia(nom_fichier, "pdf", "application/pdf", bit);
        Map map = new HashMap();
        map.put("content", amedia);
        Executions.createComponents("/zul/util/pdfviewer.zul", null, map).doModal();
    }
}

