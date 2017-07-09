$(document).ready(function () {
    var tableBody = $("tbody");
    var showDone = true;

    $("#refresh").click(function () {
        tableBody.empty();
        $.ajax({
            url: "get",
            method: "GET",
            dataType: "json",
            success: function (data) {
                $.each(data.items, function (index, element) {
                    createRow(element);
                });
            }
        });
        $("#show-done").prop('checked', true);
        showDone = true;
    });

    $("#add_button").click(function () {
        var description = $("#add_desc");
        if (Boolean(isValid(description))) {
            $.ajax({
                url: "add",
                method: "POST",
                data: {
                    'description': description.val()
                }
            });
        }
        description.val('');
    });

    $("#show-done").click(function () {
        showDone = !showDone;
        console.log(showDone);
        filterRows();
    });

    function filterRows() {
        $.each($("tbody tr"), function (index, element) {
            if ($(element).children().eq(3).html() == 'true') {
                if (!showDone) {
                    console.log('hide');
                    console.log(element);
                    $(element).hide();
                } else {
                    console.log('show');
                    console.log(element);
                    $(element).show();
                }
            }
        });
    }

    function createRow(element) {
        var tr = $("<tr></tr>");
        tr.append($("<td></td>").text(element.id));
        tr.append($("<td></td>").text(element.description));
        tr.append($("<td></td>").text(element.date));
        tr.append($("<td></td>").text(element.done));
        tableBody.append(tr);
    }

    function isValid(element) {
        var valid = element.val() != '';
        if (valid) {
            $("label[for='" + element.attr('id') + "']").css("color", "");
        } else {
            $("label[for='" + element.attr('id') + "']").css("color", "red");
        }
        return valid;
    }
});