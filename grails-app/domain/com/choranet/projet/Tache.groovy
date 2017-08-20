
package com.choranet.projet

import com.choranet.commun.DocumentNumerique
import com.choranet.rh.Employe;
import org.apache.commons.lang.builder.*
/**
 * Tache Domain Object 
 */
class Tache {	

    Integer numero	
    Date dateDebutPrevue	
    String designation	
    Date dateDebutReelle	
    Date dateFinPrevu	
    Double chargePrevue	
    Date dateFinReelle	
    Double avancementPrevu	
    Double chargeReelle	
    Double avancementReel	
    Boolean jalon	
    
    static belongsTo = [phase : Phase , employe : Employe]
    
    static hasMany = [predecesseur : Tache, documents: DocumentNumerique]
    
    static mapping = { 
        //predecesseur lazy : false
        //phase lazy : false
        //employe lazy : false
        //documents lazy : false
        batchSize: 14 
        sort "dateDebutPrevue"
    }
    static constraints = {
        numero()
        dateDebutPrevue(nullable : false)
        designation(blank : false, nullable : false)
        dateDebutReelle(nullable : false)
        dateFinPrevu(nullable : false)
        chargePrevue(min : 0d, scale : 2)
        dateFinReelle(nullable : false)
        avancementPrevu(range : 0.0..100.0, nullable : false, scale : 2)
        chargeReelle(min : 0d, nullable : false, scale : 2)
        avancementReel(range : 0.0..100.0, nullable : false, scale : 2)
        jalon(nullable : false)
        phase(nullable : false)
        employe(nullable : true)
        predecesseur(nullable : true)
        documents(nullable : true)
    }
	
    String toString() {
        return designation	
    }
    
    @Override 
    boolean equals(final Object that) {         
        if(that == null || !(that instanceof Tache)) {
            return false
        }      
        return that.id == id            
    }   
    
    @Override 
    int hashCode() { 
            HashCodeBuilder.reflectionHashCode(this, ["id"]) 
    } 
}

