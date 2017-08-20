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

class BonLivraisonServiceTests extends GrailsUnitTestCase {
    protected void setUp() {
        super.setUp()
    }

    protected void tearDown() {
        super.tearDown()
    }

    void  testGetBonsLivraisonBonsRetourPartenaire() {
        
        
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
        def testInstancesz = [new Zone(code : "001", intitule : "saiis")]
        //mockForConstraintsTests(DroitUtilisateur, testInstances)
        mockDomain(Zone, testInstancesz)
        assertTrue testInstancesz[0].validate()
        testInstancesz[0].save(flush:true)
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
        def testInstancesee = [new Employe(numero : num, nom : "allali", prenom : "youssef",telephone:"0673327392",tauxHoraire:th,salaire:sa,calendrier:testInstances22[0],fonction:testInstances33[0])]
        //mockForConstraintsTests(DroitUtilisateur, testInstances)
        mockDomain(Employe, testInstancesee)
        assertTrue testInstancesee[0].validate()
        testInstancesee[0].save(flush:true)
        
        def testInstances9 = [new Partenaire(code : "005", raisonSociale : "celibataire", idFiscal : null,patente:"15165165",registreCommerce:"commerce",cnss:"fk13252152",adresse:"oulfa",codePostal:"20200",telephone:"0673327392",fax:"0632972654",email:"contact@choranet.com",siteWeb:"www.choranet.com",adresseFacturation:"hay el hana",adresseLivraison:"ain sebaii",estClient:new Boolean(true),estFournisseur:new Boolean(false),estParticulier:new Boolean(false),estBlacklist:new Boolean(false),plafond:new Double(12.12),modeRelancePaiement:testInstances2m[0],modeReglementDefaut:testInstances3m[0],echeance:testInstances4e[0],ville:testInstances6v[0],formeJuridique:testInstances7f[0],modeLivraison:testInstances8m[0],categoriePartenaires:testInstances1c,solde:new Double(15.01),compteVerrouile:new Boolean(true),zone:testInstancesz[0],commercial:testInstancesee[0])]
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
        
        
        def testInstances9p = [new Partenaire(code : "005", raisonSociale : "celibataire", idFiscal : null,patente:"15165165",registreCommerce:"commerce",cnss:"fk13252152",adresse:"oulfa",codePostal:"20200",telephone:"0673327392",fax:"0632972654",email:"contact@choranet.com",siteWeb:"www.choranet.com",adresseFacturation:"hay el hana",adresseLivraison:"ain sebaii",estClient:new Boolean(true),estFournisseur:new Boolean(false),estParticulier:new Boolean(false),estBlacklist:new Boolean(false),plafond:new Double(12.12),modeRelancePaiement:testInstances2m[0],modeReglementDefaut:testInstances3m[0],echeance:testInstances4[0],ville:testInstances6v[0],formeJuridique:testInstances7f[0],modeLivraison:testInstances8m[0],categoriePartenaires:testInstances1c,solde:new Double(15.01),compteVerrouile:new Boolean(true),zone:testInstancesz[0],commercial:testInstancesee[0])]
        //mockForConstraintsTests(DroitUtilisateur, testInstances)
        mockDomain(Partenaire, testInstances9p)
        assertTrue testInstances9p[0].validate()
        testInstances9p[0].save(flush:true)
        def testInstances10 = [new Entrepot(code: "006", intitule : "ain sebaa", adresse : "rue 26 n°51",tel:"0673327392",modeValorisation:"PRIX_ACHAT",mode:"FIFO")]
        //mockForConstraintsTests(DroitUtilisateur, testInstances)
        mockDomain(Entrepot, testInstances10)
        assertTrue testInstances10[0].validate()
        testInstances10[0].save(flush:true)
         def testInstances111 = [new Nature(libelle : "normale")]
        //mockForConstraintsTests(DroitUtilisateur, testInstances)
        mockDomain(Nature, testInstances111)
        assertTrue testInstances111[0].validate()
        testInstances111[0].save(flush:true)
        def testInstances12 = [new CategorieProduit(code : "21211515", intitule : "alimentaire", categorieParente : null)]
        //mockForConstraintsTests(DroitUtilisateur, testInstances)
        mockDomain(CategorieProduit, testInstances12)
        assertTrue testInstances12[0].validate()
        testInstances12[0].save(flush:true)
        def testInstances13 = [new UniteMesure(libelle : "metre")]
        //mockForConstraintsTests(DroitUtilisateur, testInstances)
        mockDomain(UniteMesure, testInstances13)
        assertTrue testInstances13[0].validate()
        testInstances13[0].save(flush:true)
        def j=new Integer(20)
         def j1=new Integer(25)
        def testInstances14 = [new RegimeTVA(libelle : "20%", taux : j)]
        //mockForConstraintsTests(DroitUtilisateur, testInstances)
        mockDomain(RegimeTVA, testInstances14)
        assertTrue testInstances14[0].validate()
        testInstances14[0].save(flush:true)
        
        
        
        
        
        def testInstances20 = [new Produit(code : "0151513251", codebarre : "xxl01222",profil:"alimentaire",designation:"marjane",prixRevient:new Double(14.12),prixVenteStandard:new Double(14.12),poids:new Double(14.12),prixAchat:new Double(14.12),prixMoyenPendere:new Double(14.12),perissable:new Boolean(false),temperature_conservation:new Double(52.12),imageProd:byte1,trans_imageProd:i,empReception:testInstances10[0],nature:testInstances111[0],categorieProduit:testInstances12[0],uniteMesure:testInstances13[0],regimeTVA:testInstances14[0],produitParent:null,fournisseurPref:testInstances9[0])]
        //mockForConstraintsTests(DroitUtilisateur, testInstances)
        mockDomain(Produit, testInstances20)
        assertTrue testInstances20[0].validate()
        testInstances20[0].save(flush:true)
        def testInstanceslp = [new LigneProduit(sequenceLigne : new Integer(02), quantite : new Double(1),quantiteLivree  :new Double(0d),quantiteRetournee:new Double(0d),remise:new Double(0d),prix:new Double(15.45),prixDeduit:new Double(15.45),type:"ACHAT",trans_sousTotal:new Double(15.45),trans_sousTotalLivree:new Double(15.45),trans_sousTotalRetourne:new Double(15.45),trans_livree:new Boolean(true),trans_retournee:new Boolean(true),trans_ql:new Double(15.45),trans_qr:new Double(15.45),date_preremption:sqlDate1,produit:testInstances20[0],entrepot:testInstancese[0],bonCommande:null,bonLivraison:null,bonPret:null,devis:null)]
        //mockForConstraintsTests(DroitUtilisateur, testInstances)
        mockDomain(LigneProduit, testInstanceslp)
        assertTrue testInstanceslp[0].validate()
        testInstanceslp[0].save(flush:true)
        
        def testInstancesd = [new Devis(numDevis : "dh", type : "ACHAT", date : sqlDate1,dateFinValidite:sqlDate1,trans_totalht:new Double(14.12),trans_totaltva:new Double(14.12),trans_totalttc:new Double(14.12),trans_isvalid:new Double(14.12),partenaire:testInstances9[0],ligneProduits:testInstanceslp)]
        //mockForConstraintsTests(DroitUtilisateur, testInstances)
        mockDomain(Devis, testInstancesd)
        assertTrue testInstancesd[0].validate()
        testInstancesd[0].save(flush:true)
        
        def testInstances = [new BonCommande(numBC : "bc001", type : "ACHAT", reference : "5561",date:sqlDate1,trans_totalht:new Double(14.12),trans_totaltva:new Double(14.12),trans_totalttc:new Double(14.12),totalttcRetour:new Double(0d),livree:new Boolean(false),paye:new Boolean(false),retourPaye:new Boolean(true),facture:new Boolean(false),bonPret:new Boolean(false),retournee:new Boolean(false),avecRetour:new Boolean(false),trans_livree:new Boolean(true),trans_retournee:new Boolean(true),partenaire:testInstances9p[0],utilisateur:testInstancesu[0],deviss:testInstancesd,ligneProduits:testInstanceslp)]
        //mockForConstraintsTests(DroitUtilisateur, testInstances)
        mockDomain(BonCommande, testInstances)
        assertTrue testInstances[0].validate()
        testInstances[0].save(flush:true)
        
        def testInstancesbd = [new BonLivraison(numBL : "XYZ", type : "ACHAT", reference : "5561",date:sqlDate1,trans_totalht:new Double(15.02),trans_totaltva:new Double(15.02),trans_totalttc:new Double(15.02),estBonRetour:new Boolean(true),estLivre:new Boolean(true),bonCommande:testInstances[0],ligneProduits:testInstanceslp)]
        //mockForConstraintsTests(DroitUtilisateur, testInstances)
        mockDomain(BonLivraison, testInstancesbd)
        def testService = new BonLivraisonService()
    
         def myCriteria = [
    list : {Closure  cls -> return testInstancesbd}
]
BonLivraison.metaClass.static.createCriteria = { myCriteria }
      def res=testService.getBonsLivraisonBonsRetourPartenaire(testInstances9p[0])  
        System.out.println("getBonsLivraisonBonsRetourPartenaire   "+res)
        
      
    }
     void  testGetBonsLivraisonPartenaire() {
        
        
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
        def testInstancesz = [new Zone(code : "001", intitule : "saiis")]
        //mockForConstraintsTests(DroitUtilisateur, testInstances)
        mockDomain(Zone, testInstancesz)
        assertTrue testInstancesz[0].validate()
        testInstancesz[0].save(flush:true)
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
        def testInstancesee = [new Employe(numero : num, nom : "allali", prenom : "youssef",telephone:"0673327392",tauxHoraire:th,salaire:sa,calendrier:testInstances22[0],fonction:testInstances33[0])]
        //mockForConstraintsTests(DroitUtilisateur, testInstances)
        mockDomain(Employe, testInstancesee)
        assertTrue testInstancesee[0].validate()
        testInstancesee[0].save(flush:true)
        
        def testInstances9 = [new Partenaire(code : "005", raisonSociale : "celibataire", idFiscal : null,patente:"15165165",registreCommerce:"commerce",cnss:"fk13252152",adresse:"oulfa",codePostal:"20200",telephone:"0673327392",fax:"0632972654",email:"contact@choranet.com",siteWeb:"www.choranet.com",adresseFacturation:"hay el hana",adresseLivraison:"ain sebaii",estClient:new Boolean(true),estFournisseur:new Boolean(false),estParticulier:new Boolean(false),estBlacklist:new Boolean(false),plafond:new Double(12.12),modeRelancePaiement:testInstances2m[0],modeReglementDefaut:testInstances3m[0],echeance:testInstances4e[0],ville:testInstances6v[0],formeJuridique:testInstances7f[0],modeLivraison:testInstances8m[0],categoriePartenaires:testInstances1c,solde:new Double(15.01),compteVerrouile:new Boolean(true),zone:testInstancesz[0],commercial:testInstancesee[0])]
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
        
        
        def testInstances9p = [new Partenaire(code : "005", raisonSociale : "celibataire", idFiscal : null,patente:"15165165",registreCommerce:"commerce",cnss:"fk13252152",adresse:"oulfa",codePostal:"20200",telephone:"0673327392",fax:"0632972654",email:"contact@choranet.com",siteWeb:"www.choranet.com",adresseFacturation:"hay el hana",adresseLivraison:"ain sebaii",estClient:new Boolean(true),estFournisseur:new Boolean(false),estParticulier:new Boolean(false),estBlacklist:new Boolean(false),plafond:new Double(12.12),modeRelancePaiement:testInstances2m[0],modeReglementDefaut:testInstances3m[0],echeance:testInstances4[0],ville:testInstances6v[0],formeJuridique:testInstances7f[0],modeLivraison:testInstances8m[0],categoriePartenaires:testInstances1c,solde:new Double(15.01),compteVerrouile:new Boolean(true),zone:testInstancesz[0],commercial:testInstancesee[0])]
        //mockForConstraintsTests(DroitUtilisateur, testInstances)
        mockDomain(Partenaire, testInstances9p)
        assertTrue testInstances9p[0].validate()
        testInstances9p[0].save(flush:true)
        def testInstances10 = [new Entrepot(code: "006", intitule : "ain sebaa", adresse : "rue 26 n°51",tel:"0673327392",modeValorisation:"PRIX_ACHAT",mode:"FIFO")]
        //mockForConstraintsTests(DroitUtilisateur, testInstances)
        mockDomain(Entrepot, testInstances10)
        assertTrue testInstances10[0].validate()
        testInstances10[0].save(flush:true)
         def testInstances111 = [new Nature(libelle : "normale")]
        //mockForConstraintsTests(DroitUtilisateur, testInstances)
        mockDomain(Nature, testInstances111)
        assertTrue testInstances111[0].validate()
        testInstances111[0].save(flush:true)
        def testInstances12 = [new CategorieProduit(code : "21211515", intitule : "alimentaire", categorieParente : null)]
        //mockForConstraintsTests(DroitUtilisateur, testInstances)
        mockDomain(CategorieProduit, testInstances12)
        assertTrue testInstances12[0].validate()
        testInstances12[0].save(flush:true)
        def testInstances13 = [new UniteMesure(libelle : "metre")]
        //mockForConstraintsTests(DroitUtilisateur, testInstances)
        mockDomain(UniteMesure, testInstances13)
        assertTrue testInstances13[0].validate()
        testInstances13[0].save(flush:true)
        def j=new Integer(20)
         def j1=new Integer(25)
        def testInstances14 = [new RegimeTVA(libelle : "20%", taux : j)]
        //mockForConstraintsTests(DroitUtilisateur, testInstances)
        mockDomain(RegimeTVA, testInstances14)
        assertTrue testInstances14[0].validate()
        testInstances14[0].save(flush:true)
        
        
        
        
        
        def testInstances20 = [new Produit(code : "0151513251", codebarre : "xxl01222",profil:"alimentaire",designation:"marjane",prixRevient:new Double(14.12),prixVenteStandard:new Double(14.12),poids:new Double(14.12),prixAchat:new Double(14.12),prixMoyenPendere:new Double(14.12),perissable:new Boolean(false),temperature_conservation:new Double(52.12),imageProd:byte1,trans_imageProd:i,empReception:testInstances10[0],nature:testInstances111[0],categorieProduit:testInstances12[0],uniteMesure:testInstances13[0],regimeTVA:testInstances14[0],produitParent:null,fournisseurPref:testInstances9[0])]
        //mockForConstraintsTests(DroitUtilisateur, testInstances)
        mockDomain(Produit, testInstances20)
        assertTrue testInstances20[0].validate()
        testInstances20[0].save(flush:true)
        def testInstanceslp = [new LigneProduit(sequenceLigne : new Integer(02), quantite : new Double(1),quantiteLivree  :new Double(0d),quantiteRetournee:new Double(0d),remise:new Double(0d),prix:new Double(15.45),prixDeduit:new Double(15.45),type:"ACHAT",trans_sousTotal:new Double(15.45),trans_sousTotalLivree:new Double(15.45),trans_sousTotalRetourne:new Double(15.45),trans_livree:new Boolean(true),trans_retournee:new Boolean(true),trans_ql:new Double(15.45),trans_qr:new Double(15.45),date_preremption:sqlDate1,produit:testInstances20[0],entrepot:testInstancese[0],bonCommande:null,bonLivraison:null,bonPret:null,devis:null)]
        //mockForConstraintsTests(DroitUtilisateur, testInstances)
        mockDomain(LigneProduit, testInstanceslp)
        assertTrue testInstanceslp[0].validate()
        testInstanceslp[0].save(flush:true)
        
        def testInstancesd = [new Devis(numDevis : "dh", type : "ACHAT", date : sqlDate1,dateFinValidite:sqlDate1,trans_totalht:new Double(14.12),trans_totaltva:new Double(14.12),trans_totalttc:new Double(14.12),trans_isvalid:new Double(14.12),partenaire:testInstances9[0],ligneProduits:testInstanceslp)]
        //mockForConstraintsTests(DroitUtilisateur, testInstances)
        mockDomain(Devis, testInstancesd)
        assertTrue testInstancesd[0].validate()
        testInstancesd[0].save(flush:true)
        
        def testInstances = [new BonCommande(numBC : "bc001", type : "ACHAT", reference : "5561",date:sqlDate1,trans_totalht:new Double(14.12),trans_totaltva:new Double(14.12),trans_totalttc:new Double(14.12),totalttcRetour:new Double(0d),livree:new Boolean(false),paye:new Boolean(false),retourPaye:new Boolean(true),facture:new Boolean(false),bonPret:new Boolean(false),retournee:new Boolean(false),avecRetour:new Boolean(false),trans_livree:new Boolean(true),trans_retournee:new Boolean(true),partenaire:testInstances9p[0],utilisateur:testInstancesu[0],deviss:testInstancesd,ligneProduits:testInstanceslp)]
        //mockForConstraintsTests(DroitUtilisateur, testInstances)
        mockDomain(BonCommande, testInstances)
        assertTrue testInstances[0].validate()
        testInstances[0].save(flush:true)
        
        def testInstancesbd = [new BonLivraison(numBL : "XYZ", type : "ACHAT", reference : "5561",date:sqlDate1,trans_totalht:new Double(15.02),trans_totaltva:new Double(15.02),trans_totalttc:new Double(15.02),estBonRetour:new Boolean(true),estLivre:new Boolean(true),bonCommande:testInstances[0],ligneProduits:testInstanceslp)]
        //mockForConstraintsTests(DroitUtilisateur, testInstances)
        mockDomain(BonLivraison, testInstancesbd)
        def testService = new BonLivraisonService()
    
         def myCriteria = [
    list : {Closure  cls -> return testInstancesbd}
]
BonLivraison.metaClass.static.createCriteria = { myCriteria }
      def res=testService.getBonsLivraisonPartenaire("ACHAT",testInstances9p[0])  
        System.out.println("getBonsLivraisonPartenaire   "+res)
        
      
    }
    
        void  testGetBonsLivraisonBonCommande() {
        
        
         def ex=new Integer(30032013)
        final SimpleDateFormat sdf_yyyyMMdd = new SimpleDateFormat("yyyy-MM-dd");
		final SimpleDateFormat sdf_ddMMyyyy = new SimpleDateFormat("dd/MM/yyyy");
 
		java.util.Date judDate1 = new java.util.Date();
                java.util.Date judDate2 = new java.util.Date();
                java.util.Date judDate3 = new java.util.Date();
                java.util.Date judDate4 = new java.util.Date();
		try {
			judDate1 = sdf_ddMMyyyy.parse("01/10/2005");
                        judDate3 = sdf_ddMMyyyy.parse("01/09/2005");
                        judDate4 = sdf_ddMMyyyy.parse("01/11/2005");
                        judDate2 = sdf_ddMMyyyy.parse("03/10/2005");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 
		java.sql.Date sqlDate1 = new java.sql.Date(judDate1.getTime());
                java.sql.Date sqlDate2 = new java.sql.Date(judDate2.getTime());
                java.sql.Date sqlDate3 = new java.sql.Date(judDate3.getTime());
                java.sql.Date sqlDate4 = new java.sql.Date(judDate4.getTime());
		System.out.println("sqlDate1.toString() : " + sqlDate1.toString());
		System.out.println("sdf_ddMMyyyy.format(sqlDate1) : "
				+ sdf_ddMMyyyy.format(sqlDate1));
		System.out.println("sdf_yyyyMMdd.format(sqlDate1) : "
				+ sdf_yyyyMMdd.format(sqlDate1));
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
        def testInstancesz = [new Zone(code : "001", intitule : "saiis")]
        //mockForConstraintsTests(DroitUtilisateur, testInstances)
        mockDomain(Zone, testInstancesz)
        assertTrue testInstancesz[0].validate()
        testInstancesz[0].save(flush:true)
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
        def testInstancesee = [new Employe(numero : num, nom : "allali", prenom : "youssef",telephone:"0673327392",tauxHoraire:th,salaire:sa,calendrier:testInstances22[0],fonction:testInstances33[0])]
        //mockForConstraintsTests(DroitUtilisateur, testInstances)
        mockDomain(Employe, testInstancesee)
        assertTrue testInstancesee[0].validate()
        testInstancesee[0].save(flush:true)
        
        def testInstances9 = [new Partenaire(code : "005", raisonSociale : "celibataire", idFiscal : null,patente:"15165165",registreCommerce:"commerce",cnss:"fk13252152",adresse:"oulfa",codePostal:"20200",telephone:"0673327392",fax:"0632972654",email:"contact@choranet.com",siteWeb:"www.choranet.com",adresseFacturation:"hay el hana",adresseLivraison:"ain sebaii",estClient:new Boolean(true),estFournisseur:new Boolean(false),estParticulier:new Boolean(false),estBlacklist:new Boolean(false),plafond:new Double(12.12),modeRelancePaiement:testInstances2m[0],modeReglementDefaut:testInstances3m[0],echeance:testInstances4e[0],ville:testInstances6v[0],formeJuridique:testInstances7f[0],modeLivraison:testInstances8m[0],categoriePartenaires:testInstances1c,solde:new Double(15.01),compteVerrouile:new Boolean(true),zone:testInstancesz[0],commercial:testInstancesee[0])]
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
        
        
        def testInstances9p = [new Partenaire(code : "005", raisonSociale : "celibataire", idFiscal : null,patente:"15165165",registreCommerce:"commerce",cnss:"fk13252152",adresse:"oulfa",codePostal:"20200",telephone:"0673327392",fax:"0632972654",email:"contact@choranet.com",siteWeb:"www.choranet.com",adresseFacturation:"hay el hana",adresseLivraison:"ain sebaii",estClient:new Boolean(true),estFournisseur:new Boolean(false),estParticulier:new Boolean(false),estBlacklist:new Boolean(false),plafond:new Double(12.12),modeRelancePaiement:testInstances2m[0],modeReglementDefaut:testInstances3m[0],echeance:testInstances4[0],ville:testInstances6v[0],formeJuridique:testInstances7f[0],modeLivraison:testInstances8m[0],categoriePartenaires:testInstances1c,solde:new Double(15.01),compteVerrouile:new Boolean(true),zone:testInstancesz[0],commercial:testInstancesee[0])]
        //mockForConstraintsTests(DroitUtilisateur, testInstances)
        mockDomain(Partenaire, testInstances9p)
        assertTrue testInstances9p[0].validate()
        testInstances9p[0].save(flush:true)
        def testInstances10 = [new Entrepot(code: "006", intitule : "ain sebaa", adresse : "rue 26 n°51",tel:"0673327392",modeValorisation:"PRIX_ACHAT",mode:"FIFO")]
        //mockForConstraintsTests(DroitUtilisateur, testInstances)
        mockDomain(Entrepot, testInstances10)
        assertTrue testInstances10[0].validate()
        testInstances10[0].save(flush:true)
         def testInstances111 = [new Nature(libelle : "normale")]
        //mockForConstraintsTests(DroitUtilisateur, testInstances)
        mockDomain(Nature, testInstances111)
        assertTrue testInstances111[0].validate()
        testInstances111[0].save(flush:true)
        def testInstances12 = [new CategorieProduit(code : "21211515", intitule : "alimentaire", categorieParente : null)]
        //mockForConstraintsTests(DroitUtilisateur, testInstances)
        mockDomain(CategorieProduit, testInstances12)
        assertTrue testInstances12[0].validate()
        testInstances12[0].save(flush:true)
        def testInstances13 = [new UniteMesure(libelle : "metre")]
        //mockForConstraintsTests(DroitUtilisateur, testInstances)
        mockDomain(UniteMesure, testInstances13)
        assertTrue testInstances13[0].validate()
        testInstances13[0].save(flush:true)
        def j=new Integer(20)
         def j1=new Integer(25)
        def testInstances14 = [new RegimeTVA(libelle : "20%", taux : j)]
        //mockForConstraintsTests(DroitUtilisateur, testInstances)
        mockDomain(RegimeTVA, testInstances14)
        assertTrue testInstances14[0].validate()
        testInstances14[0].save(flush:true)
        
        
        
        
        
        def testInstances20 = [new Produit(code : "0151513251", codebarre : "xxl01222",profil:"alimentaire",designation:"marjane",prixRevient:new Double(14.12),prixVenteStandard:new Double(14.12),poids:new Double(14.12),prixAchat:new Double(14.12),prixMoyenPendere:new Double(14.12),perissable:new Boolean(false),temperature_conservation:new Double(52.12),imageProd:byte1,trans_imageProd:i,empReception:testInstances10[0],nature:testInstances111[0],categorieProduit:testInstances12[0],uniteMesure:testInstances13[0],regimeTVA:testInstances14[0],produitParent:null,fournisseurPref:testInstances9[0])]
        //mockForConstraintsTests(DroitUtilisateur, testInstances)
        mockDomain(Produit, testInstances20)
        assertTrue testInstances20[0].validate()
        testInstances20[0].save(flush:true)
        def testInstanceslp = [new LigneProduit(sequenceLigne : new Integer(02), quantite : new Double(1),quantiteLivree  :new Double(0d),quantiteRetournee:new Double(0d),remise:new Double(0d),prix:new Double(15.45),prixDeduit:new Double(15.45),type:"ACHAT",trans_sousTotal:new Double(15.45),trans_sousTotalLivree:new Double(15.45),trans_sousTotalRetourne:new Double(15.45),trans_livree:new Boolean(true),trans_retournee:new Boolean(true),trans_ql:new Double(15.45),trans_qr:new Double(15.45),date_preremption:sqlDate1,produit:testInstances20[0],entrepot:testInstancese[0],bonCommande:null,bonLivraison:null,bonPret:null,devis:null)]
        //mockForConstraintsTests(DroitUtilisateur, testInstances)
        mockDomain(LigneProduit, testInstanceslp)
        assertTrue testInstanceslp[0].validate()
        testInstanceslp[0].save(flush:true)
        
        def testInstancesd = [new Devis(numDevis : "dh", type : "ACHAT", date : sqlDate1,dateFinValidite:sqlDate1,trans_totalht:new Double(14.12),trans_totaltva:new Double(14.12),trans_totalttc:new Double(14.12),trans_isvalid:new Double(14.12),partenaire:testInstances9[0],ligneProduits:testInstanceslp)]
        //mockForConstraintsTests(DroitUtilisateur, testInstances)
        mockDomain(Devis, testInstancesd)
        assertTrue testInstancesd[0].validate()
        testInstancesd[0].save(flush:true)
        
        def testInstances = [new BonCommande(numBC : "bc001", type : "ACHAT", reference : "5561",date:sqlDate1,trans_totalht:new Double(14.12),trans_totaltva:new Double(14.12),trans_totalttc:new Double(14.12),totalttcRetour:new Double(0d),livree:new Boolean(false),paye:new Boolean(false),retourPaye:new Boolean(true),facture:new Boolean(false),bonPret:new Boolean(false),retournee:new Boolean(false),avecRetour:new Boolean(false),trans_livree:new Boolean(true),trans_retournee:new Boolean(true),partenaire:testInstances9p[0],utilisateur:testInstancesu[0],deviss:testInstancesd,ligneProduits:testInstanceslp)]
        //mockForConstraintsTests(DroitUtilisateur, testInstances)
        mockDomain(BonCommande, testInstances)
        assertTrue testInstances[0].validate()
        testInstances[0].save(flush:true)
        
        def testInstancesbd = [new BonLivraison(numBL : "XYZ", type : "ACHAT", reference : "5561",date:sqlDate1,trans_totalht:new Double(15.02),trans_totaltva:new Double(15.02),trans_totalttc:new Double(15.02),estBonRetour:new Boolean(true),estLivre:new Boolean(true),bonCommande:testInstances[0],ligneProduits:testInstanceslp)]
        //mockForConstraintsTests(DroitUtilisateur, testInstances)
        mockDomain(BonLivraison, testInstancesbd)
        def testService = new BonLivraisonService()
    
         def myCriteria = [
    list : {Closure  cls -> return testInstancesbd}
]
BonLivraison.metaClass.static.createCriteria = { myCriteria }
      def res=testService.getBonsLivraisonBonCommande(testInstances)  
        System.out.println("getBonsLivraisonBonCommande   "+res)
        
      
    } 
    
//      void  testGetBonLivraisonBetweenWI() {
//        
//        
//         def ex=new Integer(30032013)
//        final SimpleDateFormat sdf_yyyyMMdd = new SimpleDateFormat("yyyy-MM-dd");
//		final SimpleDateFormat sdf_ddMMyyyy = new SimpleDateFormat("dd/MM/yyyy");
// 
//		java.util.Date judDate1 = new java.util.Date();
//                java.util.Date judDate3 = new java.util.Date();
//                java.util.Date judDate2 = new java.util.Date();
//                java.util.Date judDate4 = new java.util.Date();
//		try {
//			judDate1 = sdf_ddMMyyyy.parse("01/10/2005");
//                        judDate3 = sdf_ddMMyyyy.parse("01/09/2005");
//                        judDate4 = sdf_ddMMyyyy.parse("01/11/2005");
//                        judDate2 = sdf_ddMMyyyy.parse("03/10/2005");
//		} catch (ParseException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
// 
//		java.sql.Date sqlDate1 = new java.sql.Date(judDate1.getTime());
//                java.sql.Date sqlDate2 = new java.sql.Date(judDate2.getTime());
//                java.sql.Date sqlDate3 = new java.sql.Date(judDate3.getTime());
//                java.sql.Date sqlDate4 = new java.sql.Date(judDate4.getTime());
//		System.out.println("sqlDate1.toString() : " + sqlDate1.toString());
//		System.out.println("sdf_ddMMyyyy.format(sqlDate1) : "
//				+ sdf_ddMMyyyy.format(sqlDate1));
//		System.out.println("sdf_yyyyMMdd.format(sqlDate1) : "
//				+ sdf_yyyyMMdd.format(sqlDate1));
//        def testInstances1c = [new CategoriePartenaire(libelle : "propr", categorieParente : null)]
//        //mockForConstraintsTests(DroitUtilisateur, testInstances)
//        mockDomain(CategoriePartenaire, testInstances1c)
//        assertTrue testInstances1c[0].validate()
//        testInstances1c[0].save(flush:true)
//        def testInstances2m = [new ModeRelancePaiement(code : "XYZ01", frequence : new Integer(10), libelle : "facture",corpsMessage:"paiment du reste de la facture")]
//        //mockForConstraintsTests(DroitUtilisateur, testInstances)
//        mockDomain(ModeRelancePaiement, testInstances2m)
//        assertTrue testInstances2m[0].validate()
//        testInstances2m[0].save(flush:true)
//        def testInstances3m = [new ModeReglement(code : "005", libelle : "espece")]
//        //mockForConstraintsTests(DroitUtilisateur, testInstances)
//        mockDomain(ModeReglement, testInstances3m)
//        assertTrue testInstances3m[0].validate()
//        testInstances3m[0].save(flush:true)
//        def testInstances4e = [new Echeance(code : new Integer(001), libelle : "date livraison", periodicite : new Integer(30),typeDeclanchement:"DATE_LIVRAISON")]
//        //mockForConstraintsTests(DroitUtilisateur, testInstances)
//        mockDomain(Echeance, testInstances4e)
//        assertTrue testInstances4e[0].validate()
//        testInstances4e[0].save(flush:true)
//        def testInstances5p = [new Pays(intitule : "maroc")]
//        //mockForConstraintsTests(DroitUtilisateur, testInstances)
//        mockDomain(Pays, testInstances5p)
//        assertTrue testInstances5p[0].validate()
//        testInstances5p[0].save(flush:true)
//        def testInstances6v = [new Ville(intitule : "AGADIR", pays:testInstances5p[0] )]
//        //mockForConstraintsTests(DroitUtilisateur, testInstances)
//        mockDomain(Ville, testInstances6v)
//        assertTrue testInstances6v[0].validate()
//        testInstances6v[0].save(flush:true)
//        def testInstances7f = [new FormeJuridique(libelle : "GIE")]
//        //mockForConstraintsTests(DroitUtilisateur, testInstances)
//        mockDomain(FormeJuridique, testInstances7f)
//        assertTrue testInstances7f[0].validate()
//        testInstances7f[0].save(flush:true)
//        def testInstances8m = [new ModeLivraison(code : "0032", transporteur : "stcr", paiementALaReception : new Boolean(true))]
//        //mockForConstraintsTests(DroitUtilisateur, testInstances)
//        mockDomain(ModeLivraison, testInstances8m)
//        assertTrue testInstances8m[0].validate()
//        testInstances8m[0].save(flush:true)
//        
//        System.out.println("la fin initialisation")
//        def testInstancesz = [new Zone(code : "001", intitule : "saiis")]
//        //mockForConstraintsTests(DroitUtilisateur, testInstances)
//        mockDomain(Zone, testInstancesz)
//        assertTrue testInstancesz[0].validate()
//        testInstancesz[0].save(flush:true)
//        def gg=new Integer(12)
//        def gg1=new Integer(22)
//        def bo=new Boolean(true)
//        def testInstances11 = [new JourFerier(date : sqlDate1, intitule : "fete independance", annuel :bo,duree:gg)]
//        //mockForConstraintsTests(DroitUtilisateur, testInstances)
//        mockDomain(JourFerier, testInstances11)
//        assertTrue testInstances11[0].validate()
//        testInstances11[0].save(flush:true)
//        def testInstances22 = [new Calendrier(nbHeuresTravailParJour : gg, nom : "annee 2013", nbJoursTravailParMois :gg1,jourFeriers:testInstances11)]
//        //mockForConstraintsTests(DroitUtilisateur, testInstances)
//        mockDomain(Calendrier, testInstances22)
//        assertTrue testInstances22[0].validate()
//        testInstances22[0].save(flush:true)
//        
//        def testInstances33 = [new Fonction(libelle : "comptable")]
//        //mockForConstraintsTests(DroitUtilisateur, testInstances)
//        mockDomain(Fonction, testInstances33)
//        assertTrue testInstances33[0].validate()
//        testInstances33[0].save(flush:true)
//        
//        
//        def num=new Integer(01)
//        def th=new Double(12.01)
//        def sa=new Double(300.14)
//        def testInstancesee = [new Employe(numero : num, nom : "allali", prenom : "youssef",telephone:"0673327392",tauxHoraire:th,salaire:sa,calendrier:testInstances22[0],fonction:testInstances33[0])]
//        //mockForConstraintsTests(DroitUtilisateur, testInstances)
//        mockDomain(Employe, testInstancesee)
//        assertTrue testInstancesee[0].validate()
//        testInstancesee[0].save(flush:true)
//        
//        def testInstances9 = [new Partenaire(code : "005", raisonSociale : "celibataire", idFiscal : null,patente:"15165165",registreCommerce:"commerce",cnss:"fk13252152",adresse:"oulfa",codePostal:"20200",telephone:"0673327392",fax:"0632972654",email:"contact@choranet.com",siteWeb:"www.choranet.com",adresseFacturation:"hay el hana",adresseLivraison:"ain sebaii",estClient:new Boolean(true),estFournisseur:new Boolean(false),estParticulier:new Boolean(false),estBlacklist:new Boolean(false),plafond:new Double(12.12),modeRelancePaiement:testInstances2m[0],modeReglementDefaut:testInstances3m[0],echeance:testInstances4e[0],ville:testInstances6v[0],formeJuridique:testInstances7f[0],modeLivraison:testInstances8m[0],categoriePartenaires:testInstances1c,solde:new Double(15.01),compteVerrouile:new Boolean(true),zone:testInstancesz[0],commercial:testInstancesee[0])]
//        //mockForConstraintsTests(DroitUtilisateur, testInstances)
//        mockDomain(Partenaire, testInstances9)
//        assertTrue testInstances9[0].validate()
//        testInstances9[0].save(flush:true)
//        
//        
//        Image i=Utilitaire.getLogoImage(getClass())
//        def byte1=i.getByteData()
//        def testInstances4 = [new DroitUtilisateur(droit:"ddd",description:"description",parent:null)]
//        def testInstances1g = [new GroupeUtilisateur(intitule:"ddd",description:"decision",droits:null)]
//        def testInstances2 = [new RegimeDeclarationTva(libelle:"Déclaration mensuelle à base encaissement",periodicite:"Trimestrielle",faitGenerateur:"Encaissement"),new RegimeDeclarationTva(libelle:"Déclaration trimestrielle à base encaissement",periodicite:"Trimestrielle",faitGenerateur:"Encaissement")]
//        def testInstances3 = [  new ChoraClientInfo(raisonSociale : "CHORA INDUSTRIE1", email : "contact@choranet.com", formeJuridique : "SARL",telephone:"+212 522 233 343",fax:"+212 522 233 344",patente:"78/7877",rc:"78965",idF:"45788",cnss:"21/45789",site:"http://www.choranet.com",repertoirBackup:"/home/qllqli",adresse:"11M3 Lot Alhamd Ain Sbaa",codePostale:"20250",ville:"Casablanca",pays:"Maroc",cachetData:null,entetepiedData:null,copieData:null,logoData:byte1,regime:testInstances2[0])]
//        def testInstancesu = [new Utilisateur(username : "XYZ", userRealName : "UTILISATEUR_XYZ",passwd :"allali",enabled:true,email:'contact@choranet.com',groupe:testInstances1g[0],societe:testInstances3[0] )]
//        //mockForConstraintsTests(Module, testInstances)
//        mockDomain(RegimeDeclarationTva, testInstances2)
//        mockDomain(GroupeUtilisateur, testInstances1g)
//        mockDomain(Utilisateur, testInstancesu)
//        mockDomain(ChoraClientInfo, testInstances3)
//        mockDomain(DroitUtilisateur, testInstances4)
//        assertEquals 1, Utilisateur.count()
//        assertTrue testInstancesu[0] instanceof Utilisateur 
//        System.out.println("troisieme bbbbbb111")
//        def utilisateur2 = new Utilisateur()
//        assertFalse utilisateur2.validate()
//        assertEquals "nullable", utilisateur2.errors["username"]
//        assertEquals "nullable", utilisateur2.errors["userRealName"]
//        assertEquals "nullable", utilisateur2.errors["passwd"]
//        assertEquals "nullable", utilisateur2.errors["groupe"]
//        assertEquals "nullable", utilisateur2.errors["societe"]
//        System.out.println("troisieme bbbbbb111 bhbhbh")
//        def utilisateur3= new Utilisateur(username : "XYZ", userRealName : "UTILISATEUR_XYZ",passwd :"allali",enabled:true,groupe:testInstances1g[0],societe:testInstances3[0] )
//        assertFalse utilisateur3.validate()
//        assertEquals "unique", utilisateur3.errors["username"]
//        System.out.println("troisieme ggggg")
//        def utilisateur4= new Utilisateur(username : "admin1", userRealName : "Super Utilisateur",passwd :"NirfFVL6jts=",email:"contact@choranet.com",enabled:true,groupe:testInstances1g[0],societe:testInstances3[0])
//        def f=utilisateur4.validate()
//        def grooupe1=new GroupeUtilisateur(intitule:"ddd",description:"decision",droits:null)
//        assertTrue testInstances1g[0].validate()
//        testInstances1g[0].save(flush:true)
//        System.out.println("troisieme ggggg premier111"+testInstances1g[0].validate()+testInstances1g[0].getClass())
//        assertTrue testInstances2[0].validate()
//        testInstances2[0].save(flush:true)
//        System.out.println("troisieme ggggg premier222"+testInstances2[0].validate()+testInstances2[0].getClass())
//        assertTrue testInstances3[0].validate()
//        testInstances3[0].save(flush:true)
//        assertTrue testInstances4[0].validate()
//        testInstances4[0].save(flush:true)
//        assertTrue testInstancesu[0].validate()
//        testInstancesu[0].save(flush:true)
//        
//        
//        
//                            
//        def testInstancese = [new Entrepot(code	 : "006", intitule : "ain sebaa", adresse : "rue 26 n°51",tel:"0673327392",modeValorisation:"PRIX_ACHAT",mode:"FIFO")]
//        //mockForConstraintsTests(DroitUtilisateur, testInstances)
//        mockDomain(Entrepot, testInstancese)
//        assertTrue testInstancese[0].validate()
//        testInstancese[0].save(flush:true)
//        
//        
//        def testInstances9p = [new Partenaire(code : "005", raisonSociale : "celibataire", idFiscal : null,patente:"15165165",registreCommerce:"commerce",cnss:"fk13252152",adresse:"oulfa",codePostal:"20200",telephone:"0673327392",fax:"0632972654",email:"contact@choranet.com",siteWeb:"www.choranet.com",adresseFacturation:"hay el hana",adresseLivraison:"ain sebaii",estClient:new Boolean(true),estFournisseur:new Boolean(false),estParticulier:new Boolean(false),estBlacklist:new Boolean(false),plafond:new Double(12.12),modeRelancePaiement:testInstances2m[0],modeReglementDefaut:testInstances3m[0],echeance:testInstances4[0],ville:testInstances6v[0],formeJuridique:testInstances7f[0],modeLivraison:testInstances8m[0],categoriePartenaires:testInstances1c,solde:new Double(15.01),compteVerrouile:new Boolean(true),zone:testInstancesz[0],commercial:testInstancesee[0])]
//        //mockForConstraintsTests(DroitUtilisateur, testInstances)
//        mockDomain(Partenaire, testInstances9p)
//        assertTrue testInstances9p[0].validate()
//        testInstances9p[0].save(flush:true)
//        def testInstances10 = [new Entrepot(code: "006", intitule : "ain sebaa", adresse : "rue 26 n°51",tel:"0673327392",modeValorisation:"PRIX_ACHAT",mode:"FIFO")]
//        //mockForConstraintsTests(DroitUtilisateur, testInstances)
//        mockDomain(Entrepot, testInstances10)
//        assertTrue testInstances10[0].validate()
//        testInstances10[0].save(flush:true)
//         def testInstances111 = [new Nature(libelle : "normale")]
//        //mockForConstraintsTests(DroitUtilisateur, testInstances)
//        mockDomain(Nature, testInstances111)
//        assertTrue testInstances111[0].validate()
//        testInstances111[0].save(flush:true)
//        def testInstances12 = [new CategorieProduit(code : "21211515", intitule : "alimentaire", categorieParente : null)]
//        //mockForConstraintsTests(DroitUtilisateur, testInstances)
//        mockDomain(CategorieProduit, testInstances12)
//        assertTrue testInstances12[0].validate()
//        testInstances12[0].save(flush:true)
//        def testInstances13 = [new UniteMesure(libelle : "metre")]
//        //mockForConstraintsTests(DroitUtilisateur, testInstances)
//        mockDomain(UniteMesure, testInstances13)
//        assertTrue testInstances13[0].validate()
//        testInstances13[0].save(flush:true)
//        def j=new Integer(20)
//         def j1=new Integer(25)
//        def testInstances14 = [new RegimeTVA(libelle : "20%", taux : j)]
//        //mockForConstraintsTests(DroitUtilisateur, testInstances)
//        mockDomain(RegimeTVA, testInstances14)
//        assertTrue testInstances14[0].validate()
//        testInstances14[0].save(flush:true)
//        
//        
//        
//        
//        
//        def testInstances20 = [new Produit(code : "0151513251", codebarre : "xxl01222",profil:"alimentaire",designation:"marjane",prixRevient:new Double(14.12),prixVenteStandard:new Double(14.12),poids:new Double(14.12),prixAchat:new Double(14.12),prixMoyenPendere:new Double(14.12),perissable:new Boolean(false),temperature_conservation:new Double(52.12),imageProd:byte1,trans_imageProd:i,empReception:testInstances10[0],nature:testInstances111[0],categorieProduit:testInstances12[0],uniteMesure:testInstances13[0],regimeTVA:testInstances14[0],produitParent:null,fournisseurPref:testInstances9[0])]
//        //mockForConstraintsTests(DroitUtilisateur, testInstances)
//        mockDomain(Produit, testInstances20)
//        assertTrue testInstances20[0].validate()
//        testInstances20[0].save(flush:true)
//        def testInstanceslp = [new LigneProduit(sequenceLigne : new Integer(02), quantite : new Double(1),quantiteLivree  :new Double(0d),quantiteRetournee:new Double(0d),remise:new Double(0d),prix:new Double(15.45),prixDeduit:new Double(15.45),type:"ACHAT",trans_sousTotal:new Double(15.45),trans_sousTotalLivree:new Double(15.45),trans_sousTotalRetourne:new Double(15.45),trans_livree:new Boolean(true),trans_retournee:new Boolean(true),trans_ql:new Double(15.45),trans_qr:new Double(15.45),date_preremption:sqlDate1,produit:testInstances20[0],entrepot:testInstancese[0],bonCommande:null,bonLivraison:null,bonPret:null,devis:null)]
//        //mockForConstraintsTests(DroitUtilisateur, testInstances)
//        mockDomain(LigneProduit, testInstanceslp)
//        assertTrue testInstanceslp[0].validate()
//        testInstanceslp[0].save(flush:true)
//        
//        def testInstancesd = [new Devis(numDevis : "dh", type : "ACHAT", date : sqlDate1,dateFinValidite:sqlDate1,trans_totalht:new Double(14.12),trans_totaltva:new Double(14.12),trans_totalttc:new Double(14.12),trans_isvalid:new Double(14.12),partenaire:testInstances9[0],ligneProduits:testInstanceslp)]
//        //mockForConstraintsTests(DroitUtilisateur, testInstances)
//        mockDomain(Devis, testInstancesd)
//        assertTrue testInstancesd[0].validate()
//        testInstancesd[0].save(flush:true)
//        
//        def testInstances = [new BonCommande(numBC : "bc001", type : "VENTE", reference : "5561",date:sqlDate1,trans_totalht:new Double(14.12),trans_totaltva:new Double(14.12),trans_totalttc:new Double(14.12),totalttcRetour:new Double(0d),livree:new Boolean(false),paye:new Boolean(false),retourPaye:new Boolean(true),facture:new Boolean(false),bonPret:new Boolean(false),retournee:new Boolean(false),avecRetour:new Boolean(false),trans_livree:new Boolean(true),trans_retournee:new Boolean(true),partenaire:testInstances9p[0],utilisateur:testInstancesu[0],deviss:testInstancesd,ligneProduits:testInstanceslp)]
//        //mockForConstraintsTests(DroitUtilisateur, testInstances)
//        mockDomain(BonCommande, testInstances)
//        assertTrue testInstances[0].validate()
//        testInstances[0].save(flush:true)
//        
//        def testInstancesbd = [new BonLivraison(numBL : "XYZ", type : "ACHAT", reference : "5561",date:sqlDate1,trans_totalht:new Double(15.02),trans_totaltva:new Double(15.02),trans_totalttc:new Double(15.02),estBonRetour:new Boolean(true),estLivre:new Boolean(true),bonCommande:testInstances[0],ligneProduits:testInstanceslp)]
//        //mockForConstraintsTests(DroitUtilisateur, testInstances)
//        mockDomain(BonLivraison, testInstancesbd)
//        def testInstancesf = [new Facture(numeroFacture : "fa121511", type : "ACHAT", reference : "ref045224",date:sqlDate1,comptabilise:new Boolean(false),estAvoir:new Boolean(false),trans_totalht:new Double(13.02),trans_totaltva:new Double(13.02),trans_totalttc:new Double(13.02),trans_ligneProduits:testInstanceslp,faitgenerateur:"bilan",partenaire:testInstances9[0],paiements:null,bonCommandes:testInstances)]
//        //mockForConstraintsTests(DroitUtilisateur, testInstances)
//        mockDomain(Facture, testInstancesf)
//        def testService = new BonLivraisonService()
//    
//         def myCriteria = [
//    list : {Closure  cls -> return testInstancesbd}
//]
//BonLivraison.metaClass.static.createCriteria = { myCriteria }
//      def res=testService.getBonLivraisonBetweenWI(sqlDate3,sqlDate4,testInstances9p[0])  
//        System.out.println("getBonLivraisonBetweenWI   "+res)
//        
//      
//    }
    
    void testSave() {
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
        
        
        
       def testInstances9 = [new Partenaire(code : "005", raisonSociale : "celibataire", idFiscal : null,patente:"15165165",registreCommerce:"commerce",cnss:"fk13252152",adresse:"oulfa",codePostal:"20200",telephone:"0673327392",fax:"0632972654",email:"contact@choranet.com",siteWeb:"www.choranet.com",adresseFacturation:"hay el hana",adresseLivraison:"ain sebaii",estClient:new Boolean(true),estFournisseur:new Boolean(false),estParticulier:new Boolean(false),estBlacklist:new Boolean(false),plafond:new Double(12.12),modeRelancePaiement:testInstances2m[0],modeReglementDefaut:testInstances3m[0],echeance:testInstances4e[0],ville:testInstances6v[0],formeJuridique:testInstances7f[0],modeLivraison:testInstances8m[0],categoriePartenaires:testInstances1c,solde:new Double(15.01),compteVerrouile:new Boolean(true),zone:testInstancesz[0],commercial:testInstancesee[0])]        //mockForConstraintsTests(DroitUtilisateur, testInstances)
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
       
        def utilisateur2 = new Utilisateur()
        assertFalse utilisateur2.validate()
        assertEquals "nullable", utilisateur2.errors["username"]
        assertEquals "nullable", utilisateur2.errors["userRealName"]
        assertEquals "nullable", utilisateur2.errors["passwd"]
        assertEquals "nullable", utilisateur2.errors["groupe"]
        assertEquals "nullable", utilisateur2.errors["societe"]
       
        def utilisateur3= new Utilisateur(username : "XYZ", userRealName : "UTILISATEUR_XYZ",passwd :"allali",enabled:true,groupe:testInstances1g[0],societe:testInstances3[0] )
        assertFalse utilisateur3.validate()
        assertEquals "unique", utilisateur3.errors["username"]
        
        def utilisateur4= new Utilisateur(username : "admin1", userRealName : "Super Utilisateur",passwd :"NirfFVL6jts=",email:"contact@choranet.com",enabled:true,groupe:testInstances1g[0],societe:testInstances3[0])
        def f=utilisateur4.validate()
        def grooupe1=new GroupeUtilisateur(intitule:"ddd",description:"decision",droits:null)
        assertTrue testInstances1g[0].validate()
        testInstances1g[0].save(flush:true)
        
        assertTrue testInstances2[0].validate()
        testInstances2[0].save(flush:true)
        
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
        
        
        def testInstances9p = [new Partenaire(code : "005", raisonSociale : "celibataire", idFiscal : null,patente:"15165165",registreCommerce:"commerce",cnss:"fk13252152",adresse:"oulfa",codePostal:"20200",telephone:"0673327392",fax:"0632972654",email:"contact@choranet.com",siteWeb:"www.choranet.com",adresseFacturation:"hay el hana",adresseLivraison:"ain sebaii",estClient:new Boolean(true),estFournisseur:new Boolean(false),estParticulier:new Boolean(false),estBlacklist:new Boolean(false),plafond:new Double(12.12),modeRelancePaiement:testInstances2m[0],modeReglementDefaut:testInstances3m[0],echeance:testInstances4e[0],ville:testInstances6v[0],formeJuridique:testInstances7f[0],modeLivraison:testInstances8m[0],categoriePartenaires:testInstances1c,solde:new Double(15.01),compteVerrouile:new Boolean(true),zone:testInstancesz[0],commercial:testInstancesee[0])]
        mockDomain(Partenaire, testInstances9p)
        assertTrue testInstances9p[0].validate()
        testInstances9p[0].save(flush:true)
        def testInstances10 = [new Entrepot(code: "006", intitule : "ain sebaa", adresse : "rue 26 n°51",tel:"0673327392",modeValorisation:"PRIX_ACHAT",mode:"FIFO")]
        //mockForConstraintsTests(DroitUtilisateur, testInstances)
        mockDomain(Entrepot, testInstances10)
        assertTrue testInstances10[0].validate()
        testInstances10[0].save(flush:true)
         def testInstances11 = [new Nature(libelle : "normale")]
        //mockForConstraintsTests(DroitUtilisateur, testInstances)
        mockDomain(Nature, testInstances11)
        assertTrue testInstances11[0].validate()
        testInstances11[0].save(flush:true)
        def testInstances12 = [new CategorieProduit(code : "21211515", intitule : "alimentaire", categorieParente : null)]
        //mockForConstraintsTests(DroitUtilisateur, testInstances)
        mockDomain(CategorieProduit, testInstances12)
        assertTrue testInstances12[0].validate()
        testInstances12[0].save(flush:true)
        def testInstances13 = [new UniteMesure(libelle : "metre")]
        //mockForConstraintsTests(DroitUtilisateur, testInstances)
        mockDomain(UniteMesure, testInstances13)
        assertTrue testInstances13[0].validate()
        testInstances13[0].save(flush:true)
        def j=new Integer(20)
         def j1=new Integer(25)
        def testInstances14 = [new RegimeTVA(libelle : "20%", taux : j)]
        //mockForConstraintsTests(DroitUtilisateur, testInstances)
        mockDomain(RegimeTVA, testInstances14)
        assertTrue testInstances14[0].validate()
        testInstances14[0].save(flush:true)
        
        
        
        
        
        def testInstances20 = [new Produit(code : "0151513251", codebarre : "xxl01222",profil:"alimentaire",designation:"marjane",prixRevient:new Double(14.12),prixVenteStandard:new Double(14.12),poids:new Double(14.12),prixAchat:new Double(14.12),prixMoyenPendere:new Double(14.12),perissable:new Boolean(false),temperature_conservation:new Double(52.12),imageProd:byte1,trans_imageProd:i,empReception:testInstances10[0],nature:testInstances11[0],categorieProduit:testInstances12[0],uniteMesure:testInstances13[0],regimeTVA:testInstances14[0],produitParent:null,fournisseurPref:testInstances9[0])]
        //mockForConstraintsTests(DroitUtilisateur, testInstances)
        mockDomain(Produit, testInstances20)
        assertTrue testInstances20[0].validate()
        testInstances20[0].save(flush:true)
        def testInstanceslp = [new LigneProduit(sequenceLigne : new Integer(02), quantite : new Double(1),quantiteLivree  :new Double(0d),quantiteRetournee:new Double(0d),remise:new Double(0d),prix:new Double(15.45),prixDeduit:new Double(15.45),type:"ACHAT",trans_sousTotal:new Double(15.45),trans_sousTotalLivree:new Double(15.45),trans_sousTotalRetourne:new Double(15.45),trans_livree:new Boolean(true),trans_retournee:new Boolean(true),trans_ql:new Double(15.45),trans_qr:new Double(15.45),date_preremption:sqlDate1,produit:testInstances20[0],entrepot:testInstancese[0],bonCommande:null,bonLivraison:null,bonPret:null,devis:null)]
        //mockForConstraintsTests(DroitUtilisateur, testInstances)
        mockDomain(LigneProduit, testInstanceslp)
        assertTrue testInstanceslp[0].validate()
        testInstanceslp[0].save(flush:true)
        
        def testInstancesd = [new Devis(numDevis : "dh", type : "ACHAT", date : sqlDate1,dateFinValidite:sqlDate1,trans_totalht:new Double(14.12),trans_totaltva:new Double(14.12),trans_totalttc:new Double(14.12),trans_isvalid:new Double(14.12),partenaire:testInstances9[0],ligneProduits:testInstanceslp)]
        //mockForConstraintsTests(DroitUtilisateur, testInstances)
        mockDomain(Devis, testInstancesd)
        assertTrue testInstancesd[0].validate()
        testInstancesd[0].save(flush:true)
        
        def testInstances = [new BonCommande(numBC : "bc001", type : "VENTE", reference : "5561",date:sqlDate1,trans_totalht:new Double(14.12),trans_totaltva:new Double(14.12),trans_totalttc:new Double(14.12),totalttcRetour:new Double(0d),livree:new Boolean(false),paye:new Boolean(false),retourPaye:new Boolean(true),facture:new Boolean(false),bonPret:new Boolean(true),retournee:new Boolean(false),avecRetour:new Boolean(false),trans_livree:new Boolean(true),trans_retournee:new Boolean(true),partenaire:testInstances9p[0],utilisateur:testInstancesu[0],deviss:testInstancesd,ligneProduits:testInstanceslp)]
        //mockForConstraintsTests(DroitUtilisateur, testInstances)
        mockDomain(BonCommande, testInstances)
        def testInstancesbd = [new BonLivraison(numBL : "XYZ", type : "ACHAT", reference : "5561",date:sqlDate1,trans_totalht:new Double(15.02),trans_totaltva:new Double(15.02),trans_totalttc:new Double(15.02),estBonRetour:new Boolean(true),estLivre:new Boolean(true),bonCommande:testInstances[0],ligneProduits:testInstanceslp)]
        //mockForConstraintsTests(DroitUtilisateur, testInstances)
        mockDomain(BonLivraison, testInstancesbd)
        def testService = new BonLivraisonService()
        def testInstances1 = [new PaternCompteur(libelle : "Pattern bon commande", prefixe : "BC{annee}{mois}{jour}", suffixe : "",pas:1,remplissage:12,numeroSuivant:2290,type:"BON_RETOUR")]
        //mockForConstraintsTests(DroitUtilisateur, testInstances)
        mockDomain(PaternCompteur, testInstances1)
       def testId = "bc515151"
        System.out.println("nb bb "+testInstances[0].bonPret)
def paternCompteurControl = mockFor(PaternCompteurService)
paternCompteurControl.demand.getProchainNumBrEtIncrementer(1..1) {it -> return  testId}
testService.paternCompteurService = paternCompteurControl.createMock()
def t=testInstances[0]
System.out.println("hjvhjv "+t)
def myCriteria = [
    list : {Closure  cls -> return testInstancesbd}
]
BonLivraison.metaClass.static.createCriteria = { myCriteria }
//def ff = testService.updateEtatBonCommandeLorsRetour(testInstances[0]) 

        
        def bonCommandeServiceControl = mockFor(BonCommandeService)
bonCommandeServiceControl.demand.update(testInstances[0]) {return testInstances[0] }
testService.bonCommandeService = bonCommandeServiceControl.createMock()

        def paiementServiceControl = mockFor(PaiementService)
paiementServiceControl.demand.updateEtatPaiementBonCommandeAprsRetour(testInstances[0]) {return  testInstances[0]}
testService.paiementService = paiementServiceControl.createMock()
def retval1 = testService.save(testInstancesbd[0])   
       assertTrue  retval1 instanceof BonLivraison
  System.out.println("la fin du tests"+retval1)        
        
}

 void testSauverAvecLivraison() {
     
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
        
        
        
       def testInstances9 = [new Partenaire(code : "005", raisonSociale : "celibataire", idFiscal : null,patente:"15165165",registreCommerce:"commerce",cnss:"fk13252152",adresse:"oulfa",codePostal:"20200",telephone:"0673327392",fax:"0632972654",email:"contact@choranet.com",siteWeb:"www.choranet.com",adresseFacturation:"hay el hana",adresseLivraison:"ain sebaii",estClient:new Boolean(true),estFournisseur:new Boolean(false),estParticulier:new Boolean(false),estBlacklist:new Boolean(false),plafond:new Double(12.12),modeRelancePaiement:testInstances2m[0],modeReglementDefaut:testInstances3m[0],echeance:testInstances4e[0],ville:testInstances6v[0],formeJuridique:testInstances7f[0],modeLivraison:testInstances8m[0],categoriePartenaires:testInstances1c,solde:new Double(15.01),compteVerrouile:new Boolean(true),zone:testInstancesz[0],commercial:testInstancesee[0])]        //mockForConstraintsTests(DroitUtilisateur, testInstances)
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
       
        def utilisateur2 = new Utilisateur()
        assertFalse utilisateur2.validate()
        assertEquals "nullable", utilisateur2.errors["username"]
        assertEquals "nullable", utilisateur2.errors["userRealName"]
        assertEquals "nullable", utilisateur2.errors["passwd"]
        assertEquals "nullable", utilisateur2.errors["groupe"]
        assertEquals "nullable", utilisateur2.errors["societe"]
       
        def utilisateur3= new Utilisateur(username : "XYZ", userRealName : "UTILISATEUR_XYZ",passwd :"allali",enabled:true,groupe:testInstances1g[0],societe:testInstances3[0] )
        assertFalse utilisateur3.validate()
        assertEquals "unique", utilisateur3.errors["username"]
        
        def utilisateur4= new Utilisateur(username : "admin1", userRealName : "Super Utilisateur",passwd :"NirfFVL6jts=",email:"contact@choranet.com",enabled:true,groupe:testInstances1g[0],societe:testInstances3[0])
        def f=utilisateur4.validate()
        def grooupe1=new GroupeUtilisateur(intitule:"ddd",description:"decision",droits:null)
        assertTrue testInstances1g[0].validate()
        testInstances1g[0].save(flush:true)
        
        assertTrue testInstances2[0].validate()
        testInstances2[0].save(flush:true)
        
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
        
        
        def testInstances9p = [new Partenaire(code : "005", raisonSociale : "celibataire", idFiscal : null,patente:"15165165",registreCommerce:"commerce",cnss:"fk13252152",adresse:"oulfa",codePostal:"20200",telephone:"0673327392",fax:"0632972654",email:"contact@choranet.com",siteWeb:"www.choranet.com",adresseFacturation:"hay el hana",adresseLivraison:"ain sebaii",estClient:new Boolean(true),estFournisseur:new Boolean(false),estParticulier:new Boolean(false),estBlacklist:new Boolean(false),plafond:new Double(12.12),modeRelancePaiement:testInstances2m[0],modeReglementDefaut:testInstances3m[0],echeance:testInstances4e[0],ville:testInstances6v[0],formeJuridique:testInstances7f[0],modeLivraison:testInstances8m[0],categoriePartenaires:testInstances1c,solde:new Double(15.01),compteVerrouile:new Boolean(true),zone:testInstancesz[0],commercial:testInstancesee[0])]
        mockDomain(Partenaire, testInstances9p)
        assertTrue testInstances9p[0].validate()
        testInstances9p[0].save(flush:true)
        def testInstances10 = [new Entrepot(code: "006", intitule : "ain sebaa", adresse : "rue 26 n°51",tel:"0673327392",modeValorisation:"PRIX_ACHAT",mode:"FIFO")]
        //mockForConstraintsTests(DroitUtilisateur, testInstances)
        mockDomain(Entrepot, testInstances10)
        assertTrue testInstances10[0].validate()
        testInstances10[0].save(flush:true)
         def testInstances11 = [new Nature(libelle : "normale")]
        //mockForConstraintsTests(DroitUtilisateur, testInstances)
        mockDomain(Nature, testInstances11)
        assertTrue testInstances11[0].validate()
        testInstances11[0].save(flush:true)
        def testInstances12 = [new CategorieProduit(code : "21211515", intitule : "alimentaire", categorieParente : null)]
        //mockForConstraintsTests(DroitUtilisateur, testInstances)
        mockDomain(CategorieProduit, testInstances12)
        assertTrue testInstances12[0].validate()
        testInstances12[0].save(flush:true)
        def testInstances13 = [new UniteMesure(libelle : "metre")]
        //mockForConstraintsTests(DroitUtilisateur, testInstances)
        mockDomain(UniteMesure, testInstances13)
        assertTrue testInstances13[0].validate()
        testInstances13[0].save(flush:true)
        def j=new Integer(20)
         def j1=new Integer(25)
        def testInstances14 = [new RegimeTVA(libelle : "20%", taux : j)]
        //mockForConstraintsTests(DroitUtilisateur, testInstances)
        mockDomain(RegimeTVA, testInstances14)
        assertTrue testInstances14[0].validate()
        testInstances14[0].save(flush:true)
        
        
        
        
        
        def testInstances20 = [new Produit(code : "0151513251", codebarre : "xxl01222",profil:"alimentaire",designation:"marjane",prixRevient:new Double(14.12),prixVenteStandard:new Double(14.12),poids:new Double(14.12),prixAchat:new Double(14.12),prixMoyenPendere:new Double(14.12),perissable:new Boolean(false),temperature_conservation:new Double(52.12),imageProd:byte1,trans_imageProd:i,empReception:testInstances10[0],nature:testInstances11[0],categorieProduit:testInstances12[0],uniteMesure:testInstances13[0],regimeTVA:testInstances14[0],produitParent:null,fournisseurPref:testInstances9[0])]
        //mockForConstraintsTests(DroitUtilisateur, testInstances)
        mockDomain(Produit, testInstances20)
        assertTrue testInstances20[0].validate()
        testInstances20[0].save(flush:true)
        def testInstanceslp = [new LigneProduit(sequenceLigne : new Integer(02), quantite : new Double(1),quantiteLivree  :new Double(0d),quantiteRetournee:new Double(0d),remise:new Double(0d),prix:new Double(15.45),prixDeduit:new Double(15.45),type:"ACHAT",trans_sousTotal:new Double(15.45),trans_sousTotalLivree:new Double(15.45),trans_sousTotalRetourne:new Double(15.45),trans_livree:new Boolean(true),trans_retournee:new Boolean(true),trans_ql:new Double(15.45),trans_qr:new Double(15.45),date_preremption:sqlDate1,produit:testInstances20[0],entrepot:testInstancese[0],bonCommande:null,bonLivraison:null,bonPret:null,devis:null)]
        //mockForConstraintsTests(DroitUtilisateur, testInstances)
        mockDomain(LigneProduit, testInstanceslp)
        assertTrue testInstanceslp[0].validate()
        testInstanceslp[0].save(flush:true)
        
        def testInstancesd = [new Devis(numDevis : "dh", type : "ACHAT", date : sqlDate1,dateFinValidite:sqlDate1,trans_totalht:new Double(14.12),trans_totaltva:new Double(14.12),trans_totalttc:new Double(14.12),trans_isvalid:new Double(14.12),partenaire:testInstances9[0],ligneProduits:testInstanceslp)]
        //mockForConstraintsTests(DroitUtilisateur, testInstances)
        mockDomain(Devis, testInstancesd)
        assertTrue testInstancesd[0].validate()
        testInstancesd[0].save(flush:true)
        
        def testInstances = [new BonCommande(numBC : "bc001", type : "VENTE", reference : "5561",date:sqlDate1,trans_totalht:new Double(14.12),trans_totaltva:new Double(14.12),trans_totalttc:new Double(14.12),totalttcRetour:new Double(0d),livree:new Boolean(false),paye:new Boolean(false),retourPaye:new Boolean(true),facture:new Boolean(false),bonPret:new Boolean(true),retournee:new Boolean(false),avecRetour:new Boolean(false),trans_livree:new Boolean(true),trans_retournee:new Boolean(true),partenaire:testInstances9p[0],utilisateur:testInstancesu[0],deviss:testInstancesd,ligneProduits:testInstanceslp)]
        //mockForConstraintsTests(DroitUtilisateur, testInstances)
        mockDomain(BonCommande, testInstances)
        def testInstancesbd = [new BonLivraison(numBL : "XYZ", type : "ACHAT", reference : "5561",date:sqlDate1,trans_totalht:new Double(15.02),trans_totaltva:new Double(15.02),trans_totalttc:new Double(15.02),estBonRetour:new Boolean(true),estLivre:new Boolean(true),bonCommande:testInstances[0],ligneProduits:testInstanceslp)]
        //mockForConstraintsTests(DroitUtilisateur, testInstances)
        mockDomain(BonLivraison, testInstancesbd)
        def testService = new BonLivraisonService()
        def testInstances1 = [new PaternCompteur(libelle : "Pattern bon commande", prefixe : "BC{annee}{mois}{jour}", suffixe : "",pas:1,remplissage:12,numeroSuivant:2290,type:"BON_RETOUR")]
        //mockForConstraintsTests(DroitUtilisateur, testInstances)
        mockDomain(PaternCompteur, testInstances1)
       def testId = "bc515151"
        System.out.println("nb bb "+testInstances[0].bonPret)
        
        def testInstancesm = [new ModeLivraison(code : "0032", transporteur : "stcr", paiementALaReception : new Boolean(true))]
        //mockForConstraintsTests(DroitUtilisateur, testInstances)
        mockDomain(ModeLivraison, testInstancesm)
        assertTrue testInstancesm[0].validate()
        testInstancesm[0].save(flush:true)
        def testInstancesli = [new Livraison(numExpedition : "l1555525",nbrColis  : new Integer(0), poidTotal : new Double(0d),frais:new Double(0d),date:sqlDate1,detail:"livraison a domicile",intituleVehicule:"hvjfh564564",immatriculation:"vgvg54654645",modeLivraison:testInstancesm[0],partenaire:testInstances9p[0],bonLivraisons:testInstancesbd)]
        //mockForConstraintsTests(DroitUtilisateur, testInstances)
        mockDomain(Livraison, testInstancesli)
        
        
        
        
        
        
def paternCompteurControl = mockFor(PaternCompteurService)
paternCompteurControl.demand.getProchainNumBrEtIncrementer(1..1) {it -> return  testId}
testService.paternCompteurService = paternCompteurControl.createMock()
def t=testInstances[0]
System.out.println("hjvhjv "+t)
def myCriteria = [
    list : {Closure  cls -> return testInstancesbd}
]
BonLivraison.metaClass.static.createCriteria = { myCriteria }
//def ff = testService.updateEtatBonCommandeLorsRetour(testInstances[0]) 

        
        def livraisonServiceControl = mockFor(LivraisonService)
livraisonServiceControl.demand.sauverEtAjouterBl(testInstancesli[0],testInstancesbd[0]) {return testInstancesli[0] }
testService.livraisonService = livraisonServiceControl.createMock()

        def bonCommandeServiceControl = mockFor(BonCommandeService)
bonCommandeServiceControl.demand.update(testInstances[0]) {return testInstances[0] }
testService.bonCommandeService = bonCommandeServiceControl.createMock()

        def paiementServiceControl = mockFor(PaiementService)
paiementServiceControl.demand.updateEtatPaiementBonCommandeAprsRetour(testInstances[0]) {return  testInstances[0]}
testService.paiementService = paiementServiceControl.createMock()
def retval1 = testService.sauverAvecLivraison(testInstancesbd[0],testInstancesli[0])   
       assertTrue  retval1 instanceof BonLivraison
  System.out.println("la fin du tests de save avec livraison"+retval1)        
           
        
    }
    
    def testMettreAjourEtAjouterLivraison() {
        
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
        
        
        
       def testInstances9 = [new Partenaire(code : "005", raisonSociale : "celibataire", idFiscal : null,patente:"15165165",registreCommerce:"commerce",cnss:"fk13252152",adresse:"oulfa",codePostal:"20200",telephone:"0673327392",fax:"0632972654",email:"contact@choranet.com",siteWeb:"www.choranet.com",adresseFacturation:"hay el hana",adresseLivraison:"ain sebaii",estClient:new Boolean(true),estFournisseur:new Boolean(false),estParticulier:new Boolean(false),estBlacklist:new Boolean(false),plafond:new Double(12.12),modeRelancePaiement:testInstances2m[0],modeReglementDefaut:testInstances3m[0],echeance:testInstances4e[0],ville:testInstances6v[0],formeJuridique:testInstances7f[0],modeLivraison:testInstances8m[0],categoriePartenaires:testInstances1c,solde:new Double(15.01),compteVerrouile:new Boolean(true),zone:testInstancesz[0],commercial:testInstancesee[0])]        //mockForConstraintsTests(DroitUtilisateur, testInstances)
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
       
        def utilisateur2 = new Utilisateur()
        assertFalse utilisateur2.validate()
        assertEquals "nullable", utilisateur2.errors["username"]
        assertEquals "nullable", utilisateur2.errors["userRealName"]
        assertEquals "nullable", utilisateur2.errors["passwd"]
        assertEquals "nullable", utilisateur2.errors["groupe"]
        assertEquals "nullable", utilisateur2.errors["societe"]
       
        def utilisateur3= new Utilisateur(username : "XYZ", userRealName : "UTILISATEUR_XYZ",passwd :"allali",enabled:true,groupe:testInstances1g[0],societe:testInstances3[0] )
        assertFalse utilisateur3.validate()
        assertEquals "unique", utilisateur3.errors["username"]
        
        def utilisateur4= new Utilisateur(username : "admin1", userRealName : "Super Utilisateur",passwd :"NirfFVL6jts=",email:"contact@choranet.com",enabled:true,groupe:testInstances1g[0],societe:testInstances3[0])
        def f=utilisateur4.validate()
        def grooupe1=new GroupeUtilisateur(intitule:"ddd",description:"decision",droits:null)
        assertTrue testInstances1g[0].validate()
        testInstances1g[0].save(flush:true)
        
        assertTrue testInstances2[0].validate()
        testInstances2[0].save(flush:true)
        
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
        
        
        def testInstances9p = [new Partenaire(code : "005", raisonSociale : "celibataire", idFiscal : null,patente:"15165165",registreCommerce:"commerce",cnss:"fk13252152",adresse:"oulfa",codePostal:"20200",telephone:"0673327392",fax:"0632972654",email:"contact@choranet.com",siteWeb:"www.choranet.com",adresseFacturation:"hay el hana",adresseLivraison:"ain sebaii",estClient:new Boolean(true),estFournisseur:new Boolean(false),estParticulier:new Boolean(false),estBlacklist:new Boolean(false),plafond:new Double(12.12),modeRelancePaiement:testInstances2m[0],modeReglementDefaut:testInstances3m[0],echeance:testInstances4e[0],ville:testInstances6v[0],formeJuridique:testInstances7f[0],modeLivraison:testInstances8m[0],categoriePartenaires:testInstances1c,solde:new Double(15.01),compteVerrouile:new Boolean(true),zone:testInstancesz[0],commercial:testInstancesee[0])]
        mockDomain(Partenaire, testInstances9p)
        assertTrue testInstances9p[0].validate()
        testInstances9p[0].save(flush:true)
        def testInstances10 = [new Entrepot(code: "006", intitule : "ain sebaa", adresse : "rue 26 n°51",tel:"0673327392",modeValorisation:"PRIX_ACHAT",mode:"FIFO")]
        //mockForConstraintsTests(DroitUtilisateur, testInstances)
        mockDomain(Entrepot, testInstances10)
        assertTrue testInstances10[0].validate()
        testInstances10[0].save(flush:true)
         def testInstances11 = [new Nature(libelle : "normale")]
        //mockForConstraintsTests(DroitUtilisateur, testInstances)
        mockDomain(Nature, testInstances11)
        assertTrue testInstances11[0].validate()
        testInstances11[0].save(flush:true)
        def testInstances12 = [new CategorieProduit(code : "21211515", intitule : "alimentaire", categorieParente : null)]
        //mockForConstraintsTests(DroitUtilisateur, testInstances)
        mockDomain(CategorieProduit, testInstances12)
        assertTrue testInstances12[0].validate()
        testInstances12[0].save(flush:true)
        def testInstances13 = [new UniteMesure(libelle : "metre")]
        //mockForConstraintsTests(DroitUtilisateur, testInstances)
        mockDomain(UniteMesure, testInstances13)
        assertTrue testInstances13[0].validate()
        testInstances13[0].save(flush:true)
        def j=new Integer(20)
         def j1=new Integer(25)
        def testInstances14 = [new RegimeTVA(libelle : "20%", taux : j)]
        //mockForConstraintsTests(DroitUtilisateur, testInstances)
        mockDomain(RegimeTVA, testInstances14)
        assertTrue testInstances14[0].validate()
        testInstances14[0].save(flush:true)
        
        
        
        
        
        def testInstances20 = [new Produit(code : "0151513251", codebarre : "xxl01222",profil:"alimentaire",designation:"marjane",prixRevient:new Double(14.12),prixVenteStandard:new Double(14.12),poids:new Double(14.12),prixAchat:new Double(14.12),prixMoyenPendere:new Double(14.12),perissable:new Boolean(false),temperature_conservation:new Double(52.12),imageProd:byte1,trans_imageProd:i,empReception:testInstances10[0],nature:testInstances11[0],categorieProduit:testInstances12[0],uniteMesure:testInstances13[0],regimeTVA:testInstances14[0],produitParent:null,fournisseurPref:testInstances9[0])]
        //mockForConstraintsTests(DroitUtilisateur, testInstances)
        mockDomain(Produit, testInstances20)
        assertTrue testInstances20[0].validate()
        testInstances20[0].save(flush:true)
        def testInstanceslp = [new LigneProduit(sequenceLigne : new Integer(02), quantite : new Double(1),quantiteLivree  :new Double(0d),quantiteRetournee:new Double(0d),remise:new Double(0d),prix:new Double(15.45),prixDeduit:new Double(15.45),type:"ACHAT",trans_sousTotal:new Double(15.45),trans_sousTotalLivree:new Double(15.45),trans_sousTotalRetourne:new Double(15.45),trans_livree:new Boolean(true),trans_retournee:new Boolean(true),trans_ql:new Double(15.45),trans_qr:new Double(15.45),date_preremption:sqlDate1,produit:testInstances20[0],entrepot:testInstancese[0],bonCommande:null,bonLivraison:null,bonPret:null,devis:null)]
        //mockForConstraintsTests(DroitUtilisateur, testInstances)
        mockDomain(LigneProduit, testInstanceslp)
        assertTrue testInstanceslp[0].validate()
        testInstanceslp[0].save(flush:true)
        
        def testInstancesd = [new Devis(numDevis : "dh", type : "ACHAT", date : sqlDate1,dateFinValidite:sqlDate1,trans_totalht:new Double(14.12),trans_totaltva:new Double(14.12),trans_totalttc:new Double(14.12),trans_isvalid:new Double(14.12),partenaire:testInstances9[0],ligneProduits:testInstanceslp)]
        //mockForConstraintsTests(DroitUtilisateur, testInstances)
        mockDomain(Devis, testInstancesd)
        assertTrue testInstancesd[0].validate()
        testInstancesd[0].save(flush:true)
        
        def testInstances = [new BonCommande(numBC : "bc001", type : "VENTE", reference : "5561",date:sqlDate1,trans_totalht:new Double(14.12),trans_totaltva:new Double(14.12),trans_totalttc:new Double(14.12),totalttcRetour:new Double(0d),livree:new Boolean(false),paye:new Boolean(false),retourPaye:new Boolean(true),facture:new Boolean(false),bonPret:new Boolean(true),retournee:new Boolean(false),avecRetour:new Boolean(false),trans_livree:new Boolean(true),trans_retournee:new Boolean(true),partenaire:testInstances9p[0],utilisateur:testInstancesu[0],deviss:testInstancesd,ligneProduits:testInstanceslp)]
        //mockForConstraintsTests(DroitUtilisateur, testInstances)
        mockDomain(BonCommande, testInstances)
        def testInstancesbd = [new BonLivraison(numBL : "XYZ", type : "ACHAT", reference : "5561",date:sqlDate1,trans_totalht:new Double(15.02),trans_totaltva:new Double(15.02),trans_totalttc:new Double(15.02),estBonRetour:new Boolean(true),estLivre:new Boolean(true),bonCommande:testInstances[0],ligneProduits:testInstanceslp)]
        //mockForConstraintsTests(DroitUtilisateur, testInstances)
        mockDomain(BonLivraison, testInstancesbd)
        def testService = new BonLivraisonService()
        def testInstances1 = [new PaternCompteur(libelle : "Pattern bon commande", prefixe : "BC{annee}{mois}{jour}", suffixe : "",pas:1,remplissage:12,numeroSuivant:2290,type:"BON_RETOUR")]
        //mockForConstraintsTests(DroitUtilisateur, testInstances)
        mockDomain(PaternCompteur, testInstances1)
       def testId = "bc515151"
        System.out.println("nb bb "+testInstances[0].bonPret)
        
        def testInstancesm = [new ModeLivraison(code : "0032", transporteur : "stcr", paiementALaReception : new Boolean(true))]
        //mockForConstraintsTests(DroitUtilisateur, testInstances)
        mockDomain(ModeLivraison, testInstancesm)
        assertTrue testInstancesm[0].validate()
        testInstancesm[0].save(flush:true)
        def testInstancesli = [new Livraison(numExpedition : "l1555525",nbrColis  : new Integer(0), poidTotal : new Double(0d),frais:new Double(0d),date:sqlDate1,detail:"livraison a domicile",intituleVehicule:"hvjfh564564",immatriculation:"vgvg54654645",modeLivraison:testInstancesm[0],partenaire:testInstances9p[0],bonLivraisons:testInstancesbd)]
        //mockForConstraintsTests(DroitUtilisateur, testInstances)
        mockDomain(Livraison, testInstancesli)
        
        
        
        
        
        
def paternCompteurControl = mockFor(PaternCompteurService)
paternCompteurControl.demand.getProchainNumBrEtIncrementer(1..1) {it -> return  testId}
testService.paternCompteurService = paternCompteurControl.createMock()
def t=testInstances[0]
System.out.println("hjvhjv "+t)
def myCriteria = [
    list : {Closure  cls -> return testInstancesbd}
]
BonLivraison.metaClass.static.createCriteria = { myCriteria }
//def ff = testService.updateEtatBonCommandeLorsRetour(testInstances[0]) 

        
        def livraisonServiceControl = mockFor(LivraisonService)
livraisonServiceControl.demand.sauverEtAjouterBl(testInstancesli[0],testInstancesbd[0]) {return testInstancesli[0] }
testService.livraisonService = livraisonServiceControl.createMock()

        def bonCommandeServiceControl = mockFor(BonCommandeService)
bonCommandeServiceControl.demand.update(testInstances[0]) {return testInstances[0] }
testService.bonCommandeService = bonCommandeServiceControl.createMock()

        def paiementServiceControl = mockFor(PaiementService)
paiementServiceControl.demand.updateEtatPaiementBonCommandeAprsRetour(testInstances[0]) {return  testInstances[0]}
testService.paiementService = paiementServiceControl.createMock()
def retval1 = testService.mettreAjourEtAjouterLivraison(testInstancesbd[0],testInstancesli[0])   
       assertTrue  retval1 instanceof BonLivraison
  System.out.println("la fin du tests de mettreAjourEtAjouterLivraison"+retval1)  
        
        
    }   
    
    def testDelete() {
        
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
        
        
        
       def testInstances9 = [new Partenaire(code : "005", raisonSociale : "celibataire", idFiscal : null,patente:"15165165",registreCommerce:"commerce",cnss:"fk13252152",adresse:"oulfa",codePostal:"20200",telephone:"0673327392",fax:"0632972654",email:"contact@choranet.com",siteWeb:"www.choranet.com",adresseFacturation:"hay el hana",adresseLivraison:"ain sebaii",estClient:new Boolean(true),estFournisseur:new Boolean(false),estParticulier:new Boolean(false),estBlacklist:new Boolean(false),plafond:new Double(12.12),modeRelancePaiement:testInstances2m[0],modeReglementDefaut:testInstances3m[0],echeance:testInstances4e[0],ville:testInstances6v[0],formeJuridique:testInstances7f[0],modeLivraison:testInstances8m[0],categoriePartenaires:testInstances1c,solde:new Double(15.01),compteVerrouile:new Boolean(true),zone:testInstancesz[0],commercial:testInstancesee[0])]        //mockForConstraintsTests(DroitUtilisateur, testInstances)
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
       
        def utilisateur2 = new Utilisateur()
        assertFalse utilisateur2.validate()
        assertEquals "nullable", utilisateur2.errors["username"]
        assertEquals "nullable", utilisateur2.errors["userRealName"]
        assertEquals "nullable", utilisateur2.errors["passwd"]
        assertEquals "nullable", utilisateur2.errors["groupe"]
        assertEquals "nullable", utilisateur2.errors["societe"]
       
        def utilisateur3= new Utilisateur(username : "XYZ", userRealName : "UTILISATEUR_XYZ",passwd :"allali",enabled:true,groupe:testInstances1g[0],societe:testInstances3[0] )
        assertFalse utilisateur3.validate()
        assertEquals "unique", utilisateur3.errors["username"]
        
        def utilisateur4= new Utilisateur(username : "admin1", userRealName : "Super Utilisateur",passwd :"NirfFVL6jts=",email:"contact@choranet.com",enabled:true,groupe:testInstances1g[0],societe:testInstances3[0])
        def f=utilisateur4.validate()
        def grooupe1=new GroupeUtilisateur(intitule:"ddd",description:"decision",droits:null)
        assertTrue testInstances1g[0].validate()
        testInstances1g[0].save(flush:true)
        
        assertTrue testInstances2[0].validate()
        testInstances2[0].save(flush:true)
        
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
        
        
        def testInstances9p = [new Partenaire(code : "005", raisonSociale : "celibataire", idFiscal : null,patente:"15165165",registreCommerce:"commerce",cnss:"fk13252152",adresse:"oulfa",codePostal:"20200",telephone:"0673327392",fax:"0632972654",email:"contact@choranet.com",siteWeb:"www.choranet.com",adresseFacturation:"hay el hana",adresseLivraison:"ain sebaii",estClient:new Boolean(true),estFournisseur:new Boolean(false),estParticulier:new Boolean(false),estBlacklist:new Boolean(false),plafond:new Double(12.12),modeRelancePaiement:testInstances2m[0],modeReglementDefaut:testInstances3m[0],echeance:testInstances4e[0],ville:testInstances6v[0],formeJuridique:testInstances7f[0],modeLivraison:testInstances8m[0],categoriePartenaires:testInstances1c,solde:new Double(15.01),compteVerrouile:new Boolean(true),zone:testInstancesz[0],commercial:testInstancesee[0])]
        mockDomain(Partenaire, testInstances9p)
        assertTrue testInstances9p[0].validate()
        testInstances9p[0].save(flush:true)
        def testInstances10 = [new Entrepot(code: "006", intitule : "ain sebaa", adresse : "rue 26 n°51",tel:"0673327392",modeValorisation:"PRIX_ACHAT",mode:"FIFO")]
        //mockForConstraintsTests(DroitUtilisateur, testInstances)
        mockDomain(Entrepot, testInstances10)
        assertTrue testInstances10[0].validate()
        testInstances10[0].save(flush:true)
         def testInstances11 = [new Nature(libelle : "normale")]
        //mockForConstraintsTests(DroitUtilisateur, testInstances)
        mockDomain(Nature, testInstances11)
        assertTrue testInstances11[0].validate()
        testInstances11[0].save(flush:true)
        def testInstances12 = [new CategorieProduit(code : "21211515", intitule : "alimentaire", categorieParente : null)]
        //mockForConstraintsTests(DroitUtilisateur, testInstances)
        mockDomain(CategorieProduit, testInstances12)
        assertTrue testInstances12[0].validate()
        testInstances12[0].save(flush:true)
        def testInstances13 = [new UniteMesure(libelle : "metre")]
        //mockForConstraintsTests(DroitUtilisateur, testInstances)
        mockDomain(UniteMesure, testInstances13)
        assertTrue testInstances13[0].validate()
        testInstances13[0].save(flush:true)
        def j=new Integer(20)
         def j1=new Integer(25)
        def testInstances14 = [new RegimeTVA(libelle : "20%", taux : j)]
        //mockForConstraintsTests(DroitUtilisateur, testInstances)
        mockDomain(RegimeTVA, testInstances14)
        assertTrue testInstances14[0].validate()
        testInstances14[0].save(flush:true)
        
        
        
        
        
        def testInstances20 = [new Produit(code : "0151513251", codebarre : "xxl01222",profil:"alimentaire",designation:"marjane",prixRevient:new Double(14.12),prixVenteStandard:new Double(14.12),poids:new Double(14.12),prixAchat:new Double(14.12),prixMoyenPendere:new Double(14.12),perissable:new Boolean(false),temperature_conservation:new Double(52.12),imageProd:byte1,trans_imageProd:i,empReception:testInstances10[0],nature:testInstances11[0],categorieProduit:testInstances12[0],uniteMesure:testInstances13[0],regimeTVA:testInstances14[0],produitParent:null,fournisseurPref:testInstances9[0])]
        //mockForConstraintsTests(DroitUtilisateur, testInstances)
        mockDomain(Produit, testInstances20)
        assertTrue testInstances20[0].validate()
        testInstances20[0].save(flush:true)
        def testInstanceslp = [new LigneProduit(sequenceLigne : new Integer(02), quantite : new Double(1),quantiteLivree  :new Double(0d),quantiteRetournee:new Double(0d),remise:new Double(0d),prix:new Double(15.45),prixDeduit:new Double(15.45),type:"ACHAT",trans_sousTotal:new Double(15.45),trans_sousTotalLivree:new Double(15.45),trans_sousTotalRetourne:new Double(15.45),trans_livree:new Boolean(true),trans_retournee:new Boolean(true),trans_ql:new Double(15.45),trans_qr:new Double(15.45),date_preremption:sqlDate1,produit:testInstances20[0],entrepot:testInstancese[0],bonCommande:null,bonLivraison:null,bonPret:null,devis:null)]
        //mockForConstraintsTests(DroitUtilisateur, testInstances)
        mockDomain(LigneProduit, testInstanceslp)
        assertTrue testInstanceslp[0].validate()
        testInstanceslp[0].save(flush:true)
        
        def testInstancesd = [new Devis(numDevis : "dh", type : "ACHAT", date : sqlDate1,dateFinValidite:sqlDate1,trans_totalht:new Double(14.12),trans_totaltva:new Double(14.12),trans_totalttc:new Double(14.12),trans_isvalid:new Double(14.12),partenaire:testInstances9[0],ligneProduits:testInstanceslp)]
        //mockForConstraintsTests(DroitUtilisateur, testInstances)
        mockDomain(Devis, testInstancesd)
        assertTrue testInstancesd[0].validate()
        testInstancesd[0].save(flush:true)
        
        def testInstances = [new BonCommande(numBC : "bc001", type : "VENTE", reference : "5561",date:sqlDate1,trans_totalht:new Double(14.12),trans_totaltva:new Double(14.12),trans_totalttc:new Double(14.12),totalttcRetour:new Double(0d),livree:new Boolean(false),paye:new Boolean(false),retourPaye:new Boolean(true),facture:new Boolean(false),bonPret:new Boolean(true),retournee:new Boolean(false),avecRetour:new Boolean(false),trans_livree:new Boolean(true),trans_retournee:new Boolean(true),partenaire:testInstances9p[0],utilisateur:testInstancesu[0],deviss:testInstancesd,ligneProduits:testInstanceslp)]
        //mockForConstraintsTests(DroitUtilisateur, testInstances)
        mockDomain(BonCommande, testInstances)
        def testInstancesbd = [new BonLivraison(numBL : "XYZ", type : "ACHAT", reference : "5561",date:sqlDate1,trans_totalht:new Double(15.02),trans_totaltva:new Double(15.02),trans_totalttc:new Double(15.02),estBonRetour:new Boolean(true),estLivre:new Boolean(true),bonCommande:testInstances[0],ligneProduits:testInstanceslp)]
        //mockForConstraintsTests(DroitUtilisateur, testInstances)
        mockDomain(BonLivraison, testInstancesbd)
        def testService = new BonLivraisonService()
        def testInstances1 = [new PaternCompteur(libelle : "Pattern bon commande", prefixe : "BC{annee}{mois}{jour}", suffixe : "",pas:1,remplissage:12,numeroSuivant:2290,type:"BON_RETOUR")]
        //mockForConstraintsTests(DroitUtilisateur, testInstances)
        mockDomain(PaternCompteur, testInstances1)
       def testId = "bc515151"
        System.out.println("nb bb "+testInstances[0].bonPret)
        
        def testInstancesm = [new ModeLivraison(code : "0032", transporteur : "stcr", paiementALaReception : new Boolean(true))]
        //mockForConstraintsTests(DroitUtilisateur, testInstances)
        mockDomain(ModeLivraison, testInstancesm)
        assertTrue testInstancesm[0].validate()
        testInstancesm[0].save(flush:true)
        def testInstancesli = [new Livraison(numExpedition : "l1555525",nbrColis  : new Integer(0), poidTotal : new Double(0d),frais:new Double(0d),date:sqlDate1,detail:"livraison a domicile",intituleVehicule:"hvjfh564564",immatriculation:"vgvg54654645",modeLivraison:testInstancesm[0],partenaire:testInstances9p[0],bonLivraisons:testInstancesbd)]
        //mockForConstraintsTests(DroitUtilisateur, testInstances)
        mockDomain(Livraison, testInstancesli)
        
        
        
        
        
        
//def paternCompteurControl = mockFor(PaternCompteurService)
//paternCompteurControl.demand.getProchainNumBrEtIncrementer(1..1) {it -> return  testId}
//testService.paternCompteurService = paternCompteurControl.createMock()
def t=testInstances[0]
System.out.println("hjvhjv "+t)
def myCriteria = [
    list : {Closure  cls -> return testInstancesbd}
]
BonLivraison.metaClass.static.createCriteria = { myCriteria }
//def ff = testService.updateEtatBonCommandeLorsRetour(testInstances[0]) 

def myCriteria1 = [
    list : {Closure  cls -> return testInstancesli[0]}
]
Livraison.metaClass.static.createCriteria = { myCriteria1 }        
        
def numexpe= "l1555525"       
        def livraisonServiceControl = mockFor(LivraisonService)
livraisonServiceControl.demand.supprimerSiLieeAvecUnSeulBl(numexpe) {return testInstancesli[0] }
testService.livraisonService = livraisonServiceControl.createMock()

        def bonCommandeServiceControl = mockFor(BonCommandeService)
bonCommandeServiceControl.demand.update(testInstances[0]) {return testInstances[0] }
testService.bonCommandeService = bonCommandeServiceControl.createMock()

        def paiementServiceControl = mockFor(PaiementService)
paiementServiceControl.demand.updateEtatPaiementBonCommandeAprsRetour(testInstances[0]) {return  testInstances[0]}
testService.paiementService = paiementServiceControl.createMock()
def retval1 = testService.delete(testInstancesbd[0])   
       assertTrue retval1 instanceof BonCommande
  System.out.println("la fin du tests de delete"+retval1)  
        
    }
        
    void testUpdateEtatBonCommandeLorsRetour() {
        
        
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
        
        
        
       def testInstances9 = [new Partenaire(code : "005", raisonSociale : "celibataire", idFiscal : null,patente:"15165165",registreCommerce:"commerce",cnss:"fk13252152",adresse:"oulfa",codePostal:"20200",telephone:"0673327392",fax:"0632972654",email:"contact@choranet.com",siteWeb:"www.choranet.com",adresseFacturation:"hay el hana",adresseLivraison:"ain sebaii",estClient:new Boolean(true),estFournisseur:new Boolean(false),estParticulier:new Boolean(false),estBlacklist:new Boolean(false),plafond:new Double(12.12),modeRelancePaiement:testInstances2m[0],modeReglementDefaut:testInstances3m[0],echeance:testInstances4e[0],ville:testInstances6v[0],formeJuridique:testInstances7f[0],modeLivraison:testInstances8m[0],categoriePartenaires:testInstances1c,solde:new Double(15.01),compteVerrouile:new Boolean(true),zone:testInstancesz[0],commercial:testInstancesee[0])]        //mockForConstraintsTests(DroitUtilisateur, testInstances)
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
       
        def utilisateur2 = new Utilisateur()
        assertFalse utilisateur2.validate()
        assertEquals "nullable", utilisateur2.errors["username"]
        assertEquals "nullable", utilisateur2.errors["userRealName"]
        assertEquals "nullable", utilisateur2.errors["passwd"]
        assertEquals "nullable", utilisateur2.errors["groupe"]
        assertEquals "nullable", utilisateur2.errors["societe"]
       
        def utilisateur3= new Utilisateur(username : "XYZ", userRealName : "UTILISATEUR_XYZ",passwd :"allali",enabled:true,groupe:testInstances1g[0],societe:testInstances3[0] )
        assertFalse utilisateur3.validate()
        assertEquals "unique", utilisateur3.errors["username"]
        
        def utilisateur4= new Utilisateur(username : "admin1", userRealName : "Super Utilisateur",passwd :"NirfFVL6jts=",email:"contact@choranet.com",enabled:true,groupe:testInstances1g[0],societe:testInstances3[0])
        def f=utilisateur4.validate()
        def grooupe1=new GroupeUtilisateur(intitule:"ddd",description:"decision",droits:null)
        assertTrue testInstances1g[0].validate()
        testInstances1g[0].save(flush:true)
        
        assertTrue testInstances2[0].validate()
        testInstances2[0].save(flush:true)
        
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
        
        
        def testInstances9p = [new Partenaire(code : "005", raisonSociale : "celibataire", idFiscal : null,patente:"15165165",registreCommerce:"commerce",cnss:"fk13252152",adresse:"oulfa",codePostal:"20200",telephone:"0673327392",fax:"0632972654",email:"contact@choranet.com",siteWeb:"www.choranet.com",adresseFacturation:"hay el hana",adresseLivraison:"ain sebaii",estClient:new Boolean(true),estFournisseur:new Boolean(false),estParticulier:new Boolean(false),estBlacklist:new Boolean(false),plafond:new Double(12.12),modeRelancePaiement:testInstances2m[0],modeReglementDefaut:testInstances3m[0],echeance:testInstances4e[0],ville:testInstances6v[0],formeJuridique:testInstances7f[0],modeLivraison:testInstances8m[0],categoriePartenaires:testInstances1c,solde:new Double(15.01),compteVerrouile:new Boolean(true),zone:testInstancesz[0],commercial:testInstancesee[0])]
        mockDomain(Partenaire, testInstances9p)
        assertTrue testInstances9p[0].validate()
        testInstances9p[0].save(flush:true)
        def testInstances10 = [new Entrepot(code: "006", intitule : "ain sebaa", adresse : "rue 26 n°51",tel:"0673327392",modeValorisation:"PRIX_ACHAT",mode:"FIFO")]
        //mockForConstraintsTests(DroitUtilisateur, testInstances)
        mockDomain(Entrepot, testInstances10)
        assertTrue testInstances10[0].validate()
        testInstances10[0].save(flush:true)
         def testInstances11 = [new Nature(libelle : "normale")]
        //mockForConstraintsTests(DroitUtilisateur, testInstances)
        mockDomain(Nature, testInstances11)
        assertTrue testInstances11[0].validate()
        testInstances11[0].save(flush:true)
        def testInstances12 = [new CategorieProduit(code : "21211515", intitule : "alimentaire", categorieParente : null)]
        //mockForConstraintsTests(DroitUtilisateur, testInstances)
        mockDomain(CategorieProduit, testInstances12)
        assertTrue testInstances12[0].validate()
        testInstances12[0].save(flush:true)
        def testInstances13 = [new UniteMesure(libelle : "metre")]
        //mockForConstraintsTests(DroitUtilisateur, testInstances)
        mockDomain(UniteMesure, testInstances13)
        assertTrue testInstances13[0].validate()
        testInstances13[0].save(flush:true)
        def j=new Integer(20)
         def j1=new Integer(25)
        def testInstances14 = [new RegimeTVA(libelle : "20%", taux : j)]
        //mockForConstraintsTests(DroitUtilisateur, testInstances)
        mockDomain(RegimeTVA, testInstances14)
        assertTrue testInstances14[0].validate()
        testInstances14[0].save(flush:true)
        
        
        
        
        
        def testInstances20 = [new Produit(code : "0151513251", codebarre : "xxl01222",profil:"alimentaire",designation:"marjane",prixRevient:new Double(14.12),prixVenteStandard:new Double(14.12),poids:new Double(14.12),prixAchat:new Double(14.12),prixMoyenPendere:new Double(14.12),perissable:new Boolean(false),temperature_conservation:new Double(52.12),imageProd:byte1,trans_imageProd:i,empReception:testInstances10[0],nature:testInstances11[0],categorieProduit:testInstances12[0],uniteMesure:testInstances13[0],regimeTVA:testInstances14[0],produitParent:null,fournisseurPref:testInstances9[0])]
        //mockForConstraintsTests(DroitUtilisateur, testInstances)
        mockDomain(Produit, testInstances20)
        assertTrue testInstances20[0].validate()
        testInstances20[0].save(flush:true)
        def testInstanceslp = [new LigneProduit(sequenceLigne : new Integer(02), quantite : new Double(1),quantiteLivree  :new Double(0d),quantiteRetournee:new Double(0d),remise:new Double(0d),prix:new Double(15.45),prixDeduit:new Double(15.45),type:"ACHAT",trans_sousTotal:new Double(15.45),trans_sousTotalLivree:new Double(15.45),trans_sousTotalRetourne:new Double(15.45),trans_livree:new Boolean(true),trans_retournee:new Boolean(true),trans_ql:new Double(15.45),trans_qr:new Double(15.45),date_preremption:sqlDate1,produit:testInstances20[0],entrepot:testInstancese[0],bonCommande:null,bonLivraison:null,bonPret:null,devis:null)]
        //mockForConstraintsTests(DroitUtilisateur, testInstances)
        mockDomain(LigneProduit, testInstanceslp)
        assertTrue testInstanceslp[0].validate()
        testInstanceslp[0].save(flush:true)
        
        def testInstancesd = [new Devis(numDevis : "dh", type : "ACHAT", date : sqlDate1,dateFinValidite:sqlDate1,trans_totalht:new Double(14.12),trans_totaltva:new Double(14.12),trans_totalttc:new Double(14.12),trans_isvalid:new Double(14.12),partenaire:testInstances9[0],ligneProduits:testInstanceslp)]
        //mockForConstraintsTests(DroitUtilisateur, testInstances)
        mockDomain(Devis, testInstancesd)
        assertTrue testInstancesd[0].validate()
        testInstancesd[0].save(flush:true)
        
        def testInstances = [new BonCommande(numBC : "bc001", type : "VENTE", reference : "5561",date:sqlDate1,trans_totalht:new Double(14.12),trans_totaltva:new Double(14.12),trans_totalttc:new Double(14.12),totalttcRetour:new Double(0d),livree:new Boolean(false),paye:new Boolean(false),retourPaye:new Boolean(true),facture:new Boolean(false),bonPret:new Boolean(true),retournee:new Boolean(false),avecRetour:new Boolean(false),trans_livree:new Boolean(true),trans_retournee:new Boolean(true),partenaire:testInstances9p[0],utilisateur:testInstancesu[0],deviss:testInstancesd,ligneProduits:testInstanceslp)]
        //mockForConstraintsTests(DroitUtilisateur, testInstances)
        mockDomain(BonCommande, testInstances)
        def testInstancesbd = [new BonLivraison(numBL : "XYZ", type : "ACHAT", reference : "5561",date:sqlDate1,trans_totalht:new Double(15.02),trans_totaltva:new Double(15.02),trans_totalttc:new Double(15.02),estBonRetour:new Boolean(true),estLivre:new Boolean(true),bonCommande:testInstances[0],ligneProduits:testInstanceslp)]
        //mockForConstraintsTests(DroitUtilisateur, testInstances)
        mockDomain(BonLivraison, testInstancesbd)
        def testService = new BonLivraisonService()
        def testInstances1 = [new PaternCompteur(libelle : "Pattern bon commande", prefixe : "BC{annee}{mois}{jour}", suffixe : "",pas:1,remplissage:12,numeroSuivant:2290,type:"BON_RETOUR")]
        //mockForConstraintsTests(DroitUtilisateur, testInstances)
        mockDomain(PaternCompteur, testInstances1)
       def testId = "bc515151"
        System.out.println("nb bb "+testInstances[0].bonPret)
        
        def testInstancesm = [new ModeLivraison(code : "0032", transporteur : "stcr", paiementALaReception : new Boolean(true))]
        //mockForConstraintsTests(DroitUtilisateur, testInstances)
        mockDomain(ModeLivraison, testInstancesm)
        assertTrue testInstancesm[0].validate()
        testInstancesm[0].save(flush:true)
        def testInstancesli = [new Livraison(numExpedition : "l1555525",nbrColis  : new Integer(0), poidTotal : new Double(0d),frais:new Double(0d),date:sqlDate1,detail:"livraison a domicile",intituleVehicule:"hvjfh564564",immatriculation:"vgvg54654645",modeLivraison:testInstancesm[0],partenaire:testInstances9p[0],bonLivraisons:testInstancesbd)]
        //mockForConstraintsTests(DroitUtilisateur, testInstances)
        mockDomain(Livraison, testInstancesli)
        
        
        
        
        
        
//def paternCompteurControl = mockFor(PaternCompteurService)
//paternCompteurControl.demand.getProchainNumBrEtIncrementer(1..1) {it -> return  testId}
//testService.paternCompteurService = paternCompteurControl.createMock()
def t=testInstances[0]
System.out.println("hjvhjv "+t)
def myCriteria = [
    list : {Closure  cls -> return testInstancesbd}
]
BonLivraison.metaClass.static.createCriteria = { myCriteria }
//def ff = testService.updateEtatBonCommandeLorsRetour(testInstances[0])      
        
def numexpe= "l1555525"       
        def livraisonServiceControl = mockFor(LivraisonService)
livraisonServiceControl.demand.supprimerSiLieeAvecUnSeulBl(numexpe) {return testInstancesli[0] }
testService.livraisonService = livraisonServiceControl.createMock()

        def bonCommandeServiceControl = mockFor(BonCommandeService)
bonCommandeServiceControl.demand.update(testInstances[0]) {return testInstances[0] }
testService.bonCommandeService = bonCommandeServiceControl.createMock()

        def paiementServiceControl = mockFor(PaiementService)
paiementServiceControl.demand.updateEtatPaiementBonCommandeAprsRetour(testInstances[0]) {return  testInstances[0]}
testService.paiementService = paiementServiceControl.createMock()
def retval1 = testService.updateEtatBonCommandeLorsRetour(testInstances[0])   
       assertTrue retval1 instanceof BonCommande
  System.out.println("la fin du tests de delete"+retval1)  
        
    }
    
    void testDeleteBonsLivraisonsZambies() {
        
        
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
        
        
        
       def testInstances9 = [new Partenaire(code : "005", raisonSociale : "celibataire", idFiscal : null,patente:"15165165",registreCommerce:"commerce",cnss:"fk13252152",adresse:"oulfa",codePostal:"20200",telephone:"0673327392",fax:"0632972654",email:"contact@choranet.com",siteWeb:"www.choranet.com",adresseFacturation:"hay el hana",adresseLivraison:"ain sebaii",estClient:new Boolean(true),estFournisseur:new Boolean(false),estParticulier:new Boolean(false),estBlacklist:new Boolean(false),plafond:new Double(12.12),modeRelancePaiement:testInstances2m[0],modeReglementDefaut:testInstances3m[0],echeance:testInstances4e[0],ville:testInstances6v[0],formeJuridique:testInstances7f[0],modeLivraison:testInstances8m[0],categoriePartenaires:testInstances1c,solde:new Double(15.01),compteVerrouile:new Boolean(true),zone:testInstancesz[0],commercial:testInstancesee[0])]        //mockForConstraintsTests(DroitUtilisateur, testInstances)
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
       
        def utilisateur2 = new Utilisateur()
        assertFalse utilisateur2.validate()
        assertEquals "nullable", utilisateur2.errors["username"]
        assertEquals "nullable", utilisateur2.errors["userRealName"]
        assertEquals "nullable", utilisateur2.errors["passwd"]
        assertEquals "nullable", utilisateur2.errors["groupe"]
        assertEquals "nullable", utilisateur2.errors["societe"]
       
        def utilisateur3= new Utilisateur(username : "XYZ", userRealName : "UTILISATEUR_XYZ",passwd :"allali",enabled:true,groupe:testInstances1g[0],societe:testInstances3[0] )
        assertFalse utilisateur3.validate()
        assertEquals "unique", utilisateur3.errors["username"]
        
        def utilisateur4= new Utilisateur(username : "admin1", userRealName : "Super Utilisateur",passwd :"NirfFVL6jts=",email:"contact@choranet.com",enabled:true,groupe:testInstances1g[0],societe:testInstances3[0])
        def f=utilisateur4.validate()
        def grooupe1=new GroupeUtilisateur(intitule:"ddd",description:"decision",droits:null)
        assertTrue testInstances1g[0].validate()
        testInstances1g[0].save(flush:true)
        
        assertTrue testInstances2[0].validate()
        testInstances2[0].save(flush:true)
        
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
        
        
        def testInstances9p = [new Partenaire(code : "005", raisonSociale : "celibataire", idFiscal : null,patente:"15165165",registreCommerce:"commerce",cnss:"fk13252152",adresse:"oulfa",codePostal:"20200",telephone:"0673327392",fax:"0632972654",email:"contact@choranet.com",siteWeb:"www.choranet.com",adresseFacturation:"hay el hana",adresseLivraison:"ain sebaii",estClient:new Boolean(true),estFournisseur:new Boolean(false),estParticulier:new Boolean(false),estBlacklist:new Boolean(false),plafond:new Double(12.12),modeRelancePaiement:testInstances2m[0],modeReglementDefaut:testInstances3m[0],echeance:testInstances4e[0],ville:testInstances6v[0],formeJuridique:testInstances7f[0],modeLivraison:testInstances8m[0],categoriePartenaires:testInstances1c,solde:new Double(15.01),compteVerrouile:new Boolean(true),zone:testInstancesz[0],commercial:testInstancesee[0])]
        mockDomain(Partenaire, testInstances9p)
        assertTrue testInstances9p[0].validate()
        testInstances9p[0].save(flush:true)
        def testInstances10 = [new Entrepot(code: "006", intitule : "ain sebaa", adresse : "rue 26 n°51",tel:"0673327392",modeValorisation:"PRIX_ACHAT",mode:"FIFO")]
        //mockForConstraintsTests(DroitUtilisateur, testInstances)
        mockDomain(Entrepot, testInstances10)
        assertTrue testInstances10[0].validate()
        testInstances10[0].save(flush:true)
         def testInstances11 = [new Nature(libelle : "normale")]
        //mockForConstraintsTests(DroitUtilisateur, testInstances)
        mockDomain(Nature, testInstances11)
        assertTrue testInstances11[0].validate()
        testInstances11[0].save(flush:true)
        def testInstances12 = [new CategorieProduit(code : "21211515", intitule : "alimentaire", categorieParente : null)]
        //mockForConstraintsTests(DroitUtilisateur, testInstances)
        mockDomain(CategorieProduit, testInstances12)
        assertTrue testInstances12[0].validate()
        testInstances12[0].save(flush:true)
        def testInstances13 = [new UniteMesure(libelle : "metre")]
        //mockForConstraintsTests(DroitUtilisateur, testInstances)
        mockDomain(UniteMesure, testInstances13)
        assertTrue testInstances13[0].validate()
        testInstances13[0].save(flush:true)
        def j=new Integer(20)
         def j1=new Integer(25)
        def testInstances14 = [new RegimeTVA(libelle : "20%", taux : j)]
        //mockForConstraintsTests(DroitUtilisateur, testInstances)
        mockDomain(RegimeTVA, testInstances14)
        assertTrue testInstances14[0].validate()
        testInstances14[0].save(flush:true)
        
        
        
        
        
        def testInstances20 = [new Produit(code : "0151513251", codebarre : "xxl01222",profil:"alimentaire",designation:"marjane",prixRevient:new Double(14.12),prixVenteStandard:new Double(14.12),poids:new Double(14.12),prixAchat:new Double(14.12),prixMoyenPendere:new Double(14.12),perissable:new Boolean(false),temperature_conservation:new Double(52.12),imageProd:byte1,trans_imageProd:i,empReception:testInstances10[0],nature:testInstances11[0],categorieProduit:testInstances12[0],uniteMesure:testInstances13[0],regimeTVA:testInstances14[0],produitParent:null,fournisseurPref:testInstances9[0])]
        //mockForConstraintsTests(DroitUtilisateur, testInstances)
        mockDomain(Produit, testInstances20)
        assertTrue testInstances20[0].validate()
        testInstances20[0].save(flush:true)
        def testInstanceslp = [new LigneProduit(sequenceLigne : new Integer(02), quantite : new Double(1),quantiteLivree  :new Double(0d),quantiteRetournee:new Double(0d),remise:new Double(0d),prix:new Double(15.45),prixDeduit:new Double(15.45),type:"ACHAT",trans_sousTotal:new Double(15.45),trans_sousTotalLivree:new Double(15.45),trans_sousTotalRetourne:new Double(15.45),trans_livree:new Boolean(true),trans_retournee:new Boolean(true),trans_ql:new Double(15.45),trans_qr:new Double(15.45),date_preremption:sqlDate1,produit:testInstances20[0],entrepot:testInstancese[0],bonCommande:null,bonLivraison:null,bonPret:null,devis:null)]
        //mockForConstraintsTests(DroitUtilisateur, testInstances)
        mockDomain(LigneProduit, testInstanceslp)
        assertTrue testInstanceslp[0].validate()
        testInstanceslp[0].save(flush:true)
        
        def testInstancesd = [new Devis(numDevis : "dh", type : "ACHAT", date : sqlDate1,dateFinValidite:sqlDate1,trans_totalht:new Double(14.12),trans_totaltva:new Double(14.12),trans_totalttc:new Double(14.12),trans_isvalid:new Double(14.12),partenaire:testInstances9[0],ligneProduits:testInstanceslp)]
        //mockForConstraintsTests(DroitUtilisateur, testInstances)
        mockDomain(Devis, testInstancesd)
        assertTrue testInstancesd[0].validate()
        testInstancesd[0].save(flush:true)
        
        def testInstances = [new BonCommande(numBC : "bc001", type : "VENTE", reference : "5561",date:sqlDate1,trans_totalht:new Double(14.12),trans_totaltva:new Double(14.12),trans_totalttc:new Double(14.12),totalttcRetour:new Double(0d),livree:new Boolean(false),paye:new Boolean(false),retourPaye:new Boolean(true),facture:new Boolean(false),bonPret:new Boolean(true),retournee:new Boolean(false),avecRetour:new Boolean(false),trans_livree:new Boolean(true),trans_retournee:new Boolean(true),partenaire:testInstances9p[0],utilisateur:testInstancesu[0],deviss:testInstancesd,ligneProduits:testInstanceslp)]
        //mockForConstraintsTests(DroitUtilisateur, testInstances)
        mockDomain(BonCommande, testInstances)
        def testInstancesbd = [new BonLivraison(numBL : "XYZ", type : "ACHAT", reference : "5561",date:sqlDate1,trans_totalht:new Double(15.02),trans_totaltva:new Double(15.02),trans_totalttc:new Double(15.02),estBonRetour:new Boolean(true),estLivre:new Boolean(true),bonCommande:testInstances[0],ligneProduits:testInstanceslp,livraison:null)]
        //mockForConstraintsTests(DroitUtilisateur, testInstances)
        mockDomain(BonLivraison, testInstancesbd)
        def testService = new BonLivraisonService()
        def testInstances1 = [new PaternCompteur(libelle : "Pattern bon commande", prefixe : "BC{annee}{mois}{jour}", suffixe : "",pas:1,remplissage:12,numeroSuivant:2290,type:"BON_RETOUR")]
        //mockForConstraintsTests(DroitUtilisateur, testInstances)
        mockDomain(PaternCompteur, testInstances1)
       def testId = "bc515151"
        System.out.println("nb bb "+testInstances[0].bonPret)
        
        def testInstancesm = [new ModeLivraison(code : "0032", transporteur : "stcr", paiementALaReception : new Boolean(true))]
        //mockForConstraintsTests(DroitUtilisateur, testInstances)
        mockDomain(ModeLivraison, testInstancesm)
        assertTrue testInstancesm[0].validate()
        testInstancesm[0].save(flush:true)
        def testInstancesli = [new Livraison(numExpedition : "l1555525",nbrColis  : new Integer(0), poidTotal : new Double(0d),frais:new Double(0d),date:sqlDate1,detail:"livraison a domicile",intituleVehicule:"hvjfh564564",immatriculation:"vgvg54654645",modeLivraison:testInstancesm[0],partenaire:testInstances9p[0],bonLivraisons:testInstancesbd)]
        //mockForConstraintsTests(DroitUtilisateur, testInstances)
        mockDomain(Livraison, testInstancesli)
def myCriteria = [
    list : {Closure  cls -> return testInstancesbd}
]
BonLivraison.metaClass.static.createCriteria = { myCriteria }

        def myCriteria1 = [
    list : {Closure  cls -> return testInstancesli[0]}
]
Livraison.metaClass.static.createCriteria = { myCriteria1 }        
        
def numexpe= "l1555525"       
        def livraisonServiceControl = mockFor(LivraisonService)
livraisonServiceControl.demand.supprimerSiLieeAvecUnSeulBl(numexpe) {return testInstancesli[0] }
testService.livraisonService = livraisonServiceControl.createMock()

        def bonCommandeServiceControl = mockFor(BonCommandeService)
bonCommandeServiceControl.demand.update(testInstances[0]) {return testInstances[0] }
testService.bonCommandeService = bonCommandeServiceControl.createMock()

        def paiementServiceControl = mockFor(PaiementService)
paiementServiceControl.demand.updateEtatPaiementBonCommandeAprsRetour(testInstances[0]) {return  testInstances[0]}
testService.paiementService = paiementServiceControl.createMock()

def retval1 = testService.deleteBonsLivraisonsZambies(testInstancesbd[0])   
       //assertTrue retval1 instanceof BonCommande
  System.out.println("la fin du tests de deleteBonsLivraisonsZambies"+retval1)  
        
        
    }
    
    
    void testGetLpsBonLivraisonEnDetails() {
        
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
        
        
        
       def testInstances9 = [new Partenaire(code : "005", raisonSociale : "celibataire", idFiscal : null,patente:"15165165",registreCommerce:"commerce",cnss:"fk13252152",adresse:"oulfa",codePostal:"20200",telephone:"0673327392",fax:"0632972654",email:"contact@choranet.com",siteWeb:"www.choranet.com",adresseFacturation:"hay el hana",adresseLivraison:"ain sebaii",estClient:new Boolean(true),estFournisseur:new Boolean(false),estParticulier:new Boolean(false),estBlacklist:new Boolean(false),plafond:new Double(12.12),modeRelancePaiement:testInstances2m[0],modeReglementDefaut:testInstances3m[0],echeance:testInstances4e[0],ville:testInstances6v[0],formeJuridique:testInstances7f[0],modeLivraison:testInstances8m[0],categoriePartenaires:testInstances1c,solde:new Double(15.01),compteVerrouile:new Boolean(true),zone:testInstancesz[0],commercial:testInstancesee[0])]        //mockForConstraintsTests(DroitUtilisateur, testInstances)
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
       
        def utilisateur2 = new Utilisateur()
        assertFalse utilisateur2.validate()
        assertEquals "nullable", utilisateur2.errors["username"]
        assertEquals "nullable", utilisateur2.errors["userRealName"]
        assertEquals "nullable", utilisateur2.errors["passwd"]
        assertEquals "nullable", utilisateur2.errors["groupe"]
        assertEquals "nullable", utilisateur2.errors["societe"]
       
        def utilisateur3= new Utilisateur(username : "XYZ", userRealName : "UTILISATEUR_XYZ",passwd :"allali",enabled:true,groupe:testInstances1g[0],societe:testInstances3[0] )
        assertFalse utilisateur3.validate()
        assertEquals "unique", utilisateur3.errors["username"]
        
        def utilisateur4= new Utilisateur(username : "admin1", userRealName : "Super Utilisateur",passwd :"NirfFVL6jts=",email:"contact@choranet.com",enabled:true,groupe:testInstances1g[0],societe:testInstances3[0])
        def f=utilisateur4.validate()
        def grooupe1=new GroupeUtilisateur(intitule:"ddd",description:"decision",droits:null)
        assertTrue testInstances1g[0].validate()
        testInstances1g[0].save(flush:true)
        
        assertTrue testInstances2[0].validate()
        testInstances2[0].save(flush:true)
        
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
        
        
        def testInstances9p = [new Partenaire(code : "005", raisonSociale : "celibataire", idFiscal : null,patente:"15165165",registreCommerce:"commerce",cnss:"fk13252152",adresse:"oulfa",codePostal:"20200",telephone:"0673327392",fax:"0632972654",email:"contact@choranet.com",siteWeb:"www.choranet.com",adresseFacturation:"hay el hana",adresseLivraison:"ain sebaii",estClient:new Boolean(true),estFournisseur:new Boolean(false),estParticulier:new Boolean(false),estBlacklist:new Boolean(false),plafond:new Double(12.12),modeRelancePaiement:testInstances2m[0],modeReglementDefaut:testInstances3m[0],echeance:testInstances4e[0],ville:testInstances6v[0],formeJuridique:testInstances7f[0],modeLivraison:testInstances8m[0],categoriePartenaires:testInstances1c,solde:new Double(15.01),compteVerrouile:new Boolean(true),zone:testInstancesz[0],commercial:testInstancesee[0])]
        mockDomain(Partenaire, testInstances9p)
        assertTrue testInstances9p[0].validate()
        testInstances9p[0].save(flush:true)
        def testInstances10 = [new Entrepot(code: "006", intitule : "ain sebaa", adresse : "rue 26 n°51",tel:"0673327392",modeValorisation:"PRIX_ACHAT",mode:"FIFO")]
        //mockForConstraintsTests(DroitUtilisateur, testInstances)
        mockDomain(Entrepot, testInstances10)
        assertTrue testInstances10[0].validate()
        testInstances10[0].save(flush:true)
         def testInstances11 = [new Nature(libelle : "normale")]
        //mockForConstraintsTests(DroitUtilisateur, testInstances)
        mockDomain(Nature, testInstances11)
        assertTrue testInstances11[0].validate()
        testInstances11[0].save(flush:true)
        def testInstances12 = [new CategorieProduit(code : "21211515", intitule : "alimentaire", categorieParente : null)]
        //mockForConstraintsTests(DroitUtilisateur, testInstances)
        mockDomain(CategorieProduit, testInstances12)
        assertTrue testInstances12[0].validate()
        testInstances12[0].save(flush:true)
        def testInstances13 = [new UniteMesure(libelle : "metre")]
        //mockForConstraintsTests(DroitUtilisateur, testInstances)
        mockDomain(UniteMesure, testInstances13)
        assertTrue testInstances13[0].validate()
        testInstances13[0].save(flush:true)
        def j=new Integer(20)
         def j1=new Integer(25)
        def testInstances14 = [new RegimeTVA(libelle : "20%", taux : j)]
        //mockForConstraintsTests(DroitUtilisateur, testInstances)
        mockDomain(RegimeTVA, testInstances14)
        assertTrue testInstances14[0].validate()
        testInstances14[0].save(flush:true)
        
        
        
        
        
        def testInstances20 = [new Produit(code : "0151513251", codebarre : "xxl01222",profil:"alimentaire",designation:"marjane",prixRevient:new Double(14.12),prixVenteStandard:new Double(14.12),poids:new Double(14.12),prixAchat:new Double(14.12),prixMoyenPendere:new Double(14.12),perissable:new Boolean(false),temperature_conservation:new Double(52.12),imageProd:byte1,trans_imageProd:i,empReception:testInstances10[0],nature:testInstances11[0],categorieProduit:testInstances12[0],uniteMesure:testInstances13[0],regimeTVA:testInstances14[0],produitParent:null,fournisseurPref:testInstances9[0])]
        //mockForConstraintsTests(DroitUtilisateur, testInstances)
        mockDomain(Produit, testInstances20)
        assertTrue testInstances20[0].validate()
        testInstances20[0].save(flush:true)
        def testInstanceslp = [new LigneProduit(sequenceLigne : new Integer(02), quantite : new Double(1),quantiteLivree  :new Double(0d),quantiteRetournee:new Double(0d),remise:new Double(0d),prix:new Double(15.45),prixDeduit:new Double(15.45),type:"ACHAT",trans_sousTotal:new Double(15.45),trans_sousTotalLivree:new Double(15.45),trans_sousTotalRetourne:new Double(15.45),trans_livree:new Boolean(true),trans_retournee:new Boolean(true),trans_ql:new Double(15.45),trans_qr:new Double(15.45),date_preremption:sqlDate1,produit:testInstances20[0],entrepot:testInstancese[0],bonCommande:null,bonLivraison:null,bonPret:null,devis:null)]
        //mockForConstraintsTests(DroitUtilisateur, testInstances)
        mockDomain(LigneProduit, testInstanceslp)
        assertTrue testInstanceslp[0].validate()
        testInstanceslp[0].save(flush:true)
        
        def testInstancesd = [new Devis(numDevis : "dh", type : "ACHAT", date : sqlDate1,dateFinValidite:sqlDate1,trans_totalht:new Double(14.12),trans_totaltva:new Double(14.12),trans_totalttc:new Double(14.12),trans_isvalid:new Double(14.12),partenaire:testInstances9[0],ligneProduits:testInstanceslp)]
        //mockForConstraintsTests(DroitUtilisateur, testInstances)
        mockDomain(Devis, testInstancesd)
        assertTrue testInstancesd[0].validate()
        testInstancesd[0].save(flush:true)
        
        def testInstances = [new BonCommande(numBC : "bc001", type : "VENTE", reference : "5561",date:sqlDate1,trans_totalht:new Double(14.12),trans_totaltva:new Double(14.12),trans_totalttc:new Double(14.12),totalttcRetour:new Double(0d),livree:new Boolean(false),paye:new Boolean(false),retourPaye:new Boolean(true),facture:new Boolean(false),bonPret:new Boolean(true),retournee:new Boolean(false),avecRetour:new Boolean(false),trans_livree:new Boolean(true),trans_retournee:new Boolean(true),partenaire:testInstances9p[0],utilisateur:testInstancesu[0],deviss:testInstancesd,ligneProduits:testInstanceslp)]
        //mockForConstraintsTests(DroitUtilisateur, testInstances)
        mockDomain(BonCommande, testInstances)
        def testInstancesbd = [new BonLivraison(numBL : "XYZ", type : "ACHAT", reference : "5561",date:sqlDate1,trans_totalht:new Double(15.02),trans_totaltva:new Double(15.02),trans_totalttc:new Double(15.02),estBonRetour:new Boolean(true),estLivre:new Boolean(true),bonCommande:testInstances[0],ligneProduits:testInstanceslp,livraison:null)]
        //mockForConstraintsTests(DroitUtilisateur, testInstances)
        mockDomain(BonLivraison, testInstancesbd)
        def testService = new BonLivraisonService()
        def testInstances1 = [new PaternCompteur(libelle : "Pattern bon commande", prefixe : "BC{annee}{mois}{jour}", suffixe : "",pas:1,remplissage:12,numeroSuivant:2290,type:"BON_RETOUR")]
        //mockForConstraintsTests(DroitUtilisateur, testInstances)
        mockDomain(PaternCompteur, testInstances1)
       def testId = "bc515151"
        System.out.println("nb bb "+testInstances[0].bonPret)
        
        def testInstancesm = [new ModeLivraison(code : "0032", transporteur : "stcr", paiementALaReception : new Boolean(true))]
        //mockForConstraintsTests(DroitUtilisateur, testInstances)
        mockDomain(ModeLivraison, testInstancesm)
        assertTrue testInstancesm[0].validate()
        testInstancesm[0].save(flush:true)
        def testInstancesli = [new Livraison(numExpedition : "l1555525",nbrColis  : new Integer(0), poidTotal : new Double(0d),frais:new Double(0d),date:sqlDate1,detail:"livraison a domicile",intituleVehicule:"hvjfh564564",immatriculation:"vgvg54654645",modeLivraison:testInstancesm[0],partenaire:testInstances9p[0],bonLivraisons:testInstancesbd)]
        //mockForConstraintsTests(DroitUtilisateur, testInstances)
        mockDomain(Livraison, testInstancesli)
        def myCriteria = [
    list : {Closure  cls -> return testInstancesbd}
]
BonLivraison.metaClass.static.createCriteria = { myCriteria }
        def retval1 = testService.bonlivraisonslivraison(testInstancesli[0])   
       //assertTrue retval1 instanceof BonCommande
  System.out.println("la fin du tests de bonlivraisonslivraison"+retval1) 
        
    }
    
   void testBonlivraisonslivraison() {
        
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
        
        
        
       def testInstances9 = [new Partenaire(code : "005", raisonSociale : "celibataire", idFiscal : null,patente:"15165165",registreCommerce:"commerce",cnss:"fk13252152",adresse:"oulfa",codePostal:"20200",telephone:"0673327392",fax:"0632972654",email:"contact@choranet.com",siteWeb:"www.choranet.com",adresseFacturation:"hay el hana",adresseLivraison:"ain sebaii",estClient:new Boolean(true),estFournisseur:new Boolean(false),estParticulier:new Boolean(false),estBlacklist:new Boolean(false),plafond:new Double(12.12),modeRelancePaiement:testInstances2m[0],modeReglementDefaut:testInstances3m[0],echeance:testInstances4e[0],ville:testInstances6v[0],formeJuridique:testInstances7f[0],modeLivraison:testInstances8m[0],categoriePartenaires:testInstances1c,solde:new Double(15.01),compteVerrouile:new Boolean(true),zone:testInstancesz[0],commercial:testInstancesee[0])]        //mockForConstraintsTests(DroitUtilisateur, testInstances)
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
       
        def utilisateur2 = new Utilisateur()
        assertFalse utilisateur2.validate()
        assertEquals "nullable", utilisateur2.errors["username"]
        assertEquals "nullable", utilisateur2.errors["userRealName"]
        assertEquals "nullable", utilisateur2.errors["passwd"]
        assertEquals "nullable", utilisateur2.errors["groupe"]
        assertEquals "nullable", utilisateur2.errors["societe"]
       
        def utilisateur3= new Utilisateur(username : "XYZ", userRealName : "UTILISATEUR_XYZ",passwd :"allali",enabled:true,groupe:testInstances1g[0],societe:testInstances3[0] )
        assertFalse utilisateur3.validate()
        assertEquals "unique", utilisateur3.errors["username"]
        
        def utilisateur4= new Utilisateur(username : "admin1", userRealName : "Super Utilisateur",passwd :"NirfFVL6jts=",email:"contact@choranet.com",enabled:true,groupe:testInstances1g[0],societe:testInstances3[0])
        def f=utilisateur4.validate()
        def grooupe1=new GroupeUtilisateur(intitule:"ddd",description:"decision",droits:null)
        assertTrue testInstances1g[0].validate()
        testInstances1g[0].save(flush:true)
        
        assertTrue testInstances2[0].validate()
        testInstances2[0].save(flush:true)
        
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
        
        
        def testInstances9p = [new Partenaire(code : "005", raisonSociale : "celibataire", idFiscal : null,patente:"15165165",registreCommerce:"commerce",cnss:"fk13252152",adresse:"oulfa",codePostal:"20200",telephone:"0673327392",fax:"0632972654",email:"contact@choranet.com",siteWeb:"www.choranet.com",adresseFacturation:"hay el hana",adresseLivraison:"ain sebaii",estClient:new Boolean(true),estFournisseur:new Boolean(false),estParticulier:new Boolean(false),estBlacklist:new Boolean(false),plafond:new Double(12.12),modeRelancePaiement:testInstances2m[0],modeReglementDefaut:testInstances3m[0],echeance:testInstances4e[0],ville:testInstances6v[0],formeJuridique:testInstances7f[0],modeLivraison:testInstances8m[0],categoriePartenaires:testInstances1c,solde:new Double(15.01),compteVerrouile:new Boolean(true),zone:testInstancesz[0],commercial:testInstancesee[0])]
        mockDomain(Partenaire, testInstances9p)
        assertTrue testInstances9p[0].validate()
        testInstances9p[0].save(flush:true)
        def testInstances10 = [new Entrepot(code: "006", intitule : "ain sebaa", adresse : "rue 26 n°51",tel:"0673327392",modeValorisation:"PRIX_ACHAT",mode:"FIFO")]
        //mockForConstraintsTests(DroitUtilisateur, testInstances)
        mockDomain(Entrepot, testInstances10)
        assertTrue testInstances10[0].validate()
        testInstances10[0].save(flush:true)
         def testInstances11 = [new Nature(libelle : "normale")]
        //mockForConstraintsTests(DroitUtilisateur, testInstances)
        mockDomain(Nature, testInstances11)
        assertTrue testInstances11[0].validate()
        testInstances11[0].save(flush:true)
        def testInstances12 = [new CategorieProduit(code : "21211515", intitule : "alimentaire", categorieParente : null)]
        //mockForConstraintsTests(DroitUtilisateur, testInstances)
        mockDomain(CategorieProduit, testInstances12)
        assertTrue testInstances12[0].validate()
        testInstances12[0].save(flush:true)
        def testInstances13 = [new UniteMesure(libelle : "metre")]
        //mockForConstraintsTests(DroitUtilisateur, testInstances)
        mockDomain(UniteMesure, testInstances13)
        assertTrue testInstances13[0].validate()
        testInstances13[0].save(flush:true)
        def j=new Integer(20)
         def j1=new Integer(25)
        def testInstances14 = [new RegimeTVA(libelle : "20%", taux : j)]
        //mockForConstraintsTests(DroitUtilisateur, testInstances)
        mockDomain(RegimeTVA, testInstances14)
        assertTrue testInstances14[0].validate()
        testInstances14[0].save(flush:true)
        
        
        
        
        
        def testInstances20 = [new Produit(code : "0151513251", codebarre : "xxl01222",profil:"alimentaire",designation:"marjane",prixRevient:new Double(14.12),prixVenteStandard:new Double(14.12),poids:new Double(14.12),prixAchat:new Double(14.12),prixMoyenPendere:new Double(14.12),perissable:new Boolean(false),temperature_conservation:new Double(52.12),imageProd:byte1,trans_imageProd:i,empReception:testInstances10[0],nature:testInstances11[0],categorieProduit:testInstances12[0],uniteMesure:testInstances13[0],regimeTVA:testInstances14[0],produitParent:null,fournisseurPref:testInstances9[0])]
        //mockForConstraintsTests(DroitUtilisateur, testInstances)
        mockDomain(Produit, testInstances20)
        assertTrue testInstances20[0].validate()
        testInstances20[0].save(flush:true)
        def testInstanceslp = [new LigneProduit(sequenceLigne : new Integer(02), quantite : new Double(1),quantiteLivree  :new Double(0d),quantiteRetournee:new Double(0d),remise:new Double(0d),prix:new Double(15.45),prixDeduit:new Double(15.45),type:"ACHAT",trans_sousTotal:new Double(15.45),trans_sousTotalLivree:new Double(15.45),trans_sousTotalRetourne:new Double(15.45),trans_livree:new Boolean(true),trans_retournee:new Boolean(true),trans_ql:new Double(15.45),trans_qr:new Double(15.45),date_preremption:sqlDate1,produit:testInstances20[0],entrepot:testInstancese[0],bonCommande:null,bonLivraison:null,bonPret:null,devis:null)]
        //mockForConstraintsTests(DroitUtilisateur, testInstances)
        mockDomain(LigneProduit, testInstanceslp)
        assertTrue testInstanceslp[0].validate()
        testInstanceslp[0].save(flush:true)
        
        def testInstancesd = [new Devis(numDevis : "dh", type : "ACHAT", date : sqlDate1,dateFinValidite:sqlDate1,trans_totalht:new Double(14.12),trans_totaltva:new Double(14.12),trans_totalttc:new Double(14.12),trans_isvalid:new Double(14.12),partenaire:testInstances9[0],ligneProduits:testInstanceslp)]
        //mockForConstraintsTests(DroitUtilisateur, testInstances)
        mockDomain(Devis, testInstancesd)
        assertTrue testInstancesd[0].validate()
        testInstancesd[0].save(flush:true)
        
        def testInstances = [new BonCommande(numBC : "bc001", type : "VENTE", reference : "5561",date:sqlDate1,trans_totalht:new Double(14.12),trans_totaltva:new Double(14.12),trans_totalttc:new Double(14.12),totalttcRetour:new Double(0d),livree:new Boolean(false),paye:new Boolean(false),retourPaye:new Boolean(true),facture:new Boolean(false),bonPret:new Boolean(true),retournee:new Boolean(false),avecRetour:new Boolean(false),trans_livree:new Boolean(true),trans_retournee:new Boolean(true),partenaire:testInstances9p[0],utilisateur:testInstancesu[0],deviss:testInstancesd,ligneProduits:testInstanceslp)]
        //mockForConstraintsTests(DroitUtilisateur, testInstances)
        mockDomain(BonCommande, testInstances)
        def testInstancesbd = [new BonLivraison(numBL : "XYZ", type : "ACHAT", reference : "5561",date:sqlDate1,trans_totalht:new Double(15.02),trans_totaltva:new Double(15.02),trans_totalttc:new Double(15.02),estBonRetour:new Boolean(true),estLivre:new Boolean(true),bonCommande:testInstances[0],ligneProduits:testInstanceslp,livraison:null)]
        //mockForConstraintsTests(DroitUtilisateur, testInstances)
        mockDomain(BonLivraison, testInstancesbd)
        def testService = new BonLivraisonService()
        def testInstances1 = [new PaternCompteur(libelle : "Pattern bon commande", prefixe : "BC{annee}{mois}{jour}", suffixe : "",pas:1,remplissage:12,numeroSuivant:2290,type:"BON_RETOUR")]
        //mockForConstraintsTests(DroitUtilisateur, testInstances)
        mockDomain(PaternCompteur, testInstances1)
       def testId = "bc515151"
        System.out.println("nb bb "+testInstances[0].bonPret)
        
        def testService1 = new LivraisonService()
        
        def testInstancesm = [new ModeLivraison(code : "0032", transporteur : "stcr", paiementALaReception : new Boolean(true))]
        //mockForConstraintsTests(DroitUtilisateur, testInstances)
        mockDomain(ModeLivraison, testInstancesm)
        assertTrue testInstancesm[0].validate()
        testInstancesm[0].save(flush:true)
        def testInstancesli = [new Livraison(numExpedition : "l1555525",nbrColis  : new Integer(0), poidTotal : new Double(0d),frais:new Double(0d),date:sqlDate1,detail:"livraison a domicile",intituleVehicule:"hvjfh564564",immatriculation:"vgvg54654645",modeLivraison:testInstancesm[0],partenaire:testInstances9p[0],bonLivraisons:testInstancesbd)]
        //mockForConstraintsTests(DroitUtilisateur, testInstances)
        mockDomain(Livraison, testInstancesli)
       
        
        def livraisonServiceControl = mockFor(LivraisonService)
livraisonServiceControl.demand.getLpsBLEnDetails(testInstanceslp) {return testInstanceslp }
testService.livraisonService = livraisonServiceControl.createMock()

        def mouvementStockServiceControl = mockFor(MouvementStockService)
mouvementStockServiceControl.demand.getLignesProduitMvmEnDetails(testInstanceslp) {return testInstanceslp }
testService1.mouvementStockService = mouvementStockServiceControl.createMock()

       
        
        
        def retval1 = testService.getLpsBonLivraisonEnDetails(testInstanceslp)   
       //assertTrue retval1 instanceof BonCommande
       System.out.println("la fin du tests de testGetLpsBonLivraisonEnDetails"+retval1[0].sequenceLigne) 
       assertTrue retval1[0] instanceof LigneProduit
       assertEquals 2,retval1[0].sequenceLigne 
       assertEquals "ACHAT",retval1[0].type
    }
    
//        void  testUpdate(){
//            
//        
//        def ex=new Integer(30032013)
//        final SimpleDateFormat sdf_yyyyMMdd = new SimpleDateFormat("yyyy-MM-dd");
//		final SimpleDateFormat sdf_ddMMyyyy = new SimpleDateFormat("dd/MM/yyyy");
// 
//		java.util.Date judDate1 = new java.util.Date();
//                java.util.Date judDate2 = new java.util.Date();
//		try {
//			judDate1 = sdf_ddMMyyyy.parse("01/10/2005");
//                        judDate2 = sdf_ddMMyyyy.parse("03/10/2005");
//		} catch (ParseException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
// 
//		java.sql.Date sqlDate1 = new java.sql.Date(judDate1.getTime());
//                java.sql.Date sqlDate2 = new java.sql.Date(judDate2.getTime());
//		
//        
//        def testInstancesz = [new Zone(code : "001", intitule : "saiis")]
//        //mockForConstraintsTests(DroitUtilisateur, testInstances)
//        mockDomain(Zone, testInstancesz)
//        assertTrue testInstancesz[0].validate()
//        testInstancesz[0].save(flush:true)
//        def gg=new Integer(12)
//        def gg1=new Integer(22)
//        def bo=new Boolean(true)
//        def testInstances111 = [new JourFerier(date : sqlDate1, intitule : "fete independance", annuel :bo,duree:gg)]
//        //mockForConstraintsTests(DroitUtilisateur, testInstances)
//        mockDomain(JourFerier, testInstances111)
//        assertTrue testInstances111[0].validate()
//        testInstances111[0].save(flush:true)
//        def testInstances222 = [new Calendrier(nbHeuresTravailParJour : gg, nom : "annee 2013", nbJoursTravailParMois :gg1,jourFeriers:testInstances111)]
//        //mockForConstraintsTests(DroitUtilisateur, testInstances)
//        mockDomain(Calendrier, testInstances222)
//        assertTrue testInstances222[0].validate()
//        testInstances222[0].save(flush:true)
//        
//        def testInstances333 = [new Fonction(libelle : "comptable")]
//        //mockForConstraintsTests(DroitUtilisateur, testInstances)
//        mockDomain(Fonction, testInstances333)
//        assertTrue testInstances333[0].validate()
//        testInstances333[0].save(flush:true)
//        
//        
//        def num=new Integer(01)
//        def th=new Double(12.01)
//        def sa=new Double(300.14)
//        def testInstancesee = [new Employe(numero : num, nom : "allali", prenom : "youssef",telephone:"0673327392",tauxHoraire:th,salaire:sa,calendrier:testInstances222[0],fonction:testInstances333[0])]
//        //mockForConstraintsTests(DroitUtilisateur, testInstances)
//        mockDomain(Employe, testInstancesee)
//        assertTrue testInstancesee[0].validate()
//        testInstancesee[0].save(flush:true)
//        
//        def testInstances1c = [new CategoriePartenaire(libelle : "propr", categorieParente : null)]
//        //mockForConstraintsTests(DroitUtilisateur, testInstances)
//        mockDomain(CategoriePartenaire, testInstances1c)
//        assertTrue testInstances1c[0].validate()
//        testInstances1c[0].save(flush:true)
//        def testInstances2m = [new ModeRelancePaiement(code : "XYZ01", frequence : new Integer(10), libelle : "facture",corpsMessage:"paiment du reste de la facture")]
//        //mockForConstraintsTests(DroitUtilisateur, testInstances)
//        mockDomain(ModeRelancePaiement, testInstances2m)
//        assertTrue testInstances2m[0].validate()
//        testInstances2m[0].save(flush:true)
//        def testInstances3m = [new ModeReglement(code : "005", libelle : "espece")]
//        //mockForConstraintsTests(DroitUtilisateur, testInstances)
//        mockDomain(ModeReglement, testInstances3m)
//        assertTrue testInstances3m[0].validate()
//        testInstances3m[0].save(flush:true)
//        def testInstances4e = [new Echeance(code : new Integer(001), libelle : "date livraison", periodicite : new Integer(30),typeDeclanchement:"DATE_LIVRAISON")]
//        //mockForConstraintsTests(DroitUtilisateur, testInstances)
//        mockDomain(Echeance, testInstances4e)
//        assertTrue testInstances4e[0].validate()
//        testInstances4e[0].save(flush:true)
//        def testInstances5p = [new Pays(intitule : "maroc")]
//        //mockForConstraintsTests(DroitUtilisateur, testInstances)
//        mockDomain(Pays, testInstances5p)
//        assertTrue testInstances5p[0].validate()
//        testInstances5p[0].save(flush:true)
//        def testInstances6v = [new Ville(intitule : "AGADIR", pays:testInstances5p[0] )]
//        //mockForConstraintsTests(DroitUtilisateur, testInstances)
//        mockDomain(Ville, testInstances6v)
//        assertTrue testInstances6v[0].validate()
//        testInstances6v[0].save(flush:true)
//        def testInstances7f = [new FormeJuridique(libelle : "GIE")]
//        //mockForConstraintsTests(DroitUtilisateur, testInstances)
//        mockDomain(FormeJuridique, testInstances7f)
//        assertTrue testInstances7f[0].validate()
//        testInstances7f[0].save(flush:true)
//        def testInstances8m = [new ModeLivraison(code : "0032", transporteur : "stcr", paiementALaReception : new Boolean(true))]
//        //mockForConstraintsTests(DroitUtilisateur, testInstances)
//        mockDomain(ModeLivraison, testInstances8m)
//        assertTrue testInstances8m[0].validate()
//        testInstances8m[0].save(flush:true)
//        
//        
//        
//       def testInstances9 = [new Partenaire(code : "005", raisonSociale : "celibataire", idFiscal : null,patente:"15165165",registreCommerce:"commerce",cnss:"fk13252152",adresse:"oulfa",codePostal:"20200",telephone:"0673327392",fax:"0632972654",email:"contact@choranet.com",siteWeb:"www.choranet.com",adresseFacturation:"hay el hana",adresseLivraison:"ain sebaii",estClient:new Boolean(true),estFournisseur:new Boolean(false),estParticulier:new Boolean(false),estBlacklist:new Boolean(false),plafond:new Double(12.12),modeRelancePaiement:testInstances2m[0],modeReglementDefaut:testInstances3m[0],echeance:testInstances4e[0],ville:testInstances6v[0],formeJuridique:testInstances7f[0],modeLivraison:testInstances8m[0],categoriePartenaires:testInstances1c,solde:new Double(15.01),compteVerrouile:new Boolean(true),zone:testInstancesz[0],commercial:testInstancesee[0])]        //mockForConstraintsTests(DroitUtilisateur, testInstances)
//        mockDomain(Partenaire, testInstances9)
//        assertTrue testInstances9[0].validate()
//        testInstances9[0].save(flush:true)
//        
//        
//        Image i=Utilitaire.getLogoImage(getClass())
//        def byte1=i.getByteData()
//        def testInstances4 = [new DroitUtilisateur(droit:"ddd",description:"description",parent:null)]
//        def testInstances1g = [new GroupeUtilisateur(intitule:"ddd",description:"decision",droits:null)]
//        def testInstances2 = [new RegimeDeclarationTva(libelle:"Déclaration mensuelle à base encaissement",periodicite:"Trimestrielle",faitGenerateur:"Encaissement"),new RegimeDeclarationTva(libelle:"Déclaration trimestrielle à base encaissement",periodicite:"Trimestrielle",faitGenerateur:"Encaissement")]
//        def testInstances3 = [  new ChoraClientInfo(raisonSociale : "CHORA INDUSTRIE1", email : "contact@choranet.com", formeJuridique : "SARL",telephone:"+212 522 233 343",fax:"+212 522 233 344",patente:"78/7877",rc:"78965",idF:"45788",cnss:"21/45789",site:"http://www.choranet.com",repertoirBackup:"/home/qllqli",adresse:"11M3 Lot Alhamd Ain Sbaa",codePostale:"20250",ville:"Casablanca",pays:"Maroc",cachetData:null,entetepiedData:null,copieData:null,logoData:byte1,regime:testInstances2[0])]
//        def testInstancesu = [new Utilisateur(username : "XYZ", userRealName : "UTILISATEUR_XYZ",passwd :"allali",enabled:true,email:'contact@choranet.com',groupe:testInstances1g[0],societe:testInstances3[0] )]
//        //mockForConstraintsTests(Module, testInstances)
//        mockDomain(RegimeDeclarationTva, testInstances2)
//        mockDomain(GroupeUtilisateur, testInstances1g)
//        mockDomain(Utilisateur, testInstancesu)
//        mockDomain(ChoraClientInfo, testInstances3)
//        mockDomain(DroitUtilisateur, testInstances4)
//        assertEquals 1, Utilisateur.count()
//        assertTrue testInstancesu[0] instanceof Utilisateur 
//       
//        def utilisateur2 = new Utilisateur()
//        assertFalse utilisateur2.validate()
//        assertEquals "nullable", utilisateur2.errors["username"]
//        assertEquals "nullable", utilisateur2.errors["userRealName"]
//        assertEquals "nullable", utilisateur2.errors["passwd"]
//        assertEquals "nullable", utilisateur2.errors["groupe"]
//        assertEquals "nullable", utilisateur2.errors["societe"]
//       
//        def utilisateur3= new Utilisateur(username : "XYZ", userRealName : "UTILISATEUR_XYZ",passwd :"allali",enabled:true,groupe:testInstances1g[0],societe:testInstances3[0] )
//        assertFalse utilisateur3.validate()
//        assertEquals "unique", utilisateur3.errors["username"]
//        
//        def utilisateur4= new Utilisateur(username : "admin1", userRealName : "Super Utilisateur",passwd :"NirfFVL6jts=",email:"contact@choranet.com",enabled:true,groupe:testInstances1g[0],societe:testInstances3[0])
//        def f=utilisateur4.validate()
//        def grooupe1=new GroupeUtilisateur(intitule:"ddd",description:"decision",droits:null)
//        assertTrue testInstances1g[0].validate()
//        testInstances1g[0].save(flush:true)
//        
//        assertTrue testInstances2[0].validate()
//        testInstances2[0].save(flush:true)
//        
//        assertTrue testInstances3[0].validate()
//        testInstances3[0].save(flush:true)
//        assertTrue testInstances4[0].validate()
//        testInstances4[0].save(flush:true)
//        assertTrue testInstancesu[0].validate()
//        testInstancesu[0].save(flush:true)
//                            
//        def testInstancese = [new Entrepot(code	 : "006", intitule : "ain sebaa", adresse : "rue 26 n°51",tel:"0673327392",modeValorisation:"PRIX_ACHAT",mode:"FIFO")]
//        //mockForConstraintsTests(DroitUtilisateur, testInstances)
//        mockDomain(Entrepot, testInstancese)
//        assertTrue testInstancese[0].validate()
//        testInstancese[0].save(flush:true)
//        
//        
//        def testInstances9p = [new Partenaire(code : "005", raisonSociale : "celibataire", idFiscal : null,patente:"15165165",registreCommerce:"commerce",cnss:"fk13252152",adresse:"oulfa",codePostal:"20200",telephone:"0673327392",fax:"0632972654",email:"contact@choranet.com",siteWeb:"www.choranet.com",adresseFacturation:"hay el hana",adresseLivraison:"ain sebaii",estClient:new Boolean(true),estFournisseur:new Boolean(false),estParticulier:new Boolean(false),estBlacklist:new Boolean(false),plafond:new Double(12.12),modeRelancePaiement:testInstances2m[0],modeReglementDefaut:testInstances3m[0],echeance:testInstances4e[0],ville:testInstances6v[0],formeJuridique:testInstances7f[0],modeLivraison:testInstances8m[0],categoriePartenaires:testInstances1c,solde:new Double(15.01),compteVerrouile:new Boolean(true),zone:testInstancesz[0],commercial:testInstancesee[0])]
//        mockDomain(Partenaire, testInstances9p)
//        assertTrue testInstances9p[0].validate()
//        testInstances9p[0].save(flush:true)
//        def testInstances10 = [new Entrepot(code: "006", intitule : "ain sebaa", adresse : "rue 26 n°51",tel:"0673327392",modeValorisation:"PRIX_ACHAT",mode:"FIFO")]
//        //mockForConstraintsTests(DroitUtilisateur, testInstances)
//        mockDomain(Entrepot, testInstances10)
//        assertTrue testInstances10[0].validate()
//        testInstances10[0].save(flush:true)
//         def testInstances11 = [new Nature(libelle : "normale")]
//        //mockForConstraintsTests(DroitUtilisateur, testInstances)
//        mockDomain(Nature, testInstances11)
//        assertTrue testInstances11[0].validate()
//        testInstances11[0].save(flush:true)
//        def testInstances12 = [new CategorieProduit(code : "21211515", intitule : "alimentaire", categorieParente : null)]
//        //mockForConstraintsTests(DroitUtilisateur, testInstances)
//        mockDomain(CategorieProduit, testInstances12)
//        assertTrue testInstances12[0].validate()
//        testInstances12[0].save(flush:true)
//        def testInstances13 = [new UniteMesure(libelle : "metre")]
//        //mockForConstraintsTests(DroitUtilisateur, testInstances)
//        mockDomain(UniteMesure, testInstances13)
//        assertTrue testInstances13[0].validate()
//        testInstances13[0].save(flush:true)
//        def j=new Integer(20)
//         def j1=new Integer(25)
//        def testInstances14 = [new RegimeTVA(libelle : "20%", taux : j)]
//        //mockForConstraintsTests(DroitUtilisateur, testInstances)
//        mockDomain(RegimeTVA, testInstances14)
//        assertTrue testInstances14[0].validate()
//        testInstances14[0].save(flush:true)
//        
//        
//        
//        
//        
//        def testInstances20 = [new Produit(code : "0151513251", codebarre : "xxl01222",profil:"alimentaire",designation:"marjane",prixRevient:new Double(14.12),prixVenteStandard:new Double(14.12),poids:new Double(14.12),prixAchat:new Double(14.12),prixMoyenPendere:new Double(14.12),perissable:new Boolean(false),temperature_conservation:new Double(52.12),imageProd:byte1,trans_imageProd:i,empReception:testInstances10[0],nature:testInstances11[0],categorieProduit:testInstances12[0],uniteMesure:testInstances13[0],regimeTVA:testInstances14[0],produitParent:null,fournisseurPref:testInstances9[0])]
//        //mockForConstraintsTests(DroitUtilisateur, testInstances)
//        mockDomain(Produit, testInstances20)
//        assertTrue testInstances20[0].validate()
//        testInstances20[0].save(flush:true)
//        def testInstanceslp = [new LigneProduit(sequenceLigne : new Integer(02), quantite : new Double(1),quantiteLivree  :new Double(0d),quantiteRetournee:new Double(0d),remise:new Double(0d),prix:new Double(15.45),prixDeduit:new Double(15.45),type:"ACHAT",trans_sousTotal:new Double(15.45),trans_sousTotalLivree:new Double(15.45),trans_sousTotalRetourne:new Double(15.45),trans_livree:new Boolean(true),trans_retournee:new Boolean(true),trans_ql:new Double(15.45),trans_qr:new Double(15.45),date_preremption:sqlDate1,produit:testInstances20[0],entrepot:testInstancese[0],bonCommande:null,bonLivraison:null,bonPret:null,devis:null)]
//        //mockForConstraintsTests(DroitUtilisateur, testInstances)
//        mockDomain(LigneProduit, testInstanceslp)
//        assertTrue testInstanceslp[0].validate()
//        testInstanceslp[0].save(flush:true)
//        
//        def testInstancesd = [new Devis(numDevis : "dh", type : "ACHAT", date : sqlDate1,dateFinValidite:sqlDate1,trans_totalht:new Double(14.12),trans_totaltva:new Double(14.12),trans_totalttc:new Double(14.12),trans_isvalid:new Double(14.12),partenaire:testInstances9[0],ligneProduits:testInstanceslp)]
//        //mockForConstraintsTests(DroitUtilisateur, testInstances)
//        mockDomain(Devis, testInstancesd)
//        assertTrue testInstancesd[0].validate()
//        testInstancesd[0].save(flush:true)
//        
//        def testInstances = [new BonCommande(numBC : "bc001", type : "VENTE", reference : "5561",date:sqlDate1,trans_totalht:new Double(14.12),trans_totaltva:new Double(14.12),trans_totalttc:new Double(14.12),totalttcRetour:new Double(0d),livree:new Boolean(false),paye:new Boolean(false),retourPaye:new Boolean(true),facture:new Boolean(false),bonPret:new Boolean(true),retournee:new Boolean(false),avecRetour:new Boolean(false),trans_livree:new Boolean(true),trans_retournee:new Boolean(true),partenaire:testInstances9p[0],utilisateur:testInstancesu[0],deviss:testInstancesd,ligneProduits:testInstanceslp)]
//        //mockForConstraintsTests(DroitUtilisateur, testInstances)
//        mockDomain(BonCommande, testInstances)
//        def testInstancesbd = [new BonLivraison(numBL : "XYZ", type : "ACHAT", reference : "5561",date:sqlDate1,trans_totalht:new Double(15.02),trans_totaltva:new Double(15.02),trans_totalttc:new Double(15.02),estBonRetour:new Boolean(true),estLivre:new Boolean(true),bonCommande:testInstances[0],ligneProduits:testInstanceslp,livraison:null)]
//        //mockForConstraintsTests(DroitUtilisateur, testInstances)
//        mockDomain(BonLivraison, testInstancesbd)
//        def testService = new BonLivraisonService()
//        def testInstances1 = [new PaternCompteur(libelle : "Pattern bon commande", prefixe : "BC{annee}{mois}{jour}", suffixe : "",pas:1,remplissage:12,numeroSuivant:2290,type:"BON_RETOUR")]
//        //mockForConstraintsTests(DroitUtilisateur, testInstances)
//        mockDomain(PaternCompteur, testInstances1)
//       def testId = "bc515151"
//        System.out.println("nb bb "+testInstances[0].bonPret)
//        
//        def testInstancesm = [new ModeLivraison(code : "0032", transporteur : "stcr", paiementALaReception : new Boolean(true))]
//        //mockForConstraintsTests(DroitUtilisateur, testInstances)
//        mockDomain(ModeLivraison, testInstancesm)
//        assertTrue testInstancesm[0].validate()
//        testInstancesm[0].save(flush:true)
//        def testInstancesli = [new Livraison(numExpedition : "l1555525",nbrColis  : new Integer(0), poidTotal : new Double(0d),frais:new Double(0d),date:sqlDate1,detail:"livraison a domicile",intituleVehicule:"hvjfh564564",immatriculation:"vgvg54654645",modeLivraison:testInstancesm[0],partenaire:testInstances9p[0],bonLivraisons:testInstancesbd)]
//        //mockForConstraintsTests(DroitUtilisateur, testInstances)
//        mockDomain(Livraison, testInstancesli)
//        //def testService1 = new SuperService()
//  
////def paternCompteurControl = mockFor(PaternCompteurService)
////paternCompteurControl.demand.getProchainNumBrEtIncrementer(1..1) {it -> return  testId}
////testService.paternCompteurService = paternCompteurControl.createMock()
//def t=testInstances[0]
//
//def myCriteria = [
//    list : {Closure  cls -> return testInstancesbd}
//]
//BonLivraison.metaClass.static.createCriteria = { myCriteria }
////def ff = testService.updateEtatBonCommandeLorsRetour(testInstances[0]) 
//
////        def bonCommandeServiceControl = mockFor(BonCommandeService)
////bonCommandeServiceControl.demand.update(testInstances[0]) {return testInstances[0] }
////testService.bonCommandeService = bonCommandeServiceControl.createMock()
//
//        def BonLivraisonServiceControl = mockFor(BonLivraisonService)
//        
//        def logTo = []
//    def loggerMock = mockFor(org.apache.commons.logging.Log)
//    loggerMock.demand.warn(1..1){ String message ->
//        logTo << [msg:message,level: 'warn']
//    }
//    SuperService.log = loggerMock.createMock()
//        
//        
//        BonLivraisonServiceControl.demand.update(1..1) {it -> return it }
//
//        def paiementServiceControl = mockFor(PaiementService)
//paiementServiceControl.demand.updateEtatPaiementBonCommandeAprsRetour(testInstances[0]) {return  testInstances[0]}
//testService.paiementService = paiementServiceControl.createMock()
//def retval1 = testService.update(testInstancesbd[0])   
////       assertTrue retval1 instanceof BonLivraison
////  System.out.println("la fin du tests de delete"+retval1)  
//        
//        
//       
//   }
}