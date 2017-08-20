
package com.choranet.projet;

import com.choranet.commun.SuperService

/**
 * NoteService Service pour la gestion des opérations
 * transactionnelles pour l'objet Note
 */
class NoteService extends SuperService {

        static transactional = true

        def list() throws Exception {
            return super.list(Note.class)
        }
}