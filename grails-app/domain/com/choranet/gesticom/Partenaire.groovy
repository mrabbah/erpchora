
package com.choranet.gesticom


import com.choranet.stock.ModeLivraison
import com.choranet.commun.FormeJuridique
import com.choranet.commun.Zone
import com.choranet.commun.Ville
import com.choranet.rh.Employe
import org.apache.commons.lang.builder.*
/**
 * Partenaire Domain Object 
 */
class Partenaire {	
    
    String code	
    	
    String raisonSociale	
    	
    String idFiscal	
    	
    String patente	
    	
    String registreCommerce	
    	
    String cnss	
    	
    String adresse	
    
    String codePostal
    	
    String telephone	
    	
    String fax	
    	
    String email	
    	
    String siteWeb	
    	
    String adresseFacturation	
    	
    String adresseLivraison	
	
    Boolean estClient	
    	
    Boolean estFournisseur	
    	
    Boolean estParticulier	
    
    Boolean estBlacklist = false
    
    Double plafond
       		   
    Double solde
    
    Boolean compteVerrouile
       		   
    static belongsTo = [modeRelancePaiement : ModeRelancePaiement, modeReglementDefaut : ModeReglement, echeance : Echeance, 
                        zone : Zone, ville : Ville, formeJuridique : FormeJuridique, modeLivraison : ModeLivraison, commercial : Employe]
    
    static hasMany = [categoriePartenaires : CategoriePartenaire]
    
    static mapping = { 
        //categoriePartenaires fetch : 'join'
        //modeRelancePaiement fetch : 'join'
        //modeReglementDefaut fetch : 'join'
        //echeance fetch : 'join'
        //ville fetch : 'join'
        //formeJuridique fetch : 'join'
        //modeLivraison fetch : 'join'
        //batchSize: 100
        sort "raisonSociale"
    }
    static constraints = {

        code(blank : false, nullable : false, unique : true)
        raisonSociale(blank : false, nullable : false, unique : true)
        idFiscal(nullable : true)
        patente(nullable : true)
        registreCommerce(nullable : true)
        cnss(nullable : true)
        adresse(nullable : true)
        codePostal(nullable : true)
        telephone(nullable : true)
        fax(nullable : true)
        email(nullable : true)
        siteWeb(nullable : true)
        adresseFacturation(nullable : true)
        adresseLivraison(nullable : true)
        categoriePartenaires(nullable : true)
        modeRelancePaiement(nullable : true)
        modeReglementDefaut(nullable : true)
        echeance(nullable : true)
        zone(nullable : true)
        ville(nullable : true)
        formeJuridique(nullable : true)
        modeLivraison(nullable : true)
        plafond(nullable : false)
        solde(nullable : false)
        compteVerrouile(nullable : false)
        commercial(nullable : true)
    }

    @Override 
    boolean equals(final Object that) {         
        if(that == null || !(that instanceof Partenaire)) {
            return false
        }      
        return that.code == code            
    }    

    @Override 
    int hashCode() { 
            HashCodeBuilder.reflectionHashCode(this, ["id"]) 
    } 
    
    // function pour retourner la difference entre deux listes d'objets Partenaire
    static def removeAll(listResult, listObject) {
        if (listResult == null || listResult.size()<1 || listObject == null || listObject.size()<1 )
        return false
            
        def listResultfinal = listResult.toList()
        listResultfinal.each { PrtBasic ->
            listObject.each {
                if (PrtBasic.code == it.code) {
                    listResult.remove(PrtBasic)
                }
            }
        }
        if (listResultfinal.size() > listResult.size()) {
            listResult = listResultfinal
            return true
        }
    }
   
    String toString() {
        return raisonSociale
    }
}

