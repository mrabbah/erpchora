package com.choranet.securite

import com.choranet.commun.ChoraClientInfo
import cr.co.arquetipos.password.PasswordTools
import cr.co.arquetipos.crypto.Blowfish 
import java.text.SimpleDateFormat
import com.choranet.commun.SuperService



class ChoraBarrageService extends SuperService {

    static transactional = true
    
    def grailsApplication
    def utilisateurService

    def verifierExistanceInstance() {
        def nb = ChoraBarrage.count()
        if(nb == 0) {
            def id = PasswordTools.generateRandomPassword(15)
            def cb = new ChoraBarrage(raisonSocial : 'CHORA INFORMATIQUE', idInstance : id, premierAcces : true, demo : true, dernierAcces : new Date(), dateInstanciation : new Date(), produit: grailsApplication.metadata['app.name'], versionProduit : grailsApplication.metadata['app.version'])
            save(cb)
        }
    }
    def list() throws Exception {
        return super.list(ChoraBarrage.class)
    }
   
    def isDemo() {
        def cb = getChoraBarrage()
        if(!cb.demo) {
            return false
        }
        def today = new Date()
        def nbJoursDemo = today - cb.dateInstanciation
        if(nbJoursDemo > 30) {
            cb.demo = false  
            update(cb);
            return false
        }
        return true
    }
    
    def getDateActivation() {
        if(Cle.count() > 0) {
            def criteria = Cle.createCriteria()
            //obtenir la derniere licence
            def cles = criteria.list {                
                maxResults(1)
                order("dateActivation", "desc")
            }
            def cle = cles[0]
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            def dexp = (Date) sdf.parse(cle.dateFinActivation)
            return (dexp + 1)
        } else {
            return new Date()
        }
    }
    
    def isHorlogeSystemNotHacked() {
        def today = new Date()
        def cb = getChoraBarrage()
        def dernierAcces = cb.dernierAcces
        def nbJours = today - dernierAcces
        return (nbJours < 0)
    }
    
    def isPremierAccess() {
        return (ChoraBarrage.count() == 0 )
    }
    
    def premierAccessEffectue(ChoraClientInfo cci) {
        if(cci != null) {
            def admin = utilisateurService.chercherUtilisateur("admin", "")
            if(admin && ChoraBarrage.count() == 0) {
                def id = PasswordTools.generateRandomPassword(15)
                def cb = new ChoraBarrage(raisonSocial : cci.raisonSociale, idInstance : id, premierAcces : false, demo : true, dernierAcces : new Date(), dateInstanciation : new Date(), produit: grailsApplication.metadata['app.name'], versionProduit : grailsApplication.metadata['app.version'])
                save(cb)     
                admin.passwd = Blowfish.encryptBase64("", id)
                utilisateurService.update(admin)
            }            
        }
    }
    
    def getNbJoursAvantExpiration() {
        def cb = getChoraBarrage()
        def today = new Date()
        //Dans le cas ou c une demo
        if(cb.demo) {
            def nbJoursDemo = 30 - (today - cb.dateInstanciation)
            return nbJoursDemo
        }
        //dans le cas ou il y a une licence
        if(Cle.count() > 0) {
            def criteria = Cle.createCriteria()
            //obtenir la derniere licence
            def cles = criteria.list {
                maxResults(1)
                order("dateActivation", "desc")
            }
            def cle = cles[0]
            def cleEnClaire = Blowfish.decryptBase64(cle.cleProduit, cb.idInstance)
            def valeurs = cleEnClaire.split("#")
            def valeur = new Integer(valeurs[5])
            if(valeur == -1) {
                return 999999
            }
            def dateExpiration = cle.dateActivation + valeur
            def nbJours = dateExpiration - today
             
            return nbJours
        }
        return 0
    }
    
    def actualiserDernierAccess() {
        try {
            def cb = getChoraBarrage()
            def now = new Date()
            if(cb.dernierAcces.before(now)) {
                cb.dernierAcces = now                
            }
            update(cb)
        } catch(Exception ex) {
            log.error(ex)
        }
    }
    
    def getChoraBarrage() {
        return ChoraBarrage.list()[0]
    }
}
