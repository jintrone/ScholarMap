<g:applyLayout name="main">
    <section class="panel panel-default">
        <div class="panel-heading"><strong><span class="glyphicon glyphicon-th"></span> Manipulate registration</strong>
        </div>

        <div class="panel-body" data-ng-controller="AccordionDemoCtrl">
            <p>Disable/Enable</p>
            <label class="switch switch-success">
                <input type="checkbox" id="isRegEnabled" ${isRegEnabled ? 'checked="checked"' : ''}/><i></i>
            </label>
        </div>
    </section>
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
                            <g:link class="glyphicon glyphicon-edit" controller="user" action="edit" id="${user.id}"/>
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