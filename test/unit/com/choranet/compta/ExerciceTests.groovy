package com.choranet.compta

import grails.test.*
import java.util.*
import java.text.*
class ExerciceTests extends GrailsUnitTestCase {
    protected void setUp() {
        super.setUp()
    }

    protected void tearDown() {
        super.tearDown()
    }

    void testExerciceContraintes() {
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
        def testInstances = [new Exercice(dateDebut : sqlDate1, intitule : "trimestriel", dateFin : sqlDate2)]
        //mockForConstraintsTests(DroitUtilisateur, testInstances)
        mockDomain(Exercice, testInstances)
        assertEquals 1, Exercice.count()
        assertTrue testInstances[0] instanceof Exercice        
        def Exercice2 = new Exercice()
        assertFalse Exercice2.validate()
        assertEquals "nullable", Exercice2.errors["dateDebut"]
        assertEquals "nullable", Exercice2.errors["intitule"]
        assertEquals "nullable", Exercice2.errors["dateFin"]
        def Exercice4 = new Exercice(dateDebut : sqlDate1, intitule : "trimestriel", dateFin : sqlDate2)
        assertTrue Exercice4.validate()
        Exercice4.save(flush:true)
        assertEquals 2, Exercice.count()
        assertTrue Exercice.get(Exercice4.id) instanceof Exercice
        assertNotNull  Exercice4.toString()
    }
}
