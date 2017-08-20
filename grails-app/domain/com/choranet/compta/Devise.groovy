
package com.choranet.compta

import org.apache.commons.lang.builder.*
/**
 * Devise Domain Object 
 */
class Devise {	
    
    	
    String libelle	
    Double valeur	
    
    static mapping = { 
        batchSize: 14 
        sort "libelle"
    }
    static constraints = {
        libelle(nullable : false)
        valeur(nullable : false)
    }
	
    String toString() {
        return super.toString()
    }
    
    @Override 
    boolean equals(final Object that) {         
        if(that == null || !(that instanceof Devise)) {
            return false
        }      
        return that.id == id            
    } 
    
    @Override 
    int hashCode() { 
            HashCodeBuilder.reflectionHashCode(this, ["id"]) 
    } 
}

