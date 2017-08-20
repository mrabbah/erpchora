package com.choranet.compta

import grails.test.*
import java.util.*
import java.text.*
class DeclarationTvaTests extends GrailsUnitTestCase {
    protected void setUp() {
        super.setUp()
    }

    protected void tearDown() {
        super.tearDown()
    }

    void testDeclarationTvaContraintes() {
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
 
		System.out.println("sqlDate1.toString() : " + sqlDate1.toString());
		System.out.println("sdf_ddMMyyyy.format(sqlDate1) : "
				+ sdf_ddMMyyyy.format(sqlDate1));
		System.out.println("sdf_yyyyMMdd.format(sqlDate1) : "
				+ sdf_yyyyMMdd.format(sqlDate1));
        def testInstances = [new DeclarationTva(numDeclaration : "001", date : sqlDate1, periode : "03/02/2012",exercise:ex)]
        //mockForConstraintsTests(DroitUtilisateur, testInstances)
        mockDomain(DeclarationTva, testInstances)
        assertEquals 1, DeclarationTva.count()
        assertTrue testInstances[0] instanceof DeclarationTva       
        def DeclarationTva2 = new DeclarationTva()
        assertFalse DeclarationTva2.validate()
        assertEquals "nullable", DeclarationTva2.errors["numDeclaration"]
        assertEquals "nullable", DeclarationTva2.errors["date"]
        assertEquals "nullable", DeclarationTva2.errors["periode"]
        assertEquals "nullable", DeclarationTva2.errors["exercise"]
        def DeclarationTva3 = new DeclarationTva(numDeclaration : "001", date : sqlDate1, periode : "03/02/2012",exercise:ex)
        assertFalse DeclarationTva3.validate()
        assertEquals "unique", DeclarationTva3.errors["numDeclaration"]
        def DeclarationTva4 = new DeclarationTva(numDeclaration : "002", date : sqlDate1, periode : "03/02/2012",exercise:ex)
        assertTrue DeclarationTva4.validate()
        DeclarationTva4.save(flush:true)
        assertEquals 2, DeclarationTva.count()
        assertTrue DeclarationTva.get(DeclarationTva4.id) instanceof DeclarationTva
        assertEquals "002", DeclarationTva4.toString()
    }
}
