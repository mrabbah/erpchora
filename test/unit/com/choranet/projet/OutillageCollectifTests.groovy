package com.choranet.projet

import grails.test.*
import java.util.*
import java.text.*
class OutillageCollectifTests extends GrailsUnitTestCase {
    protected void setUp() {
        super.setUp()
    }

    protected void tearDown() {
        super.tearDown()
    }

   void testOutillageCollectifContraintes() {
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
        assertEquals 1, OutillageCollectif.count()
        assertTrue testInstances[0] instanceof OutillageCollectif       
        def OutillageCollectif2 = new OutillageCollectif()
        assertFalse OutillageCollectif2.validate()
        assertEquals "nullable", OutillageCollectif2.errors["numero"]
        assertEquals "nullable", OutillageCollectif2.errors["designation"]
        assertEquals "nullable", OutillageCollectif2.errors["dateAchat"]
        assertEquals "nullable", OutillageCollectif2.errors["prixAchat"]
        
        def OutillageCollectif4 = new OutillageCollectif(numero : new Integer(12), designation : "fabrication", dateAchat : sqlDate1,prixAchat:deb,dateMiseEnService:sqlDate1,dateFinUtilisation:sqlDate2)
        assertTrue OutillageCollectif4.validate()
        OutillageCollectif4.save(flush:true)
        assertEquals 2, OutillageCollectif.count()
        assertTrue OutillageCollectif.get(OutillageCollectif4.id) instanceof OutillageCollectif
        assertEquals "fabrication", OutillageCollectif4.toString()
    }
}
