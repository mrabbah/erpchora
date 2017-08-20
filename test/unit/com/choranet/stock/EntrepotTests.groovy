package com.choranet.stock

import grails.test.*

class EntrepotTests extends GrailsUnitTestCase {
    protected void setUp() {
        super.setUp()
    }

    protected void tearDown() {
        super.tearDown()
    }

    void testEntrepotContraintes() {
        def testInstances = [new Entrepot(code	 : "006", intitule : "ain sebaa", adresse : "rue 26 n°51",tel:"0673327392",modeValorisation:"PRIX_ACHAT",mode:"FIFO")]
        //mockForConstraintsTests(DroitUtilisateur, testInstances)
        mockDomain(Entrepot, testInstances)
        assertEquals 1, Entrepot.count()
        assertTrue testInstances[0] instanceof Entrepot        
        def Entrepot2 = new Entrepot()
        assertFalse Entrepot2.validate()
        assertEquals "nullable", Entrepot2.errors["code"]
        assertEquals "nullable", Entrepot2.errors["intitule"]
        assertEquals "blank", Entrepot2.errors["adresse"]
        assertEquals "nullable", Entrepot2.errors["modeValorisation"]
        def Entrepot3 = new Entrepot(code: "006", intitule : "ain sebaa", adresse : "rue 26 n°51",tel:"0673327392",modeValorisation:"PRIX_ACHAT",mode:"FIFO")
        assertFalse Entrepot3.validate()
        assertEquals "unique", Entrepot3.errors["code"]
        assertEquals "unique", Entrepot3.errors["intitule"]
        def Entrepot4 = new Entrepot(code: "007", intitule : "ain sebaa plage", adresse : "rue 26 n°51",tel:"0673327392",modeValorisation:"PRIX_ACHAT",mode:"FIFO")
        assertTrue Entrepot4.validate()
        Entrepot4.save(flush:true)
        assertEquals 2, Entrepot.count()
        assertTrue Entrepot.get(Entrepot4.id) instanceof Entrepot
        assertEquals "007" + " : " + "ain sebaa plage", Entrepot4.toString()
    }
}
