package com.choranet.projet

import grails.test.*

class SoustraitantTests extends GrailsUnitTestCase {
    protected void setUp() {
        super.setUp()
    }

    protected void tearDown() {
        super.tearDown()
    }

    void testSoustraitantContraintes() {
        def num=new Integer(12)
        def num1=new Integer(1)
        def testInstances = [new Soustraitant(numero : num, nom : "trabel", adresse : "rue 26 n°51 ain sebaa",codePostal:"20200",ville:"casa",telephone:"0673327392",fax:"0673327392",email:"wwww.choranet.com")]
        //mockForConstraintsTests(DroitUtilisateur, testInstances)
        mockDomain(Soustraitant, testInstances)
        assertEquals 1, Soustraitant.count()
        assertTrue testInstances[0] instanceof Soustraitant        
        def Soustraitant2 = new Soustraitant()
        assertFalse Soustraitant2.validate()
        assertEquals "nullable", Soustraitant2.errors["numero"]
        assertEquals "nullable", Soustraitant2.errors["nom"]
        assertEquals "nullable", Soustraitant2.errors["telephone"]
        
        def Soustraitant4 = new Soustraitant(numero : num1, nom : "trabel1", adresse : "rue 26 n°51 ain sebaa h",codePostal:"20200",ville:"casa",telephone:"0673327392",fax:"0673327392",email:"contact@choranet.com")
        assertTrue Soustraitant4.validate()
        Soustraitant4.save(flush:true)
        assertEquals 2, Soustraitant.count()
        assertTrue Soustraitant.get(Soustraitant4.id) instanceof Soustraitant
        assertEquals "trabel1", Soustraitant4.toString()
    }
}
