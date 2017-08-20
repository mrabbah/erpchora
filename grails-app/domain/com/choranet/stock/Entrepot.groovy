

package com.choranet.stock


import org.apache.commons.lang.builder.*

/**
 * Entrepot Domain Object 
 */
class Entrepot {	
    
    String code	
    String intitule	
    String adresse = ""
    String tel	
    String modeValorisation	
    String mode	
    
    static mapping = { 
        cache usage : 'nonstrict-read-write'
        //batchSize: 14 
        sort "code"
    }
    
    static constraints = {
        code(blank : false, nullable : false, unique : true)
        intitule(blank : false, nullable : false, unique : true)
        adresse(nullable : false, blank : false)
        tel(nullable : true)
        modeValorisation(blank : false, nullable : false, inList:["PRIX_ACHAT", "PRIX_VENTE", "PRIX_MOYEN_PENDERE"])
        mode(blank : false, nullable : true, inList:["FIFO", "LIFO"])
    }
    
    static def remove(listEntrepots, entrepotASupprime) {
        def newEntrepotList = []
        
        if (listEntrepots == null)
        return null
            
        if (entrepotASupprime == null) 
        return listEntrepots
            
        listEntrepots.each { obj ->
            if ( obj != null && (obj.code != entrepotASupprime.code)) {
                newEntrepotList.add(obj)
            }
        }
        return newEntrepotList
    }
    
	
    String toString() {
        return (code + " : " + intitule)
    }
    
    @Override 
    boolean equals(final Object that) {         
        if(that == null || !(that instanceof Entrepot)) {
            return false
        }      
        return that.code == code            
    }   

    @Override 
    int hashCode() { 
            HashCodeBuilder.reflectionHashCode(this, ["code"]) 
    } 
}

