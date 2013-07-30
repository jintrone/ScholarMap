<%@ page import="ScholarMap.TheoryReferenceVote" %>



<g:hiddenField name="theoryId" value="${session.theoryId}"/>


<div class="fieldcontain ${hasErrors(bean: referenceInstance, field: 'text', 'error')} required">
    <table>
        <tr>
            <td>
                <label for="year">
                    <g:message code="reference.year.label" default="Year:"/>
                    <span class="required-indicator">*</span>
                </label>
                <g:textField name="year" required="" value="${referenceInstance?.year}"/>

            </td>

            <td>
                <label for="lastName">
                    <g:message code="reference.authorLastName.label" default="Author Last Name:"/>
                    <span class="required-indicator">*</span>
                </label>
                <g:textField name="lastName" required="" value="${referenceInstance?.lastName}"/>

            </td>
            <td>
                <label for="firstInitial">
                    <g:message code="reference.authorFirstInitial.label" default="Author First Initial:"/>
                    <span class="required-indicator">*</span>
                </label>
                <g:textField name="firstInitial" required="" value="${referenceInstance?.firstInitial}"/>
            </td>
        </tr>
        <tr>
            <td colspan="3">
                <label for="text">
                    <g:message code="reference.text.label" default="Text"/>
                    <span class="required-indicator">*</span>
                </label>
                <g:textArea name="text" required="" value="${referenceInstance?.text}"/>

            </td>
        </tr>
    </table>

</div>



