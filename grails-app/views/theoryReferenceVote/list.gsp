<%@ page import="ScholarMap.TheoryReferenceVote" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main">
    <g:set var="entityName" value="${message(code: 'theoryReferenceVote.label', default: 'TheoryReferenceVote')}"/>
    <title><g:message code="default.list.label" args="[entityName]"/></title>
</head>

<body>
<a href="#list-theoryReferenceVote" class="skip" tabindex="-1"><g:message code="default.link.skip.label"
                                                                          default="Skip to content&hellip;"/></a>

<div class="nav" role="navigation">
    <ul>
        <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
        <li><g:link class="create" action="create"><g:message code="default.new.label"
                                                              args="[entityName]"/></g:link></li>
    </ul>
</div>

<div id="list-theoryReferenceVote" class="content scaffold-list" role="main">
    <h1><g:message code="default.list.label" args="[entityName]"/></h1>
    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>
    <table>
        <thead>
        <tr>

            <g:sortableColumn property="dateCreated"
                              title="${message(code: 'theoryReferenceVote.dateCreated.label', default: 'Date Created')}"/>

            <th><g:message code="theoryReferenceVote.reference.label" default="Reference"/></th>

            <th><g:message code="theoryReferenceVote.theory.label" default="Theory"/></th>

            <th><g:message code="theoryReferenceVote.user.label" default="User"/></th>

        </tr>
        </thead>
        <tbody>
        <g:each in="${theoryReferenceVoteInstanceList}" status="i" var="theoryReferenceVoteInstance">
            <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">

                <td><g:link action="show"
                            id="${theoryReferenceVoteInstance.id}">${fieldValue(bean: theoryReferenceVoteInstance, field: "dateCreated")}</g:link></td>

                <td>${fieldValue(bean: theoryReferenceVoteInstance, field: "reference")}</td>

                <td>${fieldValue(bean: theoryReferenceVoteInstance, field: "theory")}</td>

                <td>${fieldValue(bean: theoryReferenceVoteInstance, field: "user")}</td>

            </tr>
        </g:each>
        </tbody>
    </table>

    <div class="pagination">
        <g:paginate total="${theoryReferenceVoteInstanceTotal}"/>
    </div>
</div>
</body>
</html>
