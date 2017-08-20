package com.choranet.compta

import grails.test.*
import com.choranet.stock.CategorieProduit
class CompteComptableProduitTests extends GrailsUnitTestCase {
    protected void setUp() {
        super.setUp()
    }

    protected void tearDown() {
        super.tearDown()
    }

    void testCompteComptableProduitContraintes() {
        def testInstancescc = [new CompteComptable(identifiant : "compta generale", nom : "bilan", code : "15",type:"compte analytique",parent:null)]
        //mockForConstraintsTests(DroitUtilisateur, testInstances)
        mockDomain(CompteComptable, testInstancescc)
        assertTrue testInstancescc[0].validate()
        testInstancescc[0].save(flush:true)
        def testInstancescp = [new CategorieProduit(code : "21211515", intitule : "alimentaire", categorieParente : null)]
        //mockForConstraintsTests(DroitUtilisateur, testInstances)
        mockDomain(CategorieProduit, testInstancescp)
        assertTrue testInstancescp[0].validate()
        testInstancescp[0].save(flush:true)
        def testInstances = [new CompteComptableProduit(type : "XYZ",compteComptable:testInstancescc[0],categorieProduit:testInstancescp[0])]
        //mockForConstraintsTests(DroitUtilisateur, testInstances)
        mockDomain(CompteComptableProduit, testInstances)
        assertEquals 1, CompteComptableProduit.count()
        assertTrue testInstances[0] instanceof CompteComptableProduit        
        def CompteComptableProduit2 = new CompteComptableProduit()
        assertFalse CompteComptableProduit2.validate()
        assertEquals "nullable", CompteComptableProduit2.errors["type"]
        def CompteComptableProduit4 = new CompteComptableProduit(type : "XYZ4",compteComptable:testInstancescc[0],categorieProduit:testInstancescp[0])
        assertTrue CompteComptableProduit4.validate()
        CompteComptableProduit4.save(flush:true)
        assertEquals 2, CompteComptableProduit.count()
        assertTrue CompteComptableProduit.get(CompteComptableProduit4.id) instanceof CompteComptableProduit
        assertNotNull CompteComptableProduit4.toString()
    }
}
