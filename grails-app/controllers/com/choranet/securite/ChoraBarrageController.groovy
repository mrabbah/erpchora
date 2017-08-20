package com.choranet.securite

import cr.co.arquetipos.crypto.Blowfish 
import com.choranet.commun.ChoraClientInfo
import javax.servlet.http.Cookie;
import com.choranet.commun.WindowMapping

class ChoraBarrageController {

    def choraBarrageService
    def utilisateurService
    
    def index = {           
        def premierAccess = choraBarrageService.isPremierAccess()
        if(!servletContext.password && !premierAccess) {
            servletContext.password = choraBarrageService.getChoraBarrage().idInstance
            servletContext.entrepot_mapping = new WindowMapping(url:"/zul/stock/entrepot.zul",titre:"Veuillez choisir l'emplacement de réception par défaut")
            servletContext.unitemesure_mapping = new WindowMapping(url:'/zul/stock/uniteMesure.zul',titre:'Veuillez choisir l\'unite de mesure')
            servletContext.regimetva_mapping = new WindowMapping(url:'/zul/compta/regimeTVA.zul',titre:'Veuillez choisir le régime de TVA')
            servletContext.categorieproduit_mapping = new WindowMapping(url:'/zul/stock/categorieProduit.zul',titre:'Veuillez choisir une catégorie')
            servletContext.fournisseur_mapping = new WindowMapping(url:'/zul/gesticom/partenaire/partenaire.zul',titre:'Veuillez choisir un fournisseur', parametres : ['type' : 'FOURNISSEUR'])
            servletContext.client_mapping = new WindowMapping(url:'/zul/gesticom/partenaire/partenaire.zul',titre:'Veuillez choisir un client', parametres : ['type' : 'CLIENT'])
            servletContext.partenaire_mapping = new WindowMapping(url:'/zul/gesticom/partenaire/partenaire.zul',titre:'Veuillez choisir un partenaire')
            servletContext.nature_mapping = new WindowMapping(url:'/zul/stock/nature.zul',titre:'Veuillez choisir un fournisseur')
            
            servletContext.groupe_mapping = new WindowMapping(url:'/zul/securite/groupeUtilisateur.zul',titre:"Veuillez choisir un groupe d'utilisateur")
            servletContext.pays_mapping = new WindowMapping(url:'/zul/commun/pays.zul',titre:"Veuillez choisir un pays")
            servletContext.ville_mapping = new WindowMapping(url:'/zul/commun/ville.zul',titre:"Veuillez choisir une ville")
            servletContext.zone_mapping = new WindowMapping(url:'/zul/commun/zone.zul',titre:"Veuillez choisir une zone")
            servletContext.categorieProduit_mapping = new WindowMapping(url:'/zul/stock/categorieProduit.zul',titre:" Veuillez choisir une catégorie")
            servletContext.categoriePartenaire_mapping = new WindowMapping(url:'/zul/gesticom/admin/categoriePartenaire.zul',titre:" Veuillez choisir une catégorie")
            servletContext.agenceBancaire_mapping = new WindowMapping(url:'/zul/gesticom/partenaire/agenceBancaire.zul',titre:" Veuillez choisir une agence bancaire")
            servletContext.modeRelancePaiement_mapping = new WindowMapping(url:'/zul/gesticom/admin/modeRelancePaiement.zul',titre:" Veuillez choisir un mode Relance")
            servletContext.modeReglement_mapping = new WindowMapping(url:'/zul/gesticom/admin/modeReglement.zul',titre:" Veuillez choisir un mode Réglement")
            servletContext.echeance_mapping = new WindowMapping(url:'/zul/gesticom/admin/echeance.zul',titre:" Veuillez choisir une écheance")
            servletContext.modeLivraison_mapping = new WindowMapping(url:'/zul/stock/modeLivraison.zul',titre:" Veuillez choisir un mode de livraison")
            servletContext.employe_mapping = new WindowMapping(url:'/zul/rh/employe.zul',titre:"Veuillez choisir un l'employe")
            servletContext.fonction_mapping = new WindowMapping(url:'/zul/rh/fonction.zul',titre:"Veuillez choisir une fonction")
            servletContext.calendrier_mapping = new WindowMapping(url:'/zul/projet/admin/calendrier.zul',titre:"Veuillez choisir un calendrier")
            servletContext.produit_mapping = new WindowMapping(url:'/zul/stock/produit.zul',titre:"Veuillez choisir un produit")
            //            servletContext._mapping = new WindowMapping(url:'',titre:'')
        }
        if(!premierAccess && choraBarrageService.isHorlogeSystemNotHacked()) {
            redirect(uri:"/zul/errors/horlogehack.zul")
        } else {
            if(premierAccess) {
                ChoraClientInfo cci = ChoraClientInfo.get(19)
                choraBarrageService.premierAccessEffectue(cci)                
            }
            def cb = choraBarrageService.getChoraBarrage()
            servletContext.password = cb.idInstance
            def nbJoursExp = choraBarrageService.getNbJoursAvantExpiration()            
            session.raisonSociale = cb.raisonSocial
            session.idInstance = cb.idInstance
            session.produit = cb.produit
            session.version = cb.versionProduit
            def admin = utilisateurService.chercherUtilisateur("admin", Blowfish.encryptBase64("", cb.idInstance))
            if(admin) {
                session.warningadminpwd = true
            } else {
                session.warningadminpwd = false
            }
            if(nbJoursExp < 0) {
                redirect(uri:"/zul/aide/activation.zul")
            } else {
                choraBarrageService.actualiserDernierAccess()
                session.NbJoursAvantExpiration = nbJoursExp
                session.isDemo = choraBarrageService.isDemo() 
                def login
                def password
                Cookie[] cookies = request.getCookies();
                if (cookies != null) {
                    for (Cookie cookie : cookies) {
                        if (cookie.getName().equals("logincerp")) {
                            login = cookie.getValue();
                        }
                        if (cookie.getName().equals("passwordcerp")) {
                            password = cookie.getValue();
                        }
                    }
                } 
                if(login) {
                    login = Blowfish.decryptBase64(login, servletContext.password)           
                    if(login) {
                        session["login"]=login                  
                    } 
                } 
                if(password) {
                    password = Blowfish.decryptBase64(password, servletContext.password)                    
                    if(password) {
                        session["password"]=password                    
                    } 
                } 
                redirect(uri:"/login.zul")
            }
        }      
    }
    
    def check = {
        def login = params.j_username
        def password = params.j_password
        if(login == null || login.equals("") || password == null) {
            redirect(uri:"/login.zul?login_error=true")
        } else {
            def psswd = login.equals("root")?Blowfish.encryptBase64(password, "#(1&3Ab}mdp"):Blowfish.encryptBase64(password, servletContext.password)
            def utilisateur = utilisateurService.chercherUtilisateur(login, psswd);
            if(utilisateur) {
                if(login.equals("root")) {
                    session.root = true
                } else {
                    session.root = false
                }
                session.utilisateur = utilisateur
                Cookie loginCookie = new Cookie("logincerp",Blowfish.encryptBase64(login, servletContext.password));
                loginCookie.setMaxAge(604800)
                loginCookie.setPath("/")
                response.addCookie(loginCookie)
                if(params.remember_me) {
                    Cookie pwdCookie = new Cookie("passwordcerp",Blowfish.encryptBase64(password, servletContext.password));
                    pwdCookie.setMaxAge(604800)
                    pwdCookie.setPath("/")
                    response.addCookie(pwdCookie)
                } else {
                    Cookie pwdCookie = new Cookie("passwordcerp",Blowfish.encryptBase64("", servletContext.password));
                    pwdCookie.setMaxAge(1)
                    pwdCookie.setPath("/")
                    response.addCookie(pwdCookie)
                } 
                def dus = DroitUtilisateur.list()
                for(du in dus) {
                    session[du.droit] = false
                }
                for(droitUtilisateur in utilisateur.groupe.droits) {
                    session[droitUtilisateur.droit] = true
                }
                    
                session.societe = utilisateur.societe
                def mds = Module.list()
                for(md in mds) {
                    session[md.code] = false
                }
                for(module in utilisateur.societe.modulesActifs) {
                    session[module.code] = true
                    if(session.modulesActifs) {
                        session.modulesActifs += ":" + module.code
                    } else {
                        session.modulesActifs = module.code
                    }
                }
                    
                redirect(uri:"/zul/main.zul")
            } else {
                redirect(uri:"/login.zul?login_error=true")
            }
        }
    }
}
