
package com.choranet.gesticom;

import com.choranet.commun.SuperService

/**
 * AgenceBancaireService Service pour la gestion des opérations
 * transactionnelles pour l'objet AgenceBancaire
 */
class AgenceBancaireService extends SuperService {

        static transactional = true

        def list() throws Exception {
            return super.list(AgenceBancaire.class)
        }
}