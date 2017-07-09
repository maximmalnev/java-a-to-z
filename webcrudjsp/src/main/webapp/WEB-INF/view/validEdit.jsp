<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>User manager</title>
    <jsp:include page="bootstrap.jsp"/>
</head>
<body>
<jsp:include page="header.jsp"/>
<div class="container">
    <div class="form-group">
        <form action="${pageContext.request.contextPath}/edit" method="post" accept-charset="utf-8">
            <input type="hidden" name="id" value="${param.id}">
            <label>
                ID:
                <input class="form-control" type="text" disabled value="${requestScope.user.id}">
            </label>
            <br>
            <label>
                Login:
                <input class="form-control" type="text" name="login" value="${requestScope.user.login}">
            </label>
            <br>
            <label>
                Password:
                <input class="form-control" type="text" name="password" value="${requestScope.user.password}">
            </label>
            <br>
            <label>
                Name:
                <input class="form-control" type="text" name="name" value="${requestScope.user.name}">
            </label>
            <br>
            <label>
                Email:
                <input class="form-control" type="email" name="email" value="${requestScope.user.email}">
            </label>
            <br>
            <c:choose>
                <c:when test="${sessionScope.user.role eq 'ADMIN'}">
                    <label>
                        Role:
                        <select class="form-control" style="width: 100px" name="role">
                            <option value="admin">Admin</option>
                            <option value="user">User</option>
                        </select>
                    </label>
                </c:when>
                <c:otherwise>
                    <input type="hidden" name="role" value="${requestScope.user.role}">
                    <label>
                        Role:
                        <input class="form-control" type="text" disabled value="${requestScope.user.role}">
                    </label>
                </c:otherwise>
            </c:choose>
            <br>
            <input class="btn btn-success" type="submit" value="Edit user">
        </form>
        <form action="${pageContext.request.contextPath}/crud" method="get" accept-charset="utf-8">
            <input class="btn btn-warning" type="submit" value="Return back">
        </form>
    </div>
</div>
</body>
</html>
