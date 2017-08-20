
package com.choranet.exception;

import com.choranet.commun.SuperService

/**
 * PaiementJblService Service pour la gestion des opérations
 * transactionnelles pour l'objet PaiementJbl
 */
class PaiementJblService extends SuperService {

        static transactional = true

        def list() throws Exception {
            return super.list(PaiementJbl.class)
        }
}