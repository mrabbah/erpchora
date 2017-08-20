package com.choranet.securite

import org.apache.commons.lang.builder.*

class Module {
    
    String code
    String intitule
    
    static constraints = {
        code(nullable: false, unique: true)
        intitule(nullable: false, unique: true)
    }
    
    static mapping = {
        //droits lazy : false
        //cache usage : 'read-only'
        sort "intitule"
    }
    
    String toString() {
        return intitule + " : " + code
    }
    
    @Override 
    boolean equals(final Object that) {         
        if(that == null || !(that instanceof Module)) {
            return false
        }      
        return that.code == code            
    }   

    @Override 
    int hashCode() { 
            HashCodeBuilder.reflectionHashCode(this, ["code"]) 
    } 
}
