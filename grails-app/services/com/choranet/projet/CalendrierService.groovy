
package com.choranet.projet;

import com.choranet.commun.SuperService

/**
 * CalendrierService Service pour la gestion des opérations
 * transactionnelles pour l'objet Calendrier
 */
class CalendrierService extends SuperService {

        static transactional = true

        def list() throws Exception {
            return super.list(Calendrier.class)
        }
}