<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<jsp:useBean id="ads" scope="request" type="java.util.List"/>
<fmt:setLocale value="ru_RU" scope="session"/>

<html>
<head>
    <title>User manager</title>
    <jsp:include page="parts/headCss.jsp"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/mainPage.css">
    <jsp:include page="parts/headJS.jsp"/>
    <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/datejs/1.0/date.min.js"></script>
</head>
<body>
<jsp:include page="parts/nav.jsp"/>
<div class="container">
    <div class="ads">
        <c:forEach var="ad" items="${ads}">
            <jsp:useBean id="ad" type="ru.kovladimir.springcars.persistence.models.Ad"/>
            <div class="ad">
                <div class='car-photo'>
                    <c:choose>
                        <c:when test="${empty ad.photo}">
                            <img class='img-rounded'
                                 src="${pageContext.request.contextPath}/images/default.jpg"/>
                        </c:when>
                        <c:otherwise>
                            <img class='img-rounded'
                                 src="${pageContext.request.contextPath}/images/${ad.photo}"/>
                        </c:otherwise>
                    </c:choose>
                </div>
                <div class='car-description'>
                    <div><a href='${pageContext.request.contextPath}/id${ad.id}'>
                        <b>${ad.car.producer} ${ad.car.model}, ${ad.car.year}</b>
                    </a>
                    </div>
                    <div>${ad.car.price} руб.</div>
                    <div>${ad.car.mileage} км, ${ad.car.engine} л.с.</div>
                    <div class='date-block'><fmt:formatDate value="${ad.createDate}" pattern="dd MMMM HH:mm"/></div>
                </div>
            </div>
        </c:forEach>
    </div>
</div>
</body>
</html>
