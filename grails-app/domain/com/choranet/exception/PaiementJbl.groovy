
package com.choranet.exception

import com.choranet.rh.Employe
import org.apache.commons.lang.builder.*
/**
 * PaiementJbl Domain Object 
 */
class PaiementJbl {	


    Integer annee	
    String mois	
    Integer joursTravailles
    Integer joursAbscences
    Double avance	
    Double retenuVetement	
    Double heuresSupp	
    Double retenuAbscence	
    Double fraisDeplacement	
    Double rappel	
    Date dateAvance	
    Date dateSalaire	
    Double salaire	
    
    static belongsTo = [employe : Employe]
    
    static mapping = { 
        //employe lazy : false
        batchSize: 20
    }
    static constraints = {
        annee(min : 2000, nullable : false)
        mois(inList:["Janvier", "Février", "Mars", "Avril", "Mai", "Juin", "Juillet", "Aout", "Septembre", "Octobre", "Novembre", "Décembre"])
        joursTravailles(min : 0, nullable : false)
        avance(min : 0d, nullable : false)
        retenuVetement(min : 0d, nullable : false)
        heuresSupp(min : 0d, nullable : false)
        retenuAbscence(min : 0d, nullable : false)
        fraisDeplacement(min : 0d, nullable : false)
        rappel(min : 0d, nullable : false)
        dateAvance(nullable : true)
        dateSalaire(nullable : true)
        salaire(min : 0d, nullable : false)
        employe(nullable : false)
    }
	
    String toString() {
        //        return employe.nom + " " + mois + " " + annee
        return super.toString()
    }
    
    @Override 
    boolean equals(final Object that) {         
        if(that == null || !(that instanceof PaiementJbl)) {
            return false
        }      
        return that.id == id            
    } 
    
    @Override 
    int hashCode() { 
            HashCodeBuilder.reflectionHashCode(this, ["id"]) 
    } 
}

