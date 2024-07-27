<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 13.05.2024
  Time: 17:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h2>Hello Buyer ${user.name}</h2>
<jsp:include page="logoutButton.jsp" />
<a href="${contextPath}/items/">See all items</a>
</body>
</html>

