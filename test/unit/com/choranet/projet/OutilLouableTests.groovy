package com.choranet.projet

import grails.test.*

class OutilLouableTests extends GrailsUnitTestCase {
    protected void setUp() {
        super.setUp()
    }

    protected void tearDown() {
        super.tearDown()
    }

    void testOutilLouableContraintes() {
        def testInstancesl = [new Loueur(numero : new Integer(12), nom : "trabel",adresse : "rue 26 n°51",codePostal:"20200",ville:"casa",telephone:"0673327392",fax:"0673327392",email:"contact@choranet.com")]
        //mockForConstraintsTests(DroitUtilisateur, testInstances)
        mockDomain(Loueur, testInstancesl)
        assertTrue testInstancesl[0].validate()
        testInstancesl[0].save(flush:true)
        def testInstances = [new OutilLouable(designation : "fabrication", prixHeureLocation : new Double(500.05), loueur : testInstancesl[0])]
        //mockForConstraintsTests(DroitUtilisateur, testInstances)
        mockDomain(OutilLouable, testInstances)
        assertEquals 1, OutilLouable.count()
        assertTrue testInstances[0] instanceof OutilLouable        
        def OutilLouable2 = new OutilLouable()
        assertFalse OutilLouable2.validate()
        assertEquals "nullable", OutilLouable2.errors["designation"]
        assertEquals "nullable", OutilLouable2.errors["prixHeureLocation"]
        assertEquals "nullable", OutilLouable2.errors["loueur"]
        def OutilLouable4 = new OutilLouable(designation : "fabrication", prixHeureLocation : new Double(500.05), loueur : testInstancesl[0])
        assertTrue OutilLouable4.validate()
        OutilLouable4.save(flush:true)
        assertEquals 2, OutilLouable.count()
        assertTrue OutilLouable.get(OutilLouable4.id) instanceof OutilLouable
        assertEquals "fabrication", OutilLouable4.toString()
        assertTrue OutilLouable4.prixHeureLocation> 0d
    }
}
