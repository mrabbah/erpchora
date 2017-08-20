
package com.choranet.projet

import com.choranet.commun.DocumentNumerique
import org.apache.commons.lang.builder.*
/**
 * Phase Domain Object 
 */
class Phase {	
    
    Date dateDebutPrevue	
    String designation	
    Date dateDebutRelle	
    Date dateFinPrevue	
    Double chargePrevue	
    Date dateFinReelle	
    Double avancementPrevu	
    Double chargeRelle	
    Double avancementReel	
    
    static belongsTo = [projet : Projet]
    static hasMany = [documents: DocumentNumerique]
    
    static mapping = { 
        //projet lazy : false
        //documents lazy : false
        batchSize: 14 
        sort "dateDebutPrevue"
    }
    static constraints = {
        dateDebutPrevue(nullable : false)
        designation(blank : false, nullable : false)
        dateDebutRelle(nullable : false)
        dateFinPrevue(nullable : false)
        chargePrevue(min : 0d, nullable : false)
        dateFinReelle(nullable : false)
        avancementPrevu(range : 0.0..100.0, nullable : false, scale : 2)
        chargeRelle(min : 0d, nullable : false)
        avancementReel(range : 0.0..100.0, nullable : false, scale : 2)
        projet(nullable : false)
        documents(nullable : true)
    }
	
    String toString() {
        return designation	
    }
    
    @Override 
    boolean equals(final Object that) {         
        if(that == null || !(that instanceof Phase)) {
            return false
        }      
        return that.id == id            
    }  
    
    @Override 
    int hashCode() { 
            HashCodeBuilder.reflectionHashCode(this, ["id"]) 
    } 
}

