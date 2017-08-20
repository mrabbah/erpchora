

package com.choranet.gesticom


import com.choranet.securite.Utilisateur
import org.apache.commons.lang.builder.*
/**
 * BonPret Domain Object 
 */
class BonPret {	
    
    String numBP	
    	
    String type	
    	
    Date date	
    	
    String reference	
   
    Double montantGarantie = 0d    	
       		   
    Double trans_totalht
    
    Double trans_totaltva
    
    Double trans_totalttc
    
    BonCommande bonCommande
    
    static transients = ['trans_totalht', 'trans_totaltva', 'trans_totalttc']
    
    static belongsTo = [partenaire : Partenaire, utilisateur : Utilisateur]
    
    static hasMany = [deviss : Devis, ligneProduits : LigneProduit]
    
    static mapping = { 
        partenaire fetch : 'join'
        //bonCommande lazy : false
        //utilisateur lazy : false
        //deviss lazy : false, batchSize : 5
        //ligneProduits lazy : false, cascade:"all-delete-orphan", batchSize : 10
        //batchSize: 14 
        sort date:"desc"
    }
    static constraints = {
    
        numBP(blank : false, nullable : false, unique : true)
    
        type(blank : false, nullable : false, inList : ["ACHAT", "VENTE"])
    
        date(nullable : false)
    
        reference(nullable : true)
    
        montantGarantie(nullable : false)
        deviss(nullable : true)
        bonCommande(nullable : true)
        utilisateur(nullable : false)
    
    }
	
    String toString() {
        return numBP
    }
    
    public Double getTrans_totalht() {
        trans_totalht = 0d;
        for(LigneProduit lp : ligneProduits) {
            trans_totalht += lp.trans_sousTotal ;   
        }
        return trans_totalht;
    }
    public Double getTrans_totaltva() {
        trans_totaltva = 0d;
        for(LigneProduit lp : ligneProduits) {
            trans_totaltva += lp.trans_sousTotal * lp.produit.regimeTVA.taux / 100 ;   
        }
        return trans_totaltva;
    }
    public Double getTrans_totalttc() {
        trans_totalttc = 0d;
        for(LigneProduit lp : ligneProduits) {
            trans_totalttc += lp.trans_sousTotal * (1 + lp.produit.regimeTVA.taux / 100) ;   
        }
        return trans_totalttc;
    }
    
    @Override 
    boolean equals(final Object that) {         
        if(that == null || !(that instanceof BonPret)) {
            return false
        }      
        return that.numBP == numBP            
    } 
    
    @Override 
    int hashCode() { 
            HashCodeBuilder.reflectionHashCode(this, ["id"]) 
    } 
}

