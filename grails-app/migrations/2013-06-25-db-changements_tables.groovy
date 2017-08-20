databaseChangeLog = {

    changeSet(author: "rabbah (generated)", id: "1372183930013-1") {
        createTable(tableName: "zone") {
            column(name: "id", type: "int8") {
                constraints(nullable: "false", primaryKey: "true", primaryKeyName: "zone_pkey")
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

    changeSet(author: "rabbah (generated)", id: "1372183930013-2") {
        addColumn(tableName: "partenaire") {
            column(name: "plafond", type: "FLOAT8(17)") 
        }
        grailsChange {
            change {
                sql.execute("UPDATE partenaire SET plafond = -1")
            }
            rollback {
            }
        }
        addNotNullConstraint(tableName: "partenaire", columnName: "plafond")
    }

    changeSet(author: "rabbah (generated)", id: "1372183930013-3") {
        addColumn(tableName: "produit") {
            column(name: "codecommun", type: "VARCHAR(255)") 
        }
    }

    changeSet(author: "rabbah (generated)", id: "1372183930013-4") {
        addColumn(tableName: "produit") {
            column(name: "image_prod", type: "bytea")
        }
    }

    changeSet(author: "rabbah (generated)", id: "1372183930013-5") {
        addColumn(tableName: "produit") {
            column(name: "profil", type: "VARCHAR(255)") 
        }
    }

    changeSet(author: "rabbah (generated)", id: "1372183930013-6") {
        addColumn(tableName: "securitestock") {
            column(name: "stock_entre", type: "FLOAT8(17)") 
        }
        grailsChange {
            change {
                sql.execute("UPDATE securitestock SET stock_entre = stock_reel")
            }
            rollback {
            }
        }
        addNotNullConstraint(tableName: "securitestock", columnName: "stock_entre")
    }

    changeSet(author: "rabbah (generated)", id: "1372183930013-7") {
        addColumn(tableName: "securitestock") {
            column(name: "stock_sortie", type: "FLOAT8(17)") 
        }
        grailsChange {
            change {
                sql.execute("UPDATE securitestock SET stock_sortie = 0")
            }
            rollback {
            }
        }
        addNotNullConstraint(tableName: "securitestock", columnName: "stock_sortie")
    }

    changeSet(author: "rabbah (generated)", id: "1372183930013-8") {
        grailsChange {
            change {
                sql.execute("UPDATE mouvement_stock SET prix_deduit_mvm = 0")
            }
            rollback {
            }
        }
        addNotNullConstraint(columnDataType: "FLOAT8(17)", columnName: "prix_deduit_mvm", tableName: "mouvement_stock")
    }

    changeSet(author: "rabbah (generated)", id: "1372183930013-50") {
        grailsChange {
            change {
                sql.execute("UPDATE paiement SET statut = 'Réglé' where statut is NULL and date_encaissement is not NULL")
                sql.execute("UPDATE paiement SET statut = 'Non Réglé' where statut is NULL and date_encaissement is NULL")
            }
            rollback {
            }
        }
        addNotNullConstraint(columnDataType: "VARCHAR(255)", columnName: "statut", tableName: "paiement")
    }

    changeSet(author: "rabbah (generated)", id: "1372183930013-53") {
        addNotNullConstraint(columnDataType: "bool", columnName: "multi_entrepot_enabled", tableName: "parametrage")
    }

    changeSet(author: "rabbah (generated)", id: "1372183930013-54") {
        addNotNullConstraint(columnDataType: "bool", columnName: "produit_perissable_managed", tableName: "parametrage")
    }

    changeSet(author: "rabbah (generated)", id: "1372183930013-59") {
        addNotNullConstraint(columnDataType: "bool", columnName: "perissable", tableName: "produit")
    }

    changeSet(author: "rabbah (generated)", id: "1372183930013-72") {
        createIndex(indexName: "zone_code", tableName: "zone", unique: "false") {
            column(name: "code")
        }
    }

    changeSet(author: "rabbah (generated)", id: "1372183930013-73") {
        createIndex(indexName: "zone_intitule", tableName: "zone", unique: "false") {
            column(name: "intitule")
        }
    }

    changeSet(author: "rabbah (generated)", id: "1372183930013-74") {
        addForeignKeyConstraint(baseColumnNames: "chora_client_info_modules_actifs_id", baseTableName: "chora_client_info_module", baseTableSchemaName: "public", constraintName: "fk28757a4f82190a1", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "chora_client_info", referencedTableSchemaName: "public", referencesUniqueColumn: "false")
    }

    changeSet(author: "rabbah (generated)", id: "1372183930013-75") {
        grailsChange {
            change {
                sql.execute("UPDATE utilisateur SET societe_id = 19 where societe_id is NULL")
            }
            rollback {
            }
        }
        addForeignKeyConstraint(baseColumnNames: "societe_id", baseTableName: "utilisateur", baseTableSchemaName: "public", constraintName: "fkdd16338395dadbac", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "chora_client_info", referencedTableSchemaName: "public", referencesUniqueColumn: "false")
    }
}
