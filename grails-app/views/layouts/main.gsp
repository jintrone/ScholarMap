<%@ page import="csst15.security.User" %>
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
                    <li><g:link href="#/home">Home</g:link></li>
                    <sec:ifLoggedIn>
                        <li class="dropdown text-normal nav-profile">
                            <a href="javascript:void(0);" class="dropdown-toggle" data-toggle="dropdown">
                                <span class="hidden-xs">
                                    <span data-i18n="Lisa Doe">Welcome  <csst:userFullName/></span>
                                </span>
                            </a>
                            <ul class="dropdown-menu with-arrow">
                                <li>
                                    <g:link controller="user" action="profile"
                                            id="${sec.loggedInUserInfo(field: 'id')}">
                                        <i class="fa fa-user"></i>
                                        <span data-i18n="My Profile">My Profile</span>
                                    </g:link>
                                </li>
                                <li>
                                    <form name="logout" method="POST" action="${createLink(controller: 'logout')}">
                                        <input type="hidden" name="" value="">
                                        <a class="fa fa-sign-out" href="javascript:document.logout.submit()">Logout</a>
                                    </form>
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
</div>


<footer class="footer text-center">&copy; Copyright 2015 ScholarMap</footer>

<script type="text/javascript" src="${resource(dir: 'js/jquery', file: 'jquery.min.js')}"></script>
<script type="text/javascript" src="${resource(dir: 'js/bootstrap', file: 'bootstrap.min.js')}"></script>
</body>
</html>

