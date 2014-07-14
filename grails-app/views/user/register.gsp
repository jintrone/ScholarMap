<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>CSST14</title>
    <asset:stylesheet src="register.css"/>
</head>

<body>
    <div class="container">
        <g:if test="flash.message">
            <div class="text-warning">
                ${flash.message}
            </div>
        </g:if>
        <g:form action="doRegister" class="form-register" role="form">
            <h2 class="form-register-heading">Please register</h2>
            <input class="form-control" name="email" type="email" autofocus="" required="true" placeholder="Email address" value="${email}">
            <input class="form-control" name="firstName" type="firstName" autofocus="" required="" placeholder="First name"/>
            <input class="form-control" name="lastName" type="firstName" autofocus="" required="" placeholder="Last name"/>
            <input class="form-control" name="institution" type="institution" autofocus="" required="" placeholder="Institution"/>
            <input class="form-control" name="position" type="position" autofocus="" required="" placeholder="Position"/>
            <br/>
            <input class="form-control" name="password1" type="password" autofocus="" required="true" placeholder="Password"/>
            <input class="form-control" name="password2" type="password" autofocus="" required="true" placeholder="Re-type password"/>

            <g:submitButton class="btn btn-lg btn-primary btn-block" name="Submit">Submit</g:submitButton>

        </g:form>

    </div>


</body>
</html>