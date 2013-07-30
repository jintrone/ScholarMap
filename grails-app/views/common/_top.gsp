<div id="site-header">
    <span class="title">Socio-technical Cartography</span>  <br/>
    <span class="subtitle">Mapping the sociotechnical landscape</span>
</div>
<g:if test = "${session.user}">
<table id="login-info" class="layout">
    <tr>
        <td id="theory-message">
            <g:if test = "${!session.theory}">
                Theories completed: <g:if test="${session.theoryCount < 3}">${session.theoryCount} of 3</g:if><g:else>${session.theoryCount}</g:else>
            </g:if>
            <g:else>
            <span>
                Current Theory: ${session.theory.name}
            </span>
            </g:else>
        </td>
        <td id="status-message">
                Step: ${session.step} of 4
        </td>
        <td id="user">
                <nobr>Hi, ${session.user?.name} <g:link controller="user" action="logout"><em>(logout)</em></g:link></nobr>

        </td>
    </tr>


</table>
</g:if>
<div class="clearfix"><!--no content--></div>