package com.choranet.gesticom

import grails.test.*

class ModeRelancePaiementTests extends GrailsUnitTestCase {
    protected void setUp() {
        super.setUp()
    }

    protected void tearDown() {
        super.tearDown()
    }

    void testModeRelancePaiementContraintes() {
        def testInstances = [new ModeRelancePaiement(code : "XYZ01", frequence : new Integer(10), libelle : "facture",corpsMessage:"paiment du reste de la facture")]
        //mockForConstraintsTests(DroitUtilisateur, testInstances)
        mockDomain(ModeRelancePaiement, testInstances)
        assertEquals 1, ModeRelancePaiement.count()
        assertTrue testInstances[0] instanceof ModeRelancePaiement        
        def ModeRelancePaiement2 = new ModeRelancePaiement()
        assertFalse ModeRelancePaiement2.validate()
        assertEquals "nullable", ModeRelancePaiement2.errors["code"]
        assertEquals "nullable", ModeRelancePaiement2.errors["frequence"]
        assertEquals "nullable", ModeRelancePaiement2.errors["libelle"]
        assertEquals "nullable", ModeRelancePaiement2.errors["corpsMessage"]
        def ModeRelancePaiement3 = new ModeRelancePaiement(code : "XYZ01", frequence : new Integer(10), libelle : "facture",corpsMessage:"paiment du reste de la facture")
        assertFalse ModeRelancePaiement3.validate()
        assertEquals "unique", ModeRelancePaiement3.errors["code"]
        assertEquals "unique", ModeRelancePaiement3.errors["libelle"]
        def ModeRelancePaiement4 = new ModeRelancePaiement(code : "XYZ012", frequence : new Integer(10), libelle : "facture01",corpsMessage:"paiment du reste de la facture")
        assertTrue ModeRelancePaiement4.validate()
        ModeRelancePaiement4.save(flush:true)
        assertEquals 2, ModeRelancePaiement.count()
        assertTrue ModeRelancePaiement.get(ModeRelancePaiement4.id) instanceof ModeRelancePaiement
        assertEquals "XYZ012" + " : " + "facture01", ModeRelancePaiement4.toString()
    }
}
