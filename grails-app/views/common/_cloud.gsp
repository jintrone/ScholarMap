<div id="entity-cloud">
<g:if test="${!data}">
    No data!
</g:if>
<g:else>
    <g:each in="${data}" var="item">
        <button onclick="addToUser('${type}',${item.id})" type="button" class="btn btn-primary"
                <g:if test="${item in user.entities}">disabled="disabled"</g:if>>${item.name}</button>
    </g:each>


</g:else>
</div>