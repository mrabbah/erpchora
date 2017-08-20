

package com.choranet.gesticom


import org.apache.commons.lang.builder.* 

/**
 * ModeReglement Domain Object 
 */
class ModeReglement {	
    
    String code	
    	
    String libelle	
       		   
    
    static mapping = { 
        cache usage : 'nonstrict-read-write'
        batchSize: 11
        sort "code"
    }
    static constraints = {
    
        code(blank : false, nullable : false, unique : true)
    
        libelle(blank : false, nullable : false, unique : true)
    
    }
	
    String toString() {
        return (libelle + " : " + code)
    }
    
    @Override 
    boolean equals(final Object that) {         
        if(that == null || !(that instanceof ModeReglement)) {
            return false
        }      
        return that.code == code            
    }    

    @Override 
    int hashCode() { 
            HashCodeBuilder.reflectionHashCode(this, ["code"]) 
    } 
}

