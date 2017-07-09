<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<nav class="nav navbar-default">
    <div class="container">
        <div class="navbar-form navbar-left">
            <button onclick="location.href = '${pageContext.request.contextPath}/'"
                    class="btn" type="submit">
                На главную
            </button>
        </div>
        <div class="navbar-form navbar-right">
            <sec:authorize access="isAuthenticated()">
                <button onclick="location.href = '${pageContext.request.contextPath}/add'"
                        class="btn" type="submit">
                    Подать объявление
                </button>
                <button onclick="location.href = '${pageContext.request.contextPath}/logout'"
                        class="btn btn-danger" type="submit">
                    Выйти
                </button>
            </sec:authorize>
            <sec:authorize access="isAnonymous()">
                <button onclick="location.href = '${pageContext.request.contextPath}/login'"
                        class="btn btn-success" type="submit">
                    Войти
                </button>
            </sec:authorize>
        </div>
    </div>
</nav>