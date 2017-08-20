
package com.choranet.gesticom

import com.choranet.stock.Produit
import com.choranet.stock.Entrepot
import org.apache.commons.lang.builder.*
/**
 * LigneProduit Domain Object 
 */
class LigneProduit {	
    
    Integer sequenceLigne	
    Double quantite = 1	
    Double quantiteLivree = 0d
    Double quantiteRetournee = 0d
    Double remise = 0d
    Double prix
    Double prixDeduit
    String type

    Double trans_sousTotal
    Double trans_sousTotalLivree
    Double trans_sousTotalRetourne
    boolean trans_livree
    boolean trans_retournee
    Double trans_ql
    Double trans_qr
    Date date_preremption
    
    static transients = ['trans_sousTotal', 'trans_livree', 'trans_retournee', 'trans_ql', 'trans_qr', 'trans_sousTotalLivree', 'trans_sousTotalRetourne']
    
    static belongsTo = [produit : Produit, entrepot : Entrepot, bonCommande : BonCommande, bonLivraison : BonLivraison, bonPret : BonPret, devis : Devis]
    
    static mapping = { 
        produit fetch : 'join'
        //entrepot lazy : false
        sort "sequenceLigne"
    }
    static constraints = {
        sequenceLigne(nullable : false)
        quantite(min : 1d, nullable : false)
        quantiteLivree(min : 0d, nullable : false)
        quantiteRetournee(min : 0d, nullable : false)
        remise(min : 0d, nullable : false)
        prixDeduit(min : 0d, nullable : false)
        type(nullable : false, inList : ["ACHAT", "VENTE"])
        entrepot(nullable : true)
        date_preremption(nullable : true)
        bonCommande(nullable : true)
        bonLivraison(nullable : true)        
        bonPret(nullable : true)        
        devis(nullable : true)        
    }

    public void mettreAjourEntrepot() {
        entrepot = produit.empReception
    }

    String toString() {
        //        return (produit.designation + " " + sequenceLigne + " " + quantite)
        return super.toString()
    }
    
    public Double getTrans_sousTotal() {
        if(prixDeduit == null)
        return 0d;
        return prixDeduit * (1 - remise / 100) * quantite
    }
    
    public Double getTrans_sousTotalLivree() {
        if(prixDeduit == null)
        return 0d;
        //return prixDeduit * quantiteLivree
        return prixDeduit * (1 - remise / 100) * quantiteLivree
    }
    
    public Double getTrans_sousTotalRetourne() {
        if(prixDeduit == null)
        return 0d;
        //return prixDeduit * quantiteRetournee
        return prixDeduit * (1 - remise / 100) * quantiteRetournee
    }
    
    public void setType(String type) {
        this.type = type    
        if(produit != null && prix == null) {
            if(type.equals("ACHAT")) {
                prix = produit.prixRevient
            } else {
                prix = produit.prixVenteStandard
            }
        }
    }
    
    public void setProduit(Produit p) {
        this.produit = p
        if(type != null && prix == null) {
            if(type.equals("ACHAT")) {
                prix = produit.prixRevient
            } else {
                prix = produit.prixVenteStandard
            }
        }
    }
    
    public boolean isTrans_livree() {
        return (quantite == quantiteLivree);
    }
    
    public boolean isTrans_retournee() {
        return (quantiteRetournee == quantiteLivree);
    }
    
    @Override 
    boolean equals(final Object that) {         
        if(that == null || !(that instanceof LigneProduit)) {
            return false
        }      
        return that.id == id            
    }   
    
    @Override 
    int hashCode() { 
            HashCodeBuilder.reflectionHashCode(this, ["id"]) 
    } 
}

