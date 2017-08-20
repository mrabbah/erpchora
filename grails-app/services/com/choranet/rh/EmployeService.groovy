
package com.choranet.rh;

import com.choranet.commun.SuperService

/**
 * EmployeService Service pour la gestion des opérations
 * transactionnelles pour l'objet Employe
 */
class EmployeService extends SuperService {

        static transactional = true

        def list() throws Exception {
            return super.list(Employe.class)
        }
}