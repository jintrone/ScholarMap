<%@ page import="cssttheorybuilder.TheoryReferenceVote" %>



<g:hiddenField name="theoryId" value="${session.theoryId}"/>



    <table class="layout" id="addreference">
        <tr>

            <td>
                <nobr>
                    <label for="year">
                        <g:message code="reference.year.label" default="Publication Year:"/>

                    </label>

                </nobr>
            </td>
            <td colspan="2">
                <g:textField name="year"  value="${reference?.year}"/>
            </td>
        </tr>
        <tr >
            <td>
                <nobr>
                    <label for="lastName">
                        <g:message code="reference.authorLastName.label" default="Primary Author:"/>
                    </label>
                </nobr>
            <td>
                <g:textField name="lastName"  value="${reference?.lastName}"/>
            </td>
            <td>

                <g:textField name="firstInitial" size="15" value="${reference?.firstInitial}"/>
                </nobr>
            </td>
        </tr    >
        <tr class="small-row">

            <td></td>
            <td class="field-hint">(last name)</td>
            <td class="field-hint"><nobr>(first initial)</nobr></td>
             </tr>

        <tr>
            <td>
                <label for="text">
                    <g:message code="reference.text.label" default="Complete Reference"/>:

                </label>
                </td>
            <td colspan="3">
                <g:textArea id="referencetext" name="text"  value="${reference?.text}"/>

            </td>
        </tr>
    </table>





