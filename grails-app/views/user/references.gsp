<%@ page import="csst15.ReferenceVote; com.google.common.base.Joiner; org.apache.commons.lang.ArrayUtils; csst15.ReferenceAuthor" %>
<g:applyLayout name="main">
    <div class="row">
        <div class="col-md-1"></div>

        <div class="col-md-10">
            <ol class="breadcrumb-alt">
                <li><a href="javascript:void(0);">Profile</a></li>
                <li>
                    <g:link controller="entity" action="view" params="[id: entity.id]">
                        ${entity?.name}
                    </g:link>
                </li>
                <li><a href="javascript:void(0);">References</a></li>
            </ol>

            <section class="panel panel-default">
                <div class="panel-heading"><strong><span class="glyphicon glyphicon-th"></span> Selected References
                </strong></div>

                <div class="panel-body" id="availableRefPanel">
                    <g:hiddenField name="entity" value="${entity?.id}"/>
                    <div class="table-responsive">
                        <g:render template="selectedReferences" model="[selectedReferences: selectedReferences]"/>
                    </div>

                </div>
            </section>
        </div>

        <div class="col-md-1"></div>
    </div>

    <div class="row">
        <div class="col-md-1"></div>

        <div class="col-md-10">

            <section class="panel panel-default">
                <div class="panel-heading"><strong><span class="glyphicon glyphicon-th"></span> Available References
                </strong></div>

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

        <div class="col-md-1"></div>
    </div>
</g:applyLayout>