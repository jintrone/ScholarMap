<g:set var="localRefs" value="${references==null?csst15.Reference.list(sort: "author"):references}"/>
<g:if test="${localRefs.isEmpty()}">
       <p>
           <em>No references to this item.</em>
       </p>
</g:if>
<g:else>
<table class="table">
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
    <g:each in="${localRefs}" var="ref">

        <tr>
            <td>${ref.author}</td>
            <td>${ref.year}</td>
            <td>${ref.text}</td>

        </tr>

    </g:each>
    </tbody>
</table>
</g:else>