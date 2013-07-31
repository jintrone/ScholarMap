<%@ page import="scholarmap.TheoryVote" %>



<div class="fieldcontain ${hasErrors(bean: theoryVoteInstance, field: 'theory', 'error')} required">
    <label for="theory">
        <g:message code="theoryVote.theory.label" default="Theory"/>
        <span class="required-indicator">*</span>
    </label>
    <g:select id="theory" name="theory.id" from="${scholarmap.Theory.list()}" optionKey="id" required=""
              value="${theoryVoteInstance?.theory?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: theoryVoteInstance, field: 'user', 'error')} required">
    <label for="user">
        <g:message code="theoryVote.user.label" default="User"/>
        <span class="required-indicator">*</span>
    </label>
    <g:select id="user" name="user.id" from="${scholarmap.User.list()}" optionKey="id" required=""
              value="${theoryVoteInstance?.user?.id}" class="many-to-one"/>
</div>

