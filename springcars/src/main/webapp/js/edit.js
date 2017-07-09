$(document).ready(function () {
    $("#edit-button").click(function () {
        var formData = new FormData($("form")[0]);
        $.ajax({
            url: "edit",
            method: "POST",
            contentType: false,
            processData: false,
            data : formData,
            success: function (data) {
                $("#main").hide(200);
                var text = "<div><span class='message'>" +
                    "Информация была успешно обновлена!</span></div>";
                var button = "<a href='" + getPrevPage() + "'>" +
                    "<button class='btn btn-success'>К объявлению</button></a>";
                var message = text + button;
                $("#info").append(message);
            }
        });
    });
});