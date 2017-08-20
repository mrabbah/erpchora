
package com.choranet.projet;

import com.choranet.commun.SuperService

/**
 * PhaseService Service pour la gestion des opérations
 * transactionnelles pour l'objet Phase
 */
class PhaseService extends SuperService {

        static transactional = true

        def list() throws Exception {
            return super.list(Phase.class)
        }
}