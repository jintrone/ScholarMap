<%@ page import="csst15.ReferenceVote; com.google.common.base.Joiner; org.apache.commons.lang.ArrayUtils; csst15.ReferenceAuthor" %>
<g:applyLayout name="main">
    <g:set var="isOwner" value="${sec.loggedInUserInfo(field: 'id') == user?.id?.toString()}"/>
    <g:hiddenField name="isOwner" value="${isOwner}"/>
    <g:hiddenField name="user" value="${user.id}"/>
    <div class="row">
        <div class="col-md-1"></div>

        <div class="col-md-10">
            <ol class="breadcrumb-alt">
                <g:if test="${isOwner}">
                    <li>
                        <g:link controller="user" action="profile"
                                params="[username: user?.username]">My Profile</g:link>
                    </li>
                </g:if>
                <g:else>
                    <li>
                        <g:link controller="user" action="profile"
                                params="[username: user?.username]">${user.firstName} ${user.lastName}'s Profile</g:link>
                    </li>
                </g:else>
                <li>
                    <g:link controller="entity" action="view" params="[id: entity?.id]">
                        Selected references for '<b>${entity?.name}</b>'
                    </g:link>
                </li>
            </ol>

            <div id="refTablesContainer">
                <div id="referenceTablesPanel">
                    <section class="panel panel-default">
                        <div class="panel-heading"><strong><span
                                class="glyphicon glyphicon-th"></span> Selected References
                        </strong></div>

                        <div class="panel-body" id="selectedRefPanel">
                            <g:hiddenField name="entity" value="${entity?.id}"/>
                            <div class="table-responsive">
                                <table class="table table-striped table-bordered" id="selectedReferences">
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
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </section>

                    <g:if test="${isOwner}">
                        <section class="panel panel-default">
                            <div class="panel-heading" style="padding: 20px; height: 80px">

                                <div class="col-md-9">
                                    <strong><span class="glyphicon glyphicon-th"></span> Available References</strong>
                                </div>

                                <div class="col-md-3">
                                    <sec:ifLoggedIn>
                                        <a href="javascript:void(0);" id="addNewRefBtn" class="btn btn-success"
                                           title="Create New Reference">Create New Reference</a>
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
                                            <th>Select</th>
                                        </tr>
                                        </thead>

                                        <tbody>
                                        </tbody>
                                    </table>
                                </div>

                            </div>
                        </section>
                    </g:if>
                </div>
            </div>
        </div>

        <div class="col-md-1"></div>
    </div>


    <div class="modal fade" id="addNewReferenceModal" style="display: none">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal"
                            aria-label="Close"><span aria-hidden="true">&times;</span>
                    </button>
                    <h4 class="modal-title" id="addInterestModalLabel">Create New Reference</h4>
                </div>

            <div class="modal-body">
    <g:form controller="interests" action="addReference">
        <g:hiddenField name="entity" value="${entity?.id}"/>
        <div class="form-group" style="margin-bottom: 60px">
            <table class="table table-bordered table-hover" id="tab_logic">
                <thead>
                <tr>
                    <th class="text-center">Author</th>
                </tr>
                </thead>
                <tbody>
                <tr id='addr0'>
                    <td class="ui-front">
                        <csst:authorsAutocomplete name="fullName" action="loadAuthors" class="form-control" id="name"
                                                  placeholder='Last name, First name'/>
                        %{--<input type="text" name='fullName' placeholder='Last name, First name' class="form-control"/>--}%
                    </td>
                </tr>
                <tr id='addr1'></tr>
                </tbody>
            </table>
            <a id="add_row" class="btn btn-default pull-left">Add Row</a>
            <a id='delete_row' class="pull-right btn btn-default">Delete Row</a>
        </div>

        <div class="form-group">
            <label for="year">Year</label>
            <g:textField class="form-control" name="year" placeholder="Not set"/>
            <g:hiddenField class="form-control" name="refId" placeholder="Not set"/>
        </div>

        <div class="form-group">
            <div class="col-xs-6">
                <label for="citation">Full Citation</label>
                <textarea maxlength="500" name="citation" rows="5" id="citation"
                          class="form-control"
                          placeholder="Not set"></textarea>
            </div>

            <div class="col-xs-6" id="matchedRefTable">
                <table class="table table-bordered table-hover" id="ref_tab" style="display: none">
                    <thead>
                    </thead>
                    <tbody>
                    <tr class="tempRow" style="cursor: pointer">
                        <g:hiddenField name="tempId"/>
                        <td></td>
                    </tr>
                    </tbody>
                </table>
            </div>
        </div>

        <div class="form-group" style="padding-bottom: 210px"></div>

        <div class="modal-footer" style="padding-top: 10px">
            <button type="button" class="btn btn-default"
                    data-dismiss="modal">Cancel</button>
            <button id="addReferenceBtn" class="btn btn-primary">Add</button>
        </div>
    </g:form>
    </div>
</div>
</div>
</g:applyLayout>