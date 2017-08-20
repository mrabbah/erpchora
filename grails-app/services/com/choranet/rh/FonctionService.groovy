
package com.choranet.rh;

import com.choranet.commun.SuperService

/**
 * FonctionService Service pour la gestion des opérations
 * transactionnelles pour l'objet Fonction
 */
class FonctionService extends SuperService {

        static transactional = true

        def list() throws Exception {
            return super.list(Fonction.class)
        }
}