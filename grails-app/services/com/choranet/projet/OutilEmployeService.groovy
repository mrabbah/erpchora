
package com.choranet.projet;

import com.choranet.commun.SuperService

/**
 * OutilEmployeService Service pour la gestion des opérations
 * transactionnelles pour l'objet OutilEmploye
 */
class OutilEmployeService extends SuperService {

        static transactional = true

        def list() throws Exception {
            return super.list(OutilEmploye.class)
        }
}