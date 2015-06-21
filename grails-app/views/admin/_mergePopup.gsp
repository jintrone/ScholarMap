%{--Merge popup--}%
<div class="modal fade" id="mergeEntityModal" tabindex="-1">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"
                        aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title">Entity merge</h4>

                <p>Select the entity you want to keep. The data on the other entity(s) will be deleted, but all related records will be moved over to the remaining entity.</p>
            </div>
            <g:form controller="admin" action="mergeEntities">
                <div class="modal-body">
                    <div class="table-responsive">
                        <table class="table table-striped table-bordered display" cellspacing="0"
                               id="referenceTable">
                            <thead>
                            <tr>
                                <th></th>
                                <th>Name</th>
                                <th>Type</th>
                            </tr>
                            </thead>

                            <tbody>
                            <g:each in="${entities}" var="entity">
                                <tr>
                                    <td>
                                        <g:radio class="ref_check" name="checkBox" id="${entity.id}"
                                                 value="${entity.id}"/>
                                    </td>
                                    <td>${entity.name}</td>
                                    <td>${entity.type}</td>
                                </tr>
                            </g:each>
                            </tbody>
                        </table>
                    </div>
                </div>

                <div class="modal-footer">
                    <button type="submit" class="btn btn-primary">Merge</button>
                    <button type="button" class="btn btn-default" data-dismiss="modal"
                            id="cancelMergeModal">Cancel</button>
                </div>
            </g:form>
        </div>
    </div>
</div>