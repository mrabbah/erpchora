
package com.choranet.projet

import org.apache.commons.lang.builder.* 

/**
 * Calendrier Domain Object 
 */
class Calendrier {
    
    Integer nbHeuresTravailParJour	
    String nom	
    Integer nbJoursTravailParMois	
    
    static hasMany = [jourFeriers : JourFerier]
    
    static mapping = { 
        //jourFeriers lazy : false
        batchSize: 10
        cache usage : 'nonstrict-read-write'
        sort "nom"
    }
    static constraints = {
        nbHeuresTravailParJour(min : 1, nullable : false)
        nom(blank : false, nullable : false, unique : true)
        nbJoursTravailParMois(range : 1..31, nullable : false)
        jourFeriers(nullable : true)
    }
	
    String toString() {
        return nom	
    }
    
    @Override 
    boolean equals(final Object that) {         
        if(that == null || !(that instanceof Calendrier)) {
            return false
        }      
        return that.nom == nom            
    }    

    @Override 
    int hashCode() { 
            HashCodeBuilder.reflectionHashCode(this, ["nom"]) 
    } 
}

