

package com.choranet.commun


import org.apache.commons.lang.builder.* 

/**
 * MessageDocument Domain Object 
 */
class MessageDocument {	
    
    String entete	
    	
    String pied	
    	
    String type	
    
    String typeMouvement
       		   
    
    static mapping = { 
        cache usage : 'nonstrict-read-write'
        batchSize: 14 
        sort id:"desc"
    }
    static constraints = {
    
        entete(nullable : true, maxSize : 6000)
    
        pied(nullable : true, maxSize : 6000)
    
        type(blank : false, nullable : false, inList:["BON_COMMANDE", "BON_LIVRAISON", "AVOIR", "FACTURE", "DEVIS", "BON_RETOUR", "BON_PRET"])
        typeMouvement(blank : false, nullable : false, inList:["ACHAT", "VENTE"])
    
    }
	
    String toString() {
        return super.toString()
    }
    
    @Override 
    boolean equals(final Object that) {         
        if(that == null || !(that instanceof MessageDocument)) {
            return false
        }      
        return that.id == id            
    } 

    @Override 
    int hashCode() { 
            HashCodeBuilder.reflectionHashCode(this, ["id"]) 
    } 
}

