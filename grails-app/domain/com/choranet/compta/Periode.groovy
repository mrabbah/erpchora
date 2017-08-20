
package com.choranet.compta

import org.apache.commons.lang.builder.*
/**
 * P�riode Domain Object 
 */
class Periode {	

    	
    String intitule	
    
    static belongsTo = [exercice : Exercice]
    
    static mapping = { 
        //exercice lazy : false
        batchSize: 14
        sort "intitule"
    }
    static constraints = {
    
        exercice(nullable : false)
        intitule(nullable : false)
    
    }
	
    String toString() {
        return super.toString()
    }
    
    @Override 
    boolean equals(final Object that) {         
        if(that == null || !(that instanceof Periode)) {
            return false
        }      
        return that.id == id            
    } 
    
    @Override 
    int hashCode() { 
            HashCodeBuilder.reflectionHashCode(this, ["id"]) 
    } 
}

