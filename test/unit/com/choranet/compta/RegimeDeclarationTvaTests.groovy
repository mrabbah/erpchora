package com.choranet.compta

import grails.test.*

class RegimeDeclarationTvaTests extends GrailsUnitTestCase {
    protected void setUp() {
        super.setUp()
    }

    protected void tearDown() {
        super.tearDown()
    }

    void testRegimeDeclarationTvaContraintes() {
        
        
        
        def testInstances = [new RegimeDeclarationTva(libelle : "XYZ", periodicite : "Mensuelle",faitGenerateur :"Facturation")]
        //mockForConstraintsTests(Module, testInstances)
        
        mockDomain(RegimeDeclarationTva, testInstances)
//        mockDomain(GroupeUtilisateur)
//        mockDomain(GroupeUtilisateur)
        assertEquals 1, RegimeDeclarationTva.count()
        assertTrue testInstances[0] instanceof RegimeDeclarationTva        
        def RegimeDeclarationTva2 = new RegimeDeclarationTva()
        assertFalse RegimeDeclarationTva2.validate()
        assertEquals "nullable", RegimeDeclarationTva2.errors["libelle"]
        assertEquals "nullable", RegimeDeclarationTva2.errors["periodicite"]
        assertEquals "nullable", RegimeDeclarationTva2.errors["faitGenerateur"]
        def RegimeDeclarationTva3=new RegimeDeclarationTva(libelle : "ABC", periodicite : "Trimestrielle",faitGenerateur :"Encaissement")
        assertTrue RegimeDeclarationTva3.validate()
        RegimeDeclarationTva3.save(flush:true)
        System.out.println("jnnjkbjb premier hsjhsjggf bbbb")
        assertEquals 2, RegimeDeclarationTva.count()
        System.out.println("jnnjkbjb premier hsjhsjggf bbbb fdhdfdfhd")
        assertTrue RegimeDeclarationTva.get(RegimeDeclarationTva3.id) instanceof RegimeDeclarationTva
        System.out.println("jnnjkbjb premier hsjhsjggf bbbb gggg")
        assertEquals "ABC", RegimeDeclarationTva3.toString()

    }
}
