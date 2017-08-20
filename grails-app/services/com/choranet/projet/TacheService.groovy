
package com.choranet.projet;

import com.choranet.commun.SuperService

/**
 * TacheService Service pour la gestion des opérations
 * transactionnelles pour l'objet Tache
 */
class TacheService extends SuperService {

        static transactional = true

        def list() throws Exception {
            return super.list(Tache.class)
        }
}