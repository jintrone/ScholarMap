<%@ page import="csst15.lists.Specialization; csst15.lists.Position; csst15.lists.Department" %>
<g:hiddenField name="userId" value="${user.id}"/>
<div class="panel-body" data-ng-controller="signupCtrl" id="editPanel">

    <div class="form-group">
        <div class="col-sm-3">
            <label for="">User Name</label>
        </div>

        <div class="col-sm-6">
            <input type="text" class="form-control" name="username" placeholder="Not set"
                   value="${user.username}"/>
        </div>

        <div class="col-sm-3">
            <g:if test="${!lockConf.isUsernameLocked}">
                <label class="switch switch-success">
                    <input type="checkbox" id="isUsernameVisible"
                           class="permission" ${visConf?.isUsernameVisible ? 'checked="checked"' : ''}/><i></i>
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
                   placeholder="Not set"/>
        </div>

        <div class="col-sm-3">
            <g:if test="${!lockConf.isEmailLocked}">
                <label class="switch switch-success">
                    <input type="checkbox" id="isEmailVisible"
                           class="permission" ${visConf?.isEmailVisible ? 'checked="checked"' : ''}/><i></i>
                </label>
            </g:if>
        </div>
    </div>

    <div class="form-group">
        <div class="col-sm-3">
            <label for="">First Name</label>
        </div>

        <div class="col-sm-6">
            <input type="text" class="form-control" name="firstName" placeholder="Not set"
                   value="${user.firstName}"/>
        </div>

        <div class="col-sm-3">
            <g:if test="${!lockConf.isFirstNameLocked}">
                <label class="switch switch-success">
                    <input type="checkbox" id="isFirstNameVisible"
                           class="permission" ${visConf?.isFirstNameVisible ? 'checked="checked"' : ''}/><i></i>
                </label>
            </g:if>
        </div>
    </div>

    <div class="form-group">
        <div class="col-sm-3">
            <label for="">Last Name</label>
        </div>

        <div class="col-sm-6">
            <input type="text" class="form-control" name="lastName" placeholder="Not set"
                   value="${user.lastName}"/>
        </div>

        <div class="col-sm-3">
            <g:if test="${!lockConf.isLastNameLocked}">
                <label class="switch switch-success">
                    <input type="checkbox" id="isLastNameVisible"
                           class="permission" ${visConf?.isLastNameVisible ? 'checked="checked"' : ''}/><i></i>
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
                   placeholder="Not set"
                   value="${user.degreeYear}"/>
        </div>

        <div class="col-sm-3">
            <g:if test="${!lockConf.isDegreeYearLocked}">
                <label class="switch switch-success">
                    <input type="checkbox" id="isDegreeYearVisible"
                           class="permission" ${visConf?.isDegreeYearVisible ? 'checked="checked"' : ''}/><i></i>
                </label>
            </g:if>
        </div>
    </div>

    <div class="form-group">
        <div class="col-sm-3">
            <label for="">Institution</label>
        </div>

        <div class="col-sm-6">
            <input type="text" class="form-control" name="institution" placeholder="Not set"
                   value="${user.institution}"/>
        </div>

        <div class="col-sm-3">
            <g:if test="${!lockConf.isInstitutionLocked}">
                <label class="switch switch-success">
                    <input type="checkbox" id="isInstitutionVisible"
                           class="permission" ${visConf?.isInstitutionVisible ? 'checked="checked"' : ''}/><i></i>
                </label>
            </g:if>
        </div>
    </div>

    <div class="form-group">
        <div class="col-sm-3">
            <label for="">Specialization</label>
        </div>

        <div class="col-sm-6">
            <g:select from="${Specialization.list().title}" noSelection="['': 'Not set']"
                      class="form-control" name="specialization" placeholder="Not set"
                      value="${user.specialization?.title}"/>
        </div>

        <div class="col-sm-3">
            <g:if test="${!lockConf.isSpecializationLocked}">
                <label class="switch switch-success">
                    <input type="checkbox" id="isSpecializationVisible"
                           class="permission" ${visConf?.isSpecializationVisible ? 'checked="checked"' : ''}/><i></i>
                </label>
            </g:if>
        </div>
    </div>

    <div class="form-group">
        <div class="col-sm-3">
            <label for="">Position</label>
        </div>

        <div class="col-sm-6">
            <g:select from="${Position.list().name}" noSelection="['': 'Not set']"
                      class="form-control" name="position" placeholder="Not set"
                      value="${user.position?.name}"/>
        </div>

        <div class="col-sm-3">
            <g:if test="${!lockConf.isPositionLocked}">
                <label class="switch switch-success">
                    <input type="checkbox" id="isPositionVisible"
                           class="permission" ${visConf?.isPositionVisible ? 'checked="checked"' : ''}/><i></i>
                </label>
            </g:if>
        </div>
    </div>

    <div class="form-group">
        <div class="col-sm-3">
            <label for="">Department</label>
        </div>

        <div class="col-sm-6">
            <g:select from="${Department.list().title}" noSelection="['': 'Not set']"
                      class="form-control" name="department" placeholder="Not set"
                      value="${user.department?.title}"/>
        </div>

        <div class="col-sm-3">
            <g:if test="${!lockConf.isDepartmentLocked}">
                <label class="switch switch-success">
                    <input type="checkbox" id="isDepartmentVisible"
                           class="permission" ${visConf?.isDepartmentVisible ? 'checked="checked"' : ''}/><i></i>
                </label>
            </g:if>
        </div>
    </div>

    <div class="form-group">
        <div class="col-sm-3">
            <label for="">Photo</label>
        </div>

        <div class="col-sm-6" id="photoPanel">
            <div class="simplePanel">
                <g:if test="${user.photo}">
                    <img class="img-circle img80_80"
                         src="${createLink(controller: 'user', action: 'avatar_image', id: user.id)}"/>
                    <a href="javascript:void(0);" id="changePhoto">Change photo</a>
                </g:if>
                <g:else>
                    <input type="file" name="photo" title="Choose File"/>

                    <div class="space"></div>
                </g:else>
            </div>
        </div>

        <div class="col-sm-3">
        </div>
    </div>

    <button type="submit" class="btn btn-da btn-block btn-lg">Update</button>

</div>