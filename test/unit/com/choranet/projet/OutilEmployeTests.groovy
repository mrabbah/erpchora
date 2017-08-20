package com.choranet.projet

import grails.test.*
import com.choranet.rh.*
import grails.test.*
import java.util.*
import java.text.*
class OutilEmployeTests extends GrailsUnitTestCase {
    protected void setUp() {
        super.setUp()
    }

    protected void tearDown() {
        super.tearDown()
    }

    void testOutilEmployeContraintes() {
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
        def testInstances2 = [new Calendrier(nbHeuresTravailParJour : gg, nom : "annee 2013", nbJoursTravailParMois :gg1,jourFeriers:testInstances1)]
        //mockForConstraintsTests(DroitUtilisateur, testInstances)
        mockDomain(Calendrier, testInstances2)
        assertTrue testInstances2[0].validate()
        testInstances2[0].save(flush:true)
        
        def testInstances3 = [new Fonction(libelle : "comptable")]
        //mockForConstraintsTests(DroitUtilisateur, testInstances)
        mockDomain(Fonction, testInstances3)
        assertTrue testInstances3[0].validate()
        testInstances3[0].save(flush:true)
        
        
        def num=new Integer(01)
        def th=new Double(12.01)
        def sa=new Double(300.14)
        def testInstances = [new Employe(numero : num, nom : "allali", prenom : "youssef",telephone:"0673327392",tauxHoraire:th,salaire:sa,calendrier:testInstances2[0],fonction:testInstances3[0])]
        //mockForConstraintsTests(DroitUtilisateur, testInstances)
        mockDomain(Employe, testInstances)
        assertTrue testInstances[0].validate()
        testInstances[0].save(flush:true)
        
        def testInstanceso = [new OutilEmploye(numero : new Integer(15), nom : "gaterpillar", coutHoraire : new Double(15.13),employe:testInstances[0])]
        //mockForConstraintsTests(DroitUtilisateur, testInstances)
        mockDomain(OutilEmploye, testInstanceso)
        assertEquals 1, OutilEmploye.count()
        assertTrue testInstanceso[0] instanceof OutilEmploye       
        def OutilEmploye2 = new OutilEmploye()
        assertFalse OutilEmploye2.validate()
        assertEquals "nullable", OutilEmploye2.errors["numero"]
        assertEquals "nullable", OutilEmploye2.errors["nom"]
        assertEquals "nullable", OutilEmploye2.errors["coutHoraire"]
        assertEquals "nullable", OutilEmploye2.errors["employe"]
        def OutilEmploye4 = new OutilEmploye(numero : new Integer(15), nom : "gaterpillar", coutHoraire : new Double(15.13),employe:testInstances[0])
        assertTrue OutilEmploye4.validate()
        OutilEmploye4.save(flush:true)
        assertEquals 2, OutilEmploye.count()
        assertTrue OutilEmploye.get(OutilEmploye4.id) instanceof OutilEmploye
        assertEquals "gaterpillar", OutilEmploye4.toString()
        assertTrue OutilEmploye4.coutHoraire> 0d
    }
}
