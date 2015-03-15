<%@ page import="csst15.lists.Position; csst15.lists.Department; csst15.lists.Specialization" %>
<g:applyLayout name="main">
    <div class="panel panel-default" xmlns="http://www.w3.org/1999/html">
        <div class="panel-body">
            <div class="col-md-6" id="admin_edit_profile">
                <div class="panel-heading">
                    <strong><span class="glyphicon glyphicon-th"></span> Edit Profile</strong>
                </div>
                <g:hasErrors bean="${userCommand}">
                    <ul class="callout callout-danger errors">
                        <g:eachError bean="${userCommand}" var="error">
                            <li><g:message error="${error}"/></li>
                        </g:eachError>
                    </ul>
                </g:hasErrors>
                <g:form controller="user" action="update" name="update" class="form-horizontal form-validation"
                        id="${user.id}">
                    <g:hiddenField name="userId" value="${user.id}"/>
                    <div class="panel-body">

                        <div class="form-group">
                            <div class="col-sm-3">
                                <label for="username">User Name</label>
                            </div>

                            <div class="col-sm-6">
                                <g:input type="text" class="form-control" name="username" placeholder="Not set"
                                       value="${user.username}"/>
                            </div>

                            <div class="col-sm-3">
                                <label class="switch switch-success">
                                    <input type="checkbox" id="isUsernameLocked"
                                           class="lock" ${lockConf.isUsernameLocked ? 'checked="checked"' : ''}/><i></i>
                                </label>
                            </div>
                        </div>

                        <div class="form-group">
                            <div class="col-sm-3">
                                <label for="email">Email</label>
                            </div>

                            <div class="col-sm-6">
                                <g:input type="email" class="form-control" name="email" value="${user.email}"
                                       placeholder="Not set"/>
                            </div>

                            <div class="col-sm-3">
                                <label class="switch switch-success">
                                    <input type="checkbox" id="isEmailLocked"
                                           class="lock" ${lockConf.isEmailLocked ? 'checked="checked"' : ''}/><i></i>
                                </label>
                            </div>
                        </div>

                        <div class="form-group">
                            <div class="col-sm-3">
                                <label for="firstName">First Name</label>
                            </div>

                            <div class="col-sm-6">
                                <g:input type="text" class="form-control" name="firstName" placeholder="Not set"
                                       value="${user.firstName}"/>
                            </div>

                            <div class="col-sm-3">
                                <label class="switch switch-success">
                                    <input type="checkbox" id="isFirstNameLocked"
                                           class="lock" ${lockConf.isFirstNameLocked ? 'checked="checked"' : ''}/><i></i>
                                </label>
                            </div>
                        </div>

                        <div class="form-group">
                            <div class="col-sm-3">
                                <label for="lastName">Last Name</label>
                            </div>

                            <div class="col-sm-6">
                                <g:input type="text" class="form-control" name="lastName" placeholder="Not set"
                                       value="${user.lastName}"/>
                            </div>

                            <div class="col-sm-3">
                                <label class="switch switch-success">
                                    <input type="checkbox" id="isLastNameLocked"
                                           class="lock" ${lockConf.isLastNameLocked ? 'checked="checked"' : ''}/><i></i>
                                </label>
                            </div>
                        </div>

                        <div class="form-group">
                            <div class="col-sm-3">
                                <label for="degreeYear">Degree Year</label>
                            </div>

                            <div class="col-sm-6">
                                <g:input type="text" class="form-control" name="degreeYear"
                                       placeholder="Not set"
                                       value="${user.degreeYear}"/>
                            </div>

                            <div class="col-sm-3">
                                <label class="switch switch-success">
                                    <input type="checkbox" id="isDegreeYearLocked"
                                           class="lock" ${lockConf.isDegreeYearLocked ? 'checked="checked"' : ''}/><i></i>
                                </label>
                            </div>
                        </div>

                        <div class="form-group">
                            <div class="col-sm-3">
                                <label for="institution">Institution</label>
                            </div>

                            <div class="col-sm-6">
                                <g:input type="text" class="form-control" name="institution" placeholder="Not set"
                                       value="${user.institution}"/>
                            </div>

                            <div class="col-sm-3">
                                <label class="switch switch-success">
                                    <input type="checkbox" id="isInstitutionLocked"
                                           class="lock" ${lockConf.isInstitutionLocked ? 'checked="checked"' : ''}/><i></i>
                                </label>
                            </div>
                        </div>

                        <div class="form-group">
                            <div class="col-sm-3">
                                <label for="specialization">Specialization</label>
                            </div>

                            <div class="col-sm-6">
                                <g:select from="${Specialization.list().title}" noSelection="['': 'Not set']"
                                          class="form-control" name="specialization" placeholder="Not set"
                                          value="${user.specialization?.title}"/>
                            </div>

                            <div class="col-sm-3">
                                <label class="switch switch-success">
                                    <input type="checkbox" id="isSpecializationLocked"
                                           class="lock" ${lockConf.isSpecializationLocked ? 'checked="checked"' : ''}/><i></i>
                                </label>
                            </div>
                        </div>

                        <div class="form-group">
                            <div class="col-sm-3">
                                <label for="position">Position</label>
                            </div>

                            <div class="col-sm-6">
                                <g:select from="${Position.list().name}" noSelection="['': 'Not set']"
                                          class="form-control" name="position" placeholder="Not set"
                                          value="${user.position?.name}"/>
                            </div>

                            <div class="col-sm-3">
                                %{--<p>Disable/Enable</p>--}%
                                <label class="switch switch-success">
                                    <input type="checkbox" id="isPositionLocked"
                                           class="lock" ${lockConf.isPositionLocked ? 'checked="checked"' : ''}/><i></i>
                                </label>
                            </div>
                        </div>

                        <div class="form-group">
                            <div class="col-sm-3">
                                <label for="department">Department</label>
                            </div>

                            <div class="col-sm-6">
                                <g:select from="${Department.list().title}" noSelection="['': 'Not set']"
                                          class="form-control" name="department" placeholder="Not set"
                                          value="${user.department?.title}"/>
                            </div>

                            <div class="col-sm-3">
                                %{--<p>Disable/Enable</p>--}%
                                <label class="switch switch-success">
                                    <input type="checkbox" id="isDepartmentLocked"
                                           class="lock" ${lockConf.isDepartmentLocked ? 'checked="checked"' : ''}/><i></i>
                                </label>
                            </div>
                        </div>

                        <button type="submit"
                                class="btn btn-default btn-block btn-lg"
                                data-ng-disabled="!canSubmit()">Update</button>

                    </div>
                </g:form>
            </div>
        </div>
    </div>
</g:applyLayout>