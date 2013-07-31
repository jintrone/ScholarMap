<%@ page import="scholarmap.TheoryReferenceVote" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main">
    <g:set var="entityName" value="${message(code: 'theoryReferenceVote.label', default: 'TheoryReferenceVote')}"/>
    <title><g:message code="default.create.label" args="[entityName]"/></title>
</head>

<body>
<a href="#create-theoryReferenceVote" class="skip" tabindex="-1"><g:message code="default.link.skip.label"
                                                                            default="Skip to content&hellip;"/></a>



<div id="create-theoryReferenceVote" role="main">
    <h1>Add references</h1>
    <p>
        Add a reference that you feel is important for understanding <b>${session.theory.name}</b>.  Include key references that help define the theory or subfield, or illustrate its use in practice.
    </p>
    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>
    <g:hasErrors bean="${theoryReferenceVoteInstance}">
        <ul class="errors" role="alert">
            <g:eachError bean="${theoryReferenceVoteInstance}" var="error">
                <li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message
                        error="${error}"/></li>
            </g:eachError>
        </ul>
    </g:hasErrors>
    <g:hasErrors bean="${reference}">
        <ul class="errors" role="alert">
            <g:eachError bean="${reference}" var="error">
                <li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message
                        error="${error}"/></li>
            </g:eachError>
        </ul>
    </g:hasErrors>
    <g:form>

            <g:render template="form"/>

        <fieldset class="buttons">

            <g:actionSubmit name="add" class="addanother"
                            value="Add another" action="addAnother"/>

            <g:actionSubmit name="create"
                            value="Next --${">"}" action="save"/>
        </fieldset>
    </g:form>
</div>

<content tag="sidebar">
    <g:render template="/common/references"/>
</content>
</body>


</html>
