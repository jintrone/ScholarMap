
<%@ page import="ScholarMap.TheoryTheoryLink" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'theoryTheoryLink.label', default: 'TheoryTheoryLink')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-theoryTheoryLink" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-theoryTheoryLink" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list theoryTheoryLink">
			
				<g:if test="${theoryTheoryLinkInstance?.dateCreated}">
				<li class="fieldcontain">
					<span id="dateCreated-label" class="property-label"><g:message code="theoryTheoryLink.dateCreated.label" default="Date Created" /></span>
					
						<span class="property-value" aria-labelledby="dateCreated-label"><g:formatDate date="${theoryTheoryLinkInstance?.dateCreated}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${theoryTheoryLinkInstance?.fromTheory}">
				<li class="fieldcontain">
					<span id="fromTheory-label" class="property-label"><g:message code="theoryTheoryLink.fromTheory.label" default="From Theory" /></span>
					
						<span class="property-value" aria-labelledby="fromTheory-label"><g:link controller="theory" action="show" id="${theoryTheoryLinkInstance?.fromTheory?.id}">${theoryTheoryLinkInstance?.fromTheory?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${theoryTheoryLinkInstance?.toTheory}">
				<li class="fieldcontain">
					<span id="toTheory-label" class="property-label"><g:message code="theoryTheoryLink.toTheory.label" default="To Theory" /></span>
					
						<span class="property-value" aria-labelledby="toTheory-label"><g:link controller="theory" action="show" id="${theoryTheoryLinkInstance?.toTheory?.id}">${theoryTheoryLinkInstance?.toTheory?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${theoryTheoryLinkInstance?.type}">
				<li class="fieldcontain">
					<span id="type-label" class="property-label"><g:message code="theoryTheoryLink.type.label" default="Type" /></span>
					
						<span class="property-value" aria-labelledby="type-label"><g:fieldValue bean="${theoryTheoryLinkInstance}" field="type"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${theoryTheoryLinkInstance?.user}">
				<li class="fieldcontain">
					<span id="user-label" class="property-label"><g:message code="theoryTheoryLink.user.label" default="User" /></span>
					
						<span class="property-value" aria-labelledby="user-label"><g:link controller="user" action="show" id="${theoryTheoryLinkInstance?.user?.id}">${theoryTheoryLinkInstance?.user?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${theoryTheoryLinkInstance?.id}" />
					<g:link class="edit" action="edit" id="${theoryTheoryLinkInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
