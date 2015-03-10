<%@ page import="csst15.lists.Specialization; csst15.lists.Position; csst15.lists.Department" %>
<g:hiddenField name="userId" value="${user.id}"/>
<div class="panel-body" data-ng-controller="signupCtrl" id="editPanel">

    <div class="form-group">
        <div class="col-sm-3">
            <label for="">User Name</label>
        </div>

        <div class="col-sm-6">
            <input type="text" class="form-control" name="username" placeholder="Username"
                   value="${user.username}"/>
        </div>

        <div class="col-sm-3">
            <g:if test="${!lockConf.isUsernameLocked}">
                <label class="switch switch-success">
                    <input type="checkbox" id="isUsernameVisible"
                           class="permission" ${visConf.isUsernameVisible ? 'checked="checked"' : ''}/><i></i>
                </label>
            </g:if>
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
            <g:if test="${!lockConf.isEmailLocked}">
                <label class="switch switch-success">
                    <input type="checkbox" id="isEmailVisible"
                           class="permission" ${visConf.isEmailVisible ? 'checked="checked"' : ''}/><i></i>
                </label>
            </g:if>
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
            <g:if test="${!lockConf.isFirstNameLocked}">
                <label class="switch switch-success">
                    <input type="checkbox" id="isFirstNameVisible"
                           class="permission" ${visConf.isFirstNameVisible ? 'checked="checked"' : ''}/><i></i>
                </label>
            </g:if>
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
            <g:if test="${!lockConf.isLastNameLocked}">
                <label class="switch switch-success">
                    <input type="checkbox" id="isLastNameVisible"
                           class="permission" ${visConf.isLastNameVisible ? 'checked="checked"' : ''}/><i></i>
                </label>
            </g:if>
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
            <g:if test="${!lockConf.isDegreeYearLocked}">
                <label class="switch switch-success">
                    <input type="checkbox" id="isDegreeYearVisible"
                           class="permission" ${visConf.isDegreeYearVisible ? 'checked="checked"' : ''}/><i></i>
                </label>
            </g:if>
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
            <g:if test="${!lockConf.isInstitutionLocked}">
                <label class="switch switch-success">
                    <input type="checkbox" id="isInstitutionVisible"
                           class="permission" ${visConf.isInstitutionVisible ? 'checked="checked"' : ''}/><i></i>
                </label>
            </g:if>
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
            <g:if test="${!lockConf.isSpecializationLocked}">
                <label class="switch switch-success">
                    <input type="checkbox" id="isSpecializationVisible"
                           class="permission" ${visConf.isSpecializationVisible ? 'checked="checked"' : ''}/><i></i>
                </label>
            </g:if>
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
            <g:if test="${!lockConf.isPositionLocked}">
                <label class="switch switch-success">
                    <input type="checkbox" id="isPositionVisible"
                           class="permission" ${visConf.isPositionVisible ? 'checked="checked"' : ''}/><i></i>
                </label>
            </g:if>
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
            <g:if test="${!lockConf.isDepartmentLocked}">
                <label class="switch switch-success">
                    <input type="checkbox" id="isDepartmentVisible"
                           class="permission" ${visConf.isDepartmentVisible ? 'checked="checked"' : ''}/><i></i>
                </label>
            </g:if>
        </div>
    </div>

    <button type="submit" class="btn btn-default btn-block btn-lg">Update</button>

</div>