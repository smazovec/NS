<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<style>
  .header div {
    display: inline-block;
  }
</style>
<div class="header">
    <div class="title">
        <h1>Сетевое хранилище</h1>
        <p>Проектная работа курса "Разработка сетевого хранилища на Java"</p>
    </div>
    <div class="logout">
        <c:if test="${not empty sessionScope.user}">
            <form action="${pageContext.request.contextPath}/logout" method="post">
                <button type="submit">Logout</button>
            </form>
        </c:if>
    </div>
    <hr>
</div>
