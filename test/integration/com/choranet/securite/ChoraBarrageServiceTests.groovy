package com.choranet.securite

import grails.test.*
import com.choranet.commun.ChoraClientInfo
import com.choranet.compta.RegimeDeclarationTva
import grails.util.Environment

class ChoraBarrageServiceTests extends GroovyTestCase {
    
    def grailsApplication
    def utilisateurService
    
    protected void setUp() {
        super.setUp()
    }

    protected void tearDown() {
        super.tearDown()
    }

    void testPremierAccessEffectue() {
        def cci = (ChoraBarrage.count() == 0)?ChoraClientInfo.build():ChoraClientInfo.list()[0]
        def raisonSociale = cci.raisonSociale
        def cbs = new ChoraBarrageService()
        cbs.utilisateurService = utilisateurService
        cbs.grailsApplication = grailsApplication
        cbs.premierAccessEffectue(cci)        
        assertEquals 1, ChoraBarrage.count()
        def cb = ChoraBarrage.list()[0]
        assertNotNull cb        
        assertEquals raisonSociale, cb.raisonSocial
        assertEquals "choraerp", cb.produit
        assertEquals "1.3", cb.versionProduit
		if(Environment.current == Environment.TEST) {
			assertFalse cb.demo
		} else {
			assertTrue cb.demo
		}        
        assertFalse cb.premierAcces
        assertNotNull cb.idInstance
    }
}
