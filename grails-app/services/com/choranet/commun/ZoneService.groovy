
package com.choranet.commun;

/**
 * ZoneService Service pour la gestion des opérations
 * transactionnelles pour l'objet Zone
 */
class ZoneService extends SuperService {

        static transactional = true

        def list() throws Exception {
            return super.list(Zone.class)
        }
}