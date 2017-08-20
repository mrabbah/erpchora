
package com.choranet.gesticom;

import com.choranet.commun.SuperService

/**
 * CompteBancaireService Service pour la gestion des opérations
 * transactionnelles pour l'objet CompteBancaire
 */
class CompteBancaireService extends SuperService {

    static transactional = true

    def list() throws Exception {
        return super.list(CompteBancaire.class)
    }
    
    def getCompteBancairePartenaire(partner) {
        def c = CompteBancaire.createCriteria()
        def cbs = c.list {
            partenaire {
                eq('code', partner.code)
            }
        } 
        return cbs
    }
}