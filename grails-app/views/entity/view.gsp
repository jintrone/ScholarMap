<g:applyLayout name="main">
    <div class="row">
        <div class="col-md-3"></div>

        <div class="col-md-6">

            <div class="panel panel-default">
                <div class="panel-heading">
                    <strong style="padding-right: 25px"><span class="glyphicon glyphicon-th"></span> Entity</strong>
                </div>

                <div class="panel-body">
                    <div class="media">
                        <div class="media-body">
                            <ul class="list-unstyled list-info">
                                <li>
                                    <label>Type</label>
                                    ${entity.type}
                                </li>
                                <li>
                                    <label>Name</label>
                                    ${entity.name}
                                </li>
                                <li>
                                    <label>Description</label>
                                    ${entity.description}
                                </li>
                            </ul>

                        </div>
                    </div>
                </div>
            </div>
        </div>

        <div class="col-md-3"></div>
    </div>
</g:applyLayout>