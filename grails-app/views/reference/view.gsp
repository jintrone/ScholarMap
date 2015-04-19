<%@ page import="csst15.ReferenceAuthor; com.google.common.base.Joiner; csst15.ReferenceVote" %>
<g:applyLayout name="main">

    <div class="modal fade" id="editReferenceModal" style="display: none" tabindex="-1" role="dialog"
         aria-labelledby="myModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <g:form controller="reference" action="update" method="post">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal"
                                aria-label="Close"><span aria-hidden="true">&times;</span>
                        </button>
                        <h4 class="modal-title" id="myModalLabel">Edit Reference</h4>
                    </div>

                    <div class="modal-body">
                        <g:hiddenField name="referenceId" value="${reference.id}"/>

                        <div class="form-group">
                            <label for="year">Year</label>
                            <input type="text" value="${reference?.year}" name="year" class="form-control"
                                   id="year" placeholder="Not set">
                        </div>

                        <div class="form-group">
                            <label for="citation">Citation</label>
                            <textarea maxlength="500" name="citation" rows="6" id="citation"
                                      class="form-control" placeholder="Not set">${reference?.citation}</textarea>
                        </div>

                        <div class="form-group">
                            <label for="content">Content</label>
                            <textarea maxlength="5000" name="content" rows="10" id="content"
                                      class="form-control" placeholder="Not set">${reference?.content}</textarea>
                        </div>

                    </div>

                    <div class="modal-footer">
                        <button type="button" class="btn btn-default"
                                data-dismiss="modal">Cancel</button>
                        <button type="submit" class="btn btn-primary">Update</button>
                    </div>
                </div>
            </g:form>
        </div>
    </div>

    <div class="row">
        <div class="col-md-1"></div>

        <div class="col-md-10">
            <ol class="breadcrumb-alt">
                <li><a href="javascript:void(0);">Explore</a></li>
                <li>
                    <g:link controller="home" action="references">Reference</g:link>
                </li>
                <li class="active"><a
                        href="javascript:void(0);">${ReferenceAuthor.findByReference(reference)?.author?.lastName} ${reference?.year}</a>
                </li>
            </ol>

            <div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true">
                <div class="panel panel-default">
                    <div class="panel-heading" style="padding-bottom: 20px; padding-top: 20px; height: 50px" role="tab">
                        <div class="col-md-11">
                            <h4 class="panel-title">
                                <strong style="padding-right: 25px"><span
                                        class="glyphicon glyphicon-th"></span> Details for ${ReferenceAuthor.findByReference(reference)?.author?.lastName} ${reference?.year}
                                </strong>
                            </h4>
                        </div>

                        <div class="col-md-1">
                            <sec:ifLoggedIn>
                                <p style="cursor: pointer" class="edit-reference" title="Edit">Edit</p>
                            </sec:ifLoggedIn>
                        </div>
                    </div>

                    <div id="collapseOne" class="panel-collapse collapse in" role="tabpanel"
                         aria-labelledby="headingOne">
                        <div class="panel-body">
                            ${reference.citation}
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
            <div role="tabpanel">

                <!-- Nav tabs -->
                <ul class="nav nav-tabs" role="tablist">
                    <li role="presentation" class="active"><a href="#profile" aria-controls="profile" role="tab"
                                                              data-toggle="tab">People</a></li>
                    <li role="presentation"><a href="#messages" aria-controls="messages" role="tab"
                                               data-toggle="tab">Interests</a></li>
                </ul>

                <!-- Tab panes -->
                <div class="tab-content" style="margin-top: 15px">

                    <div role="tabpanel" class="tab-pane active" id="profile" style="margin-top: 15px">
                        <div class="panel panel-default">

                            <div class="panel-body">

                                <table class="table table-striped table-bordered display" cellspacing="0"
                                       id="entityPeopleTable">
                                    <thead>
                                    <tr>
                                        <th>Name</th>
                                        <th>Position</th>
                                        <th>Department</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <g:each in="${ReferenceVote.findAllByReference(reference)?.user?.unique()}"
                                            var="user">
                                        <tr>
                                            <td>
                                                <g:link controller="user" action="profile"
                                                        params="[username: user.username]">
                                                    ${user.firstName} ${user.lastName}
                                                </g:link>
                                            </td>
                                            <td>${user.position?.name}</td>
                                            <td>${user.department?.title}</td>
                                        </tr>
                                    </g:each>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>

                    <div role="tabpanel" class="tab-pane" id="messages" style="margin-top: 15px">
                        <section class="panel panel-default">

                            <div class="panel-body">

                                <div class="table-responsive">
                                    <table class="table table-striped table-bordered display" cellspacing="0"
                                           id="entityRefTable">
                                        <thead>
                                        <tr>
                                            <th>Type</th>
                                            <th>Name</th>
                                            <th>Description</th>
                                        </tr>
                                        </thead>

                                        <tbody>
                                        <g:each in="${ReferenceVote.findAllByReference(reference)?.entity?.unique()}"
                                                var="entity">
                                            <tr>
                                                <td>${entity.type.name}</td>
                                                <td>
                                                    <g:link controller="entity" action="view" params="[id: entity.id]">
                                                        ${entity.name}
                                                    </g:link>
                                                </td>
                                                <td>${entity.description}</td>
                                            </tr>
                                        </g:each>
                                        </tbody>
                                    </table>
                                </div>

                            </div>
                        </section>
                    </div>
                </div>
            </div>
        </div>

        <div class="col-md-1"></div>
    </div>
</g:applyLayout>