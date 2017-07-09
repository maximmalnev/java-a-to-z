<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<jsp:useBean id="currentUser" scope="request" type="java.lang.String"/>
<jsp:useBean id="ad" scope="request" type="ru.kovladimir.springcars.persistence.models.Ad"/>

<html>
<head>
    <title>User manager</title>
    <jsp:include page="parts/headCss.jsp"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/info.css">
    <jsp:include page="parts/headJS.jsp"/>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/delete.js" charset="utf-8"></script>
</head>
<body>
<jsp:include page="parts/nav.jsp"/>
<div class="container">
    <div id="info">
        <sec:authorize access="isAuthenticated()">
            <c:if test="${currentUser eq ad.user.login}">
                <div id="buttons-id" class="buttons">
                    <button onclick="location.href = '${pageContext.request.contextPath}/id${ad.id}/edit'"
                            class="btn btn-warning" type="submit">
                        Изменить информацию
                    </button>
                    <button class="btn btn-danger" data-toggle="modal" data-target="#myModal">
                        Снять с продажи
                    </button>
                </div>
            </c:if>
        </sec:authorize>
        <div id="myModal" class="modal fade" role="dialog">
            <div class="modal-dialog">
                <!-- Modal content-->
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                        <h4 class="modal-title">Подтверждение удаления</h4>
                    </div>
                    <div class="modal-body">
                        <p>Вы дейсвительно хотите удалить объявление?</p>
                    </div>
                    <div class="modal-footer">
                        <button id="delete-button" type="button" class="btn btn-success" data-dismiss="modal">Да,
                            удалить
                        </button>
                        <button type="button" class="btn btn-danger" data-dismiss="modal">Отмена</button>
                    </div>
                </div>
            </div>
        </div>
        <div id="car-info" class="car">
            <div class="title">
            <span>
                <c:out value="${ad.car.producer} ${ad.car.model}, ${ad.car.year}"/>
            </span>
                <br>
                <c:out value="Дата создания: "/>
                <fmt:formatDate value="${ad.createDate}" pattern="HH:mm dd.MM.yyyy"/>
            </div>
            <div class="info">
                <div class="photo">
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
                </div>
                <div class="description-block">
                    <div class="properties">
                        <ul class="list-group">
                            <li class="list-group-item"><b>Характеристики</b>:</li>
                            <li class="list-group-item"><c:out value="Марка: ${ad.car.producer}"/></li>
                            <li class="list-group-item"><c:out value="Модель: ${ad.car.model}"/></li>
                            <li class="list-group-item"><c:out value="Год выпуска: ${ad.car.year}"/></li>
                            <li class="list-group-item">
                                Пробег:
                                <fmt:formatNumber value="${ad.car.mileage}" pattern="#,###"/>
                                км.
                            </li>
                            <li class="list-group-item">
                                <c:out value="Двигатель: ${ad.car.engine}"/>
                                <c:out value=" л.с."/>
                            </li>
                            <li class="list-group-item">
                                <c:out value="Цена: "/>
                                <fmt:formatNumber value="${ad.car.price}" pattern="#,###"/>
                                <c:out value=" руб."/>
                            </li>
                        </ul>
                    </div>
                    <div class="properties">
                        <ul class="list-group">
                            <li class="list-group-item"><b>Владелец</b>:</li>
                            <li class="list-group-item"><c:out value="Имя: ${ad.user.name}"/></li>
                            <c:set var="phone" value="${ad.user.phone}"/>
                            <li class="list-group-item"><c:out
                                    value="Телефон: +7 (${fn:substring(phone, 0, 3)}) ${fn:substring(phone, 3, 6)}
                                ${fn:substring(phone, 6, 8)} ${fn:substring(phone, 8, fn:length(phone))}"/></li>
                            <li class="list-group-item"><c:out value="E-mail: ${ad.user.email}"/></li>
                        </ul>
                    </div>
                    <div class="description">
                        <p><c:out value="${ad.description}"/></p>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>