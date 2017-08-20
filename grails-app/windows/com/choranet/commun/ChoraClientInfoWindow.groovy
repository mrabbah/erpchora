
package com.choranet.commun


import org.zkoss.zul.*
import org.zkoss.zkplus.databind.*
import org.apache.commons.logging.Log 
import org.apache.commons.logging.LogFactory

import org.zkoss.image.Image
import org.zkoss.zk.ui.Executions
import com.choranet.gesticom.CompteBancaireChoraClientInfo
import com.choranet.gesticom.AgenceBancaire
import org.springframework.web.context.request.RequestContextHolder
import org.zkoss.zk.ui.Executions
import com.choranet.compta.RegimeDeclarationTva

/**
 * ClientInfo Window Object
 **/
class ChoraClientInfoWindow extends Window {
        
    def objet
    /**
     * Service pour la gestion de l'objet Voiture
     **/
    def choraClientInfoService
    def choraBarrageService
    def compteBancaireChoraClientInfoService
    def listeAgences
    def agence
    def isUpdateAgence
    def agenceSelected
    def listeComptes
    def compte
    def isUpdateCompte
    def compteSelected
    def agenceBancaires
    def agenceBancaireSelected
    def agenceBancaireService
    def oldSold
    def regimes
    /**
     * Logger de la class VoitureWindow
     **/
    private Log logger = LogFactory.getLog(ChoraClientInfoWindow.class)
	

    /**
     * Constructeur
     **/
    public ChoraClientInfoWindow (securise) {
        if(securise) {
            def session = RequestContextHolder.currentRequestAttributes().getSession()            
            if(!session.utilisateur) {
                Executions.sendRedirect("/"); 
            } else {
                regimes = RegimeDeclarationTva.list()
                objet = session.societe  
                objet.regime = regimes.find{ it.id == objet.regime.id}
            }
        } else {
            regimes = RegimeDeclarationTva.list()
            objet = new ChoraClientInfo()
            objet.repertoirBackup = System.getProperty('user.home')
        }
    }  
   
    def updateLogo(Object media) {
        if (media != null) {                   
            
            if (media instanceof Image) {
                this.getFellow("appercuLogo").setContent(media);
                objet.setTrans_logo((Image) media);
            } else {
                Messagebox.show("Le fichier choisi n'est pas une image: " + media, "Erreur",
                    Messagebox.OK, Messagebox.ERROR);
                //break; //not to show too many errors
            }

        } else {
            Messagebox.show("Impossible d'envoyer le fichier choisi: " + media, "Erreur",
                Messagebox.OK, Messagebox.ERROR);
        }
    }
    
    def updateCachet(Object media) {
        if (media != null) {                   
            
            if (media instanceof Image) {
                this.getFellow("appercuCachet").setContent(media);
                objet.setTrans_cachet((Image) media);
            } else {
                Messagebox.show("Le fichier choisi n'est pas une image: " + media, "Erreur",
                    Messagebox.OK, Messagebox.ERROR);
                //break; //not to show too many errors
            }

        } else {
            Messagebox.show("Impossible d'envoyer le fichier choisi: " + media, "Erreur",
                Messagebox.OK, Messagebox.ERROR);
        }
    }
    
    def updateEntetepied(Object media) {
        if (media != null) {                   
            
            if (media instanceof Image) {
                this.getFellow("appercuEntetepied").setContent(media);
                objet.setTrans_entetepied((Image) media);
            } else {
                Messagebox.show("Le fichier choisi n'est pas une image: " + media, "Erreur",
                    Messagebox.OK, Messagebox.ERROR);
                //break; //not to show too many errors
            }

        } else {
            Messagebox.show("Impossible d'envoyer le fichier choisi: " + media, "Erreur",
                Messagebox.OK, Messagebox.ERROR);
        }
    }
    
    def updateCopie(Object media) {
        if (media != null) {                   
            
            if (media instanceof Image) {
                this.getFellow("appercuCopie").setContent(media);
                objet.setTrans_copie((Image) media);
            } else {
                Messagebox.show("Le fichier choisi n'est pas une image: " + media, "Erreur",
                    Messagebox.OK, Messagebox.ERROR);
                //break; //not to show too many errors
            }

        } else {
            Messagebox.show("Impossible d'envoyer le fichier choisi: " + media, "Erreur",
                Messagebox.OK, Messagebox.ERROR);
        }
    }
    
    /**
     * Fonction qui se charge de mettre ? jour un ?l?ment selectionn? de article
     **/
    def update() {
        try {
            choraClientInfoService.update(objet)
        }
        catch(Exception e) {
            logger.error "Error: ${e.message}", e
            Messagebox.show("Probleme lors de la mise à jour", "Erreur", Messagebox.OK, Messagebox.ERROR)
        }
        finally {
            rafraichirField()
        }  

    }
    
    /**
     * Fonction qui se charge de mettre ? jour un ?l?ment selectionn? de article
     **/
    def enregistrer() {
        try {
            if(!objet.trans_copie) {
                objet.setTrans_copie(null)                
            }
            if(!objet.trans_cachet) {
                objet.setTrans_cachet(null)                
            }
            if(!objet.trans_entetepied) {
                objet.setTrans_entetepied(null)                
            }
            objet = choraClientInfoService.save(objet)
            choraBarrageService.premierAccessEffectue(objet)
            Executions.sendRedirect("/")
        }
        catch(Exception e) {
            logger.error "Error: ${e.message}", e
            Messagebox.show("Probleme lors de l'enregistrement", "Erreur", Messagebox.OK, Messagebox.ERROR)
        }
        finally {
            rafraichirField()
        }  

    }
    
    /**
     * R?initialiser les champs du formulaire
     **/
    def rafraichirField() {
        this.getFellows().each { co ->
            if(co.getId() != null && co.getId().startsWith("field")) {
                def binder = new AnnotateDataBinder(co)
                binder.loadAll()
            }
        }               
    }
    
   
    def selectAgence() {  
        isUpdateAgence = true
        agence = agenceSelected        
        rafraichirField()
    }
    
    def addAgence() {
        try {     
            agenceBancaireService.save(agence)
        } catch(Exception ex) {
            logger.error "Error: ${ex.message}", ex
            Messagebox.show("La nouvelle entré est déjà supportée par le système\n", "Ajout non disponible", Messagebox.OK, Messagebox.ERROR)
        } finally {
            agence = new AgenceBancaire()
            rafraichirField()
            rafraichirListAgences()
        }
        
    }
    
    def addOrUpdateAgence(){
        if (isUpdateAgence) {
            this.updateAgence()
            isUpdateAgence = false
        } else {
            this.addAgence()
        }
    }
    def updateAgence() {
        try {
            agenceBancaireService.update(agence)
        }
        catch(Exception e) {
            logger.error "Error: ${e.message}", e
            Messagebox.show("La nouvelle valeur est dèjà mentionnée pour une agance existante", "Mise à jour non disponible", Messagebox.OK, Messagebox.ERROR)
        }
        finally {
            agence = new AgenceBancaire()
            rafraichirField()
            rafraichirListAgences()
        }
        
    }

    def deleteAgence() {
        try{
            agenceBancaireService.delete(agence)
        }
        catch(Exception e) {
            Messagebox.show("La présente agence est déjà associée à un compte bancaire\n", "Suppression non disponible", Messagebox.OK, Messagebox.ERROR)
        }
        agence = new AgenceBancaire()
        rafraichirField()
        rafraichirListAgences()
    }
    
    def rafraichirListAgences() {
        listeAgences = AgenceBancaire.list()
        agenceSelected = null
        def lst = getFellow("lstAgences")
        lst.clearSelection()
        new AnnotateDataBinder(lst).loadAll() 
    }
    
    def chargerAgences() {        
        agence = new AgenceBancaire()
        isUpdateAgence = false
        rafraichirListAgences()
    }
    /**********************************************/
    def rafraichirAssociation() {
        agenceBancaireSelected = agenceBancaires.find{it.id == compteSelected.agenceBancaire.id}
        new AnnotateDataBinder(getFellow("coagenceBancaires")).loadAll() 
    }
    def selectCompte() {  
        isUpdateCompte = true
        compte = compteSelected
        oldSold = compte.solde
        rafraichirField()
        rafraichirAssociation()
    }
    
    def addCompte() {
        try {    
            compte.soldePrevu = compte.solde
            compte.agenceBancaire = agenceBancaireSelected
            compteBancaireChoraClientInfoService.save(compte)
        } catch(Exception ex) {
            logger.error "Error: ${ex.message}", ex
            Messagebox.show("Veillez renseignez toutes les valeurs requises par le système\n", "Ajout non disponible", Messagebox.OK, Messagebox.ERROR)
        } finally {
            compte = new CompteBancaireChoraClientInfo()
            rafraichirField()
            rafraichirListComptes()
        }
        
    }
    
    def addOrUpdateCompte(){
        if (isUpdateCompte) {
            this.updateCompte()
            isUpdateCompte = false
        } else {
            this.addCompte()
        }
    }
    
    def updateCompte() {
        try {
            compte.agenceBancaire = agenceBancaireSelected
            if(oldSold) {
                def diff = compte.solde - oldSold
                compte.soldePrevu += diff
            }
            compteBancaireChoraClientInfoService.update(compte)
        }
        catch(Exception e) {
            logger.error "Error: ${e.message}", e
            Messagebox.show("La nouvelle valeur est dèjà mentionnée pour une agance existante", "Mise à jour non disponible", Messagebox.OK, Messagebox.ERROR)
        }
        finally {
            compte = new CompteBancaireChoraClientInfo()
            rafraichirField()
            rafraichirListComptes()
        }
        
    }
   
    def deleteCompte() {
        try{
            compteBancaireChoraClientInfoService.delete(compte)
        }
        catch(Exception e) {
            Messagebox.show("La présente compte est déjà associée à un compte bancaire\n", "Suppression non disponible", Messagebox.OK, Messagebox.ERROR)
        }
        compte = new CompteBancaireChoraClientInfo()
        rafraichirField()
        rafraichirListComptes()
    }
    
    def rafraichirListComptes() {
        listeComptes = CompteBancaireChoraClientInfo.list()
        compteSelected = null
        def lst = getFellow("lstComptes")
        lst.clearSelection()
        new AnnotateDataBinder(lst).loadAll() 
        agenceBancaireSelected = null
        new AnnotateDataBinder(getFellow("coagenceBancaires")).loadAll() 
    }
    
    def chargerComptes() {    
        agenceBancaires = AgenceBancaire.list()
        agenceBancaireSelected = null
        compte = new CompteBancaireChoraClientInfo()
        isUpdateCompte = false
        rafraichirListComptes()
        new AnnotateDataBinder(getFellow("coagenceBancaires")).loadAll() 
    }
}
