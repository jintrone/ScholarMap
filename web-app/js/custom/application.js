/**
 * Created by Emil Matevosyan
 * Date: 2/25/15.
 */

$(document).ready(function () {
    $("#isRegEnabled").click(function () {
        $.ajax({
            url: $("#manipulateReg").val(),
            type: 'POST',
            success: function (data) {
                console.log('success');
            }
        })
    })
});
