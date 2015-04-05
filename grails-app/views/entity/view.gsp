<%@ page import="csst15.ReferenceAuthor; com.google.common.base.Joiner; csst15.ReferenceVote" %>
<g:applyLayout name="main">
    <div class="row">
        <div class="col-md-1"></div>

        <div class="col-md-10">
            <ol class="breadcrumb-alt">
                <li><a href="javascript:void(0);">Explore</a></li>
                <li>
                    <g:if test="${entity.type.name.equals("Area")}">
                        <g:link controller="home" action="areas">${entity.type.name}</g:link>
                    </g:if>
                    <g:elseif test="${entity.type.name.equals("Theory")}">
                        <g:link controller="home" action="theories">${entity.type.name}</g:link>
                    </g:elseif>
                    <g:elseif test="${entity.type.name.equals("Method")}">
                        <g:link controller="home" action="methods">${entity.type.name}</g:link>
                    </g:elseif>
                    <g:elseif test="${entity.type.name.equals("Venue")}">
                        <g:link controller="home" action="venues">${entity.type.name}</g:link>
                    </g:elseif>
                </li>
                <li class="active"><a href="javascript:void(0);">${entity.name}</a></li>
            </ol>

            <div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true">
                <div class="panel panel-default">
                    <div class="panel-heading" role="tab" id="headingOne">
                        <h4 class="panel-title">
                            <strong style="padding-right: 25px"><span class="glyphicon glyphicon-th"></span> Details for
                                <a data-toggle="collapse" data-parent="#accordion" href="#collapseOne"
                                   aria-expanded="true"
                                   aria-controls="collapseOne">
                                    ${entity.name}
                                </a>
                            </strong>
                        </h4>
                    </div>

                    <div id="collapseOne" class="panel-collapse collapse in" role="tabpanel"
                         aria-labelledby="headingOne">
                        <div class="panel-body">
                            ${entity.description}
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <div class="col-md-1"></div>
    </div>

    <div class="row">
        <div class="col-md-1"></div>

        <div class="col-md-10">
            <div role="tabpanel">

                <!-- Nav tabs -->
                <ul class="nav nav-tabs" role="tablist">
                    <li role="presentation" class="active"><a href="#profile" aria-controls="profile" role="tab"
                                                              data-toggle="tab">People</a></li>
                    <li role="presentation"><a href="#messages" aria-controls="messages" role="tab"
                                               data-toggle="tab">References</a></li>
                </ul>

                <!-- Tab panes -->
                <div class="tab-content" style="margin-top: 15px">

                    <div role="tabpanel" class="tab-pane active" id="profile" style="margin-top: 15px">
                        <div class="panel panel-default">

                            <div class="panel-body">

                                <table class="table table-striped table-bordered display" cellspacing="0"
                                       id="entityPeopleTable">
                                    <thead>
                                    <tr>
                                        <th>Name</th>
                                        <th>Position</th>
                                        <th>Department</th>
                                        <th>Other Theories</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <g:each in="${ReferenceVote.findAllByEntity(entity)?.user?.unique()}" var="user">
                                        <tr>
                                            <td>
                                                <g:link controller="user" action="profile"
                                                        params="[username: user.username]">
                                                    ${user.firstName} ${user.lastName}
                                                </g:link>
                                            </td>
                                            <td>${user.position?.name}</td>
                                            <td>${user.department?.title}</td>
                                            <g:set var="other"
                                                   value="${ReferenceVote.findAllByUserAndEntityNotEqual(user, entity).entity.unique().findAll {
                                                       it.type.equals(entity.type)
                                                   }}"/>
                                            <td>${Joiner.on(', ').skipNulls().join(other.name)}</td>
                                        </tr>
                                    </g:each>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>

                    <div role="tabpanel" class="tab-pane" id="messages" style="margin-top: 15px">
                        <section class="panel panel-default">

                            <div class="panel-body">

                                <div class="table-responsive">
                                    <table class="table table-striped table-bordered display" cellspacing="0"
                                           id="entityRefTable">
                                        <thead>
                                        <tr>
                                            <th>Authors</th>
                                            <th>Year</th>
                                            <th>Citation</th>
                                        </tr>
                                        </thead>

                                        <tbody>
                                        <g:each in="${ReferenceVote.findAllByEntity(entity)?.reference?.unique()}"
                                                var="reference">
                                            <tr>
                                                <td>${Joiner.on(',').skipNulls().join(ReferenceAuthor.findAllByReference(reference)?.author?.lastName)}</td>
                                                <td>${reference.year}</td>
                                                <td>${reference.citation}</td>
                                            </tr>
                                        </g:each>
                                        </tbody>
                                    </table>
                                </div>

                            </div>
                        </section>
                    </div>
                </div>
            </div>
        </div>

        <div class="col-md-1"></div>
    </div>
</g:applyLayout>