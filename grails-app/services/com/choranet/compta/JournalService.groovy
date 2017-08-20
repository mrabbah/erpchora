
package com.choranet.compta;

import com.choranet.commun.SuperService
/**
 * JournalService Service pour la gestion des opérations
 * transactionnelles pour l'objet Journal
 */
class JournalService extends SuperService {

        static transactional = true

        def list() throws Exception {
            return super.list(Journal.class)
        }
}