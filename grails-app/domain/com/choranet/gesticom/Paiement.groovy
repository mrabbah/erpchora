

package com.choranet.gesticom


import java.text.SimpleDateFormat
import java.text.NumberFormat;
import java.text.DecimalFormat;
import org.apache.commons.lang.builder.*
/**
 * Paiement Domain Object 
 */
class Paiement {	
    
    String numTransaction	
    	
    Date date	
    	
    Double montantPaye	
    	
    Date dateEncaissement	
    
    Boolean credit
    
    String statut
    
    static belongsTo = [modeReglement : ModeReglement , compteBancairePartenaire : CompteBancaire, compteBancaire : CompteBancaireChoraClientInfo, bonCommande : BonCommande, partenaire : Partenaire]
   

    static mapping = { 
        modeReglement fetch : 'join'
        compteBancaire fetch : 'join'
        //compteBancairePartenaire lazy : false
        bonCommande fetch : 'join'
        partenaire fetch : 'join'
        batchSize: 10
        sort date:"desc"
    }
    static constraints = {
    
        numTransaction(nullable : true)    
        date(nullable : false)    
        montantPaye(min : 0d)    
        dateEncaissement(nullable : true)
        credit(nullable : false)
        modeReglement(nullable : false)
        compteBancaire(nullable: true)
        compteBancairePartenaire(nullable: true)
        bonCommande(nullable: false)
        partenaire(nullable: false)
        statut(nullabe : true)
    }	
    
    @Override 
    boolean equals(final Object that) {         
        if(that == null || !(that instanceof Paiement)) {
            return false
        }      
        return that.id == id            
    }    
    
    @Override 
    int hashCode() { 
            HashCodeBuilder.reflectionHashCode(this, ["id"]) 
    } 
    
    String toString() {
        //        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        //        NumberFormat nf = new DecimalFormat("#,##0.##");
        //        return partenaire.raisonSociale + " " + nf.format(montantPaye) + " " + sdf.format(date) + " " + numTransaction
        return super.toString()
    }
}
