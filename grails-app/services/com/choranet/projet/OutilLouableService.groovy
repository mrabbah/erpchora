
package com.choranet.projet;

import com.choranet.commun.SuperService

/**
 * OutilLouableService Service pour la gestion des opérations
 * transactionnelles pour l'objet OutilLouable
 */
class OutilLouableService extends SuperService {

        static transactional = true

        def list() throws Exception {
            return super.list(OutilLouable.class)
        }
}