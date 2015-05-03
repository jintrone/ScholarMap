<%@ page import="csst15.ReferenceVote" %>
<g:applyLayout name="main">
    <div class="row">
        <div class="col-md-1"></div>

        <div class="col-md-10">
            <ol class="breadcrumb-alt">
                <li><a href="javascript:void(0);">Explore</a></li>
                <li><a href="javascript:void(0);">Venue</a></li>
            </ol>

            <div class="panel-group" id="accordion">

                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h4 class="panel-title">
                            <a data-toggle="collapse" data-parent="#accordion" href="#collapseOne">Venues Summary</a>
                        </h4>
                    </div>

                    <div id="collapseOne" class="panel-collapse collapse in">
                        <div class="panel-body">
                            Anim pariatur cliche reprehenderit, enim eiusmod high life accusamus terry richardson ad squid. 3 wolf moon officia aute, non cupidatat skateboard dolor brunch. Food truck quinoa nesciunt laborum eiusmod. Brunch 3 wolf moon tempor, sunt aliqua put a bird on it squid single-origin coffee nulla assumenda shoreditch et. Nihil anim keffiyeh helvetica, craft beer labore wes anderson cred nesciunt sapiente ea proident. Ad vegan excepteur butcher vice lomo. Leggings occaecat craft beer farm-to-table, raw denim aesthetic synth nesciunt you probably haven't heard of them accusamus labore sustainable VHS.
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
            <section class="panel panel-default">
                <div class="panel-heading"><strong><span class="glyphicon glyphicon-th"></span> Venues</strong></div>

                <div class="panel-body">

                    <div class="table-responsive">
                        <table id="allVenuesTable" class="table table-striped table-bordered">
                            <thead>
                            <tr>
                                <th>Interest</th>
                                <th>Name</th>
                                <th>Description</th>
                                <th>References</th>
                            </tr>
                            </thead>

                            <tbody>
                            %{--<g:each in="${venues}" var="venue">--}%
                            %{--<tr>--}%
                            %{--<td>${ReferenceVote.findAllByEntity(venue).user?.unique()?.size()}</td>--}%
                            %{--<td><g:link controller="entity" action="view"--}%
                            %{--params="[id: venue.id]">${venue.name}</g:link></td>--}%
                            %{--<td>${venue.description}</td>--}%
                            %{--<td>${ReferenceVote.findAllByReferenceNotIsNullAndEntity(venue)?.reference?.unique()?.size()}</td>--}%
                            %{--</tr>--}%
                            %{--</g:each>--}%
                            </tbody>
                        </table>
                    </div>

                </div>
            </section>
        </div>

        <div class="col-md-1"></div>
    </div>
</g:applyLayout>