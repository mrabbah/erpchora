package com.choranet.compta

import grails.test.*

class JournalTests extends GrailsUnitTestCase {
    protected void setUp() {
        super.setUp()
    }

    protected void tearDown() {
        super.tearDown()
    }

    void testJournalContraintes() {
        def testInstances1 = [ new CompteComptable(identifiant : "compta generale trime", nom : "plan juridique", code : "15",type:"compte analytique",parent:null)]
        mockDomain(CompteComptable,testInstances1)
        assertTrue testInstances1[0].validate()
        def testInstances = [new Journal(intitule : "hebdomadaire", compteComptables : testInstances1)]
        //mockForConstraintsTests(DroitUtilisateur, testInstances)
        mockDomain(Journal, testInstances)
        assertEquals 1, Journal.count()
        assertTrue testInstances[0] instanceof Journal        
        def Journal2 = new Journal()
        assertFalse Journal2.validate()
        assertEquals "nullable", Journal2.errors["intitule"]
        assertEquals "nullable", Journal2.errors["compteComptables"]
        def Journal4 = new Journal(intitule : "mensuel", compteComptables : testInstances1)
        assertTrue Journal4.validate()
        Journal4.save(flush:true)
        assertEquals 2, Journal.count()
        assertTrue Journal.get(Journal4.id) instanceof Journal
        assertNotNull Journal4.toString()
    }
}
