<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <title>Network storage</title>
</head>
<style>
  .operation div {
    display: inline-block;
  }

  .hyperlink {
    margin-bottom: 0;
  }

  .hyperlink input {
    border: none;
    padding: 0;
    background: #fff;
    color: #00f;
    cursor: pointer;
  }

  .list {
    margin: 0;
    list-style-type: none;
  }
</style>
<body>
<%@include file="header.jsp" %>
<div>
    <h2>NS:${fn:substringAfter(requestScope.path, sessionScope.userRootPath)}</h2>
    <div class="operation">
        <div class="new">
            <form action="${pageContext.request.contextPath}/prepare-crete" method="post">
                <button type="submit" name="path" value="${requestScope.path}">New folder</button>
            </form>
        </div>
        <div class="upload">
            <form action="${pageContext.request.contextPath}/prepare-upload" method="post">
                <button type="submit" name="path" value="${requestScope.path}">Upload file</button>
            </form>
        </div>
    </div>

    <ul class="list">
        <c:forEach var="file" items="${requestScope.files}">
            <li>
                <c:if test="${file.dir}">
                    <form class="hyperlink" action="${pageContext.request.contextPath}/storage"
                          method="post">
                        <input type="hidden" name="path" value="${file.path}">
                        &#128449 <input type="submit" value="${file.name}">
                    </form>
                </c:if>
                <c:if test="${!file.dir}">
                    <form class="hyperlink" action="${pageContext.request.contextPath}/download"
                          method="post">
                        <input type="hidden" name="path" value="${file.path}">
                        &#128462 <input type="submit" value="${file.name}">
                    </form>
                </c:if>
            </li>
        </c:forEach>
    </ul>

</div>
<%@include file="footer.jsp" %>
</body>
</html>
