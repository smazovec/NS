<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Upload file</title>
</head>
<body>
<%@include file="header.jsp" %>
<form action="${pageContext.request.contextPath}/upload" method="post"
      enctype="multipart/form-data">
    <h3>Upload new file into: ${param.path}</h3>
    <label for="fileName">File name:
        <input type="hidden" name="path" value="${param.path}">
        <input type="file" name="fileName" id="fileName" required>
    </label>
    <button type="submit" name="path" value="${param.path}">Upload</button>
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
