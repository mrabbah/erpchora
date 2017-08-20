
package com.choranet.gesticom;

import com.choranet.commun.SuperService
/**
 * CategoriePartenaireService Service pour la gestion des opérations
 * transactionnelles pour l'objet CategoriePartenaire
 */
class CategoriePartenaireService extends SuperService {

    static transactional = true

    def list() throws Exception {
        return super.list(CategoriePartenaire.class)
    }
        
    def getDefaultCategorie() {
        return CategoriePartenaire.findByLibelleIlike("Partenaires")
    }
    
    def getCategorieClients() {
        return CategoriePartenaire.findByLibelleIlike("Clients")
    }
    
    def getCategorieFournisseurs() {
        return CategoriePartenaire.findByLibelleIlike("Fournisseurs")
    }
    def getCategorieByLibelle(libelle) {
        return CategoriePartenaire.findByLibelleIlike(libelle)
    }
}