
package com.choranet.gesticom;

import com.choranet.commun.SuperService

/**
 * FactureService Service pour la gestion des opérations
 * transactionnelles pour l'objet Facture
 */
class FactureService extends SuperService {

    def bonCommandeService
    def paternCompteurService
    
    static transactional = true

    def list() throws Exception {
        return super.list(Facture.class)
    }
    
    def update(Object object) throws Exception {
        def paiements = object.paiements
        object = super.update(object)
        //updateEtatFacturationBonCommande(paiements)
        return object
    }
    
    def save(Object object) throws Exception {
        def paiements = object.paiements
        if(object.estAvoir) {
            object.numeroFacture = paternCompteurService.getProchainNumAvoirEtIncrementer()   
        } else {
            object.numeroFacture = paternCompteurService.getProchainNumFactureEtIncrementer()   
        }  
        object = super.save(object)
        //updateEtatFacturationBonCommande(paiements)
        return object
    }
    
    def delete(Object object) throws Exception {
        def paiements = object.paiements
        super.delete(object)
        //updateEtatFacturationBonCommande(paiements)
    }
    
    def getFacturesBonsCommande(bonsCommande) {
        def nums = bonsCommande*.numBC
        if(nums.size() > 0) {
            def c = Facture.createCriteria()
            def fs = c.list {
                bonCommande {
                "in"("numBC", nums)
                }
            }
            return fs
        } else {
            return []
        }
    }
    
//    def getFraisFacture(numeroFacture) {
//        def criteria = Facture.createCriteria()
//        def result = criteria.get {
//            facture {
//                eq("numeroFacture",numeroFacture)
//            }
//            projections {
//                sum("livraisons.frais")
//            }
//        }
//        if(result == null) {
//            result = 0;
//        }
//        return result
//    }
    
//    def genererFacture(partenaire,bonLivraisons,nombreblregroupe){
//        def listFactures = []
//            
//        def dateFacture = new Date()
//        def cpt = 1 
//        def facturegeneree = new Facture('type' : 'VENTE','reference' :null, 'date' : dateFacture)
//        facturegeneree.partenaire = partenaire
//        if (nombreblregroupe == - 1)  {
//            for(bl in bonLivraisons) {
//                facturegeneree.addToBonLivraisons(bl)   
//            }
//            listFactures.add(facturegeneree)
//            save(facturegeneree) 
//            
//        }
//        else
//        {
//            for(bl in bonLivraisons) {
//                if (cpt <= nombreblregroupe){
//
//                    facturegeneree.addToBonLivraisons(bl)
//                    cpt = cpt +1 
//                }
//                else 
//                {
//                    cpt= 1
//                    facturegeneree = new Facture('type' : 'VENTE','reference' :null,'date' : dateFacture)
//                    facturegeneree.partenaire = partenaire
//                    facturegeneree.addToBonLivraisons(bl)
//                    listFactures.add(facturegeneree)
//                    cpt = cpt + 1 
//                }
//                save(facturegeneree)
//            }
//        }
//         
//        return listFactures
//    }
//    
//    def getFraisTransportFacture(facture) {
//        def resultat = 0d
//        if(facture.livraisons) {
//            for(livraison in facture.livraisons) {
//                resultat += livraison.frais
//            }
//        }
//        return resultat;
//    }
}