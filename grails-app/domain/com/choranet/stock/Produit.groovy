
package com.choranet.stock


import org.zkoss.image.AImage
import org.zkoss.image.Image
import org.apache.commons.lang.builder.*
import com.choranet.compta.RegimeTVA
import com.choranet.gesticom.Partenaire

/**
 * Produit Domain Object 
 */
class Produit {	
    
    String code	
    String codebarre
    String profil
    String designation	
    Double prixRevient	
    Double prixVenteStandard	
    Double poids	
    Double prixAchat	
    Double prixMoyenPendere
    Boolean perissable = false
    Double temperature_conservation
    
    byte[] imageProd	
    Image trans_imageProd
    
    static transients = ['trans_imageProd']
    
    static belongsTo = [empReception : Entrepot , nature : Nature , categorieProduit : CategorieProduit, 
        uniteMesure : UniteMesure , regimeTVA : RegimeTVA, produitParent : Produit, fournisseurPref : Partenaire]
    
    static mapping = { 
        //empReception lazy : false
        //nature lazy : false
        //categorieProduit lazy : false
        uniteMesure fetch : 'join'
        regimeTVA fetch : 'join'
        //produitParent lazy : false
        //batchSize: 100
        sort "designation"
    }
    static constraints = {
    
        code(blank : false, nullable : false, unique : true)
        profil(nullable : true)
        codebarre(nullable : true, unique : true)
        designation(nullable : false)
        prixRevient(min : 0d, nullable : false)
        prixVenteStandard(min : 0d, nullable : false)
        poids(nullable : true, min : 0d)
        prixAchat(min : 0d, nullable : false)
        empReception(nullable : false)
        nature(nullable : true)
        categorieProduit(nullable : false)
        uniteMesure(nullable : false)
        regimeTVA(nullable : false)
        prixMoyenPendere(nullable : true)
        produitParent(nullable : true)
        perissable(nullable : false)
        temperature_conservation(nullable : true)
        fournisseurPref(nullable : true)
        imageProd(nullable : true)
    
    }
	
//    @Override
//    public boolean equals(Object o) {
//        return this.code == o.code
//    }
    
    // function pour retourner la difference entre deux listes d'objets Produit
    static def removeAll(listResult, listObject) {
        if (listResult == null || listResult.size()<1 || listObject == null || listObject.size()<1 )
            return false
            
        def listResultfinal = listResult.toList()
        listResultfinal.each { ProdBasic ->
            listObject.each {
                if (ProdBasic.code == it.code) {
                    listResult.remove(ProdBasic)
                }
            }
        }
        if (listResultfinal.size() > listResult.size()) {
            listResult = listResultfinal
            return true
        }
    }
    
    public void setImageProd(byte[] bd) {
        imageProd = bd
        if(imageProd != null) {
            trans_imageProd = new AImage(code, bd);
        }
    }

    public void setTrans_imageProd(Image img) {        
        if(img == null) {            
            img = Utilitaire.getDumpImage(getClass())
        }
        trans_imageProd = img
        imageProd = trans_imageProd.getByteData()     
    }
    
    String toString() {
        return (designation + " : " + code)
    }
    
    @Override 
    boolean equals(final Object that) {         
        if(that == null || !(that instanceof Produit)) {
            return false
        }      
        return that.code == code            
    }   

    @Override 
    int hashCode() { 
            HashCodeBuilder.reflectionHashCode(this, ["code"]) 
    } 
}

