package com.choranet.securite

import org.apache.commons.lang.builder.* 

/**
 * Request Map domain class.
 */
class DroitUtilisateur {

    String droit
    String description
    DroitUtilisateur parent

    static constraints = {
        droit(blank: false, unique: true)
        description(nullable : true)
        parent(nullable: true)
    }
        
    static mapping = {
        //parent lazy : false
        cache usage : 'nonstrict-read-write'
    }
    
    String toString() {
        return description
    }
    
    @Override 
    boolean equals(final Object that) {         
        if(that == null || !(that instanceof DroitUtilisateur)) {
            return false
        }      
        return that.droit == droit            
    }   

    @Override 
    int hashCode() { 
            HashCodeBuilder.reflectionHashCode(this, ["droit"]) 
    } 
}
