

package com.choranet.exception


import org.apache.commons.lang.builder.*

/**
 * NatureDeCharge Domain Object 
 */
class NatureDeCharge {	
    
    String libelle	
    	
    Double chargeBasic	
    	
    String type	
       		   
    
    static mapping = { 
        cache usage : 'nonstrict-read-write'
        batchSize: 14 
    }
    static constraints = {
    
        libelle(blank : false, nullable : false, unique : true)
    
        chargeBasic(min : 0d)
    
        type(blank : false, nullable : false, inList : ["FIXE", "VARIABLE"])
    
    }
	
    String toString() {
        return (libelle + " : " + type)
    }
    
    @Override 
    boolean equals(final Object that) {         
        if(that == null || !(that instanceof NatureDeCharge)) {
            return false
        }      
        return that.id == id            
    } 

    @Override 
    int hashCode() { 
            HashCodeBuilder.reflectionHashCode(this, ["libelle"]) 
    } 
    
}

