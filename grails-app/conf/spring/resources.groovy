
	
// Place your Spring DSL code here

beans = { 
    utilisateurWindow(com.choranet.securite.UtilisateurWindow) { bean ->
        bean.scope = "prototype"
        bean.autowire = "byName"
    }
    
    groupeUtilisateurWindow(com.choranet.securite.GroupeUtilisateurWindow, ref("droitUtilisateurService")) { bean ->
        bean.scope = "prototype"
        bean.autowire = "byName"
    }
    
    activationWindow(com.choranet.securite.ActivationWindow) { bean ->
        bean.scope = "prototype"
        bean.autowire = "byName"
    }
    
    loginWindow(com.choranet.securite.LoginWindow) { bean ->
        bean.scope = "prototype"
        bean.autowire = "byName"
    }
    
    mainWindow(com.choranet.commun.MainWindow) { bean ->
        bean.scope = "prototype"
        bean.autowire = "byName"
    }
    
    choraClientInfoWindow(com.choranet.commun.ChoraClientInfoWindow, true) { bean ->
        bean.scope = "prototype"
        bean.autowire = "byName"
    }
    
    installWindow(com.choranet.commun.ChoraClientInfoWindow, false) { bean ->
        bean.scope = "prototype"
        bean.autowire = "byName"
    }
    
    backupRestoreWindow(com.choranet.commun.BackupRestoreWindow, ref("backupRestoreService")) { bean ->
        bean.scope = "prototype"
        bean.autowire = "byName"
    }
    
    cleWindow(com.choranet.securite.CleWindow) { bean ->
        bean.scope = "prototype"
        bean.autowire = "byName"
    }
    
    clePopUpWindow(com.choranet.securite.ClePopUpWindow) { bean ->
        bean.scope = "prototype"
        bean.autowire = "byName"
    }
    
    
    paysWindow(com.choranet.commun.PaysWindow) { bean ->
        bean.scope = "prototype"
        bean.autowire = "byName"
    }
    
    zoneWindow(com.choranet.commun.ZoneWindow) { bean ->
        bean.scope = "prototype"
        bean.autowire = "byName"
    }
    
    agenceBancaireWindow(com.choranet.gesticom.AgenceBancaireWindow) { bean ->
        bean.scope = "prototype"
        bean.autowire = "byName"
    }
    
    categoriePartenaireWindow(com.choranet.gesticom.CategoriePartenaireWindow) { bean ->
        bean.scope = "prototype"
        bean.autowire = "byName"
    }
    
    entrepotWindow(com.choranet.stock.EntrepotWindow) { bean ->
        bean.scope = "prototype"
        bean.autowire = "byName"
    }
    
    devisWindow(com.choranet.gesticom.DevisWindow, ref("partenaireService"), ref("paternCompteurService"), ref("devisService")) { bean ->
        bean.scope = "prototype"
        bean.autowire = "byName"
    }
    
    natureWindow(com.choranet.stock.NatureWindow) { bean ->
        bean.scope = "prototype"
        bean.autowire = "byName"
    }
    
    alertsSecuriteStockWindow(com.choranet.stock.AlertsSecuriteStockWindow, ref("alertsSecuriteStockService")) { bean ->
        bean.scope = "prototype"
        bean.autowire = "byName"
    }
    
    partenaireWindow(com.choranet.gesticom.PartenaireWindow, ref("partenaireService"), ref("compteBancaireService"), 
                              ref("agenceBancaireService"), ref("contactService")) { bean ->
        bean.scope = "prototype"
        bean.autowire = "byName"
    }
    
    depensesWindow(com.choranet.exception.DepensesWindow) { bean ->
        bean.scope = "prototype"
        bean.autowire = "byName"
    }
    
    paternCompteurWindow(com.choranet.commun.PaternCompteurWindow) { bean ->
        bean.scope = "prototype"
        bean.autowire = "byName"
    }
   
    bonLivraisonWindow(com.choranet.gesticom.BonLivraisonWindow, ref("paternCompteurService"), ref("bonCommandeService"),
                       ref("bonLivraisonService"), ref("parametrageService"), ref("partenaireService"), ref("ligneProduitService")) { bean ->
        bean.scope = "prototype"
        bean.autowire = "byName"
    }
    
    livraisonWindow(com.choranet.stock.LivraisonWindow, ref("livraisonService"), ref("paternCompteurService"),
                    ref("partenaireService"), ref("bonLivraisonService"), ref("bonCommandeService")) { bean ->
        bean.scope = "prototype"
        bean.autowire = "byName"
    }
    
    contactWindow(com.choranet.gesticom.ContactWindow) { bean ->
        bean.scope = "prototype"
        bean.autowire = "byName"
    }
    
    categorieProduitWindow(com.choranet.stock.CategorieProduitWindow) { bean ->
        bean.scope = "prototype"
        bean.autowire = "byName"
    }
    
    villeWindow(com.choranet.commun.VilleWindow) { bean ->
        bean.scope = "prototype"
        bean.autowire = "byName"
    }
    
    declarationTvaWindow(com.choranet.compta.DeclarationTvaWindow) { bean ->
        bean.scope = "prototype"
        bean.autowire = "byName"
    }

    uniteMesureWindow(com.choranet.stock.UniteMesureWindow) { bean ->
        bean.scope = "prototype"
        bean.autowire = "byName"
    }
    
    echeanceWindow(com.choranet.gesticom.EcheanceWindow) { bean ->
        bean.scope = "prototype"
        bean.autowire = "byName"
    }
    
    messageDocumentWindow(com.choranet.commun.MessageDocumentWindow) { bean ->
        bean.scope = "prototype"
        bean.autowire = "byName"
    }
    
    mouvementStockWindow(com.choranet.stock.MouvementStockWindow, ref("securitestockService"),
                         ref("parametrageService")) { bean ->
        bean.scope = "prototype"
        bean.autowire = "byName"
    }
    
    formeJuridiqueWindow(com.choranet.commun.FormeJuridiqueWindow) { bean ->
        bean.scope = "prototype"
        bean.autowire = "byName"
    }
    
    regimeDeclarationTvaWindow(com.choranet.compta.RegimeDeclarationTvaWindow) { bean ->
        bean.scope = "prototype"
        bean.autowire = "byName"
    }
    
    compteBancaireWindow(com.choranet.gesticom.CompteBancaireWindow) { bean ->
        bean.scope = "prototype"
        bean.autowire = "byName"
    }
    
    regleFacturationAutomatiqueWindow(com.choranet.gesticom.RegleFacturationAutomatiqueWindow) { bean ->
        bean.scope = "prototype"
        bean.autowire = "byName"
    }
    
    produitWindow(com.choranet.stock.ProduitWindow, ref("parametrageService"), ref("partenaireService")) { bean ->
        bean.scope = "prototype"
        bean.autowire = "byName"
    }
 
    modeLivraisonWindow(com.choranet.stock.ModeLivraisonWindow) { bean ->
        bean.scope = "prototype"
        bean.autowire = "byName"
    }
    
    regimeTVAWindow(com.choranet.compta.RegimeTVAWindow) { bean ->
        bean.scope = "prototype"
        bean.autowire = "byName"
    }
    
    securitestockWindow(com.choranet.stock.SecuritestockWindow, ref("parametrageService")) { bean ->
        bean.scope = "prototype"
        bean.autowire = "byName"
    }
    
    bonCommandeWindow(com.choranet.gesticom.BonCommandeWindow, ref("partenaireService"), ref("paternCompteurService"), 
                      ref("bonCommandeService"), ref("devisService"), ref("parametrageService")) { bean ->
        bean.scope = "prototype"
        bean.autowire = "byName"
    }
    
    inventaireWindow(com.choranet.stock.InventaireWindow, ref("inventaireService"),
        ref("parametrageService")) { bean ->
        bean.scope = "prototype"
        bean.autowire = "byName"
    }
    
    reglePrixWindow(com.choranet.gesticom.ReglePrixWindow, ref("reglePrixService")) { bean ->
        bean.scope = "prototype"
        bean.autowire = "byName"
    }
    
    factureWindow(com.choranet.gesticom.FactureWindow, ref("partenaireService"), ref("paternCompteurService"), ref("paiementService"), ref("factureService")) { bean ->
        bean.scope = "prototype"
        bean.autowire = "byName"
    }
    
    modeRelancePaiementWindow(com.choranet.gesticom.ModeRelancePaiementWindow) { bean ->
        bean.scope = "prototype"
        bean.autowire = "byName"
    }
    
    paiementWindow(com.choranet.gesticom.PaiementWindow, ref("paiementService")) { bean ->
        bean.scope = "prototype"
        bean.autowire = "byName"
    }
    
    modeReglementWindow(com.choranet.gesticom.ModeReglementWindow) { bean ->
        bean.scope = "prototype"
        bean.autowire = "byName"
    }
    
    natureDeChargeWindow(com.choranet.exception.NatureDeChargeWindow) { bean ->
        bean.scope = "prototype"
        bean.autowire = "byName"
    }
    
    rapportWindow(com.choranet.gesticom.RapportWindow, ref("bonCommandeService"), ref("paiementService"), ref("partenaireService")) { bean ->
        bean.scope = "prototype"
        bean.autowire = "byName"
    }
    
    produitsVendusAchetesWindow (com.choranet.gesticom.ProduitsVendusAchetesWindow){ bean ->
        bean.scope = "prototype"
        bean.autowire = "byName"
    }
    evolutionVentesAchatsWindow (com.choranet.gesticom.EvolutionVentesAchatsWindow, ref("livraisonService")){ bean ->
        bean.scope = "prototype"
        bean.autowire = "byName"
    }
    boiteOutilsWindow (com.choranet.gesticom.BoiteOutilsWindow, ref("produitService"), ref("partenaireService"), ref("livraisonService"),
                       ref("securitestockService"), ref("mouvementStockService")){ bean ->
        bean.scope = "prototype"
        bean.autowire = "byName"
    }
	  
    tacheWindow(com.choranet.projet.TacheWindow) { bean ->
        bean.scope = "prototype"
        bean.autowire = "byName"
    }
    
    loueurWindow(com.choranet.projet.LoueurWindow) { bean ->
        bean.scope = "prototype"
        bean.autowire = "byName"
    }

    jourFerierWindow(com.choranet.projet.JourFerierWindow) { bean ->
        bean.scope = "prototype"
        bean.autowire = "byName"
    }
    
    soustraitantWindow(com.choranet.projet.SoustraitantWindow) { bean ->
        bean.scope = "prototype"
        bean.autowire = "byName"
    }
    
    outilEmployeWindow(com.choranet.projet.OutilEmployeWindow) { bean ->
        bean.scope = "prototype"
        bean.autowire = "byName"
    }
    
    calendrierWindow(com.choranet.projet.CalendrierWindow) { bean ->
        bean.scope = "prototype"
        bean.autowire = "byName"
    }
  
    projetWindow(com.choranet.projet.ProjetWindow) { bean ->
        bean.scope = "prototype"
        bean.autowire = "byName"
    }
  
    soustraitanceWindow(com.choranet.projet.SoustraitanceWindow, ref("projetService")) { bean ->
        bean.scope = "prototype"
        bean.autowire = "byName"
    }
    
    outillageCollectifWindow(com.choranet.projet.OutillageCollectifWindow) { bean ->
        bean.scope = "prototype"
        bean.autowire = "byName"
    }
    
    fichePresenceWindow(com.choranet.rh.FichePresenceWindow, ref("projetService")) { bean ->
        bean.scope = "prototype"
        bean.autowire = "byName"
    }
    
    locationOutilWindow(com.choranet.projet.LocationOutilWindow, ref("projetService")) { bean ->
        bean.scope = "prototype"
        bean.autowire = "byName"
    }
    
    affectationProduitWindow(com.choranet.projet.AffectationProduitWindow, ref("projetService")) { bean ->
        bean.scope = "prototype"
        bean.autowire = "byName"
    }
 
    affectationOutillageWindow(com.choranet.projet.AffectationOutillageWindow, ref("projetService"), ref("affectationOutillageService")) { bean ->
        bean.scope = "prototype"
        bean.autowire = "byName"
    }
    
    noteWindow(com.choranet.projet.NoteWindow) { bean ->
        bean.scope = "prototype"
        bean.autowire = "byName"
    }
    
    phaseWindow(com.choranet.projet.PhaseWindow) { bean ->
        bean.scope = "prototype"
        bean.autowire = "byName"
    }
    
    affectationOutilWindow(com.choranet.projet.AffectationOutilWindow, ref("projetService")) { bean ->
        bean.scope = "prototype"
        bean.autowire = "byName"
    }
       
    paiementJblWindow(com.choranet.exception.PaiementJblWindow, ref("fichePresenceService")) { bean ->
        bean.scope = "prototype"
        bean.autowire = "byName"
    }
    
    outilLouableWindow(com.choranet.projet.OutilLouableWindow) { bean ->
        bean.scope = "prototype"
        bean.autowire = "byName"
    }
    
    employeWindow(com.choranet.rh.EmployeWindow) { bean ->
        bean.scope = "prototype"
        bean.autowire = "byName"
    }
        
    chargeDiversWindow(com.choranet.projet.ChargeDiversWindow, ref("projetService")) { bean ->
        bean.scope = "prototype"
        bean.autowire = "byName"
    }
        
    fonctionWindow(com.choranet.rh.FonctionWindow) { bean ->
        bean.scope = "prototype"
        bean.autowire = "byName"
    }

    tdbWindow(com.choranet.projet.TdbWindow, ref("projetService")) { bean ->
        bean.scope = "prototype"
        bean.autowire = "byName"
    }
    
    ParametrageWindow(com.choranet.commun.ParametrageWindow, true, ref("parametrageService")) { bean ->
        bean.scope = "prototype"
        bean.autowire = "byName"
    }
  
}
