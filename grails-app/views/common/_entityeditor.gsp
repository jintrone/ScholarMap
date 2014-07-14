<%@ page import="csst15.Entity; csst15.Field" %>
<div class="row">
    <div class="col-sm-6">
        <h3 class="text-muted">Your ${ Entity.stringify(type) == "theory" ? "theorie" :  Entity.stringify(type)}s</h3>

        <div id="user-${type}" class="panel-body">
            <g:render template="/common/userentities"
                      model="[data: user.getEntitiesOfType(Entity.resolve(type)), type: type]"/>

        </div>

        <h3 class="text-muted">Create a new ${ Entity.stringify(type)}</h3>
        <g:form name="fieldForm" controller="entity" action="create" role="form">
            <input type="hidden" name="type" value="${type}"/>

            <div class="form-group">
                <label for="entity-name">${ Entity.stringify(type).capitalize()} Name:</label>
                <input id="entity-name" class="form-control" type="text" name="name" required="required" placeholder="Name"/>
            </div>
            <g:if test="${type=='venue'}">
                <div class="form-group">
                    <label for="kind">${ Entity.stringify(type).capitalize()} Type:</label>
                    <g:select noSelection="${['':'Select One...']}" required="required" data-rule-required="true" class="form-control" name="kind" role="presentation" from="['meeting w/o proceedings','meeting w/proceedings','journal','party','brouhaha','other']"/>
                </div>
            </g:if>

            <div class="form-group">
                <label for="description">${ Entity.stringify(type).capitalize()} Description:</label>
                <g:textArea class="form-control" rows="4" name="description"
                            placeholder="Enter a brief description"/>
            </div>
            <g:submitButton onclick="return alertToNav(this)" name="Submit and add references" class="btn btn-primary"/>
        </g:form>

    </div>

    <div class="col-sm-6">
        <h3 class="text-muted">Known ${ Entity.stringify(type) == "theory" ? "theorie" :  Entity.stringify(type)}s</h3>

        <div class="panel panel-default">
            <div id="available-${type}" class="panel-body">
                <g:include controller="entity" action="availableByType" params="[type: type]"/>
            </div>
        </div>

    </div>
</div>