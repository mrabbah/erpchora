

package com.choranet.gesticom


import org.apache.commons.lang.builder.*
/**
 * Facture Domain Object 
 */
class Facture {	
    
    String numeroFacture	
    String type	
    String reference	
    Date date
    Boolean comptabilise = false
    Boolean estAvoir = false
    
    Double trans_totalht
    Double trans_totaltva
    Double trans_totalttc
    List trans_ligneProduits
    String faitgenerateur
    
    static transients = ['trans_totalht', 'trans_totaltva', 'trans_totalttc', 'trans_ligneProduits']
       		   
    static belongsTo = [partenaire : Partenaire]
    
    static hasMany = [paiements : Paiement, bonCommandes : BonCommande]
    
    static mapping = { 
        //paiements lazy : false
        //bonCommandes lazy : false
        partenaire fetch : 'join'
        batchSize: 10 
        sort date:"desc"
    }
    static constraints = {
    
        numeroFacture(blank : false, nullable : false, unique : true)
        type(blank : false, nullable : false, inList : ["ACHAT", "VENTE"])
        reference(nullable : true)
        date(nullable : false)
        bonCommandes(nullable : true)
        comptabilise(nullable : false)
        faitgenerateur(nullable : false)
        estAvoir(nullable : false)
        paiements(nullable : true)
        partenaire(nullable : false)
    
    }
	
    String toString() {
        return numeroFacture
    }
    
    public Double getTrans_totalht() {
        trans_totalht = 0d;
        if(faitgenerateur.equals("Encaissement")) {
            def codes = []
            def ttcbc = 0d
            def htbc = 0d
            for(p in paiements) {
                if(!codes.contains(p.bonCommande.numBC)) {
                    codes.add(p.bonCommande.numBC)
                    ttcbc += p.bonCommande.trans_totalttc
                    htbc += p.bonCommande.trans_totalht
                }
            }
            if(ttcbc != 0) {
                trans_totalht = (getTrans_totalttc()/ttcbc) * htbc 
            }
            
        } else {
            if(bonCommandes) {
                for(bc in bonCommandes) {
                    trans_totaltva += bc.trans_totalht
                }
            }
        }
            
        return trans_totalht;
    }
    public Double getTrans_totaltva() {
        trans_totaltva = getTrans_totalttc() - getTrans_totalht();
        return trans_totaltva;
    }
    public Double getTrans_totalttc() {
        trans_totalttc = 0d;
        if(faitgenerateur.equals("Encaissement")) {
            if(paiements) {
                for(paiement in paiements) {
                    trans_totalttc += paiement.montantPaye
                }
            }
        } else { // fait generateur facturation
            if(bonCommandes) {
                for(bc in bonCommandes) {
                    trans_totalttc += bc.trans_totalttc
                }
            }
        }
        return trans_totalttc;
    }
    public List getTrans_ligneProduits() {
        def trans_ligneProduits = []
        if(faitgenerateur.equals("Encaissement")) {
            if(paiements) {
                def codes = []
                for(p in paiements) {
                    if(!codes.contains(p.bonCommande.numBC)) {
                        codes.add(p.bonCommande.numBC)
                        trans_ligneProduits.addAll(p.bonCommande.ligneProduits)
                    }
                }
            }
        } else {
            if(bonCommandes) {
                for(bc in bonCommandes) {
                    trans_ligneProduits.addAll(bc.ligneProduits)
                } 
            }
        }
        
        return trans_ligneProduits.sort{it.sequenceLigne}
    }
    
    @Override 
    boolean equals(final Object that) {         
        if(that == null || !(that instanceof Facture)) {
            return false
        }      
        return that.numeroFacture == numeroFacture            
    } 
    
    @Override 
    int hashCode() { 
            HashCodeBuilder.reflectionHashCode(this, ["id"]) 
    } 
}

