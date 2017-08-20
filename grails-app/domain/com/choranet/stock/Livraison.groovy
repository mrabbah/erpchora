
package com.choranet.stock

import com.choranet.gesticom.Partenaire
import com.choranet.gesticom.BonLivraison
import org.apache.commons.lang.builder.*
/**
 * Livraison Domain Object 
 */
class Livraison {	
    
    String numExpedition	
    Integer nbrColis = 0	
    Double poidTotal = 0d	
    Double frais = 0d	
    Date date = new Date()
    String detail
    String intituleVehicule
    String immatriculation
       		   
    static belongsTo = [modeLivraison : ModeLivraison, partenaire : Partenaire]
    
    static hasMany = [bonLivraisons : BonLivraison]
    
    static mapping = { 
        modeLivraison fetch : 'join'
        bonLivraisons lazy : false
        partenaire fetch : 'join'
        batchSize: 14 
        sort date:"desc"
    }    
    static constraints = {
    
        numExpedition(blank : false, nullable : false, unique : true)
        nbrColis(min : 0)
        poidTotal(min : 0d)
        frais(min : 0d)
        date(nullable : false)
        bonLivraisons(nullable : true)
        partenaire(nullable : true)
        detail(nullable : true)
        intituleVehicule(nullable : true)
        immatriculation(nullable : true)
                
    }
	
    String toString() {
        return numExpedition
    }
    
    @Override 
    boolean equals(final Object that) {         
        if(that == null || !(that instanceof Livraison)) {
            return false
        }      
        return that.numExpedition == numExpedition            
    }   
    
    @Override 
    int hashCode() { 
            HashCodeBuilder.reflectionHashCode(this, ["id"]) 
    } 
}

