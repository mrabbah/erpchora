package com.choranet.commun

import grails.test.*

class ZoneTests extends GrailsUnitTestCase {
    protected void setUp() {
        super.setUp()
    }

    protected void tearDown() {
        super.tearDown()
    }

    void testZoneContraintes() {
        def testInstances = [new Zone(code : "001", intitule : "saiis")]
        //mockForConstraintsTests(DroitUtilisateur, testInstances)
        mockDomain(Zone, testInstances)
        assertEquals 1, Zone.count()
        assertTrue testInstances[0] instanceof Zone        
        def Zone2 = new Zone()
        assertFalse Zone2.validate()
        assertEquals "nullable", Zone2.errors["code"]
        assertEquals "nullable", Zone2.errors["intitule"]
        def Zone3 = new Zone(code : "001", intitule : "zaiir")
        assertFalse Zone3.validate()
        assertEquals "unique", Zone3.errors["code"]
        def Zone33 = new Zone(code : "002", intitule : "saiis")
        assertFalse Zone33.validate()
        assertEquals "unique", Zone33.errors["intitule"]
        def Zone4 = new Zone(code : "002", intitule : "houze")
        assertTrue Zone4.validate()
        Zone4.save(flush:true)
        assertEquals 2, Zone.count()
        assertTrue Zone.get(Zone4.id) instanceof Zone
        assertEquals "houze", Zone4.toString()
    }
}
