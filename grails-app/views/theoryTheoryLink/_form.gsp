<%@ page import="scholarmap.TheoryTheoryLink" %>



<div class="fieldcontain ${hasErrors(bean: theoryTheoryLinkInstance, field: 'fromTheory', 'error')} required">
	<label for="fromTheory">
		<g:message code="theoryTheoryLink.fromTheory.label" default="From Theory" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="fromTheory" name="fromTheory.id" from="${scholarmap.Theory.list()}" optionKey="id" required="" value="${theoryTheoryLinkInstance?.fromTheory?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: theoryTheoryLinkInstance, field: 'toTheory', 'error')} required">
	<label for="toTheory">
		<g:message code="theoryTheoryLink.toTheory.label" default="To Theory" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="toTheory" name="toTheory.id" from="${scholarmap.Theory.list()}" optionKey="id" required="" value="${theoryTheoryLinkInstance?.toTheory?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: theoryTheoryLinkInstance, field: 'type', 'error')} ">
	<label for="type">
		<g:message code="theoryTheoryLink.type.label" default="Type" />
		
	</label>
	<g:textField name="type" value="${theoryTheoryLinkInstance?.type}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: theoryTheoryLinkInstance, field: 'user', 'error')} required">
	<label for="user">
		<g:message code="theoryTheoryLink.user.label" default="User" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="user" name="user.id" from="${scholarmap.User.list()}" optionKey="id" required="" value="${theoryTheoryLinkInstance?.user?.id}" class="many-to-one"/>
</div>

