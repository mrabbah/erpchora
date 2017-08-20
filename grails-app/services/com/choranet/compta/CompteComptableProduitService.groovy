
package com.choranet.compta;

import com.choranet.commun.SuperService
/**
 * CompteComptableProduitService Service pour la gestion des opérations
 * transactionnelles pour l'objet CompteComptableProduit
 */
class CompteComptableProduitService extends SuperService {

        static transactional = true

        def list() throws Exception {
            return super.list(CompteComptableProduit.class)
        }
}