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

    jquery_ui {
        resource url: 'js/jquery/jquery-ui.min.js'
        resource url: 'css/jquery/jquery-ui.css'
    }

    dataTable {
        dependsOn 'jquery'
        resource url: 'js/jquery/jquery.dataTables.min.js'
        resource url: 'css/jquery/jquery.dataTables.css'
    }

    application {
        dependsOn 'dataTable'
        dependsOn 'bootstrap'
        dependsOn 'jquery_ui'
        resource url: 'js/custom/application.js'
    }
}