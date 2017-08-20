
package com.choranet.rh

//import java.io.Serializable;
import com.choranet.projet.Projet
import org.apache.commons.lang.builder.*
/**
 * FichePresence Domain Object 
 */
class FichePresence {	

    Date date	
    Double heuresPresence	
    Double heuresAbscence	
    Double dureeTrajet	
    Double tauxHoraire
    Double heuresTravail
    Double total
    
    static belongsTo = [projet : Projet , employe : Employe]
    
    static mapping = { 
        total formula : "(heures_presence + duree_trajet) * taux_horaire"
        heuresTravail formula : "heures_presence + duree_trajet"
        //projet lazy : false
        //employe lazy : false
        batchSize: 14
        sort date:"desc"
    }
    static constraints = {
        date(nullable : false)
        heuresPresence(min : 0d, nullable : false)
        heuresAbscence(min : 0d, nullable : false)
        dureeTrajet(min : 0d, nullable : false)
        tauxHoraire(min : 0d, nullable : false, scale : 2)
        projet(nullable : false)
        employe(nullable : false)
    }
	
    String toString() {
        return super.toString()
    }
    
    @Override 
    boolean equals(final Object that) {         
        if(that == null || !(that instanceof FichePresence)) {
            return false
        }      
        return that.id == id            
    }   
    
    @Override 
    int hashCode() { 
            HashCodeBuilder.reflectionHashCode(this, ["id"]) 
    } 
}

