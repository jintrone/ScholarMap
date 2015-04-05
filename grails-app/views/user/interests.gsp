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

        <div class="modal fade" id="deleteInterestModal" style="display: none" tabindex="-1" role="dialog"
             aria-labelledby="myModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <g:form controller="user" action="deleteInterest" method="post">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal"
                                    aria-label="Close"><span aria-hidden="true">&times;</span>
                            </button>
                            <h4 class="modal-title" id="myModalLabel">Alert</h4>
                        </div>

                        <div class="modal-body">

                            <p>Are you sure you want to delete this interest ?</p>
                            <g:hiddenField name="entityId"/>

                        </div>

                        <div class="modal-footer">
                            <button type="button" class="btn btn-default"
                                    data-dismiss="modal">Cancel</button>
                            <button type="submit" class="btn btn-primary">Delete</button>
                        </div>
                    </div>
                </g:form>
            </div>
        </div>

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
                                    <g:if test="${currentUser.username.equals(sec.username().toString())}">
                                        <th>Actions</th>
                                    </g:if>
                                </tr>
                                </thead>

                                <tbody>
                                <g:each in="${entities}" var="entity">
                                    <tr>
                                        <td>${entity?.type?.name}</td>
                                        <td>${entity?.name}</td>
                                        <td>${entity?.description}</td>
                                        <td>${ReferenceVote.findAllByReferenceNotIsNullAndEntity(entity)?.reference?.unique()?.size()}</td>
                                        <g:if test="${currentUser.username.equals(sec.username().toString())}">
                                            <td>
                                                <a href="javascript:void(0);"
                                                   class="glyphicon glyphicon-remove remove-interest"
                                                   title="Remove"></a>
                                                <g:hiddenField name="entityHiddenId" value="${entity?.id}"/>
                                            </td>
                                        </g:if>
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