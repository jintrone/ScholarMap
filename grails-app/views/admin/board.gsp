<g:applyLayout name="main">
    <section class="panel panel-default" id="mandatory-sec">
        <div class="panel-heading"><strong><span class="glyphicon glyphicon-th"></span> Set required fields</strong>
        </div>

        <div class="panel-body">
            <g:each in="${fieldMandConf}" status="i" var="field">
                <div class="form-group">
                    <div class="col-sm-2">
                        <label for="">${fieldMandConf.get(i).fieldName}</label>
                    </div>

                    <div class="col-sm-2">
                    </div>

                    <div class="col-sm-2">
                        <label class="switch switch-success">
                            <input type="checkbox" id="${fieldMandConf.get(i).fieldName}"
                                   class="mandatory" ${fieldMandConf.get(i).isMandatory ? 'checked="checked"' : ''}/><i></i>
                        </label>
                    </div>
                </div>
            </g:each>
        </div>
    </section>

    <div class="row">
        <div class="col-md-6">
            <section class="panel panel-default">
                <div class="panel-heading"><strong><span class="glyphicon glyphicon-th"></span> Manipulate registration
                </strong>
                </div>

                <div class="panel-body">
                    <p>Disable/Enable</p>
                    <label class="switch switch-success">
                        <input type="checkbox" id="isRegEnabled" ${isRegEnabled ? 'checked="checked"' : ''}/><i></i>
                    </label>
                </div>
            </section>
        </div>

        <div class="col-md-6">
            <section class="panel panel-default">
                <div class="panel-heading"><strong><span class="glyphicon glyphicon-th"></span> Create user</strong>
                </div>

                <div class="panel-body">
                    <g:form class="form-horizontal" controller="admin" action="createUser">
                        <div class="form-group">
                            <label for="inputEmail" class="col-sm-2 control-label">Email</label>

                            <div class="col-sm-8">
                                <input type="email" class="form-control" id="inputEmail" name="email"
                                       placeholder="Email">
                            </div>
                        </div>

                        <div class="form-group">
                            <div class="col-sm-offset-2 col-sm-10">
                                <button type="submit" class="btn btn-success">Create</button>
                            </div>
                        </div>
                    </g:form>
                </div>
            </section>
        </div>
    </div>
    <div class="panel panel-default">
        <div class="panel-heading"><strong><span class="glyphicon glyphicon-th"></span> User's list</strong></div>

        <div class="panel-body">

            <table class="table">
                <thead>
                <tr>
                    <th>Actions</th>
                    <th>id</th>
                    <th>Username</th>
                    <th>Full Name</th>
                    <th>E-mail address</th>
                    <th>Enabled</th>
                </tr>
                </thead>
                <tbody>
                <g:each in="${users}" var="user">
                    <tr>
                        <td>
                            <g:link class="glyphicon glyphicon-eye-open" controller="user" action="profile"
                                    params="[username: user.username]"/>
                            <g:link class="glyphicon glyphicon-edit" controller="admin" action="editUserProfile"
                                    params="[username: user.username]"/>
                            <g:link class="glyphicon glyphicon-remove-sign" controller="admin" action="deleteUser"
                                    id="${user.id}"/>
                        </td>
                        <td>${user.id}</td>
                        <td>${user.username}</td>
                        <td>${user.firstName} ${user.lastName}</td>
                        <td>${user.email}</td>
                        <td>${user.enabled ? 'true' : 'false'}</td>
                    </tr>
                </g:each>
                </tbody>
            </table>
        </div>
    </div>
</g:applyLayout>