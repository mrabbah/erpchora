package com.choranet.compta

import grails.test.*

class DeviseTests extends GrailsUnitTestCase {
    protected void setUp() {
        super.setUp()
    }

    protected void tearDown() {
        super.tearDown()
    }

    
    void testDroitUtilisateurContraintes() {
        def valeurdevise=new Double(13.5)
        def valeurdevise1=new Double(15.5)
        def testInstances = [new Devise(libelle : "dollar", valeur : valeurdevise)]
        //mockForConstraintsTests(DroitUtilisateur, testInstances)
        mockDomain(Devise, testInstances)
        assertEquals 1, Devise.count()
        assertTrue testInstances[0] instanceof Devise        
        def Devise2 = new Devise()
        assertFalse Devise2.validate()
        assertEquals "nullable", Devise2.errors["libelle"]
        assertEquals "nullable", Devise2.errors["valeur"]
        def Devise3 = new Devise(libelle : "dhm", valeur : valeurdevise1)
        assertTrue Devise3.validate()
        Devise3.save(flush:true)
        assertEquals 2, Devise.count()
        assertTrue Devise.get(Devise3.id) instanceof Devise
        assertNotNull Devise3.toString()
    }
}
