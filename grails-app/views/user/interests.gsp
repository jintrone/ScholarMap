<%@ page import="csst15.ReferenceVote; com.google.common.base.Joiner; csst15.ReferenceAuthor" %>
<g:applyLayout name="main">
    <div class="row">
        <div class="col-md-1"></div>

        <div class="col-md-10">
            <div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true">
                <div class="panel panel-default">
                    <div class="panel-heading" role="tab" id="headingOne">
                        <h4 class="panel-title">
                            <a data-toggle="collapse" data-parent="#accordion" href="#collapseOne" aria-expanded="true"
                               aria-controls="collapseOne">
                                Interests
                            </a>
                        </h4>
                    </div>

                    <div id="collapseOne" class="panel-collapse collapse in" role="tabpanel"
                         aria-labelledby="headingOne">
                        <div class="panel-body">
                            Anim pariatur cliche reprehenderit, enim eiusmod high life accusamus terry richardson ad squid. 3 wolf moon officia aute, non cupidatat skateboard dolor brunch. Food truck quinoa nesciunt laborum eiusmod. Brunch 3 wolf moon tempor, sunt aliqua put a bird on it squid single-origin coffee nulla assumenda shoreditch et. Nihil anim keffiyeh helvetica, craft beer labore wes anderson cred nesciunt sapiente ea proident. Ad vegan excepteur butcher vice lomo. Leggings occaecat craft beer farm-to-table, raw denim aesthetic synth nesciunt you probably haven't heard of them accusamus labore sustainable VHS.
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <div class="col-md-1"></div>
    </div>

    <div class="row">
        <div class="col-md-1"></div>

        <div class="col-md-10">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <strong style="padding-right: 25px"><span class="glyphicon glyphicon-th"></span> Interests</strong>
                </div>

                <div class="panel-body">
                    <div class="media">
                        <div class="media-body">
                            <table class="table table-striped table-bordered" id="interestTable">
                                <thead>
                                <tr>
                                    <th>Type</th>
                                    <th>Name</th>
                                    <th>Description</th>
                                    <th>References</th>
                                    <th>Actions</th>
                                </tr>
                                </thead>

                                <tbody>
                                <g:each in="${entities}" var="entity">
                                    <tr>
                                        <td>${entity?.type?.name}</td>
                                        <td>${entity?.name}</td>
                                        <td>${entity?.description}</td>
                                        <td>${ReferenceVote.findAllByReferenceNotIsNullAndEntity(entity)?.reference?.unique()?.size()}</td>
                                        <td><g:link class="glyphicon glyphicon-remove" title="Remove"
                                                    controller="entity" action="view" params="[id: entity.id]"/></td>
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