<%@ page import="csst15.ReferenceVote; com.google.common.base.Joiner; csst15.ReferenceAuthor" %>
<div id="referenceTablesPanel">
    <section class="panel panel-default">
        <div class="panel-heading"><strong><span class="glyphicon glyphicon-th"></span> Selected References
        </strong></div>

        <div class="panel-body" id="selectedRefPanel">
            <g:hiddenField name="entity" value="${entityId}"/>
            <div class="table-responsive">
                <table class="table table-striped table-bordered">
                    <thead>
                    <tr>
                        <th>Authors</th>
                        <th>Year</th>
                        <th>Citation</th>
                        <th>Votes</th>
                        <th>Action</th>
                    </tr>
                    </thead>

                    <tbody>
                    <g:each in="${selectedReferences}" var="reference">
                        <tr>
                            <td>${Joiner.on(',').skipNulls().join(ReferenceAuthor.findAllByReference(reference)?.author?.lastName)}</td>
                            <td>${reference?.year}</td>
                            <td>${reference?.citation}</td>
                            <td>${ReferenceVote.findAllByReferenceAndReferenceIsNotNull(reference)?.size()}</td>
                            <td>
                                <g:link class="glyphicon glyphicon-eye-open" controller="reference"
                                        action="view" params="[id: reference?.id]"/>
                                <sec:ifLoggedIn>
                                    <g:link class="glyphicon glyphicon-remove" controller="interests"
                                            action="removeVote" params="[id: reference?.id, entityId: entityId]"/>
                                </sec:ifLoggedIn>
                            </td>
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
        </div>
    </section>

    <section class="panel panel-default">
        <div class="panel-heading" style="padding: 20px; height: 80px">

            <div class="col-md-10">
                <strong><span class="glyphicon glyphicon-th"></span> Available References</strong>
            </div>

            <div class="col-md-2">
                <sec:ifLoggedIn>
                    <a href="javascript:void(0);" id="addNewRefBtn" class="btn btn-success"
                       title="Add New Reference">Add New Reference</a>
                </sec:ifLoggedIn>
            </div>
        </div>

        <div class="panel-body">

            <div class="table-responsive">
                <table class="table table-striped table-bordered" id="availableReferences">
                    <thead>
                    <tr>
                        <th>Authors</th>
                        <th>Year</th>
                        <th>Citation</th>
                        <th>Votes</th>
                        <th>Vote</th>
                    </tr>
                    </thead>

                    <tbody>
                    <g:each in="${availableReferences}" var="reference">
                        <tr>
                            <td>${Joiner.on(',').skipNulls().join(ReferenceAuthor.findAllByReference(reference)?.author?.lastName)}</td>
                            <td>${reference?.year}</td>
                            <td>${reference?.citation}</td>
                            <td>${ReferenceVote.countByReference(reference)}</td>
                            <td>
                                <p class="select-reference glyphicon glyphicon-star-empty"
                                   style="cursor: pointer" id="${reference?.id}"></p>
                            </td>
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>

        </div>
    </section>
</div>