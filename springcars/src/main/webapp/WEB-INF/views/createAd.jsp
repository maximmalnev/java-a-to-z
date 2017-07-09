<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<html>
<head>
    <title>User manager</title>
    <jsp:include page="parts/headCss.jsp"/>
    <jsp:include page="parts/headJS.jsp"/>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-filestyle/1.2.1/bootstrap-filestyle.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/create.js" charset="utf-8"></script>
</head>
<body>
<jsp:include page="parts/nav.jsp"/>
<div id="info" class="container">
    <div id="main" class="user-form" style="width: 50%;margin: 0 auto;">
        <form id="form" accept-charset="UTF-8" enctype="multipart/form-data">
            <label>Марка: </label>
            <input id="producer-field" class="form-control" type="text"
                   placeholder="Марка" name="producer" autofocus>
            <label>Модель: </label>
            <input id="model-field" class="form-control" type="text"
                   placeholder="Модель" name="model">
            <label>Год выпуска: </label>
            <input id="year-field" class="form-control" type="text"
                   placeholder="Год выпуска" name="year">
            <label>Пробег: </label>
            <input id="mileage-field" class="form-control" type="text"
                   placeholder="Пробег" name="mileage">
            <label>Двигатель: </label>
            <input id="engine-field" class="form-control" type="text"
                   placeholder="Двигатель" name="engine">
            <label>Цена: </label>
            <input id="price-field" class="form-control" type="text"
                   placeholder="Цена" name="price">
            <label>Описание: </label>
            <textarea id="description-field" class="form-control"
                      placeholder="Описание" rows="5"
                      name="description"></textarea>
            <label>Фото: </label>
            <input type="file" class="filestyle" data-buttonText="Загрузить фото"
                   name="photo-file">
        </form>
        <button id="create-button" class="btn btn-success">Подать объявление</button>
    </div>
</div>
</body>
</html>