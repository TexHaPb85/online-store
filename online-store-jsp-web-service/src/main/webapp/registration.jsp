<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Registration Page</title>
</head>
<body>
    <h2>Registration</h2>
    <form action="${pageContext.request.contextPath}/registration" method="post">
        <label for="login">login:</label><br>
        <input type="text" id="login" name="login" required><br><br>

        <label for="name">name:</label><br>
        <input type="name" id="name" name="name" required><br><br>

        <label for="password">Password:</label><br>
        <input type="password" id="password" name="password" required><br><br>

        <input type="submit" value="Register">
    </form>
    <c:if test="${not empty errorMessage}">
        <p style="color:red;">${errorMessage}</p>
    </c:if>
</body>
</html>