package com.choranet.commun

import grails.test.*

class DocumentNumeriqueTests extends GrailsUnitTestCase {
    protected void setUp() {
        super.setUp()
    }

    protected void tearDown() {
        super.tearDown()
    }

    void testModuleContraintes() {
        def testInstances = [new DocumentNumerique(titre : "Doc1", type : "PDF", donnees : null)]
        //mockForConstraintsTests(DocumentNumerique, testInstances)
        mockDomain(DocumentNumerique, testInstances)
        assertEquals 1, DocumentNumerique.count()
        assertTrue testInstances[0] instanceof DocumentNumerique        
        def doc2 = new DocumentNumerique()
        assertFalse doc2.validate()
        assertEquals "nullable", doc2.errors["titre"]
        assertEquals "nullable", doc2.errors["type"]
        def doc4 = new DocumentNumerique(titre : "Doc2", type : "TXT", donnees : new String("Text test").bytes)
        assertTrue doc4.validate()
        doc4.save(flush:true)
        assertEquals 2, DocumentNumerique.count()
        assertTrue DocumentNumerique.get(doc4.id) instanceof DocumentNumerique
        assertNotNull doc4.toString()
    }
}
