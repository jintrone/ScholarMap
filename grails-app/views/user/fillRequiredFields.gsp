<%@ page import="csst15.lists.Specialization; csst15.lists.Position; csst15.lists.Department" %>

<g:applyLayout name="main">
    <g:hiddenField name="isRequired"/>

    <div class="modal fade" id="fillRequiredModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
         aria-hidden="true">
        <div class="modal-dialog">
            <g:form name="updateForm" method="post" controller='user' action='updateRequiredFields'>
                <g:hiddenField name="userId" value="${user.id}"/>
                <div class="modal-content">
                    <div class="modal-header">
                        <h4 class="modal-title" id="exampleModalLabel">Fill required fields</h4>
                    </div>

                    <div class="modal-body">
                        <g:if test="${!user.firstName && mandatoryFields.contains("firstName")}">
                            <div class="form-group">
                                <label for="firstName">First name</label>
                                <input type="text" class="form-control" name="firstName" id="firstName"
                                       placeholder="First Name">
                            </div>
                        </g:if>
                        <g:else>
                            <g:hiddenField name="firstName" value="${user.firstName}"/>
                        </g:else>

                        <g:if test="${!user.lastName && mandatoryFields.contains("lastName")}">
                            <div class="form-group">
                                <label for="lastName">Last name</label>
                                <input type="text" class="form-control" name="lastName" id="lastName"
                                       placeholder="Last Name">
                            </div>
                        </g:if>
                        <g:else>
                            <g:hiddenField name="lastName" value="${user.lastName}"/>
                        </g:else>

                        <g:if test="${!user.degreeYear && mandatoryFields.contains("degreeYear")}">
                            <div class="form-group">
                                <label for="lastName">Degree Year</label>
                                <input type="text" class="form-control" name="degreeYear" id="degreeYear"
                                       placeholder="Degree Year">
                            </div>
                        </g:if>
                        <g:else>
                            <g:hiddenField name="degreeYear" value="${user.degreeYear}"/>
                        </g:else>

                        <g:if test="${!user.institution && mandatoryFields.contains("institution")}">
                            <div class="form-group">
                                <label for="lastName">Institution</label>
                                <input type="text" class="form-control" name="institution" id="institution"
                                       placeholder="Institution">
                            </div>
                        </g:if>
                        <g:else>
                            <g:hiddenField name="institution" value="${user.institution}"/>
                        </g:else>

                        <g:if test="${!user.specialization && mandatoryFields.contains("specialization")}">
                            <div class="form-group">
                                <label for="lastName">Specialization</label>
                                <g:select from="${Specialization.list().title}" noSelection="['': '']"
                                          class="form-control" name="specialization" placeholder="Specialization"
                                          value="${user.specialization?.title}"/>
                            </div>
                        </g:if>
                        <g:else>
                            <g:hiddenField name="specialization" value="${user.specialization?.title}"/>
                        </g:else>

                        <g:if test="${!user.position && mandatoryFields.contains("position")}">
                            <div class="form-group">
                                <label for="lastName">Position</label>
                                <g:select from="${Position.list().name}" noSelection="['': '']"
                                          class="form-control" name="position" placeholder="Position"
                                          value="${user.position?.name}"/>
                            </div>
                        </g:if>
                        <g:else>
                            <g:hiddenField name="position" value="${user.position?.name}"/>
                        </g:else>

                        <g:if test="${!user.department && mandatoryFields.contains("department")}">
                            <div class="form-group">
                                <label for="lastName">Department</label>
                                <g:select from="${Department.list().title}" noSelection="['': '']"
                                          class="form-control" name="department" placeholder="Department"
                                          value="${user.department?.title}"/>
                            </div>
                        </g:if>
                        <g:else>
                            <g:hiddenField name="department" value="${user.department?.title}"/>
                        </g:else>

                    </div>

                    <div class="modal-footer">
                        <button type="submit" class="btn btn-primary">Continue</button>
                    </div>
                </div>
            </g:form>
        </div>
    </div>

    <!-- Main jumbotron for a primary marketing message or call to action -->
    <div class="jumbotron">
        <div class="container">
            <p>Welcome, CSST 2014! This the second iteration of ScholarMap, a prototype of a tool intended
            to help us build bridges and understand our academic community a little better.
            </p>

            <p>The success of ScholarMap depends upon <b>you</b>, fellow CSSTers!  So please, register, login, and fill out your
            profiles over the course of the week. Your efforts will help reveal some of the hidden structure in our community.
            More importantly, it will show you how you fit in.
            </p>
        </div>
    </div>

    <div class="container">
        <!-- Example row of columns -->
        <div class="row">
            <div class="col-sm-3">
                <h2>Areas</h2>
                <g:render template="/common/shortEntityList" model="[type: 'field']"/>
            </div>

            <div class="col-sm-3">
                <h2>Theories</h2>
                <g:render template="/common/shortEntityList" model="[type: 'theory']"/>
            </div>

            <div class="col-sm-3">
                <h2>Methods</h2>
                <g:render template="/common/shortEntityList" model="[type: 'method']"/>
            </div>

            <div class="col-sm-3">
                <h2>Venues</h2>
                <g:render template="/common/shortEntityList" model="[type: 'venue']"/>
            </div>
        </div>

        <hr>

    </div> <!-- /container -->
</g:applyLayout>