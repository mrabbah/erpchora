package com.choranet.securite

import org.zkoss.image.AImage
import org.zkoss.image.Image
import com.choranet.gesticom.util.Utilitaire
import grails.test.*
import com.choranet.commun.*
import com.choranet.compta.*
class UtilisateurTests extends GrailsUnitTestCase {
    protected void setUp() {
        super.setUp()
    }

    protected void tearDown() {
        super.tearDown()
    }

    void testUtilisateurContraintes() {
        Image i=Utilitaire.getLogoImage(getClass())
        def byte1=i.getByteData()
        def testInstances4 = [new DroitUtilisateur(droit:"ddd",description:"description",parent:null)]
        def testInstances1 = [new GroupeUtilisateur(intitule:"ddd",description:"decision",droits:null)]
        def testInstances2 = [new RegimeDeclarationTva(libelle:"Déclaration mensuelle à base encaissement",periodicite:"Trimestrielle",faitGenerateur:"Encaissement"),new RegimeDeclarationTva(libelle:"Déclaration trimestrielle à base encaissement",periodicite:"Trimestrielle",faitGenerateur:"Encaissement")]
        def testInstances3 = [  new ChoraClientInfo(raisonSociale : "CHORA INDUSTRIE1", email : "contact@choranet.com", formeJuridique : "SARL",telephone:"+212 522 233 343",fax:"+212 522 233 344",patente:"78/7877",rc:"78965",idF:"45788",cnss:"21/45789",site:"http://www.choranet.com",repertoirBackup:"/home/qllqli",adresse:"11M3 Lot Alhamd Ain Sbaa",codePostale:"20250",ville:"Casablanca",pays:"Maroc",cachetData:null,entetepiedData:null,copieData:null,logoData:byte1,regime:testInstances2[0])]
        def testInstances = [new Utilisateur(username : "XYZ", userRealName : "UTILISATEUR_XYZ",passwd :"allali",enabled:true,email:'contact@choranet.com',groupe:testInstances1[0],societe:testInstances3[0] )]
        //mockForConstraintsTests(Module, testInstances)
        mockDomain(RegimeDeclarationTva, testInstances2)
        mockDomain(GroupeUtilisateur, testInstances1)
        mockDomain(Utilisateur, testInstances)
        mockDomain(ChoraClientInfo, testInstances3)
        mockDomain(DroitUtilisateur, testInstances4)
        assertEquals 1, Utilisateur.count()
        assertTrue testInstances[0] instanceof Utilisateur 
        System.out.println("troisieme bbbbbb111")
        def utilisateur2 = new Utilisateur()
        assertFalse utilisateur2.validate()
        assertEquals "nullable", utilisateur2.errors["username"]
        assertEquals "nullable", utilisateur2.errors["userRealName"]
        assertEquals "nullable", utilisateur2.errors["passwd"]
        assertEquals "nullable", utilisateur2.errors["groupe"]
        assertEquals "nullable", utilisateur2.errors["societe"]
        System.out.println("troisieme bbbbbb111 bhbhbh")
        def utilisateur3= new Utilisateur(username : "XYZ", userRealName : "UTILISATEUR_XYZ",passwd :"allali",enabled:true,groupe:testInstances1[0],societe:testInstances3[0] )
        assertFalse utilisateur3.validate()
        assertEquals "unique", utilisateur3.errors["username"]
        System.out.println("troisieme ggggg")
        def utilisateur4= new Utilisateur(username : "admin1", userRealName : "Super Utilisateur",passwd :"NirfFVL6jts=",email:"contact@choranet.com",enabled:true,groupe:testInstances1[0],societe:testInstances3[0])
        def f=utilisateur4.validate()
        def grooupe1=new GroupeUtilisateur(intitule:"ddd",description:"decision",droits:null)
        assertTrue testInstances1[0].validate()
        testInstances1[0].save(flush:true)
        System.out.println("troisieme ggggg premier111"+testInstances1[0].validate()+testInstances1[0].getClass())
        assertTrue testInstances2[0].validate()
        testInstances2[0].save(flush:true)
        System.out.println("troisieme ggggg premier222"+testInstances2[0].validate()+testInstances2[0].getClass())
        assertTrue testInstances3[0].validate()
        testInstances3[0].save(flush:true)
        assertTrue testInstances4[0].validate()
        testInstances4[0].save(flush:true)
        System.out.println("troisieme ggggg premier3333"+testInstances3[0].validate()+testInstances3[0].getClass())
        assertTrue utilisateur4.validate()
        System.out.println("troisieme ggggg")
        utilisateur4.save(flush:true)
        System.out.println("troisieme ggggg")
        assertEquals 2, Utilisateur.count()
        assertTrue Utilisateur.get(utilisateur4.id) instanceof Utilisateur
        assertEquals "admin1", utilisateur4.toString()

    }
}
