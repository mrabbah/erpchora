package com.choranet.projet
import grails.test.*
import java.util.*
import java.text.*
class JourFerierTests extends GrailsUnitTestCase {
    protected void setUp() {
        super.setUp()
    }

    protected void tearDown() {
        super.tearDown()
    }

    void testJourFerierTestsContraintes() {
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
        def gg=new Integer(12)
        def bo=new Boolean(true)
        def testInstances = [new JourFerier(date : sqlDate1, intitule : "fete independance", annuel :bo,duree:gg)]
        //mockForConstraintsTests(DroitUtilisateur, testInstances)
        mockDomain(JourFerier, testInstances)
        assertEquals 1, JourFerier.count()
        assertTrue testInstances[0] instanceof JourFerier       
        def JourFerier2 = new JourFerier()
        assertFalse JourFerier2.validate()
        assertEquals "nullable", JourFerier2.errors["date"]
        assertEquals "nullable", JourFerier2.errors["intitule"]
        assertEquals "nullable", JourFerier2.errors["annuel"]
        assertEquals "nullable", JourFerier2.errors["duree"]
        def JourFerier4 = new JourFerier(date : sqlDate2, intitule : "fete aid adha", annuel :bo,duree:gg)
        assertTrue JourFerier4.validate()
        JourFerier4.save(flush:true)
        assertEquals 2, JourFerier.count()
        assertTrue JourFerier.get(JourFerier4.id) instanceof JourFerier
        assertEquals "fete aid adha", JourFerier4.toString()
    }
}
