<%@ page import="scholarmap.Reference" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main">
    <g:set var="entityName" value="${message(code: 'reference.label', default: 'Reference')}"/>
    <title><g:message code="default.show.label" args="[entityName]"/></title>
</head>

<body>
<a href="#show-reference" class="skip" tabindex="-1"><g:message code="default.link.skip.label"
                                                                default="Skip to content&hellip;"/></a>

<div class="nav" role="navigation">
    <ul>
        <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
        <li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]"/></g:link></li>
        <li><g:link class="create" action="create"><g:message code="default.new.label"
                                                              args="[entityName]"/></g:link></li>
    </ul>
</div>

<div id="show-reference" class="content scaffold-show" role="main">
    <h1><g:message code="default.show.label" args="[entityName]"/></h1>
    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>
    <ol class="property-list reference">

        <g:if test="${referenceInstance?.text}">
            <li class="fieldcontain">
                <span id="text-label" class="property-label"><g:message code="reference.text.label"
                                                                        default="Text"/></span>

                <span class="property-value" aria-labelledby="text-label"><g:fieldValue bean="${referenceInstance}"
                                                                                        field="text"/></span>

            </li>
        </g:if>

        <g:if test="${referenceInstance?.creator}">
            <li class="fieldcontain">
                <span id="creator-label" class="property-label"><g:message code="reference.creator.label"
                                                                           default="Creator"/></span>

                <span class="property-value" aria-labelledby="creator-label"><g:link controller="user" action="show"
                                                                                     id="${referenceInstance?.creator?.id}">${referenceInstance?.creator?.encodeAsHTML()}</g:link></span>

            </li>
        </g:if>

        <g:if test="${referenceInstance?.dateCreated}">
            <li class="fieldcontain">
                <span id="dateCreated-label" class="property-label"><g:message code="reference.dateCreated.label"
                                                                               default="Date Created"/></span>

                <span class="property-value" aria-labelledby="dateCreated-label"><g:formatDate
                        date="${referenceInstance?.dateCreated}"/></span>

            </li>
        </g:if>

        <g:if test="${referenceInstance?.theories}">
            <li class="fieldcontain">
                <span id="theories-label" class="property-label"><g:message code="reference.theories.label"
                                                                            default="Theories"/></span>

                <g:each in="${referenceInstance.theories}" var="t">
                    <span class="property-value" aria-labelledby="theories-label"><g:link controller="theory"
                                                                                          action="show"
                                                                                          id="${t.id}">${t?.encodeAsHTML()}</g:link></span>
                </g:each>

            </li>
        </g:if>

        <g:if test="${referenceInstance?.theoryReferenceVotes}">
            <li class="fieldcontain">
                <span id="theoryReferenceVotes-label" class="property-label"><g:message
                        code="reference.theoryReferenceVotes.label" default="Theory Reference Votes"/></span>

                <g:each in="${referenceInstance.theoryReferenceVotes}" var="t">
                    <span class="property-value" aria-labelledby="theoryReferenceVotes-label"><g:link
                            controller="theoryReferenceVote" action="show"
                            id="${t.id}">${t?.encodeAsHTML()}</g:link></span>
                </g:each>

            </li>
        </g:if>

    </ol>
    <g:form>
        <fieldset class="buttons">
            <g:hiddenField name="id" value="${referenceInstance?.id}"/>
            <g:link class="edit" action="edit" id="${referenceInstance?.id}"><g:message code="default.button.edit.label"
                                                                                        default="Edit"/></g:link>
            <g:actionSubmit class="delete" action="delete"
                            value="${message(code: 'default.button.delete.label', default: 'Delete')}"
                            onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');"/>
        </fieldset>
    </g:form>
</div>
</body>
</html>
