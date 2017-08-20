
package com.choranet.stock;

import com.choranet.commun.SuperService

/**
 * UniteMesureService Service pour la gestion des opérations
 * transactionnelles pour l'objet UniteMesure
 */
class UniteMesureService extends SuperService {

    static transactional = true

    def list() throws Exception {
        return super.list(UniteMesure.class)
    }
    
    def getUnitePardefaut() {
        return UniteMesure.findByLibelle("Unité(s)")
    }
    
    def getUniteMesureByLibelle(libelle) {
        return UniteMesure.findByLibelleIlike(libelle)
    }
}