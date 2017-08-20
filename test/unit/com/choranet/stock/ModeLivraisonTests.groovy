package com.choranet.stock

import grails.test.*

class ModeLivraisonTests extends GrailsUnitTestCase {
    protected void setUp() {
        super.setUp()
    }

    protected void tearDown() {
        super.tearDown()
    }

    void testModeLivraisonContraintes() {
        def testInstances = [new ModeLivraison(code : "0032", transporteur : "stcr", paiementALaReception : new Boolean(true))]
        //mockForConstraintsTests(DroitUtilisateur, testInstances)
        mockDomain(ModeLivraison, testInstances)
        assertEquals 1, ModeLivraison.count()
        assertTrue testInstances[0] instanceof ModeLivraison        
        def ModeLivraison2 = new ModeLivraison()
        assertFalse ModeLivraison2.validate()
        assertEquals "nullable", ModeLivraison2.errors["code"]
        assertEquals "nullable", ModeLivraison2.errors["transporteur"]
        def ModeLivraison3 = new ModeLivraison(code : "0032", transporteur : "stcr", paiementALaReception : new Boolean(true))
        assertFalse ModeLivraison3.validate()
        assertEquals "unique", ModeLivraison3.errors["code"]
        def ModeLivraison4 = new ModeLivraison(code : "00323", transporteur : "stcr", paiementALaReception : new Boolean(true))
        assertTrue ModeLivraison4.validate()
        ModeLivraison4.save(flush:true)
        assertEquals 2, ModeLivraison.count()
        assertTrue ModeLivraison.get(ModeLivraison4.id) instanceof ModeLivraison
        assertEquals "00323" + " : " + "stcr", ModeLivraison4.toString()
    }
}
