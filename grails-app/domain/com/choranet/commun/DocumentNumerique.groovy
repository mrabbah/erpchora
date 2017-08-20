package com.choranet.commun

import org.apache.commons.lang.builder.*

class DocumentNumerique {

    String titre
    String type
    
    byte[] donnees
        
    static constraints = {
        donnees(nullable: true)
        titre(nullable: false)
        type(nullable: false)
    }
    
    static mapping = { 
        sort id:"desc"
    }
    
    @Override 
    boolean equals(final Object that) {         
        if(that == null || !(that instanceof DocumentNumerique)) {
            return false
        }      
        return that.id == id            
    } 
    
    @Override 
    int hashCode() { 
            HashCodeBuilder.reflectionHashCode(this, ["id"]) 
    } 
    
    String toString() {
        return super.toString()
    }
}
