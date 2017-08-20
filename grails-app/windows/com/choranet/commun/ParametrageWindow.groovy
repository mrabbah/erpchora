/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.choranet.commun

import org.zkoss.zul.*
import org.zkoss.zkplus.databind.*
import org.apache.commons.logging.Log 
import org.apache.commons.logging.LogFactory

import org.zkoss.util.media.AMedia;
import org.zkoss.util.media.Media;
import java.util.HashMap;
import java.util.Map;
import org.springframework.web.context.request.RequestContextHolder
import org.zkoss.zk.ui.Executions

/**
 *
 * @author mohamed
 * ParametrageWindow Window Object
 **/

class ParametrageWindow extends Window {
    
    def objet
    /**
     * Service pour la gestion de l'objet Parametrage
     **/
    def parametrageService
    
    /**
     * session courante
     * */
    def session
    
    /**
     * servelet Application
     * */
    def servletContext
    
    /**
     * Logger de la class DeclarationTvaWindow
     **/
    private Log logger = LogFactory.getLog(ParametrageWindow.class)
             
    /**
     * Constructeur
     **/
    public ParametrageWindow (securise, parametrageService) {
        session = RequestContextHolder.currentRequestAttributes().getSession()
        servletContext = Executions.getCurrent().getDesktop().getWebApp()
        if(securise && !session.utilisateur) {
            Executions.sendRedirect("/"); 
        }
        this.parametrageService = parametrageService
        objet = parametrageService.getConfig()
    }  

    def rafraichirField() {
        this.getFellows().each { co ->
            if(co.getId() != null && co.getId().startsWith("field")) {
                def binder = new AnnotateDataBinder(co)
                binder.loadAll()
            }
        }               
    }
    
    def update() {
        try {
            objet = parametrageService.update(objet)
        }
        catch(Exception e) {
            println  "Error: ${e.message}"
            logger.error "Error: ${e.message}", e
            Messagebox.show("Problème lors de la mise à jour", "Erreur", Messagebox.OK, Messagebox.ERROR)
        }
        finally {
         //   rafraichirField()          
        }
    }
}



