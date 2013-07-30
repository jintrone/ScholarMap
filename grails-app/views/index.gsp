<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>Welcome to Grails</title>
    <style type="text/css" media="screen">
    #status {
        background-color: #eee;
        border: .2em solid #fff;
        margin: 2em 2em 1em;
        padding: 1em;
        width: 12em;
        float: left;
        -moz-box-shadow: 0px 0px 1.25em #ccc;
        -webkit-box-shadow: 0px 0px 1.25em #ccc;
        box-shadow: 0px 0px 1.25em #ccc;
        -moz-border-radius: 0.6em;
        -webkit-border-radius: 0.6em;
        border-radius: 0.6em;
    }

    .ie6 #status {
        display: inline; /* float double margin fix http://www.positioniseverything.net/explorer/doubled-margin.html */
    }

    #status ul {
        font-size: 0.9em;
        list-style-type: none;
        margin-bottom: 0.6em;
        padding: 0;
    }

    #status li {
        line-height: 1.3;
    }

    #status h1 {
        text-transform: uppercase;
        font-size: 1.1em;
        margin: 0 0 0.3em;
    }

    #page-body {
        margin: 2em 1em 1.25em 18em;
    }

    h2 {
        margin-top: 1em;
        margin-bottom: 0.3em;
        font-size: 1em;
    }

    p {
        line-height: 1.5;
        margin: 0.25em 0;
    }

    @media screen and (max-width: 480px) {

        #page-body h1 {
            margin-top: 0;
        }
    }
    </style>
</head>

<body>
<a href="#page-body" class="skip"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>

<div role="main">
    <h1>Welcome!</h1>

    <p>This application is intended to help us sociotechnical folks collectively build a map of our own research community.
    For the effort to be successful, we ask that you contribute (or vote for) as many sub-fields or theories that have been important
    in your research as you can. At a minimum, please do three.</p>
</br>
    <p>The first version of the map will be presented at the Digital Societies
    and Social Technology institute in July, 2013 at the University of Maryland.</p>

</div>

<div id="login">
    <g:if test="${flash.message}">
        <div class="message">
            ${flash.message}
        </div>
    </g:if>
    <g:hasErrors bean="${user}">
        <ul class="errors" role="alert">
            <g:eachError bean="${user}" var="error">
                <li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message
                        error="${error}"/></li>
            </g:eachError>
        </ul>
    </g:hasErrors>
    <g:form controller="user" action="handleLogin" method="post">
        <table id="login" class="layout form"><tr>
        <g:if test="${!session.user}">
            <tr>
                <td class="instructions"><span class='nameClear'><label for="login">
                    Name:
                </label>
                </span>

                </td>
                <td class="instructions">
                    <span class='nameClear'><label for="email">
                        Email (not shared!):
                    </label>
                    </span>

                </td>
                <td class="button-layout" rowspan="2">
                    <div id="go-button" class="buttons">
                        <span class="button">
                            <g:submitButton name="submit" value="Go!"/>
                        </span>
                    </div>
                </td>
            </tr>
            <tr>
                <td>
                    <g:textField size="20" name="name" value="${user?.name}"/>
                </td>
                <td>
                    <g:textField size="30" name="email" value="${user?.email}"/>
                </td>

            </tr>
        </g:if>
        <g:else>
            <td class="button-layout">
                <div id="go-button" class="buttons">
                    <span class="button">
                        <g:submitButton name="submit" value="Go!"/>
                    </span>
                </div>
            </td>
        </g:else>

        </tr></table>

    </g:form>

</div>
</body>


<content tag="sidebar">
    <g:render template="/common/contributors"/>

</content>

</html>
