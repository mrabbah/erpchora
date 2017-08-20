package com.choranet.securite

import org.zkoss.zul.*
import org.zkoss.zkplus.databind.*
import org.apache.commons.logging.Log 
import org.apache.commons.logging.LogFactory
import com.choranet.commun.*


/**
 * GroupeUtilisateur Window Object
 **/
class ActivationWindow extends Window {
    
    def modules
    def societe
    def societes
    def choraClientInfoService
    /**
     * Logger de la class ActivationWindow
     **/
    private Log logger = LogFactory.getLog(ActivationWindow.class)
	
    /**
     * Constructeur
     **/
    public ActivationWindow () {
        societes = ChoraClientInfo.findAllByRaisonSocialeNotEqual("CHORANET")
        societe = societes.get(0)
        modules = Module.list()
    }   
        
    def societeChanger() {
        
    }
    
    def update() {
        choraClientInfoService.update(societe)
    }
}

