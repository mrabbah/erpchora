package com.choranet.stock

import grails.test.*

class NatureTests extends GrailsUnitTestCase {
    protected void setUp() {
        super.setUp()
    }

    protected void tearDown() {
        super.tearDown()
    }

    void testNatureContraintes() {
        def testInstances = [new Nature(libelle : "normale")]
        //mockForConstraintsTests(DroitUtilisateur, testInstances)
        mockDomain(Nature, testInstances)
        assertEquals 1, Nature.count()
        assertTrue testInstances[0] instanceof Nature        
        def Nature2 = new Nature()
        assertFalse Nature2.validate()
        assertEquals "nullable", Nature2.errors["libelle"]
        def Nature3 = new Nature(libelle : "normale")
        assertFalse Nature3.validate()
        assertEquals "unique", Nature3.errors["libelle"]
        def Nature4 = new Nature(libelle : "superieur")
        assertTrue Nature4.validate()
        Nature4.save(flush:true)
        assertEquals 2, Nature.count()
        assertTrue Nature.get(Nature4.id) instanceof Nature
        assertEquals "superieur", Nature4.toString()
    }
}
