package com.choranet.exception

import com.choranet.rh.*
import com.choranet.projet.*
import grails.test.*
import java.util.*
import java.text.*
class PaiementJblTests extends GrailsUnitTestCase {
    protected void setUp() {
        super.setUp()
    }

    protected void tearDown() {
        super.tearDown()
    }

    void testPaiementJblContraintes() {
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
        def gg1=new Double(12.45)
        
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
        mockDomain(Employe, testInstances)
        assertTrue testInstances[0].validate()
        testInstances[0].save(flush:true)
        def testInstances11 = [new PaiementJbl(annee : new Integer(2010), mois : "Janvier", joursTravailles : gg,joursAbscences:gg,avance:gg1,retenuVetement:gg1,heuresSupp:gg1,retenuAbscence:gg1,fraisDeplacement:gg1,rappel:gg1,dateAvance:sqlDate1,dateSalaire:sqlDate2,salaire:gg1,employe:testInstances[0])]
        //mockForConstraintsTests(DroitUtilisateur, testInstances)
        mockDomain(PaiementJbl, testInstances11)
        assertEquals 1, PaiementJbl.count()
        assertTrue testInstances11[0] instanceof PaiementJbl        
        def PaiementJbl2 = new PaiementJbl()
        assertFalse PaiementJbl2.validate()
        assertEquals "nullable", PaiementJbl2.errors["annee"]
        assertEquals "nullable", PaiementJbl2.errors["mois"]
        assertEquals "nullable", PaiementJbl2.errors["joursTravailles"]
        assertEquals "nullable", PaiementJbl2.errors["joursAbscences"]
        assertEquals "nullable", PaiementJbl2.errors["avance"]
        assertEquals "nullable", PaiementJbl2.errors["retenuVetement"]
        assertEquals "nullable", PaiementJbl2.errors["heuresSupp"]
        assertEquals "nullable", PaiementJbl2.errors["fraisDeplacement"]
        assertEquals "nullable", PaiementJbl2.errors["rappel"]
        assertEquals "nullable", PaiementJbl2.errors["salaire"]
        assertEquals "nullable", PaiementJbl2.errors["employe"]
        
        def PaiementJbl4 = new PaiementJbl(annee : new Integer(2010), mois : "Janvier", joursTravailles : gg,joursAbscences:gg,avance:gg1,retenuVetement:gg1,heuresSupp:gg1,retenuAbscence:gg1,fraisDeplacement:gg1,rappel:gg1,dateAvance:sqlDate1,dateSalaire:sqlDate2,salaire:gg1,employe:testInstances[0])
        assertTrue PaiementJbl4.validate()
        PaiementJbl4.save(flush:true)
        assertEquals 2, PaiementJbl.count()
        assertTrue PaiementJbl.get(PaiementJbl4.id) instanceof PaiementJbl
        assertNotNull  PaiementJbl4.toString()
    }
}
