package com.choranet.projet

import grails.test.*

class LoueurTests extends GrailsUnitTestCase {
    protected void setUp() {
        super.setUp()
    }

    protected void tearDown() {
        super.tearDown()
    }

    void testLoueurContraintes() {
        def testInstances = [new Loueur(numero : new Integer(12), nom : "trabel",adresse : "rue 26 n°51",codePostal:"20200",ville:"casa",telephone:"0673327392",fax:"0673327392",email:"contact@choranet.com")]
        //mockForConstraintsTests(DroitUtilisateur, testInstances)
        mockDomain(Loueur, testInstances)
        assertEquals 1, Loueur.count()
        assertTrue testInstances[0] instanceof Loueur       
        def Loueur2 = new Loueur()
        assertFalse Loueur2.validate()
        assertEquals "nullable", Loueur2.errors["numero"]
        assertEquals "nullable", Loueur2.errors["nom"]
        def Loueur4 = new Loueur(numero : new Integer(12), nom : "trabel",adresse : "rue 26 n°51",codePostal:"20200",ville:"casa",telephone:"0673327392",fax:"0673327392",email:"contact@choranet.com")
        assertTrue Loueur4.validate()
        Loueur4.save(flush:true)
        assertEquals 2, Loueur.count()
        assertTrue Loueur.get(Loueur4.id) instanceof Loueur
        assertEquals "trabel", Loueur4.toString()
    }
}
