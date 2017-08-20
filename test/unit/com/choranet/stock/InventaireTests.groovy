package com.choranet.stock

import grails.test.*
import grails.test.*
import grails.test.*
import org.zkoss.image.AImage
import org.zkoss.image.Image
import com.choranet.gesticom.util.Utilitaire
import com.choranet.gesticom.*
import com.choranet.commun.*
import com.choranet.compta.*
import com.choranet.securite.*
import java.text.*
class InventaireTests extends GrailsUnitTestCase {
    protected void setUp() {
        super.setUp()
    }

    protected void tearDown() {
        super.tearDown()
    }

    void testInventaireContraintes() {
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
                            def testInstances10 = [new Entrepot(code: "006", intitule : "ain sebaa", adresse : "rue 26 n°51",tel:"0673327392",modeValorisation:"PRIX_ACHAT",mode:"FIFO")]
        //mockForConstraintsTests(DroitUtilisateur, testInstances)
        mockDomain(Entrepot, testInstances10)
        assertTrue testInstances10[0].validate()
        testInstances10[0].save(flush:true)
        def testInstances = [new Inventaire(dateDebut : sqlDate11, dateFin : sqlDate22, details : "trimestre",etat:"EN COURS",entrepot:testInstances10[0],ligneInventaires:null)]
        //mockForConstraintsTests(DroitUtilisateur, testInstances)
        mockDomain(Inventaire, testInstances)
        assertEquals 1, Inventaire.count()
        assertTrue testInstances[0] instanceof Inventaire        
        def Inventaire2 = new Inventaire()
        assertFalse Inventaire2.validate()
        assertEquals "nullable", Inventaire2.errors["dateDebut"]
        assertEquals "nullable", Inventaire2.errors["etat"]
        assertEquals "nullable", Inventaire2.errors["entrepot"]
        def Inventaire4 = new Inventaire(dateDebut : sqlDate11, dateFin : sqlDate22, details : "trimestre",etat:"EN COURS",entrepot:testInstances10[0],ligneInventaires:null)
        assertTrue Inventaire4.validate()
        Inventaire4.save(flush:true)
        assertEquals 2, Inventaire.count()
        assertTrue Inventaire.get(Inventaire4.id) instanceof Inventaire
        System.out.println("bvghvghv"+Inventaire4.toString())
        assertEquals "Inventaire effectué entre le 2005-10-01 et le 2005-10-03", Inventaire4.toString()
    }
}
