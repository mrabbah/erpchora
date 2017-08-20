
package com.choranet.projet;

import com.choranet.commun.SuperService

/**
 * JourFerierService Service pour la gestion des opérations
 * transactionnelles pour l'objet JourFerier
 */
class JourFerierService extends SuperService {

        static transactional = true

        def list() throws Exception {
            return super.list(JourFerier.class)
        }
}