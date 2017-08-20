package com.choranet.securite

import org.zkoss.zul.*
import org.zkoss.zkplus.databind.*
import org.apache.commons.logging.Log 
import org.apache.commons.logging.LogFactory
import com.choranet.commun.SuperService
import com.choranet.commun.SuperWindow
import cr.co.arquetipos.crypto.Blowfish 
import com.choranet.commun.ChoraClientInfo


/**
 * Utilisateur Window Object
 **/
class UtilisateurWindow extends SuperWindow {
    
    def utilisateurService
    /**
     * Logger de la class UtilisateurWindow
     **/
    private Log logger = LogFactory.getLog(UtilisateurWindow.class)
	
    def societes
    /**
     * Constructeur
     **/
    public UtilisateurWindow () {
        super(Utilisateur.class, 14, ["societe","groupe","username", "userRealName", "enabled", "email"])
        if(!session.utilisateur.username.equals("root")) {
            filtre.societe = session.societe
            listeObjets = Utilisateur.findAllBySociete(session.societe)
        }
        filtre.enabled = true        
        societes = ChoraClientInfo.findAllByRaisonSocialeNotEqual("CHORANET")
        if(societes.size() > 0) {
            objet.societe = societes.get(0)
        }
    }   
    
    def add() {
        objet.passwd = Blowfish.encryptBase64(objet.pass, servletContext.password)
        super.add()
    }
    def update() {
        if(objet.pass != '[secret]') {
            objet.passwd = Blowfish.encryptBase64(objet.pass, servletContext.password)
        }
        super.update()
    }
    /**
     * Activer ou dï¿½sactiver les boutons d'ajout, suppression, modfication
     **/
    def activerBoutons(visible) {
        this.getFellow("btnUpdate").visible = visible
        //this.getFellow("btnDelete").visible = visible
        this.getFellow("btnCancel").visible = visible
        this.getFellow("btnSave").visible = !visible
        this.getFellow("btnNew").visible = !visible
        this.getFellow("westPanel").open = visible        
    }
    
    protected SuperService getService() {
        return utilisateurService
    }
}

