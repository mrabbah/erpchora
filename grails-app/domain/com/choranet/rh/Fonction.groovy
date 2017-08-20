
package com.choranet.rh

import org.apache.commons.lang.builder.*

/**
 * Fonction Domain Object 
 */
class Fonction {	

    String libelle	
    
    static mapping = { 
        batchSize: 14 
        cache usage : 'nonstrict-read-write'
        sort "libelle"
    }
    static constraints = {
        libelle(blank : false, nullable : false, unique : true)
    }
	
    String toString() {
        return libelle
    }
    
    @Override 
    boolean equals(final Object that) {         
        if(that == null || !(that instanceof Fonction)) {
            return false
        }      
        return that.libelle == libelle            
    }   

    @Override 
    int hashCode() { 
            HashCodeBuilder.reflectionHashCode(this, ["libelle"]) 
    } 
}

