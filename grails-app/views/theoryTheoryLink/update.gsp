<%--
  Created by IntelliJ IDEA.
  User: josh
  Date: 7/24/13
  Time: 11:21 PM
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="scholarmap.Theory" %>
<html>
<head>
    <meta name="layout" content="main">
    <title>Select a theory to update</title>
</head>

<body>
<div role="main">
    <h1>Select a theory to update</h1>

    <p>You have indicated that the following theories / fields have been valuable to you.  Please click on any of them
    to update relationships with other theories.</p>

    <div id="updateTheoryList">
        <g:each in="${theoryInstanceList}" status="i" var="theoryInstance">
            <p>${i+1}. <g:link action="createForUpdate" params="[theoryId:theoryInstance.id]">${theoryInstance.name}</g:link></p>
        </g:each>
    </div>
    <g:form>
    <div class="buttons">
        <span class="button">
            <g:actionSubmit name="submit" action="doneUpdate" value="Done updating --${">"}"/>
        </span>
    </div>
    </g:form>
</div>
</body>
</html>