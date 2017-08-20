

package com.choranet.stock


import org.apache.commons.lang.builder.*
/**
 * AlertsSecuriteStock Domain Object 
 */
class AlertsSecuriteStock {	

    Date dateAlerte = new Date()	
    Date dateFinAlerte	
    Boolean tjsValide = true
    Double stockMin	
    Double stockMax	
    Double stockReel	
    String details	
    
    static belongsTo = [raisonDeclanchement : MouvementStock , raisonRelachement : MouvementStock]
    
    static mapping = { 
        raisonDeclanchement fetch : 'join'
        raisonRelachement fetch : 'join'
        batchSize: 10
        sort dateAlerte : "desc"
    }
    static constraints = {
    
        dateAlerte(nullable : false)
        dateFinAlerte(nullable : true)
        tjsValide(nullable : false)
        stockMin(nullable : false, min : 0d)
        stockMax(nullable : false, min : 0d)
        stockReel(nullable : false)
        details(nullable : true, maxSize:1000)
        raisonRelachement(nullable : true)

    }
	
    String toString() {
        return super.toString()
    }
    
    @Override 
    boolean equals(final Object that) {         
        if(that == null || !(that instanceof AlertsSecuriteStock)) {
            return false
        }      
        return that.id == id            
    }   
    
    @Override 
    int hashCode() { 
            HashCodeBuilder.reflectionHashCode(this, ["id"]) 
    } 
}

