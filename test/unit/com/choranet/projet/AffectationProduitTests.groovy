package com.choranet.projet

import grails.test.*
import com.choranet.commun.*
import com.choranet.compta.*
import com.choranet.securite.*
import com.choranet.stock.*
import org.zkoss.image.AImage
import org.zkoss.image.Image
import java.text.*
import com.choranet.gesticom.*
import com.choranet.rh.*
import com.choranet.gesticom.util.Utilitaire
class AffectationProduitTests extends GrailsUnitTestCase {
    protected void setUp() {
        super.setUp()
    }

    protected void tearDown() {
        super.tearDown()
    }

    void testDroitUtilisateurContraintes() {
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
        def testInstancese = [new Employe(numero : num, nom : "allali", prenom : "youssef",telephone:"0673327392",tauxHoraire:th,salaire:sa,calendrier:testInstances22[0],fonction:testInstances33[0])]
        //mockForConstraintsTests(DroitUtilisateur, testInstances)
        mockDomain(Employe, testInstancese)
        assertTrue testInstancese[0].validate()
        testInstancese[0].save(flush:true)
        
        
        def testInstances9 = [new Partenaire(code : "005", raisonSociale : "celibataire", idFiscal : null,patente:"15165165",registreCommerce:"commerce",cnss:"fk13252152",adresse:"oulfa",codePostal:"20200",telephone:"0673327392",fax:"0632972654",email:"contact@choranet.com",siteWeb:"www.choranet.com",adresseFacturation:"hay el hana",adresseLivraison:"ain sebaii",estClient:new Boolean(true),estFournisseur:new Boolean(false),estParticulier:new Boolean(false),estBlacklist:new Boolean(false),plafond:new Double(12.12),modeRelancePaiement:testInstances2m[0],modeReglementDefaut:testInstances3m[0],echeance:testInstances4e[0],ville:testInstances6v[0],formeJuridique:testInstances7f[0],modeLivraison:testInstances8m[0],categoriePartenaires:testInstances1c,solde:new Double(15.01),compteVerrouile:new Boolean(true),zone:testInstancesz[0],commercial:testInstancese[0])]
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
                            
        def testInstancesep = [new Entrepot(code	 : "006", intitule : "ain sebaa", adresse : "rue 26 n°51",tel:"0673327392",modeValorisation:"PRIX_ACHAT",mode:"FIFO")]
        //mockForConstraintsTests(DroitUtilisateur, testInstances)
        mockDomain(Entrepot, testInstancesep)
        assertTrue testInstancesep[0].validate()
        testInstancesep[0].save(flush:true)
        
        
        def testInstances9p = [new Partenaire(code : "005", raisonSociale : "celibataire", idFiscal : null,patente:"15165165",registreCommerce:"commerce",cnss:"fk13252152",adresse:"oulfa",codePostal:"20200",telephone:"0673327392",fax:"0632972654",email:"contact@choranet.com",siteWeb:"www.choranet.com",adresseFacturation:"hay el hana",adresseLivraison:"ain sebaii",estClient:new Boolean(true),estFournisseur:new Boolean(false),estParticulier:new Boolean(false),estBlacklist:new Boolean(false),plafond:new Double(12.12),modeRelancePaiement:testInstances2m[0],modeReglementDefaut:testInstances3m[0],echeance:testInstances4[0],ville:testInstances6v[0],formeJuridique:testInstances7f[0],modeLivraison:testInstances8m[0],categoriePartenaires:testInstances1c,solde:new Double(15.01),compteVerrouile:new Boolean(true),zone:testInstancesz[0],commercial:testInstancese[0])]
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
        def testInstanceslp = [new LigneProduit(sequenceLigne : new Integer(02), quantite : new Double(1),quantiteLivree  :new Double(0d),quantiteRetournee:new Double(0d),remise:new Double(0d),prix:new Double(15.45),prixDeduit:new Double(15.45),type:"ACHAT",trans_sousTotal:new Double(15.45),trans_sousTotalLivree:new Double(15.45),trans_sousTotalRetourne:new Double(15.45),trans_livree:new Boolean(true),trans_retournee:new Boolean(true),trans_ql:new Double(15.45),trans_qr:new Double(15.45),date_preremption:sqlDate1,produit:testInstances20[0],entrepot:testInstancesep[0],bonCommande:null,bonLivraison:null,bonPret:null,devis:null)]
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
        assertTrue testInstancesbd[0].validate()
        testInstancesbd[0].save(flush:true)
        def testInstancesbc = [new BonCommande(numBC : "bc001", type : "ACHAT", reference : "5561",date:sqlDate1,trans_totalht:new Double(14.12),trans_totaltva:new Double(14.12),trans_totalttc:new Double(14.12),totalttcRetour:new Double(0d),livree:new Boolean(false),paye:new Boolean(false),retourPaye:new Boolean(true),facture:new Boolean(false),bonPret:new Boolean(false),retournee:new Boolean(false),avecRetour:new Boolean(false),trans_livree:new Boolean(true),trans_retournee:new Boolean(true),partenaire:testInstances9p[0],utilisateur:testInstancesu[0],deviss:testInstancesd,ligneProduits:testInstanceslp)]
        //mockForConstraintsTests(DroitUtilisateur, testInstances)
        mockDomain(BonCommande, testInstancesbc)
        assertTrue testInstancesbc[0].validate()
        testInstancesbc[0].save(flush:true)
        def testInstancesdn = [new DocumentNumerique(titre : "Doc1", type : "PDF", donnees : null)]
        //mockForConstraintsTests(DocumentNumerique, testInstances)
        mockDomain(DocumentNumerique, testInstancesdn)
        assertTrue testInstancesdn[0].validate()
        testInstancesdn[0].save(flush:true)
        
        
        def testInstancespj = [new Projet(numero : new Integer(12), nom : "gestion commercial", dateDebutPrevu : null,dateDebutReelle:sqlDate1,dateFinPrevue:sqlDate1,dateFinReelle:sqlDate1,chargePrevue:new Double(15.12),chargeReelle:new Double(15.12),avancementPrevu:new Double(15.12),avancementReel:new Double(15.12),adresseProjet:"ain sebaa",codePostal:"20200",ville:"casa",fax:"0673327392",telephone:"0673327392",email:"contact@choranet.com",nomContact:"pneumatique",montantBonCommande:new Integer(100),calendrier:testInstances22[0],chefChantier:testInstancese[0], bonCommande:testInstancesbc[0],documents:testInstancesdn)]
        //mockForConstraintsTests(DroitUtilisateur, testInstances)
        mockDomain(Projet, testInstancespj)
        assertTrue testInstancespj[0].validate()
        testInstancespj[0].save(flush:true)
        
        
        
        
        def testInstancesap = [new AffectationProduit(date : sqlDate1, quantite : new Integer(12), prixUnitaire : new Double(15.12),total:new Double(15.12),bonLivraison:testInstancesbd[0],projet:testInstancespj[0],produit:testInstances20[0])]
        //mockForConstraintsTests(DroitUtilisateur, testInstances)
        mockDomain(AffectationProduit, testInstancesap)
        assertEquals 1, AffectationProduit.count()
        assertTrue testInstancesap[0] instanceof AffectationProduit        
        def AffectationProduit2 = new AffectationProduit()
        assertFalse AffectationProduit2.validate()
        assertEquals "nullable", AffectationProduit2.errors["date"]
        assertEquals "nullable", AffectationProduit2.errors["quantite"]
        assertEquals "nullable", AffectationProduit2.errors["prixUnitaire"]
        assertEquals "nullable", AffectationProduit2.errors["projet"]
        assertEquals "nullable", AffectationProduit2.errors["produit"]
        def AffectationProduit4 = new AffectationProduit(date : sqlDate1, quantite : new Integer(12), prixUnitaire : new Double(15.12),total:new Double(15.12),bonLivraison:testInstancesbd[0],projet:testInstancespj[0],produit:testInstances20[0])
        assertTrue AffectationProduit4.validate()
        AffectationProduit4.save(flush:true)
        assertEquals 2, AffectationProduit.count()
        assertTrue AffectationProduit.get(AffectationProduit4.id) instanceof AffectationProduit
        assertNotNull  AffectationProduit4.toString()
        assertTrue AffectationProduit4.quantite >= 0
        assertTrue AffectationProduit4.prixUnitaire >= 0d
    }
}