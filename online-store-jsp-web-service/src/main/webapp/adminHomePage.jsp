<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Admin title</title>
</head>
<body>
    <h2>Home page for admin : ${user.name}</h2>
    <jsp:include page="logoutButton.jsp" />
</body>
</html>