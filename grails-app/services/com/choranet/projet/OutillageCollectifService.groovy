
package com.choranet.projet;

import com.choranet.commun.SuperService

/**
 * OutillageCollectifService Service pour la gestion des opérations
 * transactionnelles pour l'objet OutillageCollectif
 */
class OutillageCollectifService extends SuperService {

    static transactional = true

    def list() throws Exception {
        return super.list(OutillageCollectif.class)
    }
        
    def getOutillageToujoursUtiliser() {
        return OutillageCollectif.findAllByDateFinUtilisationIsNull()
    }
}