package com.choranet.exception

import grails.test.*

class NatureDeChargeTests extends GrailsUnitTestCase {
    protected void setUp() {
        super.setUp()
    }

    protected void tearDown() {
        super.tearDown()
    }

    void testNatureDeChargeContraintes() {
        def gg=new Double(12.45)
        def testInstances = [new NatureDeCharge(libelle: "moyenne", chargeBasic : gg, type : "VARIABLE")]
        //mockForConstraintsTests(DroitUtilisateur, testInstances)
        mockDomain(NatureDeCharge, testInstances)
        assertEquals 1, NatureDeCharge.count()
        assertTrue testInstances[0] instanceof NatureDeCharge       
        def NatureDeCharge2 = new NatureDeCharge()
        assertFalse NatureDeCharge2.validate()
        assertEquals "nullable", NatureDeCharge2.errors["libelle"]
        assertEquals "nullable", NatureDeCharge2.errors["chargeBasic"]
        assertEquals "nullable", NatureDeCharge2.errors["type"]
        def NatureDeCharge3 = new NatureDeCharge(libelle: "moyenne", chargeBasic : new Double(12.12), type : "VARIABLE")
        assertFalse NatureDeCharge3.validate()
        assertEquals "unique", NatureDeCharge3.errors["libelle"]
        def NatureDeCharge4 = new NatureDeCharge(libelle: "haute", chargeBasic : gg, type : "FIXE")
        assertTrue NatureDeCharge4.validate()
        NatureDeCharge4.save(flush:true)
        assertEquals 2, NatureDeCharge.count()
        assertTrue NatureDeCharge.get(NatureDeCharge4.id) instanceof NatureDeCharge
        assertEquals "haute" + " : " + "FIXE", NatureDeCharge4.toString()
    }
}
