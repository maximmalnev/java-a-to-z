<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>User manager</title>
    <jsp:include page="bootstrap.jsp"/>
</head>
<body>
<jsp:include page="header.jsp"/>
<div class="container">
    <form method="post">
        <table class="table table-striped table-bordered" style="border: 1px solid black;" border="1" cellpadding="1"
               cellspacing="1">
            <thead>
            <tr>
                <th>ID</th>
                <th>Login</th>
                <c:if test="${sessionScope.user.role eq 'ADMIN'}">
                    <th>Password</th>
                </c:if>
                <th>Name</th>
                <th>Email</th>
                <th>Creation Date</th>
                <th>Role</th>
                <c:if test="${sessionScope.user.role eq 'ADMIN'}">
                    <th>Choose</th>
                </c:if>
            </tr>
            </thead>
            <jsp:useBean id="users" scope="request" type="java.util.List"/>
            <c:forEach items="${users}" var="user">
                <tr>
                    <td><c:out value="${user.id}"/></td>
                    <td><c:out value="${user.login}"/></td>
                    <c:if test="${sessionScope.user.role eq 'ADMIN'}">
                        <td><c:out value="${user.password}"/></td>
                    </c:if>
                    <td><c:out value="${user.name}"/></td>
                    <td><c:out value="${user.email}"/></td>
                    <td><fmt:formatDate value="${user.createDate.time}" pattern="dd-MM-yyyy HH:mm:ss"/></td>
                    <td><c:out value="${user.role}"/></td>
                    <c:if test="${sessionScope.user.role eq 'ADMIN'}">
                        <td>
                            <input type="radio" name="id" class="radio" value="${user.id}">
                        </td>
                    </c:if>
                </tr>
            </c:forEach>
        </table>
        <br>
        <c:if test="${sessionScope.user.role eq 'ADMIN'}">
            <input class="btn btn-default" type="submit" value="Create user"
                   formaction="${pageContext.request.contextPath}/add"
                   formmethod="get">
        </c:if>
        <c:choose>
            <c:when test="${sessionScope.user.role eq 'ADMIN'}">
                <input class="btn btn-default" type="submit" value="Edit user"
                       formaction="${pageContext.request.contextPath}/edit"
                       formmethod="get">
            </c:when>
            <c:when test="${sessionScope.user.role eq 'USER'}">
                <input type="hidden" name="id" value="${sessionScope.user.id}">
                <input class="btn btn-default" type="submit" value="Edit my info"
                       formaction="${pageContext.request.contextPath}/edit"
                       formmethod="get">
            </c:when>
        </c:choose>
        <c:if test="${sessionScope.user.role eq 'ADMIN'}">
            <input class="btn btn-default" type="submit" value="Delete user"
                   formaction="${pageContext.request.contextPath}/delete">
        </c:if>
    </form>
</div>
</body>
</html>
