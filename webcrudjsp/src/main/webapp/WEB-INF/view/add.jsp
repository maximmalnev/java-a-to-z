<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>User manager</title>
    <jsp:include page="bootstrap.jsp"/>
    <script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
    <script>
        function isValidate() {
            return Boolean(checkInput("login") &
                    checkInput("password") &
                    checkInput("name"));
        }

        function checkInput(inputName) {
            var valid = true;
            var element = $("form input[name=" + inputName + "]");
            if (element.val() == "") {
                element.after("Заполните это поле!");
                valid = false;
            }
            return valid;
        }
    </script>
</head>
<body>
<jsp:include page="header.jsp"/>
<div class="container">
    <c:if test="${not empty requestScope.message}">
        <div class="alert alert-success">
            User was created!
        </div>
    </c:if>
    <div class="form-group">
        <form action="${pageContext.request.contextPath}/add" method="post" accept-charset="UTF-8"
              onsubmit="return isValidate();">
            <label>
                Login:
                <input class="form-control" type="text" name="login">
            </label>
            <br>
            <label>
                Password:
                <input class="form-control" type="text" name="password">
            </label>
            <br>
            <label>
                Name:
                <input class="form-control" type="text" name="name">
            </label>
            <br>
            <label>
                Email:
                <input class="form-control" type="text" name="email">
            </label>
            <br>
            <label for="role">
                Role:
            </label>

            <select class="form-control" style="width: auto;" name="role" id="role">
                <option value="admin">Admin</option>
                <option value="user">User</option>
            </select>
            <br>
            <input class="btn btn-success" type="submit" value="Create user">
        </form>

        <form action="${pageContext.request.contextPath}/crud" method="get" accept-charset="utf-8">
            <input class="btn btn-warning" type="submit" value="Return back">
        </form>
    </div>
</div>
</body>
</html>
