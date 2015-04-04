<g:applyLayout name="main">
    <!-- Main jumbotron for a primary marketing message or call to action -->
    <div class="jumbotron">
        <div class="container">
            <p>Welcome, CSST 2014! This the second iteration of ScholarMap, a prototype of a tool intended
            to help us build bridges and understand our academic community a little better.
            </p>

            <p>The success of ScholarMap depends upon <b>you</b>, fellow CSSTers!  So please, register, login, and fill out your
            profiles over the course of the week. Your efforts will help reveal some of the hidden structure in our community.
            More importantly, it will show you how you fit in.
            </p>
        </div>
    </div>

    <div class="container">
        <!-- Example row of columns -->
        <div class="row">
            <div class="col-sm-3">
                <h2>Areas</h2>
                <g:render template="/common/shortEntityList" model="[type: 'field']"/>
                <p><g:link controller="home" action="areas"><em>See more -></em></g:link></p>
            </div>

            <div class="col-sm-3">
                <h2>Theories</h2>
                <g:render template="/common/shortEntityList" model="[type: 'theory']"/>
                <p><g:link controller="home" action="theories"><em>See more -></em></g:link></p>
            </div>

            <div class="col-sm-3">
                <h2>Methods</h2>
                <g:render template="/common/shortEntityList" model="[type: 'method']"/>
                <p><g:link controller="home" action="methods"><em>See more -></em></g:link></p>
            </div>

            <div class="col-sm-3">
                <h2>Venues</h2>
                <g:render template="/common/shortEntityList" model="[type: 'venue']"/>
                <p><g:link controller="home" action="venues"><em>See more -></em></g:link></p>
            </div>
        </div>

        <hr>

    </div> <!-- /container -->
</g:applyLayout>