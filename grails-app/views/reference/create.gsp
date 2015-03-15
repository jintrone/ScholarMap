<g:applyLayout name="main">
    <div class="row" xmlns="http://www.w3.org/1999/html">
        <div class="col-md-2"></div>

        <div class="col-md-8">
            <section class="panel panel-default">
                <div class="panel-heading">
                    <strong><span class="glyphicon glyphicon-th"></span> Create Reference</strong>
                </div>
                <g:hasErrors bean="${referenceCommand}">
                    <ul class="callout callout-danger errors">
                        <g:eachError bean="${referenceCommand}" var="error">
                            <li><g:message error="${error}"/></li>
                        </g:eachError>
                    </ul>
                </g:hasErrors>

                <div class="panel-body">
                    <g:form controller="reference" action="submit" method="post">
                        <g:render template="form" model="[referenceCommand: referenceCommand]"/>
                    </g:form>
                </div>
            </section>
        </div>

        <div class="col-md-2"></div>
    </div>
</g:applyLayout>