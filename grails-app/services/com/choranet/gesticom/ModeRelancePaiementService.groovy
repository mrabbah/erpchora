
package com.choranet.gesticom;

import com.choranet.commun.SuperService

/**
 * ModeRelancePaiementService Service pour la gestion des opérations
 * transactionnelles pour l'objet ModeRelancePaiement
 */
class ModeRelancePaiementService extends SuperService {

        static transactional = true

        def list() throws Exception {
            return super.list(ModeRelancePaiement.class)
        }
}