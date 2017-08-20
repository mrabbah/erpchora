security {

    // see DefaultSecurityConfig.groovy for all settable/overridable properties

    active = true

    loginUserDomainClass = "com.choranet.securite.Utilisateur"
    authorityDomainClass = "com.choranet.securite.GroupeUtilisateur"
    requestMapClass = "com.choranet.securite.DroitUtilisateur"
        
    authenticationFailureUrl = '/login.zul?login_error=true'
    loginFormUrl = '/choraBarrage'
    cookieName = 'choraerp_remember_me'
    errorPage = '/zul/errors/denied.zul'
    defaultTargetUrl = '/zul/main.zul'
}
