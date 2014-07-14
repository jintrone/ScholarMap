<%@ page import="org.apache.shiro.SecurityUtils; csst15.User; csst15.Entity" %>
<g:set var="user" value="${User.findByEmail(SecurityUtils.subject.principal)}"/>
<table class="table">
    <thead>
    <tr>
        <th>${Entity.stringify(type).capitalize()}</th><th class="text-center"># Refs</th><th></th>
    </tr>
    </thead>
    <g:if test="${!data.isEmpty()}">
        <tbody>
        <g:each in="${data}" var="item">
            <tr>
                <td><g:link controller="entity" action="detail" params="[entityId:item.id]">${item.name}</g:link></td>

                <td class="text-center"><g:link controller="entity" action="update"
                                                params="[type: type, entity: item.id]">${((Entity) item).getUserReferences(user)?.size() == 0 ? "Add" : item.getUserReferences(user).size()}</g:link></td>
                <td class="text-center"><g:link class="ajax-remove-${type}-association" controller="entity"
                                                action="removeFromUser" id="${item.id}"><span
                            class="glyphicon glyphicon-remove"></span></g:link></td>
            </tr>
        </g:each>
        </tbody>
        </table>
    </g:if>
    <g:else>
        </table>
        <em>No items selected!</em>
    </g:else>

<script type="application/javascript">
    var type = "${type}"
    $(".ajax-remove-" + type + "-association").click(function () {
        $.ajax(this.href).done(function () {
            updateAll("${type}");
        });
        return false
    })
</script>