

package com.choranet.stock


import org.apache.commons.lang.builder.*

/**
 * ModeLivraison Domain Object 
 */
class ModeLivraison {	
    
    String code	
    	
    String transporteur	
    	
    Boolean paiementALaReception	
       		   
    
    static mapping = { 
        cache usage : 'nonstrict-read-write'
        batchSize: 11 
        sort "code"
    }
    static constraints = {
    
        code(blank : false, nullable : false, unique : true)
    
        transporteur(blank : false, nullable : false)
    
        paiementALaReception()
    
    }
	
    String toString() {
        return (code + " : " + transporteur)
    }
    
    @Override 
    boolean equals(final Object that) {         
        if(that == null || !(that instanceof ModeLivraison)) {
            return false
        }      
        return that.code == code            
    }   

    @Override 
    int hashCode() { 
            HashCodeBuilder.reflectionHashCode(this, ["code"]) 
    } 
}

