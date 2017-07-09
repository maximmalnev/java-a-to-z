<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<nav class="nav navbar-default">
    <div class="container">
        <div>
            <div class="navbar-text">
                Welcome, <c:out value="${sessionScope.user.name}"/>!
            </div>
            <div class="navbar-form navbar-right">
                <form action="${pageContext.request.contextPath}/logout" method="get" accept-charset="UTF-8">
                    <input class="btn btn-danger" type="submit" value="Log out">
                </form>
            </div>
        </div>
    </div>
</nav>
<br>
<br>