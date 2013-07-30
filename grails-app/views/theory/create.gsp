<%@ page import="ScholarMap.Theory" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main">
    <g:set var="entityName" value="${message(code: 'theory.label', default: 'Theory')}"/>
    <title><g:message code="default.create.label" args="[entityName]"/></title>
</head>

<body>
<a href="#create-theory" class="skip" tabindex="-1"><g:message code="default.link.skip.label"
                                                               default="Skip to content&hellip;"/></a>

<div role="main">
    <h1>Add a Theory</h1>
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
    <p>Add a theory / subfield that has been of value in your socio-technical research.</p> <br/>
    <p><em><b>Note:</b> Please do not add overly broad fields
    of research (e.g. sociology); instead pick more easily delineated research areas (e.g. social network analysis).</em></p>
</div>


<div id="create-theory" class="content scaffold-create" role="main">

    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>
    <g:hasErrors bean="${theoryInstance}">
        <ul class="errors" role="alert">
            <g:eachError bean="${theoryInstance}" var="error">
                <li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message
                        error="${error}"/></li>
            </g:eachError>
        </ul>
    </g:hasErrors>
    <g:form action="save">
        <fieldset class="form">
            <g:render template="form"/>
        </fieldset>
        <fieldset class="buttons">
            <g:submitButton name="create"
                            value="Next --${">"}"/>
        </fieldset>
    </g:form>
</div>
</body>
<content tag="sidebar">
    <g:render template="/common/theories"/>
</content>

</html>
