<g:applyLayout name="main">
    <g:set var="isOwner" value="${sec.loggedInUserInfo(field: 'id') == user.id.toString()}"/>
    <div class="page page-profile">
        <div class="row">
            <div class="col-md-6">

                <div class="panel panel-profile">
                    <div class="panel-heading text-center bg-info">
                        <g:if test="${user.photo}">
                            <img class="img-circle img80_80"
                                 src="${createLink(controller: 'user', action: 'avatar_image', id: user.id)}"/>
                        </g:if>
                        <g:else>
                            <g:img alt="" uri="/images/default-user.png" class="img-circle img80_80"/>
                        </g:else>
                        <g:if test="${hasCurrentUser || (user.visibilityConf.isFirstNameVisible && user.visibilityConf.isLastNameVisible)}">
                            <h3>${user.firstName} ${user.lastName}</h3>
                        </g:if>
                        <g:elseif test="${hasCurrentUser || user.visibilityConf.isUsernameVisible}">
                            <h3>${user.username}</h3>
                        </g:elseif>
                        <g:if test="${hasCurrentUser || user.visibilityConf.isPositionVisible}">
                            <p>${user.position?.name}</p>
                        </g:if>
                    </div>
                </div>
            </div>

            <div class="col-md-6">

                <div class="panel panel-default">
                    <div class="panel-heading">
                        <strong style="padding-right: 25px"><span class="glyphicon glyphicon-th"></span> Profile Info
                        </strong>
                        <g:if test="${isOwner}">
                            <g:link title="Edit Profile" controller="user" action="edit"
                                    class="glyphicon glyphicon-edit"
                                    params="[username: user.username]"/>
                            <g:link title="Change Password" controller="user" action="changePasswordPage"
                                    class="glyphicon glyphicon-lock"
                                    params="[username: user.username]"/>
                        </g:if>
                    </div>

                    <div class="panel-body">
                        <div class="media">
                            <div class="media-body">
                                <ul class="list-unstyled list-info">
                                    <g:if test="${hasCurrentUser || user.visibilityConf.isEmailVisible}">
                                        <li>
                                            <span class="icon glyphicon glyphicon-envelope"></span>
                                            <label>Email</label>
                                            ${user.email}
                                        </li>
                                    </g:if>
                                    <g:if test="${hasCurrentUser || user.visibilityConf.isDegreeYearVisible}">
                                        <li>
                                            <span class="icon glyphicon glyphicon-user"></span>
                                            <label>Degree Year</label>
                                            ${user.degreeYear}
                                        </li>
                                    </g:if>
                                    <g:if test="${hasCurrentUser || user.visibilityConf.isInstitutionVisible}">
                                        <li>
                                            <span class="icon glyphicon glyphicon-user"></span>
                                            <label>Institution</label>
                                            ${user.institution}
                                        </li>
                                    </g:if>
                                    <g:if test="${hasCurrentUser || user.visibilityConf.isSpecializationVisible}">
                                        <li>
                                            <span class="icon glyphicon glyphicon-user"></span>
                                            <label>Specialization</label>
                                            ${user.specialization?.title}
                                        </li>
                                    </g:if>
                                    <g:if test="${hasCurrentUser || user.visibilityConf.isDepartmentVisible}">
                                        <li>
                                            <span class="icon glyphicon glyphicon-flag"></span>
                                            <label>Department</label>
                                            ${user.department?.title}
                                        </li>
                                    </g:if>
                                </ul>

                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</g:applyLayout>