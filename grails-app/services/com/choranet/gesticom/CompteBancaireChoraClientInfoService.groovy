
package com.choranet.gesticom;

import com.choranet.commun.SuperService

/**
 * CompteBancaireService Service pour la gestion des opérations
 * transactionnelles pour l'objet CompteBancaire
 */
class CompteBancaireChoraClientInfoService extends SuperService {

        static transactional = true

        def list() throws Exception {
            return super.list(CompteBancaireChoraClientInfo.class)
        }
}