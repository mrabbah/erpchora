

package com.choranet.compta


import org.apache.commons.lang.builder.*

/**
 * RegimeTVA Domain Object 
 */
class RegimeTVA {	
    
    String libelle	
    	
    Integer taux	
       		   
    
    static mapping = { 
        batchSize: 10 
        cache usage : 'nonstrict-read-write'
        sort taux:"desc"
    }
    static constraints = {
    
        libelle(blank : false, nullable : false, unique : true)
    
        taux(nullable : false, unique : true)
    
    }
	
    String toString() {
        return libelle
    }
    
    @Override 
    boolean equals(final Object that) {         
        if(that == null || !(that instanceof RegimeTVA)) {
            return false
        }      
        return that.libelle == libelle            
    } 

    @Override 
    int hashCode() { 
            HashCodeBuilder.reflectionHashCode(this, ["libelle"]) 
    } 
}

