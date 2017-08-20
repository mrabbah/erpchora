package com.choranet.gesticom

import grails.test.*

class EcheanceTests extends GrailsUnitTestCase {
    protected void setUp() {
        super.setUp()
    }

    protected void tearDown() {
        super.tearDown()
    }

    void testEcheanceContraintes() {
        def testInstances = [new Echeance(code : new Integer(001), libelle : "date livraison", periodicite : new Integer(30),typeDeclanchement:"DATE_LIVRAISON")]
        //mockForConstraintsTests(DroitUtilisateur, testInstances)
        mockDomain(Echeance, testInstances)
        assertEquals 1, Echeance.count()
        assertTrue testInstances[0] instanceof Echeance        
        def Echeance2 = new Echeance()
        assertFalse Echeance2.validate()
        assertEquals "nullable", Echeance2.errors["code"]
        assertEquals "nullable", Echeance2.errors["libelle"]
        assertEquals "nullable", Echeance2.errors["periodicite"]
        assertEquals "nullable", Echeance2.errors["typeDeclanchement"]
        def Echeance3 = new Echeance(code : new Integer(001), libelle : "date livraison", periodicite : new Integer(30),typeDeclanchement:"DATE_LIVRAISON")
        assertFalse Echeance3.validate()
        assertEquals "unique", Echeance3.errors["code"]
        assertEquals "unique", Echeance3.errors["libelle"]
        def Echeance4 = new Echeance(code : new Integer(002), libelle : "date livraison1", periodicite : new Integer(30),typeDeclanchement:"DATE_LIVRAISON")
        System.out.println("le code "+Echeance4.code)
        assertTrue Echeance4.validate()
        Echeance4.save(flush:true)
        assertEquals 2, Echeance.count()
        assertTrue Echeance.get(Echeance4.id) instanceof Echeance
        assertEquals 002 + " : " + "date livraison1", Echeance4.toString()
    }
}
