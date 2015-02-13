<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
    <div class="container-fluid">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <g:link class="navbar-brand" uri="/"><g:layoutTitle default="ScholarMap 0.2"/></g:link>
        </div>


        <div class="navbar-collapse collapse">
            <ul class="nav navbar-nav">
                <li class="dropdown <g:if test="${webRequest.controllerName in ["entity","reference"]  && webRequest.actionName != "update"}">active</g:if>">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">Explore<span class="caret"></span>
                    </a>
                    <ul class="dropdown-menu" role="menu">
                        <li ><g:link controller="entity" action="summary"
                                    params="[type: 'field']">Areas</g:link></li>
                        <li><g:link controller="entity" action="summary"
                                    params="[type: 'theory']">Theories</g:link></li>
                        <li><g:link controller="entity" action="summary"
                                    params="[type: 'method']">Methods</g:link></li>
                        <li><g:link controller="entity" action="summary"
                                    params="[type: 'venue']">Venues</g:link></li>
                        <li><g:link controller="reference" action="index"
                                    params="[type: 'venue']">References</g:link></li>
                    </ul>
                </li>

                <li <g:if test="${request.requestURI.endsWith('/about')}">class="active"</g:if>
                    role="presentation"><g:link uri="/about">About</g:link>
                </li>


                <g:if test="${org.apache.shiro.SecurityUtils.subject.authenticated}">
                    <li <g:if test="${request.requestURI.endsWith("/profile.dispatch") || webRequest.actionName=="update"}">class="active"</g:if>
                        role="presentation"><g:link controller="user"
                                                    action="profile">Profile</g:link>
                    </li>
                </g:if>
             </ul>


        <shiro:isNotLoggedIn>
            <g:form class="navbar-form navbar-right" role="form" controller="auth" action="signIn">
                <div class="form-group">
                    <input type="text" placeholder="Email" name="email" class="form-control">
                </div>

                <div class="form-group">
                    <input type="password" placeholder="Password" name="password" class="form-control">
                </div>
                <button type="submit" class="btn btn-success">Log in</button>
                <g:link value="Register" controller="User" action="register"
                        class="btn btn-success">Register</g:link>
            </g:form>

        </shiro:isNotLoggedIn>
        <shiro:isLoggedIn>
            <ul class="nav navbar-nav navbar-right">
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">Hi, <shiro:principal/>
                        <span class="caret"></span>
                    </a>


                    <ul class="dropdown-menu" role="menu" aria-labelledby="dropdownMenu2">
                        <li role="presentation"><g:link role="menuitem" tabindex="-1" controller="auth"
                                                        action="signOut">Log out</g:link></li>
                    </ul>
                </li>
            </ul>

        </shiro:isLoggedIn>
    </div>
</div>



</nav>
