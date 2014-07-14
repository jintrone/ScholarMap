<table id="entity-summary" class="table">
    <thead>
    <tr>
        <th>
            Name
        </th>
        <g:if test="${type=='venue'}">
        <th>
           Type
        </th>
        </g:if>

        <th>
            Description
        </th>

        <th>
            Interest
        </th>


    </tr>
    </thead>
    <tbody>
    <g:each in="${data}" var="item">

        <tr>
            <td><g:link controller="entity" action="detail" params="[entityId:item[0].id]">${item[0].name}</g:link></td>
            <g:if test="${type=='venue'}">
                <td>${item[0].kind}</td>
            </g:if>
            <td>${item[0].description}</td>
            <td>${item[1]}</td>

        </tr>

    </g:each>
    </tbody>
</table>