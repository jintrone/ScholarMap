dataSource {
    pooled = true
    jmxExport = true
    driverClassName = "org.h2.Driver"
    username = "sa"
    password = ""
}
hibernate {
    cache.use_second_level_cache = true
    cache.use_query_cache = false
//    cache.region.factory_class = 'net.sf.ehcache.hibernate.EhCacheRegionFactory' // Hibernate 3
    cache.region.factory_class = 'org.hibernate.cache.ehcache.EhCacheRegionFactory' // Hibernate 4
    singleSession = true // configure OSIV singleSession mode
}

// environment specific settings
environments {
    development {
        dataSource {
            dbCreate = "update"
            pooled = true
            driverClassName = "com.mysql.jdbc.Driver"
            username = "csst"
            password = "csst"
            dbCreate = "update"
            url = "jdbc:mysql://localhost:3306/csst_v2"
            dialect = org.hibernate.dialect.MySQL5InnoDBDialect
            properties {
                validationQuery = "select 1"
                testWhileIdle = true
                timeBetweenEvictionRunsMillis = 60000
            }
        }
    }
    test {
        dataSource {
            dbCreate = "update"
            url = "jdbc:h2:mem:testDb;MVCC=TRUE;LOCK_TIMEOUT=10000;DB_CLOSE_ON_EXIT=FALSE"
        }
    }
    production {
        dataSource {
            dbCreate = "update"
            pooled = true
            driverClassName = "com.mysql.jdbc.Driver"
            username = "csst"
            password = "csst"
            dbCreate = "update"
            url = "jdbc:mysql://localhost:3306/csst_v2"
            dialect = org.hibernate.dialect.MySQL5InnoDBDialect
            properties {
                validationQuery = "select 1"
                testWhileIdle = true
                timeBetweenEvictionRunsMillis = 60000
            }
        }
    }
}
