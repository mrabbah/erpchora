
package com.choranet.projet

import com.choranet.rh.Employe
import org.apache.commons.lang.builder.*
/**
 * OutilEmploye Domain Object 
 */
class OutilEmploye {	

    Integer numero	
    String nom	
    Double coutHoraire	
    
    static belongsTo = [employe : Employe]
    
    static mapping = { 
        //employe lazy : false
        batchSize: 20 
        sort "nom"
    }
    static constraints = {
        numero(min : 1, nullable : false)
        nom(blank : false, nullable : false)
        coutHoraire(min : 0d, nullable : false)
        employe(nullable : false)
    }
	
    String toString() {
        return nom
    }
    
    @Override 
    boolean equals(final Object that) {         
        if(that == null || !(that instanceof OutilEmploye)) {
            return false
        }      
        return that.id == id            
    }   
    
    @Override 
    int hashCode() { 
            HashCodeBuilder.reflectionHashCode(this, ["id"]) 
    } 
}

