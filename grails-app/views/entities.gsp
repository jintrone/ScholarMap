<g:applyLayout name="main">
    <section class="panel panel-default">
        <div class="panel-heading"><strong><span class="glyphicon glyphicon-th"></span> Entities</strong></div>

        <div class="panel-body">

            <div class="table-responsive">
                <table class="table table-striped table-bordered">
                    <thead>
                    <tr>
                        <sec:ifLoggedIn>
                            <th>Actions</th>
                        </sec:ifLoggedIn>
                        <th>Name</th>
                        <th>Type</th>
                    </tr>
                    </thead>

                    <tbody>
                    <g:each in="${entities}" var="entity">
                        <tr>
                            <sec:ifLoggedIn>
                                <td>${entity.name}</td>
                            </sec:ifLoggedIn>
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
                        <sec:ifLoggedIn>
                            <th>Actions</th>
                        </sec:ifLoggedIn>
                        <th>Citation</th>
                        <th>Year</th>
                    </tr>
                    </thead>

                    <tbody>
                    <g:each in="${references}" var="reference">
                        <tr>
                            <sec:ifLoggedIn>
                                <td>${reference.citation}</td>
                            </sec:ifLoggedIn>
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