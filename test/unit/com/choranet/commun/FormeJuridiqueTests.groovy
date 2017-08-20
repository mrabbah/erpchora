package com.choranet.commun

import grails.test.*

class FormeJuridiqueTests extends GrailsUnitTestCase {
    protected void setUp() {
        super.setUp()
    }

    protected void tearDown() {
        super.tearDown()
    }

   void testFormeJuridiqueContraintes() {
        def testInstances = [new FormeJuridique(libelle : "GIE")]
        //mockForConstraintsTests(DroitUtilisateur, testInstances)
        mockDomain(FormeJuridique, testInstances)
        assertEquals 1, FormeJuridique.count()
        assertTrue testInstances[0] instanceof FormeJuridique        
        def FormeJuridique2 = new FormeJuridique()
        assertFalse FormeJuridique2.validate()
        assertEquals "nullable", FormeJuridique2.errors["libelle"]
        def FormeJuridique3 = new FormeJuridique(libelle : "GIE")
        assertFalse FormeJuridique3.validate()
        assertEquals "unique", FormeJuridique3.errors["libelle"]
        def FormeJuridique4 = new FormeJuridique(libelle : "SCA")
        assertTrue FormeJuridique4.validate()
        FormeJuridique4.save(flush:true)
        assertEquals 2, FormeJuridique.count()
        assertTrue FormeJuridique.get(FormeJuridique4.id) instanceof FormeJuridique
        assertEquals "SCA", FormeJuridique4.toString()
    }
}
