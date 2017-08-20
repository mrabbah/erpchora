
package com.choranet.stock;

import com.choranet.commun.SuperService

/**
 * NatureService Service pour la gestion des opérations
 * transactionnelles pour l'objet Nature
 */
class NatureService extends SuperService {

        static transactional = true

        def list() throws Exception {
            return super.list(Nature.class)
        }
}