<g:applyLayout name="main">
    <div class="page-forgot">

        <div class="signin-header">
            <section class="logo text-center">
                <a href="#/">ScholarMap</a>
            </section>
        </div>

        <div class="info text-center">
            <p class="text-small">Enter your new password.</p>
        </div>

        <div class="form-container text-center">

            <form class="form-horizontal" name="forgotPasswordForm"
                  action="${createLink(controller: 'auth', action: 'resetpassword')}">
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
                           placeholder="Confirm Password">
                </div>
                <g:hiddenField name="passwordResetToken" value="${passwordResetToken}"/>

                <div class="form-group">
                    <input type="submit" class="btn btn-lg btn-block btn-primary btn-round" value="Submit"/>
                </div>
            </form>

        </div>
    </div>
</g:applyLayout>