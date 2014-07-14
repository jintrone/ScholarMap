<%--
  Created by IntelliJ IDEA.
  User: josh
  Date: 7/7/14
  Time: 12:21 PM
--%>

<%@ page import="csst15.Theory" contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="main"/>
    <title></title>
</head>

<body>
<div class="container">
<table id="references" class="table">
    <thead>
    <tr>
        <th>
            Theory
        </th>
        <th>
            Detail
        </th>
        <th>
            Usage
        </th>
        <th>
            References
        </th>


    </tr>
    </thead>
    <tbody>
    <g:each in="${Theory.list(sort:"author")}" var="item">

        <tr>
            <td>${ref.author}</td>
            <td>${ref.year}</td>
            <td>${ref.text}</td>

        </tr>

    </g:each>
    </tbody>
</table>
</div>

</body>
</html>