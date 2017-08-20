
package com.choranet.commun;


/**
 * FormeJuridiqueService Service pour la gestion des opérations
 * transactionnelles pour l'objet FormeJuridique
 */
class FormeJuridiqueService extends SuperService {

        static transactional = true

        def list() throws Exception {
            return super.list(FormeJuridique.class)
        }
}