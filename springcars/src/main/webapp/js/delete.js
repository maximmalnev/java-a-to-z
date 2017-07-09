$(document).ready(function () {
    var url = window.location.href;
    $("#delete-button").click(function () {
        $.ajax({
            url: url + "/delete",
            method: "get",
            success: function (data) {
                $("#buttons-id").hide();
                $("#car-info").hide(200);
                var text = "<div><span class='message'>" +
                    "Объявление было снято с продажи!</span></div>";
                $("#info").append(text);
            }
        });
    });
});