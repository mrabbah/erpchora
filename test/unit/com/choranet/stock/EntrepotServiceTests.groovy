package com.choranet.stock

import grails.test.*

class EntrepotServiceTests extends GrailsUnitTestCase {
    protected void setUp() {
        super.setUp()
    }

    protected void tearDown() {
        super.tearDown()
    }

    void testGetDefaultEntrepot() {
        def testInstances = [new Entrepot(code	 : "01", intitule : "ain sebaa", adresse : "rue 26 n°51",tel:"0673327392",modeValorisation:"PRIX_ACHAT",mode:"FIFO")]
        //mockForConstraintsTests(DroitUtilisateur, testInstances)
        mockDomain(Entrepot, testInstances)
        def testService=new EntrepotService()
        testService.getDefaultEntrepot()
        
    }
    
    void testGetEntrepotByTitre() {
        def testInstances = [new Entrepot(code	 : "01", intitule : "ain sebaa", adresse : "rue 26 n°51",tel:"0673327392",modeValorisation:"PRIX_ACHAT",mode:"FIFO")]
        //mockForConstraintsTests(DroitUtilisateur, testInstances)
        mockDomain(Entrepot, testInstances)
        def testService=new EntrepotService()
        testService.getEntrepotByTitre("ain sebaa")
        
    }
}
