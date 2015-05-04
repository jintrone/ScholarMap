<%@ page import="csst15.ReferenceVote; com.google.common.base.Joiner; org.apache.commons.lang.ArrayUtils; csst15.ReferenceAuthor" %>
<g:applyLayout name="main">
    <div class="row">
        <div class="col-md-1"></div>

        <div class="col-md-10">
            <ol class="breadcrumb-alt">
                <li><a href="javascript:void(0);">Profile</a></li>
                <li>
                    <g:link controller="entity" action="view" params="[id: entity?.id]">
                        ${entity?.name}
                    </g:link>
                </li>
                <li><a href="javascript:void(0);">References</a></li>
            </ol>

            <div id="refTablesContainer">
                <g:render template="/user/referenceList"
                          model="[entityId: entity?.id, selectedReferences: selectedReferences, availableReferences: availableReferences]"/>
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
                    <th class="text-center">#</th>
                    <th class="text-center">Author</th>
                </tr>
                </thead>
                <tbody>
                <tr id='addr0'>
                    <td>1</td>
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
        </div>

        <div class="form-group">
            <label for="citation">Full Citation</label>
            <textarea maxlength="500" name="citation" rows="5" id="citation"
                      class="form-control"
                      placeholder="Not set"></textarea>
        </div>

        <div class="form-group">
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

        <div class="modal-footer" style="margin-top: 60px">
            <button type="button" class="btn btn-default"
                    data-dismiss="modal">Cancel</button>
            <button id="addReferenceBtn" class="btn btn-primary">Add</button>
        </div>
    </g:form>
    </div>
</div>
</div>
</g:applyLayout>