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
<table id="references" class="table">
    <thead>
    <tr>
        <th>
            Author
        </th>
        <th>
            Year
        </th>
        <th>
            Full text
        </th>


    </tr>
    </thead>
    <tbody>
    <g:each in="${csst15.Reference.list(sort:"author")}" var="ref">

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