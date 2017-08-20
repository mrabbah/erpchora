
package com.choranet.projet

import com.choranet.stock.Produit;
import com.choranet.gesticom.BonLivraison;
import com.choranet.stock.Entrepot;
import org.apache.commons.lang.builder.*
/**
 * AffectationProduit Domain Object 
 */
class AffectationProduit {	
    
    Date date	
    Integer quantite	
    Double prixUnitaire
    Double total
    
    static belongsTo = [bonLivraison : BonLivraison , projet : Projet , produit : Produit/* , entrepot : Entrepot */]
    
    static mapping = { 
        total formula : "quantite * prix_unitaire"
        //bonLivraison lazy : false
        //projet lazy : false
        //produit lazy : false
        //entrepot lazy : false
        batchSize: 14 
        sort date:"desc"
    }
    static constraints = {
        date(nullable : false)
        quantite(min : 0, nullable : false)
        prixUnitaire(min : 0d, nullable : false, scale : 2)
        bonLivraison(nullable : true)
        projet(nullable : false)
        produit(nullable : false)
        //entrepot(nullable : true)
    }
	
    String toString() {
        return super.toString()
    }
    
    @Override 
    boolean equals(final Object that) {         
        if(that == null || !(that instanceof AffectationProduit)) {
            return false
        }      
        return that.id == id            
    }  
    
    @Override 
    int hashCode() { 
            HashCodeBuilder.reflectionHashCode(this, ["id"]) 
    } 
}

