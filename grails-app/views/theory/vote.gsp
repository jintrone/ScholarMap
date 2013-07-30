<%--
  Created by IntelliJ IDEA.
  User: josh
  Date: 7/12/13
  Time: 9:33 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="ScholarMap.Theory" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="main">
    <title>Vote for a theory</title>
</head>

<body>
<div role="main">
    <h1>Select a Theory</h1>
    <g:if test="${flash.update}">
        <p class="update">
            Welcome back ${session.user.name}!  If you haven't already, please make sure to <g:link controller="theoryTheoryLink" action="update">update associations for your research areas.</g:link>
        </p>
    </g:if>
    <g:if test="${flash.done}">
        <p class="donetext">
            ${flash.done}
        </p>
    </g:if>
    <g:if test="${flash.info}">
        <div class="message" role="status">${flash.info}</div>
    </g:if>

    <p>Have a look at the list below, and pick a theory or subfield that has been of value to you in your socio-technical research.</p>
</br>
    <p>If nothing seems to fit, don't worry!  You'll have a chance to add something new on the next page.</p>

</div>

<g:form controller="theory" action="saveVote" method="post">
    <table id="theorylist">
        <thead>
        <tr>

            <g:sortableColumn property="name" title="${message(code: 'theory.name.label', default: 'Name')}"/>

            <th>Description</th>

            <th>Select</th>

        </tr>
        </thead>
        <tbody>

        <g:each in="${theoryInstanceList}" status="i" var="theoryInstance">
            <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">

                <td>${fieldValue(bean: theoryInstance, field: "name")}</td>

                <td>${fieldValue(bean: theoryInstance, field: "description")}</td>

                <td><g:radio name="id" value="${theoryInstance.id}"/></td>
            </tr>
        </g:each>

        </tbody>
    </table>
    <div class="buttons">
        <span class="button">
            <g:submitButton name="submit" value="Next --${">"}"/>
        </span>
    </div>

</g:form>

</body>

<content tag="sidebar">
    <g:render template="/common/theories"/>
</content>

</html>