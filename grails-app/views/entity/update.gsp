<%@ page import="org.apache.shiro.SecurityUtils; csst15.User; csst15.Entity" contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main"/>
    <title></title>
    <asset:javascript src="jquery.form.min.js"/>
    <script type="application/javascript">
        $(document).ready(function () {
            // bind 'myForm' and provide a simple callback function
            $('#referenceForm').ajaxForm(function () {
                $('#referenceForm').trigger("reset");
                updateAll();
            });


        });


        function addReference(id) {
            $.ajax("<g:createLink controller="referenceVote" action="create" params="[entityId:entity.id]"/>&id=" + id).done(function () {

                updateAll();
            });
        }

        function updateAll() {
            updateAllReferences();
            updateMyReferences();
        }

        function updateMyReferences() {
            $.ajax("<g:createLink controller="referenceVote" action="myreferences" params="[entityId:entity.id]"/>").done(function (data) {
                $("#myReferences").html(data)
            });
        }

        function updateAllReferences() {
            $.ajax("<g:createLink controller="reference" action="availablereferences" params="[interactive: 'true', callback: 'addReference', entityId: entity.id]"/>").done(function (data) {
                $("#references").html(data)
            });
        }

        function checkEnable() {
           // alert($("input#lead-author").val());
            //alert($("input#pub-year").value.trim())



            if ($("input#lead-author").val().trim() != "" && $("input#pub-year").val().trim() != "" && $("#completeReference").val().trim()!= "") {

                $("input.addRef").prop("disabled", false)
            } else {

                $("input.addRef").prop("disabled", "disabled")
            }

        }


    </script>
</head>

<g:set var="type" value="${entity.class.simpleName.toLowerCase()}"/>
<body>
<div class="container padme">
    <h2>
        Manage references for <span class="text-info">${entity.name}</span>
    </h2>

    <div class="well text-muted">
        <p>
            Use this page to select references that you feel represent this ${Entity.stringify(type)} well. You can select from among any
            existing references by simply clicking on that reference in the table. Please add any additional references using the
            form below.
        </p>
        <g:link controller="user" action="profile" params="[tab: type]"><em>&lt;- Back to profile</em></g:link>
    </div>

    <div class="row">
        <div class="col-sm-6">

            <h3 class="text-muted">Your references for this ${Entity.stringify(type)}</h3>

            <div id="myReferences">
                <g:include controller="referenceVote" action="myreferences" params="[entityId: entity.id]"/>
            </div>

            <h3 class="text-muted">Add a new reference</h3>
            <g:form name="referenceForm" controller="reference" action="create" role="form">

                <input type="hidden" name="entityId" value="${entity.id}"/>

                <div class="form-group">
                    <label for="lead-author">Lead author</label>
                    <input id="lead-author" class="form-control" type="text" name="author" required="required"
                           placeholder="Last name" onkeyup="checkEnable()"/>
                </div>

                <div class="form-group">
                    <label for="pub-year">Publication year</label>
                    <input id="pub-year" class="form-control" type="text" name="year" required="required"
                           placeholder="XXXX" onkeyup="checkEnable()"/>
                </div>

                <div class="form-group">
                    <label for="completeReference">Citation</label>
                    <g:textArea class="form-control" required="required" rows="3" name="completeReference"
                                placeholder="Full citation" onkeyup="checkEnable()"/>
                </div>
                <g:submitButton name="Add reference" disabled="disabled" class="addRef btn btn-primary"/>&nbsp;&nbsp;<g:link
                    class="btn btn-primary" controller="user" action="profile"
                    params="[tab: type]">Finished adding references</g:link>
            </g:form>
        </div>

        <div class="col-sm-6">
            <h3 class="text-muted">Available References</h3>

            <div id="references">
                <g:include controller="reference" action="availablereferences"
                           params="[interactive: 'true', callback: 'addReference', entityId: entity.id]"/>

            </div>
        </div>

    </div>
</div>
</body>
</html>

