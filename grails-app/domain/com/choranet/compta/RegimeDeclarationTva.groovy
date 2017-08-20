

package com.choranet.compta


import org.apache.commons.lang.builder.*

/**
 * RegimeDeclarationTva Domain Object 
 */
class RegimeDeclarationTva {	
    
    String libelle	
    	
    String periodicite	
    	
    String faitGenerateur	
       		   
    
    static mapping = { 
        cache usage : 'nonstrict-read-write'
        batchSize: 14 
        sort "libelle"
    }
    static constraints = {
    
        libelle(blank : false, nullable : false)
    
        periodicite(inList:["Mensuelle", "Trimestrielle"])
    
        faitGenerateur(inList:["Facturation", "Encaissement"])
    
    }
	
    String toString() {
        return libelle
    }
    
    @Override 
    boolean equals(final Object that) {         
        if(that == null || !(that instanceof RegimeDeclarationTva)) {
            return false
        }      
        return that.id == id            
    } 

    @Override 
    int hashCode() { 
            HashCodeBuilder.reflectionHashCode(this, ["id"]) 
    } 
}
