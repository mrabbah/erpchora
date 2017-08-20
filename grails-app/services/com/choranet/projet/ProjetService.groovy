
package com.choranet.projet;

import com.choranet.commun.SuperService
import com.choranet.rh.FichePresence

/**
 * ProjetService Service pour la gestion des opérations
 * transactionnelles pour l'objet Projet
 */
class ProjetService extends SuperService {

    static transactional = true
    
    def list() throws Exception {
        return super.list(Projet.class)
    }
    
    def getProjetsTerminer() {
        def c = Projet.createCriteria()
        def result = c.list {
            isNotNull("dateFinReelle")
        }
        return result
    }
    
    def getProjetsNonTerminer() {
        def c = Projet.createCriteria()
        def result = c.list {
            isNull("dateFinReelle")
        }
        return result
    }
    
    def getProjetsNonTerminerCinqDerniers() {
        def c = Projet.createCriteria()
        def result = c.list {
            isNull("dateFinReelle")
            maxResults(5)
            order("dateDebutReelle", "desc")
        }
        return result
        
    }
    
    def getProjetsCinqDerniers() {
        def c = Projet.createCriteria()
        def result = c.list {
            //   isNull("dateFinReelle")
            maxResults(5)
            order("dateDebutReelle", "desc")
        }
        return result
    }
    
    def mettreAjourChargesReellesProjet(Projet pjt) {
        def c1 = getSommeChargesAffectationOutil(pjt);
        def c2 = getSommeChargesAffectationProduit(pjt);
        def c3 = getSommeChargesChargeDivers(pjt);
        def c4 = getSommeChargesEmployes(pjt);
        def c5 = getSommeChargesLocationOutil(pjt);
        def c6 = getSommeChargesSoustraitance(pjt);
        
        pjt.chargeReelle = c1 + c2 + c3 + c4 + c5 + c6;
        
        this.update(pjt);
    }
    
        
    def getChargesAffectationOutil(Projet prj) {
        def c = AffectationOutil.createCriteria()
        def result = c.list {
            projet {
                eq("numero", prj.numero)
            }
        }
        return result
    }
        
    def getSommeChargesAffectationOutil(Projet prj) {
        def c = AffectationOutil.createCriteria()
        def result = c.get {
            projet {
                eq("numero", prj.numero)
            }
            projections {
                sum("total")
            }
        }
        if(result == null) {
            result = 0;
        }
        return result
    }
     
    def getChargesAffectationProduit(Projet prj) {
        def c = AffectationProduit.createCriteria()
        def result = c.list {
            projet {
                eq("numero", prj.numero)
            }
            projections {
                groupProperty "produit.id" , "id"
                property "produit", "produit"
                sum "quantite", "quantite"
                sum "total", "total"
            }
            order("produit.id", "desc")
        }
        def resultat = []
        result.each {
            AffectationProduitDTO ap = new AffectationProduitDTO()
            ap.setProduit(it[1]);
            ap.setQuantite(it[2]);
            ap.setTotal(it[3]);
            resultat.add(ap);
        }
        return resultat
    }
        
    def getSommeChargesAffectationProduit(Projet prj) {
        def c = AffectationProduit.createCriteria()
        def result = c.get {
            projet {
                eq("numero", prj.numero)
            }
            projections {
                sum("total")
            }
        }
        if(result == null) {
            result = 0;
        }
        return result
    }
    def getChargesChargeDivers(Projet prj) {
        def c = ChargeDivers.createCriteria()
        def result = c.list {
            projet {
                eq("numero", prj.numero)
            }
        }
        return result
    }
        
    def getSommeChargesChargeDivers(Projet prj) {
        def c = ChargeDivers.createCriteria()
        def result = c.get {
            projet {
                eq("numero", prj.numero)
            }
            projections {
                sum("cout")
            }
        }
        if(result == null) {
            result = 0;
        }
        return result
    }
    def getChargesEmployes(Projet prj) {
        def c = FichePresence.createCriteria()
        def result = c.list {
            projet {
                eq("numero", prj.numero)
            }
            projections {
                groupProperty "employe.id" , "id"
                property "employe", "employe"
                sum "heuresTravail", "heuresTravail"
                sum "heuresAbscence", "heuresAbscence"
                sum "total", "total"
            }
        }
        def resultat = []
        result.each {
            ChargeEmployeDTO ce = new ChargeEmployeDTO();
            ce.setEmploye(it[1]);
            ce.setHeuresTravail(it[2]);
            ce.setHeuresAbscence(it[3]);
            ce.setTotal(it[4]); 
            resultat.add(ce)
        }
        
        return resultat
    }
        
    def getSommeChargesEmployes(Projet prj) {
        def c = FichePresence.createCriteria()
        def result = c.get {
            projet {
                eq("numero", prj.numero)
            }
            projections {
                sum("total")
            }
        }
        if(result == null) {
            result = 0;
        }
        return result
    }
    
    def getChargesLocationOutil(Projet prj) {
        def c = LocationOutil.createCriteria()
        def result = c.list {
            projet {
                eq("numero", prj.numero)
            }
        }
        return result
    }
        
    def getSommeChargesLocationOutil(Projet prj) {
        def c = LocationOutil.createCriteria()
        def result = c.get {
            projet {
                eq("numero", prj.numero)
            }
            projections {
                sum("total")
            }
        }
        if(result == null) {
            result = 0;
        }
        return result
    }
        
    def getChargesSoustraitance(Projet prj) {
        def c = Soustraitance.createCriteria()
        def result = c.list {
            projet {
                eq("numero", prj.numero)
            }
        }
        return result
    }
        
    def getSommeChargesSoustraitance(Projet prj) {
        def c = Soustraitance.createCriteria()
        def result = c.get {
            projet {
                eq("numero", prj.numero)
            }
            projections {
                sum("cout")
            }
        }        
        if(result == null) {
            result = 0;
        }
        return result
    }
}