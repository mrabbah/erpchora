package com.choranet.projet

import grails.test.*
import java.util.*
import java.text.*
class CalendrierTests extends GrailsUnitTestCase {
    protected void setUp() {
        super.setUp()
    }

    protected void tearDown() {
        super.tearDown()
    }

   void testCalendrierTestsContraintes() {
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
        def gg1=new Integer(22)
        def bo=new Boolean(true)
        def testInstances1 = [new JourFerier(date : sqlDate1, intitule : "fete independance", annuel :bo,duree:gg)]
        //mockForConstraintsTests(DroitUtilisateur, testInstances)
        mockDomain(JourFerier, testInstances1)
        assertTrue testInstances1[0].validate()
        testInstances1[0].save(flush:true)
        def testInstances = [new Calendrier(nbHeuresTravailParJour : gg, nom : "annee 2013", nbJoursTravailParMois :gg1,jourFeriers:testInstances1)]
        //mockForConstraintsTests(DroitUtilisateur, testInstances)
        mockDomain(Calendrier, testInstances)
        assertEquals 1, Calendrier.count()
        assertTrue testInstances[0] instanceof Calendrier       
        def Calendrier2 = new Calendrier()
        assertFalse Calendrier2.validate()
        assertEquals "nullable", Calendrier2.errors["nbHeuresTravailParJour"]
        assertEquals "nullable", Calendrier2.errors["nom"]
        assertEquals "nullable", Calendrier2.errors["nbJoursTravailParMois"]
        def Calendrier3 = new Calendrier(nbHeuresTravailParJour : gg, nom : "annee 2013", nbJoursTravailParMois :gg1,jourFeriers:testInstances1)
        assertFalse Calendrier3.validate()
        assertEquals "unique", Calendrier3.errors["nom"]
        def Calendrier4 = new Calendrier(nbHeuresTravailParJour : gg, nom : "annee 2012", nbJoursTravailParMois :gg1,jourFeriers:testInstances1)
        System.out.println("nbheutravailerjour"+Calendrier4.nbHeuresTravailParJour)
        System.out.println("nom"+Calendrier4.nom)
        System.out.println("nom"+Calendrier4.nbJoursTravailParMois)
        System.out.println("nom"+Calendrier4.jourFeriers)
        assertTrue Calendrier4.validate()
        Calendrier4.save(flush:true)
        assertEquals 2, Calendrier.count()
        assertTrue Calendrier.get(Calendrier4.id) instanceof Calendrier
        assertEquals "annee 2012", Calendrier4.toString()
    }
}
