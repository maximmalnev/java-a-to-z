<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>User manager</title>
    <jsp:include page="bootstrap.jsp"/>
</head>
<body>
<jsp:include page="header.jsp"/>
<div class="container">
    <div class="alert alert-danger">
        Access denied!
    </div>
    <form action="${pageContext.request.contextPath}/crud" method="get" accept-charset="utf-8">
        <input class="btn btn-warning" type="submit" value="Return back">
    </form>
</div>
</body>
</html>
