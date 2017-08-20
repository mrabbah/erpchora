

package com.choranet.gesticom


import com.choranet.securite.Utilisateur
import org.apache.commons.lang.builder.*
/**
 * BonCommande Domain Object 
 */
class BonCommande {	
    
    String numBC	
    String type	
    String reference	
    Date date	
    Double trans_totalht
    Double trans_totaltva
    Double trans_totalttc
    Double totalttcRetour = 0d
    Boolean livree = false
    Boolean paye = false
    Boolean retourPaye = true
    Boolean facture = false
    Boolean bonPret = false  
    Boolean retournee = false
    Boolean avecRetour = false
        
    boolean trans_livree
    boolean trans_retournee
    
    static transients = ['trans_totalht', 'trans_totaltva', 'trans_totalttc', 'trans_livree', 'trans_retournee']
    
    static belongsTo = [partenaire : Partenaire, utilisateur : Utilisateur]
    
    static hasMany = [deviss : Devis , ligneProduits : LigneProduit]
    
    static mapping = { 
        //deviss lazy : false
        partenaire fetch : 'join'
        //utilisateur lazy : false
        //ligneProduits cascade:"all-delete-orphan"
        //batchSize : 10 lazy : false, 
        sort date:"desc"
    }
    static constraints = {
    
        numBC(blank : false, nullable : false, unique : true)
        type(blank : false, nullable : false, inList : ["ACHAT", "VENTE"])
        reference(nullable : true)
        date(nullable : false)
        livree(nullable : false)
        retournee(nullable : false)
        paye(nullable : false)
        retourPaye(nullable : false)
        avecRetour(nullable : false)
        totalttcRetour(nullable : false)
        utilisateur(nullable : false)
        deviss(nullable : true)
    
    }
	
    String toString() {
        return numBC
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
    public boolean isTrans_livree() {
        boolean result = true;
        for(LigneProduit lp : ligneProduits) {            
            result = lp.trans_livree ;  
            if(!result) {
                break;
            }
        }
        return result;
    }
    public boolean isTrans_retournee() {
        boolean result = true;
        for(LigneProduit lp : ligneProduits) {            
            result = lp.trans_retournee ;  
            if(!result) {
                break;
            }
        }
        return result;
    }
    
    @Override 
    boolean equals(final Object that) {         
        if(that == null || !(that instanceof BonCommande)) {
            return false
        }      
        return that.numBC == numBC            
    } 
    
    @Override 
    int hashCode() { 
            HashCodeBuilder.reflectionHashCode(this, ["id"]) 
    } 
}
