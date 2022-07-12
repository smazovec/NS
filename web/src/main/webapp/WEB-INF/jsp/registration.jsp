<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>User registration</title>
</head>
<style>
  .field {
    clear: both;
    text-align: right;
    line-height: 25px;
  }

  label {
    float: left;
    padding-right: 10px;
  }

  .block {
    float: left;
  }

  .button {
    margin: 2pt 2pt 2pt 53px;

  }
</style>
<body>
<%@include file="header.jsp" %>
<form action="${pageContext.request.contextPath}/registration" method="post">
    <div class="block">
        <div class="field">
            <label for="name"> Name:</label>
            <input type="text" name="name" id="name">
        </div>
        <div class="field">
            <label for="login"> Login:</label>
            <input type="text" name="login" id="login">
        </div>
        <div class="field">
            <label for="password"> Pass:</label>
            <input type="password" name="password" id="password">
        </div>
        <div class="button">
            <button type="submit">Registration</button>
        </div>
    </div>
    <br><br><br><br><br><br>
    <c:if test="${not empty requestScope.errors}">
    <div style="color: red">
        <c:forEach var="error" items="${requestScope.errors}">
            <span>${error.message}</span><br>
        </c:forEach>
        </c:if>
    </div>
</form>
<%@include file="footer.jsp" %>
</body>
</html>
