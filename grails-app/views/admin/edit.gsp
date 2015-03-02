<%@ page import="csst15.lists.Position; csst15.lists.Department; csst15.lists.Specialization" %>
<g:applyLayout name="main">
    <div class="panel panel-default" xmlns="http://www.w3.org/1999/html">
        <div class="panel-body">
            <div class="col-md-6">
                <div class="panel-heading">
                    <strong><span class="glyphicon glyphicon-th"></span> Edit Profile</strong>
                </div>

                <form name="admin_edit_profile" id="admin_edit_profile" class="form-horizontal form-validation">
                    <g:hiddenField name="userId" value="${user.id}"/>
                    <div class="panel-body">

                        <div class="form-group">
                            <div class="col-sm-3">
                                <label for="">User Name</label>
                            </div>

                            <div class="col-sm-6">
                                <input type="text" class="form-control" name="username" placeholder="Username"
                                       value="${user.username}"/>
                            </div>

                            <div class="col-sm-3">
                                %{--<p>Disable/Enable</p>--}%
                                <label class="switch switch-success">
                                    <input type="checkbox" id="isUsernameLocked"
                                           class="lock" ${lockConf.isUsernameLocked ? 'checked="checked"' : ''}/><i></i>
                                </label>
                            </div>
                        </div>

                        <div class="form-group">
                            <div class="col-sm-3">
                                <label for="">Email</label>
                            </div>

                            <div class="col-sm-6">
                                <input type="email" class="form-control" name="email" value="${user.email}"
                                       placeholder="E-mail"/>
                            </div>

                            <div class="col-sm-3">
                                %{--<p>Disable/Enable</p>--}%
                                <label class="switch switch-success">
                                    <input type="checkbox" id="isEmailLocked"
                                           class="lock" ${lockConf.isEmailLocked ? 'checked="checked"' : ''}/><i></i>
                                </label>
                            </div>
                        </div>

                        <div class="form-group">
                            <div class="col-sm-3">
                                <label for="">First Name</label>
                            </div>

                            <div class="col-sm-6">
                                <input type="text" class="form-control" name="firstName" placeholder="First Name"
                                       value="${user.firstName}"/>
                            </div>

                            <div class="col-sm-3">
                                %{--<p>Disable/Enable</p>--}%
                                <label class="switch switch-success">
                                    <input type="checkbox" id="isFirstNameLocked"
                                           class="lock" ${lockConf.isFirstNameLocked ? 'checked="checked"' : ''}/><i></i>
                                </label>
                            </div>
                        </div>

                        <div class="form-group">
                            <div class="col-sm-3">
                                <label for="">Last Name</label>
                            </div>

                            <div class="col-sm-6">
                                <input type="text" class="form-control" name="lastName" placeholder="Last Name"
                                       value="${user.lastName}"/>
                            </div>

                            <div class="col-sm-3">
                                %{--<p>Disable/Enable</p>--}%
                                <label class="switch switch-success">
                                    <input type="checkbox" id="isLastNameLocked"
                                           class="lock" ${lockConf.isLastNameLocked ? 'checked="checked"' : ''}/><i></i>
                                </label>
                            </div>
                        </div>

                        <div class="form-group">
                            <div class="col-sm-3">
                                <label for="">Degree Year</label>
                            </div>

                            <div class="col-sm-6">
                                <input type="text" class="form-control" name="degreeYear"
                                       placeholder="Degree year: min value 1900"
                                       value="${user.degreeYear}"/>
                            </div>

                            <div class="col-sm-3">
                                %{--<p>Disable/Enable</p>--}%
                                <label class="switch switch-success">
                                    <input type="checkbox" id="isDegreeYearLocked"
                                           class="lock" ${lockConf.isDegreeYearLocked ? 'checked="checked"' : ''}/><i></i>
                                </label>
                            </div>
                        </div>

                        <div class="form-group">
                            <div class="col-sm-3">
                                <label for="">Institution</label>
                            </div>

                            <div class="col-sm-6">
                                <input type="text" class="form-control" name="institution" placeholder="Institution"
                                       value="${user.institution}"/>
                            </div>

                            <div class="col-sm-3">
                                %{--<p>Disable/Enable</p>--}%
                                <label class="switch switch-success">
                                    <input type="checkbox" id="isInstitutionLocked"
                                           class="lock" ${lockConf.isInstitutionLocked ? 'checked="checked"' : ''}/><i></i>
                                </label>
                            </div>
                        </div>

                        <div class="form-group">
                            <div class="col-sm-3">
                                <label for="">Specialization</label>
                            </div>

                            <div class="col-sm-6">
                                <g:select from="${Specialization.list().title}" noSelection="['': '']"
                                          class="form-control" name="specialization" placeholder="Specialization"
                                          value="${user.specialization?.title}"/>
                            </div>

                            <div class="col-sm-3">
                                %{--<p>Disable/Enable</p>--}%
                                <label class="switch switch-success">
                                    <input type="checkbox" id="isSpecializationLocked"
                                           class="lock" ${lockConf.isSpecializationLocked ? 'checked="checked"' : ''}/><i></i>
                                </label>
                            </div>
                        </div>

                        <div class="form-group">
                            <div class="col-sm-3">
                                <label for="">Position</label>
                            </div>

                            <div class="col-sm-6">
                                <g:select from="${Position.list().name}" noSelection="['': '']"
                                          class="form-control" name="position" placeholder="Position"
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
                                <label for="">Department</label>
                            </div>

                            <div class="col-sm-6">
                                <g:select from="${Department.list().title}" noSelection="['': '']"
                                          class="form-control" name="department" placeholder="Department"
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
                </form>
            </div>
        </div>
    </div>
</g:applyLayout>