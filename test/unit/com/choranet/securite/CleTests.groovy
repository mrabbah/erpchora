package com.choranet.securite

import grails.test.*
import java.util.*
import java.text.*
class CleTests extends GrailsUnitTestCase {
    protected void setUp() {
        super.setUp()
    }

    protected void tearDown() {
        super.tearDown()
    }

   void testCleContraintes() {
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
 
		System.out.println("sqlDate1.toString() : " + sqlDate1.toString());
		System.out.println("sdf_ddMMyyyy.format(sqlDate1) : "
				+ sdf_ddMMyyyy.format(sqlDate1));
		System.out.println("sdf_yyyyMMdd.format(sqlDate1) : "
				+ sdf_yyyyMMdd.format(sqlDate1));
        
        
        def testInstances = [new Cle(cleProduit : "XQSrG4X+I5bU5x3xMskc3QhxIpd5wmFBCmKhXCUB8DcGmxve3XuMnxZ/A6k+cjVnZ480p0OaT/661763roP6HA==", dateActivation : judDate1, dateFinActivation : "jamais")]
        //mockForConstraintsTests(DroitUtilisateur, testInstances)
        mockDomain(Cle, testInstances)
        assertEquals 1, Cle.count()
        assertTrue testInstances[0] instanceof Cle        
        def Cle2 = new Cle()
        assertFalse Cle2.validate()
        assertEquals "nullable", Cle2.errors["cleProduit"]
        assertEquals "nullable", Cle2.errors["dateActivation"]
        assertEquals "nullable", Cle2.errors["dateFinActivation"]
        System.out.println("bhjbhjbhjb")
        def Cle3 = new Cle(cleProduit : "XQSrG4X+I5bU5x3xMskc3QhxIpd5wmFBCmKhXCUB8DcGmxve3XuMnxZ/A6k+cjVnZ480p0OaT/661763roP6HA==", dateActivation : judDate1, dateFinActivation : "jamais12")
        assertFalse Cle3.validate()
        assertEquals "unique", Cle3.errors["cleProduit"]
        def Cle4 = new Cle(cleProduit : "XQSrG4X+I5bU5x3xMskc3QhxIpd5wmFBCmKhXCUB8DcGmxve3XuMnxZ/A6k+cjVnZ480p0OaT/661763roP6HA1==", dateActivation : judDate2, dateFinActivation : "jamais13")
        assertTrue Cle4.validate()
        Cle4.save(flush:true)
        assertEquals 2, Cle.count()
        assertTrue Cle.get(Cle4.id) instanceof Cle
        System.out.println("la date activation"+Cle4.dateActivation)
        assertNotNull  Cle4.toString()
    }
}
