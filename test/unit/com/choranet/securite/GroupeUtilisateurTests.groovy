package com.choranet.securite

import grails.test.*

class GroupeUtilisateurTests extends GrailsUnitTestCase {
    protected void setUp() {
        super.setUp()
    }

    protected void tearDown() {
        super.tearDown()
    }

    void testGroupeUtilisateurContraintes() {
        def testInstances = [new GroupeUtilisateur(description : "XYZ", intitule : "DROIT_XYZ", droits : null)]
        //mockForConstraintsTests(DroitUtilisateur, testInstances)
        mockDomain(GroupeUtilisateur, testInstances)
        assertEquals 1, GroupeUtilisateur.count()
        assertTrue testInstances[0] instanceof GroupeUtilisateur        
        def GroupeUtilisateur2 = new GroupeUtilisateur()
        assertFalse GroupeUtilisateur2.validate()
        assertEquals "nullable", GroupeUtilisateur2.errors["description"]
        assertEquals "nullable", GroupeUtilisateur2.errors["intitule"]
        def GroupeUtilisateur3 = new GroupeUtilisateur(description : "XYZ", intitule : "DROIT_XYZ", droits : null)
         assertFalse GroupeUtilisateur3.validate()
        assertEquals "unique", GroupeUtilisateur3.errors["intitule"]
        def GroupeUtilisateur4 = new GroupeUtilisateur(description : "ABC", intitule : "ABC", droits : null)
        assertTrue GroupeUtilisateur4.validate()
        GroupeUtilisateur4.save(flush:true)
        assertEquals 2, GroupeUtilisateur.count()
        assertTrue GroupeUtilisateur.get(GroupeUtilisateur4.id) instanceof GroupeUtilisateur
        assertEquals "ABC", GroupeUtilisateur4.toString()
    }
}
