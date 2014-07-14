<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title></title>
    <meta name="layout" content="main"/>
    <asset:javascript src="jquery.form.min.js"/>
    <script type="application/javascript">
        $(document).ready(function () {


            $("form.entity-description").ajaxForm(function (data) {
                $("#entity-description-display").text(data)
                showDescription()
            });

            $("form.edit-name").ajaxForm(function (data) {
                $("#entity-name-display").text(data)
                showName()
            });
        });

        function editDescription() {
            $("div.entity-description").addClass("hidden");
            $("form.entity-description").removeClass("hidden")
        }

        function showDescription() {
            $("div.entity-description").removeClass("hidden");
            $("form.entity-description").addClass("hidden")
        }

        function editName() {
            $("div.entity-name").addClass("hidden");
            $("#entity-name-edit").removeClass("hidden")
        }

        function showName() {
            $("div.entity-name").removeClass("hidden");
            $("#entity-name-edit").addClass("hidden")
        }


    </script>
</head>

<body>
<div class="container">
    <div class="entity-name">
        <shiro:isLoggedIn>
            <button class="btn btn-sm btn-default pull-right" onclick="editName()">Edit!</button>
        </shiro:isLoggedIn>
        <h2>Detail for <span id="entity-name-display" class="text-info">${entity.name}</span></h2>
    </div>

    <div id="entity-name-edit" class="padme hidden">
        <g:form controller="entity" action="updateEntity" class="form-horizontal edit-name"
                role="form">
            <input type="hidden" name="entityId" value="${entity.id}"/>
            <div class="form-group">
                <div class="col-sm-10">
                    <input id="entity-name" type="text" name="name" class="col-sm-8 form-control"
                           value="${entity.name}"/>
                </div>

                <div class="col-sm-2">
                    <g:submitButton name="submit" class="btn btn-sm btn-primary form-control"
                                    >Edit!</g:submitButton>
                </div>

            </div>

        </g:form>
    </div>
    <hr/>

    <div class="entity-description well">
        <shiro:isLoggedIn>
            <button class="btn btn-sm btn-default pull-right" onclick="editDescription()">Edit!</button>
        </shiro:isLoggedIn>
        <p id="entity-description-display">
            ${entity.description}
        </p>
    </div>
    <g:form class="entity-description hidden ajaxable-form" controller="entity"
            action="updateEntity">
        <input type="hidden" name="entityId" value="${entity.id}"/>
        <div class="form-group">
            <g:textArea class="form-control" rows="4" name="description" value="${entity.description}"/>
        </div>
        <g:submitButton class="btn btn-primary" name="Save">Save</g:submitButton>
    </g:form>


    <div class="row">
        <div class="col-sm-4">
            <h3>
                People
            </h3>
            <ol>
                <g:each in="${entity.users}" var="user">
                    <li>${user.firstName} ${user.lastName}</li>
                </g:each>
            </ol>
        </div>

        <div class="col-sm-8">
            <h3>
                References
            </h3>
            <g:render template="/common/plainReferences" model="[references: entity.references*.reference]"/>
        </div>

    </div>

</div>

</body>
</html>