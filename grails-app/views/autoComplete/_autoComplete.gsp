<csst:autoField class="${clazz}" collectField="${attrs.collectField}" id='${attrs.id}' name="${name}"/>
<g:javascript>
    $(document).ready(function() {
        $('#${attrs.id}').autocomplete({
            minLength: 0,
            scroll: true,
            source: function(req, resp) {
                $.getJSON('${createLink(controller: "${attrs.controller}", action: "${attrs.action}")}?term='+req.term+'&max=${attrs.max}&order=${attrs.order}',
                { type: $('#type').val() },
                function(data) {
                  resp(data)
                })
    },
    dataType: 'json',
    focus: function(event, ui) {
        $("#${attrs.id}").val(ui.item.name);
                return false;
            },
            select: function(event, ui) {
                console.log(ui.item);
                $.ajax({
                    data: {
                        name: ui.item.value
                    },
                    url: $("#loadEntityURL").val(),
                    type: 'POST',
                    dateType:'json',
                    success: function(data) {
                        console.log(data[0].name);
                        $("#type").val(data[0].type);
                        $("#description").val(data[0].desc);
                    }
                });
                $("#${attrs.id}").val(ui.item.value);
                return false;
            }
        });
    });
</g:javascript>