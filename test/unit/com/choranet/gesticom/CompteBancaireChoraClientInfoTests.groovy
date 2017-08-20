package com.choranet.gesticom

import grails.test.*

class CompteBancaireChoraClientInfoTests extends GrailsUnitTestCase {
    protected void setUp() {
        super.setUp()
    }

    protected void tearDown() {
        super.tearDown()
    }

    void testCompteBancaireChoraClientInfoContraintes() {
        def testInstancesab = [new AgenceBancaire(nom : "bmce", adresse : "sial", tel : "0522323245")]
        //mockForConstraintsTests(DroitUtilisateur, testInstances)
        mockDomain(AgenceBancaire, testInstancesab)
        assertTrue testInstancesab[0].validate()
        testInstancesab[0].save(flush:true)
        def testInstances = [new CompteBancaireChoraClientInfo(libelle : "info", rib : "2115", codeSwift :"cs554",solde:new Double(15.02),soldePrevu:new Double(20.14),agenceBancaire:testInstancesab[0])]
        //mockForConstraintsTests(DroitUtilisateur, testInstances)
        mockDomain(CompteBancaireChoraClientInfo, testInstances)
        assertEquals 1, CompteBancaireChoraClientInfo.count()
        assertTrue testInstances[0] instanceof CompteBancaireChoraClientInfo       
        def CompteBancaireChoraClientInfo2 = new CompteBancaireChoraClientInfo()
        assertFalse CompteBancaireChoraClientInfo2.validate()
        assertEquals "nullable", CompteBancaireChoraClientInfo2.errors["libelle"]
        assertEquals "nullable", CompteBancaireChoraClientInfo2.errors["solde"]
        assertEquals "nullable", CompteBancaireChoraClientInfo2.errors["soldePrevu"]
        def CompteBancaireChoraClientInfo4 = new CompteBancaireChoraClientInfo(libelle : "info1", rib : "21155", codeSwift :"cs554d",solde:new Double(15.02),soldePrevu:new Double(20.14),agenceBancaire:testInstancesab[0])
        assertTrue CompteBancaireChoraClientInfo4.validate()
        CompteBancaireChoraClientInfo4.save(flush:true)
        assertEquals 2, CompteBancaireChoraClientInfo4.count()
        assertTrue CompteBancaireChoraClientInfo.get(CompteBancaireChoraClientInfo4.id) instanceof CompteBancaireChoraClientInfo
        assertNotNull CompteBancaireChoraClientInfo4.toString()
    }
}
