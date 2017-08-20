package com.choranet.commun
import com.choranet.compta.*
import grails.test.*
import org.zkoss.image.AImage
import org.zkoss.image.Image
import com.choranet.gesticom.util.Utilitaire

class ChoraClientInfoTests extends GrailsUnitTestCase {
    protected void setUp() {
        super.setUp()
    }

    protected void tearDown() {
        super.tearDown()
    }

   void testChoraClientInfoContraintes() {
       def testInstances1=[new RegimeDeclarationTva(libelle:"Déclaration mensuelle à base encaissement",periodicite:"Trimestrielle",faitGenerateur:"Encaissement"),new RegimeDeclarationTva(libelle:"Déclaration trimestrielle à base encaissement",periodicite:"Trimestrielle",faitGenerateur:"Encaissement")]
       System.out.println("hjjgjg")
        def testInstances = [new ChoraClientInfo(raisonSociale : "CHORA INFORMATIQUE", email : "contact@choranet.com", formeJuridique : "SARL",telephone:"+212 522 233 343",fax:"+212 522 233 344",patente:"78/7877",rc:"78965",idF:"45788",cnss:"21/45789",site:"http://www.choranet.com",repertoirBackup:"/home/qllqli",adresse:"11M3 Lot Alhamd Ain Sbaa",codePostale:"20250",ville:"Casablanca",pays:"Maroc",cachetData:null,entetepiedData:null,copieData:null,logoData:null,regime:testInstances1[0])]
        //mockForConstraintsTests(DroitUtilisateur, testInstances)
        mockDomain(RegimeDeclarationTva,testInstances1)
        mockDomain(ChoraClientInfo, testInstances)
        assertEquals 1, ChoraClientInfo.count()
        assertTrue testInstances[0] instanceof ChoraClientInfo        
        def ChoraClientInfo2 = new ChoraClientInfo()
        assertFalse ChoraClientInfo2.validate()
        assertEquals "nullable", ChoraClientInfo2.errors["raisonSociale"]
        assertEquals "nullable", ChoraClientInfo2.errors["regime"]
        def ChoraClientInfo3 = new ChoraClientInfo(raisonSociale : "Abc", email : "alla_emsi@hotmail.com", formeJuridique : "nbbh",telephone:"0673327392",fax:"0673327392",patente:"545454654",rc:"rc",idF:"idf",cnss:"5465464",site:"www.choranet.com",repertoirBackup:"c:/chora",adresse:"rue 26 n°51",codePostale:"20200",ville:"casa",pays:"maroc",cachetData:null,entetepiedData:null,copieData:null,regime:testInstances1[1])
        assertEquals 1, ChoraClientInfo.count()
        assertTrue RegimeDeclarationTva.get(testInstances1[1].id) instanceof RegimeDeclarationTva
        Image i=Utilitaire.getLogoImage(getClass())
        def byte1=i.getByteData()
        def ChoraClientInfo4 = new ChoraClientInfo(raisonSociale : "CHORA INDUSTRIE", email : "contact@choranet.com", formeJuridique : "SARL",telephone:"+212 522 233 343",fax:"+212 522 233 344",patente:"78/7877",rc:"78965",idF:"45788",cnss:"21/45789",site:"http://www.choranet.com",repertoirBackup:"/home/qllqli",adresse:"11M3 Lot Alhamd Ain Sbaa",codePostale:"20250",ville:"Casablanca",pays:"Maroc",cachetData:null,entetepiedData:null,copieData:null,logoData:byte1,regime:testInstances1[1])
        try{
         
        assertTrue testInstances1[1].validate()
        testInstances1[1].save(flush:true)
        assertTrue ChoraClientInfo4.validate()
        }
        catch(Exception e)
        {
             
            System.out.println(e.stackTrace)
        }
        ChoraClientInfo4.save(flush:true)
       
    
    }
}
