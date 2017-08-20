
package com.choranet.gesticom;

import com.choranet.commun.SuperService

/**
 * ContactService Service pour la gestion des opérations
 * transactionnelles pour l'objet Contact
 */
class ContactService extends SuperService {

    static transactional = true

    def list() throws Exception {
        return super.list(Contact.class)
    }
        
    def getContactPartenaire(partner){
        def c = Contact.createCriteria()
        def contacts = c.list {
            partenaire {
                eq('code', partner.code)
            }
        } 
        return contacts
    }
}