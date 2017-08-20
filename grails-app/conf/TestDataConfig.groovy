testDataConfig {
    sampleData {  
        def i = 1        
        'com.choranet.gesticom.AgenceBancaire' {
            nom = {-> "nom_agence${i++}" }   
        }
        'com.choranet.gesticom.BonCommande' {
            numBC = {-> "BC{i++}" }  
        }
        'com.choranet.gesticom.BonLivraison' {
            numBL = {-> "BL{i++}" }   
        }
        'com.choranet.gesticom.BonPret' {
            numBP = {-> "BP{i++}" }  
        }
        'com.choranet.projet.Calendrier' {
            nom = {-> "cal_N{i++}" }  
        }
        'com.choranet.gesticom.CategoriePartenaire' {
            libelle = {-> "cat_partenaire{i++}" }  
        }
        'com.choranet.securite.Cle' {
            cleProduit = {-> "cle{i++}" }   
            dateActivation = {-> new java.util.Date() }  
        }
        'com.choranet.gesticom.CompteBoncaire' {
            libelle = {-> "compte{i++}" }  
            rib = {-> "rib{i++}" }  
            codeSwift = {-> "code_swift{i++}" }  
        }
        'com.choranet.compta.DeclarationTva' {
            numDeclaration = {-> "compte{i++}" }  
        }
        'com.choranet.gesticom.Devis' {
            numDevis = {-> "NumDevis{i++}" }  
        }
        'com.choranet.securite.DroitUtilisateur' {
            droit = {-> "DROIT{i++}" }  
        }
        'com.choranet.gesticom.Echeance' {
            code = {-> 100*i++ }  
            libelle = {-> "ECHEANCE{i++}" }  
        }
        'com.choranet.stock.Entrepot' {
            code = {-> "EPNUM{i++}" }  
            intitule = {-> "Entrepot {i++}" }  
        }
        'com.choranet.gesticom.Facture' {
            numeroFacture = {-> "FACT_N_{i++}" }  
        }
        'com.choranet.rh.Fonction' {
            libelle = {-> "FONCTION_N_{i++}" }  
        }
        'com.choranet.commun.FormeJuridique' {
            libelle = {-> "FJ_N_{i++}" }  
        }
        'com.choranet.securite.GroupeUtilisateur' {
            intitule = {-> "GU_N_{i++}" }  
        }
        'com.choranet.stock.Livraison' {
            numExpedition = {-> "EXPEDITION_N_{i++}" }  
        }
        'com.choranet.stock.ModeLivraison' {
            code = {-> "MODELIVRAISON_N_{i++}" }  
        }
        'com.choranet.gesticom.ModeReglement' {
            code = {-> "MR_N_{i++}" }  
            libelle = {-> "MODEREGLEMENT_N_{i++}" }  
        }
        'com.choranet.gesticom.ModeRelancePaiement' {
            code = {-> "MRP_N_{i++}" }  
            libelle = {-> "MODERELANCEP_N_{i++}" }  
        }
        'com.choranet.securite.Module' {
            code = {-> "MODULE_N_{i++}" }  
            intitule = {-> "MODULE{i++}" }  
        }
        'com.choranet.stock.Nature' {
            libelle = {-> "NATURE_N_{i++}" }  
        }
        'com.choranet.exception.NatureDeCharge' {
            libelle = {-> "NATUREDC_N_{i++}" }  
        }
        'com.choranet.gesticom.Partenaire' {
            code = {-> "PART_N_{i++}" }  
            raisonSociale = {-> "Partenaire{i++}" }  
        }
        'com.choranet.commun.PaternCompteur' {
            libelle = {-> "PATTERN_COMPT_N_{i++}" }  
        }
        'com.choranet.commun.Pays' {
            intitule = {-> "PAYS{i++}" }  
        }
        'com.choranet.stock.Produit' {
            code = {-> "PDT{i++}" }  
            codebarre = {-> "{i++}9876543210" }  
        }
        'com.choranet.compta.RegimeTVA' {
            libelle = {-> "Regime{i++}" }   
            taux = {-> 11*i++ }   
        }
        'com.choranet.gesticom.RegleFacturationAutomatique' {
            libelle = {-> "RFA_N_{i++}" }   
        }
        'com.choranet.gesticom.ReglePrix' {
            code = {-> "RPRIX_N_{i++}" }   
            libelle = {-> "RPRIX{i++}" }   
        }
        'com.choranet.stock.UniteMesure' {
            libelle = {-> "UniteMesure{i++}" }   
        }
        'com.choranet.securite.Utilisateur' {
            username = {-> "user{i++}" }   
        }
        'com.choranet.commun.Ville' {
            intitule = {-> "VILLE{i++}" }   
        }
    }
}

/*
// When using a closure, if your tests expect a particular value, you'll likely want to reset
// the build-test-data config in the setUp of your test, or in the test itself.  Otherwise if
// your tests get run in a different order you'll get different values

// (in test/integration/FooTests.groovy)

void setUp() {
    grails.buildtestdata.TestDataConfigurationHolder.reset()
}
*/


// if you'd like to disable the build-test-data plugin in an environment, just set
// the "enabled" property to false

environments {
    production {
        testDataConfig {
            enabled = false
        }
    }
}
