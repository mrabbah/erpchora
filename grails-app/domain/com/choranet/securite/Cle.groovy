
package com.choranet.securite

import org.apache.commons.lang.builder.*
/**
 * Cle d activation
 **/

class Cle {

    String cleProduit
    
    Date dateActivation
    
    String dateFinActivation
    
    static constraints = {
        cleProduit(nullable : false, blank: false, unique: true)
        dateActivation(nullable : false, unique: true)
        dateFinActivation(nullable : false, blank: false)
    }
    
    static mapping = {
        batchSize: 10
    }
    
    @Override 
    boolean equals(final Object that) {         
        if(that == null || !(that instanceof Cle)) {
            return false
        }      
        return that.cleProduit == cleProduit            
    }   
    
    @Override 
    int hashCode() { 
            HashCodeBuilder.reflectionHashCode(this, ["id"]) 
    } 
    
    String toString() {
        return super.toString()
    }
}
