<%--
  Created by IntelliJ IDEA.
  User: josh
  Date: 7/12/13
  Time: 9:33 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="ScholarMap.Reference" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="noside">
    <title>Vote for a reference</title>
</head>

<body>
<div role="main">
    <h1>Select references</h1>

    <p>Pick any references that help to characterize <b>${session?.theory?.name}</b>.
    A good reference is one that either helps define the theory or subfield, or illustrates its use in practice.</p>
</br>
    <p>If nothing seems to fit, don't worry!  You'll have a chance to add something new on the next page.</p>

</div>
<g:form action="saveVotes">
    <table id="references">
        <thead>
        <tr>
            <g:sortableColumn property="year" title="${message(code: 'reference.year.label', default: 'Year')}"/>

            <g:sortableColumn property="lastName" title="${message(code: 'reference.lastName.label', default: 'Principle Author')}"/>

            <g:sortableColumn property="text" class="wide" title="${message(code: 'reference.text.label', default: 'Full Text')}"/>

            <th>Select</th>

        </tr>
        </thead>
        <tbody>

        <g:each in="${referenceInstanceList}" status="i" var="referenceInstance">
            <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">

                <td>${referenceInstance.year}</td>

                <td>${fieldValue(bean: referenceInstance, field: "lastName")},${fieldValue(bean: referenceInstance, field: "firstInitial")}</td>

                <td>${fieldValue(bean: referenceInstance, field: "text")}</td>

                <td><g:checkBox name="referenceId" value="${referenceInstance.id}" checked="false"/></td>
            </tr>
        </g:each>

        </tbody>
    </table>

    <div class="buttons">
        <span class="button">
            <g:submitButton name="Submit" value="Next --${">"}"/>
        </span>
    </div>

</g:form>

</body>


</html>