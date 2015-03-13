<%@ page import="csst15.lists.Position; csst15.lists.Department; csst15.lists.Specialization" %>
<g:applyLayout name="main">
    <div class="panel panel-default" xmlns="http://www.w3.org/1999/html">
        <div class="panel-body">
            <div class="col-md-6">
                <div class="panel-heading">
                    <strong><span class="glyphicon glyphicon-th"></span> Edit Profile</strong>
                </div>
                <g:hasErrors bean="${userCommand}">
                    <ul class="callout callout-danger errors">
                        <g:eachError bean="${userCommand}" var="error">
                            <li><g:message error="${error}"/></li>
                        </g:eachError>
                    </ul>
                </g:hasErrors>
                <g:form url="${createLink(controller: 'user', action: 'update')}" id="${user.id}"
                        enctype="multipart/form-data" name="update" class="form-horizontal form-validation">
                    <g:render template="form" model="[user: user, visConf: visConf, lockConf: lockConf]"/>
                </g:form>
                <div id="photoPanelClone" class="simplePanel" style="display: none">
                    <input type="file" name="photo" title="Choose File"/>

                    <div class="space"></div>
                </div>
            </div>
        </div>
    </div>
</g:applyLayout>