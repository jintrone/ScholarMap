<%@ page import="cssttheorybuilder.TheoryTheoryLink" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main">
    <g:set var="entityName" value="${message(code: 'theoryTheoryLink.label', default: 'TheoryTheoryLink')}"/>
    <title><g:message code="default.create.label" args="[entityName]"/></title>
</head>

<body>
<a href="#create-theoryTheoryLink" class="skip" tabindex="-1"><g:message code="default.link.skip.label"
                                                                         default="Skip to content&hellip;"/></a>

<div id="create-theoryTheoryLink" role="main">
    <h1>Specify relationships</h1>

    <p>Select any theories or sub-fields in the following list that have significant overlap with <b>${session.theory}</b>. Theories that overlap address some of the same kinds of phenomena.
    For example, distributed cognition and activity theory overlap because both address the accumulation of structure in artifacts.</p>
    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>
    <g:hasErrors bean="${theoryTheoryLinkInstance}">
        <ul class="errors" role="alert">
            <g:eachError bean="${theoryTheoryLinkInstance}" var="error">
                <li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message
                        error="${error}"/></li>
            </g:eachError>
        </ul>
    </g:hasErrors>
    <g:form action="save">
        <g:if test="${flash.update}">
            <g:hiddenField name="update" value="true"/>
        </g:if>
        <table id="theorytheory">
            <thead>
            <tr>

                <g:sortableColumn property="name" title="${message(code: 'theory.name.label', default: 'Name')}"/>

                <th>Select</th>

            </tr>
            </thead>
            <tbody>

            <g:each in="${theoryInstanceList}" status="i" var="theoryInstance">
                <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">

                    <td>${theoryInstance.name}</td>

                    <td><g:checkBox name="theoryIds" value="${theoryInstance.id}" checked="${theoryInstance.isConnected(session.theory,session.user)}"/></td>
                </tr>
            </g:each>

            </tbody>
        </table>
        <fieldset class="buttons">
            <g:submitButton name="create"
                            value="Next --${">"}"/>
        </fieldset>
    </g:form>
</div>
</body>
</html>
