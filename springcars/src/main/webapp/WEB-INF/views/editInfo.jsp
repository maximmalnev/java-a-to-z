<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<jsp:useBean id="ad" scope="request" type="ru.kovladimir.springcars.persistence.models.Ad"/>
<html>
<head>
    <title>User manager</title>
    <jsp:include page="parts/headCss.jsp"/>
    <jsp:include page="parts/headJS.jsp"/>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-filestyle/1.2.1/bootstrap-filestyle.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/edit.js"></script>
</head>
<body>
<jsp:include page="parts/nav.jsp"/>
<div id="info" class="container">
    <div id="main" class="user-form" style="width: 50%;margin: 0 auto;">
        <form id="form" accept-charset="UTF-8" enctype="multipart/form-data">
            <label>Марка: </label>
            <input id="producer-field" class="form-control" type="text"
                   placeholder="Марка" value="${ad.car.producer}" name="producer"
                   autofocus>
            <label>Модель: </label>
            <input id="model-field" class="form-control" type="text"
                   placeholder="Model" value="${ad.car.model}" name="model">
            <label>Год выпуска: </label>
            <input id="year-field" class="form-control" type="text"
                   placeholder="Год выпуска" value="${ad.car.year}" name="year">
            <label>Пробег: </label>
            <input id="mileage-field" class="form-control" type="text"
                   placeholder="Пробег" value="${ad.car.mileage}" name="mileage">
            <label>Двигатель: </label>
            <input id="engine-field" class="form-control" type="text"
                   placeholder="Двигатель" value="${ad.car.engine}" name="engine">
            <label>Цена: </label>
            <input id="price-field" class="form-control" type="text"
                   placeholder="Цена" value="${ad.car.price}" name="price">
            <label>Описание: </label>
            <textarea id="description-field" class="form-control"
                      placeholder="Описание" rows="5" name="description">${ad.description}</textarea>
            <label>Фото: </label>
            <input type="file" class="filestyle" data-buttonText="Сменить фото"
                   name="photo-file">
        </form>
        <button id="edit-button" class="btn btn-success">Обновить информацию</button>
    </div>
</div>
</body>
</html>