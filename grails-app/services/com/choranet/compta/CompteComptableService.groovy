
package com.choranet.compta;

import com.choranet.commun.SuperService
/**
 * CompteComptableService Service pour la gestion des opérations
 * transactionnelles pour l'objet CompteComptable
 */
class CompteComptableService extends SuperService {

        static transactional = true

        def list() throws Exception {
            return super.list(CompteComptable.class)
        }
}