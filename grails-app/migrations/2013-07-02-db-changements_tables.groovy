databaseChangeLog = {

    changeSet(author: "rabbah (generated)", id: "1372767393262-1") {
        addColumn(tableName: "produit") {
            column(name: "fournisseur_pref_id", type: "int8")
        }
    }

    changeSet(author: "rabbah (generated)", id: "1372767393262-61") {
        addForeignKeyConstraint(baseColumnNames: "fournisseur_pref_id", baseTableName: "produit", baseTableSchemaName: "public", constraintName: "fked8dcda95e61d40a", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "partenaire", referencedTableSchemaName: "public", referencesUniqueColumn: "false")
    }
	
    changeSet(author: "rabbah (generated)", id: "1372767393262-62") {
        preConditions (onFail: 'MARK_RAN'){
            grailsPrecondition{
                check{    
                    if(grails.util.Environment.current != grails.util.Environment.TEST) {
                        fail 'IGNORER DANS ENVIRONEMENT DIFFERENT DE TEST'
                    }
                }
            }
        }   
        grailsChange {
            change {
                sql.execute("UPDATE utilisateur SET passwd = 'ute+t66D+hw=' where id = 400")
            }
            rollback {
            }
        }
    }
        
}
