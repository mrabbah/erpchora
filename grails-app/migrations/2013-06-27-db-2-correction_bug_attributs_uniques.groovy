databaseChangeLog = {
	
    changeSet(author: "rabbah (generated)", id: "1372328392567-60") {
        addUniqueConstraint(columnNames: "nom", constraintName: "agence_bancaire_nom_key", deferrable: "false", disabled: "false", initiallyDeferred: "false", tableName: "agence_bancaire")
    }

    changeSet(author: "rabbah (generated)", id: "1372328392567-61") {
        addUniqueConstraint(columnNames: "numbc", constraintName: "bon_commande_numbc_key", deferrable: "false", disabled: "false", initiallyDeferred: "false", tableName: "bon_commande")
    }

    changeSet(author: "rabbah (generated)", id: "1372328392567-62") {
        addUniqueConstraint(columnNames: "numbl", constraintName: "bon_livraison_numbl_key", deferrable: "false", disabled: "false", initiallyDeferred: "false", tableName: "bon_livraison")
    }

    changeSet(author: "rabbah (generated)", id: "1372328392567-63") {
        addUniqueConstraint(columnNames: "numbp", constraintName: "bon_pret_numbp_key", deferrable: "false", disabled: "false", initiallyDeferred: "false", tableName: "bon_pret")
    }

    changeSet(author: "rabbah (generated)", id: "1372328392567-64") {
        addUniqueConstraint(columnNames: "nom", constraintName: "calendrier_nom_key", deferrable: "false", disabled: "false", initiallyDeferred: "false", tableName: "calendrier")
    }

    changeSet(author: "rabbah (generated)", id: "1372328392567-65") {
        addUniqueConstraint(columnNames: "libelle", constraintName: "categorie_partenaire_libelle_key", deferrable: "false", disabled: "false", initiallyDeferred: "false", tableName: "categorie_partenaire")
    }

    changeSet(author: "rabbah (generated)", id: "1372328392567-66") {
        addUniqueConstraint(columnNames: "cle_produit", constraintName: "cle_cle_produit_key", deferrable: "false", disabled: "false", initiallyDeferred: "false", tableName: "cle")
    }

    changeSet(author: "rabbah (generated)", id: "1372328392567-67") {
        addUniqueConstraint(columnNames: "date_activation", constraintName: "cle_date_activation_key", deferrable: "false", disabled: "false", initiallyDeferred: "false", tableName: "cle")
    }

    changeSet(author: "rabbah (generated)", id: "1372328392567-68") {
        addUniqueConstraint(columnNames: "code_swift", constraintName: "compte_bancaire_code_swift_key", deferrable: "false", disabled: "false", initiallyDeferred: "false", tableName: "compte_bancaire")
    }

    changeSet(author: "rabbah (generated)", id: "1372328392567-69") {
        addUniqueConstraint(columnNames: "libelle", constraintName: "compte_bancaire_libelle_key", deferrable: "false", disabled: "false", initiallyDeferred: "false", tableName: "compte_bancaire")
    }

    changeSet(author: "rabbah (generated)", id: "1372328392567-70") {
        addUniqueConstraint(columnNames: "rib", constraintName: "compte_bancaire_rib_key", deferrable: "false", disabled: "false", initiallyDeferred: "false", tableName: "compte_bancaire")
    }

    changeSet(author: "rabbah (generated)", id: "1372328392567-71") {
        addUniqueConstraint(columnNames: "num_declaration", constraintName: "declaration_tva_num_declaration_key", deferrable: "false", disabled: "false", initiallyDeferred: "false", tableName: "declaration_tva")
    }

    changeSet(author: "rabbah (generated)", id: "1372328392567-72") {
        addUniqueConstraint(columnNames: "num_devis", constraintName: "devis_num_devis_key", deferrable: "false", disabled: "false", initiallyDeferred: "false", tableName: "devis")
    }

    changeSet(author: "rabbah (generated)", id: "1372328392567-73") {
        addUniqueConstraint(columnNames: "code", constraintName: "echeance_code_key", deferrable: "false", disabled: "false", initiallyDeferred: "false", tableName: "echeance")
    }

    changeSet(author: "rabbah (generated)", id: "1372328392567-74") {
        addUniqueConstraint(columnNames: "libelle", constraintName: "echeance_libelle_key", deferrable: "false", disabled: "false", initiallyDeferred: "false", tableName: "echeance")
    }

    changeSet(author: "rabbah (generated)", id: "1372328392567-75") {
        addUniqueConstraint(columnNames: "code", constraintName: "entrepot_code_key", deferrable: "false", disabled: "false", initiallyDeferred: "false", tableName: "entrepot")
    }

    changeSet(author: "rabbah (generated)", id: "1372328392567-76") {
        addUniqueConstraint(columnNames: "intitule", constraintName: "entrepot_intitule_key", deferrable: "false", disabled: "false", initiallyDeferred: "false", tableName: "entrepot")
    }

    changeSet(author: "rabbah (generated)", id: "1372328392567-77") {
        addUniqueConstraint(columnNames: "numero_facture", constraintName: "facture_numero_facture_key", deferrable: "false", disabled: "false", initiallyDeferred: "false", tableName: "facture")
    }

    changeSet(author: "rabbah (generated)", id: "1372328392567-78") {
        addUniqueConstraint(columnNames: "libelle", constraintName: "fonction_libelle_key", deferrable: "false", disabled: "false", initiallyDeferred: "false", tableName: "fonction")
    }

    changeSet(author: "rabbah (generated)", id: "1372328392567-79") {
        addUniqueConstraint(columnNames: "num_expedition", constraintName: "livraison_num_expedition_key", deferrable: "false", disabled: "false", initiallyDeferred: "false", tableName: "livraison")
    }

    changeSet(author: "rabbah (generated)", id: "1372328392567-80") {
        addUniqueConstraint(columnNames: "code", constraintName: "mode_livraison_code_key", deferrable: "false", disabled: "false", initiallyDeferred: "false", tableName: "mode_livraison")
    }

    changeSet(author: "rabbah (generated)", id: "1372328392567-81") {
        addUniqueConstraint(columnNames: "code", constraintName: "mode_reglement_code_key", deferrable: "false", disabled: "false", initiallyDeferred: "false", tableName: "mode_reglement")
    }

    changeSet(author: "rabbah (generated)", id: "1372328392567-82") {
        addUniqueConstraint(columnNames: "libelle", constraintName: "mode_reglement_libelle_key", deferrable: "false", disabled: "false", initiallyDeferred: "false", tableName: "mode_reglement")
    }

    changeSet(author: "rabbah (generated)", id: "1372328392567-83") {
        addUniqueConstraint(columnNames: "code", constraintName: "mode_relance_paiement_code_key", deferrable: "false", disabled: "false", initiallyDeferred: "false", tableName: "mode_relance_paiement")
    }

    changeSet(author: "rabbah (generated)", id: "1372328392567-84") {
        addUniqueConstraint(columnNames: "libelle", constraintName: "mode_relance_paiement_libelle_key", deferrable: "false", disabled: "false", initiallyDeferred: "false", tableName: "mode_relance_paiement")
    }

    changeSet(author: "rabbah (generated)", id: "1372328392567-85") {
        addUniqueConstraint(columnNames: "libelle", constraintName: "nature_libelle_key", deferrable: "false", disabled: "false", initiallyDeferred: "false", tableName: "nature")
    }

    changeSet(author: "rabbah (generated)", id: "1372328392567-86") {
        addUniqueConstraint(columnNames: "libelle", constraintName: "nature_de_charge_libelle_key", deferrable: "false", disabled: "false", initiallyDeferred: "false", tableName: "nature_de_charge")
    }

    changeSet(author: "rabbah (generated)", id: "1372328392567-87") {
        addUniqueConstraint(columnNames: "code", constraintName: "partenaire_code_key", deferrable: "false", disabled: "false", initiallyDeferred: "false", tableName: "partenaire")
    }

    changeSet(author: "rabbah (generated)", id: "1372328392567-88") {
        addUniqueConstraint(columnNames: "raison_sociale", constraintName: "partenaire_raison_sociale_key", deferrable: "false", disabled: "false", initiallyDeferred: "false", tableName: "partenaire")
    }

    changeSet(author: "rabbah (generated)", id: "1372328392567-89") {
        addUniqueConstraint(columnNames: "libelle", constraintName: "patern_compteur_libelle_key", deferrable: "false", disabled: "false", initiallyDeferred: "false", tableName: "patern_compteur")
    }

    changeSet(author: "rabbah (generated)", id: "1372328392567-90") {
        addUniqueConstraint(columnNames: "intitule", constraintName: "pays_intitule_key", deferrable: "false", disabled: "false", initiallyDeferred: "false", tableName: "pays")
    }

    changeSet(author: "rabbah (generated)", id: "1372328392567-91") {
        addUniqueConstraint(columnNames: "code", constraintName: "produit_code_key", deferrable: "false", disabled: "false", initiallyDeferred: "false", tableName: "produit")
    }

    changeSet(author: "rabbah (generated)", id: "1372328392567-92") {
        grailsChange {
            change {
                sql.execute("UPDATE produit set codebarre = null where codebarre = ''")
            }
            rollback {
            }
        }
        addUniqueConstraint(columnNames: "codebarre", constraintName: "produit_codebarre_key", deferrable: "false", disabled: "false", initiallyDeferred: "false", tableName: "produit")
    }

    changeSet(author: "rabbah (generated)", id: "1372328392567-93") {
        addUniqueConstraint(columnNames: "libelle", constraintName: "regimetva_libelle_key", deferrable: "false", disabled: "false", initiallyDeferred: "false", tableName: "regimetva")
    }

    changeSet(author: "rabbah (generated)", id: "1372328392567-94") {
        addUniqueConstraint(columnNames: "taux", constraintName: "regimetva_taux_key", deferrable: "false", disabled: "false", initiallyDeferred: "false", tableName: "regimetva")
    }

    changeSet(author: "rabbah (generated)", id: "1372328392567-95") {
        addUniqueConstraint(columnNames: "libelle", constraintName: "regle_facturation_automatique_libelle_key", deferrable: "false", disabled: "false", initiallyDeferred: "false", tableName: "regle_facturation_automatique")
    }

    changeSet(author: "rabbah (generated)", id: "1372328392567-96") {
        addUniqueConstraint(columnNames: "code", constraintName: "regle_prix_code_key", deferrable: "false", disabled: "false", initiallyDeferred: "false", tableName: "regle_prix")
    }

    changeSet(author: "rabbah (generated)", id: "1372328392567-97") {
        addUniqueConstraint(columnNames: "libelle", constraintName: "regle_prix_libelle_key", deferrable: "false", disabled: "false", initiallyDeferred: "false", tableName: "regle_prix")
    }

    changeSet(author: "rabbah (generated)", id: "1372328392567-98") {
        addUniqueConstraint(columnNames: "libelle", constraintName: "unite_mesure_libelle_key", deferrable: "false", disabled: "false", initiallyDeferred: "false", tableName: "unite_mesure")
    }

    changeSet(author: "rabbah (generated)", id: "1372328392567-99") {
        grailsChange {
            change {
                sql.execute("DELETE from ville where id=262")
                sql.execute("DELETE from ville where id=269")
            }
            rollback {
            }
        }
        addUniqueConstraint(columnNames: "intitule", constraintName: "ville_intitule_key", deferrable: "false", disabled: "false", initiallyDeferred: "false", tableName: "ville")
    }

    changeSet(author: "rabbah (generated)", id: "1372328392567-100") {
        addUniqueConstraint(columnNames: "code", constraintName: "zone_code_key", deferrable: "false", disabled: "false", initiallyDeferred: "false", tableName: "zone")
    }

    changeSet(author: "rabbah (generated)", id: "1372328392567-101") {
        addUniqueConstraint(columnNames: "intitule", constraintName: "zone_intitule_key", deferrable: "false", disabled: "false", initiallyDeferred: "false", tableName: "zone")
    }

}
