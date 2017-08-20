package com.choranet.commun

import grails.test.*

class PaysTests extends GrailsUnitTestCase {
    protected void setUp() {
        super.setUp()
    }

    protected void tearDown() {
        super.tearDown()
    }

     void testPaysContraintes() {
        def testInstances = [new Pays(intitule : "maroc")]
        //mockForConstraintsTests(DroitUtilisateur, testInstances)
        mockDomain(Pays, testInstances)
        assertEquals 1, Pays.count()
        assertTrue testInstances[0] instanceof Pays        
        def Pays2 = new Pays()
        assertFalse Pays2.validate()
        assertEquals "nullable", Pays2.errors["intitule"]
        def Pays3 = new Pays(intitule : "maroc")
        assertFalse Pays3.validate()
        assertEquals "unique", Pays3.errors["intitule"]
        def Pays4 = new Pays( intitule: "france")
        assertTrue Pays4.validate()
        Pays4.save(flush:true)
        assertEquals 2, Pays.count()
        assertTrue Pays.get(Pays4.id) instanceof Pays
        assertEquals "france", Pays4.toString()
    }
}
