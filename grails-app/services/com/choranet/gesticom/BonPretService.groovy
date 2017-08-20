
package com.choranet.gesticom;

import com.choranet.commun.SuperService
/**
 * BonPretService Service pour la gestion des opérations
 * transactionnelles pour l'objet BonPret
 */
class BonPretService extends SuperService {

    static transactional = true

    def paternCompteurService
    
    def ligneProduitService
    
    def list() throws Exception {
        return super.list(BonPret.class)
    }
        
    def save(objet) throws Exception {
        objet.numBP = paternCompteurService.getProchainNumBpEtIncrementer()
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
        
}