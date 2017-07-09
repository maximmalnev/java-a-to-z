<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <title>User manager</title>
    <jsp:include page="parts/headCss.jsp"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/mainPage.css">
    <jsp:include page="parts/headJS.jsp"/>
</head>
<body>
<jsp:include page="parts/nav.jsp"/>
<div class="container">
    Что-то пошло не так!
</div>
</body>
</html>