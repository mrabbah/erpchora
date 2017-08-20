
package com.choranet.gesticom;

import com.choranet.commun.SuperService

/**
 * PaiementService Service pour la gestion des opérations
 * transactionnelles pour l'objet Paiement
 */
class PaiementService extends SuperService {

    static transactional = true
    
    def bonCommandeService
    //def bonLivraisonService
    def factureService
    def compteBancaireChoraClientInfoService

    def list() throws Exception {
        return super.list(Paiement.class)
    }
    
    def update(Paiement paiement) throws Exception {
        def oldMontant = null
        def oldCompte = null
        def oldCredit = null
        def oldDateEncaissement = null
        if(paiement.isDirty("compteBancaire")) {
            oldCompte = paiement.getPersistentValue("compteBancaire")
        }
        if(paiement.isDirty("montantPaye")) {
            oldMontant = paiement.getPersistentValue("montantPaye")
        }
        if(paiement.isDirty("credit")) {
            oldCredit = paiement.getPersistentValue("credit")
        }
        if(paiement.isDirty("dateEncaissement")) {
            oldDateEncaissement = paiement.getPersistentValue("dateEncaissement")
        }
        
        paiement = super.update(paiement)
        updateEtatPaiementBonCommande(paiement.bonCommande, paiement.credit)
        updateCompteBancaireSociete(paiement.compteBancaire, paiement.montantPaye, paiement.dateEncaissement, paiement.credit, oldDateEncaissement, "UPDATE")
    }

    def save(Paiement paiement) throws Exception {
        super.save(paiement)
        updateEtatPaiementBonCommande(paiement.bonCommande, paiement.credit)
        updateCompteBancaireSociete(paiement.compteBancaire, paiement.montantPaye, paiement.dateEncaissement, paiement.credit, null, "SAVE")
    }

    def delete(Paiement paiement) throws Exception {
        def bc = paiement.bonCommande
        def credit = paiement.credit
        def compteBancaire = paiement.compteBancaire
        def montantPaye = paiement.montantPaye
        def dateEncaissement = paiement.dateEncaissement
        super.delete(paiement)
        updateEtatPaiementBonCommande(bc, credit)
        updateCompteBancaireSociete(compteBancaire, montantPaye, dateEncaissement, credit, null, "DELETE")
    }
    
    def updateCompteBancaireSociete(compteBancaire, montantPaye, dateEncaissement, credit, oldDateEncaissement, operation)throws Exception {

        if(operation.equals("SAVE")) {
            if(compteBancaire) {
                if(dateEncaissement) {
                    if(credit) {
                        compteBancaire.solde += montantPaye 
                    } else {
                        compteBancaire.solde -= montantPaye
                    }
                } 
                if(credit) {
                    compteBancaire.soldePrevu += montantPaye 
                } else {
                    compteBancaire.soldePrevu -= montantPaye
                }
                compteBancaireChoraClientInfoService.update(compteBancaire)
            }
        } else if (operation.equals("DELETE")) {
            if(compteBancaire) {
                if(dateEncaissement) {
                    if(credit) {
                        compteBancaire.solde -= montantPaye 
                    } else {
                        compteBancaire.solde += montantPaye
                    }
                } 
                if(credit) {
                    compteBancaire.soldePrevu -= montantPaye 
                } else {
                    compteBancaire.soldePrevu += montantPaye
                }
                compteBancaireChoraClientInfoService.update(compteBancaire)
            }
        } else if (operation.equals("UPDATE")) {
            if(compteBancaire) {
                if(dateEncaissement && !oldDateEncaissement) {
                    if(credit) {
                        compteBancaire.solde += montantPaye 
                    } else {
                        compteBancaire.solde -= montantPaye
                    }
                    compteBancaireChoraClientInfoService.update(compteBancaire)
                } 
            }
        }
        
    }
    def updateEtatPaiementBonCommande(bc, credit) throws Exception {
        if((credit && bc.type.equals("ACHAT")) || (!credit && bc.type.equals("VENTE"))) {
            return updateEtatPaiementBonCommandeAprsRetour(bc)
        } else {
            def c = Paiement.createCriteria()
            def montantPaye = c.get {
                bonCommande {
                    eq("numBC", bc.numBC)
                }
                isNotNull("dateEncaissement")
                eq("credit", credit)
                projections {
                    sum("montantPaye")
                }
            }
            if(montantPaye == null) {
                montantPaye = 0d
            }
            
            bc.paye = (montantPaye == bc.trans_totalttc)
            return bonCommandeService.update(bc)   
        }
    }
    
    def updateEtatPaiementBonCommandeAprsRetour(bc) {
        def c = Paiement.createCriteria()
        def mtPaiementBrs = c.get {
            bonCommande {
                eq("numBC", bc.numBC)
            }
            isNotNull("dateEncaissement")
            if(bc.type.equals("VENTE")) {
                eq("credit", false)
            } else {
                eq("credit", true)
            }
            projections {
                sum("montantPaye")
            }
        }
        if(mtPaiementBrs == null) {
            mtPaiementBrs = 0d
        }
        bc.retourPaye = (mtPaiementBrs == bc.totalttcRetour)
        return bonCommandeService.update(bc)
    }
    
    def getMontantPayeBonCommande(bc, credit) {
        def c = Paiement.createCriteria()
        def paiements = c.list {
            bonCommande {
                eq("numBC", bc.numBC)
            }
            eq("credit", credit)
        }
        def montantPaye = 0d
        for(p in paiements) {
            montantPaye += p.montantPaye
        }
        return montantPaye
    }
    def existeDejaBonCommande(bonCommande, listeBc) {
        for(bc in listeBc) {
            if(bc.numBC.equals(bonCommande.numBC)) {
                return true
            }
        }
        return false
    }
    def existeDejaFacture(facture, listeFactures) {
        for(fact in listeFactures) {
            if(fact.numeroFacture.equals(facture.numeroFacture)) {
                return true
            }
        }
        return false
    }
    def getPaiementsFacture(fact) {
        if(fact.faitgenerateur.equals("Encaissement")) {
            fact.paiements
        } else {
            def c = Paiement.createCriteria()
            def paiements = c.list {
                bonCommande {
                    eq("numBC", fact.bonCommande.numBC)
                }
            }
            return paiements   
        }        
    }
    def getMontantPayeFacture(fact) throws Exception{
//        def c = Paiement.createCriteria()
        def result = 0d
        if(fact.faitgenerateur.equals("Encaissement")) {
            for(p in fact.paiements) {
                result += p.montantPaye
            }
        } else {
            //result = fact.bonCommande.trans_totalttc
            result = fact.bonCommandes.trans_totalttc
        }
        return result
    }
    
    def getMontantNonPayeFacture(fact) {
//        def c = Paiement.createCriteria()
        //def result = fact.bonCommande.trans_totalttc - getMontantPayeFacture(fact)
        def result = fact.bonCommandes.trans_totalttc - getMontantPayeFacture(fact)
        return result
    }
    
    def getPaiementsBetwen(type,date1,date2) {
        def criteria = Paiement.createCriteria()
        def result = criteria.list {
            between("date", date1, date2)
            bonCommande { 
                eq("type",type)
            }
            partenaire{
                order("raisonSociale", "asc")
            }
        }
        return result
    }
    
    def getPaiementsByPartenaireBetwen(type,p,date1,date2) {
        def criteria = Paiement.createCriteria()
        def result = criteria.list {
            between("date", date1, date2)
            
            bonCommande { 
                eq("type",type)

            }
            
            partenaire  {
                eq("code", p.code)
                    
            }
            
        }
        return result
    }
    
    def getPaiements(type, estAvoir) {
        //log.debug(type + " : " + estAvoir)
       
        def c = Paiement.createCriteria()
        def r = c.list {
            bonCommande {
                eq("type", type)
            }
            if((type.equals("VENTE") && !estAvoir) || type.equals("ACHAT") && estAvoir) {
                
                eq("credit", true)                    
            } else {
                eq("credit", false)                    
            }
            //isNotNull("dateEncaissement")
        }
        return r
    }
    def getPaiementsPartenaireNonFacture(type, partenaire, estAvoir) {
        def c1 = Facture.createCriteria() 
        def factures = c1.list {
            eq("partenaire", partenaire)
        }
        def paiements = factures*.paiements
        def ids = []
        for(ps in paiements) {
            for(p in ps) {
                ids.add(p.id)
            }
        }
        def c2 = Paiement.createCriteria()
        c2.list {
            eq("partenaire", partenaire)
            //isNotNull("dateEncaissement")
            bonCommande {
                eq("type", type)
            }
            if((type.equals("VENTE") && !estAvoir) || type.equals("ACHAT") && estAvoir) {
                eq("credit", true)                    
            } else {
                eq("credit", false)                    
            }
            if(ids.size() > 0) {
                not{"in"("id", ids)}
            }
        }
    }
    
    def concatPaiementsNonFactureEtPaiementFacture(type, objet) {
        def result = []
        def lst1 = getPaiementsPartenaireNonFacture(type, objet.partenaire, objet.estAvoir)
        if(lst1) {
            result.addAll(lst1)
        }
        if(objet.paiements) {
            result.addAll(objet.paiements)                    
        }
        return result
    }
    
    def getPaiementsByPartenaireEtType(partenaire,date1,date2,type = null) {
     
        def c = Paiement.createCriteria()
        def r = c.list {
            eq('partenaire',partenaire)
            //            bonCommande {
            //                eq("type", type)
            //   
            //                     }
            between('date', date1, date2)            
            if (type){
                def credit = false
                if(type == 'CREDIT'){
                    credit = true
                }
                eq('credit',credit) 
            }
            
        }
        return r
    }
    
    def getSumPaiementsByPartenaireEtType(partenaire,date1,date2,type = null) {
     
        def c = Paiement.createCriteria()
        def r = c.get {

            eq('partenaire',partenaire)
            between('date', date1, date2)            
            if (type){
                def credit = false
                if(type == 'CREDIT'){
                    credit = true
                }
                eq('credit',credit) 
            }
            projections {
                sum('montantPaye')
            }
            
        }
        if(r == null) {
            r = 0;
        }
        return r
    }

    def getPaiementsByType(type) {
        def c = Paiement.createCriteria()
        def r = c.list {
            bonCommande {
                eq("type", type)
            }
        }
        return r
    }
    
    def filtrer(clazz, filtre, sortedHeader, sortedDirection, ofs, maxNb, type) {
        try {
            def listeObjets 
            def tailleListe = 0
            def fields = clazz.getDeclaredFields()
            def requete = clazz.createCriteria()
            def attributToSort
            def selectionnerDistinctResultat = false
            listeObjets = requete.list {
                for(field in fields) {
                    def nomAttribut = field.getName()
                    def modifier = field.getModifiers()
                    if(!nomAttribut.startsWith("trans_") && 
                        !(modifier > 2)) { // eviter les attributs transients: qui ne sont pas persister et les attributs static
//                        def typeAttribut = field.getType()
                        //def valeurAttribut = field.get(filtre)
                        def valeurAttribut = filtre."$nomAttribut"
                    
                        if(sortedHeader == "h"+nomAttribut) {
                            attributToSort = nomAttribut 
                        }
                        if(valeurAttribut != null) {
                            if(valeurAttribut instanceof String) {
                                if(valeurAttribut != "") {
                                    ilike(nomAttribut, "%"+valeurAttribut+"%")                                
                                }
                            } else if(valeurAttribut instanceof Boolean || valeurAttribut == boolean.class) {
                                if(valeurAttribut) {
                                    eq(nomAttribut, true)
                                } else {
                                    eq(nomAttribut, false)
                                }
                            } else if(valeurAttribut instanceof java.util.Collection) {
                                    "$nomAttribut" {"in"("id",valeurAttribut.id)}
                                selectionnerDistinctResultat = true
                            } else {
                                eq(nomAttribut, valeurAttribut)
                            }
                        }
                    }
                }
                bonCommande {
                    eq("type", type)
                }
                if(attributToSort != null) {
                    order(attributToSort, sortedDirection)
                }
                //firstResult(ofs)
                //maxResults(maxNb)
                if(selectionnerDistinctResultat) {
                    resultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
                    //Cette solution n est pas clean puisque ce travail est fait
                    //cote client et non pas database une deuxieme solution es la
                    //suivante reste a affiner voir: 
                    //https://forum.hibernate.org/viewtopic.php?t=941669
                    //                    projections {
                    //                        clazz.getSimpleName()
                    //                        distinct("id")
                    //                    }
                }
            }
            /*if(listeObjets != null && listeObjets.size() > 0) {
            def criteria = clazz.createCriteria()
            tailleListe = criteria.count{
            for(field in fields) {
            def nomAttribut = field.getName()
            def modifier = field.getModifiers()
            if(!nomAttribut.startsWith("trans_") && 
            !(modifier > 2)) { // eviter les attributs transients: qui ne sont pas persister et les attributs static
            def typeAttribut = field.getType()
            //def valeurAttribut = field.get(filtre)
            def valeurAttribut = filtre."$nomAttribut"
                    
            if(valeurAttribut != null) {
            if(valeurAttribut instanceof String) {
            if(valeurAttribut != "") {
            ilike(nomAttribut, "%"+valeurAttribut+"%")                                
            }
            } else if(valeurAttribut instanceof Boolean || valeurAttribut == boolean.class) {
            if(valeurAttribut) {
            eq(nomAttribut, true)
            }
            } else if(valeurAttribut instanceof java.util.Collection) {
            "$nomAttribut" {"in"("id",valeurAttribut.id)}
            } else {
            eq(nomAttribut, valeurAttribut)
            }
            }
            }
            } 
            if(selectionnerDistinctResultat) {
            resultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
            //Cette solution n est pas clean puisque ce travail est fait
            //cote client et non pas database une deuxieme solution es la
            //suivante reste a affiner voir: 
            //https://forum.hibernate.org/viewtopic.php?t=941669
            //                    projections {
            //                        clazz.getSimpleName()
            //                        distinct("id")
            //                    }
            }    
            }
            } else {
            tailleListe = 0
            listeObjets = null
            }*/
            
            return [tailleListe:tailleListe,listeObjets:listeObjets]
        }
        catch(Exception e) {
            log.error(e)
            throw e
        }
    }
}