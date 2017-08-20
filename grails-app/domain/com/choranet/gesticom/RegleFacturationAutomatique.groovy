

package com.choranet.gesticom


import org.apache.commons.lang.builder.*
/**
 * RegleFacturationAutomatique Domain Object 
 */
class RegleFacturationAutomatique {	
    
    Integer nombreBlaRegroupe	
    	
    String libelle	
    	
    Integer priorite	
       		   
    String type
    
    static hasMany = [partenaires : Partenaire]
    
    static mapping = { 
        //partenaires lazy : false
        batchSize: 14 
        sort "libelle"
    }
    static constraints = {
    
        nombreBlaRegroupe()
    
        libelle(blank : false, nullable : false, unique : true)
    
        priorite(nullable : false, min : 1)
        
        type(nullable : false, inList : ["JusquaDateActuelle", "DernierMois", "TroisDerniersMois", "NombreBonLivraison"])
    
    }
	
    String toString() {
        return libelle
    }
    
    @Override 
    boolean equals(final Object that) {         
        if(that == null || !(that instanceof RegleFacturationAutomatique)) {
            return false
        }      
        return that.libelle == libelle            
    }    
    
    @Override 
    int hashCode() { 
            HashCodeBuilder.reflectionHashCode(this, ["id"]) 
    } 
}

