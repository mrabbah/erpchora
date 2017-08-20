package com.choranet.securite

import com.choranet.commun.ChoraClientInfo
import org.apache.commons.lang.builder.*

/**
 * User domain class.
 */
class Utilisateur {
    
    static transients = ['pass']
    
    /** Username */
    String username
    /** User Real Name*/
    String userRealName
    /** MD5 Password */
    String passwd
    /** enabled */
    boolean enabled

    String email

    /** plain password to create a MD5 password */
    String pass = '[secret]'

    GroupeUtilisateur groupe
    
    ChoraClientInfo societe
    
    static constraints = {
        username(blank: false, unique: true)
        userRealName(blank: false)
        passwd(blank: false)
        enabled()
        groupe(nullable : false)
        societe(nullable : false)
    }

    static mapping = {
        //groupe lazy : false
        //societe lazy : false
        cache usage : 'nonstrict-read-write'
        //batchSize: 10
        sort "username"
    }
    String toString() {
        return username
    }
    
    @Override 
    boolean equals(final Object that) {         
        if(that == null || !(that instanceof Utilisateur)) {
            return false
        }      
        return that.username == username            
    }   

    @Override 
    int hashCode() { 
            HashCodeBuilder.reflectionHashCode(this, ["username"]) 
    } 
}
