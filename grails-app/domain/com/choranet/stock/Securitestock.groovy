

package com.choranet.stock

import org.apache.commons.lang.builder.*
/**
 * Securitestock Domain Object 
 */
class Securitestock {	
    
    Double stockMin   = 1d	
    Double stockMax   = 1000d	
    Double stockReel  = 0d
    Double stockEntre = 0d
    Double stockSortie = 0d
    Date date_preremption
    
    static belongsTo = [entrepot : Entrepot , produit : Produit]
    
    static mapping = { 
        //entrepot lazy : false
        //produit lazy : false
        batchSize: 11
        sort id:"desc"
    }
    
    static constraints = {
    
        stockMin(nullable : false, min : 0d)
        stockMax(nullable : false, min : 0d)
        stockReel(nullable : false)
        stockEntre(nullable : false)
        stockSortie(nullable : false)
        date_preremption(nullable : true)
    
    }
	
    String toString() {
        return super.toString()
    }
    
    @Override 
    boolean equals(final Object that) {         
        if(that == null || !(that instanceof Securitestock)) {
            return false
        }      
        return that.id == id            
    }   
    
    @Override 
    int hashCode() { 
            HashCodeBuilder.reflectionHashCode(this, ["id"]) 
    } 
}

