<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%--
  Created by IntelliJ IDEA.
  User: developer
  Date: 21.06.18
  Time: 20:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Categories</title>
</head>
<body>
    <c:forEach var="c" items="${categories}">
        <h1><a href="<c:url value="/servlet/category?c_id=${c.id}"/>"></a></h1>
    </c:forEach>
</body>
</html>
