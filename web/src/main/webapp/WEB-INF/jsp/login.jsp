<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Login</title>
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
    margin: 2pt;
  }

</style>
<body>
<%@include file="header.jsp" %>
<form action="${pageContext.request.contextPath}/login" method="post">
    <div class="block">
        <div class="field">
            <label for="login">Login:</label>
            <input type="text" name="login" id="login" value="${param.login}" required>
        </div>
        <div class="field">
            <label for="password">Password:</label>
            <input type="password" name="password" id="password" required>
        </div>
        <div class="button">
            <button type="submit">Login</button>
            <a href="${pageContext.request.contextPath}/registration">
                <button type="button">Register</button>
            </a>
        </div>
    </div>
    <br><br><br><br><br>

    <c:if test="${param.fail !=null}">
        <div style="color: red">
            <span>Login or password incorrect</span>
        </div>
    </c:if>
</form>
<%@include file="footer.jsp" %>
</body>
</html>
