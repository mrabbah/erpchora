package com.choranet.commun

import grails.test.*

class ParametrageServiceTests extends GroovyTestCase {
    protected void setUp() {
        super.setUp()
    }

    protected void tearDown() {
        super.tearDown()
    }

    void testGetConfig() {
        ParametrageService ps = new ParametrageService()
        Parametrage result = ps.getConfig()
        assertNotNull result
        assertFalse result.afficherRemiseFacture
        assertFalse result.afficherTvaFacture
        assertFalse result.produitPerissableManaged
        Parametrage result2 = ps.getConfig()
        assertNotNull result2
        assertEquals result.afficherRemiseFacture, result2.afficherRemiseFacture
        assertEquals result.afficherTvaFacture, result2.afficherTvaFacture
        assertEquals result.produitPerissableManaged, result2.produitPerissableManaged
        assertEquals 1, Parametrage.count()
    }
    
    void testIsProduitPerissableManaged() {
        ParametrageService ps = new ParametrageService()
        assertFalse ps.isProduitPerissableManaged()
    }
    
    void testIsMultiEntrepotEnabled() {
        ParametrageService ps = new ParametrageService()
        assertFalse ps.isMultiEntrepotEnabled()
    }
}
