package com.choranet.compta

import grails.test.*

class CompteComptableTests extends GrailsUnitTestCase {
    protected void setUp() {
        super.setUp()
    }

    protected void tearDown() {
        super.tearDown()
    }

    void testDroitUtilisateurContraintes() {
        def testInstances = [new CompteComptable(identifiant : "compta generale", nom : "bilan", code : "15",type:"compte analytique",parent:null)]
        //mockForConstraintsTests(DroitUtilisateur, testInstances)
        mockDomain(CompteComptable, testInstances)
        assertEquals 1, CompteComptable.count()
        assertTrue testInstances[0] instanceof CompteComptable       
        def CompteComptable2 = new CompteComptable()
        assertFalse CompteComptable2.validate()
        assertEquals "nullable", CompteComptable2.errors["identifiant"]
        assertEquals "nullable", CompteComptable2.errors["nom"]
        assertEquals "nullable", CompteComptable2.errors["code"]
        assertEquals "nullable", CompteComptable2.errors["type"]
//        def CompteComptable3 = new CompteComptable(identifiant : "compta generale", nom : "bilan", code : "15",type:"compte analytique",parent:null)
//        assertFalse CompteComptable3.validate()
        def CompteComptable4 = new CompteComptable(identifiant : "compta generale trime", nom : "plan juridique", code : "15",type:"compte analytique",parent:null)
        assertTrue CompteComptable4.validate()
        
        
        CompteComptable4.save(flush:true)
        assertEquals 2, CompteComptable.count()
        assertTrue CompteComptable.get(CompteComptable4.id) instanceof CompteComptable
//        assertEquals CompteComptable.toString(), Object(CompteComptable4).toString()
       
    }
}
