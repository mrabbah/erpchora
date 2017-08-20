

package com.choranet.gesticom

import org.apache.commons.lang.builder.*
import com.choranet.stock.CategorieProduit
import com.choranet.stock.Produit

/**
 * ReglePrix Domain Object 
 */
class ReglePrix {	
    
    String code	
    	
    Double tauxRemise	
    	
    String libelle	
    	
    String operateurComparaison	
    	
    Double quantiteMin	
    
    Double quantiteMax
    	
    Integer priorite	
    	
    Double valeurFixe	
    	
    Date dateDebut	
    	
    Date dateFin	
    	
    Boolean active	
    	
    String typePrixdeBase
    
    String typeRegle
    	
    Boolean arrondirApresVirguel	
    	
    String senseArrondissement	
    	
    Double seuilArrondissement	
       		   
    
    static hasMany = [categoriePartenaires : CategoriePartenaire, partenaires : Partenaire , categorieProduits : CategorieProduit, produits : Produit]
    
    static mapping = { 
        //categoriePartenaires lazy : false
        //partenaires lazy : false
        //categorieProduits lazy : false
        //produits lazy : false
        batchSize: 14 
        sort "code"
    }
    static constraints = {
    
        code(blank : false, nullable : false, unique : true)
    
        tauxRemise(nullable : false)
    
        libelle(blank : false, nullable : false, unique : true)
    
        operateurComparaison(nullable : true)
    
        quantiteMin(nullable : true, min : 0d)
        
        quantiteMax(nullable : true, min : 1d)
    
        priorite(nullable : false, min : 1)
    
        valeurFixe(nullable : true)
    
        dateDebut(nullable : true)
    
        dateFin(nullable : true)
    
        active()
    
        typePrixdeBase(blank : false, nullable : false)
        
        typeRegle(nullable : false)
    
        arrondirApresVirguel()
    
        senseArrondissement(nullable : true)
    
        seuilArrondissement(nullable : true, range : 0.0..1.0)
    
    }
    
    @Override 
    boolean equals(final Object that) {         
        if(that == null || !(that instanceof ReglePrix)) {
            return false
        }      
        return that.code == code            
    }    
	
    @Override 
    int hashCode() { 
            HashCodeBuilder.reflectionHashCode(this, ["id"]) 
    } 
    
    // function pour retourner l'a difference'intersection entre deux listes d'objets reglePrix
    static def intersect(listResult, listObject) {
        if (listResult == null || listResult.size()<1 || listObject == null || listObject.size()<1 )
            return false
            
        def listResultfinal = []
        listResult.each { reglePrixBasic ->
            listObject.each {
                if (reglePrixBasic.code == it.code) {
                    listResultfinal.add(reglePrixBasic)
                }
            }
        }
        if (listResultfinal.size() > 0) {
            listResult = listResultfinal
            return true
        }
    }
    
    String toString() {
        return (code + " : " + typeRegle + "(" + priorite + ")")
    }
}

