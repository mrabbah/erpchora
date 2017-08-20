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
import java.util.Date
import grails.test.*
import org.hibernate.Criteria;

class InventaireServiceTests extends GrailsUnitTestCase {
    protected void setUp() {
        super.setUp()
    }

    protected void tearDown() {
        super.tearDown()
    }

   void testListeEntrepotsNonInventorier() {
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
        def testService=new InventaireService() 
        
        def myCriteria = [
    list : {Closure  cls -> return testInstances}
]
Inventaire.metaClass.static.createCriteria = { myCriteria }

        def myCriteria1 = [
    list : {Closure  cls -> return testInstances10}
]
Entrepot.metaClass.static.createCriteria = { myCriteria1 }
     def res=testService.listeEntrepotsNonInventorier()   
       System.out.println("resultat   "+res)
        
    }
    
    void testSuspendre() {
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
        def testInstances =[new Inventaire(dateDebut : sqlDate11, dateFin : sqlDate22, details : "trimestre",etat:"EN COURS",entrepot:testInstances10[0],ligneInventaires:null)]
        //mockForConstraintsTests(DroitUtilisateur, testInstances)
        //def s=new Inventaire(dateDebut : sqlDate11, dateFin : sqlDate22, details : "trimestre",etat:"EN COURS",entrepot:testInstances10[0],ligneInventaires:null)
        mockDomain(Inventaire,testInstances)
        def InventaireServiceControl = mockFor(InventaireService)
        def testService=new InventaireService() 
        InventaireServiceControl.demand.update(1..1) {it -> return it }
//        testService.inventaireService= InventaireControl.createMock()
        def res=testService.suspendre(testInstances[0])   
        def res1=(Inventaire)res
        System.out.println("hjvvhhvvh jbhjhb alllllllllllllll"+res.getClass()+"vvfcc    "+res1.dateDebut)
        
    }
}
