grails.project.class.dir = "target/classes"
grails.project.test.class.dir = "target/test-classes"
grails.project.test.reports.dir = "target/test-reports"
grails.project.war.file = "target/${appName}.war"
//grails.project.war.file = "target/${appName}-${appVersion}.war"
grails.war.resources = { stagingDir ->
  delete(file:"${stagingDir}/WEB-INF/lib/mysql-connector-java-5.1.15-bin.jar")
  delete(file:"${stagingDir}/WEB-INF/lib/postgresql-9.0-801.jdbc4.jar")
  //delete(file:"**.svg")
}
grails.project.dependency.resolution = {
    // inherit Grails' default dependencies
    inherits("global") {
        // uncomment to disable ehcache
        // excludes 'ehcache'
    }
    log "warn" // log level of Ivy resolver, either 'error', 'warn', 'info', 'debug' or 'verbose'
    repositories {
        grailsPlugins()
        grailsHome()
        grailsCentral()

        // uncomment the below to enable remote dependency resolution
        // from public Maven repositories
        //mavenLocal()
        mavenCentral()
        //mavenRepo "http://snapshots.repository.codehaus.org"
        //mavenRepo "http://repository.codehaus.org"
        //mavenRepo "http://download.java.net/maven/2/"
        //mavenRepo "http://repository.jboss.com/maven2/"
    }
    dependencies {
        // specify dependencies here under either 'build', 'compile', 'runtime', 'test' or 'provided' scopes eg.

        // runtime 'mysql:mysql-connector-java:5.1.13'
    }
}
codenarc.reports = {
    RapportAnalyseQualiteCode('xml') {                    // The report name "MyXmlReport" is user-defined; Report type is 'xml'
        outputFile = 'CodeNarc.xml'  // Set the 'outputFile' property of the (XML) Report
        title = 'Static Analysis Rules Report'             // Set the 'title' property of the (XML) Report
    }
}

codenarc.propertiesFile = 'grails-app/conf/codenarc.properties'
codenarc.processTestUnit = false
codenarc.processTestIntegration = false

coverage {
    xml = true
    exclusions = ["**/*Tests*", "**/org/zkoss/web/util/resource/**", "**/org/zkoss/zksandbox/**", "**/org/zkoss/zksandbox/grid/**", "**/org/zkoss/zksandbox/tree/**", "**/com/choranet/zk/**", "**/*-db-*", "**/*changelog*", "**/*SecurityConfig*", "**/*TestDataConfig*", "**/*Window*"]
}
