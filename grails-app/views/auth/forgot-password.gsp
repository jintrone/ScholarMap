<g:applyLayout name="main">
    <div class="page-forgot">

        <div class="signin-header">
            <section class="logo text-center">
                <a href="#/">ScholarMap</a>
            </section>
        </div>

        <div class="info text-center">
            <p class="text-small">Enter your email address that you used to register. We'll send you an email with your username and a link to reset your password.</p>
        </div>

        <div class="form-container text-center">

            <form class="form-horizontal" name="forgotPasswordForm"
                  action="${createLink(controller: 'auth', action: 'sendResetPasswordEmail')}">
                <div class="form-group">
                    <span class="glyphicon glyphicon-envelope"></span>
                    <input type="email" name="email"
                           class="form-control input-lg input-round text-center"
                           placeholder="Email">
                </div>

                <div class="form-group">
                    <input type="submit" class="btn btn-lg btn-block btn-primary btn-round" value="Reset"/>
                </div>
            </form>

        </div>
    </div>
</g:applyLayout>