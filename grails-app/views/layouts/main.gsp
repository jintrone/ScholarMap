<%@ page import="csst15.constants.Roles; csst15.security.User" %>
<!DOCTYPE html>
<!--[if lt IE 7 ]> <html lang="en" class="no-js ie6"> <![endif]-->
<!--[if IE 7 ]>    <html lang="en" class="no-js ie7"> <![endif]-->
<!--[if IE 8 ]>    <html lang="en" class="no-js ie8"> <![endif]-->
<!--[if IE 9 ]>    <html lang="en" class="no-js ie9"> <![endif]-->
<!--[if (gt IE 9)|!(IE)]><!--> <html lang="en" class="no-js"><!--<![endif]-->
<head>
    <title>Welcome to Scholarmap</title>
    <link href="http://fonts.googleapis.com/css?family=Lato:300,400,700,300italic,400italic" rel="stylesheet"/>
    <link href="${resource(dir: 'css/font-awesome/css', file: 'font-awesome.min.css')}" rel="stylesheet"/>
    <link href="${resource(dir: '/css/bootstrap', file: 'bootstrap.css')}" rel="stylesheet"/>
    <link href="${resource(dir: '/css', file: 'main.css')}" rel="stylesheet"/>
    <link rel="shortcut icon" href="${resource(dir: 'images', file: 'favicon.ico')}">
    <r:require module="application"/>
    <r:layoutResources/>
</head>

<body>

<header class="header navbar navbar-default navbar-fixed-top">
    <div class="container">
        <div class="row">
            <div class="navbar-header">
                <button type="button" data-toggle="collapse" data-target="#navbar-collapse-1"
                        class="navbar-toggle"><span class="icon-bar"></span><span class="icon-bar"></span><span
                        class="icon-bar"></span></button>
                <section class="logo text-center">
                    <g:link controller="home" class="navbar-brand">ScholarMap</g:link>
                </section>
            </div>

            <div id="navbar-collapse-1" class="collapse navbar-collapse">
                <ul class="nav navbar-nav navbar-right">
                    <sec:ifAllGranted roles="${Roles.ADMIN.name}">
                        <li><g:link controller="admin" action="board">Board</g:link></li>
                    </sec:ifAllGranted>
                    <sec:ifNotGranted roles="${Roles.ADMIN.name}">
                        <li><g:link controller="home" action="index">Home</g:link></li>
                        <li><g:link controller="home" action="list">Users</g:link></li>
                        <li><g:link controller="home" action="about">About</g:link></li>
                    </sec:ifNotGranted>
                    <sec:ifLoggedIn>
                        <li class="dropdown text-normal nav-profile">
                            <a href="javascript:void(0);" class="dropdown-toggle" data-toggle="dropdown">
                                <span class="hidden-xs">
                                    <span data-i18n="Lisa Doe">Welcome  <csst:userFullName/></span>
                                </span>
                            </a>
                            <ul class="dropdown-menu with-arrow">
                                <li>
                                    <g:link controller="user" action="profile" params="[username: csst.username()]">
                                        <i class="fa fa-user"></i><span data-i18n="My Profile">My Profile</span>
                                    </g:link>
                                </li>
                                <li>
                                    <g:link controller="logout">
                                        <i class="fa fa-sign-out"></i><span>Logout</span>
                                    </g:link>
                                </li>
                            </ul>
                        </li>
                    </sec:ifLoggedIn>
                    <sec:ifNotLoggedIn>
                        <li>
                            <g:link controller="login" action="auth">Sign In</g:link>
                        </li>
                    </sec:ifNotLoggedIn>
                </ul>
            </div>
        </div>
    </div>
</header>

<div class="content" style="margin-top: 150px">
    <g:layoutBody/>
    <g:render template="/utils/hiddenLinks"/>
    <r:layoutResources/>
</div>


<footer class="footer text-center" style="color: beige">&copy; Copyright 2015 ScholarMap</footer>
</body>
</html>

