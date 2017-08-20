
package com.choranet.compta;

import com.choranet.commun.SuperService

/**
 * RegimeDeclarationTvaService Service pour la gestion des opérations
 * transactionnelles pour l'objet RegimeDeclarationTva
 */
class RegimeDeclarationTvaService extends SuperService {

        static transactional = true

        def list() throws Exception {
            return super.list(RegimeDeclarationTva.class)
        }
}