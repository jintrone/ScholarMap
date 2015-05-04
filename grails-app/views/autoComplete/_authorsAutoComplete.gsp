<csst:autoField class="${clazz}" collectField="${attrs.collectField}" placeholder="${placeholder}" title="${title}"
                id='${attrs.id}' name="${name}"/>
<g:javascript>
    $(document).ready(function() {
        $('#${attrs.id}').autocomplete({
            minLength: 0,
            scroll: true,
            source: function(req, resp) {
                $.getJSON('${createLink(controller: "${attrs.controller}", action: "${attrs.action}")}?term='+req.term+'&max=${attrs.max}&order=${attrs.order}',
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
                    url: $("#loadAuthorRefs").val(),
                    type: 'POST',
                    dateType:'json',
                    success: function(data) {
                        $("#ref_tab").css('display', 'block');
                        $("#ref_tab").find("tr:gt(0)").remove();
                        for(var i = 0; i < data.length; i ++) {
                            console.log(data[i].citation);
                            $("#ref_tab").find("tr:first").clone().appendTo("#ref_tab tbody").find('td').text(data[i].citation);
                            $("#ref_tab").find("tr input").eq(i+1).val(data[i].id);

                        }
                        myFunc();
                    }
                });
                $("#${attrs.id}").val(ui.item.value);
                return false;
            }
        });


    });

    function myFunc() {
       $("#ref_tab").find("tr:gt(0)").each(function() {
            $(this).click(function() {
                $.ajax({
                    data: {
                        id: $(this).find('input').val()
                    },
                    url: $("#loadAuthorRefsDetails").val(),
                    type: 'POST',
                    dateType:'json',
                    success: function(data) {
                        $("#citation").val(data[0].citation);
                        $("#year").val(data[0].year);
                    }
                });
            });
        });
    }
</g:javascript>