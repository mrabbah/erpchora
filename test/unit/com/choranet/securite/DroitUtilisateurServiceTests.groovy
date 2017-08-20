package com.choranet.securite

import grails.test.*

class DroitUtilisateurServiceTests extends GrailsUnitTestCase {
    protected void setUp() {
        super.setUp()
    }

    protected void tearDown() {
        super.tearDown()
    }

    
    void testGetDroitsPrents() {
          def testInstances = [new DroitUtilisateur(droit : "XYZ", description : "DROIT_XYZ", parent : null)]
        //mockForConstraintsTests(DroitUtilisateur, testInstances)
        mockDomain(DroitUtilisateur, testInstances)
        def testService=new DroitUtilisateurService()
        def myCriteria = [
    list : {Closure  cls -> return testInstances}
]
DroitUtilisateur.metaClass.static.createCriteria = { myCriteria }
testService.getDroitsPrents()
        
    }
    
    void testGetDroitsByParent() {
          def testInstances = [new DroitUtilisateur(droit : "XYZ", description : "DROIT_XYZ", parent : null)]
          def testInstances1 = [new DroitUtilisateur(droit : "XYZu", description : "DROIT_XYZu", parent :testInstances[0])]
        //mockForConstraintsTests(DroitUtilisateur, testInstances)
        mockDomain(DroitUtilisateur, testInstances)
        mockDomain(DroitUtilisateur, testInstances1)
        def testService=new DroitUtilisateurService()
        def myCriteria = [
    list : {Closure  cls -> return testInstances1}
]
DroitUtilisateur.metaClass.static.createCriteria = { myCriteria }
     testService.getDroitsByParent(testInstances[0])   
    }
}
