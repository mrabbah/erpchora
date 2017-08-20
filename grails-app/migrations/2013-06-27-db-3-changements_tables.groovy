databaseChangeLog = {

    changeSet(author: "rabbah (generated)", id: "1372344154625-1") {
        addColumn(tableName: "bon_livraison") {
            column(name: "est_livre", type: "bool") 
        }
        grailsChange {
            change {
                sql.execute("UPDATE bon_livraison SET est_livre = true where livraison_id is not null")
                sql.execute("UPDATE bon_livraison SET est_livre = false where livraison_id is null")
            }
            rollback {
            }
        }        
        addNotNullConstraint(tableName: "bon_livraison", columnName: "est_livre")
    }

    changeSet(author: "rabbah (generated)", id: "1372344154625-2") {
        addColumn(tableName: "livraison") {
            column(name: "immatriculation", type: "VARCHAR(255)") 
        }
    }

    changeSet(author: "rabbah (generated)", id: "1372344154625-3") {
        addColumn(tableName: "livraison") {
            column(name: "intitule_vehicule", type: "VARCHAR(255)") 
        }
    }	
}
