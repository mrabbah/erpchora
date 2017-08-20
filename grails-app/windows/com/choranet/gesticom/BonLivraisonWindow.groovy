
package com.choranet.gesticom

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
import com.choranet.gesticom.util.*

import java.util.Comparator;
import com.choranet.commun.SuperWindow
import com.choranet.commun.SuperService
import com.choranet.commun.ChoraClientInfo
import com.choranet.stock.Livraison
import com.choranet.stock.Entrepot
import com.choranet.stock.ModeLivraison
import com.choranet.stock.Produit

/**
 * BonLivraison Window Object
 **/
class BonLivraisonWindow extends SuperWindow/* implements Comparator */{

    private Log logger = LogFactory.getLog(BonLivraisonWindow.class)
    
    def paternCompteurService
    def messageDocumentService
    def bonLivraisonService
    def bonCommandeService
    def jasperService
    def livraisonService
    def parametrageService
    def partenaireService
    
    def ligneProduitService
 
    def emplacementLivraisons	
    def emplacementLivraisonSelected
    def bonCommandes	
    def bonCommandesFilter	
    def ligneProduitsSelectedBc
    def ligneProduitsSelectedBl
    def modeLivraisons
    def nouvelleLivraison
    def livraisonExpresse = false
    def type
    def estBonRetour
    def bonCommandeSelected
    
    def sortedLigneProduitsBc
    def sortedLigneProduitsBl
    def sortedLigneProduitsBlDeBase
       
    def produitPerissableManaged = false
    def multiEntrepot = false
    
    def excludedLineProduitList
    
    /**
     * liste de partenaire
     **/	
    def partenaires
    
    def partenaireSelected
    
    def listeBonCommande
    
    /**
     * Constructeur
     **/
    public BonLivraisonWindow (paternCompteurService, bonCommandeService, bonLivraisonService, parametrageService, partenaireService, ligneProduitService) {        
        super(BonLivraison.class, 13, true, false)
        this.type = Executions.getCurrent().getParameter("type")
        this.estBonRetour = Executions.getCurrent().getParameter("estBonRetour").equals("true")
        this.paternCompteurService = paternCompteurService
        this.bonCommandeService = bonCommandeService
        this.bonLivraisonService = bonLivraisonService
        this.parametrageService = parametrageService
        this.partenaireService = partenaireService
        this.ligneProduitService = ligneProduitService
        specialInitialiserAssociation()
    }  
    
    //    int compare(lineA, lineB) {
    //        if (lineA.sequenceLigne < lineB.sequenceLigne) return -1
    //        if (lineA.sequenceLigne == lineB.sequenceLigne) return 0
    //        if (lineA.sequenceLigne > lineB.sequenceLigne) return 1
    //    }
    //    
    //    def trierParSequenceLigne(objet) {
    //        return objet.ligneProduits.sort(this)
    //    }
    
    def specialInitialiserAssociation() {
        multiEntrepot = parametrageService.isMultiEntrepotEnabled()
        produitPerissableManaged = parametrageService.isProduitPerissableManaged()
        
        if(type.equals("VENTE")) {
            partenaires = partenaireService.getClients()    
        } else {
            partenaires = partenaireService.getFournisseurs()
        }
        partenaireSelected = null
        
        objet.estBonRetour = estBonRetour
        objet.type = this.type
        if(estBonRetour) {
            objet.numBL = paternCompteurService.getProchainNumBr()
        } else {
            objet.numBL = paternCompteurService.getProchainNumBl()            
        }
        objet.date = new Date()
        if(estBonRetour) {
            bonCommandes = bonCommandeService.getBonCommandeNonRetournee(type)            
            bonCommandesFilter = bonCommandeService.getBonsPret(type)            
        } else {
            bonCommandes = bonCommandeService.getBonCommandeNonLivree(type)            
            bonCommandesFilter = bonCommandeService.getBonsCommande(type)            
        }
        objet.ligneProduits = new ArrayList<LigneProduit>();
        emplacementLivraisons = Entrepot.list()		
        if(emplacementLivraisons.size() > 0) {
            emplacementLivraisonSelected = emplacementLivraisons.get(0)            
        }        
        modeLivraisons = ModeLivraison.list()
        
        sortedLigneProduitsBc = []
        sortedLigneProduitsBl = []
        sortedLigneProduitsBlDeBase = []
        ligneProduitsSelectedBc = []
        ligneProduitsSelectedBl = []
        
        filtre.type = type
        filtre.estBonRetour = estBonRetour
        def map
        if(attributsAFiltrer == null) {
            map = bonLivraisonService.filtrer(clazz, filtre, sortedHeader, sortedDirection, ofs, maxNb)
        } else {
            map = bonLivraisonService.filtrer(clazz, filtre, attributsAFiltrer, sortedHeader, sortedDirection, ofs, maxNb)
        }
        tailleListe = map["tailleListe"]
        listeObjets = map["listeObjets"]
    }

    protected SuperService getService() {
        return this.bonLivraisonService
    }
    
    def activerBoutons(visible) {
        this.getFellow("btnUpdate").visible = visible
        this.getFellow("btnDocument").visible = visible
        this.getFellow("btnLivraison").visible = visible
        if(produitPerissableManaged && type.equals('VENTE')){
            this.getFellow("btnBLSimplifier").visible = false
            this.getFellow("btnBLEnDetails").visible = visible
        }
        this.getFellow("btnDelete").visible = visible
        this.getFellow("btnPdf").visible = !visible
        this.getFellow("btnExcel").visible = !visible
        this.getFellow("btnSave").visible = false
        this.getFellow("btnNew").visible = !visible
        //this.getFellow("westPanel").open = true        
    }
    
    def fermer() {
        if(bonCommandeSelected) {
            cancel()
        } else {
            activerBoutons(false)
            annulerSelection()
        }
        this.getFellow("westPanel").open = false
    }
    
    def cancel() { 
        objet = clazz.newInstance()
        objet.type = this.type
        if(estBonRetour) {
            objet.numBL = paternCompteurService.getProchainNumBr()
        } else {
            objet.numBL = paternCompteurService.getProchainNumBl()
        }
        objet.date = new Date()
        if(estBonRetour) {
            bonCommandes = bonCommandeService.getBonCommandeNonRetournee(type)    
        } else {
            bonCommandes = bonCommandeService.getBonCommandeNonLivree(type)    
        }
        partenaireSelected = null
        bonCommandeSelected = null
        objet.ligneProduits = new ArrayList<LigneProduit>();
        
        sortedLigneProduitsBc = []
        sortedLigneProduitsBl = []
        sortedLigneProduitsBlDeBase = []
        ligneProduitsSelectedBc = []
        ligneProduitsSelectedBl = []
        excludedLineProduitList = []
        this.getFellow("btnSave").visible = false
        new AnnotateDataBinder(this.getFellow("lstligneProduitsBl")).loadAll()
        new AnnotateDataBinder(this.getFellow("lstligneProduitsBc")).loadAll()
        def cbc = this.getFellow("cobonCommandes")
        cbc.disabled = false
        new AnnotateDataBinder(cbc).loadAll()
        
        def copartenaire = this.getFellow("copartenaire")
        copartenaire.disabled = false
        new AnnotateDataBinder(copartenaire).loadAll()
        
        this.getFellow("lstligneProduitsBl").disabled = false
        
        rafraichirField()
        activerBoutons(false)
        annulerSelection()
    }
    
    def actualiserEntrepotPourLigneProduitBL(elementlpbisCourant){
        elementlpbisCourant.entrepot = emplacementLivraisons.find{it.code == elementlpbisCourant.entrepot.code}
        //println 'elementlpbisCourant.entrepot : ' + elementlpbisCourant.entrepot
    }
    
    def isProduitPerissable(elementlpCourant){
        def currentProdEstPerissable = false
        if (elementlpCourant != null){        
            currentProdEstPerissable = Produit.findById(elementlpCourant.produit.id).perissable
            if (currentProdEstPerissable == null){
                currentProdEstPerissable = false
            }
        }
        //println 'currentProdEstPerissable : ' + currentProdEstPerissable
        return currentProdEstPerissable
    }
    
    def isDlcProduitPerissableToBeEnabled(elementlpCourant){
        def dlcToBeEnabled = false
        if (elementlpCourant.date_preremption == null) {
            dlcToBeEnabled = true
        }
        else {
            if (elementlpCourant in excludedLineProduitList)
            println 'elementlpCourant.sequenceLigne : ' + elementlpCourant.sequenceLigne
            excludedLineProduitList.each {
                if (elementlpCourant.sequenceLigne == it.sequenceLigne){
                    println 'it.sequenceLigne : ' + it.sequenceLigne
                    dlcToBeEnabled = true
                    return
                }
            }
        }
        println 'dlcToBeEnabled : ' + dlcToBeEnabled
        return dlcToBeEnabled
    }
    
    def updateLigneProduitsBlForProduitPerissable(elementlpCourant, date_preremption){
        objet.ligneProduits.each {
            if (it.produit.code == elementlpCourant.produit.code){
                it.date_preremption = date_preremption
                println 'it.date_preremption : ' + it.date_preremption
                return
            }
        }
    }
    
    def add() {
        try { 
            objet.estLivre = false
            objet.bonCommande = bonCommandeSelected
            objet = bonLivraisonService.save(objet)
            activerBoutons(true)
            if(produitPerissableManaged && type.equals('VENTE')){
                this.getFellow("btnBLSimplifier").visible = true
                this.getFellow("btnBLEnDetails").visible = false
            }
            if(this.estBonRetour) {
                if(objet.bonCommande.retournee) {
                    this.getFellow("btnUpdate").visible = false
                }
            } else {
                if(objet.bonCommande.livree) {
                    this.getFellow("btnUpdate").visible = false
                }
            }            
            Messagebox.show("Enregistrement effectué avec succès")
        } catch(Exception ex) {
            logger.error "Error: ${ex.message}", ex
            Messagebox.show("Echec lors de la transaction\n" + ex.message, "Erreur", Messagebox.OK, Messagebox.ERROR)
        } finally {
            filtrer()
            try {
                new AnnotateDataBinder(this.getFellow("lstligneProduitsBc")).loadAll()                
            } catch(Exception ex) {
                logger.error(ex)
            }
        }
    }
    
    def livraisonControl(visible){
        def windowLivraison = this.getFellow("winLivraison")
        this.getFellow("fieldDate").disabled = visible
        windowLivraison.getFellow("fieldNumExpedition").readonly = visible
        windowLivraison.getFellow("fieldDateExcpedition").readonly = visible
        windowLivraison.getFellow("fieldNbrColis").readonly = visible
        windowLivraison.getFellow("fieldPoidTotal").readonly = visible
        windowLivraison.getFellow("fieldFrais").readonly = visible
        windowLivraison.getFellow("comodeLivraisons").readonly = visible
        windowLivraison.getFellow("fieldDetail").readonly = visible
        new AnnotateDataBinder(windowLivraison).loadAll()
        
        this.getFellow("generationControl").visible = !visible
    }
    
    def select() {    
        try {
            objet = objetSelected
            if(objet.livraison != null){
                if((this.type.equals("VENTE") && estBonRetour) || (this.type.equals("ACHAT") && !estBonRetour)){
                    this.getFellow("fieldNumBLFss").readonly = true
                }
                livraisonControl(true)
            }else {
                livraisonControl(false)
            }
            
            bonCommandes = [objetSelected.bonCommande]
            bonCommandeSelected = objetSelected.bonCommande
            
            def cbc = this.getFellow("cobonCommandes")
            cbc.disabled = true
            new AnnotateDataBinder(cbc).loadAll()
            
            partenaireSelected = bonCommandeSelected.partenaire
            
            def copartenaire = this.getFellow("copartenaire")
            copartenaire.disabled = true
            new AnnotateDataBinder(copartenaire).loadAll()
            
            sortedLigneProduitsBc = objetSelected.bonCommande.ligneProduits.sort{it.produit.designation}
            if(produitPerissableManaged){
                sortedLigneProduitsBl = objetSelected.ligneProduits.sort{it.sequenceLigne}
            }else {
                sortedLigneProduitsBl = objetSelected.ligneProduits.sort{it.produit.designation}
            }
                
            def prodPerissableInList = false
            sortedLigneProduitsBc.each {
                if (it.produit.perissable){
                    prodPerissableInList = true
                    return    
                }
            }
            
            // si le bon de livraison est déjà livré alors les lignes de produits en détails sont déjà enregistrées
            if(objet.livraison == null){
                this.BonlivraisonEnDetails()
            }
            
            def lstBc = this.getFellow("lstligneProduitsBc")
            def lstBl = this.getFellow("lstligneProduitsBl")
            
            new AnnotateDataBinder(lstBc).loadAll()
            new AnnotateDataBinder(lstBl).loadAll()
            
            rafraichirField()
            activerBoutons(true)
            if(produitPerissableManaged && type.equals('VENTE')){
                this.getFellow("btnBLEnDetails").visible = false
                if (prodPerissableInList && objet.livraison == null){
                    this.getFellow("btnBLSimplifier").visible = true
                }
            }
            if(objet.livraison != null) {
                this.getFellow("btnUpdate").visible = false
                this.getFellow("btnDelete").visible = false
                this.getFellow("lstligneProduitsBl").disabled = true
            }
            if(objet.bonCommande.livree) {
                this.getFellow("btnUpdate").visible = false
            }
            this.getFellow("westPanel").open = true   
        } catch(Exception ex) {
            logger.error(ex.getMessage())
            logger.error(ex.getCause())
        }        
    }
    
    def update() {
        try {
            objet = bonLivraisonService.update(objet)
            if(objet.bonCommande.livree) {
                this.getFellow("btnUpdate").visible = false
            }
            Messagebox.show("Modification effectuée avec succès")
        }
        catch(Exception e) {
            logger.error "Error: ${e.message}", e
            Messagebox.show("Problème lors de la mise à jour\n" + e.getMessage(), "Erreur", Messagebox.OK, Messagebox.ERROR)
        }
        finally {
            filtrer()
            try {
                new AnnotateDataBinder(this.getFellow("lstligneProduitsBc")).loadAll()                
            } catch(Exception ex) {
                logger.warn(ex)
            }
        }
    }
    
    def delete() {
        bonLivraisonService.delete(objet)
        rafraichirList()          
        cancel()
    }
    
    def commandeChoisie() {        
        objet.bonCommande = bonCommandeSelected 
        bonCommandes = [bonCommandeSelected]
        
        new AnnotateDataBinder(this.getFellow("cobonCommandes")).loadAll()
        
        sortedLigneProduitsBc = objet.bonCommande.ligneProduits.sort{it.produit.designation}
        def lstBc = this.getFellow("lstligneProduitsBc")
        new AnnotateDataBinder(lstBc).loadAll()   
    }
    
    def choisirTout() {
        logger.debug("appel de choisir tout")
        for(lp in objet.bonCommande.ligneProduits) {
            logger.debug("dans la boucle for1")
            if(estBonRetour) {
                if(lp.quantiteRetournee != lp.quantiteLivree) {
                    def lpExisteDeja = false
                    for(nlp in objet.ligneProduits) {
                        logger.debug("dans la boucle for2")
                        if(lp.produit.code.equals(nlp.produit.code)) {
                            lpExisteDeja = true
                            break;
                        }
                    }
                    logger.debug(lpExisteDeja)
                    if(!lpExisteDeja) {
                        def newLp = new LigneProduit()
                        newLp.sequenceLigne = lp.sequenceLigne
                        newLp.quantite = lp.quantite
                        newLp.quantiteLivree = lp.quantiteLivree
                        newLp.quantiteRetournee = lp.quantiteLivree - lp.quantiteRetournee
                        newLp.trans_qr = lp.quantiteRetournee
                        newLp.remise = lp.remise
                        newLp.prix = lp.prix
                        newLp.prixDeduit = lp.prixDeduit
                        newLp.type = lp.type
                        newLp.produit = lp.produit
                        newLp.entrepot = lp.entrepot
                        objet.addToLigneProduits(newLp)    
                    }
                } 
            } else {
                if(lp.quantite != lp.quantiteLivree) {
                    def lpExisteDeja = false
                    for(nlp in objet.ligneProduits) {
                        if(lp.produit.code.equals(nlp.produit.code)) {
                            lpExisteDeja = true
                            break;
                        }
                    }
                    if(!lpExisteDeja) {
                        def newLp = new LigneProduit()
                        newLp.sequenceLigne = lp.sequenceLigne
                        newLp.quantite = lp.quantite
                        newLp.quantiteLivree = lp.quantite - lp.quantiteLivree
                        newLp.trans_ql = lp.quantiteLivree
                        newLp.remise = lp.remise
                        newLp.prix = lp.prix
                        newLp.prixDeduit = lp.prixDeduit
                        newLp.type = lp.type
                        newLp.produit = lp.produit
                        newLp.entrepot = lp.entrepot
                        objet.addToLigneProduits(newLp)    
                    }
                } 
            }
        }
        def lstBc = this.getFellow("lstligneProduitsBc")
        sortedLigneProduitsBc = objet.bonCommande.ligneProduits.sort{it.produit.designation}
        lstBc.clearSelection()
        new AnnotateDataBinder(lstBc).loadAll()
        
        def lstBl = this.getFellow("lstligneProduitsBl")
        sortedLigneProduitsBl = objet.ligneProduits.sort{it.produit.designation}
        lstBl.clearSelection()
        new AnnotateDataBinder(lstBl).loadAll()
        
        if(objet.ligneProduits.size() > 0) {
            if(this.getFellow("btnDelete").visible) {
                this.getFellow("btnUpdate").visible = true
            } else {
                this.getFellow("btnSave").visible = true
            }
            if(produitPerissableManaged && type.equals('VENTE')){
                objet.ligneProduits.each {
                    if (it.produit.perissable){
                        this.getFellow("btnBLSimplifier").visible = false
                        this.getFellow("btnBLEnDetails").visible = true
                        return    
                    }
                }
            }
        } else {
            this.getFellow("btnSave").visible = false
            this.getFellow("btnUpdate").visible = false
            if(produitPerissableManaged && type.equals('VENTE')){
                this.getFellow("btnBLEnDetails").visible = false
                this.getFellow("btnBLSimplifier").visible = false
            }
        }
    }
    def choisirDesElements() {
        for(lp in ligneProduitsSelectedBc) {
            if(estBonRetour) {
                if(lp.quantiteRetournee != lp.quantiteLivree) {
                    def lpExisteDeja = false
                    for(nlp in objet.ligneProduits) {
                        if(lp.produit.code.equals(nlp.produit.code)) {
                            lpExisteDeja = true
                            break;
                        }
                    }
                    if(!lpExisteDeja) {
                        def newLp = new LigneProduit()
                        newLp.sequenceLigne = lp.sequenceLigne
                        newLp.quantite = lp.quantite
                        newLp.quantiteLivree = lp.quantiteLivree
                        newLp.quantiteRetournee = lp.quantiteLivree - lp.quantiteRetournee
                        newLp.trans_qr = lp.quantiteRetournee
                        newLp.remise = lp.remise
                        newLp.prix = lp.prix
                        newLp.prixDeduit = lp.prixDeduit
                        newLp.type = lp.type
                        newLp.produit = lp.produit
                        newLp.entrepot = lp.entrepot
                        //lp.quantiteLivree = lp.quantite
                        objet.addToLigneProduits(newLp)    
                    }
                }   
            } else {
                if(lp.quantite != lp.quantiteLivree) {
                    def lpExisteDeja = false
                    for(nlp in objet.ligneProduits) {
                        if(lp.produit.code.equals(nlp.produit.code)) {
                            lpExisteDeja = true
                            break;
                        }
                    }
                    if(!lpExisteDeja) {
                        def newLp = new LigneProduit()
                        newLp.sequenceLigne = lp.sequenceLigne
                        newLp.quantite = lp.quantite
                        newLp.quantiteLivree = lp.quantite - lp.quantiteLivree
                        newLp.trans_ql = lp.quantiteLivree
                        newLp.remise = lp.remise
                        newLp.prix = lp.prix
                        newLp.prixDeduit = lp.prixDeduit
                        newLp.type = lp.type
                        newLp.produit = lp.produit
                        newLp.entrepot = lp.entrepot
                        //lp.quantiteLivree = lp.quantite
                        objet.addToLigneProduits(newLp)    
                    }
                }   
            }            
        }
        def lstBc = this.getFellow("lstligneProduitsBc")
        sortedLigneProduitsBc = objet.bonCommande.ligneProduits.sort{it.produit.designation}
        lstBc.clearSelection()
        new AnnotateDataBinder(lstBc).loadAll()
        
        def lstBl = this.getFellow("lstligneProduitsBl")
        sortedLigneProduitsBl = objet.ligneProduits.sort{it.produit.designation}
        lstBl.clearSelection()
        new AnnotateDataBinder(lstBl).loadAll()
        
        if(objet.ligneProduits.size() > 0) {
            if(this.getFellow("btnDelete").visible) {
                this.getFellow("btnUpdate").visible = true
            } else {
                this.getFellow("btnSave").visible = true
            }
            if(produitPerissableManaged && type.equals('VENTE')){
                objet.ligneProduits.each {
                    if (it.produit.perissable){
                        this.getFellow("btnBLSimplifier").visible = false
                        this.getFellow("btnBLEnDetails").visible = true
                        return    
                    }
                }
            }
        } else {
            this.getFellow("btnSave").visible = false
            this.getFellow("btnUpdate").visible = false
            if(produitPerissableManaged && type.equals('VENTE')){
                this.getFellow("btnBLEnDetails").visible = false
                this.getFellow("btnBLSimplifier").visible = false
            }
        }
    }
    def enleverDesElements() {
        objet.ligneProduits.removeAll(ligneProduitsSelectedBl)
        
        def lstBc = this.getFellow("lstligneProduitsBc")
        sortedLigneProduitsBc = objet.bonCommande.ligneProduits.sort{it.produit.designation}
        lstBc.clearSelection()
        new AnnotateDataBinder(lstBc).loadAll()
        
        def lstBl = this.getFellow("lstligneProduitsBl")
        sortedLigneProduitsBl = objet.ligneProduits.sort{it.produit.designation}
        lstBl.clearSelection()
        new AnnotateDataBinder(lstBl).loadAll()
        
        if(objet.ligneProduits.size() == 0) {
            if(this.getFellow("btnDelete").visible) {
                this.getFellow("btnUpdate").visible = false
            } else {
                this.getFellow("btnSave").visible = false
            }
        } 
    }
    def enleverTout() {
        objet.ligneProduits = null
        objet.ligneProduits = new ArrayList<LigneProduit>()
        
        def lstBc = this.getFellow("lstligneProduitsBc")
        sortedLigneProduitsBc = objet.bonCommande.ligneProduits.sort{it.produit.designation}
        lstBc.clearSelection()
        new AnnotateDataBinder(lstBc).loadAll()
        
        def lstBl = this.getFellow("lstligneProduitsBl")
        sortedLigneProduitsBl = objet.ligneProduits.sort{it.produit.designation}
        lstBl.clearSelection()
        new AnnotateDataBinder(lstBl).loadAll()
        
        this.getFellow("btnSave").visible = false
        this.getFellow("btnUpdate").visible = false
        if(produitPerissableManaged && type.equals('VENTE')){
            this.getFellow("btnBLEnDetails").visible = false
            this.getFellow("btnBLSimplifier").visible = false
        }
    }
    
    def genererDocument(Boolean defaut,Boolean avecEntetePied,Boolean sansEntetePied, Boolean avecCachet, Boolean avecCopie, Boolean avecDestinataire, String destinataire) {
        
        def typedocument
        def DocumentSubReport 
        if(estBonRetour) {
            typedocument = messageDocumentService.getMessageParType("BON_RETOUR", type);
            DocumentSubReport = Utilitaire.getDocumentSubReportBR(getClass())
        } else {
            typedocument = messageDocumentService.getMessageParType("BON_LIVRAISON", type);   
            DocumentSubReport = Utilitaire.getDocumentSubReportBL(getClass())
        }
       
        def document = objet.ligneProduits
        def societe = session.societe;
        def image = societe.trans_logo.getStreamData()
        
        def imageEntetePied = societe.trans_entetepied.getStreamData()
        def imageCachet = societe.trans_cachet.getStreamData()
        def imageCopie = societe.trans_copie.getStreamData()
        
        Boolean avecDetails = false
        Boolean avecHeader  
        Boolean avecFooter 
        
        if(defaut){
            avecHeader = true   
            avecFooter = true   
        }
        if (avecEntetePied){
            avecHeader = false   
            avecFooter = false
        }
        if (sansEntetePied){
            avecHeader = false   
            avecFooter = false
        }
        
        def nomsociete = societe.raisonSociale ;
        def titrerapport = "Bon de livraison"
        if(estBonRetour) {
            titrerapport = "Bon de retour"
        }
        def adressesociete = societe.adresse ;
        def codePostalesociete = societe.codePostale;
        def villesociete = societe.ville;
        def payssociete = societe.pays;
        def telephonesociete = societe.telephone;
        def emailsociete = societe.email;
        def faxsociete = societe.fax;
        def rcsociete = societe.rc;
        def idfsociete = societe.idF; 
        def sitesociete = societe.site; 
        def cnsssociete = societe.cnss;
        def patentesociete = societe.patente;
        
        def client = objet.bonCommande.partenaire;
        def nomclient = client.raisonSociale;
        def telclient = client.telephone;
        def faxclient = client.fax ;
        def adresseclient = client.adresseLivraison;
        if (adresseclient == null) {
            adresseclient = client.adresseFacturation
        }
        def villeclient = (client.ville != null)?client.ville.intitule: "";
        def paysclient = (client.ville != null)?client.ville.pays.intitule: "";
        def JasperReportname
        
        JasperReportname ="rapport_des_DocumentsWPRICE.jasper"

        def slogan ;
        
             
        Double autresfrais =0;
        
        
        String champ1Nom = "N° BL"
        if(estBonRetour) {
            champ1Nom = "N° BR"
        }
        String champ1Valeur  = objet.numBL
        String champ1Date = DateUtil.convertDateToStringByPattern("dd-MM-yyyy",objet.date)
        
        String champ2Nom = "N° BC"
        String champ2Valeur  
        String champ2Date 
        
        String champ3Nom = "N° Devis"
        String champ3Valeur  
        String champ3Date 
        
        String champ4Nom = "N° BP"
        String champ4Valeur  
        String champ4Date 
        
        String entete = typedocument.entete
        String pied = typedocument.pied
        
      
        Double totalht = objet.trans_totalht
        Double totaltva = objet.trans_totaltva 
        Double totalttc = objet.trans_totalttc
        
        double TotalDocument = totalttc + autresfrais
        
        Integer apresLaVergule = 0
        
        String TotalDocumentEncaractere = ""
       
        String arretermessage = ""
        
        if(type.equals("VENTE")) {
            champ2Valeur = objet.bonCommande.reference
        }
        else{
            champ2Valeur = objet.bonCommande.numBC              
        }
        champ2Date = DateUtil.convertDateToStringByPattern("dd-MM-yyyy",objet.bonCommande.date)  
        
        if (objet.bonCommande.deviss != null && objet.bonCommande.deviss.size() > 1) {

            champ3Valeur = "Plusieurs Devis";
        }
        else if (objet.bonCommande.deviss != null && objet.bonCommande.deviss.size() == 1) {
            
            //            def devis = objet.deviss[0]
            //            champ2Valeur = devis.numDevis
            //            champ2Date = DateUtil.convertDateToStringByPattern("dd-MM-yyyy",devis.date)
            
            objet.bonCommande.deviss.each {
                champ3Valeur = it.numDevis
                champ3Date = DateUtil.convertDateToStringByPattern("dd-MM-yyyy",it.date)
            }
            
            
        } else {
            champ3Nom = ""
        }
             
        def reportDef = new JasperReportDef(name:JasperReportname,
            fileFormat:JasperExportFormat.PDF_FORMAT,
            reportData : [objet],
            parameters : ['nomsociete' : nomsociete,'titrerapport' : titrerapport , 'adressesociete' : adressesociete,'codePostalesociete' : codePostalesociete,'villesociete' : villesociete, 'payssociete' : payssociete, 'telephonesociete':telephonesociete,'emailsociete': emailsociete,'faxsociete' : faxsociete,'rcsociete' :rcsociete ,'idfsociete':idfsociete,'sitesociete':sitesociete ,'cnsssociete':cnsssociete,'patentesociete':patentesociete,'nomclient' :nomclient,'telclient':telclient,'faxclient':faxclient,'adresseclient':adresseclient,'villeclient':villeclient,'paysclient':paysclient,'slogan':slogan,'arretermessage':arretermessage,'autresfrais':autresfrais,'champ1Nom':champ1Nom,'champ1Valeur':champ1Valeur,'champ1Date':champ1Date,'champ2Nom':champ2Nom,'champ2Valeur':champ2Valeur,'champ2Date':champ2Date,'champ3Nom':champ3Nom,'champ3Valeur':champ3Valeur,'champ3Date':champ3Date,'entete':entete,'pied':pied,'document':document,'DocumentSubReport':DocumentSubReport,'Image' : image,'totalht' :totalht,'totaltva' :totaltva,'totalttc' :totalttc, 'ImageEntetePied':imageEntetePied,'ImageCachet': imageCachet, 'ImageCopie':imageCopie,'avecHeader':avecHeader, 'avecFooter' : avecFooter,'avecDetails':avecDetails,'avecEntetePied':avecEntetePied,'avecCachet':avecCachet,'avecCopie':avecCopie, 'avecDestinataire' : avecDestinataire, 'destinataire': destinataire]
        )
        def bit = jasperService.generateReport(reportDef).toByteArray()
        def nom_fichier = "rapport_des_Documents_"+objet.numBL+"_"+nomclient+".pdf"
        //Filedownload.save(bit, "application/file", nom_fichier);
        Media amedia = new AMedia(nom_fichier, "pdf", "application/pdf", bit);
        Map map = new HashMap();
        map.put("content", amedia);
        Executions.createComponents("/zul/util/pdfviewer.zul", null, map).doModal();
    }
        
    /**
     * Generation du rapport pdf
     **/
    def genererRapportPdf() {
        String nomsociete = session.societe.raisonSociale
        String titrerapport = "Rapport des BonLivraisons"
        if(estBonRetour) {
            titrerapport = "Rapport des Bons retour"
        }
        def listeObjetsExporter = getObjetsToExport()
        def reportDef = new JasperReportDef(name:'rapport_des_BonLivraisons.jrxml',
            fileFormat:JasperExportFormat.PDF_FORMAT,
            reportData : listeObjetsExporter,
            parameters : [  'nomsociete':nomsociete, 'titrerapport':titrerapport ]
        )
        def bit = jasperService.generateReport(reportDef).toByteArray()
        def nom_fichier = "rapport_des_BonLivraisons.pdf"
        if(estBonRetour) {
            nom_fichier = "rapport_des_BonsRetours.pdf"
        }
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
        String titrerapport = "Rapport des BonLivraisons"
        if(estBonRetour) {
            titrerapport = "Rapport des Bons retour"
        }
        def listeObjetsExporter = getObjetsToExport()
        def reportDef = new JasperReportDef(name:'rapport_des_BonLivraisons.jrxml',
            fileFormat:JasperExportFormat.XLS_FORMAT,
            reportData : listeObjetsExporter,
            parameters : [  'nomsociete':nomsociete, 'titrerapport':titrerapport ]
        )
        def bit = jasperService.generateReport(reportDef).toByteArray()
        def nom_fichier = "rapport_des_BonLivraisons.xls"
        if(estBonRetour) {
            nom_fichier = "rapport_des_BonsRetours.xls"
        }
        Filedownload.save(bit, "application/file", nom_fichier);
        
    }
    
        
    def doModalDialogChoix(){
        this.getFellow("modalDialogChoix").visible = true
        this.getFellow("modalDialogChoix").doModal() 
    }

    def infosLivraison() {
        def winLivraison = this.getFellow("winLivraison")
        if(objet.livraison) {
            nouvelleLivraison = objet.livraison
            winLivraison.getFellow("btnAjouterLivraison").visible = false
            nouvelleLivraison.modeLivraison = modeLivraisons.find { it.code.equals(objet.livraison.modeLivraison.code)}
        } else {
            nouvelleLivraison = new Livraison()
            nouvelleLivraison.numExpedition = paternCompteurService.getProchainNumLivraison() 
            if(objet.bonCommande.partenaire.modeLivraison) {
                nouvelleLivraison.modeLivraison = modeLivraisons.find { it.code.equals(objet.bonCommande.partenaire.modeLivraison.code)}
            } else if(modeLivraisons.size() > 0) {
                nouvelleLivraison.modeLivraison = modeLivraisons.get(0)
            }
            winLivraison.getFellow("btnAjouterLivraison").visible = true
        }
        new AnnotateDataBinder(winLivraison.getFellow("fieldNbrColis")).loadAll()
        new AnnotateDataBinder(winLivraison.getFellow("fieldPoidTotal")).loadAll()
        new AnnotateDataBinder(winLivraison.getFellow("fieldDateExcpedition")).loadAll()
        new AnnotateDataBinder(winLivraison.getFellow("fieldFrais")).loadAll()
        new AnnotateDataBinder(winLivraison.getFellow("comodeLivraisons")).loadAll()
        new AnnotateDataBinder(winLivraison.getFellow("fieldNumExpedition")).loadAll()
        winLivraison.visible = true
        winLivraison.doModal() 
    }
    
    def BonlivraisonEnDetails(){
        if(produitPerissableManaged && type.equals('VENTE')){
            sortedLigneProduitsBlDeBase = sortedLigneProduitsBl
            sortedLigneProduitsBl = bonLivraisonService.getLpsBonLivraisonEnDetails(sortedLigneProduitsBl)
            
            excludedLineProduitList = new ArrayList<LigneProduit>()
            sortedLigneProduitsBl.each {
                if (it.produit.perissable && it.date_preremption == null){
                    sortedLigneProduitsBlDeBase.each { lpbase ->
                        if (lpbase.produit.code.equals(it.produit.code)){
                            lpbase.sequenceLigne = it.sequenceLigne
                            lpbase = ligneProduitService.update(lpbase)
                            excludedLineProduitList.add(lpbase)
                            it.date_preremption = lpbase.date_preremption
                        }
                    }
                }  
            }
            this.getFellow("btnBLSimplifier").visible = true
            this.getFellow("btnBLEnDetails").visible = false
            new AnnotateDataBinder(this.getFellow("lstligneProduitsBl")).loadAll()
        }
    }
    
    def BonlivraisonSimplifier(){
        if(produitPerissableManaged && type.equals('VENTE')){
            sortedLigneProduitsBl = sortedLigneProduitsBlDeBase
            this.getFellow("btnBLSimplifier").visible = false
            this.getFellow("btnBLEnDetails").visible = true
            new AnnotateDataBinder(this.getFellow("lstligneProduitsBl")).loadAll()
        }
    }
    
    def ajouterLivraison() {
        objet.estLivre = true
        objet.ligneProduits = new ArrayList<LigneProduit>()
        sortedLigneProduitsBl.each {
            objet.addToLigneProduits(it)
            //println "ligneProduit : [ " + it.produit.designation + ' ' + it.quantite + ' ' + it.date_preremption + ' ]'
        }
        
        nouvelleLivraison = livraisonService.sauverEtAjouterBl(nouvelleLivraison, objet)
        objet.bonCommande = BonCommande.findById(objet.bonCommande.id)
        sortedLigneProduitsBc = objet.bonCommande.ligneProduits.sort{it.produit.designation}        
        
        Messagebox.show("Livraison ajoutée avec succès")
        this.getFellow("btnUpdate").visible = false
        this.getFellow("winLivraison").visible = false
        if(objet.bonCommande.livree) {
            bonLivraisonService.deleteBonsLivraisonsZambies(objet)
            filtrer()
        }
        
        filtrer()
        new AnnotateDataBinder(this.getFellow("lstligneProduitsBc")).loadAll()                
        new AnnotateDataBinder(this.getFellow("lstligneProduitsBl")).loadAll()
        this.getFellow("generationControl").visible = false
    }
    
    def partenaireSelectionner() {
        bonCommandes = bonCommandeService.getBonCommandePartenaireNonLivree(type, partenaireSelected)   
        def binderboncommandes = new AnnotateDataBinder(this.getFellow("cobonCommandes"))
        binderboncommandes.loadAll()   
    }
    
    def bonCommandeSelectionner() {
        partenaireSelected = bonCommandeSelected.partenaire
        new AnnotateDataBinder(this.getFellow("copartenaire")).loadAll()   
    }

}

