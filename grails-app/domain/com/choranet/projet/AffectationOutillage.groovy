
package com.choranet.projet

import com.choranet.rh.Employe;
import org.apache.commons.lang.builder.*
/**
 * AffectationOutillage Domain Object 
 */
class AffectationOutillage {	
    
    Date dateAffectation	
    Date dateFinUtilisationOutillage	
    
    static belongsTo = [projet : Projet , outillageCollectif : OutillageCollectif , employe : Employe]
    
    static mapping = { 
        //projet lazy : false
        //outillageCollectif lazy : false
        //employe lazy : false
        batchSize: 14 
        sort dateAffectation : "desc"
    }
    static constraints = {
        dateAffectation(nullable : false)
        dateFinUtilisationOutillage(nullable : true)
        projet(nullable : false)
        outillageCollectif(nullable : false)
        employe(nullable : false)
    }
	
    String toString() {
        return super.toString()
    }
    
    @Override 
    boolean equals(final Object that) {         
        if(that == null || !(that instanceof AffectationOutillage)) {
            return false
        }      
        return that.id == id            
    }    
    
    @Override 
    int hashCode() { 
            HashCodeBuilder.reflectionHashCode(this, ["id"]) 
    } 
}

