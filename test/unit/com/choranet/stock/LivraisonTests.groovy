package com.choranet.stock

import grails.test.*

import com.choranet.gesticom.*
import grails.test.*
import com.choranet.commun.*
import com.choranet.securite.*
import com.choranet.gesticom.*
import com.choranet.stock.*
import java.text.*
class LivraisonTests extends GrailsUnitTestCase {
    protected void setUp() {
        super.setUp()
    }

    protected void tearDown() {
        super.tearDown()
    }

    void testLivraisonContraintes() {
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
 
		java.sql.Date sqlDate11 = new java.sql.Date(judDate1.getTime());
                java.sql.Date sqlDate22 = new java.sql.Date(judDate2.getTime());
		System.out.println("sqlDate1.toString() : " + sqlDate11.toString());
		System.out.println("sdf_ddMMyyyy.format(sqlDate1) : "
				+ sdf_ddMMyyyy.format(sqlDate11));
		System.out.println("sdf_yyyyMMdd.format(sqlDate1) : "
				+ sdf_yyyyMMdd.format(sqlDate11));
        def testInstances1 = [new CategoriePartenaire(libelle : "propr", categorieParente : null)]
        //mockForConstraintsTests(DroitUtilisateur, testInstances)
        mockDomain(CategoriePartenaire, testInstances1)
        assertTrue testInstances1[0].validate()
        testInstances1[0].save(flush:true)
        def testInstances2 = [new ModeRelancePaiement(code : "XYZ01", frequence : new Integer(10), libelle : "facture",corpsMessage:"paiment du reste de la facture")]
        //mockForConstraintsTests(DroitUtilisateur, testInstances)
        mockDomain(ModeRelancePaiement, testInstances2)
        assertTrue testInstances2[0].validate()
        testInstances2[0].save(flush:true)
        def testInstances3 = [new ModeReglement(code : "005", libelle : "espece")]
        //mockForConstraintsTests(DroitUtilisateur, testInstances)
        mockDomain(ModeReglement, testInstances3)
        assertTrue testInstances3[0].validate()
        testInstances3[0].save(flush:true)
        def testInstances4 = [new Echeance(code : new Integer(001), libelle : "date livraison", periodicite : new Integer(30),typeDeclanchement:"DATE_LIVRAISON")]
        //mockForConstraintsTests(DroitUtilisateur, testInstances)
        mockDomain(Echeance, testInstances4)
        assertTrue testInstances4[0].validate()
        testInstances4[0].save(flush:true)
        def testInstances5 = [new Pays(intitule : "maroc")]
        //mockForConstraintsTests(DroitUtilisateur, testInstances)
        mockDomain(Pays, testInstances5)
        assertTrue testInstances5[0].validate()
        testInstances5[0].save(flush:true)
        def testInstances6 = [new Ville(intitule : "AGADIR", pays:testInstances5[0] )]
        //mockForConstraintsTests(DroitUtilisateur, testInstances)
        mockDomain(Ville, testInstances6)
        assertTrue testInstances6[0].validate()
        testInstances6[0].save(flush:true)
        def testInstances7 = [new FormeJuridique(libelle : "GIE")]
        //mockForConstraintsTests(DroitUtilisateur, testInstances)
        mockDomain(FormeJuridique, testInstances7)
        assertTrue testInstances7[0].validate()
        testInstances7[0].save(flush:true)
        def testInstances8 = [new ModeLivraison(code : "0032", transporteur : "stcr", paiementALaReception : new Boolean(true))]
        //mockForConstraintsTests(DroitUtilisateur, testInstances)
        mockDomain(ModeLivraison, testInstances8)
        assertTrue testInstances8[0].validate()
        testInstances8[0].save(flush:true)
        
        System.out.println("la fin initialisation")
        
        def testInstances9 = [new Partenaire(code : "005", raisonSociale : "celibataire", idFiscal : null,patente:"15165165",registreCommerce:"commerce",cnss:"fk13252152",adresse:"oulfa",codePostal:"20200",telephone:"0673327392",fax:"0632972654",email:"contact@choranet.com",siteWeb:"www.choranet.com",adresseFacturation:"hay el hana",adresseLivraison:"ain sebaii",estClient:new Boolean(true),estFournisseur:new Boolean(false),estParticulier:new Boolean(false),estBlacklist:new Boolean(false),plafond:new Double(12.12),modeRelancePaiement:testInstances2[0],modeReglementDefaut:testInstances3[0],echeance:testInstances4[0],ville:testInstances6[0],formeJuridique:testInstances7[0],modeLivraison:testInstances8[0],categoriePartenaires:testInstances1)]
        //mockForConstraintsTests(DroitUtilisateur, testInstances)
        mockDomain(Partenaire, testInstances9)
        def testInstancesm = [new ModeLivraison(code : "0032", transporteur : "stcr", paiementALaReception : new Boolean(true))]
        //mockForConstraintsTests(DroitUtilisateur, testInstances)
        mockDomain(ModeLivraison, testInstancesm)
        assertTrue testInstancesm[0].validate()
        testInstancesm[0].save(flush:true)
        def testInstances = [new Livraison(numExpedition : "l1555525",nbrColis  : new Integer(0), poidTotal : new Double(0d),frais:new Double(0d),date:sqlDate11,detail:"livraison a domicile",intituleVehicule:"hvjfh564564",immatriculation:"vgvg54654645",modeLivraison:testInstancesm[0],partenaire:testInstances9[0],bonLivraisons:null)]
        //mockForConstraintsTests(DroitUtilisateur, testInstances)
        mockDomain(Livraison, testInstances)
        assertEquals 1, Livraison.count()
        assertTrue testInstances[0] instanceof Livraison       
        def Livraison2 = new Livraison()
        assertFalse Livraison2.validate()
        assertEquals "nullable", Livraison2.errors["numExpedition"]
        def Livraison3 = new Livraison(numExpedition : "l1555525",nbrColis  : new Integer(0), poidTotal : new Double(0d),frais:new Double(0d),date:sqlDate11,detail:"livraison a domicile",intituleVehicule:"hvjfh564564",immatriculation:"vgvg54654645",modeLivraison:testInstancesm[0],partenaire:testInstances9[0],bonLivraisons:null)
        assertFalse Livraison3.validate()
        assertEquals "unique", Livraison3.errors["numExpedition"]
        def Livraison4 = new Livraison(numExpedition : "l1555525v",nbrColis  : new Integer(0), poidTotal : new Double(0d),frais:new Double(0d),date:sqlDate11,detail:"livraison a domicile",intituleVehicule:"hvjfh564564",immatriculation:"vgvg54654645",modeLivraison:testInstancesm[0],partenaire:testInstances9[0],bonLivraisons:null)
        assertTrue Livraison4.validate()
        Livraison4.save(flush:true)
        assertEquals 2, Livraison.count()
        assertTrue Livraison.get(Livraison4.id) instanceof Livraison
        assertEquals "l1555525v", Livraison4.toString()
    }
}
