
package com.choranet.projet

import org.apache.commons.lang.builder.* 

/**
 * JourFerier Domain Object 
 */
class JourFerier {	

    Date date	
    String intitule	
    Boolean annuel	
    Integer duree
    
    static mapping = { 
        batchSize: 14 
        cache usage : 'nonstrict-read-write'
        sort date:"desc"
    }
    
    static constraints = {
        date(nullable : false)
        intitule(blank : false, nullable : false)
        annuel(nullable : false)
        duree(nullable : false)
    }
	
    String toString() {
        return intitule	
    }
    
    @Override 
    boolean equals(final Object that) {         
        if(that == null || !(that instanceof JourFerier)) {
            return false
        }      
        return that.id == id            
    }    

    @Override 
    int hashCode() { 
            HashCodeBuilder.reflectionHashCode(this, ["id"]) 
    } 
}

