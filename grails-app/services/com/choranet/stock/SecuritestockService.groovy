
package com.choranet.stock;

import com.choranet.commun.SuperService

/**
 * SecuritestockService Service pour la gestion des opérations
 * transactionnelles pour l'objet Securitestock
 */
class SecuritestockService extends SuperService {

    static transactional = true

    def alertsSecuriteStockService 
    
    def list() throws Exception {
        return super.list(Securitestock.class)
    }
   
    def getSecuritesStockEntrepot(Entrepot e) {
        def c = Securitestock.createCriteria()
        def result = c.list {
            entrepot {
                eq("code", e.code)
            }
        }
        return result
    }
    def getQuantiteProduitEnStock(Produit p) {
        def c = Securitestock.createCriteria()
        def sss = c.list {
            produit {
                eq("code", p.code)
            }
        }
        def resultat = 0
        for(Securitestock ss : sss) {
            resultat += ss.stockReel
        } 
        return resultat
    }
    
     def getQuantiteProduitEnEntrepot(Produit p, Entrepot e) {
        def c = Securitestock.createCriteria()
        def sss = c.list {
            produit {
                eq("code", p.code)
            }
            entrepot {
                eq("code", e.code)
            }
        }
        def resultat = 0
        for(Securitestock ss : sss) {
            resultat += ss.stockReel
        } 
        return resultat
    }
    
    
    def getQuantiteProduitEnEntrepot(Produit p, Entrepot e, objet) {
        def c = Securitestock.createCriteria()
        def sss = c.list {
            produit {
                eq("code", p.code)
            }
            entrepot {
                eq("code", e.code)
            }
        }
        objet.stockReel = 0
        objet.stockEntre = 0
        objet.stockSortie = 0
        for(Securitestock ss : sss) {
            objet.stockReel += ss.stockReel
            objet.stockEntre += ss.stockEntre
            objet.stockSortie += ss.stockSortie
        } 
        return objet
    }
    
    def getSecuriteStockEnEntrepotPourDLC(Produit p, Entrepot e, date_preremption){
        def c = Securitestock.createCriteria()
        def result = c.get {
            if(p.perissable){
                eq("date_preremption", date_preremption)
            }else 
            isNull("date_preremption")
            produit {
                eq("code", p.code)
            }
            if(e != null){
                entrepot {
                    eq("code", e.code)
                }
            }
        }
        return result
    }
    
    def isProduiEnEntrepotPourDatePreremptionFound(Produit p, Entrepot e, date_preremption){
        def result = getSecuriteStockEnEntrepotPourDLC(p, e, date_preremption)
        return (result != null)
    }
    
    def getQuantiteProduiEnEntrepotPourDatePreremption(Produit p, Entrepot e, date_preremption) {
        if (p == null) {
            return 0d
        }
        def c = Securitestock.createCriteria()
        def sss = c.list {
            if(date_preremption != null){
                eq("date_preremption", date_preremption)
            }else 
            isNull("date_preremption")
            produit {
                eq("code", p.code)
            }
            if(e != null){
                entrepot {
                    eq("code", e.code)
                }
            }
        }
        def resultat = 0
        for(Securitestock ss : sss) {
            resultat += ss.stockReel
        } 
        return resultat
    }
    
    def getQuantiteProduiEnEntrepotPourDatePreremption(Produit p, Entrepot e, date_preremption, objet) {
        if (p == null) {
            return 0d
        }
        def c = Securitestock.createCriteria()
        def sss = c.list {
            if(date_preremption != null){
                eq("date_preremption", date_preremption)
            }else 
            isNull("date_preremption")
            produit {
                eq("code", p.code)
            }
            if(e != null){
                entrepot {
                    eq("code", e.code)
                }
            }
        }
        objet.stockReel = 0
        objet.stockEntre = 0
        objet.stockSortie = 0
        for(Securitestock ss : sss) {
            objet.stockReel += ss.stockReel
            objet.stockEntre += ss.stockEntre
            objet.stockSortie += ss.stockSortie
        } 
        return objet
    }
        
    def getProduitsEnStock(Entrepot e) {
        if (e == null) return
        def c = Securitestock.createCriteria()
        def ss = c.list {
            entrepot {
                eq("code", e.code)
            }
        }
        return ss*.produit.unique()
    }
    
    def getProduitsSansSecuriteStock(Entrepot e) {
        def c = Securitestock.createCriteria()
        def ss = c.list {
            entrepot {
                eq("code", e.code)
            }
        }
        def ids = ss*.produit*.id.unique()
        def idds = []
        ids.each {
            idds.addAll(it)
        }
        def cbis = Produit.createCriteria()
        def produits = cbis.list {
            if(idds.size() > 0) {
                not{"in"("id", idds)}
            }
        }
        return produits
    }
    
    // Récuperer les dates limite de consommation pour les produits périssables
    def getDLCs(Produit p, Entrepot e){
        if(!p.perissable){
            return null
        }
        def req = Securitestock.createCriteria()
        def result = req.list{
            //isNotNull("date_preremption")
            produit {
                eq("code", p.code)
            }
            if(e != null){
                entrepot {
                    eq("code", e.code)
                }
            }
        }
        return result.date_preremption
    }
    
    /*def getSecuriteStockEnEntrepotPourQtyProduitPerissable(Produit p, Entrepot e, quantiteProduit){
        if (p == null || p.perissable == null || !p.perissable){
            return null
        }
        def c = Securitestock.createCriteria()
        def ssList = c.list {
            produit {
                eq("code", p.code)
            }
            if(e != null){
                entrepot {
                    eq("code", e.code)
                }
            }
        }
        def sommeQuantites = 0d
        for(ssCourante in ssList){
            
        }
        return result
    }
    */
    def mettreAjourSecuriteStockApresMouvement(m) {
        def c = Securitestock.createCriteria()
        if(m.typeMouvement.equals("ACHAT") || m.typeMouvement.equals("RETOUR VENTE")) {
            def ss = c.get {
                if(m.date_preremption != null){
                    eq("date_preremption", m.date_preremption)
                }else{
                    isNull("date_preremption")
                }
                entrepot {
                    eq("code", m.empDestination.code)
                }
                produit {
                    eq("code", m.produit.code)
                }
            }
            if(ss == null) {
                ss = new Securitestock()               
                ss.produit = m.produit
                ss.entrepot = m.empDestination
                ss.date_preremption = m.date_preremption
                ss.stockEntre = 0
                ss.stockSortie = 0
                save(ss)
            }
            ss.stockEntre += m.quantite
            ss.stockReel += m.quantite
            update(ss)     
            alertsSecuriteStockService.mettreAjourAlertSecuriteStockApresMouvement(m, null, ss)
        } else if (m.typeMouvement.equals("VENTE") || m.typeMouvement.equals("RETOUR ACHAT")) {
            def ss = c.get {
                if(m.date_preremption != null){
                    eq("date_preremption", m.date_preremption)
                }else{
                    isNull("date_preremption")
                }
                entrepot {
                    eq("code", m.empSource.code)
                }
                produit {
                    eq("code", m.produit.code)
                }
            }
            if(ss == null) {
                ss = new Securitestock()                
                ss.produit = m.produit
                ss.entrepot = m.empSource
                ss.date_preremption = m.date_preremption
                ss.stockEntre = 0
                ss.stockSortie = 0
                save(ss)
            }
            ss.stockSortie += m.quantite
            ss.stockReel -= m.quantite
            update(ss)
            alertsSecuriteStockService.mettreAjourAlertSecuriteStockApresMouvement(m, ss, null)
        } else if(m.typeMouvement.equals("TRANSFERT")) {
            def ssSource = c.get {
                if(m.date_preremption != null){
                    eq("date_preremption", m.date_preremption)
                }else{
                    isNull("date_preremption")
                }
                entrepot {
                    eq("code", m.empSource.code)
                }
                produit {
                    eq("code", m.produit.code)
                }
            }
            if(ssSource == null) {
                ssSource = new Securitestock()                
                ssSource.produit = m.produit
                ssSource.entrepot = m.empSource
                ssSource.date_preremption = m.date_preremption
                ssSource.stockEntre = 0
                ssSource.stockSortie = 0
                save(ssSource)
            }
            ssSource.stockSortie += m.quantite
            ssSource.stockReel -= m.quantite
            
            // Pour les produits périssables dont la quantité du stock devient null
            if (ssSource.stockReel == 0d && ssSource.date_preremption != null){
                delete(ssSource)
            }
            else update(ssSource)
            
            def cbis = Securitestock.createCriteria()
            def ssDestination = cbis.get {
                if(m.date_preremption != null){
                    eq("date_preremption", m.date_preremption)
                }else{
                    isNull("date_preremption")
                }
                entrepot {
                    eq("code", m.empDestination.code)
                }
                produit {
                    eq("code", m.produit.code)
                }   
            }
            if(ssDestination == null) {
                ssDestination = new Securitestock()                
                ssDestination.produit = m.produit
                ssDestination.entrepot = m.empDestination
                ssDestination.date_preremption = m.date_preremption
                ssDestination.stockEntre = 0
                ssDestination.stockSortie = 0
                save(ssDestination)
            }
            ssDestination.stockEntre += m.quantite
            ssDestination.stockReel += m.quantite
            update(ssDestination)    
            alertsSecuriteStockService.mettreAjourAlertSecuriteStockApresMouvement(m, ssSource, ssDestination)
        } else if (m.typeMouvement.equals("INVENTAIRE")) {
            def ss = c.get {
                if(m.date_preremption != null){
                    eq("date_preremption", m.date_preremption)
                }else{
                    isNull("date_preremption")
                }
                entrepot {
                    eq("code", m.empDestination.code)
                }
                produit {
                    eq("code", m.produit.code)
                }
            }
            if(ss == null) {
                ss = new Securitestock()                
                ss.produit = m.produit
                ss.entrepot = m.empDestination
                ss.date_preremption = m.date_preremption
                ss.stockEntre = 0
                ss.stockSortie = 0
                save(ss)
            }
            ss.stockEntre = m.quantite
            ss.stockReel = m.quantite
            update(ss)        
            alertsSecuriteStockService.mettreAjourAlertSecuriteStockApresMouvement(m, null, ss)
        // A Revoir !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!    
        } else if (m.typeMouvement.equals("AFFECTATION CHANTIER")) {
            def ss = c.get {
                entrepot {
                    eq("code", m.empSource.code)
                }
                produit {
                    eq("code", m.produit.code)
                }
            }
            if(ss == null) {
                ss = new Securitestock()                
                ss.produit = m.produit
                ss.entrepot = m.empSource
                ss.stockEntre = 0
                ss.stockSortie = 0
                save(ss)
            }
            ss.stockSortie += m.quantite
            ss.stockReel -= m.quantite
            update(ss)
            alertsSecuriteStockService.mettreAjourAlertSecuriteStockApresMouvement(m, ss, null)
        }
    }
    
    def getEtatsStockPourProduitPerissable(produitPerissable, duree_limite_expiration) {
        if (produitPerissable == null) {
            return null
        }
        def c = Securitestock.createCriteria()
        def result = c.list {
            if(duree_limite_expiration >= 0){
                le("date_preremption", new Date() + duree_limite_expiration)
            }
            produit {
                eq("code", produitPerissable.code)
            }
            gt("stockReel" , 0d)
        }
        return result
    }
}