

package com.choranet.commun


import org.apache.commons.lang.builder.*

/**
 * Pays Domain Object 
 */
class Zone {	
    
    String code
    String intitule	
       		   
    
    static mapping = { 
        batchSize: 11
        //cache usage : 'nonstrict-read-write'
        sort intitule:"asc"
    }
    static constraints = {
    
        code(blank : false, nullable : false, unique : true)
        intitule(blank : false, nullable : false, unique : true)
    
    }
	
    String toString() {
        return intitule
    }
    
    @Override 
    boolean equals(final Object that) {         
        if(that == null || !(that instanceof Zone)) {
            return false
        }      
        return that.code == code
            //EqualsBuilder.reflectionEquals(this, that, ["intitule"]) 
    } 

    @Override 
    int hashCode() { 
            HashCodeBuilder.reflectionHashCode(this, ["code"]) 
    } 
}

