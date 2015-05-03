<%@ page import="csst15.constants.EntityType" %>
<g:applyLayout name="main">
    <g:set var="isOwner" value="${sec.loggedInUserInfo(field: 'id') == user.id.toString()}"/>
    <div class="row">
        <div class="col-md-1"></div>

        <div class="col-md-10">
            <div class="panel panel-default">
                <div class="panel-heading" style="padding: 10px; height: 40px">
                    <div class="col-md-11">
                        <strong><span class="glyphicon glyphicon-th"></span>
                            <g:if test="${hasCurrentUser || (user.visibilityConf.isFirstNameVisible && user.visibilityConf.isLastNameVisible)}">
                                ${user.firstName} ${user.lastName}
                            </g:if>
                        </strong>
                    </div>
                </div>

                <div class="panel-body">
                    <div class="col-md-4">
                        <div class="panel panel-profile">
                            <div class="panel-heading text-center bg-info">
                                <g:if test="${user.photo}">
                                    <img class="img-circle img120_120"
                                         src="${createLink(controller: 'user', action: 'avatar_image', id: user.id)}"/>
                                </g:if>
                                <g:else>
                                    <g:img alt="" uri="/images/default-user.png" class="img-circle img120_120"/>
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

                    <div class="col-md-8">

                        <div class="panel panel-default">
                            <div class="panel-body">
                                <div class="media">
                                    <div class="media-body">
                                        <ul class="list-unstyled list-info">
                                            <g:if test="${hasCurrentUser || user.visibilityConf.isEmailVisible}">
                                                <li>
                                                    <span class="icon glyphicon"></span>
                                                    <label>Email</label>
                                                    ${user.email}
                                                </li>
                                            </g:if>
                                            <g:if test="${hasCurrentUser || user.visibilityConf.isDegreeYearVisible}">
                                                <li>
                                                    <span class="icon glyphicon"></span>
                                                    <label>Degree Year</label>
                                                    ${user.degreeYear}
                                                </li>
                                            </g:if>
                                            <g:if test="${hasCurrentUser || user.visibilityConf.isInstitutionVisible}">
                                                <li>
                                                    <span class="icon glyphicon"></span>
                                                    <label>Institution</label>
                                                    ${user.institution}
                                                </li>
                                            </g:if>
                                            <g:if test="${hasCurrentUser || user.visibilityConf.isSpecializationVisible}">
                                                <li>
                                                    <span class="icon glyphicon"></span>
                                                    <label>Specialization</label>
                                                    ${user.specialization?.title}
                                                </li>
                                            </g:if>
                                            <g:if test="${hasCurrentUser || user.visibilityConf.isDepartmentVisible}">
                                                <li>
                                                    <span class="icon glyphicon"></span>
                                                    <label>Department</label>
                                                    ${user.department?.title}
                                                </li>
                                            </g:if>
                                        </ul>
                                        <g:if test="${isOwner}">
                                            <g:link title="Edit Profile" controller="user" action="edit"
                                                    class="btn btn-success"
                                                    params="[username: user.username]">Edit Profile</g:link>
                                            <g:link title="Change Password" controller="user"
                                                    action="changePasswordPage"
                                                    class="btn btn-default"
                                                    params="[username: user.username]">Change Password</g:link>
                                        </g:if>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <div class="col-md-1"></div>
    </div>

    <div class="row">
        <div class="col-md-1"></div>

        <div class="col-md-10">
            <div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true">
                <div class="panel panel-default">
                    <div class="panel-heading" role="tab" id="headingOne">
                        <h4 class="panel-title">
                            <a data-toggle="collapse" data-parent="#accordion" href="#collapseOne" aria-expanded="true"
                               aria-controls="collapseOne">
                                Interest summary
                            </a>
                        </h4>
                    </div>

                    <div id="collapseOne" class="panel-collapse collapse in" role="tabpanel"
                         aria-labelledby="headingOne">
                        <div class="panel-body">
                            Anim pariatur cliche reprehenderit, enim eiusmod high life accusamus terry richardson ad squid. 3 wolf moon officia aute, non cupidatat skateboard dolor brunch. Food truck quinoa nesciunt laborum eiusmod. Brunch 3 wolf moon tempor, sunt aliqua put a bird on it squid single-origin coffee nulla assumenda shoreditch et. Nihil anim keffiyeh helvetica, craft beer labore wes anderson cred nesciunt sapiente ea proident. Ad vegan excepteur butcher vice lomo. Leggings occaecat craft beer farm-to-table, raw denim aesthetic synth nesciunt you probably haven't heard of them accusamus labore sustainable VHS.
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <div class="col-md-1"></div>
    </div>

    <div class="row">
        <div class="col-md-1"></div>

        <div class="modal fade" id="deleteInterestModal" style="display: none" tabindex="-1" role="dialog"
             aria-labelledby="myModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <g:form controller="interests" action="deleteInterest" method="post">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal"
                                    aria-label="Close"><span aria-hidden="true">&times;</span>
                            </button>
                            <h4 class="modal-title" id="myModalLabel">Delete Interest</h4>
                        </div>

                        <div class="modal-body">

                            <p>Are you sure you want to delete this interest ?</p>
                            <g:hiddenField name="entityId"/>

                        </div>

                        <div class="modal-footer">
                            <button type="button" class="btn btn-default"
                                    data-dismiss="modal">Cancel</button>
                            <button type="submit" class="btn btn-primary">Delete</button>
                        </div>
                    </div>
                </g:form>
            </div>
        </div>

        <div class="col-md-10">
            <div class="panel panel-default">
                <div class="panel-heading" style="padding: 20px; height: 80px">

                    <div class="col-md-11">
                        <strong><span class="glyphicon glyphicon-th"></span> Interests</strong>
                    </div>

                    <div class="col-md-1">
                        <sec:ifLoggedIn>
                            <a href="javascript:void(0);" id="addNewEntity" class="btn btn-success"
                               title="Edit">Add New</a>
                        </sec:ifLoggedIn>
                    </div>
                </div>

                <div class="panel-body">
                    <div class="media">
                        <div class="media-body" id="interestRecords">
                            <g:render template="interestRecords"
                                      model="[entities: entities, currentUser: user]"/>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <div class="col-md-1"></div>
    </div>

    <div class="modal fade" id="addInterestModal" style="display: none">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal"
                            aria-label="Close"><span aria-hidden="true">&times;</span>
                    </button>
                    <h4 class="modal-title" id="addInterestModalLabel">Add Interest</h4>
                </div>

                <div class="modal-body">
                    <div class="form-group">
                        <label for="type">Type</label>
                        <g:select from="${EntityType.values().name}" noSelection="['': 'Not set']"
                                  class="form-control" name="type" placeholder="Type"/>
                    </div>

                    <div class="form-group ui-front">
                        <label for="name">Name</label>
                        <csst:autocomplete name="name" action="loadInterests" class="form-control" id="name"/>
                    </div>

                    <div class="form-group">
                        <label for="description">Description</label>
                        <textarea maxlength="5000" name="description" rows="10" id="description"
                                  class="form-control"
                                  placeholder="Not set"></textarea>
                    </div>

                </div>

                <div class="modal-footer">
                    <button type="button" class="btn btn-default"
                            data-dismiss="modal">Cancel</button>
                    <a href="javascript:void(0);" id="addInterestBtn" class="btn btn-primary">Add</a>
                </div>
            </div>
        </div>
    </div>
</g:applyLayout>