package com.choranet.rh

import grails.test.*

class FonctionTests extends GrailsUnitTestCase {
    protected void setUp() {
        super.setUp()
    }

    protected void tearDown() {
        super.tearDown()
    }

    void testFonctionContraintes() {
        def testInstances = [new Fonction(libelle : "comptable")]
        //mockForConstraintsTests(DroitUtilisateur, testInstances)
        mockDomain(Fonction, testInstances)
        assertEquals 1, Fonction.count()
        assertTrue testInstances[0] instanceof Fonction       
        def Fonction2 = new Fonction()
        assertFalse Fonction2.validate()
        assertEquals "nullable", Fonction2.errors["libelle"]
        def Fonction3 = new Fonction(libelle : "comptable")
        assertFalse Fonction3.validate()
        assertEquals "unique", Fonction3.errors["libelle"]
        def Fonction4 = new Fonction(libelle : "finacier")
        assertTrue Fonction4.validate()
        Fonction4.save(flush:true)
        assertEquals 2, Fonction.count()
        assertTrue Fonction.get(Fonction4.id) instanceof Fonction
        assertEquals "finacier", Fonction4.toString()
    }
}
