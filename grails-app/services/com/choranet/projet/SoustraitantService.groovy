
package com.choranet.projet;

import com.choranet.commun.SuperService

/**
 * SoustraitantService Service pour la gestion des opérations
 * transactionnelles pour l'objet Soustraitant
 */
class SoustraitantService extends SuperService {

        static transactional = true

        def list() throws Exception {
            return super.list(Soustraitant.class)
        }
}