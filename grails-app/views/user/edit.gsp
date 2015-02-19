<%@ page import="csst15.lists.Department; csst15.lists.Position; csst15.lists.Specialization" %>
<g:applyLayout name="main">
    <div class="page-signin">

        <div class="signin-header">
            <section class="logo text-center">
                <a href="#/">Edit Profile</a>
            </section>
        </div>

        <div class="signin-body">
            <div class="container">
                <div class="form-container">

                    <form action='${createLink(controller: 'user', action: 'update')}' method='POST' id='loginForm'
                          class='cssform form-horizontal'
                          autocomplete='off'>
                        <fieldset>
                            <div class="form-group">
                                <span class="glyphicon glyphicon-envelope"></span>
                                <input type="text" name='username' id='username'
                                       class="form-control input-lg input-round text-center"
                                       placeholder="E-mail" value="${user.email}"/>
                            </div>

                            <div class="form-group">
                                <span class="glyphicon glyphicon-user"></span>
                                <input type="text" name='firstName' id='firstName'
                                       class="form-control input-lg input-round text-center"
                                       placeholder="First Name" value="${user.firstName}"/>
                            </div>

                            <div class="form-group">
                                <span class="glyphicon glyphicon-user"></span>
                                <input type="text" name='lastName' id='lastName'
                                       class="form-control input-lg input-round text-center"
                                       placeholder="Last Name" value="${user.lastName}"/>
                            </div>

                            <div class="form-group">
                                <span class="glyphicon glyphicon-flag"></span>
                                <input type="text" name='institution' id='institution'
                                       class="form-control input-lg input-round text-center"
                                       placeholder="Institution" value="${user.institution}"/>
                            </div>

                            <div class="form-group">
                                <span class="glyphicon glyphicon-flag"></span>
                                <g:select from="${Specialization.list().title}" noSelection="['': '']"
                                          type="text" name='specialization' id='specialization'
                                          class="form-control input-lg input-round text-center"
                                          placeholder="Specialization" value="${user.specialization}"/>
                            </div>

                            <div class="form-group">
                                <span class="glyphicon glyphicon-flag"></span>
                                <g:select from="${Position.list().name}" noSelection="['': '']"
                                          type="text" name='position' id='position'
                                          class="form-control input-lg input-round text-center"
                                          placeholder="Position" value="${user.position}"/>
                            </div>

                            <div class="form-group">
                                <span class="glyphicon glyphicon-flag"></span>
                                <g:select from="${Department.list().title}" noSelection="['': '']"
                                          type="text" name='department' id='department'
                                          class="form-control input-lg input-round text-center"
                                          placeholder="Department" value="${user.department}"/>
                            </div>


                            <div class="form-group">
                                <input type='submit' id="submit"
                                       class="btn btn-primary btn-lg btn-round btn-block text-center"
                                       value='Update'/>
                            </div>
                        </fieldset>
                    </form>
                </div>
            </div>
        </div>

    </div>
</g:applyLayout>