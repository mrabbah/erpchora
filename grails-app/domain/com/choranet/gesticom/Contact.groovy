

package com.choranet.gesticom

import org.apache.commons.lang.builder.*
/**
 * Contact Domain Object 
 */
class Contact {	
    
    String nom	
    	
    String titre	
    	
    String fonction	
    	
    String fixe	
    	
    String portable	
    	
    String email	
       		   
    
    static belongsTo = [partenaire : Partenaire]
    
    static mapping = { 
        //partenaire lazy : false
        batchSize: 11 
        sort "nom"
    }
    static constraints = {
    
        nom(blank : false, nullable : false)
    
        titre(blank : false, nullable : false)
    
        fonction(nullable : true)
    
        fixe(nullable : true)
    
        portable(nullable : true)
    
        email(nullable : true)
        
        partenaire(nullable : true)
    
    }
	
    String toString() {
        return (titre + " " + nom + " : " + fonction)
    }
    
    @Override 
    boolean equals(final Object that) {         
        if(that == null || !(that instanceof Contact)) {
            return false
        }      
        return that.id == id            
    } 
    
    @Override 
    int hashCode() { 
            HashCodeBuilder.reflectionHashCode(this, ["id"]) 
    } 
}

