<%@ page import="scholarmap.TheoryVote" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main">
    <g:set var="entityName" value="${message(code: 'theoryVote.label', default: 'TheoryVote')}"/>
    <title><g:message code="default.list.label" args="[entityName]"/></title>
</head>

<body>
<a href="#list-theoryVote" class="skip" tabindex="-1"><g:message code="default.link.skip.label"
                                                                 default="Skip to content&hellip;"/></a>

<div class="nav" role="navigation">
    <ul>
        <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
        <li><g:link class="create" action="create"><g:message code="default.new.label"
                                                              args="[entityName]"/></g:link></li>
    </ul>
</div>

<div id="list-theoryVote" class="content scaffold-list" role="main">
    <h1><g:message code="default.list.label" args="[entityName]"/></h1>
    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>
    <table>
        <thead>
        <tr>

            <g:sortableColumn property="dateCreated"
                              title="${message(code: 'theoryVote.dateCreated.label', default: 'Date Created')}"/>

            <th><g:message code="theoryVote.theory.label" default="Theory"/></th>

            <th><g:message code="theoryVote.user.label" default="User"/></th>

        </tr>
        </thead>
        <tbody>
        <g:each in="${theoryVoteInstanceList}" status="i" var="theoryVoteInstance">
            <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">

                <td><g:link action="show"
                            id="${theoryVoteInstance.id}">${fieldValue(bean: theoryVoteInstance, field: "dateCreated")}</g:link></td>

                <td>${fieldValue(bean: theoryVoteInstance, field: "theory")}</td>

                <td>${fieldValue(bean: theoryVoteInstance, field: "user")}</td>

            </tr>
        </g:each>
        </tbody>
    </table>

    <div class="pagination">
        <g:paginate total="${theoryVoteInstanceTotal}"/>
    </div>
</div>
</body>
</html>
