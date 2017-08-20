package com.choranet.gesticom

import grails.test.*

class CategoriePartenaireServiceTests extends GrailsUnitTestCase {
    protected void setUp() {
        super.setUp()
    }

    protected void tearDown() {
        super.tearDown()
    }

    void testGetDefaultCategorie() {
        def testInstances = [new CategoriePartenaire(libelle : "Partenaires", categorieParente : null)]
        //mockForConstraintsTests(DroitUtilisateur, testInstances)
        mockDomain(CategoriePartenaire, testInstances)
        def testservice=new CategoriePartenaireService()
        def res=testservice.getDefaultCategorie()
        System.out.println("default categorie "+res)
    }
    void testGetCategorieClients() {
        def testInstances = [new CategoriePartenaire(libelle : "Clients", categorieParente : null)]
        //mockForConstraintsTests(DroitUtilisateur, testInstances)
        mockDomain(CategoriePartenaire, testInstances)
        def testservice=new CategoriePartenaireService()
        
        def res=testservice.getCategorieClients()
        System.out.println("client categorie "+res)
    }
    void testGetCategorieFournisseurs() {
        def testInstances = [new CategoriePartenaire(libelle : "Fournisseurs", categorieParente : null)]
        //mockForConstraintsTests(DroitUtilisateur, testInstances)
        mockDomain(CategoriePartenaire, testInstances)
        def testservice=new CategoriePartenaireService()
        def res=testservice.getCategorieFournisseurs()
        System.out.println("fournisseur categorie "+res)
    }
    void testGetCategorieByLibelle() {
        
        def testInstances = [new CategoriePartenaire(libelle : "Fournisseurs", categorieParente : null)]
        //mockForConstraintsTests(DroitUtilisateur, testInstances)
        mockDomain(CategoriePartenaire, testInstances)
        def testservice=new CategoriePartenaireService()
        def par="Fournisseurs"
        def res=testservice.getCategorieByLibelle(par)
        System.out.println("categorie by libelle"+res)
    }
    
}
