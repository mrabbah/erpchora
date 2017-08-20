

package com.choranet.stock


import org.apache.commons.lang.builder.*
/**
 * Inventaire Domain Object 
 */
class Inventaire {	
    
    Date dateDebut
    Date dateFin
    String details	
    String etat
       		   
    static belongsTo = [entrepot : Entrepot]
    
    static hasMany = [ligneInventaires : LigneInventaire]
    
    static mapping = { 
        entrepot fetch : 'join'
        //ligneInventaires lazy : false, cascade:"all-delete-orphan", batchSize : 50
        //batchSize: 14 
        sort dateDebut:"desc"
    }
    static constraints = {
        dateDebut(nullable : false)    
        dateFin(nullable : true)    
        details(nullable : true, maxSize : 1000)
        etat(nullable : false, inList : ["EN COURS", "SUSPENDU", "ACHEVE", "VALIDE"])
        entrepot(nullable : false)
        ligneInventaires(nullable : true)
    }
	
    String toString() {
        String result = "Inventaire effectué entre le " + dateDebut
        if(dateFin != null) {
            result += " et le " + dateFin
        }
        return  result
    }
    
    @Override 
    boolean equals(final Object that) {         
        if(that == null || !(that instanceof Inventaire)) {
            return false
        }      
        return that.id == id            
    }   
    
    @Override 
    int hashCode() { 
            HashCodeBuilder.reflectionHashCode(this, ["id"]) 
    } 
}

