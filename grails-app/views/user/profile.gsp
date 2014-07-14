<%@ page import="grails.converters.JSON; csst15.User; org.apache.shiro.SecurityUtils; csst15.Field" %>
<!DOCTYPE html>
<html>
<g:set var="user" value="${User.findByEmail(SecurityUtils.subject.principal)}"/>
<head>

    <meta name="layout" content="main"/>


    <script type="application/javascript">

        $(document).ready(function () {
            var showModal = "${user.getPrefs()["profile-modal"]}";
            if (showModal != "false") {
                $("#profileModal").modal("show");
            }

            var tab = "${tab}";
            var selector = 'a[href="#' + tab + '"]';
            if ($.inArray(tab, ["theory", "field", "venue", "method"]) > -1) {

                $(selector).tab('show')
            }
        });

        var pendingForm;

        function alertToNav(obj) {

            var showModal = "${user.getPrefs()["ref-modal"]}";

            if (showModal != "false") {
                pendingForm = $(obj).closest("form");

                $("#myModal").modal("show");

                return false
            } else {
                return true
            }

        }

        function formSubmit() {
            pendingForm.submit();
        }

        function updateAvailable(type) {

            $.ajax("<g:createLink controller="entity" action="availableByType"/>?type=" + type).done(function (data) {
                $("#available-" + type).html(data)
            });
        }
        function updateMine(type) {

            $.ajax("<g:createLink controller="user" action="entitiesByType"/>?type=" + type).done(function (data) {
                $("#user-" + type).html(data)
            });
        }

        function addToUser(kind, id) {
            $.ajax("<g:createLink controller="entity" action="addToUser"/>/" + id).done(function () {
                updateAll(kind)
            })
        }

        function setPreferences(modal, obj) {

            $.ajax("<g:createLink controller="user" action="setprefs"/>?pref=" + modal + "&value=" + obj.value)
        }

        function updateAll(type) {
            updateAvailable(type);
            updateMine(type)
        }


    </script>
</head>

<body>

<div class="container">

    <div class="padme">

        <ul class="nav nav-pills nav-justified" role="tablist">
            <li class="active"><a class="profile_tab" href="#field" role="tab" data-toggle="tab">Area</a></li>
            <li><a class="profile_tab" href="#theory" role="tab" data-toggle="tab">Theory</a></li>
            <li><a class="profile_tab" href="#method" role="tab" data-toggle="tab">Method</a></li>
            <li><a class="profile_tab" href="#venue" role="tab" data-toggle="tab">Venue</a></li>
        </ul>

        <div class="tab-content">
            <div class="tab-pane active" id="field">
                <div class="padme-v">
                    <div class="well ">
                        Area refers to an area of study. It may be very broad (sociology, communication) or
                        quite narrow (neural plasticity, evolutionary computation). Select from among existing areas on the right, or define your
                        own using the form at the bottom of the screen.
                    </div>
                </div>
                <g:render template="/common/entityeditor" model="[user: user, type: 'field']"/>

            </div>

            <div class="tab-pane" id="theory">
                <div class="padme-v">
                    <div class="well ">
                        Theory is meant broadly&mdash;in different contexts a theory might be a precise modal, an analytical framework, or even a perspective.
                    All of these definitions are intended here.
                    </div>
                </div>
                <g:render template="/common/entityeditor" model="[user: user, type: 'theory']"/>
            </div>

            <div class="tab-pane" id="method">
                <div class="padme-v">
                    <div class="well ">
                        A method is a set of tools or techniques used in executing scholarly research.
                    </div>
                </div>

                <g:render template="/common/entityeditor" model="[user: user, type: 'method']"/>
            </div>

            <div class="tab-pane" id="venue">
                <div class="padme-v">
                    <div class="well ">
                        A venue is any outlet where you might communicate about your work. For this application, please provide
                        venues that are or might be amenable to the kinds of work you do as a sociotechnical scholar.
                    </div>
                </div>
                <g:render template="/common/entityeditor" model="[user: user, type: 'venue']"/>

            </div>
        </div>
    </div>

</div>

<div id="profileModal" class="modal fade">
    <div class="modal-dialog">
        <div class="modal-content">

            <div class="modal-body">
                <p>This is your profile page.  It is divided into four categories:  Areas, Theories, Methods, and Venues.  These
                categories intended to be broad and non-exclusive. Do not worry if you find yourself adding the same terms in several categories
                (e.g. social network analysis is both an area and a method).
                </p>

                <p>
                    Within each category, please add any items that you either have expertise with or interest in. You can use pre-existing items in the tag-cloud,
                    or add new items using the form on the bottom left.
                </p>
            </div>

            <div class="modal-footer">

                <div class="pull-left">
                    <g:checkBox name="nomodal"
                                onclick="setPreferences('profile-modal',this)"/>I've got it, never show me this again!
                </div>
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>

            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->

<div id="myModal" class="modal fade">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">Add references</h4>
            </div>

            <div class="modal-body">
                <p>Now you will be asked to add references that help characterize this item. You
                can select from among those references others have already entered, or create your own.
                </p>
            </div>

            <div class="modal-footer">

                <div class="pull-left">
                    <g:checkBox class="pull-left" name="nomodal"
                                onclick="setPreferences('ref-modal',this)"/>I've got it, never show me this again!
                </div>

                <button type="button" class="btn btn-default" data-dismiss="modal" onclick="formSubmit()">Close</button>

            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->

</body>
</html>