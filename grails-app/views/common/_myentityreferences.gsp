<%@ page import="csst15.ReferenceVote; csst15.User; org.apache.shiro.SecurityUtils" %>
<g:set value="${ReferenceVote.findAllByUserAndEntity(User.findByEmail(SecurityUtils.subject.principal), entity)}" var="references"/>
<table class="table">
    <thead>
    <tr>
        <th>Author</th>
        <th>Year</th>
        <th></th>
    </tr>

    </thead>
    <g:if test="${!references.isEmpty()}">
        <tbody>
        <g:each in="${references}"
                var="ref">

            <tr>
                <td>${((ReferenceVote) ref).reference.author}</td>

                <td>${((ReferenceVote) ref).reference.year}</td>

                <td><g:link class="ajax-remove-association" controller="referenceVote" action="remove"
                            id="${ref.id}"><span class="glyphicon glyphicon-remove"></span></g:link></td>
            </tr>


        </g:each>

        </tbody>

    </g:if>
    <g:else>
        <em>No references found!</em>
    </g:else>


</table>
<script type="application/javascript">
    $(".ajax-remove-association").click(function () {
        $.ajax(this.href).done(function () {
            updateAll();
        });
        return false
    })
</script>