package com.choranet.securite

import grails.test.*
import com.choranet.commun.ChoraClientInfo
import com.choranet.compta.RegimeDeclarationTva

class ChoraBarrageServiceTests extends GrailsUnitTestCase {
        
    def grailsApplication
    
    protected void setUp() {
        super.setUp()
        def metadata = [:]
        metadata['app.name'] = 'erpchora'
        metadata['app.version'] = '1.3'
        grailsApplication = [:] 
        grailsApplication.metadata =  metadata 
    }

    protected void tearDown() {
        super.tearDown()
    }

    void testIsPremierAccess() {
        mockDomain(ChoraBarrage)
        def cbs = new ChoraBarrageService()
        assertTrue cbs.isPremierAccess()
    }
    
    void testVerifierExistanceInstance() {        
        mockDomain(ChoraBarrage)
        def cbs = new ChoraBarrageService()
        cbs.grailsApplication = grailsApplication
        cbs.verifierExistanceInstance()
        assertEquals 1, ChoraBarrage.count()
        def cb = ChoraBarrage.list()[0]
        assertNotNull cb
        assertEquals "CHORA INFORMATIQUE", cb.raisonSocial
        assertEquals "erpchora", cb.produit
        assertEquals "1.3", cb.versionProduit
        assertTrue cb.demo
        assertTrue cb.premierAcces
        assertNotNull cb.idInstance
    }
    
    void testGetChoraBarrage() {
        mockDomain(ChoraBarrage)
        def cbs = new ChoraBarrageService()
        cbs.grailsApplication = grailsApplication
        cbs.verifierExistanceInstance()
        def cb = cbs.getChoraBarrage()
        assertNotNull cb
        assertEquals "CHORA INFORMATIQUE", cb.raisonSocial
        assertEquals "erpchora", cb.produit
        assertEquals "1.3", cb.versionProduit
        assertTrue cb.demo
        assertTrue cb.premierAcces
        assertNotNull cb.idInstance
    }
    
    void testIsDemo() {
        mockDomain(ChoraBarrage)
        def cbs = new ChoraBarrageService()
        cbs.grailsApplication = grailsApplication
        cbs.verifierExistanceInstance()
        assertTrue cbs.isDemo()
    }
    
    void testGetDateActivation() {
        mockDomain(ChoraBarrage)
        mockDomain(Cle)
        def cbs = new ChoraBarrageService()
        cbs.grailsApplication = grailsApplication
        cbs.verifierExistanceInstance() 
        assertNotNull cbs.getDateActivation()
    }
    
    void testIsHorlogeSystemNotHacked() {
        mockDomain(ChoraBarrage)
        def cbs = new ChoraBarrageService()
        cbs.grailsApplication = grailsApplication
        cbs.verifierExistanceInstance()   
        assertFalse cbs.isHorlogeSystemNotHacked()
    }
    
    void testPremierAccessEffectue() {
        mockDomain(ChoraBarrage)        
        def utilisateurControl = mockFor(UtilisateurService)
        utilisateurControl.demand.chercherUtilisateur(1..1) {login, pwd -> return new Utilisateur() }
        utilisateurControl.demand.update(1..1) {it -> return it }
        def cci = new ChoraClientInfo(raisonSociale : "CHORA INFORMATIQUE")//, formeJuridique : "SARL", telephone : null, fax : null, email : null, patente : null, rc : null, idF : null, cnss : null, site : null, repertoirBackup : null, adresse : null, codePostale : null, ville : null, pays : null, logoData : (new String("image")).bytes, cachetData : null, entetepiedData : null, copieData : null, regime : rdt)
        def cbs = new ChoraBarrageService()
        cbs.utilisateurService = utilisateurControl.createMock()
        cbs.grailsApplication = grailsApplication
        cbs.premierAccessEffectue(cci)
        assertEquals 1, ChoraBarrage.count()
        def cb = ChoraBarrage.list()[0]
        assertNotNull cb
        assertEquals "CHORA INFORMATIQUE", cb.raisonSocial
        assertEquals "erpchora", cb.produit
        assertEquals "1.3", cb.versionProduit
        assertTrue cb.demo
        assertFalse cb.premierAcces
        assertNotNull cb.idInstance
    }
    
    void testGetNbJoursAvantExpiration() {
        mockDomain(ChoraBarrage)
        def cbs = new ChoraBarrageService()
        cbs.grailsApplication = grailsApplication
        cbs.verifierExistanceInstance()        
        assertEquals 30, cbs.getNbJoursAvantExpiration()
    }
    
    void testActualiserDernierAccess() {
        mockDomain(ChoraBarrage)
        mockLogging(ChoraBarrageService)
        def cbs = new ChoraBarrageService()
        cbs.grailsApplication = grailsApplication
        cbs.verifierExistanceInstance()     
        cbs.actualiserDernierAccess()
        def cb = ChoraBarrage.list()[0]
        assertNotNull cb.dernierAcces
        assertTrue cb.dernierAcces.before(new Date())
    }
}
