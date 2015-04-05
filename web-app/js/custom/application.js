/**
 * Created by Emil Matevosyan
 * Date: 2/25/15.
 */

$(document).ready(function () {
    if ($("#isRequired").length > 0) {
        $('#fillRequiredModal').modal({
            keyboard: false,
            backdrop: 'static'
        })
    }

    $("#isRegEnabled").click(function () {
        $.ajax({
            url: $("#manipulateReg").val(),
            type: 'POST',
            success: function (data) {
                console.log('success');
            }
        })
    });

    $("#admin_edit_profile").find(".lock").click(function () {
        $.ajax({
            url: $("#manipulateLock").val(),
            type: 'POST',
            data: {
                fieldName: $(this).attr('id'),
                userId: $("#userId").val()
            },
            success: function (data) {
                console.log('success');
            }
        })
    });

    $("#mandatory-sec").find(".mandatory").click(function () {
        $.ajax({
            url: $("#manipulateMand").val(),
            type: 'POST',
            data: {
                fieldName: $(this).attr('id')
            },
            success: function (data) {
                console.log('success');
            }
        })
    });

    $("#editPanel").find(".permission").click(function () {
        $.ajax({
            url: $("#manipulatePermission").val(),
            type: 'POST',
            data: {
                fieldName: $(this).attr('id'),
                userId: $("#userId").val()
            },
            success: function (data) {
                console.log('success');
            }
        })
    });

    $("#changePhoto").click(function () {
        $("#photoPanel").find(".simplePanel").remove();
        $("#photoPanel").append($("#photoPanelClone").clone().css("display", "block"));
    });

    $("#importUser").click(function () {
        $('#myModal').modal('show')
    });

    $(".remove-interest").click(function () {
        $('#deleteInterestModal').modal('show');
        $("#entityId").val($(this).next().val());
    });

    $("#entityTable").dataTable({});

    $("#referenceTable").dataTable({});

    $("#usersTable").dataTable({});

    $("#listTable").dataTable({});

    $("#interestTable").dataTable({
        "paging": false,
        "ordering": true,
        "info": false,
        "bFilter": false
    });

    $("#entityPeopleTable").dataTable({
        "paging": false,
        "ordering": true,
        "info": false,
        "bFilter": false
    });

    $("#entityRefTable").dataTable({
        "paging": false,
        "ordering": true,
        "info": false,
        "bFilter": false
    });
});
