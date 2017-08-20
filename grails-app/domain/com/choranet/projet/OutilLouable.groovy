
package com.choranet.projet

import org.apache.commons.lang.builder.*
/**
 * OutilLouable Domain Object 
 */
class OutilLouable {	

    String designation	
    Double prixHeureLocation	
    
    static belongsTo = [loueur : Loueur]
    
    static mapping = { 
        //loueur lazy : false
        batchSize: 20
        sort "designation"
    }
    static constraints = {
        designation(blank : false, nullable : false)
        prixHeureLocation(min : 0d, nullable : false)
        loueur(nullable : false)
    }
	
    String toString() {
        return designation
    }
    
    @Override 
    boolean equals(final Object that) {         
        if(that == null || !(that instanceof OutilLouable)) {
            return false
        }      
        return that.id == id            
    }    
    
    @Override 
    int hashCode() { 
            HashCodeBuilder.reflectionHashCode(this, ["id"]) 
    } 
}

