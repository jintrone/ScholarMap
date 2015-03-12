<g:applyLayout name="main">
    <section class="panel panel-default">
        <div class="panel-heading"><strong><span class="glyphicon glyphicon-th"></span> Entities</strong></div>

        <div class="panel-body">

            <div class="table-responsive">
                <table class="table table-striped table-bordered">
                    <thead>
                    <tr>
                        <th>Actions</th>
                        <th>Name</th>
                        <th>Type</th>
                    </tr>
                    </thead>

                    <tbody>
                    <g:each in="${entities}" var="entity">
                        <tr>
                            <td>
                                <g:link class="glyphicon glyphicon-eye-open" controller="entity" action="view"
                                        params="[id: entity.id]"/>
                                <sec:ifLoggedIn>
                                    <g:link class="glyphicon glyphicon-edit" controller="entity"
                                            params="[id: entity.id]"
                                            action="edit"/>
                                </sec:ifLoggedIn>
                            </td>
                            <td>${entity.name}</td>
                            <td>${entity.type}</td>
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>

        </div>
    </section>

    <section class="panel panel-default">
        <div class="panel-heading"><strong><span class="glyphicon glyphicon-th"></span> References</strong></div>

        <div class="panel-body">

            <div class="table-responsive">
                <table class="table table-striped table-bordered">
                    <thead>
                    <tr>
                        <th>Actions</th>
                        <th>Citation</th>
                        <th>Year</th>
                    </tr>
                    </thead>

                    <tbody>
                    <g:each in="${references}" var="reference">
                        <tr>
                            <td>
                                <g:link class="glyphicon glyphicon-eye-open" controller="reference" action="view"
                                        params="[id: reference.id]"/>
                                <sec:ifLoggedIn>
                                    <g:link class="glyphicon glyphicon-edit" controller="reference"
                                            action="edit" params="[id: reference.id]"/>
                                </sec:ifLoggedIn>
                            </td>
                            <td>${reference.citation}</td>
                            <td>${reference.year}</td>
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>

        </div>
    </section>
</g:applyLayout>