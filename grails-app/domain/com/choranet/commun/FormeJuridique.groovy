

package com.choranet.commun


import org.apache.commons.lang.builder.* 

/**
 * FormeJuridique Domain Object 
 */
class FormeJuridique {	
    
    String libelle	
       		   
    
    static mapping = { 
        libelle index : "formejuridique_libelle"
        batchSize: 8
        cache usage : 'nonstrict-read-write'
        sort libelle:"asc"
    }
    static constraints = {
    
        libelle(blank : false, nullable : false, unique : true)
    
    }
	
    String toString() {
        return libelle
    }
    
    @Override 
    boolean equals(final Object that) {         
        if(that == null || !(that instanceof FormeJuridique)) {
            return false
        }      
        return that.libelle == libelle            
    } 

    @Override 
    int hashCode() { 
            HashCodeBuilder.reflectionHashCode(this, ["libelle"]) 
    } 
}

