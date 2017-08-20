
package com.choranet.commun;

/**
 * VilleService Service pour la gestion des opérations
 * transactionnelles pour l'objet Ville
 */
class VilleService extends SuperService {

        static transactional = true

        def list() throws Exception {
            return super.list(Ville.class)
        }
}