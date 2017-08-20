package com.choranet.commun

import grails.test.*

class PaternCompteurTests extends GrailsUnitTestCase {
    protected void setUp() {
        super.setUp()
    }

    protected void tearDown() {
        super.tearDown()
    }

   void testPaternCompteurContraintes() {
        def testInstances = [new PaternCompteur(libelle : "Pattern bon commande", prefixe : "BC{annee}{mois}{jour}", suffixe : "",pas:1,remplissage:12,numeroSuivant:2290,type:"BON_COMMANDE")]
        //mockForConstraintsTests(DroitUtilisateur, testInstances)
        mockDomain(PaternCompteur, testInstances)
        assertEquals 1, PaternCompteur.count()
        assertTrue testInstances[0] instanceof PaternCompteur       
        def PaternCompteur2 = new PaternCompteur()
        assertFalse PaternCompteur2.validate()
        assertEquals "nullable", PaternCompteur2.errors["libelle"]
        assertEquals "nullable", PaternCompteur2.errors["prefixe"]
        assertEquals "nullable", PaternCompteur2.errors["suffixe"]
        assertEquals "nullable", PaternCompteur2.errors["pas"]
        assertEquals "nullable", PaternCompteur2.errors["remplissage"]
        assertEquals "nullable", PaternCompteur2.errors["numeroSuivant"]
        assertEquals "nullable", PaternCompteur2.errors["type"]
        def PaternCompteur3=new PaternCompteur(libelle : "Pattern bon commande", prefixe : "BC{annee}{mois}{jour}", suffixe : "",pas:1,remplissage:12,numeroSuivant:2290,type:"BON_COMMANDE")
        assertFalse PaternCompteur3.validate()
        assertEquals "unique", PaternCompteur3.errors["libelle"]
        def PaternCompteur4=new PaternCompteur(libelle : "Pattern facture", prefixe : "BC{annee}{mois}{jour}", suffixe : "",pas:1,remplissage:12,numeroSuivant:2290,type:"BON_COMMANDE")
        assertTrue PaternCompteur4.validate()
        PaternCompteur4.save(flush:true)
        assertEquals 2, PaternCompteur.count()
        assertTrue PaternCompteur.get(PaternCompteur4.id) instanceof PaternCompteur
        assertEquals "Pattern facture", PaternCompteur4.toString()
    }
}
