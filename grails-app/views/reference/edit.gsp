<%@ page import="csst15.constants.EntityType" %>
<g:applyLayout name="main">
    <div class="row" xmlns="http://www.w3.org/1999/html">
        <div class="col-md-2"></div>

        <div class="col-md-8">
            <section class="panel panel-default">
                <div class="panel-heading">
                    <strong><span class="glyphicon glyphicon-th"></span> Edit Reference</strong>
                </div>

                <div class="panel-body">
                    <g:form controller="reference" action="update" method="post">
                        <g:hiddenField name="referenceId" value="${reference.id}"/>
                        <g:render template="form" model="[referenceCommand: reference]"/>
                    </g:form>
                </div>
            </section>
        </div>

        <div class="col-md-2"></div>
    </div>
</g:applyLayout>