
package com.choranet.projet

import org.apache.commons.lang.builder.*
/**
 * Soustraitance Domain Object 
 */
class Soustraitance {	
	
    Date date	
    String nom	
    Double cout	
    
    static belongsTo = [soustraitant : Soustraitant , projet : Projet]
    
    static mapping = { 
        //soustraitant lazy : false
        //projet lazy : false
        batchSize: 14 
        sort date:"desc"
    }
    static constraints = {
        date(nullable : false)
        nom(blank : false, nullable : false)
        cout(min : 0d, nullable : false)
        soustraitant(nullable : false)
        projet(nullable : false)
    }
	
    String toString() {
        return nom
    }
    
    @Override 
    boolean equals(final Object that) {         
        if(that == null || !(that instanceof Soustraitance)) {
            return false
        }      
        return that.id == id            
    }  
    
    @Override 
    int hashCode() { 
            HashCodeBuilder.reflectionHashCode(this, ["id"]) 
    } 
}

