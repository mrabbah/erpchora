package com.choranet.compta

import grails.test.*
import java.util.*
import java.text.*
class EcritureTests extends GrailsUnitTestCase {
    protected void setUp() {
        super.setUp()
    }

    protected void tearDown() {
        super.tearDown()
    }

    void testEcritureContraintes() {
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
        def cred=new Double(13.8)
        def md=new Double(23.8)
        def num=new Integer(10)
        def date1=sdf_yyyyMMdd.format(sqlDate1)
        System.out.println("la date adequate est"+date1+"  "+sqlDate1+ "   "+sqlDate2)
        def testInstances1 = [ new CompteComptable(identifiant : "compta generale trime", nom : "plan juridique", code : "15",type:"compte analytique",parent:null)]
        
        def testInstances2 = [ new Exercice(dateDebut :sqlDate1 , intitule : "trimestriel", dateFin :sqlDate2)]
        mockDomain(Exercice, testInstances2)
        assertTrue testInstances2[0].validate()
        testInstances2[0].save(flush:true)
        def testInstances3 = [ new Periode(intitule : "troisieme", exercice :testInstances2[0])]
        mockDomain(Periode, testInstances3)
        assertTrue testInstances3[0].validate()
        testInstances3[0].save(flush:true)
        System.out.println("apres le test de excerice et periode")
        def testInstances = [new Ecriture(description : "financiere", debit : deb, reference : "ecriture",credit:cred,date:sqlDate1,numero:num,dateEcheance:sqlDate2,dateCreance:sqlDate1,montantDevise:md,paiement:null,ligneProduit:null,devise:null,compteComptable:testInstances1[0],periode:testInstances3[0])]
        //mockForConstraintsTests(DroitUtilisateur, testInstances)
        mockDomain(Ecriture, testInstances)
        mockDomain(CompteComptable,testInstances1)
        assertTrue testInstances1[0].validate()
        testInstances1[0].save(flush:true)
        assertEquals 1, Ecriture.count()
        assertTrue testInstances[0] instanceof Ecriture       
        def Ecriture2 = new Ecriture()
        assertFalse Ecriture2.validate()
        assertEquals "nullable", Ecriture2.errors["compteComptable"]
        assertEquals "nullable", Ecriture2.errors["date"]
        assertEquals "nullable", Ecriture2.errors["numero"]
        def comptecomptable1=new CompteComptable(identifiant : "compta generale trime", nom : "plan juridique", code : "15",type:"compte analytique",parent:null)
        assertTrue testInstances1[0].validate()
        testInstances1[0].save(flush:true)
       System.out.println(sqlDate1)
        def Ecriture4= new Ecriture(description : 'hhh', debit : new Double(13), reference : 'nnn',credit:new Double(12.23),date:sqlDate1,numero:new Integer(14),dateEcheance:sqlDate2,dateCreance:sqlDate2,montantDevise:new Double(10.12),paiement:null,ligneProduit:null,devise:null,compteComptable:testInstances1[0],periode:testInstances3[0])
        
        System.out.println("description "+Ecriture4.description)
        System.out.println("debit "+Ecriture4.debit)
        System.out.println("reference "+Ecriture4.reference)
        System.out.println("credit "+Ecriture4.credit)
        System.out.println("date "+Ecriture4.date)
        System.out.println("numero "+Ecriture4.numero)
        System.out.println("dateEcheance "+Ecriture4.dateEcheance)
        System.out.println("dateCreance "+Ecriture4.dateCreance)
        System.out.println("montantDevise "+Ecriture4.montantDevise)
        
        assertTrue Ecriture4.validate()
        Ecriture4.save(flush:true)
        assertEquals 2, Ecriture.count()
        assertTrue Ecriture.get(Ecriture4.id) instanceof Ecriture
        assertNotNull Ecriture4.toString()
    }
}
