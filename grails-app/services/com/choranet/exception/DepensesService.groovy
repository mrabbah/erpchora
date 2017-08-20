
package com.choranet.exception;

import com.choranet.commun.SuperService

/**
 * DepensesService Service pour la gestion des opérations
 * transactionnelles pour l'objet Depenses
 */
class DepensesService extends SuperService {

        static transactional = true

        def list() throws Exception {
            return super.list(Depenses.class)
        }
}