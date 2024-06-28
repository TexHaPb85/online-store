<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Item Detail</title>
</head>
<body>
<h1>Item Detail</h1>
<p>Item ID: ${item.itemId}</p>
<p>Item Name: ${item.itemName}</p>
<p>Description: ${item.description}</p>
<!-- Add other item details -->
<form action="${pageContext.request.contextPath}/items/${item.itemId}" method="post">
    <!-- Add input fields for updating item -->
    <button type="submit">Update Item</button>
</form>
<form action="${pageContext.request.contextPath}/items/${item.itemId}" method="post">
    <input type="hidden" name="_method" value="delete">
    <button type="submit">Delete Item</button>
</form>
</body>
</html>
