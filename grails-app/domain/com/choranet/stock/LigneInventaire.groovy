

package com.choranet.stock


import org.apache.commons.lang.builder.*
/**
 * LigneInventaire Domain Object 
 */
class LigneInventaire {	
   
    Double quantiteInv	
    Double qantiteActuele
    Date date_preremption
    
    String trans_style
    
    static transients = ['trans_style']
    
    static belongsTo = [produit : Produit, inventaire : Inventaire]
    
    static mapping = { 
        produit fetch : 'join'
        //batchSize: 14 
    }
    static constraints = {
        quantiteInv(min : 0d, nullable : false)
        qantiteActuele(min : 0d, nullable : false)
        produit(nullable : false)
        date_preremption(nullable : true)
    }
	
    String toString() {        
        //        if(!produit.isAttached()) {
        //            produit = Produit.findById(produit.id)
        //        }
        //        return produit.toString()
        return super.toString()
    }
    
    public String getTrans_style() {
        def resultat = "font-weight: bold;color: #060;"
        if(quantiteInv != null && qantiteActuele != null && qantiteActuele != quantiteInv) {
            resultat = "font-weight: bold;color: #F81105;"
        }
        return resultat
    }
    
    @Override 
    boolean equals(final Object that) {         
        if(that == null || !(that instanceof LigneInventaire)) {
            return false
        }      
        return that.id == id            
    }   
    
    @Override 
    int hashCode() { 
            HashCodeBuilder.reflectionHashCode(this, ["id"]) 
    } 
}

