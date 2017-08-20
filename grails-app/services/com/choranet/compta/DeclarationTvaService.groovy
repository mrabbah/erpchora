
package com.choranet.compta;

import com.choranet.commun.SuperService

/**
 * DeclarationTvaService Service pour la gestion des opérations
 * transactionnelles pour l'objet DeclarationTva
 */
class DeclarationTvaService extends SuperService {

        static transactional = true

        def list() throws Exception {
            return super.list(DeclarationTva.class)
        }
}