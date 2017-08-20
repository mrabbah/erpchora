
package com.choranet.commun;

/**
 * PaysService Service pour la gestion des opérations
 * transactionnelles pour l'objet Pays
 */
class PaysService extends SuperService {

        static transactional = true

        def list() throws Exception {
            return super.list(Pays.class)
        }
}