
package com.choranet.projet

import org.apache.commons.lang.builder.*
/**
 * ChargeDivers Domain Object 
 */
class ChargeDivers {
    
    Date date	
    String nom	
    Double cout	
    
    static belongsTo = [projet : Projet]
    
    static mapping = { 
        //projet lazy : false
        batchSize: 14 
        sort date:"desc"
    }
    static constraints = {
        date(nullable : false)
        nom(blank : false, nullable : false)
        cout(min : 0d, nullable : false)
        projet(nullable : false)
    }
	
    String toString() {
        return nom
    }
    
    @Override 
    boolean equals(final Object that) {         
        if(that == null || !(that instanceof ChargeDivers)) {
            return false
        }      
        return that.id == id            
    }    
    
    @Override 
    int hashCode() { 
            HashCodeBuilder.reflectionHashCode(this, ["id"]) 
    } 
}

