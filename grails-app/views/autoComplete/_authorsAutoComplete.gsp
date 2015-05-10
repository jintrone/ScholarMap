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
                $.ajax({
                    type: 'POST',
                    url: $("#loadAuthorRefs").val(),
                    data: {
                        name: ui.item.value
                    },
                    dateType:'json',
                    success: function(data) {
                        $("#ref_tab").css('display', 'block');
                        $("#ref_tab").find("tr:gt(0)").remove();
                        for(var i = 0; i < data.length; i ++) {
                            $("#ref_tab").find("tr:first").clone().appendTo("#ref_tab tbody").find('td').text(data[i].citation);
                            $("#ref_tab").find("tr input").eq(i+1).val(data[i].id);
                        }
                        setReferenceDetails();
                    }
                });
                $("#${attrs.id}").val(ui.item.value);
                return false;
            }
        });


    });

    function setReferenceDetails() {
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
                        $("#tab_logic").find("tbody tr:gt(0)").remove();
                        $.each(data[0].authors, function(index, val) {
                            if(val != $("#tab_logic").find("tbody tr:first input").val()) {
                                $("#tab_logic").find("tbody tr:first").clone().appendTo("#tab_logic tbody").find('input').val(val);
                            }
                        });
                        $("#citation").val(data[0].citation);
                        $("#year").val(data[0].year);
                    }
                });
            });
        });
    }
</g:javascript>