<%@ page import="csst15.constants.EntityType" %>
<g:applyLayout name="main">
    <div class="row" xmlns="http://www.w3.org/1999/html">
        <div class="col-md-2"></div>

        <div class="col-md-8">
            <section class="panel panel-default">
                <div class="panel-heading">
                    <strong><span class="glyphicon glyphicon-th"></span> Create Entity</strong>
                </div>
                <g:hasErrors bean="${command}">
                    <ul class="callout callout-danger errors">
                        <g:eachError bean="${command}" var="error">
                            <li><g:message error="${error}"/></li>
                        </g:eachError>
                    </ul>
                </g:hasErrors>
                <div class="panel-body">
                    <g:form controller="entity" action="submit" method="post">
                        <g:render template="form" model="[command: command]"/>
                    </g:form>
                </div>
            </section>
        </div>

        <div class="col-md-2"></div>
    </div>
</g:applyLayout>