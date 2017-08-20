
package com.choranet.projet

import org.apache.commons.lang.builder.*
/**
 * LocationOutil Domain Object 
 */
class LocationOutil {	

    Date date	
    Integer dureeLocation	
    Double prixHeureLocation	
    Double prixForfait
    Double total
    
    static belongsTo = [loueur : Loueur , projet : Projet , outilLouable : OutilLouable]
    
    static mapping = { 
        total formula : "duree_location * prix_heure_location + prix_forfait"
        //loueur lazy : false
        //projet lazy : false
        //outilLouable lazy : false
        batchSize: 14 
        sort date:"desc"
    }
    static constraints = {
        date(nullable : false)
        dureeLocation(min : 0, nullable : false)
        prixHeureLocation(min : 0d)
        prixForfait(min : 0d)
        loueur(nullable : false)
        projet(nullable : false)
        outilLouable(nullable : false)
    }
	
    String toString() {
        return super.toString()
    }
    
    @Override 
    boolean equals(final Object that) {         
        if(that == null || !(that instanceof LocationOutil)) {
            return false
        }      
        return that.id == id            
    }   
    
    @Override 
    int hashCode() { 
            HashCodeBuilder.reflectionHashCode(this, ["id"]) 
    } 
}

