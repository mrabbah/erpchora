package com.choranet.gesticom

import grails.test.*
import com.choranet.commun.*
import com.choranet.compta.*
import com.choranet.securite.*
import com.choranet.stock.*
import com.choranet.rh.*
import com.choranet.projet.*
import org.zkoss.image.AImage
import org.zkoss.image.Image
import com.choranet.gesticom.util.Utilitaire
import java.text.*
class RegleFacturationAutomatiqueTests extends GrailsUnitTestCase {
    protected void setUp() {
        super.setUp()
    }

    protected void tearDown() {
        super.tearDown()
    }

   void testRegleFacturationAutomatiqueContraintes() {
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
        
        def testInstancesz = [new Zone(code : "001", intitule : "saiis")]
        //mockForConstraintsTests(DroitUtilisateur, testInstances)
        mockDomain(Zone, testInstancesz)
        assertTrue testInstancesz[0].validate()
        testInstancesz[0].save(flush:true)
        def gg=new Integer(12)
        def gg1=new Integer(22)
        def bo=new Boolean(true)
        def testInstances111 = [new JourFerier(date : sqlDate1, intitule : "fete independance", annuel :bo,duree:gg)]
        //mockForConstraintsTests(DroitUtilisateur, testInstances)
        mockDomain(JourFerier, testInstances111)
        assertTrue testInstances111[0].validate()
        testInstances111[0].save(flush:true)
        def testInstances222 = [new Calendrier(nbHeuresTravailParJour : gg, nom : "annee 2013", nbJoursTravailParMois :gg1,jourFeriers:testInstances111)]
        //mockForConstraintsTests(DroitUtilisateur, testInstances)
        mockDomain(Calendrier, testInstances222)
        assertTrue testInstances222[0].validate()
        testInstances222[0].save(flush:true)
        
        def testInstances333 = [new Fonction(libelle : "comptable")]
        //mockForConstraintsTests(DroitUtilisateur, testInstances)
        mockDomain(Fonction, testInstances333)
        assertTrue testInstances333[0].validate()
        testInstances333[0].save(flush:true)
        
        
        def num=new Integer(01)
        def th=new Double(12.01)
        def sa=new Double(300.14)
        def testInstancesee = [new Employe(numero : num, nom : "allali", prenom : "youssef",telephone:"0673327392",tauxHoraire:th,salaire:sa,calendrier:testInstances222[0],fonction:testInstances333[0])]
        //mockForConstraintsTests(DroitUtilisateur, testInstances)
        mockDomain(Employe, testInstancesee)
        assertTrue testInstancesee[0].validate()
        testInstancesee[0].save(flush:true)
        
        
       def testInstances1c = [new CategoriePartenaire(libelle : "propr", categorieParente : null)]
        //mockForConstraintsTests(DroitUtilisateur, testInstances)
        mockDomain(CategoriePartenaire, testInstances1c)
        assertTrue testInstances1c[0].validate()
        testInstances1c[0].save(flush:true)
        def testInstances2m = [new ModeRelancePaiement(code : "XYZ01", frequence : new Integer(10), libelle : "facture",corpsMessage:"paiment du reste de la facture")]
        //mockForConstraintsTests(DroitUtilisateur, testInstances)
        mockDomain(ModeRelancePaiement, testInstances2m)
        assertTrue testInstances2m[0].validate()
        testInstances2m[0].save(flush:true)
        def testInstances3m = [new ModeReglement(code : "005", libelle : "espece")]
        //mockForConstraintsTests(DroitUtilisateur, testInstances)
        mockDomain(ModeReglement, testInstances3m)
        assertTrue testInstances3m[0].validate()
        testInstances3m[0].save(flush:true)
        def testInstances4e = [new Echeance(code : new Integer(001), libelle : "date livraison", periodicite : new Integer(30),typeDeclanchement:"DATE_LIVRAISON")]
        //mockForConstraintsTests(DroitUtilisateur, testInstances)
        mockDomain(Echeance, testInstances4e)
        assertTrue testInstances4e[0].validate()
        testInstances4e[0].save(flush:true)
        def testInstances5p = [new Pays(intitule : "maroc")]
        //mockForConstraintsTests(DroitUtilisateur, testInstances)
        mockDomain(Pays, testInstances5p)
        assertTrue testInstances5p[0].validate()
        testInstances5p[0].save(flush:true)
        def testInstances6v = [new Ville(intitule : "AGADIR", pays:testInstances5p[0] )]
        //mockForConstraintsTests(DroitUtilisateur, testInstances)
        mockDomain(Ville, testInstances6v)
        assertTrue testInstances6v[0].validate()
        testInstances6v[0].save(flush:true)
        def testInstances7f = [new FormeJuridique(libelle : "GIE")]
        //mockForConstraintsTests(DroitUtilisateur, testInstances)
        mockDomain(FormeJuridique, testInstances7f)
        assertTrue testInstances7f[0].validate()
        testInstances7f[0].save(flush:true)
        def testInstances8m = [new ModeLivraison(code : "0032", transporteur : "stcr", paiementALaReception : new Boolean(true))]
        //mockForConstraintsTests(DroitUtilisateur, testInstances)
        mockDomain(ModeLivraison, testInstances8m)
        assertTrue testInstances8m[0].validate()
        testInstances8m[0].save(flush:true)
        
        System.out.println("la fin initialisation")
        
       def testInstances9 = [new Partenaire(code : "005", raisonSociale : "celibataire", idFiscal : null,patente:"15165165",registreCommerce:"commerce",cnss:"fk13252152",adresse:"oulfa",codePostal:"20200",telephone:"0673327392",fax:"0632972654",email:"contact@choranet.com",siteWeb:"www.choranet.com",adresseFacturation:"hay el hana",adresseLivraison:"ain sebaii",estClient:new Boolean(true),estFournisseur:new Boolean(false),estParticulier:new Boolean(false),estBlacklist:new Boolean(false),plafond:new Double(12.12),modeRelancePaiement:testInstances2m[0],modeReglementDefaut:testInstances3m[0],echeance:testInstances4e[0],ville:testInstances6v[0],formeJuridique:testInstances7f[0],modeLivraison:testInstances8m[0],categoriePartenaires:testInstances1c,solde:new Double(15.01),compteVerrouile:new Boolean(true),zone:testInstancesz[0],commercial:testInstancesee[0])]        //mockForConstraintsTests(DroitUtilisateur, testInstances)
        //mockForConstraintsTests(DroitUtilisateur, testInstances)
        mockDomain(Partenaire, testInstances9)
        assertTrue testInstances9[0].validate()
        testInstances9[0].save(flush:true)
        def testInstancesr = [new RegleFacturationAutomatique(nombreBlaRegroupe : new Integer(5), libelle : "auto", priorite :new Integer(5),type:"JusquaDateActuelle",partenaires:testInstances9 )]
        //mockForConstraintsTests(DroitUtilisateur, testInstances)
        mockDomain(RegleFacturationAutomatique, testInstancesr)
        assertEquals 1, RegleFacturationAutomatique.count()
        assertTrue testInstancesr[0] instanceof RegleFacturationAutomatique       
        def RegleFacturationAutomatique2 = new RegleFacturationAutomatique()
        assertFalse RegleFacturationAutomatique2.validate()
        assertEquals "nullable", RegleFacturationAutomatique2.errors["nombreBlaRegroupe"]
        assertEquals "nullable", RegleFacturationAutomatique2.errors["libelle"]
        assertEquals "nullable", RegleFacturationAutomatique2.errors["priorite"]
        assertEquals "nullable", RegleFacturationAutomatique2.errors["type"]
        def RegleFacturationAutomatique3 = new RegleFacturationAutomatique(nombreBlaRegroupe : new Integer(5), libelle : "auto", priorite :new Integer(5),type:"JusquaDateActuelle",partenaires:testInstances9 )
        assertFalse RegleFacturationAutomatique3.validate()
        assertEquals "unique", RegleFacturationAutomatique3.errors["libelle"]
        def RegleFacturationAutomatique4 = new RegleFacturationAutomatique(nombreBlaRegroupe : new Integer(5), libelle : "auto1", priorite :new Integer(5),type:"JusquaDateActuelle",partenaires:testInstances9 )
        assertTrue RegleFacturationAutomatique4.validate()
        RegleFacturationAutomatique4.save(flush:true)
        assertEquals 2, RegleFacturationAutomatique.count()
        assertTrue RegleFacturationAutomatique.get(RegleFacturationAutomatique4.id) instanceof RegleFacturationAutomatique
        assertEquals "auto1", RegleFacturationAutomatique4.toString()
        def listype=["JusquaDateActuelle","DernierMois","DernierMois","TroisDerniersMois","NombreBonLivraison"]
        def iterator=(listype[0].equals(RegleFacturationAutomatique4.type)||listype[1].equals(RegleFacturationAutomatique4.type))
        System.out.println("nbbhbhbh"+iterator)
        assertTrue iterator
        assertTrue RegleFacturationAutomatique4.priorite >= 1
    }
}
