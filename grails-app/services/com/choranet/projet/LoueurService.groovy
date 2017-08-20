
package com.choranet.projet;

import com.choranet.commun.SuperService

/**
 * LoueurService Service pour la gestion des opérations
 * transactionnelles pour l'objet Loueur
 */
class LoueurService extends SuperService {

        static transactional = true

        def list() throws Exception {
            return super.list(Loueur.class)
        }
}