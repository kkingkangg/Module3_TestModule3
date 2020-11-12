<%--
  Created by IntelliJ IDEA.
  User: Nghia
  Date: 11/8/2020
  Time: 9:31 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<a href="/productServlet?action=create">Create new Product</a>

<div>
    <table style="border: solid">
        <tr>
            <td colspan="8" align="center">Product</td>
        </tr>
        <tr>
            <td>ID</td>
            <td>Name</td>
            <td>Price</td>
            <td>Quantity</td>
            <td>Color</td>
            <td>Description</td>
            <td>Category</td>
        </tr>
        <tr>
            <c:forEach items='${requestScope["productList"]}' var="product" varStatus="loop">
        <tr>
            <td>${product.getProductId()}</td>
            <td>${product.getProductName()}</td>
            <td>${product.getPrice()}</td>
            <td>${product.getQuantity()}</td>
            <td>${product.getColor()}</td>
            <td>${product.getDesc()}</td>
            <td>${product.getCategory().getCategoryName()}</td>
            <td><a href="/productServlet?action=edit&id=${product.getProductId()}">Edit</a></td>
            <td><a href="/productServlet?action=delete&id=${product.getProductId()}">Delete</a></td>
        </tr>
            <%--                <td><img src="${item.getImg}" alt="${item.getName}"></td>--%>
        </c:forEach>
        </tr>
    </table>
</div>

<style>
    img {
        width: 80px;
    }
</style>
</body>
</html>
