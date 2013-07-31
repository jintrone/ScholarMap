
<%@ page import="scholarmap.TheoryTheoryLink" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'theoryTheoryLink.label', default: 'TheoryTheoryLink')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-theoryTheoryLink" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-theoryTheoryLink" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="dateCreated" title="${message(code: 'theoryTheoryLink.dateCreated.label', default: 'Date Created')}" />
					
						<th><g:message code="theoryTheoryLink.fromTheory.label" default="From Theory" /></th>
					
						<th><g:message code="theoryTheoryLink.toTheory.label" default="To Theory" /></th>
					
						<g:sortableColumn property="type" title="${message(code: 'theoryTheoryLink.type.label', default: 'Type')}" />
					
						<th><g:message code="theoryTheoryLink.user.label" default="User" /></th>
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${theoryTheoryLinkInstanceList}" status="i" var="theoryTheoryLinkInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${theoryTheoryLinkInstance.id}">${fieldValue(bean: theoryTheoryLinkInstance, field: "dateCreated")}</g:link></td>
					
						<td>${fieldValue(bean: theoryTheoryLinkInstance, field: "fromTheory")}</td>
					
						<td>${fieldValue(bean: theoryTheoryLinkInstance, field: "toTheory")}</td>
					
						<td>${fieldValue(bean: theoryTheoryLinkInstance, field: "type")}</td>
					
						<td>${fieldValue(bean: theoryTheoryLinkInstance, field: "user")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${theoryTheoryLinkInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
