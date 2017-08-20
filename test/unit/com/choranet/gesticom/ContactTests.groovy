package com.choranet.gesticom


import grails.test.*
import com.choranet.commun.*
import com.choranet.compta.*
import com.choranet.securite.*
import com.choranet.gesticom.*
import com.choranet.stock.*
import com.choranet.projet.*
import com.choranet.rh.*
import java.util.*
import java.text.*

class ContactTests extends GrailsUnitTestCase {
    protected void setUp() {
        super.setUp()
    }

    protected void tearDown() {
        super.tearDown()
    }

    void testContactContraintes() {
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
        def testInstances11 = [new JourFerier(date : sqlDate1, intitule : "fete independance", annuel :bo,duree:gg)]
        //mockForConstraintsTests(DroitUtilisateur, testInstances)
        mockDomain(JourFerier, testInstances11)
        assertTrue testInstances11[0].validate()
        testInstances11[0].save(flush:true)
        def testInstances22 = [new Calendrier(nbHeuresTravailParJour : gg, nom : "annee 2013", nbJoursTravailParMois :gg1,jourFeriers:testInstances11)]
        //mockForConstraintsTests(DroitUtilisateur, testInstances)
        mockDomain(Calendrier, testInstances22)
        assertTrue testInstances22[0].validate()
        testInstances22[0].save(flush:true)
        
        def testInstances33 = [new Fonction(libelle : "comptable")]
        //mockForConstraintsTests(DroitUtilisateur, testInstances)
        mockDomain(Fonction, testInstances33)
        assertTrue testInstances33[0].validate()
        testInstances33[0].save(flush:true)
        
        
        def num=new Integer(01)
        def th=new Double(12.01)
        def sa=new Double(300.14)
        def testInstancese = [new Employe(numero : num, nom : "allali", prenom : "youssef",telephone:"0673327392",tauxHoraire:th,salaire:sa,calendrier:testInstances22[0],fonction:testInstances33[0])]
        //mockForConstraintsTests(DroitUtilisateur, testInstances)
        mockDomain(Employe, testInstancese)
        assertTrue testInstancese[0].validate()
        testInstancese[0].save(flush:true)
        def testInstancesz = [new Zone(code : "001", intitule : "saiis")]
        //mockForConstraintsTests(DroitUtilisateur, testInstances)
        mockDomain(Zone, testInstancesz)
        assertTrue testInstancesz[0].validate()
        testInstancesz[0].save(flush:true)
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
        def testInstances6 = [new Ville(intitule : "AGADIR", pays:testInstances5[0])]
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
        
        def testInstances9 = [new Partenaire(code : "005", raisonSociale : "celibataire", idFiscal : null,patente:"15165165",registreCommerce:"commerce",cnss:"fk13252152",adresse:"oulfa",codePostal:"20200",telephone:"0673327392",fax:"0632972654",email:"contact@choranet.com",siteWeb:"www.choranet.com",adresseFacturation:"hay el hana",adresseLivraison:"ain sebaii",estClient:new Boolean(true),estFournisseur:new Boolean(false),estParticulier:new Boolean(false),estBlacklist:new Boolean(false),plafond:new Double(12.12),modeRelancePaiement:testInstances2[0],modeReglementDefaut:testInstances3[0],echeance:testInstances4[0],ville:testInstances6[0],formeJuridique:testInstances7[0],modeLivraison:testInstances8[0],categoriePartenaires:testInstances1,solde:new Double(15.01),compteVerrouile:new Boolean(true),zone:testInstancesz[0],commercial:testInstancese[0])]
        //mockForConstraintsTests(DroitUtilisateur, testInstances)
        mockDomain(Partenaire, testInstances9)
        assertTrue testInstances9[0].validate()
        testInstances9[0].save(flush:true)
        def testInstancesc = [new Contact(nom : "direct", titre : "information", fonction : "consultant",fixe:"0673327392",portable:"0673327392",email:"contact@choranet.com",partenaire:testInstances9[0])]
        //mockForConstraintsTests(DroitUtilisateur, testInstances)
        mockDomain(Contact, testInstancesc)
        assertEquals 1, Contact.count()
        assertTrue testInstancesc[0] instanceof Contact        
        def Contact2 = new Contact()
        assertFalse Contact2.validate()
        assertEquals "nullable", Contact2.errors["nom"]
        assertEquals "nullable", Contact2.errors["titre"]
        def Contact4 = new Contact(nom : "direct", titre : "information", fonction : "consultant",fixe:"0673327392",portable:"0673327392",email:"contact@choranet.com",partenaire:testInstances9[0])
        assertTrue Contact4.validate()
        Contact4.save(flush:true)
        assertEquals 2, Contact.count()
        assertTrue Contact.get(Contact4.id) instanceof Contact
        assertEquals "information" + " " + "direct" + " : " + "consultant", Contact4.toString()
    }
}
