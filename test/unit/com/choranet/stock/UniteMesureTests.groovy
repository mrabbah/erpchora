package com.choranet.stock

import grails.test.*

class UniteMesureTests extends GrailsUnitTestCase {
    protected void setUp() {
        super.setUp()
    }

    protected void tearDown() {
        super.tearDown()
    }

    void testUniteMesureContraintes() {
        def testInstances = [new UniteMesure(libelle : "metre")]
        //mockForConstraintsTests(DroitUtilisateur, testInstances)
        mockDomain(UniteMesure, testInstances)
        assertEquals 1, UniteMesure.count()
        assertTrue testInstances[0] instanceof UniteMesure        
        def UniteMesure2 = new UniteMesure()
        assertFalse UniteMesure2.validate()
        assertEquals "nullable", UniteMesure2.errors["libelle"]
        def UniteMesure3 = new UniteMesure(libelle : "metre")
        assertFalse UniteMesure3.validate()
        assertEquals "unique", UniteMesure3.errors["libelle"]
        def UniteMesure4 = new UniteMesure(libelle : "bar")
        assertTrue UniteMesure4.validate()
        UniteMesure4.save(flush:true)
        assertEquals 2, UniteMesure.count()
        assertTrue UniteMesure.get(UniteMesure4.id) instanceof UniteMesure
        assertEquals "bar", UniteMesure4.toString()
    }
}
