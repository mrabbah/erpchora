
package com.choranet.gesticom;

import com.choranet.commun.SuperService
import com.choranet.stock.Livraison

/**
 * PartenaireService Service pour la gestion des opérations
 * transactionnelles pour l'objet Partenaire
 */
class PartenaireService extends SuperService {

    static transactional = true

    def list() throws Exception {
        return super.list(Partenaire.class)
    }
        
    def getClients() {
        return Partenaire.findAllByEstClient(true)
    }
        
    def getClientsParticuliers() {
        return Partenaire.findAllByEstClientAndEstParticulier(true, true).sort{it.raisonSociale}
    }
    def getFournisseursParticuliers() {
        return Partenaire.findAllByEstFournisseurAndEstParticulier(true, true)
    }
        
    def getParticuliers() {
        return Partenaire.findAllByEstParticulier(true)
    }
        
    def getFournisseurs() {
        return Partenaire.findAllByEstFournisseur(true).sort{it.raisonSociale}
    }
        
    def getPartenaireParRaisonSociale(raisonSociale){
        return Partenaire.findByRaisonSociale(raisonSociale)
    }
    def getBlackLists(){
        return Partenaire.findAllByEstBlacklist(true)
    }
    
    //    def partenairedelivaison(p)
    //    {
    //     
    //     def c = Partenaire.createCriteria()  
    //     def result = c.list {
    //         
    //            Livraison  {
    //                eq("numExpedition", p.numExpedition)
    //            }
    //            
    //         
    //     }
    //    
    //        return result
    //        
    //    }
    
    def getClientsNonBlacklist(){
        def c = Partenaire.createCriteria()
        def result = c.list {
            eq("estClient", true)
            eq("estBlacklist", false)
        }
        return result
    }
    
    def getNextCode() {
        def numSuivant = Partenaire.count() + 1
        def c = "" + numSuivant
        def diff = 5
        while(diff > 0) {
            c = "0" + c;
            diff--;
        }
        return c
    }
    
    def getPartenairebyLivraison (Livraison livraison)
    { def bondelivraison=new ArrayList<BonLivraison>()
        def resu
        //        println('les partenaaires'+livraison.bonLivraisons)
        bondelivraison=livraison.bonLivraisons
        Iterator it=bondelivraison.iterator()
        while(it.hasNext())
        { 
                
            resu=it.next()
        }
       
       
    
        return livraison.bonLivraisons.bonCommande.partenaire.unique()
    
       
    }
}