<g:applyLayout name="main">
    <div class="row">
        <div class="col-lg-12">
            <g:each in="${users}" var="user">
                <div class="panel panel-profile col-md-3">
                    <div class="panel-heading text-center bg-info">
                        <g:link controller="user" action="profile" params="[username: user.username]">
                            <g:img alt="" uri="/images/default-user.png" class="img-circle img80_80"/>

                            <h3>${user.firstName} ${user.lastName}</h3>
                        </g:link>
                        <p>${user.position}</p>
                    </div>
                </div>
            </g:each>
        </div>
    </div>
</g:applyLayout>