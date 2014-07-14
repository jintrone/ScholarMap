<g:if test="${!references}">
    No references found!
</g:if>
<g:else>
    <table id="references" class="table table-hover">
        <thead>
        <tr>
            <th>
                Author
            </th>
            <th>
                Year
            </th>
            <th>
                Citation
            </th>

        </tr>
        </thead>
        <tbody>
        <g:each in="${references}" var="ref">

            <tr id="${ref.id}${ref.id in exclude?"-exclude":""}" <g:if test="${ref.id in exclude}">class="info"</g:if>>
                <td>${ref.author}</td>
                <td>${ref.year}</td>
                <td>${ref.text}</td>
            </tr>

        </g:each>
        </tbody>
    </table>
    <script type="application/javascript">
        $('#references tr').click(function() {
            if (this.id.indexOf("exclude") < 0) {
                ${callback}(this.id);
            }
        });
    </script>

</g:else>