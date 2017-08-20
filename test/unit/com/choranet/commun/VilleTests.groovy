package com.choranet.commun

import grails.test.*

class VilleTests extends GrailsUnitTestCase {
    protected void setUp() {
        super.setUp()
    }

    protected void tearDown() {
        super.tearDown()
    }

    void testVilleContraintes() {
        def pays1=new Pays(intitule : "maroc")
        def Pays4 = new Pays( intitule: "france")
       // mockDomain(Pays, testInstances1)
        def testInstances = [new Ville(intitule : "AGADIR", pays: pays1)]
        //mockForConstraintsTests(DroitUtilisateur, testInstances)
        mockDomain(Ville, testInstances)
        assertEquals 1, Ville.count()
      assertTrue testInstances[0] instanceof Ville       
      def Ville2 = new Ville()
      assertFalse Ville2.validate()
       assertEquals "nullable", Ville2.errors["intitule"]
       assertEquals "nullable", Ville2.errors["pays"]
        def Ville3 = new Ville(intitule : "AGADIR", pays: Pays4)
        assertFalse Ville3.validate()
        assertEquals "unique", Ville3.errors["intitule"]
        def   Ville4 = new Ville(intitule : "AZROU", pays: pays1)
        assertTrue Ville4.validate()
       Ville4.save(flush:true)
       assertEquals 2, Ville.count()
       assertTrue Ville.get(Ville4.id) instanceof Ville
       assertEquals "AZROU", Ville4.toString()
    }
}
