
package com.choranet.gesticom;

import com.choranet.commun.SuperService
import com.choranet.projet.AffectationProduitDTO;

/**
 * LigneProduitService Service pour la gestion des opérations
 * transactionnelles pour l'objet LigneProduit
 */
class LigneProduitService extends SuperService {

    static transactional = true

    def list() throws Exception {
        return super.list(LigneProduit.class)
    }        
        
    def getProduitsQuantitesBonCommande(BonCommande bc) {
        def resultat = []
        if(bc != null) {
            def criteria = LigneProduit.createCriteria()
            def ids = bc*.ligneProduits*.id
            def idds = []
            ids.each {
                idds.addAll(it)
            }
            if(idds.size() > 0) {
                def result = criteria.list {
                    "in"("id", idds) 
                    projections {
                        groupProperty "produit.id" , "id"
                        property "produit", "produit"
                        sum "quantite", "quantite"
                        sum "total", "total"
                    }
                    order("produit.id", "desc")
                }
                result.each {
                    AffectationProduitDTO ap = new AffectationProduitDTO()
                    ap.setProduit(it[1]);
                    ap.setQuantite(it[2]);
                    ap.setTotal(it[3]);
                    resultat.add(ap);
                }
            } 
        }
        return resultat
    }
}