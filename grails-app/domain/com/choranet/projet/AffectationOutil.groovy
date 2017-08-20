
package com.choranet.projet

import com.choranet.rh.Employe
import org.apache.commons.lang.builder.*
/**
 * AffectationOutil Domain Object 
 */
class AffectationOutil {
    
    Date date	
    Integer heuresEntretien	
    Integer heuresUtilisation	
    Double coutHoraire
    Double total
    
    static belongsTo = [outilEmploye : OutilEmploye , projet : Projet , employe : Employe]
    
    static mapping = { 
        total formula : "(heures_entretien + heures_utilisation) * cout_horaire"
        //outilEmploye lazy : false
        //projet lazy : false
        //employe lazy : false
        batchSize: 14 
        sort date:"desc"
    }
    static constraints = {
        date(nullable : false)
        heuresEntretien(min : 0)
        heuresUtilisation(min : 0)
        coutHoraire(min : 0d, nullable : false)
        outilEmploye(nullable : false)
        projet(nullable : false)
        employe(nullable : false)
    }
	
    String toString() {
        return super.toString()
    }
    
    @Override 
    boolean equals(final Object that) {         
        if(that == null || !(that instanceof AffectationOutil)) {
            return false
        }      
        return that.id == id            
    }    
    
    @Override 
    int hashCode() { 
            HashCodeBuilder.reflectionHashCode(this, ["id"]) 
    } 
}

