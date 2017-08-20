

package com.choranet.gesticom


import com.choranet.stock.Livraison
import org.apache.commons.lang.builder.*
/**
 * BonLivraison Domain Object 
 */
class BonLivraison {	
    
    String numBL	
    String type	
    String reference	
    Date date	
    Double trans_totalht
    Double trans_totaltva
    Double trans_totalttc
    Boolean estBonRetour = false
    Boolean estLivre 
    
    static transients = ['trans_totalht', 'trans_totaltva', 'trans_totalttc']
    
    Livraison livraison
    
    static belongsTo = [bonCommande : BonCommande]
    
    static hasMany = [ligneProduits : LigneProduit]
    
    static mapping = { 
        bonCommande fetch : 'join'
        //ligneProduits cascade:"all-delete-orphan"
        livraison fetch : 'join'
        batchSize: 13 
        sort date:"desc"
    }
    static constraints = {
    
        numBL(blank : false, nullable : false, unique : true)
        type(nullable : false, inList : ["ACHAT", "VENTE"])
        reference(nullable : true)
        date()
        livraison(nullable : true)
        bonCommande(nullable : false)
        estBonRetour(nullable: false)
        estLivre(nullable: false)
    }
	
    String toString() {
        return numBL
    }
    
    public Double getTrans_totalht() {
        trans_totalht = 0d;
        if(estBonRetour) {
            for(LigneProduit lp : ligneProduits) {
                trans_totalht += lp.trans_sousTotalRetourne ;   
            } 
        } else {
            for(LigneProduit lp : ligneProduits) {
                trans_totalht += lp.trans_sousTotalLivree ;   
            }   
        }        
        return trans_totalht;
    }
    public Double getTrans_totaltva() {
        trans_totaltva = 0d;
        if(estBonRetour) {
            for(LigneProduit lp : ligneProduits) {
                trans_totaltva += lp.trans_sousTotalRetourne * lp.produit.regimeTVA.taux / 100 ;   
            }
        } else {
            for(LigneProduit lp : ligneProduits) {
                trans_totaltva += lp.trans_sousTotalLivree * lp.produit.regimeTVA.taux / 100 ;   
            }
        }
        return trans_totaltva;
    }
    public Double getTrans_totalttc() {
        trans_totalttc = 0d;
        if(estBonRetour) {
            for(LigneProduit lp : ligneProduits) {
                trans_totalttc += lp.trans_sousTotalRetourne * (1 + lp.produit.regimeTVA.taux / 100) ;   
            }
        } else {
            for(LigneProduit lp : ligneProduits) {
                trans_totalttc += lp.trans_sousTotalLivree * (1 + lp.produit.regimeTVA.taux / 100) ;   
            }
        }
        
        return trans_totalttc;
    }
    
    @Override 
    boolean equals(final Object that) {         
        if(that == null || !(that instanceof BonLivraison)) {
            return false
        }      
        return that.numBL == numBL            
    } 
    
    @Override 
    int hashCode() { 
            HashCodeBuilder.reflectionHashCode(this, ["id"]) 
    } 
}

