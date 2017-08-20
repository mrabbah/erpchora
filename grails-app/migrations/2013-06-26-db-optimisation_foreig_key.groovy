databaseChangeLog = {

    //Suppression des tables en exces
    changeSet(author: "rabbah (generated)", id: "1372261380225-1") {
        addColumn(tableName: "ligne_inventaire") {
            column(name: "inventaire_id", type: "int8") 
        }
        grailsChange {
            change {
                sql.execute("UPDATE ligne_inventaire SET inventaire_id = (select inventaire_ligne_inventaires_id from inventaire_ligne_inventaire where inventaire_ligne_inventaire.ligne_inventaire_id = ligne_inventaire.id)")
            }
            rollback {
            }
        }
        addNotNullConstraint(tableName: "ligne_inventaire", columnName: "inventaire_id")
    }

    changeSet(author: "rabbah (generated)", id: "1372261380225-2") {
        addColumn(tableName: "ligne_produit") {
            column(name: "bon_commande_id", type: "int8")
        }
        grailsChange {
            change {
                sql.execute("UPDATE ligne_produit SET bon_commande_id = (select bon_commande_ligne_produit.bon_commande_ligne_produits_id from bon_commande_ligne_produit where bon_commande_ligne_produit.ligne_produit_id = ligne_produit.id)")
            }
            rollback {
            }
        }
    }

    changeSet(author: "rabbah (generated)", id: "1372261380225-3") {
        addColumn(tableName: "ligne_produit") {
            column(name: "bon_livraison_id", type: "int8")
        }
        grailsChange {
            change {
                sql.execute("UPDATE ligne_produit SET bon_livraison_id = (select bon_livraison_ligne_produit.bon_livraison_ligne_produits_id from bon_livraison_ligne_produit where bon_livraison_ligne_produit.ligne_produit_id = ligne_produit.id)")
            }
            rollback {
            }
        }
    }

    changeSet(author: "rabbah (generated)", id: "1372261380225-4") {
        addColumn(tableName: "ligne_produit") {
            column(name: "bon_pret_id", type: "int8")
        }
        grailsChange {
            change {
                sql.execute("UPDATE ligne_produit SET bon_pret_id = (select bon_pret_ligne_produit.bon_pret_ligne_produits_id from bon_pret_ligne_produit where bon_pret_ligne_produit.ligne_produit_id = ligne_produit.id)")
            }
            rollback {
            }
        }
    }

    changeSet(author: "rabbah (generated)", id: "1372261380225-5") {
        addColumn(tableName: "ligne_produit") {
            column(name: "devis_id", type: "int8")
        }
        grailsChange {
            change {
                sql.execute("UPDATE ligne_produit SET devis_id = (select devis_ligne_produit.devis_ligne_produits_id from devis_ligne_produit where devis_ligne_produit.ligne_produit_id = ligne_produit.id)")
            }
            rollback {
            }
        }
    }

    changeSet(author: "rabbah (generated)", id: "1372261380225-65") {
        dropForeignKeyConstraint(baseTableName: "bon_commande_ligne_produit", baseTableSchemaName: "public", constraintName: "fkaaa4d0647fd8d0ac")
    }

    changeSet(author: "rabbah (generated)", id: "1372261380225-66") {
        dropForeignKeyConstraint(baseTableName: "bon_commande_ligne_produit", baseTableSchemaName: "public", constraintName: "fkaaa4d064978ebb91")
    }

    changeSet(author: "rabbah (generated)", id: "1372261380225-67") {
        dropForeignKeyConstraint(baseTableName: "bon_livraison_ligne_produit", baseTableSchemaName: "public", constraintName: "fk234cebffc51bcd70")
    }

    changeSet(author: "rabbah (generated)", id: "1372261380225-68") {
        dropForeignKeyConstraint(baseTableName: "bon_livraison_ligne_produit", baseTableSchemaName: "public", constraintName: "fk234cebff978ebb91")
    }

    changeSet(author: "rabbah (generated)", id: "1372261380225-69") {
        dropForeignKeyConstraint(baseTableName: "bon_pret_ligne_produit", baseTableSchemaName: "public", constraintName: "fke1d9eadb8ae23ada")
    }

    changeSet(author: "rabbah (generated)", id: "1372261380225-70") {
        dropForeignKeyConstraint(baseTableName: "bon_pret_ligne_produit", baseTableSchemaName: "public", constraintName: "fke1d9eadb978ebb91")
    }

    changeSet(author: "rabbah (generated)", id: "1372261380225-71") {
        dropForeignKeyConstraint(baseTableName: "devis_ligne_produit", baseTableSchemaName: "public", constraintName: "fk65eea0ebfbe826d7")
    }

    changeSet(author: "rabbah (generated)", id: "1372261380225-72") {
        dropForeignKeyConstraint(baseTableName: "devis_ligne_produit", baseTableSchemaName: "public", constraintName: "fk65eea0eb978ebb91")
    }

    changeSet(author: "rabbah (generated)", id: "1372261380225-73") {
        dropForeignKeyConstraint(baseTableName: "inventaire_ligne_inventaire", baseTableSchemaName: "public", constraintName: "fk6daa303dc2baad93")
    }

    changeSet(author: "rabbah (generated)", id: "1372261380225-74") {
        dropForeignKeyConstraint(baseTableName: "inventaire_ligne_inventaire", baseTableSchemaName: "public", constraintName: "fk6daa303ddc093bc3")
    }

    changeSet(author: "rabbah (generated)", id: "1372261380225-75") {
        addForeignKeyConstraint(baseColumnNames: "inventaire_id", baseTableName: "ligne_inventaire", baseTableSchemaName: "public", constraintName: "fk15723ff377f4636f", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "inventaire", referencedTableSchemaName: "public", referencesUniqueColumn: "false")
    }

    changeSet(author: "rabbah (generated)", id: "1372261380225-76") {
        addForeignKeyConstraint(baseColumnNames: "bon_commande_id", baseTableName: "ligne_produit", baseTableSchemaName: "public", constraintName: "fk9fd3592ba3b5f963", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "bon_commande", referencedTableSchemaName: "public", referencesUniqueColumn: "false")
    }

    changeSet(author: "rabbah (generated)", id: "1372261380225-77") {
        addForeignKeyConstraint(baseColumnNames: "bon_livraison_id", baseTableName: "ligne_produit", baseTableSchemaName: "public", constraintName: "fk9fd3592bd4433751", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "bon_livraison", referencedTableSchemaName: "public", referencesUniqueColumn: "false")
    }

    changeSet(author: "rabbah (generated)", id: "1372261380225-78") {
        addForeignKeyConstraint(baseColumnNames: "bon_pret_id", baseTableName: "ligne_produit", baseTableSchemaName: "public", constraintName: "fk9fd3592b6a2d2683", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "bon_pret", referencedTableSchemaName: "public", referencesUniqueColumn: "false")
    }

    changeSet(author: "rabbah (generated)", id: "1372261380225-79") {
        addForeignKeyConstraint(baseColumnNames: "devis_id", baseTableName: "ligne_produit", baseTableSchemaName: "public", constraintName: "fk9fd3592ba3485860", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "devis", referencedTableSchemaName: "public", referencesUniqueColumn: "false")
    }

    changeSet(author: "rabbah (generated)", id: "1372261380225-80") {
        dropTable(tableName: "bon_commande_ligne_produit")
    }

    changeSet(author: "rabbah (generated)", id: "1372261380225-81") {
        dropTable(tableName: "bon_livraison_ligne_produit")
    }

    changeSet(author: "rabbah (generated)", id: "1372261380225-82") {
        dropTable(tableName: "bon_pret_ligne_produit")
    }

    changeSet(author: "rabbah (generated)", id: "1372261380225-83") {
        dropTable(tableName: "devis_ligne_produit")
    }

    changeSet(author: "rabbah (generated)", id: "1372261380225-84") {
        dropTable(tableName: "inventaire_ligne_inventaire")
    }
}
