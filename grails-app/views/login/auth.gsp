<g:applyLayout name="main">
    <div class="page-signin">

        <div class="signin-header">
            <section class="logo text-center">
                <a href="#/">Sign in</a>
            </section>
        </div>

        <div class="signin-body">
            <div class="container">
                <div class="form-container">

                    <form action='${postUrl}' method='POST' id='loginForm' class='cssform form-horizontal'
                          autocomplete='off'>
                        <fieldset>
                            <div class="form-group">
                                <span class="glyphicon glyphicon-envelope"></span>
                                <input type="text" name='j_username' id='username'
                                       class="form-control input-lg input-round text-center"
                                       placeholder="Username">
                            </div>

                            <div class="form-group">
                                <span class="glyphicon glyphicon-lock"></span>
                                <input type="password" name='j_password' id='password'
                                       class="form-control input-lg input-round text-center"
                                       placeholder="Password">
                            </div>

                            <div class="form-group">
                                <input type='submit' id="submit"
                                       class="btn btn-primary btn-lg btn-round btn-block text-center"
                                       value='${message(code: "springSecurity.login.button")}'/>
                                %{--<a href="#/" class="btn btn-primary btn-lg btn-round btn-block text-center">Log in</a>--}%
                            </div>
                        </fieldset>
                    </form>

                    <section>
                        <p class="text-center"><a href="#/pages/forgot-password">Forgot your password?</a></p>

                        <p class="text-center text-muted text-small">Don't have an account yet?
                            <g:link controller="user" action="signup">Sign up</g:link></p>
                    </section>

                </div>
            </div>
        </div>

    </div>
</g:applyLayout>