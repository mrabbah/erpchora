
package com.choranet.compta

import org.apache.commons.lang.builder.*
/**
 * Journal Domain Object 
 */
class Journal {	

    	
    String intitule	
    
    static hasMany = [compteComptables : CompteComptable]
    
    static mapping = { 
        //compteComptables lazy : false
        batchSize: 14 
        sort "intitule"
    }
    static constraints = {
    
        compteComptables(nullable : false)
        intitule(nullable : false)
    
    }
	
    String toString() {
        return super.toString()
    }
    
    @Override 
    boolean equals(final Object that) {         
        if(that == null || !(that instanceof Journal)) {
            return false
        }      
        return that.id == id            
    } 
    
    @Override 
    int hashCode() { 
            HashCodeBuilder.reflectionHashCode(this, ["id"]) 
    } 
}

