
package com.choranet.projet

import com.choranet.gesticom.BonCommande;
import com.choranet.commun.DocumentNumerique
import com.choranet.rh.Employe;
import org.apache.commons.lang.builder.*
/**
 * Projet Domain Object 
 */
class Projet {	
   
    static auditable = true
    
    Integer numero	
    String nom	
    Date dateDebutPrevu	
    Date dateDebutReelle	
    Date dateFinPrevue	
    Date dateFinReelle	
    Double chargePrevue	
    Double chargeReelle	
    Double avancementPrevu	
    Double avancementReel	
    String adresseProjet	
    String codePostal	
    String ville	
    String fax	
    String telephone	
    String email	
    String nomContact	
    Double montantBonCommande	
     

    static belongsTo = [calendrier : Calendrier , chefChantier : Employe, bonCommande : BonCommande]
    static hasMany = [documents: DocumentNumerique]
    
    static mapping = { 
        //calendrier lazy : false
        //chefChantier lazy : false
        //bonCommande lazy : false
        //documents lazy : false
        //batchSize: 14
        sort numero:"desc"
    }
    static constraints = {
        numero(min : 1, nullable : false)
        nom(blank : false, nullable : false)
        dateDebutPrevu(nullable : true)
        dateDebutReelle(nullable : false)
        dateFinPrevue(nullable : true)
        dateFinReelle(nullable : true)
        chargePrevue(min : 0d, nullable : true)
        chargeReelle(min : 0d, nullable : true)
        avancementPrevu(range : 0.0..100.0, nullable : true, scale : 2)
        avancementReel(range : 0.0..100.0, nullable : false, scale : 2)
        adresseProjet(nullable : true)
        codePostal(nullable : true)
        ville(nullable: true)
        fax(nullable : true)
        telephone(nullable : true)
        email(email : true, nullable : true)
        nomContact(nullable : true)
        montantBonCommande(min : 0d, nullable : false)
        calendrier(nullable : false)
        chefChantier(nullable : false)
        bonCommande(nullable : true)
        documents(nullable : true)
    }
	
    String toString() {
        return nom
    }
    
    @Override 
    boolean equals(final Object that) {         
        if(that == null || !(that instanceof Projet)) {
            return false
        }      
        return that.id == id            
    }    
    
    @Override 
    int hashCode() { 
            HashCodeBuilder.reflectionHashCode(this, ["id"]) 
    } 
}

