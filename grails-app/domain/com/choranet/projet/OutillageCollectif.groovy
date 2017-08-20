
package com.choranet.projet

import org.apache.commons.lang.builder.*
/**
 * OutillageCollectif Domain Object 
 */
class OutillageCollectif {	
    
    Integer numero	
    String designation	
    Date dateAchat	
    Double prixAchat	
    Date dateMiseEnService	
    Date dateFinUtilisation	
    
    static mapping = { 
        batchSize: 20
        sort "designation"
    }
    static constraints = {
        numero(min : 1, nullable : false)
        designation(blank : false)
        dateAchat(nullable : false)
        prixAchat(min : 0d, nullable : false)
        dateMiseEnService(nullable : true)
        dateFinUtilisation(nullable : true)
    }
	
    String toString() {
        return designation	
    }
    
    @Override 
    boolean equals(final Object that) {         
        if(that == null || !(that instanceof OutillageCollectif)) {
            return false
        }      
        return that.id == id            
    }    
    
    @Override 
    int hashCode() { 
            HashCodeBuilder.reflectionHashCode(this, ["id"]) 
    } 
}

