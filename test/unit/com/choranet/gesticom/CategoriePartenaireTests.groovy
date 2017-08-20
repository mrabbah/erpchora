package com.choranet.gesticom

import grails.test.*

class CategoriePartenaireTests extends GrailsUnitTestCase {
    protected void setUp() {
        super.setUp()
    }

    protected void tearDown() {
        super.tearDown()
    }

    void testCategoriePartenaireContraintes() {
        def testInstances = [new CategoriePartenaire(libelle : "propr", categorieParente : null)]
        //mockForConstraintsTests(DroitUtilisateur, testInstances)
        mockDomain(CategoriePartenaire, testInstances)
        assertEquals 1, CategoriePartenaire.count()
        assertTrue testInstances[0] instanceof CategoriePartenaire        
        def CategoriePartenaire2 = new CategoriePartenaire()
        assertFalse CategoriePartenaire2.validate()
        assertEquals "nullable", CategoriePartenaire2.errors["libelle"]
        def CategoriePartenaire3 = new CategoriePartenaire(libelle : "propr", categorieParente : null)
        assertFalse CategoriePartenaire3.validate()
        assertEquals "unique", CategoriePartenaire3.errors["libelle"]
        def CategoriePartenaire4 = new CategoriePartenaire(libelle : "propr1", categorieParente : testInstances[0])
        assertTrue CategoriePartenaire4.validate()
        CategoriePartenaire4.save(flush:true)
        assertEquals 2, CategoriePartenaire.count()
         System.out.println("bhbhbh"+testInstances[0].toString()+"   "+testInstances[0].getCategorieFullName())
        System.out.println("bhbhbh"+CategoriePartenaire4.toString()+"   "+CategoriePartenaire4.getCategorieFullName())
        assertTrue CategoriePartenaire.get(CategoriePartenaire4.id) instanceof CategoriePartenaire
        assertEquals "/propr/propr1", CategoriePartenaire4.toString()
    }
}
