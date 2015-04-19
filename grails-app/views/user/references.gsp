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

            <div id="refTablesContainer">
                <g:render template="/user/referenceList"
                          model="[entityId: entity.id, selectedReferences: selectedReferences, availableReferences: availableReferences]"/>
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
                    <h4 class="modal-title" id="addInterestModalLabel">Add New Reference</h4>
                </div>

            <div class="modal-body">
                <g:form controller="interests" action="addReference">
                    <g:hiddenField name="entity" value="${entity.id}"/>
                    <div class="form-group">
                        <label for="year">Year</label>
                        <g:textField class="form-control" name="year" placeholder="Not set"/>
                    </div>

                    <div class="form-group">
                        <label for="citation">Citation</label>
                        <textarea maxlength="500" name="citation" rows="5" id="citation"
                                  class="form-control"
                                  placeholder="Not set"></textarea>
                    </div>

                    <div class="form-group">
                        <label for="content">Content</label>
                        <textarea maxlength="5000" name="content" rows="5" id="content"
                                  class="form-control"
                                  placeholder="Not set"></textarea>
                    </div>

                    </div>

                    <div class="modal-footer">
                        <button type="button" class="btn btn-default"
                                data-dismiss="modal">Cancel</button>
                        <button id="addReferenceBtn" class="btn btn-primary">Add</button>
                    </div>
                </g:form>
            </div>
        </div>
    </div>
</g:applyLayout>