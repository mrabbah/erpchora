package com.choranet.compta

import grails.test.*
import java.util.*
import java.text.*
class PeriodeTests extends GrailsUnitTestCase {
    protected void setUp() {
        super.setUp()
    }

    protected void tearDown() {
        super.tearDown()
    }

    void testPeriodeContraintes() {
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
        def testInstances2 = [ new Exercice(dateDebut :sqlDate1 , intitule : "trimestriel", dateFin :sqlDate2)]
        mockDomain(Exercice, testInstances2)
        assertTrue testInstances2[0].validate()
        testInstances2[0].save(flush:true)
        def testInstances = [new Periode(intitule : "troisieme", exercice :testInstances2[0])]
        //mockForConstraintsTests(DroitUtilisateur, testInstances)
        mockDomain(Periode, testInstances)
        assertEquals 1, Periode.count()
        assertTrue testInstances[0] instanceof Periode        
        def Periode2 = new Periode()
        assertFalse Periode2.validate()
        assertEquals "nullable", Periode2.errors["exercice"]
        assertEquals "nullable", Periode2.errors["intitule"]
        def Periode4 = new Periode(intitule : "premiere", exercice :testInstances2[0])
        assertTrue Periode4.validate()
        Periode4.save(flush:true)
        assertEquals 2, Periode.count()
        assertTrue Periode.get(Periode4.id) instanceof Periode
        assertNotNull Periode4.toString()
    }
}
