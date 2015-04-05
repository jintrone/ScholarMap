<%@ page import="csst15.ReferenceAuthor; com.google.common.base.Joiner; csst15.ReferenceVote" %>
<g:applyLayout name="main">
    <div class="row">
        <div class="col-md-1"></div>

        <div class="col-md-10">

            <div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true">
                <div class="panel panel-default">
                    <div class="panel-heading" role="tab" id="headingOne">
                        <h4 class="panel-title">
                            <strong style="padding-right: 25px"><span class="glyphicon glyphicon-th"></span> Details for
                                <a data-toggle="collapse" data-parent="#accordion" href="#collapseOne"
                                   aria-expanded="true"
                                   aria-controls="collapseOne">
                                    ${entity.name}
                                </a>
                            </strong>
                        </h4>
                    </div>

                    <div id="collapseOne" class="panel-collapse collapse in" role="tabpanel"
                         aria-labelledby="headingOne">
                        <div class="panel-body">
                            ${entity.description}
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <div class="col-md-1"></div>
    </div>

    <div class="row">
        <div class="col-md-1"></div>

        <div class="col-md-4">

            <div class="panel panel-default">
                <div class="panel-heading">
                    <strong style="padding-right: 25px"><span class="glyphicon glyphicon-th"></span> People</strong>
                </div>

                <div class="panel-body">
                    <div class="media">
                        <div class="media-body">
                            <ul class="list-unstyled list-info">
                                <g:each in="${ReferenceVote.findAllByEntity(entity)?.user?.unique()}" var="user">
                                    <li>${user.firstName} ${user.lastName}</li>
                                </g:each>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <div class="col-md-6">

            <div class="panel panel-default">
                <div class="panel-heading">
                    <strong style="padding-right: 25px"><span class="glyphicon glyphicon-th"></span> References</strong>
                </div>

                <div class="panel-body">
                    <div class="media">
                        <div class="media-body">
                            <table class="table table-striped table-bordered">
                                <thead>
                                <tr>
                                    <th>Authors</th>
                                    <th>Year</th>
                                    <th>Citation</th>
                                </tr>
                                </thead>

                                <tbody>
                                <g:each in="${ReferenceVote.findAllByEntity(entity)?.reference?.unique()}"
                                        var="reference">
                                    <tr>
                                        <td>${Joiner.on(',').skipNulls().join(ReferenceAuthor.findAllByReference(reference)?.author?.lastName)}</td>
                                        <td>${reference.year}</td>
                                        <td>${reference.citation}</td>
                                    </tr>
                                </g:each>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <div class="col-md-1"></div>
    </div>
</g:applyLayout>