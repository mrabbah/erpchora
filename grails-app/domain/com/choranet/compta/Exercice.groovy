

package com.choranet.compta

import org.apache.commons.lang.builder.*
/**
 * Exercice Domain Object 
 */
class Exercice {	

    	
    Date dateDebut	   	
    String intitule	
    Date dateFin	
       		   
    
    static mapping = { 
        batchSize: 14 
        sort "intitule"
    }
    static constraints = {
    
        dateDebut(nullable : false)
        intitule(nullable : false)
        dateFin(nullable : false)
    
    }
	
    String toString() {
        return super.toString()
    }
    
    @Override 
    boolean equals(final Object that) {         
        if(that == null || !(that instanceof Exercice)) {
            return false
        }      
        return that.id == id            
    } 
    
    @Override 
    int hashCode() { 
            HashCodeBuilder.reflectionHashCode(this, ["id"]) 
    } 
}

