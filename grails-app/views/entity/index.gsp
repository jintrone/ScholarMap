<g:applyLayout name="main">
    <div class="row" xmlns="http://www.w3.org/1999/html">
        <div class="col-md-2"></div>

        <div class="col-md-8">
            <section class="panel panel-default">
                <div class="panel-heading"><strong><span class="glyphicon glyphicon-th"></span> Create Reference
                </strong>
                </div>

                <div class="panel-body">
                    <form role="form">
                        <div class="form-group">
                            <label for="year">Year</label>
                            <input type="text" name="year" class="form-control" id="year" placeholder="Year">
                        </div>

                        <div class="form-group">
                            <label for="citation">Citation</label>
                            <input type="text" name="citation" class="form-control" id="citation"
                                   placeholder="Citation">
                        </div>

                        <div class="form-group">
                            <label for="content">Content</label>
                            <textarea name="content" rows="10" id="content" class="form-control"
                                      placeholder="Content"></textarea>
                        </div>
                        <button type="submit" class="btn btn-primary">Submit</button>
                    </form>
                </div>
            </section>
        </div>

        <div class="col-md-2"></div>
    </div>
</g:applyLayout>