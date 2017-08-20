package com.choranet.securite

import grails.test.*

class ChoraBarrageControllerTests extends GroovyTestCase {
    
    def choraBarrageService
    def utilisateurService
    
    protected void setUp() {
        super.setUp()
    }

    protected void tearDown() {
        super.tearDown()
    }

    void testIndex() {
        def cbc = new ChoraBarrageController()
        cbc.choraBarrageService = choraBarrageService
        cbc.utilisateurService = utilisateurService
        cbc.index()        
        assertEquals "/login.zul", cbc.response.redirectedUrl
    }
    
    void testCheckUsernameNullAndPsswdNull() {
        def cbc = new ChoraBarrageController()
        cbc.choraBarrageService = choraBarrageService
        cbc.utilisateurService = utilisateurService
        cbc.params.j_username = null
        cbc.params.j_password = null
        cbc.check()        
        assertEquals "/login.zul?login_error=true", cbc.response.redirectedUrl
    }
    void testCheckUsernameVideAndPsswdNull() {
        def cbc = new ChoraBarrageController()
        cbc.choraBarrageService = choraBarrageService
        cbc.utilisateurService = utilisateurService
        cbc.params.j_username = ""
        cbc.params.j_password = null
        cbc.check()
        assertEquals "/login.zul?login_error=true", cbc.response.redirectedUrl
    }
    void testCheckPsswdNull() {
        def cbc = new ChoraBarrageController()
        cbc.choraBarrageService = choraBarrageService
        cbc.utilisateurService = utilisateurService
        cbc.params.j_username = "admin"
        cbc.params.j_password = null
        cbc.check()
        assertEquals "/login.zul?login_error=true", cbc.response.redirectedUrl        
    }
    void testCheckUserNotExiste() {
        def cbc = new ChoraBarrageController()
        cbc.choraBarrageService = choraBarrageService
        cbc.utilisateurService = utilisateurService
        cbc.params.j_username = "notexistuser"
        cbc.params.j_password = "notexistuser"
        cbc.check()
        assertEquals "/login.zul?login_error=true", cbc.response.redirectedUrl        
    }
    void testCheckUserExiste() {
        def cbc = new ChoraBarrageController()
        cbc.choraBarrageService = choraBarrageService
        cbc.utilisateurService = utilisateurService
        cbc.params.j_username = "root"
        cbc.params.j_password = "chora20102011"
        cbc.check()
        assertEquals "/zul/main.zul", cbc.response.redirectedUrl
    }
}
