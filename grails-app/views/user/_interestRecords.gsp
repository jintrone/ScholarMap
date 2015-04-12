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