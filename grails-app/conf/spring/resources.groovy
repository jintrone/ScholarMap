import csst15.auth.CustomAuthSuccessHandler
import grails.plugin.springsecurity.SpringSecurityUtils

// Place your Spring DSL code here
beans = {

    authenticationSuccessHandler(CustomAuthSuccessHandler) {
        def config = SpringSecurityUtils.securityConfig
        requestCache = ref('requestCache')
        defaultTargetUrl = config.successHandler.defaultTargetUrl
        alwaysUseDefaultTargetUrl = config.successHandler.alwaysUseDefault
        targetUrlParameter = config.successHandler.targetUrlParameter
        useReferer = config.successHandler.useReferer
        redirectStrategy = ref('redirectStrategy')
        adminUrl = "/admin/board"
        userUrl = "/home/index"
    }
}
