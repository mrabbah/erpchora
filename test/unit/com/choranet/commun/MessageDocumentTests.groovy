package com.choranet.commun

import grails.test.*

class MessageDocumentTests extends GrailsUnitTestCase {
    protected void setUp() {
        super.setUp()
    }

    protected void tearDown() {
        super.tearDown()
    }

    void testDroitUtilisateurContraintes() {
        def testInstances = [new MessageDocument(entete : null, pied : null, type : "BON_COMMANDE",typeMouvement:"ACHAT")]
        //mockForConstraintsTests(DroitUtilisateur, testInstances)
        mockDomain(MessageDocument, testInstances)
        assertEquals 1, MessageDocument.count()
        assertTrue testInstances[0] instanceof MessageDocument        
        def MessageDocument2 = new MessageDocument()
        assertFalse MessageDocument2.validate()
        assertEquals "nullable", MessageDocument2.errors["type"]
        assertEquals "nullable", MessageDocument2.errors["typeMouvement"]
        def MessageDocument3 = new MessageDocument(entete : "gfcfdhdh", pied : "vghvgghvg", type : "BON_COMMANDE",typeMouvement:"ACHAT")
//        assertEquals "maxSize", MessageDocument3.errors["entete"]
        assertTrue MessageDocument3.validate()
        MessageDocument3.save(flush:true)
        assertEquals 2, MessageDocument.count()
        assertTrue MessageDocument.get(MessageDocument3.id) instanceof MessageDocument
        System.out.println("bkbkjbkjbkb"+MessageDocument3.toString())
        assertNotNull  MessageDocument3.toString()
    }
}
