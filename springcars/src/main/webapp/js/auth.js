$(document).ready(function () {
    $("#auth-button").click(function () {
        var username = $("#username");
        var password = $("#password");
        if (Boolean(isValid(username) & isValid(password))) {
            $.ajax({
                url: "/login",
                method: "POST",
                data: {
                    "username": username.val(),
                    "password": password.val()
                }
            });
        }
    });

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
