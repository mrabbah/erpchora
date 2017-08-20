
package com.choranet.gesticom;

import com.choranet.commun.SuperService

/**
 * EcheanceService Service pour la gestion des opérations
 * transactionnelles pour l'objet Echeance
 */
class EcheanceService extends SuperService {

        static transactional = true

        def list() throws Exception {
            return super.list(Echeance.class)
        }
}