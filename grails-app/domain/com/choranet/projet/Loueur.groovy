
package com.choranet.projet

import org.apache.commons.lang.builder.*
/**
 * Loueur Domain Object 
 */
class Loueur {	
    
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
        telephone(nullable : true)
        fax(nullable : true)
        email(email : true, nullable : true)
    }
	
    String toString() {
        return nom
    }
    
    @Override 
    boolean equals(final Object that) {         
        if(that == null || !(that instanceof Loueur)) {
            return false
        }      
        return that.id == id            
    }   
    
    @Override 
    int hashCode() { 
            HashCodeBuilder.reflectionHashCode(this, ["id"]) 
    } 
}

