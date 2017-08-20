package com.choranet.gesticom

import grails.test.*

class AgenceBancaireTests extends GrailsUnitTestCase {
    protected void setUp() {
        super.setUp()
    }

    protected void tearDown() {
        super.tearDown()
    }

    void testAgenceBancaireContraintes() {
        def testInstances = [new AgenceBancaire(nom : "bmce", adresse : "sial", tel : "0522323245")]
        //mockForConstraintsTests(DroitUtilisateur, testInstances)
        mockDomain(AgenceBancaire, testInstances)
        assertEquals 1, AgenceBancaire.count()
        assertTrue testInstances[0] instanceof AgenceBancaire        
        def AgenceBancaire2 = new AgenceBancaire()
        assertFalse AgenceBancaire2.validate()
        assertEquals "nullable", AgenceBancaire2.errors["nom"]
        def AgenceBancaire3 = new AgenceBancaire(nom : "bmce", adresse : "sial", tel : "0522323245")
        assertFalse AgenceBancaire3.validate()
        assertEquals "unique", AgenceBancaire3.errors["nom"]
        def AgenceBancaire4 = new AgenceBancaire(nom : "bmci", adresse : "sial", tel : "0522323245")
        assertTrue AgenceBancaire4.validate()
        AgenceBancaire4.save(flush:true)
        assertEquals 2, AgenceBancaire.count()
        assertTrue AgenceBancaire.get(AgenceBancaire4.id) instanceof AgenceBancaire
        assertEquals "bmci", AgenceBancaire4.toString()
    }
}
