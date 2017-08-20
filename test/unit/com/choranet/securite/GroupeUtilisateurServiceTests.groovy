package com.choranet.securite

import grails.test.*

class GroupeUtilisateurServiceTests extends GrailsUnitTestCase {
    protected void setUp() {
        super.setUp()
    }

    void testGetGroupeByAuthority(){
         def testInstances = [new GroupeUtilisateur(description : "XYZ", intitule : "DROIT_XYZ", droits : null)]
        //mockForConstraintsTests(DroitUtilisateur, testInstances)
        mockDomain(GroupeUtilisateur, testInstances)
        def testService=new GroupeUtilisateurService()
        testService.getGroupeByAuthority("DROIT_XYZ")
        
    }
}
