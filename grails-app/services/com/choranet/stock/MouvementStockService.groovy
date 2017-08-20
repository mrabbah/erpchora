
package com.choranet.stock;

import org.springframework.web.context.request.RequestContextHolder
//import java.util.Date
import com.choranet.commun.SuperService
import com.choranet.projet.AffectationProduit
import com.choranet.gesticom.LigneProduit

/**
 * MouvementStockService Service pour la gestion des opérations
 * transactionnelles pour l'objet MouvementStock
 */
class MouvementStockService extends SuperService {

    static transactional = true

    def securitestockService
        
    def list() throws Exception {
        return super.list(MouvementStock.class)
    }
    
//    def getPrixMvms(Produit p, entrepot, date_preremption){
//        def req = MouvementStock.createCriteria()
////        def prixMvms = new ArrayList()
//        def result = req.list{
//            
//            isNotNull("prixDeduitMvm")
//            
//            if(p.perissable){
//                eq("date_preremption", date_preremption)
//            }else 
//            isNull("date_preremption")
//            
//            if(entrepot != null){
//                if ("typeMouvement".equals("VENTE") || "typeMouvement".equals("RETOUR ACHAT")){
//                    empSource {
//                        eq("code", entrepot.code)
//                    }
//                }else {
//                    empDestination {
//                        eq("code", entrepot.code)
//                    }
//                }
//            }
//            produit {
//                eq("code", p.code)
//            }
//        }
//        return result.prixDeduitMvm
//    }
    
//    def getQuantiteProduiEnEntrepotPourDlcEtPrixMvm(Produit p, entrepot, date_preremption, prixMvm){
//        if (p == null) {
//            return 0d
//        }
//        def c = MouvementStock.createCriteria()
//        def sss = c.list {
//            
//            if(!prixMvm.toString().equals("Non Précisé")){
//                eq("prixDeduitMvm", prixMvm)
//            }else isNull("prixDeduitMvm")
//            
//            if(!date_preremption.toString().equals("Non Précisée")){
//                eq("date_preremption", date_preremption)
//            }else isNull("date_preremption")
//            
//            produit {
//                eq("code", p.code)
//            }
//            
//            if(entrepot != null){
//                if ("typeMouvement".equals("VENTE") || "typeMouvement".equals("RETOUR ACHAT")){
//                    empSource {
//                        eq("code", entrepot.code)
//                    }
//                }else {
//                    empDestination {
//                        eq("code", entrepot.code)
//                    }
//                }
//            }
//        }
//        def resultat = 0
//        for(MouvementStock ss : sss) {
//            resultat += ss.quantite
//        } 
//        return resultat
//    }
    
    def ajouterMouvementAffectationProduit(AffectationProduit ap) {
        //if(ap.entrepot != null) {
        MouvementStock m = new MouvementStock();
        m.date = ap.date;
        m.quantite = ap.quantite
        m.typeMouvement = "AFFECTATION CHANTIER"
        //TODO préciser l'emplacement source en dépend du réel entrepot et non par defaut
        m.empSource = ap.produit.empReception
        m.empDestination = null
        m.produit = ap.produit
        save(m)
        securitestockService.mettreAjourSecuriteStockApresMouvement(m)   
        //}       
    }
    
    def getLignesProduitMvmForProduitPerissable(ligneProduit){
        def prod = ligneProduit.produit
        if (prod == null || prod.perissable == null || !prod.perissable){
            return null
        }
        def depot = ligneProduit.entrepot
        def ss = Securitestock.createCriteria()
        def ssList = ss.list {
            produit {
                eq("code", prod.code)
            }
            if(depot != null){
                entrepot {
                    eq("code", depot.code)
                }
            }
            gt("stockReel", 0d)
        }
        def ssFifoList = ssList.sort{it.date_preremption}
        
        println 'ssFifoList : [' + ssFifoList.date_preremption + ',' + ssFifoList.stockReel + ']'
        println '[produit , entrepot, quantite] : [ ' + ligneProduit.produit.designation + ' , ' + ligneProduit.entrepot.intitule +  ' , ' +  ligneProduit.quantite + ' ]'
        
        def quantiteALivrerDeBase = ligneProduit.quantite
        def quantiteALivrerDisponible = 0d
        def lignesProduitMvm = new ArrayList()
        
        def newlp 
        for(ssCourante in ssFifoList){
            newlp = new LigneProduit()
            newlp.produit = ligneProduit.produit
            newlp.entrepot = ligneProduit.entrepot
            newlp.prixDeduit = ligneProduit.prixDeduit
            newlp.prix = ligneProduit.prix
            newlp.type = ligneProduit.type
            quantiteALivrerDisponible = ssCourante.stockReel
            
            println 'quantiteALivrerDisponible : ' + quantiteALivrerDisponible 
            println 'quantiteALivrerDeBase qui doit etre livré: ' + quantiteALivrerDeBase
            
            if(quantiteALivrerDisponible >= quantiteALivrerDeBase){
                newlp.quantite = quantiteALivrerDeBase 
                newlp.quantiteLivree = newlp.quantite
                newlp.date_preremption = ssCourante.date_preremption
                lignesProduitMvm.add(newlp)
                println '[dlc, qty] : [ ' + newlp.date_preremption + ' , ' + newlp.quantite + ' ]'
                quantiteALivrerDeBase = 0
                println 'quantiteALivrerDeBase aprés livraison: ' + quantiteALivrerDeBase
                break
            }else {
                newlp.quantite = quantiteALivrerDisponible
                newlp.quantiteLivree = newlp.quantite
                newlp.date_preremption = ssCourante.date_preremption
                lignesProduitMvm.add(newlp)
                println '[dlc, qty] : [ ' + newlp.date_preremption + ' , ' + newlp.quantite + ' ]'
                quantiteALivrerDeBase -= quantiteALivrerDisponible
                println 'quantiteALivrerDeBase aprés livraison: ' + quantiteALivrerDeBase
            }
        }
        // si le stock réel est épuisé alors que la quantité A livrer n'est pas encore atteinte
        if (quantiteALivrerDeBase > 0){
            newlp = new LigneProduit()
            newlp.produit = ligneProduit.produit
            newlp.entrepot = ligneProduit.entrepot
            newlp.prix = ligneProduit.prix
            newlp.prixDeduit = ligneProduit.prixDeduit
            newlp.type = ligneProduit.type
            newlp.quantite = quantiteALivrerDeBase
            newlp.quantiteLivree = newlp.quantite
            newlp.date_preremption = null
            lignesProduitMvm.add(newlp)
        }
        
        for(lp in lignesProduitMvm){
            println '[prod, dlc, qty] : [ ' + lp.produit.designation + ' , ' + lp.date_preremption + ' , ' + lp.quantite + ' ]'
        }
        return lignesProduitMvm
    }
    
    def getLignesProduitMvmEnDetails(lignesProduitBaseList){
        def lignesProduitDetailsList = new ArrayList() 
        for(lp in lignesProduitBaseList){
            def lpsMvm = getLignesProduitMvmForProduitPerissable(lp)
            if (lpsMvm != null){
                lignesProduitDetailsList.addAll(lpsMvm)
            }else {
                lignesProduitDetailsList.add(lp)
            }
        }
        def seqLigne = 1
        def sortedLignesProduitDetailsList = lignesProduitDetailsList.sort{it.produit.designation}
        sortedLignesProduitDetailsList.each {
            it.sequenceLigne = seqLigne++
        }
        return lignesProduitDetailsList
    }
    
    def ajouterMouvementBlApresLivraison(bl) {
        def ligneProduitDeBase = bl.ligneProduits
        //        if(bl.type.equals("VENTE")){
        //            ligneProduitDeBase = getLignesProduitMvmEnDetails(bl.ligneProduits)
        //        }
        for(lp in ligneProduitDeBase) {
            def m = new MouvementStock();
            m.date = bl.livraison.date
            m.quantite = lp.quantiteLivree
            m.typeMouvement = bl.type
            m.date_preremption = lp.date_preremption
            m.prixDeduitMvm = lp.prixDeduit
            if(bl.estBonRetour) {
                m.typeMouvement = "RETOUR " + m.typeMouvement
                if(bl.type.equals("VENTE")) {
                    m.empSource = null//bl.emplacementLivraison
                    m.empDestination = lp.entrepot
                } else if (bl.type.equals("ACHAT")) {
                    m.empSource = lp.entrepot
                    m.empDestination = null
                }
            } else {
                if(bl.type.equals("VENTE")) {
                    m.empSource = lp.entrepot                  
                    m.empDestination = null
                } else if (bl.type.equals("ACHAT")) {
                    m.empSource = null
                    m.empDestination = lp.entrepot
                }
            }
            logger.debug("m.produit = lp.produit")
            m.produit = lp.produit
            logger.debug("save(m)")
            save(m)
            logger.debug("securitestockService.mettreAjourSecuriteStockApresMouvement(m)")
            securitestockService.mettreAjourSecuriteStockApresMouvement(m)
        }
    }
    
    def getPrixMvmSuivantMVStock(entrepot, produit){
        def prixDeduit = produit.prixAchat
        def mvs = entrepot.modeValorisation
       
        if (mvs.equals("PRIX_VENTE")) prixDeduit = produit.prixVenteStandard
        if (mvs.equals("PRIX_MOYEN_PENDERE")) prixDeduit = produit.prixMoyenPendere 
       
        return prixDeduit
    }
    
    def ajouterMouvementInventaire(LigneInventaire ligne, Inventaire inv) {
        MouvementStock m = new MouvementStock();
        m.date = new Date();
        m.quantite = ligne.quantiteInv;
        m.typeMouvement = "INVENTAIRE"
        m.empDestination = inv.entrepot
        m.empSource = null
        m.produit = ligne.produit;
        
        m.prixDeduitMvm = this.getPrixMvmSuivantMVStock(inv.entrepot,ligne.produit)
        m.date_preremption = ligne.date_preremption
        save(m)
        securitestockService.mettreAjourSecuriteStockApresMouvement(m)
    }
    
    def ajouterMouvementTransfert(m) {
        //m.prixDeduitMvm = 0d;
        save(m)
        securitestockService.mettreAjourSecuriteStockApresMouvement(m)
    }
    
    def save(Object object) throws Exception {
        def session = RequestContextHolder.currentRequestAttributes().getSession()
        object.utilisateur = session.utilisateur
        super.save(object)
    }
    
    
    //Pour sécuriser l'application de mettre à jour ou supprimer un mouvement de stock
    def update(Object object) {
        throw new UnsupportedOperationException( "Ne pas mettre à jour les mouvements de stock" );
    }
    def delete(Object object) {
        throw new UnsupportedOperationException( "Ne pas supprimer un mouvement de stock" );
    }
    
    
    def getPrixLiquidationUnitaire(produitPerissable, duree_limite_expiration){
        def prixLiquidation = 0
        def qtStockReel = 0
        def stockReel = securitestockService.getEtatsStockPourProduitPerissable(produitPerissable, duree_limite_expiration)
        for(Securitestock ss : stockReel) {
            qtStockReel += ss.stockReel
        } 
        println 'qtStockReel : ' + qtStockReel
        if(qtStockReel == 0)
        return prixLiquidation

        def qtAchat = 0d
        def prixMvmsAchat = 0d
        def mvmAchat = MouvementStock.createCriteria().list {
            eq("typeMouvement", "ACHAT")
            if(duree_limite_expiration >= 0){
                le("date_preremption", new Date() + duree_limite_expiration)
            }
            produit {
                eq("code", produitPerissable.code)
            }
            order("date", "desc")
        }
        mvmAchat.each {
            if(qtAchat < qtStockReel){
                qtAchat += it.quantite
                prixMvmsAchat += it.quantite * it.prixDeduitMvm
            }
        }
        println 'qtAchat : ' + qtAchat
        println 'prixMvmsAchat : ' + prixMvmsAchat
        
        def qtVente = 0d
        def prixMvmsVente = 0d
        def qtVendu = qtAchat - qtStockReel
        println 'qtVendu : ' + qtVendu
        
        def mvmVente = MouvementStock.createCriteria().list {
            eq("typeMouvement", "VENTE")
            if(duree_limite_expiration >= 0){
                le("date_preremption", new Date() + duree_limite_expiration)
            }
            produit {
                eq("code", produitPerissable.code)
            }
            order("date", "desc")
        }
        mvmVente.each {
            if(qtVente < qtVendu){
                qtVente += it.quantite
                prixMvmsVente += it.quantite * it.prixDeduitMvm
            }
        }
        println 'qtVente : ' + qtVente
        println 'prixMvmsVente : ' + prixMvmsVente
        
        def prixStockReel = prixMvmsAchat - prixMvmsVente
        if (prixStockReel > 0) {
            prixLiquidation = prixStockReel / qtStockReel
        }
        println 'prixLiquidation : ' + prixLiquidation
        return prixLiquidation
    }
}