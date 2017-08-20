
package com.choranet.projet

import org.apache.commons.lang.builder.*
/**
 * Note Domain Object 
 */
class Note {	
    	
    Date date	
    String objet	
    String corps	
    
    static belongsTo = [projet : Projet]
    
    static mapping = { 
        //projet lazy : false
        batchSize: 14 
        sort date : "desc"
    }
    static constraints = {
        date(nullable : false)
        objet(blank : false, nullable : false)
        corps(blank : true)
        projet(nullable : false)
    }
	
    String toString() {
        return objet
    }
    
    @Override 
    boolean equals(final Object that) {         
        if(that == null || !(that instanceof Note)) {
            return false
        }      
        return that.id == id            
    }  
    
    @Override 
    int hashCode() { 
            HashCodeBuilder.reflectionHashCode(this, ["id"]) 
    } 
}

