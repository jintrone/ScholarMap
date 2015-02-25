/**
 * Created by Emil Matevosyan
 * Date: 2/25/15.
 */
modules = {
    bootstrap {
        resource url: 'js/bootstrap/bootstrap.min.js'
    }

    jquery {
        resource url: 'js/jquery/jquery.min.js'
    }

    application {
        dependsOn 'jquery'
        dependsOn 'bootstrap'
        resource url: 'js/custom/application.js'
    }
}