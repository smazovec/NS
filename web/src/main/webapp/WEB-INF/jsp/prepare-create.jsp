<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Create folder</title>
</head>
<body>
<%@include file="header.jsp" %>
<form action="${pageContext.request.contextPath}/create" method="post">
    <h3>Create new folder into: ${param.path}</h3>
    <label for="folderName">Folder name:
        <input type="hidden" name="path" value="${param.path}">
        <input type="text" name="folderName" id="folderName" value="New name" required>
    </label>
    <button type="submit" name="path" value="${param.path}">Create</button>
    <a href="${pageContext.request.contextPath}/storage">
        <button type="button">Cancel</button>
    </a>
    <c:if test="${param.fail !=null}">
        <div style="color: red">
            <span>Folder name incorrect</span>
        </div>
    </c:if>
</form>
<%@include file="footer.jsp" %>
</body>
</html>
