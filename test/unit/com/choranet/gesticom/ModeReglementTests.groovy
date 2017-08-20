package com.choranet.gesticom

import grails.test.*

class ModeReglementTests extends GrailsUnitTestCase {
    protected void setUp() {
        super.setUp()
    }

    protected void tearDown() {
        super.tearDown()
    }

    void testModeReglementContraintes() {
        def testInstances = [new ModeReglement(code : "005", libelle : "espece")]
        //mockForConstraintsTests(DroitUtilisateur, testInstances)
        mockDomain(ModeReglement, testInstances)
        assertEquals 1, ModeReglement.count()
        assertTrue testInstances[0] instanceof ModeReglement        
        def ModeReglement2 = new ModeReglement()
        assertFalse ModeReglement2.validate()
        assertEquals "nullable", ModeReglement2.errors["code"]
        assertEquals "nullable", ModeReglement2.errors["libelle"]
        def ModeReglement3 = new ModeReglement(code : "005", libelle : "espece")
        assertFalse ModeReglement3.validate()
        assertEquals "unique", ModeReglement3.errors["code"]
        assertEquals "unique", ModeReglement3.errors["libelle"]
        def ModeReglement4 = new ModeReglement(code : "0076", libelle : "cheque")
        assertTrue ModeReglement4.validate()
        ModeReglement4.save(flush:true)
        assertEquals 2, ModeReglement.count()
        assertTrue ModeReglement.get(ModeReglement4.id) instanceof ModeReglement
        assertEquals "cheque" + " : " + "0076", ModeReglement4.toString()
    }
}
