databaseChangeLog = {

    changeSet(author: "rabbah (generated)", id: "1371166194608-1") {
        createTable(tableName: "affectation_outil") {
            column(name: "id", type: "int8") {
                constraints(nullable: "false", primaryKey: "true", primaryKeyName: "affectation_outil_pkey")
            }
            column(name: "version", type: "int8") {
                constraints(nullable: "false")
            }
            column(name: "cout_horaire", type: "FLOAT8(17)") {
                constraints(nullable: "false")
            }
            column(name: "date", type: "TIMESTAMP WITH TIME ZONE") {
                constraints(nullable: "false")
            }
            column(name: "employe_id", type: "int8") {
                constraints(nullable: "false")
            }
            column(name: "heures_entretien", type: "int4") {
                constraints(nullable: "false")
            }
            column(name: "heures_utilisation", type: "int4") {
                constraints(nullable: "false")
            }
            column(name: "outil_employe_id", type: "int8") {
                constraints(nullable: "false")
            }
            column(name: "projet_id", type: "int8") {
                constraints(nullable: "false")
            }
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-2") {
        createTable(tableName: "affectation_outillage") {
            column(name: "id", type: "int8") {
                constraints(nullable: "false", primaryKey: "true", primaryKeyName: "affectation_outillage_pkey")
            }
            column(name: "version", type: "int8") {
                constraints(nullable: "false")
            }
            column(name: "date_affectation", type: "TIMESTAMP WITH TIME ZONE") {
                constraints(nullable: "false")
            }
            column(name: "date_fin_utilisation_outillage", type: "TIMESTAMP WITH TIME ZONE")

            column(name: "employe_id", type: "int8") {
                constraints(nullable: "false")
            }

            column(name: "outillage_collectif_id", type: "int8") {
                constraints(nullable: "false")
            }

            column(name: "projet_id", type: "int8") {
                constraints(nullable: "false")
            }
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-3") {
        createTable(tableName: "affectation_produit") {
            column(name: "id", type: "int8") {
                constraints(nullable: "false", primaryKey: "true", primaryKeyName: "affectation_produit_pkey")
            }

            column(name: "version", type: "int8") {
                constraints(nullable: "false")
            }

            column(name: "bon_livraison_id", type: "int8")

            column(name: "date", type: "TIMESTAMP WITH TIME ZONE") {
                constraints(nullable: "false")
            }

            column(name: "prix_unitaire", type: "FLOAT8(17)") {
                constraints(nullable: "false")
            }

            column(name: "produit_id", type: "int8") {
                constraints(nullable: "false")
            }

            column(name: "projet_id", type: "int8") {
                constraints(nullable: "false")
            }

            column(name: "quantite", type: "int4") {
                constraints(nullable: "false")
            }
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-4") {
        createTable(tableName: "agence_bancaire") {
            column(name: "id", type: "int8") {
                constraints(nullable: "false", primaryKey: "true", primaryKeyName: "agence_bancaire_pkey")
            }

            column(name: "version", type: "int8") {
                constraints(nullable: "false")
            }

            column(name: "adresse", type: "VARCHAR(255)")

            column(name: "nom", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }

            column(name: "tel", type: "VARCHAR(255)")
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-5") {
        createTable(tableName: "alerts_securite_stock") {
            column(name: "id", type: "int8") {
                constraints(nullable: "false", primaryKey: "true", primaryKeyName: "alerts_securite_stock_pkey")
            }

            column(name: "version", type: "int8") {
                constraints(nullable: "false")
            }

            column(name: "date_alerte", type: "TIMESTAMP WITH TIME ZONE") {
                constraints(nullable: "false")
            }

            column(name: "date_fin_alerte", type: "TIMESTAMP WITH TIME ZONE")

            column(name: "details", type: "VARCHAR(1000)")

            column(name: "raison_declanchement_id", type: "int8") {
                constraints(nullable: "false")
            }

            column(name: "raison_relachement_id", type: "int8")

            column(name: "stock_max", type: "FLOAT8(17)") {
                constraints(nullable: "false")
            }

            column(name: "stock_min", type: "FLOAT8(17)") {
                constraints(nullable: "false")
            }

            column(name: "stock_reel", type: "FLOAT8(17)") {
                constraints(nullable: "false")
            }

            column(name: "tjs_valide", type: "bool") {
                constraints(nullable: "false")
            }
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-6") {
        createTable(tableName: "audit_log") {
            column(name: "id", type: "int8") {
                constraints(nullable: "false", primaryKey: "true", primaryKeyName: "audit_log_pkey")
            }

            column(name: "actor", type: "VARCHAR(255)")

            column(name: "class_name", type: "VARCHAR(255)")

            column(name: "date_created", type: "TIMESTAMP WITH TIME ZONE") {
                constraints(nullable: "false")
            }

            column(name: "event_name", type: "VARCHAR(255)")

            column(name: "last_updated", type: "TIMESTAMP WITH TIME ZONE") {
                constraints(nullable: "false")
            }

            column(name: "new_value", type: "VARCHAR(255)")

            column(name: "old_value", type: "VARCHAR(255)")

            column(name: "persisted_object_id", type: "VARCHAR(255)")

            column(name: "persisted_object_version", type: "int8")

            column(name: "property_name", type: "VARCHAR(255)")

            column(name: "uri", type: "VARCHAR(255)")
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-7") {
        createTable(tableName: "bon_commande") {
            column(name: "id", type: "int8") {
                constraints(nullable: "false", primaryKey: "true", primaryKeyName: "bon_commande_pkey")
            }

            column(name: "version", type: "int8") {
                constraints(nullable: "false")
            }

            column(name: "avec_retour", type: "bool") {
                constraints(nullable: "false")
            }

            column(name: "bon_pret", type: "bool") {
                constraints(nullable: "false")
            }

            column(name: "date", type: "TIMESTAMP WITH TIME ZONE") {
                constraints(nullable: "false")
            }

            column(name: "facture", type: "bool") {
                constraints(nullable: "false")
            }

            column(name: "livree", type: "bool") {
                constraints(nullable: "false")
            }

            column(name: "numbc", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }

            column(name: "partenaire_id", type: "int8") {
                constraints(nullable: "false")
            }

            column(name: "paye", type: "bool") {
                constraints(nullable: "false")
            }

            column(name: "reference", type: "VARCHAR(255)")

            column(name: "retour_paye", type: "bool") {
                constraints(nullable: "false")
            }

            column(name: "retournee", type: "bool") {
                constraints(nullable: "false")
            }

            column(name: "totalttc_retour", type: "FLOAT8(17)") {
                constraints(nullable: "false")
            }

            column(name: "type", type: "VARCHAR(5)") {
                constraints(nullable: "false")
            }

            column(name: "utilisateur_id", type: "int8") {
                constraints(nullable: "false")
            }
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-8") {
        createTable(tableName: "bon_commande_devis") {
            column(name: "bon_commande_deviss_id", type: "int8")

            column(name: "devis_id", type: "int8")
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-9") {
        createTable(tableName: "bon_commande_ligne_produit") {
            column(name: "bon_commande_ligne_produits_id", type: "int8")

            column(name: "ligne_produit_id", type: "int8")
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-10") {
        createTable(tableName: "bon_livraison") {
            column(name: "id", type: "int8") {
                constraints(nullable: "false", primaryKey: "true", primaryKeyName: "bon_livraison_pkey")
            }

            column(name: "version", type: "int8") {
                constraints(nullable: "false")
            }

            column(name: "bon_commande_id", type: "int8") {
                constraints(nullable: "false")
            }

            column(name: "date", type: "TIMESTAMP WITH TIME ZONE") {
                constraints(nullable: "false")
            }

            column(name: "est_bon_retour", type: "bool") {
                constraints(nullable: "false")
            }

            column(name: "livraison_id", type: "int8")

            column(name: "numbl", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }

            column(name: "reference", type: "VARCHAR(255)")

            column(name: "type", type: "VARCHAR(5)") {
                constraints(nullable: "false")
            }
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-11") {
        createTable(tableName: "bon_livraison_ligne_produit") {
            column(name: "bon_livraison_ligne_produits_id", type: "int8")

            column(name: "ligne_produit_id", type: "int8")
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-12") {
        createTable(tableName: "bon_pret") {
            column(name: "id", type: "int8") {
                constraints(nullable: "false", primaryKey: "true", primaryKeyName: "bon_pret_pkey")
            }

            column(name: "version", type: "int8") {
                constraints(nullable: "false")
            }

            column(name: "bon_commande_id", type: "int8")

            column(name: "date", type: "TIMESTAMP WITH TIME ZONE") {
                constraints(nullable: "false")
            }

            column(name: "montant_garantie", type: "FLOAT8(17)") {
                constraints(nullable: "false")
            }

            column(name: "numbp", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }

            column(name: "partenaire_id", type: "int8") {
                constraints(nullable: "false")
            }

            column(name: "reference", type: "VARCHAR(255)")

            column(name: "type", type: "VARCHAR(5)") {
                constraints(nullable: "false")
            }

            column(name: "utilisateur_id", type: "int8") {
                constraints(nullable: "false")
            }
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-13") {
        createTable(tableName: "bon_pret_devis") {
            column(name: "bon_pret_deviss_id", type: "int8")

            column(name: "devis_id", type: "int8")
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-14") {
        createTable(tableName: "bon_pret_ligne_produit") {
            column(name: "bon_pret_ligne_produits_id", type: "int8")

            column(name: "ligne_produit_id", type: "int8")
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-15") {
        createTable(tableName: "calendrier") {
            column(name: "id", type: "int8") {
                constraints(nullable: "false", primaryKey: "true", primaryKeyName: "calendrier_pkey")
            }

            column(name: "version", type: "int8") {
                constraints(nullable: "false")
            }

            column(name: "nb_heures_travail_par_jour", type: "int4") {
                constraints(nullable: "false")
            }

            column(name: "nb_jours_travail_par_mois", type: "int4") {
                constraints(nullable: "false")
            }

            column(name: "nom", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-16") {
        createTable(tableName: "calendrier_jour_ferier") {
            column(name: "calendrier_jour_feriers_id", type: "int8")

            column(name: "jour_ferier_id", type: "int8")
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-17") {
        createTable(tableName: "categorie_partenaire") {
            column(name: "id", type: "int8") {
                constraints(nullable: "false", primaryKey: "true", primaryKeyName: "categorie_partenaire_pkey")
            }

            column(name: "version", type: "int8") {
                constraints(nullable: "false")
            }

            column(name: "categorie_parente_id", type: "int8")

            column(name: "libelle", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-18") {
        createTable(tableName: "categorie_produit") {
            column(name: "id", type: "int8") {
                constraints(nullable: "false", primaryKey: "true", primaryKeyName: "categorie_produit_pkey")
            }

            column(name: "version", type: "int8") {
                constraints(nullable: "false")
            }

            column(name: "categorie_parente_id", type: "int8")

            column(name: "code", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }

            column(name: "intitule", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-19") {
        createTable(tableName: "charge_divers") {
            column(name: "id", type: "int8") {
                constraints(nullable: "false", primaryKey: "true", primaryKeyName: "charge_divers_pkey")
            }

            column(name: "version", type: "int8") {
                constraints(nullable: "false")
            }

            column(name: "cout", type: "FLOAT8(17)") {
                constraints(nullable: "false")
            }

            column(name: "date", type: "TIMESTAMP WITH TIME ZONE") {
                constraints(nullable: "false")
            }

            column(name: "nom", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }

            column(name: "projet_id", type: "int8") {
                constraints(nullable: "false")
            }
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-20") {
        createTable(tableName: "chora_barrage") {
            column(name: "id", type: "int8") {
                constraints(nullable: "false", primaryKey: "true", primaryKeyName: "chora_barrage_pkey")
            }

            column(name: "version", type: "int8") {
                constraints(nullable: "false")
            }

            column(name: "date_instanciation", type: "TIMESTAMP WITH TIME ZONE") {
                constraints(nullable: "false")
            }

            column(name: "demo", type: "bool") {
                constraints(nullable: "false")
            }

            column(name: "dernier_acces", type: "TIMESTAMP WITH TIME ZONE") {
                constraints(nullable: "false")
            }

            column(name: "id_instance", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }

            column(name: "premier_acces", type: "bool") {
                constraints(nullable: "false")
            }

            column(name: "produit", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }

            column(name: "raison_social", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }

            column(name: "version_produit", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-21") {
        createTable(tableName: "chora_client_info") {
            column(name: "id", type: "int8") {
                constraints(nullable: "false", primaryKey: "true", primaryKeyName: "chora_client_info_pkey")
            }

            column(name: "version", type: "int8") {
                constraints(nullable: "false")
            }

            column(name: "adresse", type: "VARCHAR(255)")

            column(name: "cachet_data", type: "bytea")

            column(name: "cnss", type: "VARCHAR(255)")

            column(name: "code_postale", type: "VARCHAR(255)")

            column(name: "copie_data", type: "bytea")

            column(name: "email", type: "VARCHAR(255)")

            column(name: "entetepied_data", type: "bytea")

            column(name: "fax", type: "VARCHAR(255)")

            column(name: "forme_juridique", type: "VARCHAR(255)")

            column(name: "idf", type: "VARCHAR(255)")

            column(name: "logo_data", type: "bytea") {
                constraints(nullable: "false")
            }

            column(name: "patente", type: "VARCHAR(255)")

            column(name: "pays", type: "VARCHAR(255)")

            column(name: "raison_sociale", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }

            column(name: "rc", type: "VARCHAR(255)")

            column(name: "regime_id", type: "int8") {
                constraints(nullable: "false")
            }

            column(name: "repertoir_backup", type: "VARCHAR(255)")

            column(name: "site", type: "VARCHAR(255)")

            column(name: "telephone", type: "VARCHAR(255)")

            column(name: "ville", type: "VARCHAR(255)")
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-22") {
        createTable(tableName: "chora_client_info_module") {
            column(name: "chora_client_info_modules_actifs_id", type: "int8")

            column(name: "module_id", type: "int8")
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-23") {
        createTable(tableName: "cle") {
            column(name: "id", type: "int8") {
                constraints(nullable: "false", primaryKey: "true", primaryKeyName: "cle_pkey")
            }

            column(name: "version", type: "int8") {
                constraints(nullable: "false")
            }

            column(name: "cle_produit", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }

            column(name: "date_activation", type: "TIMESTAMP WITH TIME ZONE") {
                constraints(nullable: "false")
            }

            column(name: "date_fin_activation", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-24") {
        createTable(tableName: "compte_bancaire") {
            column(name: "id", type: "int8") {
                constraints(nullable: "false", primaryKey: "true", primaryKeyName: "compte_bancaire_pkey")
            }

            column(name: "version", type: "int8") {
                constraints(nullable: "false")
            }

            column(name: "agence_bancaire_id", type: "int8") {
                constraints(nullable: "false")
            }

            column(name: "code_swift", type: "VARCHAR(255)")

            column(name: "libelle", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }

            column(name: "partenaire_id", type: "int8") {
                constraints(nullable: "false")
            }

            column(name: "rib", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-25") {
        createTable(tableName: "compte_bancaire_chora_client_info") {
            column(name: "id", type: "int8") {
                constraints(nullable: "false", primaryKey: "true", primaryKeyName: "compte_bancaire_chora_client_info_pkey")
            }

            column(name: "version", type: "int8") {
                constraints(nullable: "false")
            }

            column(name: "agence_bancaire_id", type: "int8") {
                constraints(nullable: "false")
            }

            column(name: "code_swift", type: "VARCHAR(255)")

            column(name: "libelle", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }

            column(name: "rib", type: "VARCHAR(255)")

            column(name: "solde", type: "FLOAT8(17)") {
                constraints(nullable: "false")
            }

            column(name: "solde_prevu", type: "FLOAT8(17)") {
                constraints(nullable: "false")
            }
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-26") {
        createTable(tableName: "compte_comptable") {
            column(name: "id", type: "int8") {
                constraints(nullable: "false", primaryKey: "true", primaryKeyName: "compte_comptable_pkey")
            }

            column(name: "version", type: "int8") {
                constraints(nullable: "false")
            }

            column(name: "code", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }

            column(name: "identifiant", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }

            column(name: "nom", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }

            column(name: "parent_id", type: "int8")

            column(name: "type", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-27") {
        createTable(tableName: "compte_comptable_produit") {
            column(name: "id", type: "int8") {
                constraints(nullable: "false", primaryKey: "true", primaryKeyName: "compte_comptable_produit_pkey")
            }

            column(name: "version", type: "int8") {
                constraints(nullable: "false")
            }

            column(name: "categorie_produit_id", type: "int8") {
                constraints(nullable: "false")
            }

            column(name: "compte_comptable_id", type: "int8") {
                constraints(nullable: "false")
            }

            column(name: "type", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-28") {
        createTable(tableName: "contact") {
            column(name: "id", type: "int8") {
                constraints(nullable: "false", primaryKey: "true", primaryKeyName: "contact_pkey")
            }

            column(name: "version", type: "int8") {
                constraints(nullable: "false")
            }

            column(name: "email", type: "VARCHAR(255)")

            column(name: "fixe", type: "VARCHAR(255)")

            column(name: "fonction", type: "VARCHAR(255)")

            column(name: "nom", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }

            column(name: "partenaire_id", type: "int8")

            column(name: "portable", type: "VARCHAR(255)")

            column(name: "titre", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-29") {
        createTable(tableName: "declaration_tva") {
            column(name: "id", type: "int8") {
                constraints(nullable: "false", primaryKey: "true", primaryKeyName: "declaration_tva_pkey")
            }

            column(name: "version", type: "int8") {
                constraints(nullable: "false")
            }

            column(name: "date", type: "TIMESTAMP WITH TIME ZONE") {
                constraints(nullable: "false")
            }

            column(name: "exercise", type: "int4") {
                constraints(nullable: "false")
            }

            column(name: "num_declaration", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }

            column(name: "periode", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-30") {
        createTable(tableName: "declaration_tva_facture") {
            column(name: "declaration_tva_factures_id", type: "int8")

            column(name: "facture_id", type: "int8")
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-31") {
        createTable(tableName: "declaration_tva_paiement") {
            column(name: "declaration_tva_paiements_id", type: "int8")

            column(name: "paiement_id", type: "int8")
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-32") {
        createTable(tableName: "depenses") {
            column(name: "id", type: "int8") {
                constraints(nullable: "false", primaryKey: "true", primaryKeyName: "depenses_pkey")
            }

            column(name: "version", type: "int8") {
                constraints(nullable: "false")
            }

            column(name: "date", type: "TIMESTAMP WITH TIME ZONE") {
                constraints(nullable: "false")
            }

            column(name: "detail", type: "VARCHAR(255)")

            column(name: "montant", type: "FLOAT8(17)")

            column(name: "nature_de_charge_id", type: "int8") {
                constraints(nullable: "false")
            }
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-33") {
        createTable(tableName: "devis") {
            column(name: "id", type: "int8") {
                constraints(nullable: "false", primaryKey: "true", primaryKeyName: "devis_pkey")
            }

            column(name: "version", type: "int8") {
                constraints(nullable: "false")
            }

            column(name: "date", type: "TIMESTAMP WITH TIME ZONE") {
                constraints(nullable: "false")
            }

            column(name: "date_fin_validite", type: "TIMESTAMP WITH TIME ZONE")

            column(name: "num_devis", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }

            column(name: "partenaire_id", type: "int8") {
                constraints(nullable: "false")
            }

            column(name: "reference", type: "VARCHAR(255)")

            column(name: "type", type: "VARCHAR(5)") {
                constraints(nullable: "false")
            }
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-34") {
        createTable(tableName: "devis_ligne_produit") {
            column(name: "devis_ligne_produits_id", type: "int8")

            column(name: "ligne_produit_id", type: "int8")
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-35") {
        createTable(tableName: "devise") {
            column(name: "id", type: "int8") {
                constraints(nullable: "false", primaryKey: "true", primaryKeyName: "devise_pkey")
            }

            column(name: "version", type: "int8") {
                constraints(nullable: "false")
            }

            column(name: "libelle", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }

            column(name: "valeur", type: "FLOAT8(17)") {
                constraints(nullable: "false")
            }
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-36") {
        createTable(tableName: "document_numerique") {
            column(name: "id", type: "int8") {
                constraints(nullable: "false", primaryKey: "true", primaryKeyName: "document_numerique_pkey")
            }

            column(name: "version", type: "int8") {
                constraints(nullable: "false")
            }

            column(name: "donnees", type: "bytea")

            column(name: "titre", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }

            column(name: "type", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-37") {
        createTable(tableName: "droit_utilisateur") {
            column(name: "id", type: "int8") {
                constraints(nullable: "false", primaryKey: "true", primaryKeyName: "droit_utilisateur_pkey")
            }

            column(name: "version", type: "int8") {
                constraints(nullable: "false")
            }

            column(name: "description", type: "VARCHAR(255)")

            column(name: "droit", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }

            column(name: "parent_id", type: "int8")
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-38") {
        createTable(tableName: "echeance") {
            column(name: "id", type: "int8") {
                constraints(nullable: "false", primaryKey: "true", primaryKeyName: "echeance_pkey")
            }

            column(name: "version", type: "int8") {
                constraints(nullable: "false")
            }

            column(name: "code", type: "int4") {
                constraints(nullable: "false")
            }

            column(name: "libelle", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }

            column(name: "periodicite", type: "int4") {
                constraints(nullable: "false")
            }

            column(name: "type_declanchement", type: "VARCHAR(16)") {
                constraints(nullable: "false")
            }
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-39") {
        createTable(tableName: "ecriture") {
            column(name: "id", type: "int8") {
                constraints(nullable: "false", primaryKey: "true", primaryKeyName: "ecriture_pkey")
            }

            column(name: "version", type: "int8") {
                constraints(nullable: "false")
            }

            column(name: "compte_comptable_id", type: "int8") {
                constraints(nullable: "false")
            }

            column(name: "credit", type: "FLOAT8(17)")

            column(name: "date", type: "TIMESTAMP WITH TIME ZONE") {
                constraints(nullable: "false")
            }

            column(name: "date_creance", type: "TIMESTAMP WITH TIME ZONE")

            column(name: "date_echeance", type: "TIMESTAMP WITH TIME ZONE")

            column(name: "debit", type: "FLOAT8(17)")

            column(name: "description", type: "VARCHAR(255)")

            column(name: "devise_id", type: "int8")

            column(name: "ligne_produit_id", type: "int8")

            column(name: "montant_devise", type: "FLOAT8(17)")

            column(name: "numero", type: "int4") {
                constraints(nullable: "false")
            }

            column(name: "paiement_id", type: "int8")

            column(name: "periode_id", type: "int8") {
                constraints(nullable: "false")
            }

            column(name: "reference", type: "VARCHAR(255)")
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-40") {
        createTable(tableName: "employe") {
            column(name: "id", type: "int8") {
                constraints(nullable: "false", primaryKey: "true", primaryKeyName: "employe_pkey")
            }

            column(name: "version", type: "int8") {
                constraints(nullable: "false")
            }

            column(name: "calendrier_id", type: "int8")

            column(name: "fonction_id", type: "int8") {
                constraints(nullable: "false")
            }

            column(name: "nom", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }

            column(name: "numero", type: "int4") {
                constraints(nullable: "false")
            }

            column(name: "prenom", type: "VARCHAR(255)")

            column(name: "salaire", type: "FLOAT8(17)") {
                constraints(nullable: "false")
            }

            column(name: "taux_horaire", type: "FLOAT8(17)") {
                constraints(nullable: "false")
            }

            column(name: "telephone", type: "VARCHAR(255)")
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-41") {
        createTable(tableName: "encrypted_data") {
            column(name: "id", type: "VARCHAR(32)") {
                constraints(nullable: "false", primaryKey: "true", primaryKeyName: "encrypted_data_pkey")
            }

            column(name: "version", type: "int8") {
                constraints(nullable: "false")
            }

            column(name: "data_item", type: "VARCHAR(512)") {
                constraints(nullable: "false")
            }
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-42") {
        createTable(tableName: "entrepot") {
            column(name: "id", type: "int8") {
                constraints(nullable: "false", primaryKey: "true", primaryKeyName: "entrepot_pkey")
            }

            column(name: "version", type: "int8") {
                constraints(nullable: "false")
            }

            column(name: "adresse", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }

            column(name: "code", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }

            column(name: "intitule", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }

            column(name: "mode", type: "VARCHAR(4)")

            column(name: "mode_valorisation", type: "VARCHAR(18)") {
                constraints(nullable: "false")
            }

            column(name: "tel", type: "VARCHAR(255)")
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-43") {
        createTable(tableName: "exercice") {
            column(name: "id", type: "int8") {
                constraints(nullable: "false", primaryKey: "true", primaryKeyName: "exercice_pkey")
            }

            column(name: "version", type: "int8") {
                constraints(nullable: "false")
            }

            column(name: "date_debut", type: "TIMESTAMP WITH TIME ZONE") {
                constraints(nullable: "false")
            }

            column(name: "date_fin", type: "TIMESTAMP WITH TIME ZONE") {
                constraints(nullable: "false")
            }

            column(name: "intitule", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-44") {
        createTable(tableName: "facture") {
            column(name: "id", type: "int8") {
                constraints(nullable: "false", primaryKey: "true", primaryKeyName: "facture_pkey")
            }

            column(name: "version", type: "int8") {
                constraints(nullable: "false")
            }

            column(name: "comptabilise", type: "bool") {
                constraints(nullable: "false")
            }

            column(name: "date", type: "TIMESTAMP WITH TIME ZONE") {
                constraints(nullable: "false")
            }

            column(name: "est_avoir", type: "bool") {
                constraints(nullable: "false")
            }

            column(name: "faitgenerateur", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }

            column(name: "numero_facture", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }

            column(name: "partenaire_id", type: "int8") {
                constraints(nullable: "false")
            }

            column(name: "reference", type: "VARCHAR(255)")

            column(name: "type", type: "VARCHAR(5)") {
                constraints(nullable: "false")
            }
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-45") {
        createTable(tableName: "facture_bon_commande") {
            column(name: "facture_bon_commandes_id", type: "int8")

            column(name: "bon_commande_id", type: "int8")
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-46") {
        createTable(tableName: "facture_paiement") {
            column(name: "facture_paiements_id", type: "int8")

            column(name: "paiement_id", type: "int8")
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-47") {
        createTable(tableName: "fiche_presence") {
            column(name: "id", type: "int8") {
                constraints(nullable: "false", primaryKey: "true", primaryKeyName: "fiche_presence_pkey")
            }

            column(name: "version", type: "int8") {
                constraints(nullable: "false")
            }

            column(name: "date", type: "TIMESTAMP WITH TIME ZONE") {
                constraints(nullable: "false")
            }

            column(name: "duree_trajet", type: "FLOAT8(17)") {
                constraints(nullable: "false")
            }

            column(name: "employe_id", type: "int8") {
                constraints(nullable: "false")
            }

            column(name: "heures_abscence", type: "FLOAT8(17)") {
                constraints(nullable: "false")
            }

            column(name: "heures_presence", type: "FLOAT8(17)") {
                constraints(nullable: "false")
            }

            column(name: "projet_id", type: "int8") {
                constraints(nullable: "false")
            }

            column(name: "taux_horaire", type: "FLOAT8(17)") {
                constraints(nullable: "false")
            }
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-48") {
        createTable(tableName: "fonction") {
            column(name: "id", type: "int8") {
                constraints(nullable: "false", primaryKey: "true", primaryKeyName: "fonction_pkey")
            }

            column(name: "version", type: "int8") {
                constraints(nullable: "false")
            }

            column(name: "libelle", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-49") {
        createTable(tableName: "forme_juridique") {
            column(name: "id", type: "int8") {
                constraints(nullable: "false", primaryKey: "true", primaryKeyName: "forme_juridique_pkey")
            }

            column(name: "version", type: "int8") {
                constraints(nullable: "false")
            }

            column(name: "libelle", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-50") {
        createTable(tableName: "groupe_utilisateur") {
            column(name: "id", type: "int8") {
                constraints(nullable: "false", primaryKey: "true", primaryKeyName: "groupe_utilisateur_pkey")
            }

            column(name: "version", type: "int8") {
                constraints(nullable: "false")
            }

            column(name: "description", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }

            column(name: "intitule", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-51") {
        createTable(tableName: "groupe_utilisateur_droit_utilisateur") {
            column(name: "groupe_utilisateur_droits_id", type: "int8")

            column(name: "droit_utilisateur_id", type: "int8")
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-52") {
        createTable(tableName: "inventaire") {
            column(name: "id", type: "int8") {
                constraints(nullable: "false", primaryKey: "true", primaryKeyName: "inventaire_pkey")
            }

            column(name: "version", type: "int8") {
                constraints(nullable: "false")
            }

            column(name: "date_debut", type: "TIMESTAMP WITH TIME ZONE") {
                constraints(nullable: "false")
            }

            column(name: "date_fin", type: "TIMESTAMP WITH TIME ZONE")

            column(name: "details", type: "VARCHAR(1000)")

            column(name: "entrepot_id", type: "int8") {
                constraints(nullable: "false")
            }

            column(name: "etat", type: "VARCHAR(8)") {
                constraints(nullable: "false")
            }
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-53") {
        createTable(tableName: "inventaire_ligne_inventaire") {
            column(name: "inventaire_ligne_inventaires_id", type: "int8")

            column(name: "ligne_inventaire_id", type: "int8")
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-54") {
        createTable(tableName: "jour_ferier") {
            column(name: "id", type: "int8") {
                constraints(nullable: "false", primaryKey: "true", primaryKeyName: "jour_ferier_pkey")
            }

            column(name: "version", type: "int8") {
                constraints(nullable: "false")
            }

            column(name: "annuel", type: "bool") {
                constraints(nullable: "false")
            }

            column(name: "date", type: "TIMESTAMP WITH TIME ZONE") {
                constraints(nullable: "false")
            }

            column(name: "duree", type: "int4") {
                constraints(nullable: "false")
            }

            column(name: "intitule", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-55") {
        createTable(tableName: "journal") {
            column(name: "id", type: "int8") {
                constraints(nullable: "false", primaryKey: "true", primaryKeyName: "journal_pkey")
            }

            column(name: "version", type: "int8") {
                constraints(nullable: "false")
            }

            column(name: "intitule", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-56") {
        createTable(tableName: "journal_compte_comptable") {
            column(name: "journal_compte_comptables_id", type: "int8") {
                constraints(nullable: "false")
            }

            column(name: "compte_comptable_id", type: "int8")
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-57") {
        createTable(tableName: "ligne_inventaire") {
            column(name: "id", type: "int8") {
                constraints(nullable: "false", primaryKey: "true", primaryKeyName: "ligne_inventaire_pkey")
            }

            column(name: "version", type: "int8") {
                constraints(nullable: "false")
            }

            column(name: "produit_id", type: "int8") {
                constraints(nullable: "false")
            }

            column(name: "qantite_actuele", type: "FLOAT8(17)") {
                constraints(nullable: "false")
            }

            column(name: "quantite_inv", type: "FLOAT8(17)") {
                constraints(nullable: "false")
            }

            column(name: "date_preremption", type: "TIMESTAMP WITH TIME ZONE")
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-58") {
        createTable(tableName: "ligne_produit") {
            column(name: "id", type: "int8") {
                constraints(nullable: "false", primaryKey: "true", primaryKeyName: "ligne_produit_pkey")
            }

            column(name: "version", type: "int8") {
                constraints(nullable: "false")
            }

            column(name: "entrepot_id", type: "int8")

            column(name: "prix", type: "FLOAT8(17)") {
                constraints(nullable: "false")
            }

            column(name: "prix_deduit", type: "FLOAT8(17)") {
                constraints(nullable: "false")
            }

            column(name: "produit_id", type: "int8") {
                constraints(nullable: "false")
            }

            column(name: "quantite", type: "FLOAT8(17)") {
                constraints(nullable: "false")
            }

            column(name: "quantite_livree", type: "FLOAT8(17)") {
                constraints(nullable: "false")
            }

            column(name: "quantite_retournee", type: "FLOAT8(17)") {
                constraints(nullable: "false")
            }

            column(name: "remise", type: "FLOAT8(17)") {
                constraints(nullable: "false")
            }

            column(name: "sequence_ligne", type: "int4") {
                constraints(nullable: "false")
            }

            column(name: "type", type: "VARCHAR(5)") {
                constraints(nullable: "false")
            }

            column(name: "date_preremption", type: "TIMESTAMP WITH TIME ZONE")
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-59") {
        createTable(tableName: "livraison") {
            column(name: "id", type: "int8") {
                constraints(nullable: "false", primaryKey: "true", primaryKeyName: "livraison_pkey")
            }

            column(name: "version", type: "int8") {
                constraints(nullable: "false")
            }

            column(name: "date", type: "TIMESTAMP WITH TIME ZONE") {
                constraints(nullable: "false")
            }

            column(name: "detail", type: "VARCHAR(255)")

            column(name: "frais", type: "FLOAT8(17)") {
                constraints(nullable: "false")
            }

            column(name: "mode_livraison_id", type: "int8") {
                constraints(nullable: "false")
            }

            column(name: "nbr_colis", type: "int4") {
                constraints(nullable: "false")
            }

            column(name: "num_expedition", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }

            column(name: "partenaire_id", type: "int8")

            column(name: "poid_total", type: "FLOAT8(17)") {
                constraints(nullable: "false")
            }
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-60") {
        createTable(tableName: "location_outil") {
            column(name: "id", type: "int8") {
                constraints(nullable: "false", primaryKey: "true", primaryKeyName: "location_outil_pkey")
            }

            column(name: "version", type: "int8") {
                constraints(nullable: "false")
            }

            column(name: "date", type: "TIMESTAMP WITH TIME ZONE") {
                constraints(nullable: "false")
            }

            column(name: "duree_location", type: "int4") {
                constraints(nullable: "false")
            }

            column(name: "loueur_id", type: "int8") {
                constraints(nullable: "false")
            }

            column(name: "outil_louable_id", type: "int8") {
                constraints(nullable: "false")
            }

            column(name: "prix_forfait", type: "FLOAT8(17)") {
                constraints(nullable: "false")
            }

            column(name: "prix_heure_location", type: "FLOAT8(17)") {
                constraints(nullable: "false")
            }

            column(name: "projet_id", type: "int8") {
                constraints(nullable: "false")
            }
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-61") {
        createTable(tableName: "loueur") {
            column(name: "id", type: "int8") {
                constraints(nullable: "false", primaryKey: "true", primaryKeyName: "loueur_pkey")
            }

            column(name: "version", type: "int8") {
                constraints(nullable: "false")
            }

            column(name: "adresse", type: "VARCHAR(255)")

            column(name: "code_postal", type: "VARCHAR(255)")

            column(name: "email", type: "VARCHAR(255)")

            column(name: "fax", type: "VARCHAR(255)")

            column(name: "nom", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }

            column(name: "numero", type: "int4") {
                constraints(nullable: "false")
            }

            column(name: "telephone", type: "VARCHAR(255)")

            column(name: "ville", type: "VARCHAR(255)")
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-62") {
        createTable(tableName: "message_document") {
            column(name: "id", type: "int8") {
                constraints(nullable: "false", primaryKey: "true", primaryKeyName: "message_document_pkey")
            }

            column(name: "version", type: "int8") {
                constraints(nullable: "false")
            }

            column(name: "entete", type: "VARCHAR(6000)")

            column(name: "pied", type: "VARCHAR(6000)")

            column(name: "type", type: "VARCHAR(13)") {
                constraints(nullable: "false")
            }

            column(name: "type_mouvement", type: "VARCHAR(5)") {
                constraints(nullable: "false")
            }
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-63") {
        createTable(tableName: "mode_livraison") {
            column(name: "id", type: "int8") {
                constraints(nullable: "false", primaryKey: "true", primaryKeyName: "mode_livraison_pkey")
            }

            column(name: "version", type: "int8") {
                constraints(nullable: "false")
            }

            column(name: "code", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }

            column(name: "paiementala_reception", type: "bool") {
                constraints(nullable: "false")
            }

            column(name: "transporteur", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-64") {
        createTable(tableName: "mode_reglement") {
            column(name: "id", type: "int8") {
                constraints(nullable: "false", primaryKey: "true", primaryKeyName: "mode_reglement_pkey")
            }

            column(name: "version", type: "int8") {
                constraints(nullable: "false")
            }

            column(name: "code", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }

            column(name: "libelle", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-65") {
        createTable(tableName: "mode_relance_paiement") {
            column(name: "id", type: "int8") {
                constraints(nullable: "false", primaryKey: "true", primaryKeyName: "mode_relance_paiement_pkey")
            }

            column(name: "version", type: "int8") {
                constraints(nullable: "false")
            }

            column(name: "code", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }

            column(name: "corps_message", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }

            column(name: "frequence", type: "int4") {
                constraints(nullable: "false")
            }

            column(name: "libelle", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-66") {
        createTable(tableName: "module") {
            column(name: "id", type: "int8") {
                constraints(nullable: "false", primaryKey: "true", primaryKeyName: "module_pkey")
            }

            column(name: "version", type: "int8") {
                constraints(nullable: "false")
            }

            column(name: "code", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }

            column(name: "intitule", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-67") {
        createTable(tableName: "mouvement_stock") {
            column(name: "id", type: "int8") {
                constraints(nullable: "false", primaryKey: "true", primaryKeyName: "mouvement_stock_pkey")
            }

            column(name: "version", type: "int8") {
                constraints(nullable: "false")
            }

            column(name: "date", type: "TIMESTAMP WITH TIME ZONE") {
                constraints(nullable: "false")
            }

            column(name: "emp_destination_id", type: "int8")

            column(name: "emp_source_id", type: "int8")

            column(name: "produit_id", type: "int8") {
                constraints(nullable: "false")
            }

            column(name: "quantite", type: "FLOAT8(17)") {
                constraints(nullable: "false")
            }

            column(name: "type_mouvement", type: "VARCHAR(20)") {
                constraints(nullable: "false")
            }

            column(name: "utilisateur_id", type: "int8") {
                constraints(nullable: "false")
            }

            column(name: "date_preremption", type: "TIMESTAMP WITH TIME ZONE")

            column(name: "prix_deduit_mvm", type: "FLOAT8(17)")
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-68") {
        createTable(tableName: "nature") {
            column(name: "id", type: "int8") {
                constraints(nullable: "false", primaryKey: "true", primaryKeyName: "nature_pkey")
            }

            column(name: "version", type: "int8") {
                constraints(nullable: "false")
            }

            column(name: "libelle", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-69") {
        createTable(tableName: "nature_de_charge") {
            column(name: "id", type: "int8") {
                constraints(nullable: "false", primaryKey: "true", primaryKeyName: "nature_de_charge_pkey")
            }

            column(name: "version", type: "int8") {
                constraints(nullable: "false")
            }

            column(name: "charge_basic", type: "FLOAT8(17)") {
                constraints(nullable: "false")
            }

            column(name: "libelle", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }

            column(name: "type", type: "VARCHAR(8)") {
                constraints(nullable: "false")
            }
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-70") {
        createTable(tableName: "note") {
            column(name: "id", type: "int8") {
                constraints(nullable: "false", primaryKey: "true", primaryKeyName: "note_pkey")
            }

            column(name: "version", type: "int8") {
                constraints(nullable: "false")
            }

            column(name: "corps", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }

            column(name: "date", type: "TIMESTAMP WITH TIME ZONE") {
                constraints(nullable: "false")
            }

            column(name: "objet", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }

            column(name: "projet_id", type: "int8") {
                constraints(nullable: "false")
            }
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-71") {
        createTable(tableName: "outil_employe") {
            column(name: "id", type: "int8") {
                constraints(nullable: "false", primaryKey: "true", primaryKeyName: "outil_employe_pkey")
            }

            column(name: "version", type: "int8") {
                constraints(nullable: "false")
            }

            column(name: "cout_horaire", type: "FLOAT8(17)") {
                constraints(nullable: "false")
            }

            column(name: "employe_id", type: "int8") {
                constraints(nullable: "false")
            }

            column(name: "nom", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }

            column(name: "numero", type: "int4") {
                constraints(nullable: "false")
            }
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-72") {
        createTable(tableName: "outil_louable") {
            column(name: "id", type: "int8") {
                constraints(nullable: "false", primaryKey: "true", primaryKeyName: "outil_louable_pkey")
            }

            column(name: "version", type: "int8") {
                constraints(nullable: "false")
            }

            column(name: "designation", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }

            column(name: "loueur_id", type: "int8") {
                constraints(nullable: "false")
            }

            column(name: "prix_heure_location", type: "FLOAT8(17)") {
                constraints(nullable: "false")
            }
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-73") {
        createTable(tableName: "outillage_collectif") {
            column(name: "id", type: "int8") {
                constraints(nullable: "false", primaryKey: "true", primaryKeyName: "outillage_collectif_pkey")
            }

            column(name: "version", type: "int8") {
                constraints(nullable: "false")
            }

            column(name: "date_achat", type: "TIMESTAMP WITH TIME ZONE") {
                constraints(nullable: "false")
            }

            column(name: "date_fin_utilisation", type: "TIMESTAMP WITH TIME ZONE")

            column(name: "date_mise_en_service", type: "TIMESTAMP WITH TIME ZONE")

            column(name: "designation", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }

            column(name: "numero", type: "int4") {
                constraints(nullable: "false")
            }

            column(name: "prix_achat", type: "FLOAT8(17)") {
                constraints(nullable: "false")
            }
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-74") {
        createTable(tableName: "paiement") {
            column(name: "id", type: "int8") {
                constraints(nullable: "false", primaryKey: "true", primaryKeyName: "paiement_pkey")
            }

            column(name: "version", type: "int8") {
                constraints(nullable: "false")
            }

            column(name: "bon_commande_id", type: "int8") {
                constraints(nullable: "false")
            }

            column(name: "compte_bancaire_id", type: "int8")

            column(name: "compte_bancaire_partenaire_id", type: "int8")

            column(name: "credit", type: "bool") {
                constraints(nullable: "false")
            }

            column(name: "date", type: "TIMESTAMP WITH TIME ZONE") {
                constraints(nullable: "false")
            }

            column(name: "date_encaissement", type: "TIMESTAMP WITH TIME ZONE")

            column(name: "mode_reglement_id", type: "int8") {
                constraints(nullable: "false")
            }

            column(name: "montant_paye", type: "FLOAT8(17)") {
                constraints(nullable: "false")
            }

            column(name: "num_transaction", type: "VARCHAR(255)")

            column(name: "partenaire_id", type: "int8") {
                constraints(nullable: "false")
            }

            column(name: "statut", type: "VARCHAR(255)")
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-75") {
        createTable(tableName: "paiement_jbl") {
            column(name: "id", type: "int8") {
                constraints(nullable: "false", primaryKey: "true", primaryKeyName: "paiement_jbl_pkey")
            }

            column(name: "version", type: "int8") {
                constraints(nullable: "false")
            }

            column(name: "annee", type: "int4") {
                constraints(nullable: "false")
            }

            column(name: "avance", type: "FLOAT8(17)") {
                constraints(nullable: "false")
            }

            column(name: "date_avance", type: "TIMESTAMP WITH TIME ZONE")

            column(name: "date_salaire", type: "TIMESTAMP WITH TIME ZONE")

            column(name: "employe_id", type: "int8") {
                constraints(nullable: "false")
            }

            column(name: "frais_deplacement", type: "FLOAT8(17)") {
                constraints(nullable: "false")
            }

            column(name: "heures_supp", type: "FLOAT8(17)") {
                constraints(nullable: "false")
            }

            column(name: "jours_abscences", type: "int4") {
                constraints(nullable: "false")
            }

            column(name: "jours_travailles", type: "int4") {
                constraints(nullable: "false")
            }

            column(name: "mois", type: "VARCHAR(9)") {
                constraints(nullable: "false")
            }

            column(name: "rappel", type: "FLOAT8(17)") {
                constraints(nullable: "false")
            }

            column(name: "retenu_abscence", type: "FLOAT8(17)") {
                constraints(nullable: "false")
            }

            column(name: "retenu_vetement", type: "FLOAT8(17)") {
                constraints(nullable: "false")
            }

            column(name: "salaire", type: "FLOAT8(17)") {
                constraints(nullable: "false")
            }
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-76") {
        createTable(tableName: "parametrage") {
            column(name: "id", type: "int8") {
                constraints(nullable: "false", primaryKey: "true", primaryKeyName: "parametrage_pkey")
            }

            column(name: "version", type: "int8") {
                constraints(nullable: "false")
            }

            column(name: "afficher_remise_facture", type: "bool") {
                constraints(nullable: "false")
            }

            column(name: "afficher_tva_facture", type: "bool") {
                constraints(nullable: "false")
            }

            column(name: "produit_perissable_managed", type: "bool")

            column(name: "multi_entrepot_enabled", type: "bool")
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-77") {
        createTable(tableName: "partenaire") {
            column(name: "id", type: "int8") {
                constraints(nullable: "false", primaryKey: "true", primaryKeyName: "partenaire_pkey")
            }

            column(name: "version", type: "int8") {
                constraints(nullable: "false")
            }

            column(name: "adresse", type: "VARCHAR(255)")

            column(name: "adresse_facturation", type: "VARCHAR(255)")

            column(name: "adresse_livraison", type: "VARCHAR(255)")

            column(name: "cnss", type: "VARCHAR(255)")

            column(name: "code", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }

            column(name: "code_postal", type: "VARCHAR(255)")

            column(name: "echeance_id", type: "int8")

            column(name: "email", type: "VARCHAR(255)")

            column(name: "est_blacklist", type: "bool") {
                constraints(nullable: "false")
            }

            column(name: "est_client", type: "bool") {
                constraints(nullable: "false")
            }

            column(name: "est_fournisseur", type: "bool") {
                constraints(nullable: "false")
            }

            column(name: "est_particulier", type: "bool") {
                constraints(nullable: "false")
            }

            column(name: "fax", type: "VARCHAR(255)")

            column(name: "forme_juridique_id", type: "int8")

            column(name: "id_fiscal", type: "VARCHAR(255)")

            column(name: "mode_livraison_id", type: "int8")

            column(name: "mode_reglement_defaut_id", type: "int8")

            column(name: "mode_relance_paiement_id", type: "int8")

            column(name: "patente", type: "VARCHAR(255)")

            column(name: "raison_sociale", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }

            column(name: "registre_commerce", type: "VARCHAR(255)")

            column(name: "site_web", type: "VARCHAR(255)")

            column(name: "telephone", type: "VARCHAR(255)")

            column(name: "ville_id", type: "int8")
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-78") {
        createTable(tableName: "partenaire_categorie_partenaire") {
            column(name: "partenaire_categorie_partenaires_id", type: "int8")

            column(name: "categorie_partenaire_id", type: "int8")
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-79") {
        createTable(tableName: "patern_compteur") {
            column(name: "id", type: "int8") {
                constraints(nullable: "false", primaryKey: "true", primaryKeyName: "patern_compteur_pkey")
            }

            column(name: "version", type: "int8") {
                constraints(nullable: "false")
            }

            column(name: "libelle", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }

            column(name: "numero_suivant", type: "int4") {
                constraints(nullable: "false")
            }

            column(name: "pas", type: "int4") {
                constraints(nullable: "false")
            }

            column(name: "prefixe", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }

            column(name: "remplissage", type: "int4") {
                constraints(nullable: "false")
            }

            column(name: "suffixe", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }

            column(name: "type", type: "VARCHAR(13)") {
                constraints(nullable: "false")
            }
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-80") {
        createTable(tableName: "pays") {
            column(name: "id", type: "int8") {
                constraints(nullable: "false", primaryKey: "true", primaryKeyName: "pays_pkey")
            }

            column(name: "version", type: "int8") {
                constraints(nullable: "false")
            }

            column(name: "intitule", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-81") {
        createTable(tableName: "periode") {
            column(name: "id", type: "int8") {
                constraints(nullable: "false", primaryKey: "true", primaryKeyName: "periode_pkey")
            }

            column(name: "version", type: "int8") {
                constraints(nullable: "false")
            }

            column(name: "exercice_id", type: "int8") {
                constraints(nullable: "false")
            }

            column(name: "intitule", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-82") {
        createTable(tableName: "phase") {
            column(name: "id", type: "int8") {
                constraints(nullable: "false", primaryKey: "true", primaryKeyName: "phase_pkey")
            }

            column(name: "version", type: "int8") {
                constraints(nullable: "false")
            }

            column(name: "avancement_prevu", type: "FLOAT8(17)") {
                constraints(nullable: "false")
            }

            column(name: "avancement_reel", type: "FLOAT8(17)") {
                constraints(nullable: "false")
            }

            column(name: "charge_prevue", type: "FLOAT8(17)") {
                constraints(nullable: "false")
            }

            column(name: "charge_relle", type: "FLOAT8(17)") {
                constraints(nullable: "false")
            }

            column(name: "date_debut_prevue", type: "TIMESTAMP WITH TIME ZONE") {
                constraints(nullable: "false")
            }

            column(name: "date_debut_relle", type: "TIMESTAMP WITH TIME ZONE") {
                constraints(nullable: "false")
            }

            column(name: "date_fin_prevue", type: "TIMESTAMP WITH TIME ZONE") {
                constraints(nullable: "false")
            }

            column(name: "date_fin_reelle", type: "TIMESTAMP WITH TIME ZONE") {
                constraints(nullable: "false")
            }

            column(name: "designation", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }

            column(name: "projet_id", type: "int8") {
                constraints(nullable: "false")
            }
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-83") {
        createTable(tableName: "phase_document_numerique") {
            column(name: "phase_documents_id", type: "int8")

            column(name: "document_numerique_id", type: "int8")
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-84") {
        createTable(tableName: "produit") {
            column(name: "id", type: "int8") {
                constraints(nullable: "false", primaryKey: "true", primaryKeyName: "produit_pkey")
            }

            column(name: "version", type: "int8") {
                constraints(nullable: "false")
            }

            column(name: "categorie_produit_id", type: "int8") {
                constraints(nullable: "false")
            }

            column(name: "code", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }

            column(name: "codebarre", type: "VARCHAR(255)")

            column(name: "designation", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }

            column(name: "emp_reception_id", type: "int8") {
                constraints(nullable: "false")
            }

            column(name: "nature_id", type: "int8")

            column(name: "poids", type: "FLOAT8(17)")

            column(name: "prix_achat", type: "FLOAT8(17)") {
                constraints(nullable: "false")
            }

            column(name: "prix_moyen_pendere", type: "FLOAT8(17)")

            column(name: "prix_revient", type: "FLOAT8(17)") {
                constraints(nullable: "false")
            }

            column(name: "prix_vente_standard", type: "FLOAT8(17)") {
                constraints(nullable: "false")
            }

            column(name: "produit_parent_id", type: "int8")

            column(name: "regimetva_id", type: "int8") {
                constraints(nullable: "false")
            }

            column(name: "unite_mesure_id", type: "int8") {
                constraints(nullable: "false")
            }

            column(name: "perissable", type: "bool")

            column(name: "temperature_conservation", type: "FLOAT8(17)")
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-85") {
        createTable(tableName: "projet") {
            column(name: "id", type: "int8") {
                constraints(nullable: "false", primaryKey: "true", primaryKeyName: "projet_pkey")
            }

            column(name: "version", type: "int8") {
                constraints(nullable: "false")
            }

            column(name: "adresse_projet", type: "VARCHAR(255)")

            column(name: "avancement_prevu", type: "FLOAT8(17)")

            column(name: "avancement_reel", type: "FLOAT8(17)") {
                constraints(nullable: "false")
            }

            column(name: "bon_commande_id", type: "int8")

            column(name: "calendrier_id", type: "int8") {
                constraints(nullable: "false")
            }

            column(name: "charge_prevue", type: "FLOAT8(17)")

            column(name: "charge_reelle", type: "FLOAT8(17)")

            column(name: "chef_chantier_id", type: "int8") {
                constraints(nullable: "false")
            }

            column(name: "code_postal", type: "VARCHAR(255)")

            column(name: "date_debut_prevu", type: "TIMESTAMP WITH TIME ZONE")

            column(name: "date_debut_reelle", type: "TIMESTAMP WITH TIME ZONE") {
                constraints(nullable: "false")
            }

            column(name: "date_fin_prevue", type: "TIMESTAMP WITH TIME ZONE")

            column(name: "date_fin_reelle", type: "TIMESTAMP WITH TIME ZONE")

            column(name: "email", type: "VARCHAR(255)")

            column(name: "fax", type: "VARCHAR(255)")

            column(name: "montant_bon_commande", type: "FLOAT8(17)") {
                constraints(nullable: "false")
            }

            column(name: "nom", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }

            column(name: "nom_contact", type: "VARCHAR(255)")

            column(name: "numero", type: "int4") {
                constraints(nullable: "false")
            }

            column(name: "telephone", type: "VARCHAR(255)")

            column(name: "ville", type: "VARCHAR(255)")
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-86") {
        createTable(tableName: "projet_document_numerique") {
            column(name: "projet_documents_id", type: "int8")

            column(name: "document_numerique_id", type: "int8")
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-87") {
        createTable(tableName: "regime_declaration_tva") {
            column(name: "id", type: "int8") {
                constraints(nullable: "false", primaryKey: "true", primaryKeyName: "regime_declaration_tva_pkey")
            }

            column(name: "version", type: "int8") {
                constraints(nullable: "false")
            }

            column(name: "fait_generateur", type: "VARCHAR(12)") {
                constraints(nullable: "false")
            }

            column(name: "libelle", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }

            column(name: "periodicite", type: "VARCHAR(13)") {
                constraints(nullable: "false")
            }
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-88") {
        createTable(tableName: "regimetva") {
            column(name: "id", type: "int8") {
                constraints(nullable: "false", primaryKey: "true", primaryKeyName: "regimetva_pkey")
            }

            column(name: "version", type: "int8") {
                constraints(nullable: "false")
            }

            column(name: "libelle", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }

            column(name: "taux", type: "int4") {
                constraints(nullable: "false")
            }
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-89") {
        createTable(tableName: "regle_facturation_automatique") {
            column(name: "id", type: "int8") {
                constraints(nullable: "false", primaryKey: "true", primaryKeyName: "regle_facturation_automatique_pkey")
            }

            column(name: "version", type: "int8") {
                constraints(nullable: "false")
            }

            column(name: "libelle", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }

            column(name: "nombre_bla_regroupe", type: "int4") {
                constraints(nullable: "false")
            }

            column(name: "priorite", type: "int4") {
                constraints(nullable: "false")
            }

            column(name: "type", type: "VARCHAR(18)") {
                constraints(nullable: "false")
            }
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-90") {
        createTable(tableName: "regle_facturation_automatique_partenaire") {
            column(name: "regle_facturation_automatique_partenaires_id", type: "int8")

            column(name: "partenaire_id", type: "int8")
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-91") {
        createTable(tableName: "regle_prix") {
            column(name: "id", type: "int8") {
                constraints(nullable: "false", primaryKey: "true", primaryKeyName: "regle_prix_pkey")
            }

            column(name: "version", type: "int8") {
                constraints(nullable: "false")
            }

            column(name: "active", type: "bool") {
                constraints(nullable: "false")
            }

            column(name: "arrondir_apres_virguel", type: "bool") {
                constraints(nullable: "false")
            }

            column(name: "code", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }

            column(name: "date_debut", type: "TIMESTAMP WITH TIME ZONE")

            column(name: "date_fin", type: "TIMESTAMP WITH TIME ZONE")

            column(name: "libelle", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }

            column(name: "operateur_comparaison", type: "VARCHAR(255)")

            column(name: "priorite", type: "int4") {
                constraints(nullable: "false")
            }

            column(name: "quantite_max", type: "FLOAT8(17)")

            column(name: "quantite_min", type: "FLOAT8(17)")

            column(name: "sense_arrondissement", type: "VARCHAR(255)")

            column(name: "seuil_arrondissement", type: "FLOAT8(17)")

            column(name: "taux_remise", type: "FLOAT8(17)") {
                constraints(nullable: "false")
            }

            column(name: "type_prixde_base", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }

            column(name: "type_regle", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }

            column(name: "valeur_fixe", type: "FLOAT8(17)")
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-92") {
        createTable(tableName: "regle_prix_categorie_partenaire") {
            column(name: "regle_prix_categorie_partenaires_id", type: "int8")

            column(name: "categorie_partenaire_id", type: "int8")
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-93") {
        createTable(tableName: "regle_prix_categorie_produit") {
            column(name: "regle_prix_categorie_produits_id", type: "int8")

            column(name: "categorie_produit_id", type: "int8")
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-94") {
        createTable(tableName: "regle_prix_partenaire") {
            column(name: "regle_prix_partenaires_id", type: "int8")

            column(name: "partenaire_id", type: "int8")
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-95") {
        createTable(tableName: "regle_prix_produit") {
            column(name: "regle_prix_produits_id", type: "int8")

            column(name: "produit_id", type: "int8")
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-96") {
        createTable(tableName: "securitestock") {
            column(name: "id", type: "int8") {
                constraints(nullable: "false", primaryKey: "true", primaryKeyName: "securitestock_pkey")
            }

            column(name: "version", type: "int8") {
                constraints(nullable: "false")
            }

            column(name: "entrepot_id", type: "int8") {
                constraints(nullable: "false")
            }

            column(name: "produit_id", type: "int8") {
                constraints(nullable: "false")
            }

            column(name: "stock_max", type: "FLOAT8(17)") {
                constraints(nullable: "false")
            }

            column(name: "stock_min", type: "FLOAT8(17)") {
                constraints(nullable: "false")
            }

            column(name: "stock_reel", type: "FLOAT8(17)") {
                constraints(nullable: "false")
            }

            column(name: "date_preremption", type: "TIMESTAMP WITH TIME ZONE")
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-97") {
        createTable(tableName: "soustraitance") {
            column(name: "id", type: "int8") {
                constraints(nullable: "false", primaryKey: "true", primaryKeyName: "soustraitance_pkey")
            }

            column(name: "version", type: "int8") {
                constraints(nullable: "false")
            }

            column(name: "cout", type: "FLOAT8(17)") {
                constraints(nullable: "false")
            }

            column(name: "date", type: "TIMESTAMP WITH TIME ZONE") {
                constraints(nullable: "false")
            }

            column(name: "nom", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }

            column(name: "projet_id", type: "int8") {
                constraints(nullable: "false")
            }

            column(name: "soustraitant_id", type: "int8") {
                constraints(nullable: "false")
            }
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-98") {
        createTable(tableName: "soustraitant") {
            column(name: "id", type: "int8") {
                constraints(nullable: "false", primaryKey: "true", primaryKeyName: "soustraitant_pkey")
            }

            column(name: "version", type: "int8") {
                constraints(nullable: "false")
            }

            column(name: "adresse", type: "VARCHAR(255)")

            column(name: "code_postal", type: "VARCHAR(255)")

            column(name: "email", type: "VARCHAR(255)")

            column(name: "fax", type: "VARCHAR(255)")

            column(name: "nom", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }

            column(name: "numero", type: "int4") {
                constraints(nullable: "false")
            }

            column(name: "telephone", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }

            column(name: "ville", type: "VARCHAR(255)")
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-99") {
        createTable(tableName: "tache") {
            column(name: "id", type: "int8") {
                constraints(nullable: "false", primaryKey: "true", primaryKeyName: "tache_pkey")
            }

            column(name: "version", type: "int8") {
                constraints(nullable: "false")
            }

            column(name: "avancement_prevu", type: "FLOAT8(17)") {
                constraints(nullable: "false")
            }

            column(name: "avancement_reel", type: "FLOAT8(17)") {
                constraints(nullable: "false")
            }

            column(name: "charge_prevue", type: "FLOAT8(17)") {
                constraints(nullable: "false")
            }

            column(name: "charge_reelle", type: "FLOAT8(17)") {
                constraints(nullable: "false")
            }

            column(name: "date_debut_prevue", type: "TIMESTAMP WITH TIME ZONE") {
                constraints(nullable: "false")
            }

            column(name: "date_debut_reelle", type: "TIMESTAMP WITH TIME ZONE") {
                constraints(nullable: "false")
            }

            column(name: "date_fin_prevu", type: "TIMESTAMP WITH TIME ZONE") {
                constraints(nullable: "false")
            }

            column(name: "date_fin_reelle", type: "TIMESTAMP WITH TIME ZONE") {
                constraints(nullable: "false")
            }

            column(name: "designation", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }

            column(name: "employe_id", type: "int8")

            column(name: "jalon", type: "bool") {
                constraints(nullable: "false")
            }

            column(name: "numero", type: "int4") {
                constraints(nullable: "false")
            }

            column(name: "phase_id", type: "int8") {
                constraints(nullable: "false")
            }
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-100") {
        createTable(tableName: "tache_document_numerique") {
            column(name: "tache_documents_id", type: "int8")

            column(name: "document_numerique_id", type: "int8")
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-101") {
        createTable(tableName: "tache_tache") {
            column(name: "tache_predecesseur_id", type: "int8")

            column(name: "tache_id", type: "int8")
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-102") {
        createTable(tableName: "unite_mesure") {
            column(name: "id", type: "int8") {
                constraints(nullable: "false", primaryKey: "true", primaryKeyName: "unite_mesure_pkey")
            }

            column(name: "version", type: "int8") {
                constraints(nullable: "false")
            }

            column(name: "libelle", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-103") {
        createTable(tableName: "utilisateur") {
            column(name: "id", type: "int8") {
                constraints(nullable: "false", primaryKey: "true", primaryKeyName: "utilisateur_pkey")
            }

            column(name: "version", type: "int8") {
                constraints(nullable: "false")
            }

            column(name: "email", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }

            column(name: "enabled", type: "bool") {
                constraints(nullable: "false")
            }

            column(name: "groupe_id", type: "int8") {
                constraints(nullable: "false")
            }

            column(name: "passwd", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }

            column(name: "societe_id", type: "int8") {
                constraints(nullable: "false")
            }

            column(name: "user_real_name", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }

            column(name: "username", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-104") {
        createTable(tableName: "ville") {
            column(name: "id", type: "int8") {
                constraints(nullable: "false", primaryKey: "true", primaryKeyName: "ville_pkey")
            }

            column(name: "version", type: "int8") {
                constraints(nullable: "false")
            }

            column(name: "intitule", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }

            column(name: "pays_id", type: "int8") {
                constraints(nullable: "false")
            }
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-105") {
        addUniqueConstraint(columnNames: "droit", constraintName: "droit_utilisateur_droit_key", deferrable: "false", disabled: "false", initiallyDeferred: "false", tableName: "droit_utilisateur")
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-106") {
        addUniqueConstraint(columnNames: "intitule", constraintName: "groupe_utilisateur_intitule_key", deferrable: "false", disabled: "false", initiallyDeferred: "false", tableName: "groupe_utilisateur")
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-107") {
        addUniqueConstraint(columnNames: "code", constraintName: "module_code_key", deferrable: "false", disabled: "false", initiallyDeferred: "false", tableName: "module")
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-108") {
        addUniqueConstraint(columnNames: "intitule", constraintName: "module_intitule_key", deferrable: "false", disabled: "false", initiallyDeferred: "false", tableName: "module")
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-109") {
        addUniqueConstraint(columnNames: "username", constraintName: "utilisateur_username_key", deferrable: "false", disabled: "false", initiallyDeferred: "false", tableName: "utilisateur")
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-110") {
        createIndex(indexName: "affectationoutil_cout_horaire", tableName: "affectation_outil", unique: "false") {
            column(name: "cout_horaire")
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-111") {
        createIndex(indexName: "affectationoutil_date", tableName: "affectation_outil", unique: "false") {
            column(name: "date")
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-112") {
        createIndex(indexName: "affectationoutil_heures_entretien", tableName: "affectation_outil", unique: "false") {
            column(name: "heures_entretien")
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-113") {
        createIndex(indexName: "affectationoutil_heures_utilisation", tableName: "affectation_outil", unique: "false") {
            column(name: "heures_utilisation")
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-114") {
        createIndex(indexName: "affutillage_da", tableName: "affectation_outillage", unique: "false") {
            column(name: "date_affectation")
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-115") {
        createIndex(indexName: "affutillage_dfuo", tableName: "affectation_outillage", unique: "false") {
            column(name: "date_fin_utilisation_outillage")
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-116") {
        createIndex(indexName: "affectationproduit_date", tableName: "affectation_produit", unique: "false") {
            column(name: "date")
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-117") {
        createIndex(indexName: "affectationproduit_prix_unitaire", tableName: "affectation_produit", unique: "false") {
            column(name: "prix_unitaire")
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-118") {
        createIndex(indexName: "affectationproduit_quantite", tableName: "affectation_produit", unique: "false") {
            column(name: "quantite")
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-119") {
        createIndex(indexName: "agencebancaire_adresse", tableName: "agence_bancaire", unique: "false") {
            column(name: "adresse")
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-120") {
        createIndex(indexName: "agencebancaire_nom", tableName: "agence_bancaire", unique: "false") {
            column(name: "nom")
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-121") {
        createIndex(indexName: "agencebancaire_tel", tableName: "agence_bancaire", unique: "false") {
            column(name: "tel")
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-122") {
        createIndex(indexName: "alertssecuritestock_date_alerte", tableName: "alerts_securite_stock", unique: "false") {
            column(name: "date_alerte")
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-123") {
        createIndex(indexName: "alertssecuritestock_date_fin_alerte", tableName: "alerts_securite_stock", unique: "false") {
            column(name: "date_fin_alerte")
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-124") {
        createIndex(indexName: "alertssecuritestock_details", tableName: "alerts_securite_stock", unique: "false") {
            column(name: "details")
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-125") {
        createIndex(indexName: "alertssecuritestock_stock_max", tableName: "alerts_securite_stock", unique: "false") {
            column(name: "stock_max")
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-126") {
        createIndex(indexName: "alertssecuritestock_stock_min", tableName: "alerts_securite_stock", unique: "false") {
            column(name: "stock_min")
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-127") {
        createIndex(indexName: "alertssecuritestock_stock_reel", tableName: "alerts_securite_stock", unique: "false") {
            column(name: "stock_reel")
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-128") {
        createIndex(indexName: "alertssecuritestock_tjs_valide", tableName: "alerts_securite_stock", unique: "false") {
            column(name: "tjs_valide")
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-129") {
        createIndex(indexName: "boncommande_avec_retour", tableName: "bon_commande", unique: "false") {
            column(name: "avec_retour")
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-130") {
        createIndex(indexName: "boncommande_bonpret", tableName: "bon_commande", unique: "false") {
            column(name: "bon_pret")
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-131") {
        createIndex(indexName: "boncommande_date", tableName: "bon_commande", unique: "false") {
            column(name: "date")
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-132") {
        createIndex(indexName: "boncommande_livree", tableName: "bon_commande", unique: "false") {
            column(name: "livree")
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-133") {
        createIndex(indexName: "boncommande_num_b_c", tableName: "bon_commande", unique: "false") {
            column(name: "numbc")
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-134") {
        createIndex(indexName: "boncommande_paye", tableName: "bon_commande", unique: "false") {
            column(name: "paye")
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-135") {
        createIndex(indexName: "boncommande_reference", tableName: "bon_commande", unique: "false") {
            column(name: "reference")
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-136") {
        createIndex(indexName: "boncommande_retour_paye", tableName: "bon_commande", unique: "false") {
            column(name: "retour_paye")
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-137") {
        createIndex(indexName: "boncommande_retournee", tableName: "bon_commande", unique: "false") {
            column(name: "retournee")
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-138") {
        createIndex(indexName: "boncommande_totalttc_retour", tableName: "bon_commande", unique: "false") {
            column(name: "totalttc_retour")
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-139") {
        createIndex(indexName: "boncommande_type", tableName: "bon_commande", unique: "false") {
            column(name: "type")
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-140") {
        createIndex(indexName: "bonlivraison_date", tableName: "bon_livraison", unique: "false") {
            column(name: "date")
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-141") {
        createIndex(indexName: "bonlivraison_num_b_l", tableName: "bon_livraison", unique: "false") {
            column(name: "numbl")
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-142") {
        createIndex(indexName: "bonlivraison_reference", tableName: "bon_livraison", unique: "false") {
            column(name: "reference")
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-143") {
        createIndex(indexName: "bonlivraison_type", tableName: "bon_livraison", unique: "false") {
            column(name: "type")
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-144") {
        createIndex(indexName: "bonlivriason_est_br", tableName: "bon_livraison", unique: "false") {
            column(name: "est_bon_retour")
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-145") {
        createIndex(indexName: "bonpret_date", tableName: "bon_pret", unique: "false") {
            column(name: "date")
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-146") {
        createIndex(indexName: "bonpret_montant_garantie", tableName: "bon_pret", unique: "false") {
            column(name: "montant_garantie")
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-147") {
        createIndex(indexName: "bonpret_num_b_p", tableName: "bon_pret", unique: "false") {
            column(name: "numbp")
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-148") {
        createIndex(indexName: "bonpret_reference", tableName: "bon_pret", unique: "false") {
            column(name: "reference")
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-149") {
        createIndex(indexName: "bonpret_type", tableName: "bon_pret", unique: "false") {
            column(name: "type")
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-150") {
        createIndex(indexName: "calendrier_nbhtpj", tableName: "calendrier", unique: "false") {
            column(name: "nb_heures_travail_par_jour")
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-151") {
        createIndex(indexName: "calendrier_nbjtpm", tableName: "calendrier", unique: "false") {
            column(name: "nb_jours_travail_par_mois")
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-152") {
        createIndex(indexName: "calendrier_nom", tableName: "calendrier", unique: "false") {
            column(name: "nom")
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-153") {
        createIndex(indexName: "categoriepartenaire_libelle", tableName: "categorie_partenaire", unique: "false") {
            column(name: "libelle")
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-154") {
        createIndex(indexName: "categorieproduit_code", tableName: "categorie_produit", unique: "false") {
            column(name: "code")
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-155") {
        createIndex(indexName: "categorieproduit_intitule", tableName: "categorie_produit", unique: "false") {
            column(name: "intitule")
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-156") {
        createIndex(indexName: "chargedivers_cout", tableName: "charge_divers", unique: "false") {
            column(name: "cout")
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-157") {
        createIndex(indexName: "chargedivers_date", tableName: "charge_divers", unique: "false") {
            column(name: "date")
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-158") {
        createIndex(indexName: "chargedivers_nom", tableName: "charge_divers", unique: "false") {
            column(name: "nom")
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-159") {
        createIndex(indexName: "cle_cleproduit", tableName: "cle", unique: "false") {
            column(name: "cle_produit")
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-160") {
        createIndex(indexName: "cle_datefinactivation", tableName: "cle", unique: "false") {
            column(name: "date_activation")
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-161") {
        createIndex(indexName: "comptebancaire_code_swift", tableName: "compte_bancaire", unique: "false") {
            column(name: "code_swift")
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-162") {
        createIndex(indexName: "comptebancaire_libelle", tableName: "compte_bancaire", unique: "false") {
            column(name: "libelle")
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-163") {
        createIndex(indexName: "comptebancaire_rib", tableName: "compte_bancaire", unique: "false") {
            column(name: "rib")
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-164") {
        createIndex(indexName: "comptebancaire_client_code_swift", tableName: "compte_bancaire_chora_client_info", unique: "false") {
            column(name: "code_swift")
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-165") {
        createIndex(indexName: "comptebancaire_client_libelle", tableName: "compte_bancaire_chora_client_info", unique: "false") {
            column(name: "libelle")
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-166") {
        createIndex(indexName: "comptebancaire_client_rib", tableName: "compte_bancaire_chora_client_info", unique: "false") {
            column(name: "rib")
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-167") {
        createIndex(indexName: "comptecomptable_code", tableName: "compte_comptable", unique: "false") {
            column(name: "code")
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-168") {
        createIndex(indexName: "comptecomptable_identifiant", tableName: "compte_comptable", unique: "false") {
            column(name: "identifiant")
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-169") {
        createIndex(indexName: "comptecomptable_nom", tableName: "compte_comptable", unique: "false") {
            column(name: "nom")
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-170") {
        createIndex(indexName: "comptecomptable_type", tableName: "compte_comptable", unique: "false") {
            column(name: "type")
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-171") {
        createIndex(indexName: "comptecomptableproduit_type", tableName: "compte_comptable_produit", unique: "false") {
            column(name: "type")
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-172") {
        createIndex(indexName: "contact_email", tableName: "contact", unique: "false") {
            column(name: "email")
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-173") {
        createIndex(indexName: "contact_fixe", tableName: "contact", unique: "false") {
            column(name: "fixe")
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-174") {
        createIndex(indexName: "contact_fonction", tableName: "contact", unique: "false") {
            column(name: "fonction")
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-175") {
        createIndex(indexName: "contact_nom", tableName: "contact", unique: "false") {
            column(name: "nom")
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-176") {
        createIndex(indexName: "contact_portable", tableName: "contact", unique: "false") {
            column(name: "portable")
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-177") {
        createIndex(indexName: "contact_titre", tableName: "contact", unique: "false") {
            column(name: "titre")
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-178") {
        createIndex(indexName: "declarationtva_date", tableName: "declaration_tva", unique: "false") {
            column(name: "date")
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-179") {
        createIndex(indexName: "declarationtva_exercise", tableName: "declaration_tva", unique: "false") {
            column(name: "exercise")
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-180") {
        createIndex(indexName: "declarationtva_num_declaration", tableName: "declaration_tva", unique: "false") {
            column(name: "num_declaration")
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-181") {
        createIndex(indexName: "declarationtva_periode", tableName: "declaration_tva", unique: "false") {
            column(name: "periode")
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-182") {
        createIndex(indexName: "depenses_date", tableName: "depenses", unique: "false") {
            column(name: "date")
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-183") {
        createIndex(indexName: "depenses_detail", tableName: "depenses", unique: "false") {
            column(name: "detail")
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-184") {
        createIndex(indexName: "depenses_montant", tableName: "depenses", unique: "false") {
            column(name: "montant")
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-185") {
        createIndex(indexName: "devis_date", tableName: "devis", unique: "false") {
            column(name: "date")
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-186") {
        createIndex(indexName: "devis_date_fin_validite", tableName: "devis", unique: "false") {
            column(name: "date_fin_validite")
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-187") {
        createIndex(indexName: "devis_num_devis", tableName: "devis", unique: "false") {
            column(name: "num_devis")
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-188") {
        createIndex(indexName: "devis_reference", tableName: "devis", unique: "false") {
            column(name: "reference")
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-189") {
        createIndex(indexName: "devis_type", tableName: "devis", unique: "false") {
            column(name: "type")
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-190") {
        createIndex(indexName: "devise_libelle", tableName: "devise", unique: "false") {
            column(name: "libelle")
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-191") {
        createIndex(indexName: "devise_valeur", tableName: "devise", unique: "false") {
            column(name: "valeur")
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-192") {
        createIndex(indexName: "echeance_code", tableName: "echeance", unique: "false") {
            column(name: "code")
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-193") {
        createIndex(indexName: "echeance_libelle", tableName: "echeance", unique: "false") {
            column(name: "libelle")
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-194") {
        createIndex(indexName: "echeance_periodicite", tableName: "echeance", unique: "false") {
            column(name: "periodicite")
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-195") {
        createIndex(indexName: "echeance_type_declanchement", tableName: "echeance", unique: "false") {
            column(name: "type_declanchement")
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-196") {
        createIndex(indexName: "ecriture_credit", tableName: "ecriture", unique: "false") {
            column(name: "credit")
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-197") {
        createIndex(indexName: "ecriture_date", tableName: "ecriture", unique: "false") {
            column(name: "date")
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-198") {
        createIndex(indexName: "ecriture_date_creance", tableName: "ecriture", unique: "false") {
            column(name: "date_creance")
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-199") {
        createIndex(indexName: "ecriture_date_echeance", tableName: "ecriture", unique: "false") {
            column(name: "date_echeance")
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-200") {
        createIndex(indexName: "ecriture_debit", tableName: "ecriture", unique: "false") {
            column(name: "debit")
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-201") {
        createIndex(indexName: "ecriture_description", tableName: "ecriture", unique: "false") {
            column(name: "description")
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-202") {
        createIndex(indexName: "ecriture_montant_devise", tableName: "ecriture", unique: "false") {
            column(name: "montant_devise")
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-203") {
        createIndex(indexName: "ecriture_numero", tableName: "ecriture", unique: "false") {
            column(name: "numero")
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-204") {
        createIndex(indexName: "ecriture_reference", tableName: "ecriture", unique: "false") {
            column(name: "reference")
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-205") {
        createIndex(indexName: "employe_nom", tableName: "employe", unique: "false") {
            column(name: "nom")
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-206") {
        createIndex(indexName: "employe_numero", tableName: "employe", unique: "false") {
            column(name: "numero")
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-207") {
        createIndex(indexName: "employe_prenom", tableName: "employe", unique: "false") {
            column(name: "prenom")
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-208") {
        createIndex(indexName: "employe_salaire", tableName: "employe", unique: "false") {
            column(name: "salaire")
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-209") {
        createIndex(indexName: "employe_taux_horaire", tableName: "employe", unique: "false") {
            column(name: "taux_horaire")
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-210") {
        createIndex(indexName: "employe_telephone", tableName: "employe", unique: "false") {
            column(name: "telephone")
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-211") {
        createIndex(indexName: "entrepot_adresse", tableName: "entrepot", unique: "false") {
            column(name: "adresse")
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-212") {
        createIndex(indexName: "entrepot_code", tableName: "entrepot", unique: "false") {
            column(name: "code")
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-213") {
        createIndex(indexName: "entrepot_intitule", tableName: "entrepot", unique: "false") {
            column(name: "intitule")
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-214") {
        createIndex(indexName: "entrepot_mode", tableName: "entrepot", unique: "false") {
            column(name: "mode")
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-215") {
        createIndex(indexName: "entrepot_mode_valorisation", tableName: "entrepot", unique: "false") {
            column(name: "mode_valorisation")
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-216") {
        createIndex(indexName: "entrepot_tel", tableName: "entrepot", unique: "false") {
            column(name: "tel")
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-217") {
        createIndex(indexName: "exercice_date_debut", tableName: "exercice", unique: "false") {
            column(name: "date_debut")
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-218") {
        createIndex(indexName: "exercice_date_fin", tableName: "exercice", unique: "false") {
            column(name: "date_fin")
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-219") {
        createIndex(indexName: "exercice_intitule", tableName: "exercice", unique: "false") {
            column(name: "intitule")
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-220") {
        createIndex(indexName: "facture_comptabilise", tableName: "facture", unique: "false") {
            column(name: "comptabilise")
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-221") {
        createIndex(indexName: "facture_date", tableName: "facture", unique: "false") {
            column(name: "date")
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-222") {
        createIndex(indexName: "facture_est_avoir", tableName: "facture", unique: "false") {
            column(name: "est_avoir")
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-223") {
        createIndex(indexName: "facture_faitgenerateur", tableName: "facture", unique: "false") {
            column(name: "faitgenerateur")
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-224") {
        createIndex(indexName: "facture_numero_facture", tableName: "facture", unique: "false") {
            column(name: "numero_facture")
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-225") {
        createIndex(indexName: "facture_reference", tableName: "facture", unique: "false") {
            column(name: "reference")
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-226") {
        createIndex(indexName: "facture_type", tableName: "facture", unique: "false") {
            column(name: "type")
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-227") {
        createIndex(indexName: "fichepresence_date", tableName: "fiche_presence", unique: "false") {
            column(name: "date")
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-228") {
        createIndex(indexName: "fichepresence_duree_trajet", tableName: "fiche_presence", unique: "false") {
            column(name: "duree_trajet")
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-229") {
        createIndex(indexName: "fichepresence_heures_abscence", tableName: "fiche_presence", unique: "false") {
            column(name: "heures_abscence")
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-230") {
        createIndex(indexName: "fichepresence_heures_presence", tableName: "fiche_presence", unique: "false") {
            column(name: "heures_presence")
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-231") {
        createIndex(indexName: "fichepresence_taux_horaire", tableName: "fiche_presence", unique: "false") {
            column(name: "taux_horaire")
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-232") {
        createIndex(indexName: "fonction_libelle", tableName: "fonction", unique: "false") {
            column(name: "libelle")
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-233") {
        createIndex(indexName: "formejuridique_libelle", tableName: "forme_juridique", unique: "false") {
            column(name: "libelle")
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-234") {
        createIndex(indexName: "inventaire_details", tableName: "inventaire", unique: "false") {
            column(name: "details")
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-235") {
        createIndex(indexName: "jourferier_annuel", tableName: "jour_ferier", unique: "false") {
            column(name: "annuel")
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-236") {
        createIndex(indexName: "jourferier_date", tableName: "jour_ferier", unique: "false") {
            column(name: "date")
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-237") {
        createIndex(indexName: "jourferier_duree", tableName: "jour_ferier", unique: "false") {
            column(name: "duree")
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-238") {
        createIndex(indexName: "jourferier_intitule", tableName: "jour_ferier", unique: "false") {
            column(name: "intitule")
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-239") {
        createIndex(indexName: "journal_intitule", tableName: "journal", unique: "false") {
            column(name: "intitule")
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-240") {
        createIndex(indexName: "ligneinventaire_qantite_actuele", tableName: "ligne_inventaire", unique: "false") {
            column(name: "qantite_actuele")
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-241") {
        createIndex(indexName: "ligneinventaire_quantite_inv", tableName: "ligne_inventaire", unique: "false") {
            column(name: "quantite_inv")
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-242") {
        createIndex(indexName: "ligneproduit_prix", tableName: "ligne_produit", unique: "false") {
            column(name: "prix")
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-243") {
        createIndex(indexName: "ligneproduit_prix_deduit", tableName: "ligne_produit", unique: "false") {
            column(name: "prix_deduit")
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-244") {
        createIndex(indexName: "ligneproduit_quantite", tableName: "ligne_produit", unique: "false") {
            column(name: "quantite")
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-245") {
        createIndex(indexName: "ligneproduit_quantite_livree", tableName: "ligne_produit", unique: "false") {
            column(name: "quantite_livree")
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-246") {
        createIndex(indexName: "ligneproduit_quantite_retournee", tableName: "ligne_produit", unique: "false") {
            column(name: "quantite_retournee")
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-247") {
        createIndex(indexName: "ligneproduit_remise", tableName: "ligne_produit", unique: "false") {
            column(name: "remise")
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-248") {
        createIndex(indexName: "ligneproduit_sequence_ligne", tableName: "ligne_produit", unique: "false") {
            column(name: "sequence_ligne")
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-249") {
        createIndex(indexName: "ligneproduit_type", tableName: "ligne_produit", unique: "false") {
            column(name: "type")
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-250") {
        createIndex(indexName: "livraison_date", tableName: "livraison", unique: "false") {
            column(name: "date")
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-251") {
        createIndex(indexName: "livraison_frais", tableName: "livraison", unique: "false") {
            column(name: "frais")
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-252") {
        createIndex(indexName: "livraison_nbr_colis", tableName: "livraison", unique: "false") {
            column(name: "nbr_colis")
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-253") {
        createIndex(indexName: "livraison_num_expedition", tableName: "livraison", unique: "false") {
            column(name: "num_expedition")
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-254") {
        createIndex(indexName: "livraison_poid_total", tableName: "livraison", unique: "false") {
            column(name: "poid_total")
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-255") {
        createIndex(indexName: "locationoutil_date", tableName: "location_outil", unique: "false") {
            column(name: "date")
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-256") {
        createIndex(indexName: "locationoutil_dlocation", tableName: "location_outil", unique: "false") {
            column(name: "duree_location")
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-257") {
        createIndex(indexName: "locationoutil_pforfait", tableName: "location_outil", unique: "false") {
            column(name: "prix_forfait")
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-258") {
        createIndex(indexName: "locationoutil_phlocation", tableName: "location_outil", unique: "false") {
            column(name: "prix_heure_location")
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-259") {
        createIndex(indexName: "loueur_adresse", tableName: "loueur", unique: "false") {
            column(name: "adresse")
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-260") {
        createIndex(indexName: "loueur_code_postal", tableName: "loueur", unique: "false") {
            column(name: "code_postal")
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-261") {
        createIndex(indexName: "loueur_email", tableName: "loueur", unique: "false") {
            column(name: "email")
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-262") {
        createIndex(indexName: "loueur_fax", tableName: "loueur", unique: "false") {
            column(name: "fax")
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-263") {
        createIndex(indexName: "loueur_nom", tableName: "loueur", unique: "false") {
            column(name: "nom")
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-264") {
        createIndex(indexName: "loueur_numero", tableName: "loueur", unique: "false") {
            column(name: "numero")
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-265") {
        createIndex(indexName: "loueur_telephone", tableName: "loueur", unique: "false") {
            column(name: "telephone")
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-266") {
        createIndex(indexName: "loueur_ville", tableName: "loueur", unique: "false") {
            column(name: "ville")
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-267") {
        createIndex(indexName: "messagedocument_entete", tableName: "message_document", unique: "false") {
            column(name: "entete")
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-268") {
        createIndex(indexName: "messagedocument_pied", tableName: "message_document", unique: "false") {
            column(name: "pied")
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-269") {
        createIndex(indexName: "messagedocument_type", tableName: "message_document", unique: "false") {
            column(name: "type")
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-270") {
        createIndex(indexName: "messagedocument_type_mouvement", tableName: "message_document", unique: "false") {
            column(name: "type_mouvement")
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-271") {
        createIndex(indexName: "modelivraison_code", tableName: "mode_livraison", unique: "false") {
            column(name: "code")
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-272") {
        createIndex(indexName: "modelivraison_paiement_a_la_reception", tableName: "mode_livraison", unique: "false") {
            column(name: "paiementala_reception")
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-273") {
        createIndex(indexName: "modelivraison_transporteur", tableName: "mode_livraison", unique: "false") {
            column(name: "transporteur")
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-274") {
        createIndex(indexName: "modereglement_code", tableName: "mode_reglement", unique: "false") {
            column(name: "code")
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-275") {
        createIndex(indexName: "modereglement_libelle", tableName: "mode_reglement", unique: "false") {
            column(name: "libelle")
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-276") {
        createIndex(indexName: "moderelancepaiement_code", tableName: "mode_relance_paiement", unique: "false") {
            column(name: "code")
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-277") {
        createIndex(indexName: "moderelancepaiement_corps_message", tableName: "mode_relance_paiement", unique: "false") {
            column(name: "corps_message")
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-278") {
        createIndex(indexName: "moderelancepaiement_frequence", tableName: "mode_relance_paiement", unique: "false") {
            column(name: "frequence")
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-279") {
        createIndex(indexName: "moderelancepaiement_libelle", tableName: "mode_relance_paiement", unique: "false") {
            column(name: "libelle")
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-280") {
        createIndex(indexName: "mouvementstock_date", tableName: "mouvement_stock", unique: "false") {
            column(name: "date")
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-281") {
        createIndex(indexName: "mouvementstock_quantite", tableName: "mouvement_stock", unique: "false") {
            column(name: "quantite")
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-282") {
        createIndex(indexName: "mouvementstock_type_mouvement", tableName: "mouvement_stock", unique: "false") {
            column(name: "type_mouvement")
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-283") {
        createIndex(indexName: "nature_libelle", tableName: "nature", unique: "false") {
            column(name: "libelle")
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-284") {
        createIndex(indexName: "naturedecharge_charge_basic", tableName: "nature_de_charge", unique: "false") {
            column(name: "charge_basic")
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-285") {
        createIndex(indexName: "naturedecharge_libelle", tableName: "nature_de_charge", unique: "false") {
            column(name: "libelle")
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-286") {
        createIndex(indexName: "naturedecharge_type", tableName: "nature_de_charge", unique: "false") {
            column(name: "type")
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-287") {
        createIndex(indexName: "note_corps", tableName: "note", unique: "false") {
            column(name: "corps")
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-288") {
        createIndex(indexName: "note_date", tableName: "note", unique: "false") {
            column(name: "date")
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-289") {
        createIndex(indexName: "note_objet", tableName: "note", unique: "false") {
            column(name: "objet")
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-290") {
        createIndex(indexName: "outilemploye_cout_horaire", tableName: "outil_employe", unique: "false") {
            column(name: "cout_horaire")
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-291") {
        createIndex(indexName: "outilemploye_nom", tableName: "outil_employe", unique: "false") {
            column(name: "nom")
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-292") {
        createIndex(indexName: "outilemploye_numero", tableName: "outil_employe", unique: "false") {
            column(name: "numero")
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-293") {
        createIndex(indexName: "outillouable_designation", tableName: "outil_louable", unique: "false") {
            column(name: "designation")
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-294") {
        createIndex(indexName: "outillouable_prix_heure_location", tableName: "outil_louable", unique: "false") {
            column(name: "prix_heure_location")
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-295") {
        createIndex(indexName: "outillagecollectif_date_achat", tableName: "outillage_collectif", unique: "false") {
            column(name: "date_achat")
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-296") {
        createIndex(indexName: "outillagecollectif_date_fin_utilisation", tableName: "outillage_collectif", unique: "false") {
            column(name: "date_fin_utilisation")
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-297") {
        createIndex(indexName: "outillagecollectif_date_mise_en_service", tableName: "outillage_collectif", unique: "false") {
            column(name: "date_mise_en_service")
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-298") {
        createIndex(indexName: "outillagecollectif_designation", tableName: "outillage_collectif", unique: "false") {
            column(name: "designation")
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-299") {
        createIndex(indexName: "outillagecollectif_numero", tableName: "outillage_collectif", unique: "false") {
            column(name: "numero")
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-300") {
        createIndex(indexName: "outillagecollectif_prix_achat", tableName: "outillage_collectif", unique: "false") {
            column(name: "prix_achat")
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-301") {
        createIndex(indexName: "paiement_credit", tableName: "paiement", unique: "false") {
            column(name: "credit")
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-302") {
        createIndex(indexName: "paiement_date", tableName: "paiement", unique: "false") {
            column(name: "date")
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-303") {
        createIndex(indexName: "paiement_date_encaissement", tableName: "paiement", unique: "false") {
            column(name: "date_encaissement")
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-304") {
        createIndex(indexName: "paiement_montant_paye", tableName: "paiement", unique: "false") {
            column(name: "montant_paye")
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-305") {
        createIndex(indexName: "paiement_num_transaction", tableName: "paiement", unique: "false") {
            column(name: "num_transaction")
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-306") {
        createIndex(indexName: "paiementjbl_annee", tableName: "paiement_jbl", unique: "false") {
            column(name: "annee")
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-307") {
        createIndex(indexName: "paiementjbl_avance", tableName: "paiement_jbl", unique: "false") {
            column(name: "avance")
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-308") {
        createIndex(indexName: "paiementjbl_date_avance", tableName: "paiement_jbl", unique: "false") {
            column(name: "date_avance")
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-309") {
        createIndex(indexName: "paiementjbl_date_salaire", tableName: "paiement_jbl", unique: "false") {
            column(name: "date_salaire")
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-310") {
        createIndex(indexName: "paiementjbl_frais_deplacement", tableName: "paiement_jbl", unique: "false") {
            column(name: "frais_deplacement")
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-311") {
        createIndex(indexName: "paiementjbl_heures_supp", tableName: "paiement_jbl", unique: "false") {
            column(name: "heures_supp")
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-312") {
        createIndex(indexName: "paiementjbl_jours_abscences", tableName: "paiement_jbl", unique: "false") {
            column(name: "jours_abscences")
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-313") {
        createIndex(indexName: "paiementjbl_jours_travailles", tableName: "paiement_jbl", unique: "false") {
            column(name: "jours_travailles")
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-314") {
        createIndex(indexName: "paiementjbl_mois", tableName: "paiement_jbl", unique: "false") {
            column(name: "mois")
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-315") {
        createIndex(indexName: "paiementjbl_rappel", tableName: "paiement_jbl", unique: "false") {
            column(name: "rappel")
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-316") {
        createIndex(indexName: "paiementjbl_retenu_abscence", tableName: "paiement_jbl", unique: "false") {
            column(name: "retenu_abscence")
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-317") {
        createIndex(indexName: "paiementjbl_retenu_vetement", tableName: "paiement_jbl", unique: "false") {
            column(name: "retenu_vetement")
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-318") {
        createIndex(indexName: "paiementjbl_salaire", tableName: "paiement_jbl", unique: "false") {
            column(name: "salaire")
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-319") {
        createIndex(indexName: "partenaire_adresse", tableName: "partenaire", unique: "false") {
            column(name: "adresse")
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-320") {
        createIndex(indexName: "partenaire_adresse_facturation", tableName: "partenaire", unique: "false") {
            column(name: "adresse_facturation")
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-321") {
        createIndex(indexName: "partenaire_adresse_livraison", tableName: "partenaire", unique: "false") {
            column(name: "adresse_livraison")
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-322") {
        createIndex(indexName: "partenaire_cnss", tableName: "partenaire", unique: "false") {
            column(name: "cnss")
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-323") {
        createIndex(indexName: "partenaire_code", tableName: "partenaire", unique: "false") {
            column(name: "code")
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-324") {
        createIndex(indexName: "partenaire_code_postal", tableName: "partenaire", unique: "false") {
            column(name: "code_postal")
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-325") {
        createIndex(indexName: "partenaire_email", tableName: "partenaire", unique: "false") {
            column(name: "email")
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-326") {
        createIndex(indexName: "partenaire_est_client", tableName: "partenaire", unique: "false") {
            column(name: "est_client")
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-327") {
        createIndex(indexName: "partenaire_est_en_liste_noire", tableName: "partenaire", unique: "false") {
            column(name: "est_blacklist")
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-328") {
        createIndex(indexName: "partenaire_est_fournisseur", tableName: "partenaire", unique: "false") {
            column(name: "est_fournisseur")
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-329") {
        createIndex(indexName: "partenaire_est_particulier", tableName: "partenaire", unique: "false") {
            column(name: "est_particulier")
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-330") {
        createIndex(indexName: "partenaire_fax", tableName: "partenaire", unique: "false") {
            column(name: "fax")
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-331") {
        createIndex(indexName: "partenaire_id_fiscal", tableName: "partenaire", unique: "false") {
            column(name: "id_fiscal")
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-332") {
        createIndex(indexName: "partenaire_patente", tableName: "partenaire", unique: "false") {
            column(name: "patente")
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-333") {
        createIndex(indexName: "partenaire_raison_sociale", tableName: "partenaire", unique: "false") {
            column(name: "raison_sociale")
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-334") {
        createIndex(indexName: "partenaire_registre_commerce", tableName: "partenaire", unique: "false") {
            column(name: "registre_commerce")
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-335") {
        createIndex(indexName: "partenaire_site_web", tableName: "partenaire", unique: "false") {
            column(name: "site_web")
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-336") {
        createIndex(indexName: "partenaire_telephone", tableName: "partenaire", unique: "false") {
            column(name: "telephone")
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-337") {
        createIndex(indexName: "paterncompteur_libelle", tableName: "patern_compteur", unique: "false") {
            column(name: "libelle")
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-338") {
        createIndex(indexName: "paterncompteur_numero_suivant", tableName: "patern_compteur", unique: "false") {
            column(name: "numero_suivant")
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-339") {
        createIndex(indexName: "paterncompteur_pas", tableName: "patern_compteur", unique: "false") {
            column(name: "pas")
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-340") {
        createIndex(indexName: "paterncompteur_prefixe", tableName: "patern_compteur", unique: "false") {
            column(name: "prefixe")
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-341") {
        createIndex(indexName: "paterncompteur_remplissage", tableName: "patern_compteur", unique: "false") {
            column(name: "remplissage")
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-342") {
        createIndex(indexName: "paterncompteur_suffixe", tableName: "patern_compteur", unique: "false") {
            column(name: "suffixe")
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-343") {
        createIndex(indexName: "paterncompteur_type", tableName: "patern_compteur", unique: "false") {
            column(name: "type")
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-344") {
        createIndex(indexName: "pays_intitule", tableName: "pays", unique: "false") {
            column(name: "intitule")
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-345") {
        createIndex(indexName: "periode_intitule", tableName: "periode", unique: "false") {
            column(name: "intitule")
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-346") {
        createIndex(indexName: "phase_avancement_prevu", tableName: "phase", unique: "false") {
            column(name: "avancement_prevu")
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-347") {
        createIndex(indexName: "phase_avancement_reel", tableName: "phase", unique: "false") {
            column(name: "avancement_reel")
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-348") {
        createIndex(indexName: "phase_charge_prevue", tableName: "phase", unique: "false") {
            column(name: "charge_prevue")
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-349") {
        createIndex(indexName: "phase_charge_relle", tableName: "phase", unique: "false") {
            column(name: "charge_relle")
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-350") {
        createIndex(indexName: "phase_date_debut_prevue", tableName: "phase", unique: "false") {
            column(name: "date_debut_prevue")
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-351") {
        createIndex(indexName: "phase_date_debut_relle", tableName: "phase", unique: "false") {
            column(name: "date_debut_relle")
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-352") {
        createIndex(indexName: "phase_date_fin_prevue", tableName: "phase", unique: "false") {
            column(name: "date_fin_prevue")
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-353") {
        createIndex(indexName: "phase_date_fin_reelle", tableName: "phase", unique: "false") {
            column(name: "date_fin_reelle")
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-354") {
        createIndex(indexName: "phase_designation", tableName: "phase", unique: "false") {
            column(name: "designation")
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-355") {
        createIndex(indexName: "produit_code", tableName: "produit", unique: "false") {
            column(name: "code")
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-356") {
        createIndex(indexName: "produit_codebarre", tableName: "produit", unique: "false") {
            column(name: "codebarre")
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-357") {
        createIndex(indexName: "produit_designation", tableName: "produit", unique: "false") {
            column(name: "designation")
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-358") {
        createIndex(indexName: "produit_poids", tableName: "produit", unique: "false") {
            column(name: "poids")
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-359") {
        createIndex(indexName: "produit_prix_achat", tableName: "produit", unique: "false") {
            column(name: "prix_achat")
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-360") {
        createIndex(indexName: "produit_prix_moyen_pendere", tableName: "produit", unique: "false") {
            column(name: "prix_moyen_pendere")
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-361") {
        createIndex(indexName: "produit_prix_revient", tableName: "produit", unique: "false") {
            column(name: "prix_revient")
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-362") {
        createIndex(indexName: "produit_prix_vente_standard", tableName: "produit", unique: "false") {
            column(name: "prix_vente_standard")
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-363") {
        createIndex(indexName: "projet_adresse_projet", tableName: "projet", unique: "false") {
            column(name: "adresse_projet")
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-364") {
        createIndex(indexName: "projet_avancement_prevu", tableName: "projet", unique: "false") {
            column(name: "avancement_prevu")
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-365") {
        createIndex(indexName: "projet_avancement_reel", tableName: "projet", unique: "false") {
            column(name: "avancement_reel")
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-366") {
        createIndex(indexName: "projet_charge_prevue", tableName: "projet", unique: "false") {
            column(name: "charge_prevue")
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-367") {
        createIndex(indexName: "projet_charge_reelle", tableName: "projet", unique: "false") {
            column(name: "charge_reelle")
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-368") {
        createIndex(indexName: "projet_code_postal", tableName: "projet", unique: "false") {
            column(name: "code_postal")
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-369") {
        createIndex(indexName: "projet_date_debut_prevu", tableName: "projet", unique: "false") {
            column(name: "date_debut_prevu")
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-370") {
        createIndex(indexName: "projet_date_debut_reelle", tableName: "projet", unique: "false") {
            column(name: "date_debut_reelle")
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-371") {
        createIndex(indexName: "projet_date_fin_prevue", tableName: "projet", unique: "false") {
            column(name: "date_fin_prevue")
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-372") {
        createIndex(indexName: "projet_date_fin_reelle", tableName: "projet", unique: "false") {
            column(name: "date_fin_reelle")
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-373") {
        createIndex(indexName: "projet_email", tableName: "projet", unique: "false") {
            column(name: "email")
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-374") {
        createIndex(indexName: "projet_fax", tableName: "projet", unique: "false") {
            column(name: "fax")
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-375") {
        createIndex(indexName: "projet_montant_bon_commande", tableName: "projet", unique: "false") {
            column(name: "montant_bon_commande")
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-376") {
        createIndex(indexName: "projet_nom", tableName: "projet", unique: "false") {
            column(name: "nom")
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-377") {
        createIndex(indexName: "projet_nom_contact", tableName: "projet", unique: "false") {
            column(name: "nom_contact")
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-378") {
        createIndex(indexName: "projet_numero", tableName: "projet", unique: "false") {
            column(name: "numero")
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-379") {
        createIndex(indexName: "projet_telephone", tableName: "projet", unique: "false") {
            column(name: "telephone")
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-380") {
        createIndex(indexName: "projet_ville", tableName: "projet", unique: "false") {
            column(name: "ville")
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-381") {
        createIndex(indexName: "regimedeclarationtva_fait_generateur", tableName: "regime_declaration_tva", unique: "false") {
            column(name: "fait_generateur")
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-382") {
        createIndex(indexName: "regimedeclarationtva_libelle", tableName: "regime_declaration_tva", unique: "false") {
            column(name: "libelle")
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-383") {
        createIndex(indexName: "regimedeclarationtva_periodicite", tableName: "regime_declaration_tva", unique: "false") {
            column(name: "periodicite")
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-384") {
        createIndex(indexName: "regimetva_libelle", tableName: "regimetva", unique: "false") {
            column(name: "libelle")
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-385") {
        createIndex(indexName: "regimetva_taux", tableName: "regimetva", unique: "false") {
            column(name: "taux")
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-386") {
        createIndex(indexName: "reglefacturationautomatique_libelle", tableName: "regle_facturation_automatique", unique: "false") {
            column(name: "libelle")
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-387") {
        createIndex(indexName: "reglefacturationautomatique_nombre_bla_regroupe", tableName: "regle_facturation_automatique", unique: "false") {
            column(name: "nombre_bla_regroupe")
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-388") {
        createIndex(indexName: "reglefacturationautomatique_priorite", tableName: "regle_facturation_automatique", unique: "false") {
            column(name: "priorite")
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-389") {
        createIndex(indexName: "reglefacturationautomatique_type", tableName: "regle_facturation_automatique", unique: "false") {
            column(name: "type")
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-390") {
        createIndex(indexName: "regleprix_active", tableName: "regle_prix", unique: "false") {
            column(name: "active")
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-391") {
        createIndex(indexName: "regleprix_arrondir_apres_virguel", tableName: "regle_prix", unique: "false") {
            column(name: "arrondir_apres_virguel")
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-392") {
        createIndex(indexName: "regleprix_code", tableName: "regle_prix", unique: "false") {
            column(name: "code")
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-393") {
        createIndex(indexName: "regleprix_date_debut", tableName: "regle_prix", unique: "false") {
            column(name: "date_debut")
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-394") {
        createIndex(indexName: "regleprix_date_fin", tableName: "regle_prix", unique: "false") {
            column(name: "date_fin")
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-395") {
        createIndex(indexName: "regleprix_libelle", tableName: "regle_prix", unique: "false") {
            column(name: "libelle")
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-396") {
        createIndex(indexName: "regleprix_operateur_comparaison", tableName: "regle_prix", unique: "false") {
            column(name: "operateur_comparaison")
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-397") {
        createIndex(indexName: "regleprix_priorite", tableName: "regle_prix", unique: "false") {
            column(name: "priorite")
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-398") {
        createIndex(indexName: "regleprix_quantite_max", tableName: "regle_prix", unique: "false") {
            column(name: "quantite_max")
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-399") {
        createIndex(indexName: "regleprix_quantite_min", tableName: "regle_prix", unique: "false") {
            column(name: "quantite_min")
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-400") {
        createIndex(indexName: "regleprix_sense_arrondissement", tableName: "regle_prix", unique: "false") {
            column(name: "sense_arrondissement")
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-401") {
        createIndex(indexName: "regleprix_seuil_arrondissement", tableName: "regle_prix", unique: "false") {
            column(name: "seuil_arrondissement")
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-402") {
        createIndex(indexName: "regleprix_taux_remise", tableName: "regle_prix", unique: "false") {
            column(name: "taux_remise")
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-403") {
        createIndex(indexName: "regleprix_type_prixde_base", tableName: "regle_prix", unique: "false") {
            column(name: "type_prixde_base")
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-404") {
        createIndex(indexName: "regleprix_typeregle", tableName: "regle_prix", unique: "false") {
            column(name: "type_regle")
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-405") {
        createIndex(indexName: "regleprix_valeur_fixe", tableName: "regle_prix", unique: "false") {
            column(name: "valeur_fixe")
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-406") {
        createIndex(indexName: "securitestock_stock_max", tableName: "securitestock", unique: "false") {
            column(name: "stock_max")
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-407") {
        createIndex(indexName: "securitestock_stock_min", tableName: "securitestock", unique: "false") {
            column(name: "stock_min")
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-408") {
        createIndex(indexName: "securitestock_stock_reel", tableName: "securitestock", unique: "false") {
            column(name: "stock_reel")
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-409") {
        createIndex(indexName: "soustraitance_cout", tableName: "soustraitance", unique: "false") {
            column(name: "cout")
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-410") {
        createIndex(indexName: "soustraitance_date", tableName: "soustraitance", unique: "false") {
            column(name: "date")
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-411") {
        createIndex(indexName: "soustraitance_nom", tableName: "soustraitance", unique: "false") {
            column(name: "nom")
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-412") {
        createIndex(indexName: "soustraitant_adresse", tableName: "soustraitant", unique: "false") {
            column(name: "adresse")
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-413") {
        createIndex(indexName: "soustraitant_code_postal", tableName: "soustraitant", unique: "false") {
            column(name: "code_postal")
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-414") {
        createIndex(indexName: "soustraitant_email", tableName: "soustraitant", unique: "false") {
            column(name: "email")
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-415") {
        createIndex(indexName: "soustraitant_fax", tableName: "soustraitant", unique: "false") {
            column(name: "fax")
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-416") {
        createIndex(indexName: "soustraitant_nom", tableName: "soustraitant", unique: "false") {
            column(name: "nom")
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-417") {
        createIndex(indexName: "soustraitant_numero", tableName: "soustraitant", unique: "false") {
            column(name: "numero")
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-418") {
        createIndex(indexName: "soustraitant_telephone", tableName: "soustraitant", unique: "false") {
            column(name: "telephone")
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-419") {
        createIndex(indexName: "soustraitant_ville", tableName: "soustraitant", unique: "false") {
            column(name: "ville")
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-420") {
        createIndex(indexName: "tache_avancement_prevu", tableName: "tache", unique: "false") {
            column(name: "avancement_prevu")
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-421") {
        createIndex(indexName: "tache_avancement_reel", tableName: "tache", unique: "false") {
            column(name: "avancement_reel")
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-422") {
        createIndex(indexName: "tache_charge_prevue", tableName: "tache", unique: "false") {
            column(name: "charge_prevue")
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-423") {
        createIndex(indexName: "tache_charge_reelle", tableName: "tache", unique: "false") {
            column(name: "charge_reelle")
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-424") {
        createIndex(indexName: "tache_date_debut_prevue", tableName: "tache", unique: "false") {
            column(name: "date_debut_prevue")
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-425") {
        createIndex(indexName: "tache_date_debut_reelle", tableName: "tache", unique: "false") {
            column(name: "date_debut_reelle")
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-426") {
        createIndex(indexName: "tache_date_fin_prevu", tableName: "tache", unique: "false") {
            column(name: "date_fin_prevu")
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-427") {
        createIndex(indexName: "tache_date_fin_reelle", tableName: "tache", unique: "false") {
            column(name: "date_fin_reelle")
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-428") {
        createIndex(indexName: "tache_designation", tableName: "tache", unique: "false") {
            column(name: "designation")
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-429") {
        createIndex(indexName: "tache_jalon", tableName: "tache", unique: "false") {
            column(name: "jalon")
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-430") {
        createIndex(indexName: "tache_numero", tableName: "tache", unique: "false") {
            column(name: "numero")
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-431") {
        createIndex(indexName: "unitemesure_libelle", tableName: "unite_mesure", unique: "false") {
            column(name: "libelle")
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-432") {
        createIndex(indexName: "ville_intitule", tableName: "ville", unique: "false") {
            column(name: "intitule")
        }
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-433") {
        addForeignKeyConstraint(baseColumnNames: "employe_id", baseTableName: "affectation_outil", baseTableSchemaName: "public", constraintName: "fk6d92ccf25a166235", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "employe", referencedTableSchemaName: "public", referencesUniqueColumn: "false")
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-434") {
        addForeignKeyConstraint(baseColumnNames: "outil_employe_id", baseTableName: "affectation_outil", baseTableSchemaName: "public", constraintName: "fk6d92ccf22ee3953c", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "outil_employe", referencedTableSchemaName: "public", referencesUniqueColumn: "false")
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-435") {
        addForeignKeyConstraint(baseColumnNames: "projet_id", baseTableName: "affectation_outil", baseTableSchemaName: "public", constraintName: "fk6d92ccf22aa47aff", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "projet", referencedTableSchemaName: "public", referencesUniqueColumn: "false")
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-436") {
        addForeignKeyConstraint(baseColumnNames: "employe_id", baseTableName: "affectation_outillage", baseTableSchemaName: "public", constraintName: "fk9cd195055a166235", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "employe", referencedTableSchemaName: "public", referencesUniqueColumn: "false")
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-437") {
        addForeignKeyConstraint(baseColumnNames: "outillage_collectif_id", baseTableName: "affectation_outillage", baseTableSchemaName: "public", constraintName: "fk9cd19505fa2ec756", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "outillage_collectif", referencedTableSchemaName: "public", referencesUniqueColumn: "false")
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-438") {
        addForeignKeyConstraint(baseColumnNames: "projet_id", baseTableName: "affectation_outillage", baseTableSchemaName: "public", constraintName: "fk9cd195052aa47aff", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "projet", referencedTableSchemaName: "public", referencesUniqueColumn: "false")
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-439") {
        addForeignKeyConstraint(baseColumnNames: "bon_livraison_id", baseTableName: "affectation_produit", baseTableSchemaName: "public", constraintName: "fk83927d6ad4433751", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "bon_livraison", referencedTableSchemaName: "public", referencesUniqueColumn: "false")
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-440") {
        addForeignKeyConstraint(baseColumnNames: "produit_id", baseTableName: "affectation_produit", baseTableSchemaName: "public", constraintName: "fk83927d6aa267d6a0", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "produit", referencedTableSchemaName: "public", referencesUniqueColumn: "false")
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-441") {
        addForeignKeyConstraint(baseColumnNames: "projet_id", baseTableName: "affectation_produit", baseTableSchemaName: "public", constraintName: "fk83927d6a2aa47aff", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "projet", referencedTableSchemaName: "public", referencesUniqueColumn: "false")
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-442") {
        addForeignKeyConstraint(baseColumnNames: "raison_declanchement_id", baseTableName: "alerts_securite_stock", baseTableSchemaName: "public", constraintName: "fk2e2d2aeb8df6b80a", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "mouvement_stock", referencedTableSchemaName: "public", referencesUniqueColumn: "false")
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-443") {
        addForeignKeyConstraint(baseColumnNames: "raison_relachement_id", baseTableName: "alerts_securite_stock", baseTableSchemaName: "public", constraintName: "fk2e2d2aebf2aa291b", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "mouvement_stock", referencedTableSchemaName: "public", referencesUniqueColumn: "false")
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-444") {
        addForeignKeyConstraint(baseColumnNames: "partenaire_id", baseTableName: "bon_commande", baseTableSchemaName: "public", constraintName: "fk5a34a7384c49cb14", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "partenaire", referencedTableSchemaName: "public", referencesUniqueColumn: "false")
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-445") {
        addForeignKeyConstraint(baseColumnNames: "utilisateur_id", baseTableName: "bon_commande", baseTableSchemaName: "public", constraintName: "fk5a34a738a6f86c55", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "utilisateur", referencedTableSchemaName: "public", referencesUniqueColumn: "false")
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-446") {
        addForeignKeyConstraint(baseColumnNames: "bon_commande_deviss_id", baseTableName: "bon_commande_devis", baseTableSchemaName: "public", constraintName: "fk4e05a038e3e59680", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "bon_commande", referencedTableSchemaName: "public", referencesUniqueColumn: "false")
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-447") {
        addForeignKeyConstraint(baseColumnNames: "devis_id", baseTableName: "bon_commande_devis", baseTableSchemaName: "public", constraintName: "fk4e05a038a3485860", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "devis", referencedTableSchemaName: "public", referencesUniqueColumn: "false")
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-448") {
        addForeignKeyConstraint(baseColumnNames: "bon_commande_ligne_produits_id", baseTableName: "bon_commande_ligne_produit", baseTableSchemaName: "public", constraintName: "fkaaa4d0647fd8d0ac", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "bon_commande", referencedTableSchemaName: "public", referencesUniqueColumn: "false")
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-449") {
        addForeignKeyConstraint(baseColumnNames: "ligne_produit_id", baseTableName: "bon_commande_ligne_produit", baseTableSchemaName: "public", constraintName: "fkaaa4d064978ebb91", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "ligne_produit", referencedTableSchemaName: "public", referencesUniqueColumn: "false")
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-450") {
        addForeignKeyConstraint(baseColumnNames: "bon_commande_id", baseTableName: "bon_livraison", baseTableSchemaName: "public", constraintName: "fk981f5e13a3b5f963", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "bon_commande", referencedTableSchemaName: "public", referencesUniqueColumn: "false")
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-451") {
        addForeignKeyConstraint(baseColumnNames: "livraison_id", baseTableName: "bon_livraison", baseTableSchemaName: "public", constraintName: "fk981f5e13896cb420", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "livraison", referencedTableSchemaName: "public", referencesUniqueColumn: "false")
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-452") {
        addForeignKeyConstraint(baseColumnNames: "bon_livraison_ligne_produits_id", baseTableName: "bon_livraison_ligne_produit", baseTableSchemaName: "public", constraintName: "fk234cebffc51bcd70", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "bon_livraison", referencedTableSchemaName: "public", referencesUniqueColumn: "false")
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-453") {
        addForeignKeyConstraint(baseColumnNames: "ligne_produit_id", baseTableName: "bon_livraison_ligne_produit", baseTableSchemaName: "public", constraintName: "fk234cebff978ebb91", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "ligne_produit", referencedTableSchemaName: "public", referencesUniqueColumn: "false")
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-454") {
        addForeignKeyConstraint(baseColumnNames: "bon_commande_id", baseTableName: "bon_pret", baseTableSchemaName: "public", constraintName: "fk752b4defa3b5f963", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "bon_commande", referencedTableSchemaName: "public", referencesUniqueColumn: "false")
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-455") {
        addForeignKeyConstraint(baseColumnNames: "partenaire_id", baseTableName: "bon_pret", baseTableSchemaName: "public", constraintName: "fk752b4def4c49cb14", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "partenaire", referencedTableSchemaName: "public", referencesUniqueColumn: "false")
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-456") {
        addForeignKeyConstraint(baseColumnNames: "utilisateur_id", baseTableName: "bon_pret", baseTableSchemaName: "public", constraintName: "fk752b4defa6f86c55", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "utilisateur", referencedTableSchemaName: "public", referencesUniqueColumn: "false")
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-457") {
        addForeignKeyConstraint(baseColumnNames: "bon_pret_deviss_id", baseTableName: "bon_pret_devis", baseTableSchemaName: "public", constraintName: "fkf37c21af24e7ae", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "bon_pret", referencedTableSchemaName: "public", referencesUniqueColumn: "false")
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-458") {
        addForeignKeyConstraint(baseColumnNames: "devis_id", baseTableName: "bon_pret_devis", baseTableSchemaName: "public", constraintName: "fkf37c21afa3485860", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "devis", referencedTableSchemaName: "public", referencesUniqueColumn: "false")
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-459") {
        addForeignKeyConstraint(baseColumnNames: "bon_pret_ligne_produits_id", baseTableName: "bon_pret_ligne_produit", baseTableSchemaName: "public", constraintName: "fke1d9eadb8ae23ada", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "bon_pret", referencedTableSchemaName: "public", referencesUniqueColumn: "false")
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-460") {
        addForeignKeyConstraint(baseColumnNames: "ligne_produit_id", baseTableName: "bon_pret_ligne_produit", baseTableSchemaName: "public", constraintName: "fke1d9eadb978ebb91", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "ligne_produit", referencedTableSchemaName: "public", referencesUniqueColumn: "false")
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-461") {
        addForeignKeyConstraint(baseColumnNames: "calendrier_jour_feriers_id", baseTableName: "calendrier_jour_ferier", baseTableSchemaName: "public", constraintName: "fka48085240bdf2ef", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "calendrier", referencedTableSchemaName: "public", referencesUniqueColumn: "false")
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-462") {
        addForeignKeyConstraint(baseColumnNames: "jour_ferier_id", baseTableName: "calendrier_jour_ferier", baseTableSchemaName: "public", constraintName: "fka480852c1a9d504", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "jour_ferier", referencedTableSchemaName: "public", referencesUniqueColumn: "false")
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-463") {
        addForeignKeyConstraint(baseColumnNames: "categorie_parente_id", baseTableName: "categorie_partenaire", baseTableSchemaName: "public", constraintName: "fkcbcafe3fe6bd7a3b", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "categorie_partenaire", referencedTableSchemaName: "public", referencesUniqueColumn: "false")
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-464") {
        addForeignKeyConstraint(baseColumnNames: "categorie_parente_id", baseTableName: "categorie_produit", baseTableSchemaName: "public", constraintName: "fk43b52ea16b620893", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "categorie_produit", referencedTableSchemaName: "public", referencesUniqueColumn: "false")
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-465") {
        addForeignKeyConstraint(baseColumnNames: "projet_id", baseTableName: "charge_divers", baseTableSchemaName: "public", constraintName: "fk85a597202aa47aff", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "projet", referencedTableSchemaName: "public", referencesUniqueColumn: "false")
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-466") {
        addForeignKeyConstraint(baseColumnNames: "regime_id", baseTableName: "chora_client_info", baseTableSchemaName: "public", constraintName: "fk900c7dbc94511279", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "regime_declaration_tva", referencedTableSchemaName: "public", referencesUniqueColumn: "false")
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-467") {
        addForeignKeyConstraint(baseColumnNames: "module_id", baseTableName: "chora_client_info_module", baseTableSchemaName: "public", constraintName: "fk28757a4f689abbdf", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "module", referencedTableSchemaName: "public", referencesUniqueColumn: "false")
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-468") {
        addForeignKeyConstraint(baseColumnNames: "agence_bancaire_id", baseTableName: "compte_bancaire", baseTableSchemaName: "public", constraintName: "fk94bf02ed38fb6d7", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "agence_bancaire", referencedTableSchemaName: "public", referencesUniqueColumn: "false")
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-469") {
        addForeignKeyConstraint(baseColumnNames: "partenaire_id", baseTableName: "compte_bancaire", baseTableSchemaName: "public", constraintName: "fk94bf02e4c49cb14", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "partenaire", referencedTableSchemaName: "public", referencesUniqueColumn: "false")
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-470") {
        addForeignKeyConstraint(baseColumnNames: "agence_bancaire_id", baseTableName: "compte_bancaire_chora_client_info", baseTableSchemaName: "public", constraintName: "fk9efce7ebd38fb6d7", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "agence_bancaire", referencedTableSchemaName: "public", referencesUniqueColumn: "false")
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-471") {
        addForeignKeyConstraint(baseColumnNames: "parent_id", baseTableName: "compte_comptable", baseTableSchemaName: "public", constraintName: "fk45179e601f4f7ada", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "compte_comptable", referencedTableSchemaName: "public", referencesUniqueColumn: "false")
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-472") {
        addForeignKeyConstraint(baseColumnNames: "categorie_produit_id", baseTableName: "compte_comptable_produit", baseTableSchemaName: "public", constraintName: "fk8e60b2cae2c16125", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "categorie_produit", referencedTableSchemaName: "public", referencesUniqueColumn: "false")
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-473") {
        addForeignKeyConstraint(baseColumnNames: "compte_comptable_id", baseTableName: "compte_comptable_produit", baseTableSchemaName: "public", constraintName: "fk8e60b2cafb718164", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "compte_comptable", referencedTableSchemaName: "public", referencesUniqueColumn: "false")
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-474") {
        addForeignKeyConstraint(baseColumnNames: "partenaire_id", baseTableName: "contact", baseTableSchemaName: "public", constraintName: "fk38b724204c49cb14", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "partenaire", referencedTableSchemaName: "public", referencesUniqueColumn: "false")
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-475") {
        addForeignKeyConstraint(baseColumnNames: "declaration_tva_factures_id", baseTableName: "declaration_tva_facture", baseTableSchemaName: "public", constraintName: "fk8ed18e17fea138fd", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "declaration_tva", referencedTableSchemaName: "public", referencesUniqueColumn: "false")
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-476") {
        addForeignKeyConstraint(baseColumnNames: "facture_id", baseTableName: "declaration_tva_facture", baseTableSchemaName: "public", constraintName: "fk8ed18e1780a921c0", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "facture", referencedTableSchemaName: "public", referencesUniqueColumn: "false")
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-477") {
        addForeignKeyConstraint(baseColumnNames: "declaration_tva_paiements_id", baseTableName: "declaration_tva_paiement", baseTableSchemaName: "public", constraintName: "fk6391d6706ee3acd6", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "declaration_tva", referencedTableSchemaName: "public", referencesUniqueColumn: "false")
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-478") {
        addForeignKeyConstraint(baseColumnNames: "paiement_id", baseTableName: "declaration_tva_paiement", baseTableSchemaName: "public", constraintName: "fk6391d670242c0d14", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "paiement", referencedTableSchemaName: "public", referencesUniqueColumn: "false")
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-479") {
        addForeignKeyConstraint(baseColumnNames: "nature_de_charge_id", baseTableName: "depenses", baseTableSchemaName: "public", constraintName: "fk37acb069bdcddcf6", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "nature_de_charge", referencedTableSchemaName: "public", referencesUniqueColumn: "false")
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-480") {
        addForeignKeyConstraint(baseColumnNames: "partenaire_id", baseTableName: "devis", baseTableSchemaName: "public", constraintName: "fk5b0dfff4c49cb14", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "partenaire", referencedTableSchemaName: "public", referencesUniqueColumn: "false")
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-481") {
        addForeignKeyConstraint(baseColumnNames: "devis_ligne_produits_id", baseTableName: "devis_ligne_produit", baseTableSchemaName: "public", constraintName: "fk65eea0ebfbe826d7", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "devis", referencedTableSchemaName: "public", referencesUniqueColumn: "false")
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-482") {
        addForeignKeyConstraint(baseColumnNames: "ligne_produit_id", baseTableName: "devis_ligne_produit", baseTableSchemaName: "public", constraintName: "fk65eea0eb978ebb91", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "ligne_produit", referencedTableSchemaName: "public", referencesUniqueColumn: "false")
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-483") {
        addForeignKeyConstraint(baseColumnNames: "parent_id", baseTableName: "droit_utilisateur", baseTableSchemaName: "public", constraintName: "fk5acbce50533d758c", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "droit_utilisateur", referencedTableSchemaName: "public", referencesUniqueColumn: "false")
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-484") {
        addForeignKeyConstraint(baseColumnNames: "compte_comptable_id", baseTableName: "ecriture", baseTableSchemaName: "public", constraintName: "fk39663949fb718164", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "compte_comptable", referencedTableSchemaName: "public", referencesUniqueColumn: "false")
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-485") {
        addForeignKeyConstraint(baseColumnNames: "devise_id", baseTableName: "ecriture", baseTableSchemaName: "public", constraintName: "fk39663949a3a639cf", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "devise", referencedTableSchemaName: "public", referencesUniqueColumn: "false")
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-486") {
        addForeignKeyConstraint(baseColumnNames: "ligne_produit_id", baseTableName: "ecriture", baseTableSchemaName: "public", constraintName: "fk39663949978ebb91", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "ligne_produit", referencedTableSchemaName: "public", referencesUniqueColumn: "false")
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-487") {
        addForeignKeyConstraint(baseColumnNames: "paiement_id", baseTableName: "ecriture", baseTableSchemaName: "public", constraintName: "fk39663949242c0d14", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "paiement", referencedTableSchemaName: "public", referencesUniqueColumn: "false")
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-488") {
        addForeignKeyConstraint(baseColumnNames: "periode_id", baseTableName: "ecriture", baseTableSchemaName: "public", constraintName: "fk3966394918c11005", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "periode", referencedTableSchemaName: "public", referencesUniqueColumn: "false")
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-489") {
        addForeignKeyConstraint(baseColumnNames: "calendrier_id", baseTableName: "employe", baseTableSchemaName: "public", constraintName: "fk9f32ac97e45e25f", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "calendrier", referencedTableSchemaName: "public", referencesUniqueColumn: "false")
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-490") {
        addForeignKeyConstraint(baseColumnNames: "fonction_id", baseTableName: "employe", baseTableSchemaName: "public", constraintName: "fk9f32ac97524667ff", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "fonction", referencedTableSchemaName: "public", referencesUniqueColumn: "false")
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-491") {
        addForeignKeyConstraint(baseColumnNames: "partenaire_id", baseTableName: "facture", baseTableSchemaName: "public", constraintName: "fkbeeb477c4c49cb14", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "partenaire", referencedTableSchemaName: "public", referencesUniqueColumn: "false")
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-492") {
        addForeignKeyConstraint(baseColumnNames: "bon_commande_id", baseTableName: "facture_bon_commande", baseTableSchemaName: "public", constraintName: "fkde21d31ba3b5f963", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "bon_commande", referencedTableSchemaName: "public", referencesUniqueColumn: "false")
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-493") {
        addForeignKeyConstraint(baseColumnNames: "facture_bon_commandes_id", baseTableName: "facture_bon_commande", baseTableSchemaName: "public", constraintName: "fkde21d31b8fa67f04", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "facture", referencedTableSchemaName: "public", referencesUniqueColumn: "false")
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-494") {
        addForeignKeyConstraint(baseColumnNames: "facture_paiements_id", baseTableName: "facture_paiement", baseTableSchemaName: "public", constraintName: "fkc77bdf0ee4635977", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "facture", referencedTableSchemaName: "public", referencesUniqueColumn: "false")
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-495") {
        addForeignKeyConstraint(baseColumnNames: "paiement_id", baseTableName: "facture_paiement", baseTableSchemaName: "public", constraintName: "fkc77bdf0e242c0d14", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "paiement", referencedTableSchemaName: "public", referencesUniqueColumn: "false")
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-496") {
        addForeignKeyConstraint(baseColumnNames: "employe_id", baseTableName: "fiche_presence", baseTableSchemaName: "public", constraintName: "fk62fe01fd5a166235", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "employe", referencedTableSchemaName: "public", referencesUniqueColumn: "false")
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-497") {
        addForeignKeyConstraint(baseColumnNames: "projet_id", baseTableName: "fiche_presence", baseTableSchemaName: "public", constraintName: "fk62fe01fd2aa47aff", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "projet", referencedTableSchemaName: "public", referencesUniqueColumn: "false")
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-498") {
        addForeignKeyConstraint(baseColumnNames: "droit_utilisateur_id", baseTableName: "groupe_utilisateur_droit_utilisateur", baseTableSchemaName: "public", constraintName: "fk2b88dbdbe2f60626", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "droit_utilisateur", referencedTableSchemaName: "public", referencesUniqueColumn: "false")
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-499") {
        addForeignKeyConstraint(baseColumnNames: "groupe_utilisateur_droits_id", baseTableName: "groupe_utilisateur_droit_utilisateur", baseTableSchemaName: "public", constraintName: "fk2b88dbdbb6ea5896", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "groupe_utilisateur", referencedTableSchemaName: "public", referencesUniqueColumn: "false")
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-500") {
        addForeignKeyConstraint(baseColumnNames: "entrepot_id", baseTableName: "inventaire", baseTableSchemaName: "public", constraintName: "fk6a6c92b58391d194", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "entrepot", referencedTableSchemaName: "public", referencesUniqueColumn: "false")
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-501") {
        addForeignKeyConstraint(baseColumnNames: "inventaire_ligne_inventaires_id", baseTableName: "inventaire_ligne_inventaire", baseTableSchemaName: "public", constraintName: "fk6daa303dc2baad93", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "inventaire", referencedTableSchemaName: "public", referencesUniqueColumn: "false")
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-502") {
        addForeignKeyConstraint(baseColumnNames: "ligne_inventaire_id", baseTableName: "inventaire_ligne_inventaire", baseTableSchemaName: "public", constraintName: "fk6daa303ddc093bc3", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "ligne_inventaire", referencedTableSchemaName: "public", referencesUniqueColumn: "false")
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-503") {
        addForeignKeyConstraint(baseColumnNames: "compte_comptable_id", baseTableName: "journal_compte_comptable", baseTableSchemaName: "public", constraintName: "fk3227c668fb718164", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "compte_comptable", referencedTableSchemaName: "public", referencesUniqueColumn: "false")
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-504") {
        addForeignKeyConstraint(baseColumnNames: "journal_compte_comptables_id", baseTableName: "journal_compte_comptable", baseTableSchemaName: "public", constraintName: "fk3227c668886e7ed1", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "journal", referencedTableSchemaName: "public", referencesUniqueColumn: "false")
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-505") {
        addForeignKeyConstraint(baseColumnNames: "produit_id", baseTableName: "ligne_inventaire", baseTableSchemaName: "public", constraintName: "fk15723ff3a267d6a0", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "produit", referencedTableSchemaName: "public", referencesUniqueColumn: "false")
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-506") {
        addForeignKeyConstraint(baseColumnNames: "entrepot_id", baseTableName: "ligne_produit", baseTableSchemaName: "public", constraintName: "fk9fd3592b8391d194", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "entrepot", referencedTableSchemaName: "public", referencesUniqueColumn: "false")
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-507") {
        addForeignKeyConstraint(baseColumnNames: "produit_id", baseTableName: "ligne_produit", baseTableSchemaName: "public", constraintName: "fk9fd3592ba267d6a0", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "produit", referencedTableSchemaName: "public", referencesUniqueColumn: "false")
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-508") {
        addForeignKeyConstraint(baseColumnNames: "mode_livraison_id", baseTableName: "livraison", baseTableSchemaName: "public", constraintName: "fk526a6311e7477fd9", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "mode_livraison", referencedTableSchemaName: "public", referencesUniqueColumn: "false")
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-509") {
        addForeignKeyConstraint(baseColumnNames: "partenaire_id", baseTableName: "livraison", baseTableSchemaName: "public", constraintName: "fk526a63114c49cb14", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "partenaire", referencedTableSchemaName: "public", referencesUniqueColumn: "false")
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-510") {
        addForeignKeyConstraint(baseColumnNames: "loueur_id", baseTableName: "location_outil", baseTableSchemaName: "public", constraintName: "fk813c7e7d0b1f07f", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "loueur", referencedTableSchemaName: "public", referencesUniqueColumn: "false")
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-511") {
        addForeignKeyConstraint(baseColumnNames: "outil_louable_id", baseTableName: "location_outil", baseTableSchemaName: "public", constraintName: "fk813c7e72600411c", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "outil_louable", referencedTableSchemaName: "public", referencesUniqueColumn: "false")
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-512") {
        addForeignKeyConstraint(baseColumnNames: "projet_id", baseTableName: "location_outil", baseTableSchemaName: "public", constraintName: "fk813c7e72aa47aff", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "projet", referencedTableSchemaName: "public", referencesUniqueColumn: "false")
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-513") {
        addForeignKeyConstraint(baseColumnNames: "emp_destination_id", baseTableName: "mouvement_stock", baseTableSchemaName: "public", constraintName: "fk4395d377dcc42bb4", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "entrepot", referencedTableSchemaName: "public", referencesUniqueColumn: "false")
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-514") {
        addForeignKeyConstraint(baseColumnNames: "emp_source_id", baseTableName: "mouvement_stock", baseTableSchemaName: "public", constraintName: "fk4395d3777ea25d79", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "entrepot", referencedTableSchemaName: "public", referencesUniqueColumn: "false")
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-515") {
        addForeignKeyConstraint(baseColumnNames: "produit_id", baseTableName: "mouvement_stock", baseTableSchemaName: "public", constraintName: "fk4395d377a267d6a0", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "produit", referencedTableSchemaName: "public", referencesUniqueColumn: "false")
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-516") {
        addForeignKeyConstraint(baseColumnNames: "utilisateur_id", baseTableName: "mouvement_stock", baseTableSchemaName: "public", constraintName: "fk4395d377a6f86c55", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "utilisateur", referencedTableSchemaName: "public", referencesUniqueColumn: "false")
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-517") {
        addForeignKeyConstraint(baseColumnNames: "projet_id", baseTableName: "note", baseTableSchemaName: "public", constraintName: "fk33aff22aa47aff", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "projet", referencedTableSchemaName: "public", referencesUniqueColumn: "false")
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-518") {
        addForeignKeyConstraint(baseColumnNames: "employe_id", baseTableName: "outil_employe", baseTableSchemaName: "public", constraintName: "fk5161f3495a166235", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "employe", referencedTableSchemaName: "public", referencesUniqueColumn: "false")
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-519") {
        addForeignKeyConstraint(baseColumnNames: "loueur_id", baseTableName: "outil_louable", baseTableSchemaName: "public", constraintName: "fkc758865ed0b1f07f", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "loueur", referencedTableSchemaName: "public", referencesUniqueColumn: "false")
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-520") {
        addForeignKeyConstraint(baseColumnNames: "bon_commande_id", baseTableName: "paiement", baseTableSchemaName: "public", constraintName: "fk36af49aba3b5f963", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "bon_commande", referencedTableSchemaName: "public", referencesUniqueColumn: "false")
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-521") {
        addForeignKeyConstraint(baseColumnNames: "compte_bancaire_id", baseTableName: "paiement", baseTableSchemaName: "public", constraintName: "fk36af49abd09065b5", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "compte_bancaire_chora_client_info", referencedTableSchemaName: "public", referencesUniqueColumn: "false")
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-522") {
        addForeignKeyConstraint(baseColumnNames: "compte_bancaire_partenaire_id", baseTableName: "paiement", baseTableSchemaName: "public", constraintName: "fk36af49abdbcd6bfb", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "compte_bancaire", referencedTableSchemaName: "public", referencesUniqueColumn: "false")
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-523") {
        addForeignKeyConstraint(baseColumnNames: "mode_reglement_id", baseTableName: "paiement", baseTableSchemaName: "public", constraintName: "fk36af49ab9ba1999", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "mode_reglement", referencedTableSchemaName: "public", referencesUniqueColumn: "false")
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-524") {
        addForeignKeyConstraint(baseColumnNames: "partenaire_id", baseTableName: "paiement", baseTableSchemaName: "public", constraintName: "fk36af49ab4c49cb14", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "partenaire", referencedTableSchemaName: "public", referencesUniqueColumn: "false")
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-525") {
        addForeignKeyConstraint(baseColumnNames: "employe_id", baseTableName: "paiement_jbl", baseTableSchemaName: "public", constraintName: "fk557945a05a166235", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "employe", referencedTableSchemaName: "public", referencesUniqueColumn: "false")
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-526") {
        addForeignKeyConstraint(baseColumnNames: "echeance_id", baseTableName: "partenaire", baseTableSchemaName: "public", constraintName: "fkfb3da23797fb30b4", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "echeance", referencedTableSchemaName: "public", referencesUniqueColumn: "false")
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-527") {
        addForeignKeyConstraint(baseColumnNames: "forme_juridique_id", baseTableName: "partenaire", baseTableSchemaName: "public", constraintName: "fkfb3da2376790a371", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "forme_juridique", referencedTableSchemaName: "public", referencesUniqueColumn: "false")
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-528") {
        addForeignKeyConstraint(baseColumnNames: "mode_livraison_id", baseTableName: "partenaire", baseTableSchemaName: "public", constraintName: "fkfb3da237e7477fd9", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "mode_livraison", referencedTableSchemaName: "public", referencesUniqueColumn: "false")
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-529") {
        addForeignKeyConstraint(baseColumnNames: "mode_reglement_defaut_id", baseTableName: "partenaire", baseTableSchemaName: "public", constraintName: "fkfb3da237739705dd", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "mode_reglement", referencedTableSchemaName: "public", referencesUniqueColumn: "false")
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-530") {
        addForeignKeyConstraint(baseColumnNames: "mode_relance_paiement_id", baseTableName: "partenaire", baseTableSchemaName: "public", constraintName: "fkfb3da237faa6ad2", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "mode_relance_paiement", referencedTableSchemaName: "public", referencesUniqueColumn: "false")
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-531") {
        addForeignKeyConstraint(baseColumnNames: "ville_id", baseTableName: "partenaire", baseTableSchemaName: "public", constraintName: "fkfb3da237b4c93380", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "ville", referencedTableSchemaName: "public", referencesUniqueColumn: "false")
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-532") {
        addForeignKeyConstraint(baseColumnNames: "categorie_partenaire_id", baseTableName: "partenaire_categorie_partenaire", baseTableSchemaName: "public", constraintName: "fk5c44cf47c048766f", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "categorie_partenaire", referencedTableSchemaName: "public", referencesUniqueColumn: "false")
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-533") {
        addForeignKeyConstraint(baseColumnNames: "partenaire_categorie_partenaires_id", baseTableName: "partenaire_categorie_partenaire", baseTableSchemaName: "public", constraintName: "fk5c44cf47264bc71f", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "partenaire", referencedTableSchemaName: "public", referencesUniqueColumn: "false")
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-534") {
        addForeignKeyConstraint(baseColumnNames: "exercice_id", baseTableName: "periode", baseTableSchemaName: "public", constraintName: "fkd78b42c4c8c95d4f", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "exercice", referencedTableSchemaName: "public", referencesUniqueColumn: "false")
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-535") {
        addForeignKeyConstraint(baseColumnNames: "projet_id", baseTableName: "phase", baseTableSchemaName: "public", constraintName: "fk65b097b2aa47aff", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "projet", referencedTableSchemaName: "public", referencesUniqueColumn: "false")
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-536") {
        addForeignKeyConstraint(baseColumnNames: "document_numerique_id", baseTableName: "phase_document_numerique", baseTableSchemaName: "public", constraintName: "fke51fd20bc49bcf57", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "document_numerique", referencedTableSchemaName: "public", referencesUniqueColumn: "false")
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-537") {
        addForeignKeyConstraint(baseColumnNames: "phase_documents_id", baseTableName: "phase_document_numerique", baseTableSchemaName: "public", constraintName: "fke51fd20b1eeb745c", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "phase", referencedTableSchemaName: "public", referencesUniqueColumn: "false")
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-538") {
        addForeignKeyConstraint(baseColumnNames: "categorie_produit_id", baseTableName: "produit", baseTableSchemaName: "public", constraintName: "fked8dcda9e2c16125", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "categorie_produit", referencedTableSchemaName: "public", referencesUniqueColumn: "false")
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-539") {
        addForeignKeyConstraint(baseColumnNames: "emp_reception_id", baseTableName: "produit", baseTableSchemaName: "public", constraintName: "fked8dcda993e9f713", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "entrepot", referencedTableSchemaName: "public", referencesUniqueColumn: "false")
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-540") {
        addForeignKeyConstraint(baseColumnNames: "nature_id", baseTableName: "produit", baseTableSchemaName: "public", constraintName: "fked8dcda9ac63e214", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "nature", referencedTableSchemaName: "public", referencesUniqueColumn: "false")
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-541") {
        addForeignKeyConstraint(baseColumnNames: "produit_parent_id", baseTableName: "produit", baseTableSchemaName: "public", constraintName: "fked8dcda9eba9b8e9", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "produit", referencedTableSchemaName: "public", referencesUniqueColumn: "false")
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-542") {
        addForeignKeyConstraint(baseColumnNames: "regimetva_id", baseTableName: "produit", baseTableSchemaName: "public", constraintName: "fked8dcda99c38a460", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "regimetva", referencedTableSchemaName: "public", referencesUniqueColumn: "false")
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-543") {
        addForeignKeyConstraint(baseColumnNames: "unite_mesure_id", baseTableName: "produit", baseTableSchemaName: "public", constraintName: "fked8dcda99ea63883", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "unite_mesure", referencedTableSchemaName: "public", referencesUniqueColumn: "false")
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-544") {
        addForeignKeyConstraint(baseColumnNames: "bon_commande_id", baseTableName: "projet", baseTableSchemaName: "public", constraintName: "fkc5994ccca3b5f963", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "bon_commande", referencedTableSchemaName: "public", referencesUniqueColumn: "false")
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-545") {
        addForeignKeyConstraint(baseColumnNames: "calendrier_id", baseTableName: "projet", baseTableSchemaName: "public", constraintName: "fkc5994ccce45e25f", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "calendrier", referencedTableSchemaName: "public", referencesUniqueColumn: "false")
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-546") {
        addForeignKeyConstraint(baseColumnNames: "chef_chantier_id", baseTableName: "projet", baseTableSchemaName: "public", constraintName: "fkc5994ccc607a9a7f", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "employe", referencedTableSchemaName: "public", referencesUniqueColumn: "false")
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-547") {
        addForeignKeyConstraint(baseColumnNames: "document_numerique_id", baseTableName: "projet_document_numerique", baseTableSchemaName: "public", constraintName: "fk5671831ac49bcf57", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "document_numerique", referencedTableSchemaName: "public", referencesUniqueColumn: "false")
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-548") {
        addForeignKeyConstraint(baseColumnNames: "projet_documents_id", baseTableName: "projet_document_numerique", baseTableSchemaName: "public", constraintName: "fk5671831a3a6fe626", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "projet", referencedTableSchemaName: "public", referencesUniqueColumn: "false")
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-549") {
        addForeignKeyConstraint(baseColumnNames: "partenaire_id", baseTableName: "regle_facturation_automatique_partenaire", baseTableSchemaName: "public", constraintName: "fkbb95b9324c49cb14", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "partenaire", referencedTableSchemaName: "public", referencesUniqueColumn: "false")
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-550") {
        addForeignKeyConstraint(baseColumnNames: "regle_facturation_automatique_partenaires_id", baseTableName: "regle_facturation_automatique_partenaire", baseTableSchemaName: "public", constraintName: "fkbb95b932f535b0e9", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "regle_facturation_automatique", referencedTableSchemaName: "public", referencesUniqueColumn: "false")
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-551") {
        addForeignKeyConstraint(baseColumnNames: "categorie_partenaire_id", baseTableName: "regle_prix_categorie_partenaire", baseTableSchemaName: "public", constraintName: "fk1b27225bc048766f", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "categorie_partenaire", referencedTableSchemaName: "public", referencesUniqueColumn: "false")
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-552") {
        addForeignKeyConstraint(baseColumnNames: "regle_prix_categorie_partenaires_id", baseTableName: "regle_prix_categorie_partenaire", baseTableSchemaName: "public", constraintName: "fk1b27225b899356a6", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "regle_prix", referencedTableSchemaName: "public", referencesUniqueColumn: "false")
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-553") {
        addForeignKeyConstraint(baseColumnNames: "categorie_produit_id", baseTableName: "regle_prix_categorie_produit", baseTableSchemaName: "public", constraintName: "fk679ce005e2c16125", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "categorie_produit", referencedTableSchemaName: "public", referencesUniqueColumn: "false")
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-554") {
        addForeignKeyConstraint(baseColumnNames: "regle_prix_categorie_produits_id", baseTableName: "regle_prix_categorie_produit", baseTableSchemaName: "public", constraintName: "fk679ce00543e2f50", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "regle_prix", referencedTableSchemaName: "public", referencesUniqueColumn: "false")
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-555") {
        addForeignKeyConstraint(baseColumnNames: "partenaire_id", baseTableName: "regle_prix_partenaire", baseTableSchemaName: "public", constraintName: "fkc416f9534c49cb14", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "partenaire", referencedTableSchemaName: "public", referencesUniqueColumn: "false")
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-556") {
        addForeignKeyConstraint(baseColumnNames: "regle_prix_partenaires_id", baseTableName: "regle_prix_partenaire", baseTableSchemaName: "public", constraintName: "fkc416f953f84ef19e", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "regle_prix", referencedTableSchemaName: "public", referencesUniqueColumn: "false")
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-557") {
        addForeignKeyConstraint(baseColumnNames: "produit_id", baseTableName: "regle_prix_produit", baseTableSchemaName: "public", constraintName: "fk7d3d2c0da267d6a0", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "produit", referencedTableSchemaName: "public", referencesUniqueColumn: "false")
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-558") {
        addForeignKeyConstraint(baseColumnNames: "regle_prix_produits_id", baseTableName: "regle_prix_produit", baseTableSchemaName: "public", constraintName: "fk7d3d2c0d79493758", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "regle_prix", referencedTableSchemaName: "public", referencesUniqueColumn: "false")
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-559") {
        addForeignKeyConstraint(baseColumnNames: "entrepot_id", baseTableName: "securitestock", baseTableSchemaName: "public", constraintName: "fkb5001b6a8391d194", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "entrepot", referencedTableSchemaName: "public", referencesUniqueColumn: "false")
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-560") {
        addForeignKeyConstraint(baseColumnNames: "produit_id", baseTableName: "securitestock", baseTableSchemaName: "public", constraintName: "fkb5001b6aa267d6a0", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "produit", referencedTableSchemaName: "public", referencesUniqueColumn: "false")
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-561") {
        addForeignKeyConstraint(baseColumnNames: "projet_id", baseTableName: "soustraitance", baseTableSchemaName: "public", constraintName: "fk7aa81fa32aa47aff", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "projet", referencedTableSchemaName: "public", referencesUniqueColumn: "false")
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-562") {
        addForeignKeyConstraint(baseColumnNames: "soustraitant_id", baseTableName: "soustraitance", baseTableSchemaName: "public", constraintName: "fk7aa81fa3e17ad9df", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "soustraitant", referencedTableSchemaName: "public", referencesUniqueColumn: "false")
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-563") {
        addForeignKeyConstraint(baseColumnNames: "employe_id", baseTableName: "tache", baseTableSchemaName: "public", constraintName: "fk6903f135a166235", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "employe", referencedTableSchemaName: "public", referencesUniqueColumn: "false")
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-564") {
        addForeignKeyConstraint(baseColumnNames: "phase_id", baseTableName: "tache", baseTableSchemaName: "public", constraintName: "fk6903f13d8f7c275", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "phase", referencedTableSchemaName: "public", referencesUniqueColumn: "false")
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-565") {
        addForeignKeyConstraint(baseColumnNames: "document_numerique_id", baseTableName: "tache_document_numerique", baseTableSchemaName: "public", constraintName: "fk5043c573c49bcf57", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "document_numerique", referencedTableSchemaName: "public", referencesUniqueColumn: "false")
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-566") {
        addForeignKeyConstraint(baseColumnNames: "tache_documents_id", baseTableName: "tache_document_numerique", baseTableSchemaName: "public", constraintName: "fk5043c573e3794b5c", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "tache", referencedTableSchemaName: "public", referencesUniqueColumn: "false")
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-567") {
        addForeignKeyConstraint(baseColumnNames: "tache_id", baseTableName: "tache_tache", baseTableSchemaName: "public", constraintName: "fk18e806e7934bb75", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "tache", referencedTableSchemaName: "public", referencesUniqueColumn: "false")
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-568") {
        addForeignKeyConstraint(baseColumnNames: "tache_predecesseur_id", baseTableName: "tache_tache", baseTableSchemaName: "public", constraintName: "fk18e806e779131360", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "tache", referencedTableSchemaName: "public", referencesUniqueColumn: "false")
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-569") {
        addForeignKeyConstraint(baseColumnNames: "groupe_id", baseTableName: "utilisateur", baseTableSchemaName: "public", constraintName: "fkdd1633831316494c", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "groupe_utilisateur", referencedTableSchemaName: "public", referencesUniqueColumn: "false")
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-570") {
        addForeignKeyConstraint(baseColumnNames: "pays_id", baseTableName: "ville", baseTableSchemaName: "public", constraintName: "fk6b0335250256614", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "pays", referencedTableSchemaName: "public", referencesUniqueColumn: "false")
    }

    changeSet(author: "rabbah (generated)", id: "1371166194608-571") {
        createSequence(schemaName: "public", sequenceName: "hibernate_sequence")
    }
    
    changeSet(author: "rabbah", id: "1371166194608-572") {
        sqlFile( path: "sql/init_db_1_2.sql")
    }
  
    changeSet(author: "rabbah", id: "1371166194608-573") {
        preConditions (onFail: 'MARK_RAN'){
            grailsPrecondition{
                check{    
                    if(grails.util.Environment.current != grails.util.Environment.TEST) {
                        fail 'CHARGEMENT DES DONNEES DE TEST Version 1.2 IGNORER DANS ENVIRONEMENT DIFFERENT DE TEST'
                    }
                }
            }
        }   
        sqlFile( path: "sql/test_db_1_2.sql")           
    }
    
}
