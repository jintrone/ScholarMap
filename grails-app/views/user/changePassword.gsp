<g:applyLayout name="main">
    <div class="panel panel-default" xmlns="http://www.w3.org/1999/html">
        <div class="panel-body">
            <div class="col-md-6">
                <div class="panel-heading">
                    <strong><span class="glyphicon glyphicon-th"></span> Change Password</strong>
                </div>
                <g:hasErrors bean="${command}">
                    <ul class="callout callout-danger errors">
                        <g:eachError bean="${command}" var="error">
                            <li><g:message error="${error}"/></li>
                        </g:eachError>
                    </ul>
                </g:hasErrors>
                <p>In order to change your Password, you must first enter your old Password:</p>
                <g:form controller="user" action="changePassword" name="changePass"
                        class="form-horizontal form-validation">
                    <g:hiddenField name="id" value="${user.id}"/>
                    <div class="panel-body" data-ng-controller="signupCtrl" id="editPanel">

                        <div class="form-group">
                            <div class="col-sm-3">
                                <label for="">Old Password</label>
                            </div>

                            <div class="col-sm-6">
                                <input type="password" class="form-control" name="oldPassword"
                                       placeholder="Old Password"/>
                            </div>
                        </div>

                        <div class="form-group">
                            <div class="col-sm-3">
                                <label for="">New Password</label>
                            </div>

                            <div class="col-sm-6">
                                <input type="password" class="form-control" name="newPassword"
                                       placeholder="New Password"/>
                            </div>
                        </div>

                        <div class="form-group">
                            <div class="col-sm-3">
                                <label for="">Confirm Password</label>
                            </div>

                            <div class="col-sm-6">
                                <input type="password" class="form-control" name="confirmPassword"
                                       placeholder="Confirm Password"/>
                            </div>
                        </div>

                        <button type="submit" class="btn btn-default btn-block btn-lg">Change</button>

                    </div>
                </g:form>
            </div>
        </div>
    </div>
</g:applyLayout>