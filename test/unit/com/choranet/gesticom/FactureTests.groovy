package com.choranet.gesticom

import grails.test.*
import com.choranet.commun.*
import com.choranet.securite.*
import com.choranet.gesticom.*
import com.choranet.stock.*
import com.choranet.rh.*
import com.choranet.projet.*
import com.choranet.compta.*
import java.text.*
import com.choranet.gesticom.util.Utilitaire
import org.zkoss.image.AImage
import org.zkoss.image.Image
class FactureTests extends GrailsUnitTestCase {
    protected void setUp() {
        super.setUp()
    }

    protected void tearDown() {
        super.tearDown()
    }

    void testFactureContraintes() {
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
        
       def testInstances9 = [new Partenaire(code : "005", raisonSociale : "celibataire", idFiscal : null,patente:"15165165",registreCommerce:"commerce",cnss:"fk13252152",adresse:"oulfa",codePostal:"20200",telephone:"0673327392",fax:"0632972654",email:"contact@choranet.com",siteWeb:"www.choranet.com",adresseFacturation:"hay el hana",adresseLivraison:"ain sebaii",estClient:new Boolean(true),estFournisseur:new Boolean(false),estParticulier:new Boolean(false),estBlacklist:new Boolean(false),plafond:new Double(12.12),modeRelancePaiement:testInstances2[0],modeReglementDefaut:testInstances3[0],echeance:testInstances4[0],ville:testInstances6[0],formeJuridique:testInstances7[0],modeLivraison:testInstances8[0],categoriePartenaires:testInstances1,solde:new Double(15.01),compteVerrouile:new Boolean(true),zone:testInstancesz[0],commercial:testInstancesee[0])]        //mockForConstraintsTests(DroitUtilisateur, testInstances)
        //mockForConstraintsTests(DroitUtilisateur, testInstances)
        mockDomain(Partenaire, testInstances9)
        assertTrue testInstances9[0].validate()
        testInstances9[0].save(flush:true)
                            
        def testInstancese = [new Entrepot(code	 : "006", intitule : "ain sebaa", adresse : "rue 26 n°51",tel:"0673327392",modeValorisation:"PRIX_ACHAT",mode:"FIFO")]
        //mockForConstraintsTests(DroitUtilisateur, testInstances)
        mockDomain(Entrepot, testInstancese)
        assertTrue testInstancese[0].validate()
        testInstancese[0].save(flush:true)
       def testInstances9p = [new Partenaire(code : "005", raisonSociale : "celibataire", idFiscal : null,patente:"15165165",registreCommerce:"commerce",cnss:"fk13252152",adresse:"oulfa",codePostal:"20200",telephone:"0673327392",fax:"0632972654",email:"contact@choranet.com",siteWeb:"www.choranet.com",adresseFacturation:"hay el hana",adresseLivraison:"ain sebaii",estClient:new Boolean(true),estFournisseur:new Boolean(false),estParticulier:new Boolean(false),estBlacklist:new Boolean(false),plafond:new Double(12.12),modeRelancePaiement:testInstances2[0],modeReglementDefaut:testInstances3[0],echeance:testInstances4[0],ville:testInstances6[0],formeJuridique:testInstances7[0],modeLivraison:testInstances8[0],categoriePartenaires:testInstances1,solde:new Double(15.01),compteVerrouile:new Boolean(true),zone:testInstancesz[0],commercial:testInstancesee[0])]        //mockForConstraintsTests(DroitUtilisateur, testInstances)
        //mockForConstraintsTests(DroitUtilisateur, testInstances)
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
        
        
        Image i=Utilitaire.getLogoImage(getClass())
        def byte1=i.getByteData()
        
        
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
        
        
        
        def testInstancesf = [new Facture(numeroFacture : "fa121511", type : "ACHAT", reference : "ref045224",date:sqlDate1,comptabilise:new Boolean(false),estAvoir:new Boolean(false),trans_totalht:new Double(13.02),trans_totaltva:new Double(13.02),trans_totalttc:new Double(13.02),trans_ligneProduits:testInstanceslp,faitgenerateur:"bilan",partenaire:testInstances9[0],paiements:null,bonCommandes:null)]
        //mockForConstraintsTests(DroitUtilisateur, testInstances)
        mockDomain(Facture, testInstancesf)
        assertEquals 1, Facture.count()
        assertTrue testInstancesf[0] instanceof Facture        
        def Facture2 = new Facture()
        assertFalse Facture2.validate()
        assertEquals "nullable", Facture2.errors["numeroFacture"]
        assertEquals "nullable", Facture2.errors["type"]
        assertEquals "nullable", Facture2.errors["date"]
        assertEquals "nullable", Facture2.errors["partenaire"]
        def Facture3 = new Facture(numeroFacture : "fa121511", type : "ACHAT", reference : "ref045224",date:sqlDate1,comptabilise:new Boolean(false),estAvoir:new Boolean(false),trans_totalht:new Double(13.02),trans_totaltva:new Double(13.02),trans_totalttc:new Double(13.02),trans_ligneProduits:testInstanceslp,faitgenerateur:"bilan",partenaire:testInstances9[0],paiements:null,bonCommandes:null)
        assertFalse Facture3.validate()
        assertEquals "unique", Facture3.errors["numeroFacture"]
        def Facture4 = new Facture(numeroFacture : "fa121512", type : "ACHAT", reference : "ref045224",date:sqlDate1,comptabilise:new Boolean(false),estAvoir:new Boolean(false),trans_totalht:new Double(13.02),trans_totaltva:new Double(13.02),trans_totalttc:new Double(13.02),trans_ligneProduits:testInstanceslp,faitgenerateur:"bilan",partenaire:testInstances9[0],paiements:null,bonCommandes:null)
        assertTrue Facture4.validate()
        Facture4.save(flush:true)
        assertEquals 2, Facture.count()
        assertTrue Facture.get(Facture4.id) instanceof Facture
        assertEquals "fa121512", Facture4.toString()
    }
}
