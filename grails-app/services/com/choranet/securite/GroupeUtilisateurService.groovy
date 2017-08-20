package com.choranet.securite

import com.choranet.commun.SuperService

class GroupeUtilisateurService extends SuperService {

    static transactional = true

    def list() throws Exception {
        return super.list(GroupeUtilisateur.class)
    }
    
    def getGroupeByAuthority(authority) throws Exception {
        //return GroupeUtilisateur.findByAuthority(authority)
        return GroupeUtilisateur.findByIntitule(authority)
    }
}
