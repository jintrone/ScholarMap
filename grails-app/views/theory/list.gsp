<%@ page import="ScholarMap.Theory" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main">
    <g:set var="entityName" value="${message(code: 'theory.label', default: 'Theory')}"/>
    <title><g:message code="default.list.label" args="[entityName]"/></title>
</head>

<body>
<a href="#list-theory" class="skip" tabindex="-1"><g:message code="default.link.skip.label"
                                                             default="Skip to content&hellip;"/></a>

<div class="nav" role="navigation">
    <ul>
        <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
        <li><g:link class="create" action="create"><g:message code="default.new.label"
                                                              args="[entityName]"/></g:link></li>
    </ul>
</div>

<div id="list-theory" class="content scaffold-list" role="main">
    <h1><g:message code="default.list.label" args="[entityName]"/></h1>
    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>
    <table>
        <thead>
        <tr>

            <g:sortableColumn property="name" title="${message(code: 'theory.name.label', default: 'Name')}"/>

            <g:sortableColumn property="description"
                              title="${message(code: 'theory.description.label', default: 'Description')}"/>

            <th><g:message code="theory.creator.label" default="Creator"/></th>

            <g:sortableColumn property="dateCreated"
                              title="${message(code: 'theory.dateCreated.label', default: 'Date Created')}"/>

        </tr>
        </thead>
        <tbody>
        <g:each in="${theoryInstanceList}" status="i" var="theoryInstance">
            <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">

                <td><g:link action="show"
                            id="${theoryInstance.id}">${fieldValue(bean: theoryInstance, field: "name")}</g:link></td>

                <td>${fieldValue(bean: theoryInstance, field: "description")}</td>

                <td>${fieldValue(bean: theoryInstance, field: "creator")}</td>

                <td><g:formatDate date="${theoryInstance.dateCreated}"/></td>

            </tr>
        </g:each>
        </tbody>
    </table>

    <div class="pagination">
        <g:paginate total="${theoryInstanceTotal}"/>
    </div>
</div>
</body>
</html>
