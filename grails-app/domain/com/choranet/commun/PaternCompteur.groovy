

package com.choranet.commun


import org.apache.commons.lang.builder.* 

/**
 * PaternCompteur Domain Object 
 */
class PaternCompteur {	
    
    String libelle	
    	
    String prefixe	
    	
    String suffixe	
    	
    Integer pas	
    	
    Integer remplissage	
    	
    Integer numeroSuivant	
    	
    String type	
       		   
    
    static mapping = { 
        batchSize: 14 
        cache usage : 'nonstrict-read-write'
        sort libelle:"asc"
    }
    static constraints = {
    
        libelle(blank : false, nullable : false, unique : true)
    
        prefixe(blank : true, nullable : false)
    
        suffixe(blank : true, nullable : false)
    
        pas(min:1, nullable : false)
    
        remplissage(min:1, nullable : false)
    
        numeroSuivant(min : 0, nullable : false)
    
        type(blank : false, nullable : false, inList:["BON_COMMANDE", "BON_LIVRAISON", "AVOIR", "FACTURE", "DEVIS", "BON_RETOUR", "BON_PRET", "LIVRAISON"])
    
    }
	
    String toString() {
        return libelle
    }
    
    @Override 
    boolean equals(final Object that) {         
        if(that == null || !(that instanceof PaternCompteur)) {
            return false
        }      
        return that.libelle == libelle            
    } 

    @Override 
    int hashCode() { 
            HashCodeBuilder.reflectionHashCode(this, ["libelle"]) 
    } 
}

