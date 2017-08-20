
package com.choranet.compta;

import com.choranet.commun.SuperService
/**
 * ExerciceService Service pour la gestion des opérations
 * transactionnelles pour l'objet Exercice
 */
class ExerciceService extends SuperService {

        static transactional = true

        def list() throws Exception {
            return super.list(Exercice.class)
        }
}