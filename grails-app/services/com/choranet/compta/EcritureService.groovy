
package com.choranet.compta;

import com.choranet.commun.SuperService
/**
 * EcritureService Service pour la gestion des opérations
 * transactionnelles pour l'objet Ecriture
 */
class EcritureService extends SuperService {

        static transactional = true

        def list() throws Exception {
            return super.list(Ecriture.class)
        }
}