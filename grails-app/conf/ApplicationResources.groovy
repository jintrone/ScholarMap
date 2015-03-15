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

    dataTable {
        dependsOn 'jquery'
        resource url: 'js/jquery/jquery.dataTables.min.js'
        resource url: 'css/jquery/jquery.dataTables.css'
    }

    application {
        dependsOn 'dataTable'
        dependsOn 'bootstrap'
        resource url: 'js/custom/application.js'
    }
}