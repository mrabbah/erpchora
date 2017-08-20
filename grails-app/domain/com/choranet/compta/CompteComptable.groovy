
package com.choranet.compta

import org.apache.commons.lang.builder.*

/**
 * CompteComptable Domain Object 
 */
class CompteComptable {	

    	
    String identifiant	
    String nom	
    String code	
    String type	
    
    static belongsTo = [parent : CompteComptable]
    
    static mapping = { 
        parent lazy : false
        batchSize: 14 
        sort code:"asc"
    }
    static constraints = {
        identifiant(nullable : false)
        parent(nullable : true)
        nom(nullable : false)
        code(nullable : false)
        type(nullable : false)
    }
	
    String toString() {
        return super.toString()
    }
    
    @Override 
    boolean equals(final Object that) {         
        if(that == null || !(that instanceof CompteComptable)) {
            return false
        }      
        return that.id == id            
    } 
    
    @Override 
    int hashCode() { 
            HashCodeBuilder.reflectionHashCode(this, ["id"]) 
    } 
}

