

package com.choranet.gesticom


import org.apache.commons.lang.builder.* 

/**
 * ModeRelancePaiement Domain Object 
 */
class ModeRelancePaiement {	
    
    String code	
    	
    Integer frequence	
    	
    String libelle	
    	
    String corpsMessage	
       		   
    
    static mapping = { 
        cache usage : 'nonstrict-read-write'
        batchSize: 11 
        sort "code"
    }
    static constraints = {
    
        code(blank : false, nullable : false, unique : true)
    
        frequence()
    
        libelle(blank : false, nullable : false, unique : true)
    
        corpsMessage(nullable : false)
    
    }
	
    String toString() {
        return (code + " : " + libelle)
    }
    
    @Override 
    boolean equals(final Object that) {         
        if(that == null || !(that instanceof ModeRelancePaiement)) {
            return false
        }      
        return that.code == code            
    }    

    @Override 
    int hashCode() { 
            HashCodeBuilder.reflectionHashCode(this, ["code"]) 
    } 
}

