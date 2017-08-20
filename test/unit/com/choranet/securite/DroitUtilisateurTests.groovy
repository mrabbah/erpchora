package com.choranet.securite

import grails.test.*

class DroitUtilisateurTests extends GrailsUnitTestCase {
    protected void setUp() {
        super.setUp()
    }

    protected void tearDown() {
        super.tearDown()
    }

    void testDroitUtilisateurContraintes() {
        def testInstances = [new DroitUtilisateur(droit : "XYZ", description : "DROIT_XYZ", parent : null)]
        //mockForConstraintsTests(DroitUtilisateur, testInstances)
        mockDomain(DroitUtilisateur, testInstances)
        assertEquals 1, DroitUtilisateur.count()
        assertTrue testInstances[0] instanceof DroitUtilisateur        
        def droitUtilisateur2 = new DroitUtilisateur()
        assertFalse droitUtilisateur2.validate()
        assertEquals "nullable", droitUtilisateur2.errors["droit"]
        def droitUtilisateur3 = new DroitUtilisateur(droit : "XYZ", description : "", parent : null)
        assertFalse droitUtilisateur3.validate()
        assertEquals "unique", droitUtilisateur3.errors["droit"]
        def droitUtilisateur4 = new DroitUtilisateur(droit : "ABC", description : "DROIT_ABC", parent : testInstances[0])
        assertTrue droitUtilisateur4.validate()
        droitUtilisateur4.save(flush:true)
        assertEquals 2, DroitUtilisateur.count()
        assertTrue DroitUtilisateur.get(droitUtilisateur4.id) instanceof DroitUtilisateur
        assertEquals "DROIT_ABC", droitUtilisateur4.toString()
    }
}
