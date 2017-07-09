$(document).ready(function () {
    $("#create-button").click(function () {
        var formData = new FormData($("form")[0]);
        $.ajax({
            url: "add",
            method: "POST",
            contentType: false,
            processData: false,
            data : formData,
            success: function (data) {
                $("#main").hide(200);
                var text = "<div><span class='message'>" +
                    "Информация была успешно добавлена!</span></div>";
                $("#info").append(text);
            }
        });
    });
});