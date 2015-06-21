%{--Merge popup--}%
<div class="modal fade" id="mergeReferenceModal" tabindex="-1">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"
                        aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title">Reference Merge</h4>

                <p>Select the reference you want to keep. The data on the other reference(s) will be deleted, but all related records will be moved over to the remaining reference.</p>
            </div>
            <g:form controller="admin" action="mergeReferences">
                <div class="modal-body">
                    <div class="table-responsive">
                        <table class="table table-striped table-bordered display" cellspacing="0"
                               id="referenceTable">
                            <thead>
                            <tr>
                                <th></th>
                                <th>Citation</th>
                                <th>Year</th>
                            </tr>
                            </thead>

                            <tbody>
                            <g:each in="${references}" var="reference">
                                <tr>
                                    <td>
                                        <g:radio class="ref_check" name="checkBox" id="${reference.id}"
                                                 value="${reference.id}"/>
                                    </td>
                                    <td>${reference.citation}</td>
                                    <td>${reference.year}</td>
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