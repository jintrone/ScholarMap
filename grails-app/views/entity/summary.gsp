<%--
  Created by IntelliJ IDEA.
  User: josh
  Date: 7/7/14
  Time: 12:21 PM
--%>

<%@ page import="csst15.Entity" contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="main"/>
    <title></title>
</head>

<body>
<div class="container">
    <h3>${ Entity.stringify(type).capitalize()} summary</h3>
    <g:render template="/common/entitySummary" model="[data: data, type: type]"/>

</div>

</body>
</html>