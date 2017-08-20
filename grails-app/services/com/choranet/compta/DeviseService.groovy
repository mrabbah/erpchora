
package com.choranet.compta;

import com.choranet.commun.SuperService
/**
 * DeviseService Service pour la gestion des opérations
 * transactionnelles pour l'objet Devise
 */
class DeviseService extends SuperService {

        static transactional = true

        def list() throws Exception {
            return super.list(Devise.class)
        }
}