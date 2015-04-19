<csst:autoField class="${clazz}" id='${attrs.id}' name="${name}"/>
<g:javascript>
$(document).ready(function() {
	$('#${attrs.id}').autocomplete({
		source: '<g:createLink action='${attrs.action}' controller="${attrs.controller}"
                               params="[max: '' + attrs.max + '', order: '' + attrs.order + '']"/>',
		dataType: 'json'
	});
});
</g:javascript>