

package com.choranet.exception

import org.apache.commons.lang.builder.*

/**
 * Depenses Domain Object 
 */
class Depenses {	
    
    Date date	
    	
    String detail	
    	
    Double montant	
       		   
    
    static belongsTo = [natureDeCharge : NatureDeCharge]
    
    static mapping = { 
        //natureDeCharge lazy : false
        batchSize: 14 
    }
    static constraints = {
    
        date(nullable : false)
    
        detail(nullable : true)
    
        montant(nullable : true, min : 0d)
        
        natureDeCharge(nullable : false)
    
    }
	
    String toString() {
        return super.toString()
    }
    
    @Override 
    boolean equals(final Object that) {         
        if(that == null || !(that instanceof Depenses)) {
            return false
        }      
        return that.id == id            
    } 
    
    @Override 
    int hashCode() { 
            HashCodeBuilder.reflectionHashCode(this, ["id"]) 
    } 
}

