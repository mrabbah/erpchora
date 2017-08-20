
package com.choranet.projet;

import com.choranet.commun.SuperService

/**
 * SoustraitanceService Service pour la gestion des opérations
 * transactionnelles pour l'objet Soustraitance
 */
class SoustraitanceService extends SuperService {

    static transactional = true

    def projetService
    
    def update(Object object) throws Exception {
        super.update(object);
        projetService.mettreAjourChargesReellesProjet(object.projet)
    }

    def save(Object object) throws Exception {
        super.save(object);
        projetService.mettreAjourChargesReellesProjet(object.projet)
    }

    def delete(Object object) throws Exception {
        super.delete(object);
        projetService.mettreAjourChargesReellesProjet(object.projet)
    }
    
    def list() throws Exception {
        return super.list(Soustraitance.class)
    }
}