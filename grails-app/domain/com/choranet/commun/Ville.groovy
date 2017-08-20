

package com.choranet.commun


import org.apache.commons.lang.builder.* 

/**
 * Ville Domain Object 
 */
class Ville {	
    
    String intitule	
       		   
    static belongsTo = [pays : Pays]
    
    static mapping = { 
        //pays lazy : false
        batchSize: 11
        //        cache usage : 'nonstrict-read-write'
        sort intitule:"asc"
    }
    static constraints = {
    
        intitule(blank : false, nullable : false, unique : true)
        pays(nullable : false)
    
    }
	
    String toString() {
        return intitule
    }
    
    @Override 
    boolean equals(final Object that) {         
        if(that == null || !(that instanceof Ville)) {
            return false
        }      
        return that.intitule == intitule            
    } 
    
    @Override 
    int hashCode() { 
            HashCodeBuilder.reflectionHashCode(this, ["id"]) 
    } 
}

