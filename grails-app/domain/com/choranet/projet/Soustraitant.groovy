
package com.choranet.projet

import org.apache.commons.lang.builder.*
/**
 * Soustraitant Domain Object 
 */
class Soustraitant {	

    Integer numero	
    String nom	
    String adresse	
    String codePostal	
    String ville	
    String telephone	
    String fax	
    String email	
    
    static mapping = { 
        batchSize: 14 
        sort "nom"
    }
    static constraints = {
        numero(min : 1, nullable : false)
        nom(nullable : false)
        adresse(nullable : true)
        codePostal(nullable : true)
        ville(nullable : true)
        telephone(nullable : false)
        fax(nullable : true)
        email(email : true, nullable : true)
    }
	
    String toString() {
        return nom
    }
    
    @Override 
    boolean equals(final Object that) {         
        if(that == null || !(that instanceof Soustraitant)) {
            return false
        }      
        return that.id == id            
    }   
    
    @Override 
    int hashCode() { 
            HashCodeBuilder.reflectionHashCode(this, ["id"]) 
    } 
}

