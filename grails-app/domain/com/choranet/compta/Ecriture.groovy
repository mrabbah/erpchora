
package com.choranet.compta

import com.choranet.gesticom.Paiement
import com.choranet.gesticom.LigneProduit
import org.apache.commons.lang.builder.*
/**
 * Ecriture Domain Object 
 */
class Ecriture {	

    	
    String description	
    Double debit	
    String reference	
    Double credit	
    Date date	
    Integer numero	
    Date dateEcheance	
    Date dateCreance	
    Double montantDevise	
   
    static belongsTo = [devise : Devise , compteComptable : CompteComptable , periode : Periode, paiement : Paiement, ligneProduit : LigneProduit]
    
    static mapping = { 
        //devise lazy : false
        paiement cascade:"all"
        ligneProduit cascade:"all"
        //compteComptable lazy : false
        //periode lazy : false
        batchSize: 14 
        sort date:"desc"
    }
    static constraints = {
    
        paiement(nullable : true)
        ligneProduit(nullable : true)
        devise(nullable : true)
        compteComptable(nullable : false)
        description(nullable : true)
        debit(nullable : true)
        reference(nullable : true)
        credit(nullable : true)
        date(nullable : false)
        numero(nullable : false)
        dateEcheance(nullable : true)
        dateCreance(nullable : true)
        montantDevise(nullable : true)
    
    }
	
    String toString() {
        return super.toString()
    }
    
    @Override 
    boolean equals(final Object that) {         
        if(that == null || !(that instanceof Ecriture)) {
            return false
        }      
        return that.id == id            
    } 
    
    @Override 
    int hashCode() { 
            HashCodeBuilder.reflectionHashCode(this, ["id"]) 
    } 
}

