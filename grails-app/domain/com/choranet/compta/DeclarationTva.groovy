

package com.choranet.compta

import org.apache.commons.lang.builder.*
import com.choranet.gesticom.Facture
import com.choranet.gesticom.Paiement
/**
 * DeclarationTva Domain Object 
 */
class DeclarationTva {	
    
    String numDeclaration	
    	
    Date date	
    	
    String periode	
    	
    Integer exercise	
       		   
    static hasMany = [paiements : Paiement , factures : Facture]
    
    static mapping = { 
        //paiements lazy : false
        //factures lazy : false
        batchSize: 14 
        sort date:"desc"
    }
    static constraints = {
    
        numDeclaration(blank : false, nullable : false, unique : true)
    
        date()
    
        periode()
    
        exercise()
    
    }
	
    String toString() {
        return numDeclaration
    }
    
    @Override 
    boolean equals(final Object that) {         
        if(that == null || !(that instanceof DeclarationTva)) {
            return false
        }      
        return that.numDeclaration == numDeclaration            
    } 
    
    @Override 
    int hashCode() { 
            HashCodeBuilder.reflectionHashCode(this, ["id"]) 
    } 
    
}
