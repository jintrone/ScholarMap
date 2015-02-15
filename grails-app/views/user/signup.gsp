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
                        <form action='${createLink(controller: 'user', action: 'register')}' method='POST'
                              id='loginForm' class='form-horizontal text-center' autocomplete='off'>
                            <div class="form-group">
                                <span class="glyphicon glyphicon-user"></span>
                                <input type="text" name="username"
                                       class="form-control input-lg input-round text-center"
                                       placeholder="Username">
                            </div>

                            <div class="form-group">
                                <span class="glyphicon glyphicon-envelope"></span>
                                <input type="email" name="email"
                                       class="form-control input-lg input-round text-center"
                                       placeholder="Email">
                            </div>

                            <div class="form-group">
                                <span class="glyphicon glyphicon-lock"></span>
                                <input type="password" name="password"
                                       class="form-control input-lg input-round text-center"
                                       placeholder="Password">
                            </div>

                            <div class="form-group">
                                <span class="glyphicon glyphicon-lock"></span>
                                <input type="password" name="rpassword"
                                       class="form-control input-lg input-round text-center"
                                       placeholder="Password">
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