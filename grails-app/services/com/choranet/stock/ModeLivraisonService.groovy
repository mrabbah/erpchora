
package com.choranet.stock;

import com.choranet.commun.SuperService

/**
 * ModeLivraisonService Service pour la gestion des opérations
 * transactionnelles pour l'objet ModeLivraison
 */
class ModeLivraisonService extends SuperService {

        static transactional = true

        def list() throws Exception {
            return super.list(ModeLivraison.class)
        }
}