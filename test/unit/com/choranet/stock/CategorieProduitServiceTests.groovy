package com.choranet.stock

import grails.test.*

class CategorieProduitServiceTests extends GrailsUnitTestCase {
    protected void setUp() {
        super.setUp()
    }

    protected void tearDown() {
        super.tearDown()
    }

    void testGetCategorieByLibelle() {
        def testInstances = [new CategorieProduit(code : "21211515", intitule : "alimentaire", categorieParente : null)]
        //mockForConstraintsTests(DroitUtilisateur, testInstances)
        mockDomain(CategorieProduit, testInstances)
        def testService=new CategorieProduitService()
        testService.getCategorieByLibelle("alimentaire")
            
        }
        void testGsetCategorieByCode() {
        def testInstances = [new CategorieProduit(code : "21211515", intitule : "alimentaire", categorieParente : null)]
        //mockForConstraintsTests(DroitUtilisateur, testInstances)
        mockDomain(CategorieProduit, testInstances)
        def testService=new CategorieProduitService()
        testService.getCategorieByCode("21211515")
            
        }
        void testGetDefaultCategorie() {
        def testInstances = [new CategorieProduit(code : "01", intitule : "alimentaire", categorieParente : null)]
        //mockForConstraintsTests(DroitUtilisateur, testInstances)
        mockDomain(CategorieProduit, testInstances)
        def testService=new CategorieProduitService()
        testService.getDefaultCategorie()
            
        }
}
