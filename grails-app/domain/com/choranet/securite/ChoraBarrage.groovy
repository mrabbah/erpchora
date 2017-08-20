

package com.choranet.securite

import org.apache.commons.lang.builder.*
/**
 * Security domain class
 */
class ChoraBarrage {
    	
    Date dateInstanciation;
    
    Date dernierAcces;
    
    boolean demo = true
    
    boolean premierAcces = true
    
    String raisonSocial
    
    String idInstance
    
    String produit
    
    String versionProduit
    
    static mapping = { 
        cache : true
    }
    static constraints = {  
        dateInstanciation(nullable : false)
        dernierAcces(nullable : false)
        idInstance(nullable : false, blank: false)
        raisonSocial(nullable : false, blank: false)
        produit(nullable : false, blank: false)
        versionProduit(nullable : false, blank: false)
    }

    @Override 
    boolean equals(final Object that) {         
        if(that == null || !(that instanceof ChoraBarrage)) {
            return false
        }      
        return that.idInstance == idInstance            
    }   
    
    @Override 
    int hashCode() { 
            HashCodeBuilder.reflectionHashCode(this, ["id"]) 
    } 
    
}

