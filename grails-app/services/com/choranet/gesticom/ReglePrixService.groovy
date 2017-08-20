
package com.choranet.gesticom;

import com.choranet.commun.SuperService

/**
 * ReglePrixService Service pour la gestion des opérations
 * transactionnelles pour l'objet ReglePrix
 */
class ReglePrixService extends SuperService implements Comparator {

    static transactional = true


    int compare(rpA, rpB) {
        if (rpA.priorite < rpB.priorite) return -1
        if (rpA.priorite == rpB.priorite) return 0
        if (rpA.priorite > rpB.priorite) return 1
    }
    
    def list() throws Exception {
        return super.list(ReglePrix.class)
    }
    
    def getCurrentRegle(regle) {
        def criteria = ReglePrix.createCriteria()
        def regleCourante = criteria.list {
            not {
                eq("code", regle.code)
            } 
        }
        return regleCourante
    }
        
    def getCategoriePartenairesForCurrentRegle(ReglePrix regle) {
        return getCurrentRegle(regle).categoriePartenaires
    }
    
    def getPartenairesForCurrentRegle(ReglePrix regle) {
        return getCurrentRegle(regle).partenaires
    }
    
    def getCategorieProduitsForCurrentRegle(ReglePrix regle) {
        return getCurrentRegle(regle).categorieProduits
    }
    
    def getProduitsForCurrentRegle(ReglePrix regle) {
        return getCurrentRegle(regle).produits
    }
    
    // fonction recursive pour vérifier si une catégorie donnée est fait partie d'une autre catégorie ou ses catégories parentes 
    def isASonCategorieProduit(currentCategory, refCategory){ 
        if (refCategory != null){
            if (currentCategory.code == refCategory.code) return true
            isASonCategorieProduit(currentCategory, refCategory.categorieParente)
        }
        else return false
    }
    // fonction recursive pour vérifier si une catégorie donnée est fait partie d'une autre catégorie ou ses catégories parentes 
    def isASonCategoriePartenaire(currentCategory, refCategory){ 
        if (refCategory != null){
            if (currentCategory.libelle == refCategory.libelle) return true
            isASonCategoriePartenaire(currentCategory, refCategory.categorieParente)
        }
        else return false
    }
    
    def getReglePrixToUse(partenaire, produit, quantite, type, isActive) {
        
        def now = new Date()
        def criteria = ReglePrix.createCriteria()
        def reglePrixs = []
        reglePrixs = criteria.list {
            and {
                eq("typeRegle", type)
                eq("active", isActive)
                if(isActive) {
                    le("dateDebut", now) 
                    or {
                        isNull("dateFin")
                        and {
                            isNotNull("dateFin")
                            ge("dateFin", now)
                        }
                    }
                }
                or {
                    eq("operateurComparaison", '')
                    isNull("operateurComparaison")
                    and {
                        isNotNull("operateurComparaison")
                        isNull("quantiteMin")
                        isNotNull("quantiteMax")
                        or {
                            and {
                                eq("operateurComparaison", "Egal à")
                                eq("quantiteMax", quantite)
                            }
                            and {
                                eq("operateurComparaison", "Inférieur à")
                                gt("quantiteMax", quantite)
                            }
                            and {
                                eq("operateurComparaison", "Supérieur à")
                                lt("quantiteMax", quantite)
                            }
                        }
                    }
                    and {
                        isNotNull("operateurComparaison")
                        isNotNull("quantiteMin")
                        isNotNull("quantiteMax")
                        lt("quantiteMin", quantite)
                        gt("quantiteMax", quantite)
                    }
                }
            }
        }
        if (reglePrixs.size() == 0) return null
        
        def regleprixListeProduit = []
        def produitsRegle = []
        def categorieProduitsRegle = []
        def categorieProduitsParam = produit.categorieProduit
        //liste des regleprix à qui produit appartient à leurs produits ou à leurs catégorieProduits
        reglePrixs.each { currentRegle ->
            produitsRegle = currentRegle.produits
            categorieProduitsRegle = currentRegle.categorieProduits
           
            def existOneSon = false
            categorieProduitsRegle.each{ ctgrPrd ->
                existOneSon = isASonCategorieProduit(ctgrPrd, categorieProduitsParam)
                if (existOneSon) {
                    return
                }
            }
            
            if ( ( produitsRegle != null && produitsRegle.size()>0 && produitsRegle*.code.contains(produit.code)) ||
                (categorieProduitsRegle != null && categorieProduitsRegle.size()>0 && categorieProduitsParam != null && existOneSon)){
                regleprixListeProduit.addAll(currentRegle)
            }
        }
                
        def regleprixListePartenaire = []
        def partenairesRegle = []
        def categoriePartenairesRegle = []
        def categoriePartenairesParam = []
        categoriePartenairesParam = partenaire.categoriePartenaires
        //liste des regleprix à qui partenaire appartient à leurs partenaires ou catégoriePartanaires
        reglePrixs.each { currentRegle ->
            def existOneSon = false
            partenairesRegle = currentRegle.partenaires
            categoriePartenairesRegle = currentRegle.categoriePartenaires
            
            categoriePartenairesParam.each { ctgrPrtParam ->
                categoriePartenairesRegle.each { ctgrPrt ->
                    existOneSon = isASonCategoriePartenaire(ctgrPrtParam, ctgrPrt)
                    if (existOneSon) {
                        regleprixListePartenaire.addAll(currentRegle)
                        existOneSon = false
                        return
                    } 
                }
                
            }
            if ( partenairesRegle != null && partenairesRegle*.code.contains(partenaire.code)){ 
                regleprixListePartenaire.addAll(currentRegle)
            }
        }
        
        def regleprixListe = []
        // cas de l'existance des règlePrix communes pour un pair (produit,partenaire) donné 
        if (regleprixListeProduit.size()>0 && regleprixListePartenaire.size()>0){
            ReglePrix.intersect(regleprixListeProduit, regleprixListePartenaire)
            regleprixListe = regleprixListeProduit
        } 
        // cas de l'inexistance d'aucune reglePrix commune pour le pair donné
        // if (regleprixListe.size() < 1 && regleprixListeProduit.size()>0) {
        //    regleprixListeProduit.addAll(regleprixListePartenaire) 
        //    regleprixListe = regleprixListeProduit
        // }
        
        if (regleprixListe.size() < 1) return null
            
        // le resultat visé est la règle la plus prioritaire
        def regleprixSorted = regleprixListe.sort(this)
        
        // plus de traitement à ajouter en cas d'avoir plusieurs règles de même priorité
        return regleprixSorted[0]
    }
    
    def getPrixDeduit2(partenaire, ligneProduit, regleToUse) {
        
        def prixDeduit = 0d
        def regTobeUsed = regleToUse
        if(regTobeUsed == null) {
            switch(ligneProduit.type){
                case "ACHAT" : prixDeduit = ligneProduit.produit.prixAchat 
                break
                case "VENTE" : prixDeduit = ligneProduit.produit.prixVenteStandard
            }
            ligneProduit.prix = prixDeduit
            ligneProduit.remise = 0d
            return prixDeduit
        }
        //Prix de Base pour ligne produit
        def prixDeBaseRegle = regTobeUsed.typePrixdeBase
        switch(prixDeBaseRegle){
            case "Prix d'achat" : prixDeduit = ligneProduit.produit.prixAchat
            break
            case "Prix de vente" : prixDeduit = ligneProduit.produit.prixVenteStandard
            break
            case "Prix de revient" : prixDeduit = ligneProduit.produit.prixRevient
        }
        
        def remiseLigneProd = regTobeUsed.tauxRemise
        def remiseFixeLigneProd = 0d
        
        if (regTobeUsed.valeurFixe != null) {
            remiseFixeLigneProd = regTobeUsed.valeurFixe
        }
        
        ligneProduit.prix = prixDeduit
        if (ligneProduit.prix > 0d)
        ligneProduit.remise = remiseLigneProd - ( (remiseFixeLigneProd * 100)/ ligneProduit.prix)
        else ligneProduit.remise = 0d
        
        if (remiseLigneProd > 0d) {
            prixDeduit = prixDeduit * (1 - remiseLigneProd / 100) + remiseFixeLigneProd
        }
        else { 
            if (remiseLigneProd < 0d) {
                prixDeduit = prixDeduit * (1 + remiseLigneProd / 100) + remiseFixeLigneProd 
            }
            else {
                // remiseLigneProd == 0d
                prixDeduit += remiseFixeLigneProd    
            }
        }
            
        // Arrondissement
        def prixArrondi = arrondirePrixDeduit(prixDeduit, regTobeUsed)
        
        return prixArrondi
    }
    
    def getPrixDeduit1(partenaire, ligneProduit, regleToUse, givenRemise) {
        
        def prixDeduit = 0d
        def regTobeUsed = regleToUse
        if(regTobeUsed == null) {
            switch(ligneProduit.type){
                case "ACHAT" : prixDeduit = ligneProduit.produit.prixAchat 
                break
                case "VENTE" : prixDeduit = ligneProduit.produit.prixVenteStandard
            }
        }
        else {
            //Prix de Base pour ligne produit
            def prixDeBaseRegle = regTobeUsed.typePrixdeBase
            switch(prixDeBaseRegle){
                case "Prix d'achat" : prixDeduit = ligneProduit.produit.prixAchat
                break
                case "Prix de vente" : prixDeduit = ligneProduit.produit.prixVenteStandard
                break
                case "Prix de revient" : prixDeduit = ligneProduit.produit.prixRevient
            }
        }
        ligneProduit.prix = prixDeduit
        ligneProduit.remise = givenRemise
               
        if (givenRemise >= 0d) {
            prixDeduit = prixDeduit * (1 - givenRemise / 100)
        }
        else { 
            prixDeduit = prixDeduit * (1 + givenRemise / 100)
        }  
        
        // Arrondissement
        if(regTobeUsed == null)
              return prixDeduit
        else  return arrondirePrixDeduit(prixDeduit, regTobeUsed)
    }
    
    def arrondirePrixDeduit(prixDeduit, regTobeUsed){
        
        if (regTobeUsed.arrondirApresVirguel) {
            def seuilArrondissLgPrd = regTobeUsed.seuilArrondissement
            def senseArrondissLgPrdRegle = regTobeUsed.senseArrondissement
            
            if (seuilArrondissLgPrd == null || senseArrondissLgPrdRegle == null) {
                return prixDeduit
            }
            
            // prixDeduit = SArd*Q + R 
            // Q = (int)prixDeduit / SArd
            // R = prixDeduit - SArd*Q
            def quotion  = (int)prixDeduit/seuilArrondissLgPrd
            def reste = prixDeduit - seuilArrondissLgPrd*quotion
                
            // si le prix est déjà arrondi
            if (reste == 0d) return prixDeduit
            // Sinon
            def minoration = 0d
            def majoration = 0d
            def rapprochement = 0d
                
            if (reste < seuilArrondissLgPrd){
                minoration = reste
                majoration = seuilArrondissLgPrd - reste
            } else {
                minoration = seuilArrondissLgPrd
                majoration = 2*seuilArrondissLgPrd - reste  //majoration = SArd - (R - SArd) = 2SArd-R
            }
                 
            if (senseArrondissLgPrdRegle.equals("Majoration")) {
                // prixDeduit = prixDeduit + majoration
                // prixDeduit = 12.3 -> Q = 24, R = 0.3
                prixDeduit += majoration
            }
            else {
                if (senseArrondissLgPrdRegle.equals("Minoration")) {                        
                    prixDeduit -= minoration
                } else { 
                    // rapprochement
                    if (minoration < majoration){
                        rapprochement = minoration
                        prixDeduit -= rapprochement
                    }
                    else {
                        rapprochement = majoration
                        prixDeduit += rapprochement
                    }
                }    
            }
        }
        return prixDeduit
    }
    
    def getUsedRegle(partenaire, ligneProduit){
        def getActiveRegles = true
        return getReglePrixToUse(partenaire, ligneProduit.produit, ligneProduit.quantite, ligneProduit.type, getActiveRegles)
    }
    
    def getPrixDeduit(partenaire, ligneProduit) {       
        def regTobeUsed = getUsedRegle(partenaire, ligneProduit)
        /*if (regTobeUsed != null) 
            println 'regTobeUsed : ' + regTobeUsed.typePrixdeBase*/
        return getPrixDeduit2(partenaire, ligneProduit, regTobeUsed)
        //return getPrixDeduit(partenaire, ligneProduit, null)
    }
    
    def calculerPrixDeduit(partenaire, ligneProduit, givenRemise) {
        def regTobeUsed = getUsedRegle(partenaire, ligneProduit)
        return getPrixDeduit1(partenaire, ligneProduit, regTobeUsed, givenRemise)
        //return getPrixDeduit(partenaire, ligneProduit, null, givenRemise)
    }
        
}
