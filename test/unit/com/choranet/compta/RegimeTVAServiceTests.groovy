package com.choranet.compta

import grails.test.*

class RegimeTVAServiceTests extends GrailsUnitTestCase {
    protected void setUp() {
        super.setUp()
    }

    protected void tearDown() {
        super.tearDown()
    }

     void testGetRegimeByLibelle() {
             def j=new Integer(20)
         def j1=new Integer(25)
        def testInstances = [new RegimeTVA(libelle : "20%", taux : j)]
        //mockForConstraintsTests(DroitUtilisateur, testInstances)
        mockDomain(RegimeTVA, testInstances)
         def rts=new RegimeTVAService()
        rts.getDefaultRegime()
        def par="20"
         def myCriteria = [
    list : {Closure  cls -> return new RegimeTVA()}
]
RegimeTVA.metaClass.static.createCriteria = { myCriteria }
def f = rts.getRegimeByLibelle(par)  
        System.out.println("autre fonction"+f)

        }
    
     void testGetDefaultRegime() {
         
        def j=new Integer(20)
         def j1=new Integer(25)
        def testInstances = [new RegimeTVA(libelle : "20%", taux : j)]
        //mockForConstraintsTests(DroitUtilisateur, testInstances)
        mockDomain(RegimeTVA, testInstances)
         def rts=new RegimeTVAService()
        rts.getDefaultRegime()
        System.out.println("bjbjb "+rts.getDefaultRegime())
        assertNotNull rts.getDefaultRegime() 
        
        }
        
         
        
}
