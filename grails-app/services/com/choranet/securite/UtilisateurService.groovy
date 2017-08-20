package com.choranet.securite

import com.choranet.commun.SuperService

class UtilisateurService extends SuperService {

    static transactional = true

    
    def list() throws Exception {
        return super.list(Utilisateur.class)
    }
    
    def chercherUtilisateur(login, psswd) {
        def c = Utilisateur.createCriteria()
        def user = c.get {
            eq('username', login)
            eq('passwd', psswd)
            eq('enabled', true)
        }
        return user
    } 

    def getUtilisateursDuGroupe(grp) {
        def c = Utilisateur.createCriteria()
        def result = c.list {
            groupe {
                eq("id", grp.id)
            }
        }
        return result
    }
}
