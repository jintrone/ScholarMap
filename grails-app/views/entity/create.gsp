<%@ page import="csst15.constants.EntityType" %>
<g:applyLayout name="main">
    <div class="row" xmlns="http://www.w3.org/1999/html">
        <div class="col-md-2"></div>

        <div class="col-md-8">
            <section class="panel panel-default">
                <div class="panel-heading">
                    <strong><span class="glyphicon glyphicon-th"></span> Create Entity</strong>
                </div>

                <div class="panel-body">
                    <g:form controller="entity" action="submit" method="post">
                        <div class="form-group">
                            <g:select from="${EntityType.values()}" noSelection="['': '']"
                                      class="form-control" value="${command?.type}" name="type" placeholder="Type"/>
                        </div>

                        <div class="form-group">
                            <label for="name">Name</label>
                            <input type="text" name="name" value="${command?.name}" class="form-control" id="name"
                                   placeholder="Name">
                        </div>

                        <div class="form-group">
                            <label for="description">Description</label>
                            <textarea name="description" rows="10" id="description" class="form-control"
                                      placeholder="Description">${command?.description}</textarea>
                        </div>
                        <button type="submit" class="btn btn-primary">Submit</button>
                    </g:form>
                </div>
            </section>
        </div>

        <div class="col-md-2"></div>
    </div>
</g:applyLayout>