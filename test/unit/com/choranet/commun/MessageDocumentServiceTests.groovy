package com.choranet.commun

import grails.test.*

class MessageDocumentServiceTests extends GrailsUnitTestCase {
    protected void setUp() {
        super.setUp()
    }

    protected void tearDown() {
        super.tearDown()
    }

    void testGetMessageParType () { 
        def testInstances = [new MessageDocument(entete : "hvhjv", pied : "bhhvhv", type : "BON_COMMANDE",typeMouvement:"ACHAT")]
        //mockForConstraintsTests(DroitUtilisateur, testInstances)
        mockDomain(MessageDocument, testInstances)
         def mds = new MessageDocumentService()
         
        def myCriteria = [
    list : {Closure  cls -> return testInstances[0]}
]
MessageDocument.metaClass.static.createCriteria = { myCriteria }
def f = mds.getMessageParType("BON_COMMANDE","ACHAT")  
System.out.println("hbhhjvjv   "+f)
assertNotNull f
assertEquals "hvhjv",testInstances[0].entete
    }
        
   
    
}
