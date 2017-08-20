package com.choranet.gesticom

import org.apache.commons.lang.builder.* 

class CompteBancaireChoraClientInfo {	
    
    String libelle	
    	
    String rib	
    	
    String codeSwift	
       		   
    Double solde
    
    Double soldePrevu
    
    static belongsTo = [agenceBancaire : AgenceBancaire]
    
    static mapping = { 
        cache true
        //agenceBancaire lazy : false
        //batchSize: 14 
        sort "libelle"
    }
    static constraints = {
        libelle(blank : false, nullable : false)
        rib(nullable : true)
        codeSwift(nullable : true)
        solde(nullable : false)
        soldePrevu(nullable : false)
    }
	
    String toString() {
        //        return agenceBancaire.nom + " - " + libelle
        return super.toString()
    }   
    
    @Override 
    boolean equals(final Object that) {         
        if(that == null || !(that instanceof CompteBancaireChoraClientInfo)) {
            return false
        }      
        return that.id == id            
    } 

    @Override 
    int hashCode() { 
        HashCodeBuilder.reflectionHashCode(this, ["id"]) 
    } 
}