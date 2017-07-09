<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <jsp:include page="parts/headCss.jsp"/>
    <jsp:include page="parts/headJS.jsp"/>
</head>
<body>
<jsp:include page="parts/nav.jsp"/>
<div class="container">
    <div class="user-form" style="width: 50%;margin: 0 auto;">
        <c:if test="${not empty SPRING_SECURITY_LAST_EXCEPTION}">
        <span style="color: red; ">
            Пожалуйста, проверьте правильность написания логина и пароля!
        </span>
        </c:if>
        <form accept-charset="UTF-8"
              action="${pageContext.request.contextPath}/login" method="post">
            <label>Логин: </label>
            <input class="form-control" type="text" name="username"
                   placeholder="Логин" autofocus>
            <label>Пароль: </label>
            <input class="form-control" type="password" name="password"
                   placeholder="Пароль">
            <button class="btn btn-success" type="submit">Войти</button>
        </form>
    </div>
</div>
</body>
</html>