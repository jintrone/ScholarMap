<%@ page import="org.springframework.validation.FieldError; csst15.lists.Position" %>
<g:applyLayout name="main">
    <div class="page-signup">

        <div class="signin-header">
            <section class="logo text-center">
                <a href="#/">ScholarMap</a>
            </section>
        </div>

        <div class="signup-body">
            <div class="container">
                <div class="form-container">

                    <section>
                        <g:hasErrors bean="${userInstance}">
                            <ul class="callout callout-danger errors">
                                <g:eachError bean="${userInstance}" var="error">
                                    <li><g:message error="${error}"/></li>
                                </g:eachError>
                            </ul>
                        </g:hasErrors>
                        <form action='${createLink(controller: 'auth', action: 'register')}' method='POST'
                              id='loginForm' class='form-horizontal text-center' autocomplete='off'>
                            <div class="form-group">
                                <span class="glyphicon glyphicon-user"></span>
                                <input type="text" name="username" value="${userInstance?.username}"
                                       class="form-control input-lg input-round text-center"
                                       placeholder="Username">
                            </div>

                            <div class="form-group">
                                <span class="glyphicon glyphicon-user"></span>
                                <input type="text" name="firstName" value="${userInstance?.firstName}"
                                       class="form-control input-lg input-round text-center"
                                       placeholder="First Name">
                            </div>

                            <div class="form-group">
                                <span class="glyphicon glyphicon-user"></span>
                                <input type="text" name="lastName" value="${userInstance?.lastName}"
                                       class="form-control input-lg input-round text-center"
                                       placeholder="Last Name">
                            </div>

                            <div class="form-group">
                                <span class="glyphicon glyphicon-envelope"></span>
                                <input type="email" name="email" value="${userInstance?.email}"
                                       class="form-control input-lg input-round text-center"
                                       placeholder="Email">
                            </div>

                            %{--<div class="form-group">--}%
                            %{--<span class="glyphicon glyphicon-pencil"></span>--}%
                            %{--<input type="text" name="institution" value="${userInstance?.institution}"--}%
                            %{--class="form-control input-lg input-round text-center"--}%
                            %{--placeholder="Institution">--}%
                            %{--</div>--}%

                            %{--<div class="form-group">--}%
                            %{--<span class="glyphicon glyphicon-pencil"></span>--}%
                            %{--<g:select from="${Position.list().name}" noSelection="['': '']" name="position"--}%
                            %{--class="form-control input-lg input-round text-center"--}%
                            %{--value="${userInstance?.position}"--}%
                            %{--placeholder="Position"/>--}%
                            %{--</div>--}%

                            <div class="form-group">
                                <span class="glyphicon glyphicon-lock"></span>
                                <input type="password" name="password"
                                       class="form-control input-lg input-round text-center"
                                       placeholder="Password">
                            </div>

                            <div class="form-group">
                                <span class="glyphicon glyphicon-lock"></span>
                                <input type="password" name="confirmPassword"
                                       class="form-control input-lg input-round text-center"
                                       placeholder="Confirm Password">
                            </div>

                            <div class="form-group">
                                <p class="text-muted text-small">By clicking on Sign up, you agree to <a
                                        href="javascript:;">terms & conditions</a> and <a
                                        href="javascript:;">privacy policy</a></p>

                                <div class="divider"></div>
                                <input type="submit" class="btn btn-primary btn-block btn-lg btn-round" value="Sign up">
                            </div>

                        </form>
                    </section>

                    <section>
                        <p class="text-center text-muted">Already have an account?
                        <g:link controller="login" action="auth">Log in now</g:link>
                        </p>
                    </section>

                </div>
            </div>
        </div>
    </div>
</g:applyLayout>