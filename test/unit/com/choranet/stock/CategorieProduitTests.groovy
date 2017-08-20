package com.choranet.stock

import grails.test.*

class CategorieProduitTests extends GrailsUnitTestCase {
    protected void setUp() {
        super.setUp()
    }

    protected void tearDown() {
        super.tearDown()
    }

    void testCategorieProduitContraintes() {
        def testInstances = [new CategorieProduit(code : "21211515", intitule : "alimentaire", categorieParente : null)]
        //mockForConstraintsTests(DroitUtilisateur, testInstances)
        mockDomain(CategorieProduit, testInstances)
        assertEquals 1, CategorieProduit.count()
        assertTrue testInstances[0] instanceof CategorieProduit       
        def CategorieProduit2 = new CategorieProduit()
        assertFalse CategorieProduit2.validate()
        assertEquals "nullable", CategorieProduit2.errors["code"]
        assertEquals "nullable", CategorieProduit2.errors["intitule"]
        def CategorieProduit4 = new CategorieProduit(code : "21211515", intitule : "alimentaire", categorieParente : null)
        assertTrue CategorieProduit4.validate()
        CategorieProduit4.save(flush:true)
        System.out.println("hjvhhvvhj  "+CategorieProduit4.toString()+"hhhh   "+CategorieProduit4.getCategorieFullName())
        assertEquals 2, CategorieProduit.count()
        assertTrue CategorieProduit.get(CategorieProduit4.id) instanceof CategorieProduit
        assertEquals "alimentaire", CategorieProduit4.toString()
    }
}
