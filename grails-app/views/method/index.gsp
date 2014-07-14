<%--
  Created by IntelliJ IDEA.
  User: josh
  Date: 7/7/14
  Time: 12:21 PM
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="main"/>
    <title></title>
</head>

<body>
<div class="container">
    <h3>Field summary</h3>
    <g:render template="/common/entitySummary" model="[data: data, type: 'field']"/>
</div>

</body>
</html>