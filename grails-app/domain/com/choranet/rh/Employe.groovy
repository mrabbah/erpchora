
package com.choranet.rh

import org.apache.commons.lang.builder.*
import com.choranet.projet.Calendrier

/**
 * Employe Domain Object 
 */
class Employe {	
    
    Integer numero	
    String nom	
    String prenom	
    String telephone	
    Double tauxHoraire	
    Double salaire
    String site
    
    static belongsTo = [calendrier : Calendrier , fonction : Fonction]
    
    static mapping = { 
        cache usage : 'nonstrict-read-write'
        //calendrier lazy : false
        //fonction lazy : false
        batchSize: 14 
        sort "nom"
    }
    static constraints = {
        numero(min : 1, nullable : false)
        nom(blank : false, nullable : false)
        prenom(nullable : true)
        telephone(nullable : true)
        tauxHoraire(min : 0d, nullable : false)
        salaire(min : 0d, nullable : false)
        calendrier(nullable : true)
        fonction(nullable : false)
        site(nullable : true)
    }
	
    String toString() {
        return (nom + " " + prenom)
    }
    
    @Override 
    boolean equals(final Object that) {         
        if(that == null || !(that instanceof Employe)) {
            return false
        }      
        return that.id == id            
    }   

    @Override 
    int hashCode() { 
            HashCodeBuilder.reflectionHashCode(this, ["id"]) 
    } 
}

