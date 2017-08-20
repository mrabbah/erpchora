databaseChangeLog = {
	
	changeSet(author: "rabbah (generated)", id: "1372323562567-1") {
		dropIndex(indexName: "affectationoutil_cout_horaire", tableName: "affectation_outil")
	}

	changeSet(author: "rabbah (generated)", id: "1372323562567-2") {
		dropIndex(indexName: "affectationoutil_date", tableName: "affectation_outil")
	}

	changeSet(author: "rabbah (generated)", id: "1372323562567-3") {
		dropIndex(indexName: "affectationoutil_heures_entretien", tableName: "affectation_outil")
	}

	changeSet(author: "rabbah (generated)", id: "1372323562567-4") {
		dropIndex(indexName: "affectationoutil_heures_utilisation", tableName: "affectation_outil")
	}

	changeSet(author: "rabbah (generated)", id: "1372323562567-5") {
		dropIndex(indexName: "affutillage_da", tableName: "affectation_outillage")
	}

	changeSet(author: "rabbah (generated)", id: "1372323562567-6") {
		dropIndex(indexName: "affutillage_dfuo", tableName: "affectation_outillage")
	}

	changeSet(author: "rabbah (generated)", id: "1372323562567-7") {
		dropIndex(indexName: "affectationproduit_date", tableName: "affectation_produit")
	}

	changeSet(author: "rabbah (generated)", id: "1372323562567-8") {
		dropIndex(indexName: "affectationproduit_prix_unitaire", tableName: "affectation_produit")
	}

	changeSet(author: "rabbah (generated)", id: "1372323562567-9") {
		dropIndex(indexName: "affectationproduit_quantite", tableName: "affectation_produit")
	}

	changeSet(author: "rabbah (generated)", id: "1372323562567-10") {
		dropIndex(indexName: "agencebancaire_adresse", tableName: "agence_bancaire")
	}

	changeSet(author: "rabbah (generated)", id: "1372323562567-11") {
		dropIndex(indexName: "agencebancaire_nom", tableName: "agence_bancaire")
	}

	changeSet(author: "rabbah (generated)", id: "1372323562567-12") {
		dropIndex(indexName: "agencebancaire_tel", tableName: "agence_bancaire")
	}

	changeSet(author: "rabbah (generated)", id: "1372323562567-13") {
		dropIndex(indexName: "alertssecuritestock_date_alerte", tableName: "alerts_securite_stock")
	}

	changeSet(author: "rabbah (generated)", id: "1372323562567-14") {
		dropIndex(indexName: "alertssecuritestock_date_fin_alerte", tableName: "alerts_securite_stock")
	}

	changeSet(author: "rabbah (generated)", id: "1372323562567-15") {
		dropIndex(indexName: "alertssecuritestock_details", tableName: "alerts_securite_stock")
	}

	changeSet(author: "rabbah (generated)", id: "1372323562567-16") {
		dropIndex(indexName: "alertssecuritestock_stock_max", tableName: "alerts_securite_stock")
	}

	changeSet(author: "rabbah (generated)", id: "1372323562567-17") {
		dropIndex(indexName: "alertssecuritestock_stock_min", tableName: "alerts_securite_stock")
	}

	changeSet(author: "rabbah (generated)", id: "1372323562567-18") {
		dropIndex(indexName: "alertssecuritestock_stock_reel", tableName: "alerts_securite_stock")
	}

	changeSet(author: "rabbah (generated)", id: "1372323562567-19") {
		dropIndex(indexName: "alertssecuritestock_tjs_valide", tableName: "alerts_securite_stock")
	}

	changeSet(author: "rabbah (generated)", id: "1372323562567-20") {
		dropIndex(indexName: "boncommande_avec_retour", tableName: "bon_commande")
	}

	changeSet(author: "rabbah (generated)", id: "1372323562567-21") {
		dropIndex(indexName: "boncommande_bonpret", tableName: "bon_commande")
	}

	changeSet(author: "rabbah (generated)", id: "1372323562567-22") {
		dropIndex(indexName: "boncommande_date", tableName: "bon_commande")
	}

	changeSet(author: "rabbah (generated)", id: "1372323562567-23") {
		dropIndex(indexName: "boncommande_livree", tableName: "bon_commande")
	}

	changeSet(author: "rabbah (generated)", id: "1372323562567-24") {
		dropIndex(indexName: "boncommande_num_b_c", tableName: "bon_commande")
	}

	changeSet(author: "rabbah (generated)", id: "1372323562567-25") {
		dropIndex(indexName: "boncommande_paye", tableName: "bon_commande")
	}

	changeSet(author: "rabbah (generated)", id: "1372323562567-26") {
		dropIndex(indexName: "boncommande_reference", tableName: "bon_commande")
	}

	changeSet(author: "rabbah (generated)", id: "1372323562567-27") {
		dropIndex(indexName: "boncommande_retour_paye", tableName: "bon_commande")
	}

	changeSet(author: "rabbah (generated)", id: "1372323562567-28") {
		dropIndex(indexName: "boncommande_retournee", tableName: "bon_commande")
	}

	changeSet(author: "rabbah (generated)", id: "1372323562567-29") {
		dropIndex(indexName: "boncommande_totalttc_retour", tableName: "bon_commande")
	}

	changeSet(author: "rabbah (generated)", id: "1372323562567-30") {
		dropIndex(indexName: "boncommande_type", tableName: "bon_commande")
	}

	changeSet(author: "rabbah (generated)", id: "1372323562567-31") {
		dropIndex(indexName: "bonlivraison_date", tableName: "bon_livraison")
	}

	changeSet(author: "rabbah (generated)", id: "1372323562567-32") {
		dropIndex(indexName: "bonlivraison_num_b_l", tableName: "bon_livraison")
	}

	changeSet(author: "rabbah (generated)", id: "1372323562567-33") {
		dropIndex(indexName: "bonlivraison_reference", tableName: "bon_livraison")
	}

	changeSet(author: "rabbah (generated)", id: "1372323562567-34") {
		dropIndex(indexName: "bonlivraison_type", tableName: "bon_livraison")
	}

	changeSet(author: "rabbah (generated)", id: "1372323562567-35") {
		dropIndex(indexName: "bonlivriason_est_br", tableName: "bon_livraison")
	}

	changeSet(author: "rabbah (generated)", id: "1372323562567-36") {
		dropIndex(indexName: "bonpret_date", tableName: "bon_pret")
	}

	changeSet(author: "rabbah (generated)", id: "1372323562567-37") {
		dropIndex(indexName: "bonpret_montant_garantie", tableName: "bon_pret")
	}

	changeSet(author: "rabbah (generated)", id: "1372323562567-38") {
		dropIndex(indexName: "bonpret_num_b_p", tableName: "bon_pret")
	}

	changeSet(author: "rabbah (generated)", id: "1372323562567-39") {
		dropIndex(indexName: "bonpret_reference", tableName: "bon_pret")
	}

	changeSet(author: "rabbah (generated)", id: "1372323562567-40") {
		dropIndex(indexName: "bonpret_type", tableName: "bon_pret")
	}

	changeSet(author: "rabbah (generated)", id: "1372323562567-41") {
		dropIndex(indexName: "calendrier_nbhtpj", tableName: "calendrier")
	}

	changeSet(author: "rabbah (generated)", id: "1372323562567-42") {
		dropIndex(indexName: "calendrier_nbjtpm", tableName: "calendrier")
	}

	changeSet(author: "rabbah (generated)", id: "1372323562567-43") {
		dropIndex(indexName: "calendrier_nom", tableName: "calendrier")
	}

	changeSet(author: "rabbah (generated)", id: "1372323562567-44") {
		dropIndex(indexName: "categoriepartenaire_libelle", tableName: "categorie_partenaire")
	}

	changeSet(author: "rabbah (generated)", id: "1372323562567-45") {
		dropIndex(indexName: "categorieproduit_code", tableName: "categorie_produit")
	}

	changeSet(author: "rabbah (generated)", id: "1372323562567-46") {
		dropIndex(indexName: "categorieproduit_intitule", tableName: "categorie_produit")
	}

	changeSet(author: "rabbah (generated)", id: "1372323562567-47") {
		dropIndex(indexName: "chargedivers_cout", tableName: "charge_divers")
	}

	changeSet(author: "rabbah (generated)", id: "1372323562567-48") {
		dropIndex(indexName: "chargedivers_date", tableName: "charge_divers")
	}

	changeSet(author: "rabbah (generated)", id: "1372323562567-49") {
		dropIndex(indexName: "chargedivers_nom", tableName: "charge_divers")
	}

	changeSet(author: "rabbah (generated)", id: "1372323562567-50") {
		dropIndex(indexName: "cle_cleproduit", tableName: "cle")
	}

	changeSet(author: "rabbah (generated)", id: "1372323562567-51") {
		dropIndex(indexName: "cle_datefinactivation", tableName: "cle")
	}

	changeSet(author: "rabbah (generated)", id: "1372323562567-52") {
		dropIndex(indexName: "comptebancaire_code_swift", tableName: "compte_bancaire")
	}

	changeSet(author: "rabbah (generated)", id: "1372323562567-53") {
		dropIndex(indexName: "comptebancaire_libelle", tableName: "compte_bancaire")
	}

	changeSet(author: "rabbah (generated)", id: "1372323562567-54") {
		dropIndex(indexName: "comptebancaire_rib", tableName: "compte_bancaire")
	}

	changeSet(author: "rabbah (generated)", id: "1372323562567-55") {
		dropIndex(indexName: "comptebancaire_client_code_swift", tableName: "compte_bancaire_chora_client_info")
	}

	changeSet(author: "rabbah (generated)", id: "1372323562567-56") {
		dropIndex(indexName: "comptebancaire_client_libelle", tableName: "compte_bancaire_chora_client_info")
	}

	changeSet(author: "rabbah (generated)", id: "1372323562567-57") {
		dropIndex(indexName: "comptebancaire_client_rib", tableName: "compte_bancaire_chora_client_info")
	}

	changeSet(author: "rabbah (generated)", id: "1372323562567-58") {
		dropIndex(indexName: "comptecomptable_code", tableName: "compte_comptable")
	}

	changeSet(author: "rabbah (generated)", id: "1372323562567-59") {
		dropIndex(indexName: "comptecomptable_identifiant", tableName: "compte_comptable")
	}

	changeSet(author: "rabbah (generated)", id: "1372323562567-60") {
		dropIndex(indexName: "comptecomptable_nom", tableName: "compte_comptable")
	}

	changeSet(author: "rabbah (generated)", id: "1372323562567-61") {
		dropIndex(indexName: "comptecomptable_type", tableName: "compte_comptable")
	}

	changeSet(author: "rabbah (generated)", id: "1372323562567-62") {
		dropIndex(indexName: "comptecomptableproduit_type", tableName: "compte_comptable_produit")
	}

	changeSet(author: "rabbah (generated)", id: "1372323562567-63") {
		dropIndex(indexName: "contact_email", tableName: "contact")
	}

	changeSet(author: "rabbah (generated)", id: "1372323562567-64") {
		dropIndex(indexName: "contact_fixe", tableName: "contact")
	}

	changeSet(author: "rabbah (generated)", id: "1372323562567-65") {
		dropIndex(indexName: "contact_fonction", tableName: "contact")
	}

	changeSet(author: "rabbah (generated)", id: "1372323562567-66") {
		dropIndex(indexName: "contact_nom", tableName: "contact")
	}

	changeSet(author: "rabbah (generated)", id: "1372323562567-67") {
		dropIndex(indexName: "contact_portable", tableName: "contact")
	}

	changeSet(author: "rabbah (generated)", id: "1372323562567-68") {
		dropIndex(indexName: "contact_titre", tableName: "contact")
	}

	changeSet(author: "rabbah (generated)", id: "1372323562567-69") {
		dropIndex(indexName: "declarationtva_date", tableName: "declaration_tva")
	}

	changeSet(author: "rabbah (generated)", id: "1372323562567-70") {
		dropIndex(indexName: "declarationtva_exercise", tableName: "declaration_tva")
	}

	changeSet(author: "rabbah (generated)", id: "1372323562567-71") {
		dropIndex(indexName: "declarationtva_num_declaration", tableName: "declaration_tva")
	}

	changeSet(author: "rabbah (generated)", id: "1372323562567-72") {
		dropIndex(indexName: "declarationtva_periode", tableName: "declaration_tva")
	}

	changeSet(author: "rabbah (generated)", id: "1372323562567-73") {
		dropIndex(indexName: "depenses_date", tableName: "depenses")
	}

	changeSet(author: "rabbah (generated)", id: "1372323562567-74") {
		dropIndex(indexName: "depenses_detail", tableName: "depenses")
	}

	changeSet(author: "rabbah (generated)", id: "1372323562567-75") {
		dropIndex(indexName: "depenses_montant", tableName: "depenses")
	}

	changeSet(author: "rabbah (generated)", id: "1372323562567-76") {
		dropIndex(indexName: "devis_date", tableName: "devis")
	}

	changeSet(author: "rabbah (generated)", id: "1372323562567-77") {
		dropIndex(indexName: "devis_date_fin_validite", tableName: "devis")
	}

	changeSet(author: "rabbah (generated)", id: "1372323562567-78") {
		dropIndex(indexName: "devis_num_devis", tableName: "devis")
	}

	changeSet(author: "rabbah (generated)", id: "1372323562567-79") {
		dropIndex(indexName: "devis_reference", tableName: "devis")
	}

	changeSet(author: "rabbah (generated)", id: "1372323562567-80") {
		dropIndex(indexName: "devis_type", tableName: "devis")
	}

	changeSet(author: "rabbah (generated)", id: "1372323562567-81") {
		dropIndex(indexName: "devise_libelle", tableName: "devise")
	}

	changeSet(author: "rabbah (generated)", id: "1372323562567-82") {
		dropIndex(indexName: "devise_valeur", tableName: "devise")
	}

	changeSet(author: "rabbah (generated)", id: "1372323562567-83") {
		dropIndex(indexName: "echeance_code", tableName: "echeance")
	}

	changeSet(author: "rabbah (generated)", id: "1372323562567-84") {
		dropIndex(indexName: "echeance_libelle", tableName: "echeance")
	}

	changeSet(author: "rabbah (generated)", id: "1372323562567-85") {
		dropIndex(indexName: "echeance_periodicite", tableName: "echeance")
	}

	changeSet(author: "rabbah (generated)", id: "1372323562567-86") {
		dropIndex(indexName: "echeance_type_declanchement", tableName: "echeance")
	}

	changeSet(author: "rabbah (generated)", id: "1372323562567-87") {
		dropIndex(indexName: "ecriture_credit", tableName: "ecriture")
	}

	changeSet(author: "rabbah (generated)", id: "1372323562567-88") {
		dropIndex(indexName: "ecriture_date", tableName: "ecriture")
	}

	changeSet(author: "rabbah (generated)", id: "1372323562567-89") {
		dropIndex(indexName: "ecriture_date_creance", tableName: "ecriture")
	}

	changeSet(author: "rabbah (generated)", id: "1372323562567-90") {
		dropIndex(indexName: "ecriture_date_echeance", tableName: "ecriture")
	}

	changeSet(author: "rabbah (generated)", id: "1372323562567-91") {
		dropIndex(indexName: "ecriture_debit", tableName: "ecriture")
	}

	changeSet(author: "rabbah (generated)", id: "1372323562567-92") {
		dropIndex(indexName: "ecriture_description", tableName: "ecriture")
	}

	changeSet(author: "rabbah (generated)", id: "1372323562567-93") {
		dropIndex(indexName: "ecriture_montant_devise", tableName: "ecriture")
	}

	changeSet(author: "rabbah (generated)", id: "1372323562567-94") {
		dropIndex(indexName: "ecriture_numero", tableName: "ecriture")
	}

	changeSet(author: "rabbah (generated)", id: "1372323562567-95") {
		dropIndex(indexName: "ecriture_reference", tableName: "ecriture")
	}

	changeSet(author: "rabbah (generated)", id: "1372323562567-96") {
		dropIndex(indexName: "employe_nom", tableName: "employe")
	}

	changeSet(author: "rabbah (generated)", id: "1372323562567-97") {
		dropIndex(indexName: "employe_numero", tableName: "employe")
	}

	changeSet(author: "rabbah (generated)", id: "1372323562567-98") {
		dropIndex(indexName: "employe_prenom", tableName: "employe")
	}

	changeSet(author: "rabbah (generated)", id: "1372323562567-99") {
		dropIndex(indexName: "employe_salaire", tableName: "employe")
	}

	changeSet(author: "rabbah (generated)", id: "1372323562567-100") {
		dropIndex(indexName: "employe_taux_horaire", tableName: "employe")
	}

	changeSet(author: "rabbah (generated)", id: "1372323562567-101") {
		dropIndex(indexName: "employe_telephone", tableName: "employe")
	}

	changeSet(author: "rabbah (generated)", id: "1372323562567-102") {
		dropIndex(indexName: "entrepot_adresse", tableName: "entrepot")
	}

	changeSet(author: "rabbah (generated)", id: "1372323562567-103") {
		dropIndex(indexName: "entrepot_code", tableName: "entrepot")
	}

	changeSet(author: "rabbah (generated)", id: "1372323562567-104") {
		dropIndex(indexName: "entrepot_intitule", tableName: "entrepot")
	}

	changeSet(author: "rabbah (generated)", id: "1372323562567-105") {
		dropIndex(indexName: "entrepot_mode", tableName: "entrepot")
	}

	changeSet(author: "rabbah (generated)", id: "1372323562567-106") {
		dropIndex(indexName: "entrepot_mode_valorisation", tableName: "entrepot")
	}

	changeSet(author: "rabbah (generated)", id: "1372323562567-107") {
		dropIndex(indexName: "entrepot_tel", tableName: "entrepot")
	}

	changeSet(author: "rabbah (generated)", id: "1372323562567-108") {
		dropIndex(indexName: "exercice_date_debut", tableName: "exercice")
	}

	changeSet(author: "rabbah (generated)", id: "1372323562567-109") {
		dropIndex(indexName: "exercice_date_fin", tableName: "exercice")
	}

	changeSet(author: "rabbah (generated)", id: "1372323562567-110") {
		dropIndex(indexName: "exercice_intitule", tableName: "exercice")
	}

	changeSet(author: "rabbah (generated)", id: "1372323562567-111") {
		dropIndex(indexName: "facture_comptabilise", tableName: "facture")
	}

	changeSet(author: "rabbah (generated)", id: "1372323562567-112") {
		dropIndex(indexName: "facture_date", tableName: "facture")
	}

	changeSet(author: "rabbah (generated)", id: "1372323562567-113") {
		dropIndex(indexName: "facture_est_avoir", tableName: "facture")
	}

	changeSet(author: "rabbah (generated)", id: "1372323562567-114") {
		dropIndex(indexName: "facture_faitgenerateur", tableName: "facture")
	}

	changeSet(author: "rabbah (generated)", id: "1372323562567-115") {
		dropIndex(indexName: "facture_numero_facture", tableName: "facture")
	}

	changeSet(author: "rabbah (generated)", id: "1372323562567-116") {
		dropIndex(indexName: "facture_reference", tableName: "facture")
	}

	changeSet(author: "rabbah (generated)", id: "1372323562567-117") {
		dropIndex(indexName: "facture_type", tableName: "facture")
	}

	changeSet(author: "rabbah (generated)", id: "1372323562567-118") {
		dropIndex(indexName: "fichepresence_date", tableName: "fiche_presence")
	}

	changeSet(author: "rabbah (generated)", id: "1372323562567-119") {
		dropIndex(indexName: "fichepresence_duree_trajet", tableName: "fiche_presence")
	}

	changeSet(author: "rabbah (generated)", id: "1372323562567-120") {
		dropIndex(indexName: "fichepresence_heures_abscence", tableName: "fiche_presence")
	}

	changeSet(author: "rabbah (generated)", id: "1372323562567-121") {
		dropIndex(indexName: "fichepresence_heures_presence", tableName: "fiche_presence")
	}

	changeSet(author: "rabbah (generated)", id: "1372323562567-122") {
		dropIndex(indexName: "fichepresence_taux_horaire", tableName: "fiche_presence")
	}

	changeSet(author: "rabbah (generated)", id: "1372323562567-123") {
		dropIndex(indexName: "fonction_libelle", tableName: "fonction")
	}

	changeSet(author: "rabbah (generated)", id: "1372323562567-124") {
		dropIndex(indexName: "inventaire_details", tableName: "inventaire")
	}

	changeSet(author: "rabbah (generated)", id: "1372323562567-125") {
		dropIndex(indexName: "jourferier_annuel", tableName: "jour_ferier")
	}

	changeSet(author: "rabbah (generated)", id: "1372323562567-126") {
		dropIndex(indexName: "jourferier_date", tableName: "jour_ferier")
	}

	changeSet(author: "rabbah (generated)", id: "1372323562567-127") {
		dropIndex(indexName: "jourferier_duree", tableName: "jour_ferier")
	}

	changeSet(author: "rabbah (generated)", id: "1372323562567-128") {
		dropIndex(indexName: "jourferier_intitule", tableName: "jour_ferier")
	}

	changeSet(author: "rabbah (generated)", id: "1372323562567-129") {
		dropIndex(indexName: "journal_intitule", tableName: "journal")
	}

	changeSet(author: "rabbah (generated)", id: "1372323562567-130") {
		dropIndex(indexName: "ligneinventaire_qantite_actuele", tableName: "ligne_inventaire")
	}

	changeSet(author: "rabbah (generated)", id: "1372323562567-131") {
		dropIndex(indexName: "ligneinventaire_quantite_inv", tableName: "ligne_inventaire")
	}

	changeSet(author: "rabbah (generated)", id: "1372323562567-132") {
		dropIndex(indexName: "ligneproduit_prix", tableName: "ligne_produit")
	}

	changeSet(author: "rabbah (generated)", id: "1372323562567-133") {
		dropIndex(indexName: "ligneproduit_prix_deduit", tableName: "ligne_produit")
	}

	changeSet(author: "rabbah (generated)", id: "1372323562567-134") {
		dropIndex(indexName: "ligneproduit_quantite", tableName: "ligne_produit")
	}

	changeSet(author: "rabbah (generated)", id: "1372323562567-135") {
		dropIndex(indexName: "ligneproduit_quantite_livree", tableName: "ligne_produit")
	}

	changeSet(author: "rabbah (generated)", id: "1372323562567-136") {
		dropIndex(indexName: "ligneproduit_quantite_retournee", tableName: "ligne_produit")
	}

	changeSet(author: "rabbah (generated)", id: "1372323562567-137") {
		dropIndex(indexName: "ligneproduit_remise", tableName: "ligne_produit")
	}

	changeSet(author: "rabbah (generated)", id: "1372323562567-138") {
		dropIndex(indexName: "ligneproduit_sequence_ligne", tableName: "ligne_produit")
	}

	changeSet(author: "rabbah (generated)", id: "1372323562567-139") {
		dropIndex(indexName: "ligneproduit_type", tableName: "ligne_produit")
	}

	changeSet(author: "rabbah (generated)", id: "1372323562567-140") {
		dropIndex(indexName: "livraison_date", tableName: "livraison")
	}

	changeSet(author: "rabbah (generated)", id: "1372323562567-141") {
		dropIndex(indexName: "livraison_frais", tableName: "livraison")
	}

	changeSet(author: "rabbah (generated)", id: "1372323562567-142") {
		dropIndex(indexName: "livraison_nbr_colis", tableName: "livraison")
	}

	changeSet(author: "rabbah (generated)", id: "1372323562567-143") {
		dropIndex(indexName: "livraison_num_expedition", tableName: "livraison")
	}

	changeSet(author: "rabbah (generated)", id: "1372323562567-144") {
		dropIndex(indexName: "livraison_poid_total", tableName: "livraison")
	}

	changeSet(author: "rabbah (generated)", id: "1372323562567-145") {
		dropIndex(indexName: "locationoutil_date", tableName: "location_outil")
	}

	changeSet(author: "rabbah (generated)", id: "1372323562567-247") {
		dropIndex(indexName: "locationoutil_dlocation", tableName: "location_outil")
	}

	changeSet(author: "rabbah (generated)", id: "1372323562567-248") {
		dropIndex(indexName: "locationoutil_pforfait", tableName: "location_outil")
	}

	changeSet(author: "rabbah (generated)", id: "1372323562567-249") {
		dropIndex(indexName: "locationoutil_phlocation", tableName: "location_outil")
	}

	changeSet(author: "rabbah (generated)", id: "1372323562567-250") {
		dropIndex(indexName: "loueur_adresse", tableName: "loueur")
	}

	changeSet(author: "rabbah (generated)", id: "1372323562567-251") {
		dropIndex(indexName: "loueur_code_postal", tableName: "loueur")
	}

	changeSet(author: "rabbah (generated)", id: "1372323562567-252") {
		dropIndex(indexName: "loueur_email", tableName: "loueur")
	}

	changeSet(author: "rabbah (generated)", id: "1372323562567-253") {
		dropIndex(indexName: "loueur_fax", tableName: "loueur")
	}

	changeSet(author: "rabbah (generated)", id: "1372323562567-254") {
		dropIndex(indexName: "loueur_nom", tableName: "loueur")
	}

	changeSet(author: "rabbah (generated)", id: "1372323562567-255") {
		dropIndex(indexName: "loueur_numero", tableName: "loueur")
	}

	changeSet(author: "rabbah (generated)", id: "1372323562567-256") {
		dropIndex(indexName: "loueur_telephone", tableName: "loueur")
	}

	changeSet(author: "rabbah (generated)", id: "1372323562567-257") {
		dropIndex(indexName: "loueur_ville", tableName: "loueur")
	}

	changeSet(author: "rabbah (generated)", id: "1372323562567-258") {
		dropIndex(indexName: "messagedocument_entete", tableName: "message_document")
	}

	changeSet(author: "rabbah (generated)", id: "1372323562567-259") {
		dropIndex(indexName: "messagedocument_pied", tableName: "message_document")
	}

	changeSet(author: "rabbah (generated)", id: "1372323562567-260") {
		dropIndex(indexName: "messagedocument_type", tableName: "message_document")
	}

	changeSet(author: "rabbah (generated)", id: "1372323562567-261") {
		dropIndex(indexName: "messagedocument_type_mouvement", tableName: "message_document")
	}

	changeSet(author: "rabbah (generated)", id: "1372323562567-262") {
		dropIndex(indexName: "modelivraison_code", tableName: "mode_livraison")
	}

	changeSet(author: "rabbah (generated)", id: "1372323562567-263") {
		dropIndex(indexName: "modelivraison_paiement_a_la_reception", tableName: "mode_livraison")
	}

	changeSet(author: "rabbah (generated)", id: "1372323562567-264") {
		dropIndex(indexName: "modelivraison_transporteur", tableName: "mode_livraison")
	}

	changeSet(author: "rabbah (generated)", id: "1372323562567-265") {
		dropIndex(indexName: "modereglement_code", tableName: "mode_reglement")
	}

	changeSet(author: "rabbah (generated)", id: "1372323562567-266") {
		dropIndex(indexName: "modereglement_libelle", tableName: "mode_reglement")
	}

	changeSet(author: "rabbah (generated)", id: "1372323562567-267") {
		dropIndex(indexName: "moderelancepaiement_code", tableName: "mode_relance_paiement")
	}

	changeSet(author: "rabbah (generated)", id: "1372323562567-268") {
		dropIndex(indexName: "moderelancepaiement_corps_message", tableName: "mode_relance_paiement")
	}

	changeSet(author: "rabbah (generated)", id: "1372323562567-269") {
		dropIndex(indexName: "moderelancepaiement_frequence", tableName: "mode_relance_paiement")
	}

	changeSet(author: "rabbah (generated)", id: "1372323562567-270") {
		dropIndex(indexName: "moderelancepaiement_libelle", tableName: "mode_relance_paiement")
	}

	changeSet(author: "rabbah (generated)", id: "1372323562567-271") {
		dropIndex(indexName: "mouvementstock_date", tableName: "mouvement_stock")
	}

	changeSet(author: "rabbah (generated)", id: "1372323562567-272") {
		dropIndex(indexName: "mouvementstock_quantite", tableName: "mouvement_stock")
	}

	changeSet(author: "rabbah (generated)", id: "1372323562567-273") {
		dropIndex(indexName: "mouvementstock_type_mouvement", tableName: "mouvement_stock")
	}

	changeSet(author: "rabbah (generated)", id: "1372323562567-274") {
		dropIndex(indexName: "nature_libelle", tableName: "nature")
	}

	changeSet(author: "rabbah (generated)", id: "1372323562567-275") {
		dropIndex(indexName: "naturedecharge_charge_basic", tableName: "nature_de_charge")
	}

	changeSet(author: "rabbah (generated)", id: "1372323562567-276") {
		dropIndex(indexName: "naturedecharge_libelle", tableName: "nature_de_charge")
	}

	changeSet(author: "rabbah (generated)", id: "1372323562567-277") {
		dropIndex(indexName: "naturedecharge_type", tableName: "nature_de_charge")
	}

	changeSet(author: "rabbah (generated)", id: "1372323562567-278") {
		dropIndex(indexName: "note_corps", tableName: "note")
	}

	changeSet(author: "rabbah (generated)", id: "1372323562567-279") {
		dropIndex(indexName: "note_date", tableName: "note")
	}

	changeSet(author: "rabbah (generated)", id: "1372323562567-280") {
		dropIndex(indexName: "note_objet", tableName: "note")
	}

	changeSet(author: "rabbah (generated)", id: "1372323562567-281") {
		dropIndex(indexName: "outilemploye_cout_horaire", tableName: "outil_employe")
	}

	changeSet(author: "rabbah (generated)", id: "1372323562567-282") {
		dropIndex(indexName: "outilemploye_nom", tableName: "outil_employe")
	}

	changeSet(author: "rabbah (generated)", id: "1372323562567-283") {
		dropIndex(indexName: "outilemploye_numero", tableName: "outil_employe")
	}

	changeSet(author: "rabbah (generated)", id: "1372323562567-284") {
		dropIndex(indexName: "outillouable_designation", tableName: "outil_louable")
	}

	changeSet(author: "rabbah (generated)", id: "1372323562567-285") {
		dropIndex(indexName: "outillouable_prix_heure_location", tableName: "outil_louable")
	}

	changeSet(author: "rabbah (generated)", id: "1372323562567-286") {
		dropIndex(indexName: "outillagecollectif_date_achat", tableName: "outillage_collectif")
	}

	changeSet(author: "rabbah (generated)", id: "1372323562567-287") {
		dropIndex(indexName: "outillagecollectif_date_fin_utilisation", tableName: "outillage_collectif")
	}

	changeSet(author: "rabbah (generated)", id: "1372323562567-288") {
		dropIndex(indexName: "outillagecollectif_date_mise_en_service", tableName: "outillage_collectif")
	}

	changeSet(author: "rabbah (generated)", id: "1372323562567-289") {
		dropIndex(indexName: "outillagecollectif_designation", tableName: "outillage_collectif")
	}

	changeSet(author: "rabbah (generated)", id: "1372323562567-290") {
		dropIndex(indexName: "outillagecollectif_numero", tableName: "outillage_collectif")
	}

	changeSet(author: "rabbah (generated)", id: "1372323562567-291") {
		dropIndex(indexName: "outillagecollectif_prix_achat", tableName: "outillage_collectif")
	}

	changeSet(author: "rabbah (generated)", id: "1372323562567-292") {
		dropIndex(indexName: "paiement_credit", tableName: "paiement")
	}

	changeSet(author: "rabbah (generated)", id: "1372323562567-293") {
		dropIndex(indexName: "paiement_date", tableName: "paiement")
	}

	changeSet(author: "rabbah (generated)", id: "1372323562567-294") {
		dropIndex(indexName: "paiement_date_encaissement", tableName: "paiement")
	}

	changeSet(author: "rabbah (generated)", id: "1372323562567-295") {
		dropIndex(indexName: "paiement_montant_paye", tableName: "paiement")
	}

	changeSet(author: "rabbah (generated)", id: "1372323562567-296") {
		dropIndex(indexName: "paiement_num_transaction", tableName: "paiement")
	}

	changeSet(author: "rabbah (generated)", id: "1372323562567-297") {
		dropIndex(indexName: "paiementjbl_annee", tableName: "paiement_jbl")
	}

	changeSet(author: "rabbah (generated)", id: "1372323562567-298") {
		dropIndex(indexName: "paiementjbl_avance", tableName: "paiement_jbl")
	}

	changeSet(author: "rabbah (generated)", id: "1372323562567-299") {
		dropIndex(indexName: "paiementjbl_date_avance", tableName: "paiement_jbl")
	}

	changeSet(author: "rabbah (generated)", id: "1372323562567-300") {
		dropIndex(indexName: "paiementjbl_date_salaire", tableName: "paiement_jbl")
	}

	changeSet(author: "rabbah (generated)", id: "1372323562567-301") {
		dropIndex(indexName: "paiementjbl_frais_deplacement", tableName: "paiement_jbl")
	}

	changeSet(author: "rabbah (generated)", id: "1372323562567-302") {
		dropIndex(indexName: "paiementjbl_heures_supp", tableName: "paiement_jbl")
	}

	changeSet(author: "rabbah (generated)", id: "1372323562567-303") {
		dropIndex(indexName: "paiementjbl_jours_abscences", tableName: "paiement_jbl")
	}

	changeSet(author: "rabbah (generated)", id: "1372323562567-304") {
		dropIndex(indexName: "paiementjbl_jours_travailles", tableName: "paiement_jbl")
	}

	changeSet(author: "rabbah (generated)", id: "1372323562567-305") {
		dropIndex(indexName: "paiementjbl_mois", tableName: "paiement_jbl")
	}

	changeSet(author: "rabbah (generated)", id: "1372323562567-306") {
		dropIndex(indexName: "paiementjbl_rappel", tableName: "paiement_jbl")
	}

	changeSet(author: "rabbah (generated)", id: "1372323562567-307") {
		dropIndex(indexName: "paiementjbl_retenu_abscence", tableName: "paiement_jbl")
	}

	changeSet(author: "rabbah (generated)", id: "1372323562567-308") {
		dropIndex(indexName: "paiementjbl_retenu_vetement", tableName: "paiement_jbl")
	}

	changeSet(author: "rabbah (generated)", id: "1372323562567-309") {
		dropIndex(indexName: "paiementjbl_salaire", tableName: "paiement_jbl")
	}

	changeSet(author: "rabbah (generated)", id: "1372323562567-310") {
		dropIndex(indexName: "partenaire_adresse", tableName: "partenaire")
	}

	changeSet(author: "rabbah (generated)", id: "1372323562567-311") {
		dropIndex(indexName: "partenaire_adresse_facturation", tableName: "partenaire")
	}

	changeSet(author: "rabbah (generated)", id: "1372323562567-312") {
		dropIndex(indexName: "partenaire_adresse_livraison", tableName: "partenaire")
	}

	changeSet(author: "rabbah (generated)", id: "1372323562567-313") {
		dropIndex(indexName: "partenaire_cnss", tableName: "partenaire")
	}

	changeSet(author: "rabbah (generated)", id: "1372323562567-314") {
		dropIndex(indexName: "partenaire_code", tableName: "partenaire")
	}

	changeSet(author: "rabbah (generated)", id: "1372323562567-315") {
		dropIndex(indexName: "partenaire_code_postal", tableName: "partenaire")
	}

	changeSet(author: "rabbah (generated)", id: "1372323562567-316") {
		dropIndex(indexName: "partenaire_email", tableName: "partenaire")
	}

	changeSet(author: "rabbah (generated)", id: "1372323562567-317") {
		dropIndex(indexName: "partenaire_est_client", tableName: "partenaire")
	}

	changeSet(author: "rabbah (generated)", id: "1372323562567-318") {
		dropIndex(indexName: "partenaire_est_en_liste_noire", tableName: "partenaire")
	}

	changeSet(author: "rabbah (generated)", id: "1372323562567-319") {
		dropIndex(indexName: "partenaire_est_fournisseur", tableName: "partenaire")
	}

	changeSet(author: "rabbah (generated)", id: "1372323562567-320") {
		dropIndex(indexName: "partenaire_est_particulier", tableName: "partenaire")
	}

	changeSet(author: "rabbah (generated)", id: "1372323562567-321") {
		dropIndex(indexName: "partenaire_fax", tableName: "partenaire")
	}

	changeSet(author: "rabbah (generated)", id: "1372323562567-322") {
		dropIndex(indexName: "partenaire_id_fiscal", tableName: "partenaire")
	}

	changeSet(author: "rabbah (generated)", id: "1372323562567-323") {
		dropIndex(indexName: "partenaire_patente", tableName: "partenaire")
	}

	changeSet(author: "rabbah (generated)", id: "1372323562567-324") {
		dropIndex(indexName: "partenaire_raison_sociale", tableName: "partenaire")
	}

	changeSet(author: "rabbah (generated)", id: "1372323562567-325") {
		dropIndex(indexName: "partenaire_registre_commerce", tableName: "partenaire")
	}

	changeSet(author: "rabbah (generated)", id: "1372323562567-326") {
		dropIndex(indexName: "partenaire_site_web", tableName: "partenaire")
	}

	changeSet(author: "rabbah (generated)", id: "1372323562567-327") {
		dropIndex(indexName: "partenaire_telephone", tableName: "partenaire")
	}

	changeSet(author: "rabbah (generated)", id: "1372323562567-328") {
		dropIndex(indexName: "paterncompteur_libelle", tableName: "patern_compteur")
	}

	changeSet(author: "rabbah (generated)", id: "1372323562567-329") {
		dropIndex(indexName: "paterncompteur_numero_suivant", tableName: "patern_compteur")
	}

	changeSet(author: "rabbah (generated)", id: "1372323562567-330") {
		dropIndex(indexName: "paterncompteur_pas", tableName: "patern_compteur")
	}

	changeSet(author: "rabbah (generated)", id: "1372323562567-331") {
		dropIndex(indexName: "paterncompteur_prefixe", tableName: "patern_compteur")
	}

	changeSet(author: "rabbah (generated)", id: "1372323562567-332") {
		dropIndex(indexName: "paterncompteur_remplissage", tableName: "patern_compteur")
	}

	changeSet(author: "rabbah (generated)", id: "1372323562567-333") {
		dropIndex(indexName: "paterncompteur_suffixe", tableName: "patern_compteur")
	}

	changeSet(author: "rabbah (generated)", id: "1372323562567-334") {
		dropIndex(indexName: "paterncompteur_type", tableName: "patern_compteur")
	}

	changeSet(author: "rabbah (generated)", id: "1372323562567-335") {
		dropIndex(indexName: "pays_intitule", tableName: "pays")
	}

	changeSet(author: "rabbah (generated)", id: "1372323562567-336") {
		dropIndex(indexName: "periode_intitule", tableName: "periode")
	}

	changeSet(author: "rabbah (generated)", id: "1372323562567-337") {
		dropIndex(indexName: "phase_avancement_prevu", tableName: "phase")
	}

	changeSet(author: "rabbah (generated)", id: "1372323562567-338") {
		dropIndex(indexName: "phase_avancement_reel", tableName: "phase")
	}

	changeSet(author: "rabbah (generated)", id: "1372323562567-339") {
		dropIndex(indexName: "phase_charge_prevue", tableName: "phase")
	}

	changeSet(author: "rabbah (generated)", id: "1372323562567-340") {
		dropIndex(indexName: "phase_charge_relle", tableName: "phase")
	}

	changeSet(author: "rabbah (generated)", id: "1372323562567-341") {
		dropIndex(indexName: "phase_date_debut_prevue", tableName: "phase")
	}

	changeSet(author: "rabbah (generated)", id: "1372323562567-342") {
		dropIndex(indexName: "phase_date_debut_relle", tableName: "phase")
	}

	changeSet(author: "rabbah (generated)", id: "1372323562567-343") {
		dropIndex(indexName: "phase_date_fin_prevue", tableName: "phase")
	}

	changeSet(author: "rabbah (generated)", id: "1372323562567-344") {
		dropIndex(indexName: "phase_date_fin_reelle", tableName: "phase")
	}

	changeSet(author: "rabbah (generated)", id: "1372323562567-345") {
		dropIndex(indexName: "phase_designation", tableName: "phase")
	}

	changeSet(author: "rabbah (generated)", id: "1372323562567-346") {
		dropIndex(indexName: "produit_code", tableName: "produit")
	}

	changeSet(author: "rabbah (generated)", id: "1372323562567-347") {
		dropIndex(indexName: "produit_codebarre", tableName: "produit")
	}

	changeSet(author: "rabbah (generated)", id: "1372323562567-348") {
		dropIndex(indexName: "produit_designation", tableName: "produit")
	}

	changeSet(author: "rabbah (generated)", id: "1372323562567-349") {
		dropIndex(indexName: "produit_poids", tableName: "produit")
	}

	changeSet(author: "rabbah (generated)", id: "1372323562567-350") {
		dropIndex(indexName: "produit_prix_achat", tableName: "produit")
	}

	changeSet(author: "rabbah (generated)", id: "1372323562567-351") {
		dropIndex(indexName: "produit_prix_moyen_pendere", tableName: "produit")
	}

	changeSet(author: "rabbah (generated)", id: "1372323562567-352") {
		dropIndex(indexName: "produit_prix_revient", tableName: "produit")
	}

	changeSet(author: "rabbah (generated)", id: "1372323562567-353") {
		dropIndex(indexName: "produit_prix_vente_standard", tableName: "produit")
	}

	changeSet(author: "rabbah (generated)", id: "1372323562567-354") {
		dropIndex(indexName: "projet_adresse_projet", tableName: "projet")
	}

	changeSet(author: "rabbah (generated)", id: "1372323562567-355") {
		dropIndex(indexName: "projet_avancement_prevu", tableName: "projet")
	}

	changeSet(author: "rabbah (generated)", id: "1372323562567-356") {
		dropIndex(indexName: "projet_avancement_reel", tableName: "projet")
	}

	changeSet(author: "rabbah (generated)", id: "1372323562567-357") {
		dropIndex(indexName: "projet_charge_prevue", tableName: "projet")
	}

	changeSet(author: "rabbah (generated)", id: "1372323562567-358") {
		dropIndex(indexName: "projet_charge_reelle", tableName: "projet")
	}

	changeSet(author: "rabbah (generated)", id: "1372323562567-359") {
		dropIndex(indexName: "projet_code_postal", tableName: "projet")
	}

	changeSet(author: "rabbah (generated)", id: "1372323562567-360") {
		dropIndex(indexName: "projet_date_debut_prevu", tableName: "projet")
	}

	changeSet(author: "rabbah (generated)", id: "1372323562567-361") {
		dropIndex(indexName: "projet_date_debut_reelle", tableName: "projet")
	}

	changeSet(author: "rabbah (generated)", id: "1372323562567-362") {
		dropIndex(indexName: "projet_date_fin_prevue", tableName: "projet")
	}

	changeSet(author: "rabbah (generated)", id: "1372323562567-363") {
		dropIndex(indexName: "projet_date_fin_reelle", tableName: "projet")
	}

	changeSet(author: "rabbah (generated)", id: "1372323562567-364") {
		dropIndex(indexName: "projet_email", tableName: "projet")
	}

	changeSet(author: "rabbah (generated)", id: "1372323562567-365") {
		dropIndex(indexName: "projet_fax", tableName: "projet")
	}

	changeSet(author: "rabbah (generated)", id: "1372323562567-366") {
		dropIndex(indexName: "projet_montant_bon_commande", tableName: "projet")
	}

	changeSet(author: "rabbah (generated)", id: "1372323562567-367") {
		dropIndex(indexName: "projet_nom", tableName: "projet")
	}

	changeSet(author: "rabbah (generated)", id: "1372323562567-368") {
		dropIndex(indexName: "projet_nom_contact", tableName: "projet")
	}

	changeSet(author: "rabbah (generated)", id: "1372323562567-369") {
		dropIndex(indexName: "projet_numero", tableName: "projet")
	}

	changeSet(author: "rabbah (generated)", id: "1372323562567-370") {
		dropIndex(indexName: "projet_telephone", tableName: "projet")
	}

	changeSet(author: "rabbah (generated)", id: "1372323562567-371") {
		dropIndex(indexName: "projet_ville", tableName: "projet")
	}

	changeSet(author: "rabbah (generated)", id: "1372323562567-372") {
		dropIndex(indexName: "regimedeclarationtva_fait_generateur", tableName: "regime_declaration_tva")
	}

	changeSet(author: "rabbah (generated)", id: "1372323562567-373") {
		dropIndex(indexName: "regimedeclarationtva_libelle", tableName: "regime_declaration_tva")
	}

	changeSet(author: "rabbah (generated)", id: "1372323562567-374") {
		dropIndex(indexName: "regimedeclarationtva_periodicite", tableName: "regime_declaration_tva")
	}

	changeSet(author: "rabbah (generated)", id: "1372323562567-375") {
		dropIndex(indexName: "regimetva_libelle", tableName: "regimetva")
	}

	changeSet(author: "rabbah (generated)", id: "1372323562567-376") {
		dropIndex(indexName: "regimetva_taux", tableName: "regimetva")
	}

	changeSet(author: "rabbah (generated)", id: "1372323562567-377") {
		dropIndex(indexName: "reglefacturationautomatique_libelle", tableName: "regle_facturation_automatique")
	}

	changeSet(author: "rabbah (generated)", id: "1372323562567-378") {
		dropIndex(indexName: "reglefacturationautomatique_nombre_bla_regroupe", tableName: "regle_facturation_automatique")
	}

	changeSet(author: "rabbah (generated)", id: "1372323562567-379") {
		dropIndex(indexName: "reglefacturationautomatique_priorite", tableName: "regle_facturation_automatique")
	}

	changeSet(author: "rabbah (generated)", id: "1372323562567-380") {
		dropIndex(indexName: "reglefacturationautomatique_type", tableName: "regle_facturation_automatique")
	}

	changeSet(author: "rabbah (generated)", id: "1372323562567-381") {
		dropIndex(indexName: "regleprix_active", tableName: "regle_prix")
	}

	changeSet(author: "rabbah (generated)", id: "1372323562567-382") {
		dropIndex(indexName: "regleprix_arrondir_apres_virguel", tableName: "regle_prix")
	}

	changeSet(author: "rabbah (generated)", id: "1372323562567-383") {
		dropIndex(indexName: "regleprix_code", tableName: "regle_prix")
	}

	changeSet(author: "rabbah (generated)", id: "1372323562567-384") {
		dropIndex(indexName: "regleprix_date_debut", tableName: "regle_prix")
	}

	changeSet(author: "rabbah (generated)", id: "1372323562567-385") {
		dropIndex(indexName: "regleprix_date_fin", tableName: "regle_prix")
	}

	changeSet(author: "rabbah (generated)", id: "1372323562567-386") {
		dropIndex(indexName: "regleprix_libelle", tableName: "regle_prix")
	}

	changeSet(author: "rabbah (generated)", id: "1372323562567-387") {
		dropIndex(indexName: "regleprix_operateur_comparaison", tableName: "regle_prix")
	}

	changeSet(author: "rabbah (generated)", id: "1372323562567-388") {
		dropIndex(indexName: "regleprix_priorite", tableName: "regle_prix")
	}

	changeSet(author: "rabbah (generated)", id: "1372323562567-389") {
		dropIndex(indexName: "regleprix_quantite_max", tableName: "regle_prix")
	}

	changeSet(author: "rabbah (generated)", id: "1372323562567-390") {
		dropIndex(indexName: "regleprix_quantite_min", tableName: "regle_prix")
	}

	changeSet(author: "rabbah (generated)", id: "1372323562567-391") {
		dropIndex(indexName: "regleprix_sense_arrondissement", tableName: "regle_prix")
	}

	changeSet(author: "rabbah (generated)", id: "1372323562567-392") {
		dropIndex(indexName: "regleprix_seuil_arrondissement", tableName: "regle_prix")
	}

	changeSet(author: "rabbah (generated)", id: "1372323562567-393") {
		dropIndex(indexName: "regleprix_taux_remise", tableName: "regle_prix")
	}

	changeSet(author: "rabbah (generated)", id: "1372323562567-394") {
		dropIndex(indexName: "regleprix_type_prixde_base", tableName: "regle_prix")
	}

	changeSet(author: "rabbah (generated)", id: "1372323562567-395") {
		dropIndex(indexName: "regleprix_typeregle", tableName: "regle_prix")
	}

	changeSet(author: "rabbah (generated)", id: "1372323562567-396") {
		dropIndex(indexName: "regleprix_valeur_fixe", tableName: "regle_prix")
	}

	changeSet(author: "rabbah (generated)", id: "1372323562567-397") {
		dropIndex(indexName: "securitestock_stock_max", tableName: "securitestock")
	}

	changeSet(author: "rabbah (generated)", id: "1372323562567-398") {
		dropIndex(indexName: "securitestock_stock_min", tableName: "securitestock")
	}

	changeSet(author: "rabbah (generated)", id: "1372323562567-399") {
		dropIndex(indexName: "securitestock_stock_reel", tableName: "securitestock")
	}

	changeSet(author: "rabbah (generated)", id: "1372323562567-400") {
		dropIndex(indexName: "soustraitance_cout", tableName: "soustraitance")
	}

	changeSet(author: "rabbah (generated)", id: "1372323562567-401") {
		dropIndex(indexName: "soustraitance_date", tableName: "soustraitance")
	}

	changeSet(author: "rabbah (generated)", id: "1372323562567-402") {
		dropIndex(indexName: "soustraitance_nom", tableName: "soustraitance")
	}

	changeSet(author: "rabbah (generated)", id: "1372323562567-403") {
		dropIndex(indexName: "soustraitant_adresse", tableName: "soustraitant")
	}

	changeSet(author: "rabbah (generated)", id: "1372323562567-404") {
		dropIndex(indexName: "soustraitant_code_postal", tableName: "soustraitant")
	}

	changeSet(author: "rabbah (generated)", id: "1372323562567-405") {
		dropIndex(indexName: "soustraitant_email", tableName: "soustraitant")
	}

	changeSet(author: "rabbah (generated)", id: "1372323562567-406") {
		dropIndex(indexName: "soustraitant_fax", tableName: "soustraitant")
	}

	changeSet(author: "rabbah (generated)", id: "1372323562567-407") {
		dropIndex(indexName: "soustraitant_nom", tableName: "soustraitant")
	}

	changeSet(author: "rabbah (generated)", id: "1372323562567-408") {
		dropIndex(indexName: "soustraitant_numero", tableName: "soustraitant")
	}

	changeSet(author: "rabbah (generated)", id: "1372323562567-409") {
		dropIndex(indexName: "soustraitant_telephone", tableName: "soustraitant")
	}

	changeSet(author: "rabbah (generated)", id: "1372323562567-410") {
		dropIndex(indexName: "soustraitant_ville", tableName: "soustraitant")
	}

	changeSet(author: "rabbah (generated)", id: "1372323562567-411") {
		dropIndex(indexName: "tache_avancement_prevu", tableName: "tache")
	}

	changeSet(author: "rabbah (generated)", id: "1372323562567-412") {
		dropIndex(indexName: "tache_avancement_reel", tableName: "tache")
	}

	changeSet(author: "rabbah (generated)", id: "1372323562567-413") {
		dropIndex(indexName: "tache_charge_prevue", tableName: "tache")
	}

	changeSet(author: "rabbah (generated)", id: "1372323562567-414") {
		dropIndex(indexName: "tache_charge_reelle", tableName: "tache")
	}

	changeSet(author: "rabbah (generated)", id: "1372323562567-415") {
		dropIndex(indexName: "tache_date_debut_prevue", tableName: "tache")
	}

	changeSet(author: "rabbah (generated)", id: "1372323562567-416") {
		dropIndex(indexName: "tache_date_debut_reelle", tableName: "tache")
	}

	changeSet(author: "rabbah (generated)", id: "1372323562567-417") {
		dropIndex(indexName: "tache_date_fin_prevu", tableName: "tache")
	}

	changeSet(author: "rabbah (generated)", id: "1372323562567-418") {
		dropIndex(indexName: "tache_date_fin_reelle", tableName: "tache")
	}

	changeSet(author: "rabbah (generated)", id: "1372323562567-419") {
		dropIndex(indexName: "tache_designation", tableName: "tache")
	}

	changeSet(author: "rabbah (generated)", id: "1372323562567-420") {
		dropIndex(indexName: "tache_jalon", tableName: "tache")
	}

	changeSet(author: "rabbah (generated)", id: "1372323562567-421") {
		dropIndex(indexName: "tache_numero", tableName: "tache")
	}

	changeSet(author: "rabbah (generated)", id: "1372323562567-422") {
		dropIndex(indexName: "unitemesure_libelle", tableName: "unite_mesure")
	}

	changeSet(author: "rabbah (generated)", id: "1372323562567-423") {
		dropIndex(indexName: "ville_intitule", tableName: "ville")
	}

	changeSet(author: "rabbah (generated)", id: "1372323562567-424") {
		dropIndex(indexName: "zone_code", tableName: "zone")
	}

	changeSet(author: "rabbah (generated)", id: "1372323562567-425") {
		dropIndex(indexName: "zone_intitule", tableName: "zone")
	}
}
