<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Items</title>
</head>
<body>
    <table>
        <tr>
            <th>Item ID</th>
            <th>Item Name</th>
            <!-- Add other headers -->
        </tr>
        <tbody>
            <c:forEach var="item" items="${goods}">
                <tr>
                    <td><a href="${pageContext.request.contextPath}/items/${item.itemId}">ID: ${item.itemId}</a></td>
                    <td>${item.itemName}</td>
                    <!-- Add other columns -->
                </tr>
            </c:forEach>
        </tbody>
    </table>

    <form action="${pageContext.request.contextPath}/items/" method="post">
        <label>Item Name: <input type="text" name="itemName"></label><br>
        <label>Description: <input type="text" name="description"></label><br>
        <button type="submit">Add Item</button>
    </form>
</body>
</html>
