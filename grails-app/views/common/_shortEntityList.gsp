<%@ page import="csst15.Entity" %>

<g:each in="${Entity.resolve(type).list(max: 5)}" var="item">
    <g:link controller="entity" action="view" params="[id: item.id]">${item.name}</g:link><br/>
</g:each>

<hr/>