<g:applyLayout name="main">
    <div class="row">
        <div class="col-md-3"></div>

        <div class="col-md-6">

            <div class="panel panel-default">
                <div class="panel-heading">
                    <strong style="padding-right: 25px"><span class="glyphicon glyphicon-th"></span> Reference</strong>
                </div>

                <div class="panel-body">
                    <div class="media">
                        <div class="media-body">
                            <ul class="list-unstyled list-info">
                                <li>
                                    <label>Creator</label>
                                    <g:link controller="user" action="profile"
                                            params="[username: reference.creator?.username]">
                                        ${reference.creator?.firstName + " " + reference.creator?.lastName}
                                    </g:link>
                                </li>
                                <li>
                                    <label>Year</label>
                                    ${reference.year}
                                </li>
                                <li>
                                    <label>Citation</label>
                                    ${reference.citation}
                                </li>
                                <li>
                                    <label>Content</label>
                                    ${reference.content}
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