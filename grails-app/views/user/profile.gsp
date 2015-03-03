<g:applyLayout name="main">

    <div class="page page-profile">

        <div class="row">
            <div class="col-md-6">

                <div class="panel panel-profile">
                    <div class="panel-heading text-center bg-info">
                        <img alt="" src="images/g1.jpg" class="img-circle img80_80">

                        <h3>${user.firstName} ${user.lastName}</h3>

                        <p>${user.position?.name}</p>
                    </div>
                </div>
            </div>

            <div class="col-md-6">

                <div class="panel panel-default">
                    <div class="panel-heading">
                        <div class="col-md-3">
                            <strong><span class="glyphicon glyphicon-th"></span> Profile Info</strong>
                        </div>

                        <div style="text-align: right">
                            <g:if test="${sec.loggedInUserInfo(field: 'id') == user.id.toString()}">
                                <g:link title="Edit Profile" controller="user" action="edit"
                                        class="glyphicon glyphicon-edit"
                                        params="[username: user.username]"/>
                                <g:link title="Change Password" controller="user" action="changePasswordPage"
                                        class="glyphicon glyphicon-lock"
                                        params="[username: user.username]"/>
                            </g:if>
                        </div>
                    </div>

                    <div class="panel-body">
                        <div class="media">
                            <div class="media-body">
                                <ul class="list-unstyled list-info">
                                    <li>
                                        <span class="icon glyphicon glyphicon-envelope"></span>
                                        <label>Email</label>
                                        ${user.email}
                                    </li>
                                    <li>
                                        <span class="icon glyphicon glyphicon-user"></span>
                                        <label>Institution</label>
                                        ${user.institution}
                                    </li>
                                    <li>
                                        <span class="icon glyphicon glyphicon-user"></span>
                                        <label>Specialization</label>
                                        ${user.specialization?.title}
                                    </li>
                                    <li>
                                        <span class="icon glyphicon glyphicon-flag"></span>
                                        <label>Department</label>
                                        ${user.department?.title}
                                    </li>
                                </ul>

                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</g:applyLayout>