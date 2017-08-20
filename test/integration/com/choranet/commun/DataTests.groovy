package com.choranet.commun

import grails.test.*

class DataTests extends GroovyTestCase {
    
    def grailsApplication // get grails app injected into test
    
    protected void setUp() {
        grails.buildtestdata.TestDataConfigurationHolder.reset()
    }

    protected void tearDown() {
        super.tearDown()
    }

    void testBuildAllDomains() {
        def successful = true
        grailsApplication.domainClasses.each { domainClass ->
            println "Test of ${domainClass.name}.build()"
            try {
                def domainObject = domainClass.clazz.build()
                assertNotNull domainObject."${domainClass.identifier.name}"
                //                if(domainClass.name.equals("LigneInventaire") || domainClass.name.equals("LigneProduit") || domainClass.name.equals("CompteBancaire") || domainClass.name.equals("CompteBancaireChoraClientInfo") || domainClass.name.equals("Paiement") || domainClass.name.equals("PaiementJbl")) {
                //                    println "********** ! SUCCESSFUL BUILD OF $domainClass.name !"
                //                } else {
                println "********** SUCCESSFUL BUILD OF $domainClass"
                //                }
            } catch (Exception e) {
                println "********** FAILED BUILD OF $domainClass"
                println "Exception = " + e
                successful = false
            }
        }
        assert successful
    }
}
