<%@ page import="scholarmap.TheoryVote" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main">
    <g:set var="entityName" value="${message(code: 'theoryVote.label', default: 'TheoryVote')}"/>
    <title><g:message code="default.show.label" args="[entityName]"/></title>
</head>

<body>
<a href="#show-theoryVote" class="skip" tabindex="-1"><g:message code="default.link.skip.label"
                                                                 default="Skip to content&hellip;"/></a>

<div class="nav" role="navigation">
    <ul>
        <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
        <li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]"/></g:link></li>
        <li><g:link class="create" action="create"><g:message code="default.new.label"
                                                              args="[entityName]"/></g:link></li>
    </ul>
</div>

<div id="show-theoryVote" class="content scaffold-show" role="main">
    <h1><g:message code="default.show.label" args="[entityName]"/></h1>
    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>
    <ol class="property-list theoryVote">

        <g:if test="${theoryVoteInstance?.dateCreated}">
            <li class="fieldcontain">
                <span id="dateCreated-label" class="property-label"><g:message code="theoryVote.dateCreated.label"
                                                                               default="Date Created"/></span>

                <span class="property-value" aria-labelledby="dateCreated-label"><g:formatDate
                        date="${theoryVoteInstance?.dateCreated}"/></span>

            </li>
        </g:if>

        <g:if test="${theoryVoteInstance?.theory}">
            <li class="fieldcontain">
                <span id="theory-label" class="property-label"><g:message code="theoryVote.theory.label"
                                                                          default="Theory"/></span>

                <span class="property-value" aria-labelledby="theory-label"><g:link controller="theory" action="show"
                                                                                    id="${theoryVoteInstance?.theory?.id}">${theoryVoteInstance?.theory?.encodeAsHTML()}</g:link></span>

            </li>
        </g:if>

        <g:if test="${theoryVoteInstance?.user}">
            <li class="fieldcontain">
                <span id="user-label" class="property-label"><g:message code="theoryVote.user.label"
                                                                        default="User"/></span>

                <span class="property-value" aria-labelledby="user-label"><g:link controller="user" action="show"
                                                                                  id="${theoryVoteInstance?.user?.id}">${theoryVoteInstance?.user?.encodeAsHTML()}</g:link></span>

            </li>
        </g:if>

    </ol>
    <g:form>
        <fieldset class="buttons">
            <g:hiddenField name="id" value="${theoryVoteInstance?.id}"/>
            <g:link class="edit" action="edit" id="${theoryVoteInstance?.id}"><g:message
                    code="default.button.edit.label" default="Edit"/></g:link>
            <g:actionSubmit class="delete" action="delete"
                            value="${message(code: 'default.button.delete.label', default: 'Delete')}"
                            onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');"/>
        </fieldset>
    </g:form>
</div>
</body>
</html>
