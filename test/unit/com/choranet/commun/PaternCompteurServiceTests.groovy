package com.choranet.commun

import grails.test.*

class PaternCompteurServiceTests extends GrailsUnitTestCase {
    protected void setUp() {
        super.setUp()
    }

    protected void tearDown() {
        super.tearDown()
    }

   void testGetProchainNumBcEtIncrementer() {
       def testInstances1 = [new PaternCompteur(libelle : "Pattern bon commande", prefixe : "BC{annee}{mois}{jour}", suffixe : "",pas:1,remplissage:12,numeroSuivant:2290,type:"BON_COMMANDE")]
        //mockForConstraintsTests(DroitUtilisateur, testInstances)
        mockDomain(PaternCompteur, testInstances1)
        def PaternCompteurServiceControl = mockFor(PaternCompteurService)
        PaternCompteurServiceControl.demand.update(1..1) {it -> return it }
//        def myCriteria = [
//    list : {Closure  cls -> return new PaternCompteur()}
//]
//PaternCompteur.metaClass.static.createCriteria = { myCriteria }
         def mds = new PaternCompteurService()
         def res=mds.getProchainNumBcEtIncrementer()
        System.out.println("yyyyy "+res)
    }
    
    void testGetProchainNumBrEtIncrementer() {
       def testInstances1 = [new PaternCompteur(libelle : "Pattern bon commande", prefixe : "BC{annee}{mois}{jour}", suffixe : "",pas:1,remplissage:12,numeroSuivant:2290,type:"BON_RETOUR")]
        //mockForConstraintsTests(DroitUtilisateur, testInstances)
        mockDomain(PaternCompteur, testInstances1)
        def PaternCompteurServiceControl = mockFor(PaternCompteurService)
        PaternCompteurServiceControl.demand.update(1..1) {it -> return it }
//        def myCriteria = [
//    list : {Closure  cls -> return new PaternCompteur()}
//]
//PaternCompteur.metaClass.static.createCriteria = { myCriteria }
         def mds = new PaternCompteurService()
         def res=mds.getProchainNumBrEtIncrementer()
        System.out.println("yyyyy "+res)
    }
    
    void testGetProchainNumBlEtIncrementer() {
       def testInstances1 = [new PaternCompteur(libelle : "Pattern bon commande", prefixe : "BC{annee}{mois}{jour}", suffixe : "",pas:1,remplissage:12,numeroSuivant:2290,type:"BON_LIVRAISON")]
        //mockForConstraintsTests(DroitUtilisateur, testInstances)
        mockDomain(PaternCompteur, testInstances1)
        def PaternCompteurServiceControl = mockFor(PaternCompteurService)
        PaternCompteurServiceControl.demand.update(1..1) {it -> return it }
//        def myCriteria = [
//    list : {Closure  cls -> return new PaternCompteur()}
//]
//PaternCompteur.metaClass.static.createCriteria = { myCriteria }
         def mds = new PaternCompteurService()
         def res=mds.getProchainNumBlEtIncrementer()
        System.out.println("yyyyy "+res)
    }
    
    void testGetProchainNumBpEtIncrementer() {
       def testInstances1 = [new PaternCompteur(libelle : "Pattern bon commande", prefixe : "BC{annee}{mois}{jour}", suffixe : "",pas:1,remplissage:12,numeroSuivant:2290,type:"BON_PRET")]
        //mockForConstraintsTests(DroitUtilisateur, testInstances)
        mockDomain(PaternCompteur, testInstances1)
        def PaternCompteurServiceControl = mockFor(PaternCompteurService)
        PaternCompteurServiceControl.demand.update(1..1) {it -> return it }
//        def myCriteria = [
//    list : {Closure  cls -> return new PaternCompteur()}
//]
//PaternCompteur.metaClass.static.createCriteria = { myCriteria }
         def mds = new PaternCompteurService()
         def res=mds.getProchainNumBpEtIncrementer()
        System.out.println("yyyyy "+res)
    }
    
    void testGetProchainNumDevisEtIncrementer() {
       def testInstances1 = [new PaternCompteur(libelle : "Pattern bon commande", prefixe : "BC{annee}{mois}{jour}", suffixe : "",pas:1,remplissage:12,numeroSuivant:2290,type:"DEVIS")]
        //mockForConstraintsTests(DroitUtilisateur, testInstances)
        mockDomain(PaternCompteur, testInstances1)
        def PaternCompteurServiceControl = mockFor(PaternCompteurService)
        PaternCompteurServiceControl.demand.update(1..1) {it -> return it }
//        def myCriteria = [
//    list : {Closure  cls -> return new PaternCompteur()}
//]
//PaternCompteur.metaClass.static.createCriteria = { myCriteria }
         def mds = new PaternCompteurService()
         def res=mds.getProchainNumDevisEtIncrementer()
        System.out.println("yyyyy "+res)
    }
    
    void testGetProchainNumFactureEtIncrementer() {
       def testInstances1 = [new PaternCompteur(libelle : "Pattern bon commande", prefixe : "BC{annee}{mois}{jour}", suffixe : "",pas:1,remplissage:12,numeroSuivant:2290,type:"FACTURE")]
        //mockForConstraintsTests(DroitUtilisateur, testInstances)
        mockDomain(PaternCompteur, testInstances1)
        def PaternCompteurServiceControl = mockFor(PaternCompteurService)
        PaternCompteurServiceControl.demand.update(1..1) {it -> return it }
//        def myCriteria = [
//    list : {Closure  cls -> return new PaternCompteur()}
//]
//PaternCompteur.metaClass.static.createCriteria = { myCriteria }
         def mds = new PaternCompteurService()
         def res=mds.getProchainNumFactureEtIncrementer()
        System.out.println("yyyyy "+res)
    }
    
    void testGetProchainNumAvoirEtIncrementer() {
       def testInstances1 = [new PaternCompteur(libelle : "Pattern bon commande", prefixe : "BC{annee}{mois}{jour}", suffixe : "",pas:1,remplissage:12,numeroSuivant:2290,type:"AVOIR")]
        //mockForConstraintsTests(DroitUtilisateur, testInstances)
        mockDomain(PaternCompteur, testInstances1)
        def PaternCompteurServiceControl = mockFor(PaternCompteurService)
        PaternCompteurServiceControl.demand.update(1..1) {it -> return it }
//        def myCriteria = [
//    list : {Closure  cls -> return new PaternCompteur()}
//]
//PaternCompteur.metaClass.static.createCriteria = { myCriteria }
         def mds = new PaternCompteurService()
         def res=mds.getProchainNumAvoirEtIncrementer()
        System.out.println("yyyyy "+res)
    }
    
    void testGetProchainNumLivraisonEtIncrementer() {
       def testInstances1 = [new PaternCompteur(libelle : "Pattern bon commande", prefixe : "BC{annee}{mois}{jour}", suffixe : "",pas:1,remplissage:12,numeroSuivant:2290,type:"LIVRAISON")]
        //mockForConstraintsTests(DroitUtilisateur, testInstances)
        mockDomain(PaternCompteur, testInstances1)
        def PaternCompteurServiceControl = mockFor(PaternCompteurService)
        PaternCompteurServiceControl.demand.update(1..1) {it -> return it }
//        def myCriteria = [
//    list : {Closure  cls -> return new PaternCompteur()}
//]
//PaternCompteur.metaClass.static.createCriteria = { myCriteria }
         def mds = new PaternCompteurService()
         def res=mds.getProchainNumLivraisonEtIncrementer()
        System.out.println("yyyyy "+res)
    }
    
    void testGetProchainNumLivraison() {
       def testInstances1 = [new PaternCompteur(libelle : "Pattern bon commande", prefixe : "BC{annee}{mois}{jour}", suffixe : "",pas:1,remplissage:12,numeroSuivant:2290,type:"LIVRAISON")]
        //mockForConstraintsTests(DroitUtilisateur, testInstances)
        mockDomain(PaternCompteur, testInstances1)
        def PaternCompteurServiceControl = mockFor(PaternCompteurService)
        PaternCompteurServiceControl.demand.update(1..1) {it -> return it }
//        def myCriteria = [
//    list : {Closure  cls -> return new PaternCompteur()}
//]
//PaternCompteur.metaClass.static.createCriteria = { myCriteria }
         def mds = new PaternCompteurService()
         def res=mds.getProchainNumLivraison()
        System.out.println("yyyyy "+res)
    }
    
     void testGetProchainNumBc() {
       def testInstances1 = [new PaternCompteur(libelle : "Pattern bon commande", prefixe : "BC{annee}{mois}{jour}", suffixe : "",pas:1,remplissage:12,numeroSuivant:2290,type:"BON_COMMANDE")]
        //mockForConstraintsTests(DroitUtilisateur, testInstances)
        mockDomain(PaternCompteur, testInstances1)
        def PaternCompteurServiceControl = mockFor(PaternCompteurService)
        PaternCompteurServiceControl.demand.update(1..1) {it -> return it }
//        def myCriteria = [
//    list : {Closure  cls -> return new PaternCompteur()}
//]
//PaternCompteur.metaClass.static.createCriteria = { myCriteria }
         def mds = new PaternCompteurService()
         def res=mds.getProchainNumBc()
        System.out.println("yyyyy "+res)
    }
    
    void testGetProchainNumBr() {
       def testInstances1 = [new PaternCompteur(libelle : "Pattern bon commande", prefixe : "BC{annee}{mois}{jour}", suffixe : "",pas:1,remplissage:12,numeroSuivant:2290,type:"BON_RETOUR")]
        //mockForConstraintsTests(DroitUtilisateur, testInstances)
        mockDomain(PaternCompteur, testInstances1)
        def PaternCompteurServiceControl = mockFor(PaternCompteurService)
        PaternCompteurServiceControl.demand.update(1..1) {it -> return it }
//        def myCriteria = [
//    list : {Closure  cls -> return new PaternCompteur()}
//]
//PaternCompteur.metaClass.static.createCriteria = { myCriteria }
         def mds = new PaternCompteurService()
         def res=mds.getProchainNumBr()
        System.out.println("yyyyy "+res)
    }
    void testGetProchainNumBl() {
       def testInstances1 = [new PaternCompteur(libelle : "Pattern bon commande", prefixe : "BC{annee}{mois}{jour}", suffixe : "",pas:1,remplissage:12,numeroSuivant:2290,type:"BON_LIVRAISON")]
        //mockForConstraintsTests(DroitUtilisateur, testInstances)
        mockDomain(PaternCompteur, testInstances1)
        def PaternCompteurServiceControl = mockFor(PaternCompteurService)
        PaternCompteurServiceControl.demand.update(1..1) {it -> return it }
//        def myCriteria = [
//    list : {Closure  cls -> return new PaternCompteur()}
//]
//PaternCompteur.metaClass.static.createCriteria = { myCriteria }
         def mds = new PaternCompteurService()
         def res=mds.getProchainNumBl()
        System.out.println("yyyyy "+res)
    }
    
     void testGetProchainNumBp() {
       def testInstances1 = [new PaternCompteur(libelle : "Pattern bon commande", prefixe : "BC{annee}{mois}{jour}", suffixe : "",pas:1,remplissage:12,numeroSuivant:2290,type:"BON_PRET")]
        //mockForConstraintsTests(DroitUtilisateur, testInstances)
        mockDomain(PaternCompteur, testInstances1)
        def PaternCompteurServiceControl = mockFor(PaternCompteurService)
        PaternCompteurServiceControl.demand.update(1..1) {it -> return it }
//        def myCriteria = [
//    list : {Closure  cls -> return new PaternCompteur()}
//]
//PaternCompteur.metaClass.static.createCriteria = { myCriteria }
         def mds = new PaternCompteurService()
         def res=mds.getProchainNumBp()
        System.out.println("yyyyy "+res)
    }
    
     void testGetProchainNumDevis() {
       def testInstances1 = [new PaternCompteur(libelle : "Pattern bon commande", prefixe : "BC{annee}{mois}{jour}", suffixe : "",pas:1,remplissage:12,numeroSuivant:2290,type:"DEVIS")]
        //mockForConstraintsTests(DroitUtilisateur, testInstances)
        mockDomain(PaternCompteur, testInstances1)
        def PaternCompteurServiceControl = mockFor(PaternCompteurService)
        PaternCompteurServiceControl.demand.update(1..1) {it -> return it }
//        def myCriteria = [
//    list : {Closure  cls -> return new PaternCompteur()}
//]
//PaternCompteur.metaClass.static.createCriteria = { myCriteria }
         def mds = new PaternCompteurService()
         def res=mds.getProchainNumDevis()
        System.out.println("yyyyy "+res)
    }
    
     void testGetProchainNumFacture() {
       def testInstances1 = [new PaternCompteur(libelle : "Pattern bon commande", prefixe : "BC{annee}{mois}{jour}", suffixe : "",pas:1,remplissage:12,numeroSuivant:2290,type:"FACTURE")]
        //mockForConstraintsTests(DroitUtilisateur, testInstances)
        mockDomain(PaternCompteur, testInstances1)
        def PaternCompteurServiceControl = mockFor(PaternCompteurService)
        PaternCompteurServiceControl.demand.update(1..1) {it -> return it }
//        def myCriteria = [
//    list : {Closure  cls -> return new PaternCompteur()}
//]
//PaternCompteur.metaClass.static.createCriteria = { myCriteria }
         def mds = new PaternCompteurService()
         def res=mds.getProchainNumFacture()
        System.out.println("yyyyy "+res)
    }
     void testGetProchainNumAvoir() {
       def testInstances1 = [new PaternCompteur(libelle : "Pattern bon commande", prefixe : "BC{annee}{mois}{jour}", suffixe : "",pas:1,remplissage:12,numeroSuivant:2290,type:"AVOIR")]
        //mockForConstraintsTests(DroitUtilisateur, testInstances)
        mockDomain(PaternCompteur, testInstances1)
        def PaternCompteurServiceControl = mockFor(PaternCompteurService)
        PaternCompteurServiceControl.demand.update(1..1) {it -> return it }
//        def myCriteria = [
//    list : {Closure  cls -> return new PaternCompteur()}
//]
//PaternCompteur.metaClass.static.createCriteria = { myCriteria }
         def mds = new PaternCompteurService()
         def res=mds.getProchainNumAvoir()
        System.out.println("yyyyy "+res)
    }

     void testConstituerPattern() {
       def testInstances1 = [new PaternCompteur(libelle : "Pattern bon commande", prefixe : "BC{annee}{mois}{jour}", suffixe : "",pas:1,remplissage:12,numeroSuivant:2290,type:"FACTURE")]
        //mockForConstraintsTests(DroitUtilisateur, testInstances)
        mockDomain(PaternCompteur, testInstances1)
        def PaternCompteurServiceControl = mockFor(PaternCompteurService)
        PaternCompteurServiceControl.demand.update(1..1) {it -> return it }
//        def myCriteria = [
//    list : {Closure  cls -> return new PaternCompteur()}
//]
//PaternCompteur.metaClass.static.createCriteria = { myCriteria }
         def mds = new PaternCompteurService()
         def res=mds.constituerPattern(testInstances1[0])
        System.out.println("yyyyy "+res)
    }
    
    void testIncrementerPattern() {
       def testInstances1 = [new PaternCompteur(libelle : "Pattern bon commande", prefixe : "BC{annee}{mois}{jour}", suffixe : "",pas:1,remplissage:12,numeroSuivant:2290,type:"FACTURE")]
        //mockForConstraintsTests(DroitUtilisateur, testInstances)
        mockDomain(PaternCompteur, testInstances1)
        def PaternCompteurServiceControl = mockFor(PaternCompteurService)
        PaternCompteurServiceControl.demand.update(1..1) {it -> return it }
//        def myCriteria = [
//    list : {Closure  cls -> return new PaternCompteur()}
//]
//PaternCompteur.metaClass.static.createCriteria = { myCriteria }
         def mds = new PaternCompteurService()
         def res=mds.incrementerPattern(testInstances1[0])
        System.out.println("yyyyy "+res)
    }
    
}
