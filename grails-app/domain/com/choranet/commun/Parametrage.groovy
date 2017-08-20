package com.choranet.commun

import org.apache.commons.lang.builder.*

class Parametrage {

    Boolean afficherRemiseFacture = false
    Boolean afficherTvaFacture = false
    Boolean produitPerissableManaged = false
    Boolean multiEntrepotEnabled = false
    
    //static belongsTo = [choraClientInfo : ChoraClientInfo]
    
    static constraints = {
//        afficherRemiseFacture(nullable : false)
//        afficherTvaFacture(nullable : false)
//        produitPerissableManaged(nullable : false)
//        multiEntrepotEnabled(nullable : false)
    }
     
    public boolean isProduitPerissableManaged() {
        return produitPerissableManaged;
    }
	
    public boolean isAfficherRemiseFacture() {
        return afficherRemiseFacture;
    }
  
    public boolean isAfficherTvaFacture() {
        return afficherTvaFacture;
    }
  
    public boolean isMultiEntrepotEnabled() {
        return multiEntrepotEnabled;
    }
    
    @Override 
    boolean equals(final Object that) {         
        if(that == null || !(that instanceof Parametrage)) {
            return false
        }      
        return that.id == id            
    } 
    
    @Override 
    int hashCode() { 
            HashCodeBuilder.reflectionHashCode(this, ["id"]) 
    } 
}
