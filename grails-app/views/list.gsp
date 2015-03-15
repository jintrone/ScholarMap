<g:applyLayout name="main">
    <div class="panel panel-default">
        <div class="panel-heading"><strong><span class="glyphicon glyphicon-th"></span> Community members</strong>
        </div>

        <div class="panel-body">

            <table class="table display" cellspacing="0" id="listTable">
                <thead>
                <tr>
                    <th>Username</th>
                    <th>Full Name</th>
                    <th>Department</th>
                    <th>Position</th>
                </tr>
                </thead>
                <tbody>
                <g:each in="${users}" var="user">
                    <g:if test="${user.visibilityConf.isUsernameVisible || (user.visibilityConf.isFirstNameVisible && user.visibilityConf.isLastNameVisible)}">
                        <tr>
                            <td>
                                <g:link controller="user" action="profile"
                                        params="[username: user.username]">${user.username}</g:link>
                            </td>
                            <td>
                                <g:if test="${(user.visibilityConf.isFirstNameVisible && user.visibilityConf.isLastNameVisible)}">
                                    ${user.firstName} ${user.lastName}
                                </g:if>
                            </td>
                            <td>
                                <g:if test="${user.visibilityConf.isDepartmentVisible}">
                                    ${user.department?.title}
                                </g:if>
                            </td>
                            <td>
                                <g:if test="${user.visibilityConf.isPositionVisible}">
                                    ${user.position?.name}
                                </g:if>
                            </td>
                        </tr>
                    </g:if>
                </g:each>
                </tbody>
            </table>
        </div>
    </div>
</g:applyLayout>