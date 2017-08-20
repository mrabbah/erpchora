

package com.choranet.gesticom

import org.apache.commons.lang.builder.*
/**
 * Devis Domain Object 
 */
class Devis {	
    
    String numDevis	
    	
    String type	
    	
    String reference	
    	
    Date date	
    	
    Date dateFinValidite	
    
    Double trans_totalht
    
    Double trans_totaltva
    
    Double trans_totalttc
    
    Boolean trans_isvalid
    
    static transients = ['trans_totalht', 'trans_totaltva', 'trans_totalttc', 'trans_isvalid']
    
    static belongsTo = [partenaire : Partenaire]
    
    static hasMany = [ligneProduits : LigneProduit]
    
    static mapping = { 
        partenaire fetch : 'join'
        //ligneProduits cascade:"all-delete-orphan"
        //batchSize: 14 
        sort date:"desc"
    }
    static constraints = {
    
        numDevis(blank : false, nullable : false, unique : true)
    
        type(nullable : false, inList : ["ACHAT", "VENTE"])
    
        reference(nullable : true)
    
        date(nullable : false)
    
        dateFinValidite(nullable : true)
    
    }
	
    String toString() {
        return numDevis
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
    
    public Boolean getTrans_isvalid() {
        if (dateFinValidite == null) 
            return true
        return (date <= dateFinValidite)
    }
    
    @Override 
    boolean equals(final Object that) {         
        if(that == null || !(that instanceof Devis)) {
            return false
        }      
        return that.numDevis == numDevis            
    } 
    
    @Override 
    int hashCode() { 
            HashCodeBuilder.reflectionHashCode(this, ["id"]) 
    } 
}

