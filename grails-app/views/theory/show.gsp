<%@ page import="cssttheorybuilder.Theory" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main">
    <g:set var="entityName" value="${message(code: 'theory.label', default: 'Theory')}"/>
    <title><g:message code="default.show.label" args="[entityName]"/></title>
</head>

<body>
<a href="#show-theory" class="skip" tabindex="-1"><g:message code="default.link.skip.label"
                                                             default="Skip to content&hellip;"/></a>

<div class="nav" role="navigation">
    <ul>
        <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
        <li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]"/></g:link></li>
        <li><g:link class="create" action="create"><g:message code="default.new.label"
                                                              args="[entityName]"/></g:link></li>
    </ul>
</div>

<div id="show-theory" class="content scaffold-show" role="main">
    <h1><g:message code="default.show.label" args="[entityName]"/></h1>
    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>
    <ol class="property-list theory">

        <g:if test="${theoryInstance?.name}">
            <li class="fieldcontain">
                <span id="name-label" class="property-label"><g:message code="theory.name.label" default="Name"/></span>

                <span class="property-value" aria-labelledby="name-label"><g:fieldValue bean="${theoryInstance}"
                                                                                        field="name"/></span>

            </li>
        </g:if>

        <g:if test="${theoryInstance?.description}">
            <li class="fieldcontain">
                <span id="description-label" class="property-label"><g:message code="theory.description.label"
                                                                               default="Description"/></span>

                <span class="property-value" aria-labelledby="description-label"><g:fieldValue bean="${theoryInstance}"
                                                                                               field="description"/></span>

            </li>
        </g:if>

        <g:if test="${theoryInstance?.creator}">
            <li class="fieldcontain">
                <span id="creator-label" class="property-label"><g:message code="theory.creator.label"
                                                                           default="Creator"/></span>

                <span class="property-value" aria-labelledby="creator-label"><g:link controller="user" action="show"
                                                                                     id="${theoryInstance?.creator?.id}">${theoryInstance?.creator?.encodeAsHTML()}</g:link></span>

            </li>
        </g:if>

        <g:if test="${theoryInstance?.dateCreated}">
            <li class="fieldcontain">
                <span id="dateCreated-label" class="property-label"><g:message code="theory.dateCreated.label"
                                                                               default="Date Created"/></span>

                <span class="property-value" aria-labelledby="dateCreated-label"><g:formatDate
                        date="${theoryInstance?.dateCreated}"/></span>

            </li>
        </g:if>

        <g:if test="${theoryInstance?.referenceVotes}">
            <li class="fieldcontain">
                <span id="referenceVotes-label" class="property-label"><g:message code="theory.referenceVotes.label"
                                                                                  default="Reference Votes"/></span>

                <g:each in="${theoryInstance.referenceVotes}" var="r">
                    <span class="property-value" aria-labelledby="referenceVotes-label"><g:link
                            controller="theoryReferenceVote" action="show"
                            id="${r.id}">${r?.encodeAsHTML()}</g:link></span>
                </g:each>

            </li>
        </g:if>

        <g:if test="${theoryInstance?.references}">
            <li class="fieldcontain">
                <span id="references-label" class="property-label"><g:message code="theory.references.label"
                                                                              default="References"/></span>

                <g:each in="${theoryInstance.references}" var="r">
                    <span class="property-value" aria-labelledby="references-label"><g:link controller="reference"
                                                                                            action="show"
                                                                                            id="${r.id}">${r?.encodeAsHTML()}</g:link></span>
                </g:each>

            </li>
        </g:if>

        <g:if test="${theoryInstance?.votes}">
            <li class="fieldcontain">
                <span id="votes-label" class="property-label"><g:message code="theory.votes.label"
                                                                         default="Votes"/></span>

                <g:each in="${theoryInstance.votes}" var="v">
                    <span class="property-value" aria-labelledby="votes-label"><g:link controller="theoryVote"
                                                                                       action="show"
                                                                                       id="${v.id}">${v?.encodeAsHTML()}</g:link></span>
                </g:each>

            </li>
        </g:if>

    </ol>
    <g:form>
        <fieldset class="buttons">
            <g:hiddenField name="id" value="${theoryInstance?.id}"/>
            <g:link class="edit" action="edit" id="${theoryInstance?.id}"><g:message code="default.button.edit.label"
                                                                                     default="Edit"/></g:link>
            <g:actionSubmit class="delete" action="delete"
                            value="${message(code: 'default.button.delete.label', default: 'Delete')}"
                            onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');"/>
        </fieldset>
    </g:form>
</div>
</body>
</html>
