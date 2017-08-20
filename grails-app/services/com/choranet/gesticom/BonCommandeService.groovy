
package com.choranet.gesticom;

import org.springframework.web.context.request.RequestContextHolder
import com.choranet.commun.SuperService
/**
 * BonCommandeService Service pour la gestion des opérations
 * transactionnelles pour l'objet BonCommande
 */
class BonCommandeService extends SuperService {

    static transactional = true

    def paternCompteurService
    
    def ligneProduitService
    
    def list() throws Exception {
        return super.list(BonCommande.class)
    }
        
    def save(objet) throws Exception {
        if(objet.bonPret) {
            objet.numBC = paternCompteurService.getProchainNumBpEtIncrementer()
        } else {
            objet.numBC = paternCompteurService.getProchainNumBcEtIncrementer()
        }  
       def session = RequestContextHolder.currentRequestAttributes().getSession()
      objet.utilisateur = session.utilisateur
        super.save(objet)
    }
        
    def getSequenceSuivante(objet) {
        def resultat = 0;
        for(LigneProduit lp : objet.ligneProduits) {
            if(lp.sequenceLigne > resultat) {
                resultat = lp.sequenceLigne;
            }   
        }
        resultat++;
        return resultat;
    }
    
    def getBonCommandeNonLivree(type) {
        def c = BonCommande.createCriteria()
        def result = c.list {
            eq("type", type)
            eq("livree", false)
        }
        return result.sort{it.numBC}.reverse()
    }
    
    def getBonCommandePartenaireNonLivree(type,p) {
        def c = BonCommande.createCriteria()
        def result = c.list {
            eq("type", type)
            eq("livree", false)
            partenaire  {
                eq("code", p.code)
            }
        }
        return result.sort{it.numBC}.reverse()
    }
    
    def getBonsCommande(type) {
        def c = BonCommande.createCriteria()
        def result = c.list {
            eq("type", type)
            eq("bonPret", false)
        }
        return result
    }
    def getBonsPret(type) {
        def c = BonCommande.createCriteria()
        def result = c.list {
            eq("type", type)
            eq("bonPret", true)
        }
        return result
    }
    
    def getBonCommandeNonRetournee(type) {
        def c = BonCommande.createCriteria()
        def result = c.list {
            eq("type", type)
            eq("retournee", false)
        }
        return result.sort{it.numBC}.reverse()
    }
    
    def getBonCommandeBetwen(type,date1,date2) {
        def criteria = BonCommande.createCriteria()
        def result = criteria.list {
            between("date", date1, date2)
            eq("type",type)
        }
        return result
    }
    
    def getBonCommandeByPartenaireBetwen(type,p,date1,date2) {
        def criteria = BonCommande.createCriteria()
        def result = criteria.list {
            between("date", date1, date2)
            eq("type",type)
            partenaire  {
                eq("code", p.code)
            }
        }
        return result
    }
     
    def getBonCommandesNonPayePartenaire (p) {
        def c = BonCommande.createCriteria()
        def r = c.list {
            eq("paye", false)
            partenaire {
                eq("id", p.id)
            }
        }
        return r;
    }
    
    def getBonCommandesNonPayePartenaire1(p, t) {
        def c = BonCommande.createCriteria()
        def r = c.list {
            eq("paye", false)
            partenaire {
                eq("id", p.id)
            }
            if(t) {
                eq("type", t)
            }
        }
        return r;
    }
    
    def getBonCommandeByPaiement(paiements,String type = null){
        def ids = paiements.bonCommande*.id
        def idds = []
        ids.each {
            idds.addAll(it)
        }
        
        def c = BonCommande.createCriteria()
        def r = c.list {
              "in"("id", idds)
            if(type) {
                eq("type", type)
            }
        }
        return r;  
    }
    
    //    def getSumBonCommandeByPaiement(paiements,String type = null){
    //        def ids = paiements.bonCommande*.id
    //        def idds = []
    //        ids.each {
    //            idds.addAll(it)
    //        }
    //        
    //        def c = BonCommande.createCriteria()
    //        def r = c.list {
    //              "in"("id", idds)
    //            if(type) {
    //                eq("type", type)
    //            }
    //
    //        }
    //        def trans_totalttc = 0d;
    //        println "++++ calc ttc"
    //        if(r && r.size()>0){
    //            for(LigneProduit lp : r*.ligneProduits) {
    //                trans_totalttc += lp.trans_sousTotal * (1 + lp.produit.regimeTVA.taux / 100) ;   
    //            }
    //        }
    //        println "++++ ok calc ttc"
    //        if(trans_totalttc == null) {
    //            trans_totalttc = 0;
    //        }
    //        return trans_totalttc
    //    }
}
