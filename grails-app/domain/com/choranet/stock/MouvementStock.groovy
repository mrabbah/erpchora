

package com.choranet.stock


import com.choranet.securite.Utilisateur
import org.apache.commons.lang.builder.*
/**
 * MouvementStock Domain Object 
 */
class MouvementStock {	
    
    Date date	
    Double quantite	
    String typeMouvement
    Date date_preremption
    Double prixDeduitMvm
    
    static belongsTo = [empSource : Entrepot , empDestination : Entrepot , produit : Produit, utilisateur : Utilisateur]
    
    static mapping = { 
        //empSource lazy : false
        //empDestination lazy : false
        //produit lazy : false
        batchSize: 14 
        sort date:"desc"
    }
    static constraints = {
    
        date()
        quantite()
        typeMouvement(inList : ["ACHAT", "VENTE", "TRANSFERT", "RETOUR ACHAT", "RETOUR VENTE", "INVENTAIRE", "AFFECTATION CHANTIER", "RETOUR CHANTIER"])
        date_preremption(nullable : true)
        prixDeduitMvm(nullable : false)
        empDestination(nullable : true)
        empSource(nullable : true)
        utilisateur(nullable : false)
    
    }
	
    String toString() {
        return super.toString()
    }
    
    @Override 
    boolean equals(final Object that) {         
        if(that == null || !(that instanceof MouvementStock)) {
            return false
        }      
        return that.id == id            
    }   
    
    @Override 
    int hashCode() { 
            HashCodeBuilder.reflectionHashCode(this, ["id"]) 
    } 
    
}

