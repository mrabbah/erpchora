databaseChangeLog = {

    changeSet(author: "rabbah (generated)", id: "1373381094248-1") {
        addColumn(tableName: "partenaire") {
            column(name: "commercial_id", type: "int8")
        }
    }

    changeSet(author: "rabbah (generated)", id: "1373381094248-2") {
        addColumn(tableName: "partenaire") {
            column(name: "compte_verrouile", type: "bool") 
        }
        grailsChange {
            change {
                sql.execute("UPDATE partenaire SET compte_verrouile = false")
            }
            rollback {
            }
        }
        addNotNullConstraint(tableName: "partenaire", columnName: "compte_verrouile")
    }

    changeSet(author: "rabbah (generated)", id: "1373381094248-3") {
        addColumn(tableName: "partenaire") {
            column(name: "solde", type: "FLOAT8(17)")
        }
        grailsChange {
            change {
                sql.execute("UPDATE partenaire SET solde = 0")
            }
            rollback {
            }
        }
        addNotNullConstraint(tableName: "partenaire", columnName: "solde")
    }

    changeSet(author: "rabbah (generated)", id: "1373381094248-4") {
        addColumn(tableName: "partenaire") {
            column(name: "zone_id", type: "int8")
        }
    }

    changeSet(author: "rabbah (generated)", id: "1373381094248-64") {
        addForeignKeyConstraint(baseColumnNames: "commercial_id", baseTableName: "partenaire", baseTableSchemaName: "public", constraintName: "fkfb3da237de9ac08c", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "employe", referencedTableSchemaName: "public", referencesUniqueColumn: "false")
    }

    changeSet(author: "rabbah (generated)", id: "1373381094248-65") {
        addForeignKeyConstraint(baseColumnNames: "zone_id", baseTableName: "partenaire", baseTableSchemaName: "public", constraintName: "fkfb3da237d9e0de06", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "zone", referencedTableSchemaName: "public", referencesUniqueColumn: "false")
    }
    
    changeSet(author: "rabbah (generated)", id: "1373381094248-66") {
        addColumn(tableName: "employe") {
            column(name: "site", type: "VARCHAR(255)")
        }
    }

    changeSet(author: "rabbah (generated)", id: "1373381094248-67") {
        dropColumn(columnName: "codecommun", tableName: "produit")
    }
    
}
