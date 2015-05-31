<%@ page import="csst15.ReferenceVote; com.google.common.base.Joiner; org.apache.commons.lang.ArrayUtils; csst15.ReferenceAuthor" %>
<g:applyLayout name="main">
    <div class="row">
        <div class="col-md-1"></div>

        <div class="col-md-10">
            <ol class="breadcrumb-alt">
                <li><a href="javascript:void(0);">Explore</a></li>
                <li><a href="javascript:void(0);">Reference</a></li>
            </ol>

            <div class="panel-group" id="accordion">

                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h4 class="panel-title">
                            <a data-toggle="collapse" data-parent="#accordion"
                               href="#collapseOne">References Summary</a>
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
                <div class="panel-heading"><strong><span class="glyphicon glyphicon-th"></span> References
                </strong></div>

                <div class="panel-body">

                    <div class="table-responsive">
                        <table class="table table-striped table-bordered">
                            <thead>
                            <tr>
                                <th style="width: 200px !important;">Authors</th>
                                <th>Year</th>
                                <th>Full Citation</th>
                                <th>Total Votes</th>
                                <th>Action</th>
                            </tr>
                            </thead>

                            <tbody>
                            <g:each in="${references}" var="reference">
                                <tr>
                                    <td><csst:author reference="${reference}"/></td>
                                    <td>${reference.year}</td>
                                    <td>${reference.citation}</td>
                                    <td>${ReferenceVote.countByReference(reference)}</td>
                                    <td><g:link class="glyphicon glyphicon-eye-open" controller="reference"
                                                action="view" params="[id: reference.id]"/></td>
                                </tr>
                            </g:each>
                            </tbody>
                        </table>
                    </div>

                </div>
            </section>
        </div>

        <div class="col-md-1"></div>
    </div>
</g:applyLayout>