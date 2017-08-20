

package com.choranet.commun


import org.apache.commons.lang.builder.*

/**
 * Pays Domain Object 
 */
class Pays {	
    
    String intitule	
       		   
    
    static mapping = { 
        batchSize: 11
        cache usage : 'nonstrict-read-write'
        sort intitule:"asc"
    }
    static constraints = {
    
        intitule(blank : false, nullable : false, unique : true)
    
    }
	
    String toString() {
        return intitule
    }
    
    @Override 
    boolean equals(final Object that) {         
        if(that == null || !(that instanceof Pays)) {
            return false
        }      
        return that.intitule == intitule
            //EqualsBuilder.reflectionEquals(this, that, ["intitule"]) 
    } 

    @Override 
    int hashCode() { 
            HashCodeBuilder.reflectionHashCode(this, ["intitule"]) 
    } 
}

