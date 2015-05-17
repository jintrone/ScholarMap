/**
 * Created by Emil Matevosyan
 * Date: 2/25/15.
 */

$(document).ready(function () {

    var i = 1;
    $("#add_row").click(function () {
        $('#addr' + i).html("<td>" + (i + 1) + "</td><td><input name='fullName' type='text' placeholder='Last name, First name' class='form-control input-md ui-autocomplete-input'  /> </td>");

        $('#tab_logic').append('<tr id="addr' + (i + 1) + '"></tr>');
        i++;
    });
    $("#delete_row").click(function () {
        if (i > 1) {
            $("#addr" + (i - 1)).html('');
            i--;
        }
    });


    setMethodsDataTable();
    setVenuesDataTable();
    setFieldsDataTable();
    setTheoriesDataTable();

    $("#addInterestModal").find("#type").change(function () {
        $("#addInterestModal").find("#name").attr("collectfield", $(this).val());
    });

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

    var photoPanel = $("#photoPanel");
    $("#changePhoto").click(function () {
        photoPanel.find(".simplePanel").remove();
        photoPanel.append($("#photoPanelClone").clone().css("display", "block"));
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

    $(".edit-entity").click(function () {
        $('#editEntityModal').modal('show');
    });

    $(".edit-reference").click(function () {
        $('#editReferenceModal').modal('show');
    });

    $("#addNewEntity").click(function () {
        $('#addInterestModal').modal('show');
    });

    $("#addInterestBtn").click(function () {
        $.ajax({
            type: 'POST',
            url: $("#addInterestURL").val(),
            data: {
                type: $("#type").val(),
                name: $("#name").val(),
                description: $("#description").val()
            },
            success: function (data) {
                var interestRecords = $('#interestRecords');
                $('#addInterestModal').modal('hide');
                interestRecords.find('table').remove();
                interestRecords.find('#addReferenceModal').remove();
                interestRecords.append(data);
                $('#addReferenceModal').modal('show');
                resetFields($('#addInterestModal'));

            }
        });
    });

    refVote();
    applyDatatable();
    showRefModal();

});

function applyDatatable() {
    //$("#availableReferences").dataTable({
    //    "paging": true,
    //    "ordering": true,
    //    "info": false,
    //    "bFilter": true
    //});

    setAvailableRefsDataTable();
    setSelectedRefsDataTable();

    //$("#selectedReferences").dataTable({
    //    "paging": true,
    //    "ordering": true,
    //    "info": false,
    //    "bFilter": true
    //});
}

function resetFields(container) {
    container.find('input').val("");
    container.find('select').val("");
    container.find('textarea').val("");
}

function refVote() {
    var availableReferences = $("#availableReferences");
    availableReferences.find(".select-reference").click(function () {
        $.ajax({
            type: 'POST',
            url: $("#referenceVoteURL").val(),
            data: {
                refId: $(this).attr('id'),
                entity: $("#selectedRefPanel").find("#entity").val()
            },
            success: function (data) {
                $("#referenceTablesPanel").remove();
                $("#refTablesContainer").append(data);
                refVote();
                showRefModal();
                applyDatatable();
            }
        });
    });
}

function showRefModal() {
    $("#addNewRefBtn").click(function () {
        clearRefModalFields();
        $('#addNewReferenceModal').modal('show');
    });
}

function clearRefModalFields() {
    $("#addNewReferenceModal").find('.modal-dialog .form-group input').val("");
    $("#addNewReferenceModal").find('.modal-dialog .form-group textarea').val("");
    $("#ref_tab").find("tr:gt(0)").remove();
    $("#ref_tab").css('display', 'none');
    $("#tab_logic").find("tbody tr:gt(0)").remove();
    $("#tab_logic").find("tbody tr:first").val("");
}

function setMethodsDataTable() {
    $("#allMethodsTable").dataTable({
        "processing": true,
        "serverSide": true,
        "ajax": {
            "url": './home/methods',
            "type": "POST"
        },
        "columns": [
            {"data": "interest"},
            {
                "data": "name",
                "mRender": function (data, type, full) {
                    return "<a href='./entity/view?name=" + data + "'>" + data + "</a>";
                }
            },
            {"data": "description"},
            {"data": "references"}
        ]
    });
}

function setVenuesDataTable() {
    $("#allVenuesTable").dataTable({
        "processing": true,
        "serverSide": true,
        "ajax": {
            "url": './home/venues',
            "type": "POST"
        },
        "columns": [
            {"data": "interest"},
            {
                "data": "name",
                "mRender": function (data, type, full) {
                    return "<a href='./entity/view?name=" + data + "'>" + data + "</a>";
                }
            },
            {"data": "description"},
            {"data": "references"}
        ]
    });
}

function setFieldsDataTable() {
    $("#allFieldsTable").dataTable({
        "processing": true,
        "serverSide": true,
        "ajax": {
            "url": './home/areas',
            "type": "POST"
        },
        "columns": [
            {"data": "interest"},
            {
                "data": "name",
                "mRender": function (data, type, full) {
                    return "<a href='./entity/view?name=" + data + "'>" + data + "</a>";
                }
            },
            {"data": "description"},
            {"data": "references"}
        ]
    });
}

function setTheoriesDataTable() {
    $("#allTheoriesTable").dataTable({
        "processing": true,
        "serverSide": true,
        "ajax": {
            "url": './home/theories',
            "type": "POST"
        },
        "columns": [
            {"data": "interest"},
            {
                "data": "name",
                "mRender": function (data, type, full) {
                    return "<a href='./entity/view?name=" + data + "'>" + data + "</a>";
                }
            },
            {"data": "description"},
            {"data": "references"}
        ]
    });
}

function setAvailableRefsDataTable() {
    $("#availableReferences").dataTable({
        "processing": true,
        "serverSide": true,
        "ajax": {
            "url": './loadAvailableReferences',
            "type": "POST",
            'data': {
                entity: $("#entity").val()
            }
        },
        "columns": [
            {"data": "author[; ]"},
            {"data": "year"},
            {"data": "citation"},
            {"data": "votes"},
            {
                "data": "id",
                "mRender": function (data, type, full) {
                    return "<a class='glyphicon glyphicon-eye-open' href='/scholarMap/reference/view" + data + "'></a>" +
                        "<a class='select-reference glyphicon glyphicon-arrow-up' href='/scholarMap/interests/referenceVote" + data + "'></a>";
                }
            }
        ]
    });
}

function setSelectedRefsDataTable() {
    $("#selectedReferences").dataTable({
        "processing": true,
        "serverSide": true,
        "ajax": {
            "url": './loadSelectedReferences',
            "type": "POST",
            'data': {
                entity: $("#entity").val(),
                isOwner: $("#isOwner").val()
            }
        },
        "columns": [
            {"data": "author[; ]"},
            {"data": "year"},
            {"data": "citation"},
            {"data": "votes"},
            {
                "data": "id",
                "mRender": function (data, type, full) {
                    if (full.isOwner == true) {
                        return "<a class='glyphicon glyphicon-eye-open' href='/scholarMap/reference/view" + data + "'></a>" +
                            "<a class='glyphicon glyphicon-remove' href='/scholarMap/interests/removeVote" + data + "'></a>";
                    } else {
                        return "<a class='glyphicon glyphicon-eye-open' href='/scholarMap/reference/view" + data + "'></a>";
                    }

                }
            }
        ]
    });
}