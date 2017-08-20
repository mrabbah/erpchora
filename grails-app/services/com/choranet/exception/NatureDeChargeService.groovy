
package com.choranet.exception;

import com.choranet.commun.SuperService

/**
 * NatureDeChargeService Service pour la gestion des opérations
 * transactionnelles pour l'objet NatureDeCharge
 */
class NatureDeChargeService extends SuperService {

        static transactional = true

        def list() throws Exception {
            return super.list(NatureDeCharge.class)
        }
}