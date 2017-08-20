

package com.choranet.gesticom


import org.apache.commons.lang.builder.*

/**
 * AgenceBancaire Domain Object 
 */
class AgenceBancaire {	
    
    String nom	
    	
    String adresse	
    	
    String tel	
       		   
    
    static mapping = { 
        batchSize: 10
        cache usage : 'nonstrict-read-write'
        sort "nom"
    }
    static constraints = {
    
        nom(blank : false, nullable : false, unique : true)
    
        adresse(nullable : true)
    
        tel(nullable : true)
    
    }
	
    String toString() {
        return nom
    }
    
    @Override 
    boolean equals(final Object that) {         
        if(that == null || !(that instanceof AgenceBancaire)) {
            return false
        }      
        return that.nom == nom            
    } 

    @Override 
    int hashCode() { 
            HashCodeBuilder.reflectionHashCode(this, ["nom"]) 
    } 
}

