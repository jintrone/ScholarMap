<%@ page import="csst15.ReferenceVote" %>
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
            <td>
                <g:link controller="entity" action="view" params="[id: entity.id]">
                    ${entity?.name}
                </g:link>
            </td>
            <td>${entity?.description}</td>
            <td>
                <g:if test="${!ReferenceVote.findAllByReferenceNotIsNullAndEntity(entity)}">
                    <g:link controller="user" action="references" params="[entityId: entity?.id]">
                        Add
                    </g:link>
                </g:if>
                <g:else>
                    ${ReferenceVote.findAllByReferenceNotIsNullAndEntity(entity)?.reference?.unique()?.size()}
                </g:else>
            </td>
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

<div class="modal fade" id="addReferenceModal" style="display: none" tabindex="-1" role="dialog"
     aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"
                        aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title">${newEntity?.name}</h4>
            </div>

            <div class="modal-body">
                ${newEntity?.name} has been added. Please take the time to add a reference.
            </div>

            <div class="modal-footer">
                <g:link controller="user" action="references" params="[entityId: newEntity?.id]"
                        class="btn btn-primary">Add Reference</g:link>
            </div>
        </div>
    </div>
</div>