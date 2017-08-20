
package com.choranet.compta

import com.choranet.stock.CategorieProduit
import org.apache.commons.lang.builder.* 

/**
 * CompteComptableProduit Domain Object 
 */
class CompteComptableProduit {	

    	
    String type	
    
    static belongsTo = [compteComptable : CompteComptable , categorieProduit : CategorieProduit]
    
    static mapping = { 
        //compteComptable lazy : false
        //categorieProduit lazy : false
        batchSize: 14 
        sort type:"asc"
    }
    static constraints = {
        type(nullable : false)
    }
	
    String toString() {
        return super.toString()
    }
    
    @Override 
    boolean equals(final Object that) {         
        if(that == null || !(that instanceof CompteComptableProduit)) {
            return false
        }      
        return that.id == id            
    } 

    @Override 
    int hashCode() { 
            HashCodeBuilder.reflectionHashCode(this, ["id"]) 
    } 
}

