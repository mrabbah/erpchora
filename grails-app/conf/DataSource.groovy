dataSource {
    pooled = true
    driverClassName = "org.postgresql.Driver"
    dialect = org.hibernate.dialect.PostgreSQLDialect
    username = "root"
    password = "root"
}
hibernate {
    //hibernate.show_sql = true
    cache.use_second_level_cache = true
    cache.use_query_cache = true
    cache.provider_class = 'net.sf.ehcache.hibernate.EhCacheProvider'
}
// environment specific settings
environments {
    development {        
        dataSource {            
            url = "jdbc:postgresql://localhost/choraerpdev"     
            //logSql = true
        }
    }
    dbdiff {
        dataSource {            
            url = "jdbc:postgresql://localhost/choraerpdiff"  
            dbCreate = "create-drop"
        }
    }
    test {
        dataSource {            
            url = "jdbc:postgresql://localhost/choraerptest"          
        }
    }
    production {
        dataSource {            
            jndiName = "jdbc/choraerp"           
        }
    }
}
