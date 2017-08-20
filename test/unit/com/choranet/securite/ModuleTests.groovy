package com.choranet.securite

import grails.test.*

class ModuleTests extends GrailsUnitTestCase {
    protected void setUp() {
        super.setUp()
    }

    protected void tearDown() {
        super.tearDown()
    }

    void testModuleContraintes() {
        def testInstances = [new Module(code : "XYZ", intitule : "MODULE_XYZ")]
        //mockForConstraintsTests(Module, testInstances)
        mockDomain(Module, testInstances)
        assertEquals 1, Module.count()
        assertTrue testInstances[0] instanceof Module        
        def module2 = new Module()
        assertFalse module2.validate()
        assertEquals "nullable", module2.errors["code"]
        assertEquals "nullable", module2.errors["intitule"]
        def module3 = new Module(code : "XYZ", intitule : "MODULE_XYZ")
        assertFalse module3.validate()
        assertEquals "unique", module3.errors["code"]
        assertEquals "unique", module3.errors["intitule"]
        def module4 = new Module(code : "ABC", intitule : "MODULE_ABC")
        assertTrue module4.validate()
        module4.save(flush:true)
        assertEquals 2, Module.count()
        assertTrue Module.get(module4.id) instanceof Module
        assertEquals "MODULE_ABC : ABC", module4.toString()
    }
}
