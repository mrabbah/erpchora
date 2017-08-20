
package com.choranet.gesticom;

import com.choranet.commun.SuperService

/**
 * ModeReglementService Service pour la gestion des opérations
 * transactionnelles pour l'objet ModeReglement
 */
class ModeReglementService extends SuperService {

        static transactional = true

        def list() throws Exception {
            return super.list(ModeReglement.class)
        }
}