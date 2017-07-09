<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>User manager</title>
    <jsp:include page="bootstrap.jsp"/>
    <style>
        .sign-block {
            max-width: 330px;
            padding: 15px;
            margin: 10% auto 0;
        }
    </style>
</head>
<body>


<div class="container-fluid">
    <div class="form-group sign-block">
        <c:if test="${not empty requestScope.message}">
            <div class="alert alert-danger">
                Not valid pair login-password!
            </div>
        </c:if>
        <form action="${pageContext.request.contextPath}/signin" method="post" accept-charset="UTF-8">
            <h2>Sign in</h2>
            <label>
                Login
                <input class="form-control" type="text" name="login" placeholder="Login" autofocus>
            </label>
            <br>
            <label>
                Password
                <input class="form-control" type="password" name="password" placeholder="Password">
            </label>
            <br>
            <button type="submit" class="btn btn-primary">Sign in</button>
        </form>
    </div>
</div>


</body>
</html>
