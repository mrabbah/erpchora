package com.choranet.compta

import grails.test.*

class RegimeTVATests extends GrailsUnitTestCase {
    protected void setUp() {
        super.setUp()
    }

    protected void tearDown() {
        super.tearDown()
    }

    void testDroitUtilisateurContraintes() {
        def j=new Integer(20)
         def j1=new Integer(25)
        def testInstances = [new RegimeTVA(libelle : "20%", taux : j)]
        //mockForConstraintsTests(DroitUtilisateur, testInstances)
        mockDomain(RegimeTVA, testInstances)
        assertEquals 1, RegimeTVA.count()
        assertTrue testInstances[0] instanceof RegimeTVA        
        def RegimeTVA2 = new RegimeTVA()
        assertFalse RegimeTVA2.validate()
        assertEquals "nullable", RegimeTVA2.errors["libelle"]
        assertEquals "nullable", RegimeTVA2.errors["taux"]
        def RegimeTVA3 = new RegimeTVA(libelle : "20%", taux : j1)
        assertFalse RegimeTVA3.validate()
        assertEquals "unique", RegimeTVA3.errors["libelle"]
        def RegimeTVA4 = new RegimeTVA(libelle : "30%", taux : j)
        assertFalse RegimeTVA4.validate()
        assertEquals "unique", RegimeTVA4.errors["taux"]
        def RegimeTVA5 = new RegimeTVA(libelle : "25%", taux : j1)
        assertTrue RegimeTVA5.validate()
        RegimeTVA5.save(flush:true)
        assertEquals 2, RegimeTVA5.count()
        assertTrue RegimeTVA.get(RegimeTVA5.id) instanceof RegimeTVA
        assertEquals "25%", RegimeTVA5.toString()
    }
}
