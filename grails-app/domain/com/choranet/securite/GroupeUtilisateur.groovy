package com.choranet.securite

import org.apache.commons.lang.builder.* 

/**
 * Authority domain class.
 */
class GroupeUtilisateur {

    static hasMany = [droits: DroitUtilisateur]
    
    /** description */
    String description
   
    String intitule

    static constraints = {
        intitule(blank: false, unique: true)
        description()
        droits(nullable : true)
        
    }
    
    static mapping = {
        //droits lazy : false
        cache usage : 'nonstrict-read-write'
        sort "intitule"
    }
        
    String toString() {
        return intitule
    }
    
    @Override 
    boolean equals(final Object that) {         
        if(that == null || !(that instanceof GroupeUtilisateur)) {
            return false
        }      
        return that.intitule == intitule            
    }   

    @Override 
    int hashCode() { 
            HashCodeBuilder.reflectionHashCode(this, ["intitule"]) 
    } 
}
