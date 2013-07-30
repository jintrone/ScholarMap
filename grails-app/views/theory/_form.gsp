<%@ page import="ScholarMap.Theory" %>



<div class="fieldcontain ${hasErrors(bean: theoryInstance, field: 'name', 'error')} required">
    <label for="name">
        <g:message code="theory.name.label" default="Name"/>
        <span class="required-indicator">*</span>
    </label>
    <g:textField name="name" required="" value="${theoryInstance?.name}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: theoryInstance, field: 'description', 'error')} ">
    <label for="description">
        <g:message code="theory.description.label" default="Description"/>

    </label>
    <g:textArea name="description" cols="40" rows="5" maxlength="1000" value="${theoryInstance?.description}"/>
</div>




