<%@ page import="csst15.ReferenceVote; com.google.common.base.Joiner; csst15.ReferenceAuthor" %>
<table class="table table-striped table-bordered">
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
    <g:each in="${selectedReferences}" var="reference">
        <tr>
            <td>${Joiner.on(',').skipNulls().join(ReferenceAuthor.findAllByReference(reference)?.author?.lastName)}</td>
            <td>${reference?.year}</td>
            <td>${reference?.citation}</td>
            <td>${ReferenceVote.findAllByReferenceAndReferenceIsNotNull(reference)?.size()}</td>
            <td><g:link class="glyphicon glyphicon-eye-open" controller="reference"
                        action="view" params="[id: reference?.id]"/></td>
        </tr>
    </g:each>
    </tbody>
</table>