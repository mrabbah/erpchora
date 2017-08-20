package com.choranet.gesticom

import grails.test.*
import com.choranet.commun.*
import com.choranet.compta.*
import com.choranet.securite.*
import com.choranet.stock.*
import com.choranet.projet.*
import com.choranet.rh.*
import org.zkoss.image.AImage
import org.zkoss.image.Image
import com.choranet.gesticom.util.Utilitaire
import java.text.*
class CompteBancaireTests extends GrailsUnitTestCase {
    protected void setUp() {
        super.setUp()
    }

    protected void tearDown() {
        super.tearDown()
    }

    void testCompteBancaireContraintes() {
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
        
        
             def testInstances = [new ModeReglement(code : "005", libelle : "espece")]
        //mockForConstraintsTests(DroitUtilisateur, testInstances)
        mockDomain(ModeReglement, testInstances)
        assertTrue testInstances[0].validate()
        testInstances[0].save(flush:true)
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
        
        
        Image i=Utilitaire.getLogoImage(getClass())
        def byte1=i.getByteData()
        def testInstances4 = [new DroitUtilisateur(droit:"ddd",description:"description",parent:null)]
        def testInstances1g = [new GroupeUtilisateur(intitule:"ddd",description:"decision",droits:null)]
        def testInstances2 = [new RegimeDeclarationTva(libelle:"Déclaration mensuelle à base encaissement",periodicite:"Trimestrielle",faitGenerateur:"Encaissement"),new RegimeDeclarationTva(libelle:"Déclaration trimestrielle à base encaissement",periodicite:"Trimestrielle",faitGenerateur:"Encaissement")]
        def testInstances3 = [  new ChoraClientInfo(raisonSociale : "CHORA INDUSTRIE1", email : "contact@choranet.com", formeJuridique : "SARL",telephone:"+212 522 233 343",fax:"+212 522 233 344",patente:"78/7877",rc:"78965",idF:"45788",cnss:"21/45789",site:"http://www.choranet.com",repertoirBackup:"/home/qllqli",adresse:"11M3 Lot Alhamd Ain Sbaa",codePostale:"20250",ville:"Casablanca",pays:"Maroc",cachetData:null,entetepiedData:null,copieData:null,logoData:byte1,regime:testInstances2[0])]
        def testInstancesu = [new Utilisateur(username : "XYZ", userRealName : "UTILISATEUR_XYZ",passwd :"allali",enabled:true,email:'contact@choranet.com',groupe:testInstances1g[0],societe:testInstances3[0] )]
        //mockForConstraintsTests(Module, testInstances)
        mockDomain(RegimeDeclarationTva, testInstances2)
        mockDomain(GroupeUtilisateur, testInstances1g)
        mockDomain(Utilisateur, testInstancesu)
        mockDomain(ChoraClientInfo, testInstances3)
        mockDomain(DroitUtilisateur, testInstances4)
        assertEquals 1, Utilisateur.count()
        assertTrue testInstancesu[0] instanceof Utilisateur 
        System.out.println("troisieme bbbbbb111")
        def utilisateur2 = new Utilisateur()
        assertFalse utilisateur2.validate()
        assertEquals "nullable", utilisateur2.errors["username"]
        assertEquals "nullable", utilisateur2.errors["userRealName"]
        assertEquals "nullable", utilisateur2.errors["passwd"]
        assertEquals "nullable", utilisateur2.errors["groupe"]
        assertEquals "nullable", utilisateur2.errors["societe"]
        System.out.println("troisieme bbbbbb111 bhbhbh")
        def utilisateur3= new Utilisateur(username : "XYZ", userRealName : "UTILISATEUR_XYZ",passwd :"allali",enabled:true,groupe:testInstances1g[0],societe:testInstances3[0] )
        assertFalse utilisateur3.validate()
        assertEquals "unique", utilisateur3.errors["username"]
        System.out.println("troisieme ggggg")
        def utilisateur4= new Utilisateur(username : "admin1", userRealName : "Super Utilisateur",passwd :"NirfFVL6jts=",email:"contact@choranet.com",enabled:true,groupe:testInstances1g[0],societe:testInstances3[0])
        def f=utilisateur4.validate()
        def grooupe1=new GroupeUtilisateur(intitule:"ddd",description:"decision",droits:null)
        assertTrue testInstances1g[0].validate()
        testInstances1g[0].save(flush:true)
        System.out.println("troisieme ggggg premier111"+testInstances1g[0].validate()+testInstances1g[0].getClass())
        assertTrue testInstances2[0].validate()
        testInstances2[0].save(flush:true)
        System.out.println("troisieme ggggg premier222"+testInstances2[0].validate()+testInstances2[0].getClass())
        assertTrue testInstances3[0].validate()
        testInstances3[0].save(flush:true)
        assertTrue testInstances4[0].validate()
        testInstances4[0].save(flush:true)
        assertTrue testInstancesu[0].validate()
        testInstancesu[0].save(flush:true)
                            
        def testInstancese = [new Entrepot(code	 : "006", intitule : "ain sebaa", adresse : "rue 26 n°51",tel:"0673327392",modeValorisation:"PRIX_ACHAT",mode:"FIFO")]
        //mockForConstraintsTests(DroitUtilisateur, testInstances)
        mockDomain(Entrepot, testInstancese)
        assertTrue testInstancese[0].validate()
        testInstancese[0].save(flush:true)
        
        
       def testInstances9p = [new Partenaire(code : "005", raisonSociale : "celibataire", idFiscal : null,patente:"15165165",registreCommerce:"commerce",cnss:"fk13252152",adresse:"oulfa",codePostal:"20200",telephone:"0673327392",fax:"0632972654",email:"contact@choranet.com",siteWeb:"www.choranet.com",adresseFacturation:"hay el hana",adresseLivraison:"ain sebaii",estClient:new Boolean(true),estFournisseur:new Boolean(false),estParticulier:new Boolean(false),estBlacklist:new Boolean(false),plafond:new Double(12.12),modeRelancePaiement:testInstances2m[0],modeReglementDefaut:testInstances3m[0],echeance:testInstances4e[0],ville:testInstances6v[0],formeJuridique:testInstances7f[0],modeLivraison:testInstances8m[0],categoriePartenaires:testInstances1c,solde:new Double(15.01),compteVerrouile:new Boolean(true),zone:testInstancesz[0],commercial:testInstancesee[0])]        //mockForConstraintsTests(DroitUtilisateur, testInstances)
        //mockForConstraintsTests(DroitUtilisateur, testInstances)
        mockDomain(Partenaire, testInstances9p)
        assertTrue testInstances9p[0].validate()
        testInstances9p[0].save(flush:true)
        
        def testInstancesab = [new AgenceBancaire(nom : "bmce", adresse : "sial", tel : "0522323245")]
        //mockForConstraintsTests(DroitUtilisateur, testInstances)
        mockDomain(AgenceBancaire, testInstancesab)
        assertTrue testInstancesab[0].validate()
        testInstancesab[0].save(flush:true)
        def testInstancescb = [new CompteBancaire(libelle : "axkkxbb", rib : "ab1555", codeSwift :"s548484",agenceBancaire:testInstancesab[0],partenaire:testInstances9p[0])]
        //mockForConstraintsTests(DroitUtilisateur, testInstances)
        mockDomain(CompteBancaire, testInstancescb)
        assertEquals 1, CompteBancaire.count()
        assertTrue testInstancescb[0] instanceof CompteBancaire        
        def CompteBancaire2 = new CompteBancaire()
        assertFalse CompteBancaire2.validate()
        assertEquals "nullable", CompteBancaire2.errors["libelle"]
        assertEquals "nullable", CompteBancaire2.errors["rib"]
        def CompteBancaire3 = new CompteBancaire(libelle : "axkkxbb", rib : "ab1555", codeSwift :"s548484",agenceBancaire:testInstancesab[0],partenaire:testInstances9p[0])
        assertFalse CompteBancaire3.validate()
        assertEquals "unique", CompteBancaire3.errors["libelle"]
        assertEquals "unique", CompteBancaire3.errors["rib"]
//        assertEquals "unique", CompteBancaire3.errors["codeSwift"]
        def CompteBancaire4 = new CompteBancaire(libelle : "axkkxbbc", rib : "ab15555", codeSwift :"s5484844",agenceBancaire:testInstancesab[0],partenaire:testInstances9p[0])
        assertTrue CompteBancaire4.validate()
        CompteBancaire4.save(flush:true)
        assertEquals 2, CompteBancaire.count()
        assertTrue CompteBancaire.get(CompteBancaire4.id) instanceof CompteBancaire
        assertNotNull CompteBancaire4.toString()
    }
}
