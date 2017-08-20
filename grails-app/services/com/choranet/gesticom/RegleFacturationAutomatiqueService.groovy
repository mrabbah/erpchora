
package com.choranet.gesticom;

import com.choranet.commun.SuperService

/**
 * RegleFacturationAutomatiqueService Service pour la gestion des opérations
 * transactionnelles pour l'objet RegleFacturationAutomatique
 */
class RegleFacturationAutomatiqueService extends SuperService {

        static transactional = true

        def list() throws Exception {
            return super.list(RegleFacturationAutomatique.class)
        }
}