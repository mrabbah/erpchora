

package com.choranet.gesticom


import org.apache.commons.lang.builder.* 

/**
 * Echeance Domain Object 
 */
class Echeance {	
    
    Integer code	
    	
    String libelle	
    	
    Integer periodicite	
    	
    String typeDeclanchement	
       		   
    
    static mapping = { 
        cache usage : 'nonstrict-read-write'
        batchSize: 11 
        sort "code"
    }
    static constraints = {
    
        code(nullable : false, unique : true)
    
        libelle(nullable : false, unique : true)
    
        periodicite(min : 0)
    
        typeDeclanchement(inList:["DATE_LIVRAISON", "DATE_FACTURATION"])
    
    }
	
    String toString() {
        return (code + " : " + libelle)
    }
    
    @Override 
    boolean equals(final Object that) {         
        if(that == null || !(that instanceof Echeance)) {
            return false
        }      
        return that.code == code            
    }     

    @Override 
    int hashCode() { 
            HashCodeBuilder.reflectionHashCode(this, ["code"]) 
    } 
}

