

package com.choranet.gesticom


import org.apache.commons.lang.builder.*

/**
 * CompteBancaire Domain Object 
 */
class CompteBancaire {	
    
    String libelle	
    	
    String rib	
    	
    String codeSwift	
       		   
    
    static belongsTo = [agenceBancaire : AgenceBancaire , partenaire : Partenaire]
    
    static mapping = { 
        //agenceBancaire lazy : false
        partenaire fetch : 'join'
        cache usage : 'nonstrict-read-write'
        //batchSize: 14 
        sort "libelle"
    }
    static constraints = {
    
        libelle(blank : false, nullable : false, unique : true)
    
        rib(blank : false, nullable : false, unique : true)
    
        codeSwift(nullable : true)
    
    }
	
    String toString() {
        //        return agenceBancaire.nom+" -"+libelle
        return super.toString()
    }
    
    @Override 
    boolean equals(final Object that) {         
        if(that == null || !(that instanceof CompteBancaire)) {
            return false
        }      
        return that.libelle == libelle            
    } 

    @Override 
    int hashCode() { 
        HashCodeBuilder.reflectionHashCode(this, ["libelle"]) 
    } 
}

