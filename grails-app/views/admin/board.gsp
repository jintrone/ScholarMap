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
                        <td>${user.id}</td>
                        <td>${user.username}</td>
                        <td>${user.firstName} ${user.lastName}</td>
                        <td>${user.email}</td>
                        <td>${user.enabled ? 'true' : 'false'}</td>
                    </tr>
                </g:each>
                %{--<tr class="active">--}%
                %{--<td>Table cell</td>--}%
                %{--<td>Column content</td>--}%
                %{--<td>Column content</td>--}%
                %{--<td>Column content</td>--}%
                %{--</tr>--}%
                %{--<tr>--}%
                %{--<td>Table cell</td>--}%
                %{--<td>Column content</td>--}%
                %{--<td>Column content</td>--}%
                %{--<td>Column content</td>--}%
                %{--</tr>--}%
                %{--<tr class="success">--}%
                %{--<td>Table cell</td>--}%
                %{--<td>Column content</td>--}%
                %{--<td>Column content</td>--}%
                %{--<td>Column content</td>--}%
                %{--</tr>--}%
                %{--<tr>--}%
                %{--<td>Table cell</td>--}%
                %{--<td>Column content</td>--}%
                %{--<td>Column content</td>--}%
                %{--<td>Column content</td>--}%
                %{--</tr>--}%
                %{--<tr class="warning">--}%
                %{--<td>Table cell</td>--}%
                %{--<td>Column content</td>--}%
                %{--<td>Column content</td>--}%
                %{--<td>Column content</td>--}%
                %{--</tr>--}%
                %{--<tr>--}%
                %{--<td>Table cell</td>--}%
                %{--<td>Column content</td>--}%
                %{--<td>Column content</td>--}%
                %{--<td>Column content</td>--}%
                %{--</tr>--}%
                %{--<tr class="danger">--}%
                %{--<td>Table cell</td>--}%
                %{--<td>Column content</td>--}%
                %{--<td>Column content</td>--}%
                %{--<td>Column content</td>--}%
                %{--</tr>--}%
                </tbody>
            </table>

        </div>
    </div>
</g:applyLayout>