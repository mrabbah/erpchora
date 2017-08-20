package com.choranet.projet

import grails.test.*
import java.util.*
import java.text.*
class OutillageCollectifServiceTests extends GrailsUnitTestCase {
    protected void setUp() {
        super.setUp()
    }

    protected void tearDown() {
        super.tearDown()
    }

     void testGetOutillageToujoursUtiliser() {
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
        def deb=new Double(13.8)
        def testInstances = [new OutillageCollectif(numero : new Integer(12), designation : "fabrication", dateAchat : sqlDate1,prixAchat:deb,dateMiseEnService:sqlDate2,dateFinUtilisation:sqlDate2)]
        //mockForConstraintsTests(DroitUtilisateur, testInstances)
        mockDomain(OutillageCollectif, testInstances) 
        def testService=new OutillageCollectifService()
        testService.getOutillageToujoursUtiliser()
        
        
    }
}
