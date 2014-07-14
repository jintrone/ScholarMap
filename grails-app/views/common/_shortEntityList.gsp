<%@ page import="csst15.Entity" %>

<g:each in="${Entity.resolve(type).list(max: 5)}" var="item">
    <g:link controller="entity" action="detail"
                params="[entityId: item.id]">${item.name}</g:link><br/>
</g:each>

<hr/>
<p><g:link controller="entity" action="summary" params="[type:type]"><em>See more -></em></g:link></p>