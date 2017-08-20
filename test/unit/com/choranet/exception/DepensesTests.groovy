package com.choranet.exception

import grails.test.*
import java.util.*
import java.text.*
class DepensesTests extends GrailsUnitTestCase {
    protected void setUp() {
        super.setUp()
    }

    protected void tearDown() {
        super.tearDown()
    }

    void testDepensesContraintes() {
        def ex=new Integer(30032013)
        final SimpleDateFormat sdf_yyyyMMdd = new SimpleDateFormat("yyyy-MM-dd");
		final SimpleDateFormat sdf_ddMMyyyy = new SimpleDateFormat("dd/MM/yyyy");
 
		java.util.Date judDate1 = new java.util.Date();
                java.util.Date judDate2 = new java.util.Date();
		try {
			judDate1 = sdf_ddMMyyyy.parse("01/10/2005");
                        judDate2 = sdf_ddMMyyyy.parse("03/10/2005");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 
		java.sql.Date sqlDate1 = new java.sql.Date(judDate1.getTime());
                java.sql.Date sqlDate2 = new java.sql.Date(judDate2.getTime());
		System.out.println("sqlDate1.toString() : " + sqlDate1.toString());
		System.out.println("sdf_ddMMyyyy.format(sqlDate1) : "
				+ sdf_ddMMyyyy.format(sqlDate1));
		System.out.println("sdf_yyyyMMdd.format(sqlDate1) : "
				+ sdf_yyyyMMdd.format(sqlDate1));
        def gg=new Double(12.45)
        def testInstances1 = [new NatureDeCharge(libelle: "moyenne", chargeBasic : gg, type : "VARIABLE")]
        //mockForConstraintsTests(DroitUtilisateur, testInstances)
        mockDomain(NatureDeCharge, testInstances1)
        assertTrue testInstances1[0].validate()
        testInstances1[0].save(flush:true)
        def testInstances = [new Depenses(date	 : sqlDate1, detail : "construction", montant : gg,natureDeCharge:testInstances1[0])]
        //mockForConstraintsTests(DroitUtilisateur, testInstances)
        mockDomain(Depenses, testInstances)
        assertEquals 1, Depenses.count()
        assertTrue testInstances[0] instanceof Depenses        
        def Depenses2 = new Depenses()
        assertFalse Depenses2.validate()
        assertEquals "nullable", Depenses2.errors["date"]
        assertEquals "nullable", Depenses2.errors["natureDeCharge"]
        def Depenses4 = new Depenses(date	 : sqlDate1, detail : "construction", montant : gg,natureDeCharge:testInstances1[0])
        assertTrue Depenses4.validate()
        Depenses4.save(flush:true)
        assertEquals 2, Depenses.count()
        assertTrue Depenses.get(Depenses4.id) instanceof Depenses
        assertNotNull  Depenses4.toString()
    }
}
