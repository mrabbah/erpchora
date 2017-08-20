package com.choranet.commun

import grails.test.*

class ExcelImporterServiceTests extends GroovyTestCase {
    protected void setUp() {
        super.setUp()
    }

    protected void tearDown() {
        super.tearDown()
    }

    void testCreerFichierLogImport() {
        def eis = new ExcelImporterService();
        File f = eis.creerFichierLogImport("test_eis")
        assertNotNull f
        assertTrue f.path.contains("test_eis")
        assertTrue f.path.contains(System.getProperty('user.home'))
    }
    
    void testImporterProduits() {
        //fail "TODO IMPLEMENTER LE TEST"
    }
}
