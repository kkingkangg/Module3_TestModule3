<%--
  Created by IntelliJ IDEA.
  User: Nghia
  Date: 11/12/2020
  Time: 11:20 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Update Product</title>
</head>
<body>

<p>
    <c:if test = '${requestScope["message"] != null}'>
        <span class = "message">${requestScope["message"]}</span>
    </c:if>
</p>
<form method="post">
    <fieldset>
        <legend>Product Information</legend>
        <table>
            <tr>
                <td>ID: </td>
                <td><input type="text" name="Id" value="${requestScope["product"].getProductId()}" disabled></td>
            </tr>
            <tr>
                <td>Name: </td>
                <td><input type="text" name="name" value="${requestScope["product"].getProductName()}"></td>
            </tr>
            <tr>
                <td>Price: </td>
                <td><input type="text" name="price" value="${requestScope["product"].getPrice()}"></td>
            </tr>
            <tr>
                <td>Quantity: </td>
                <td><input type="text" name="quantity" value="${requestScope["product"].getQuantity()}"></td>
            </tr>
            <tr>
                <td>Color: </td>
                <td><input type="text" name="color" value="${requestScope["product"].getColor()}"></td>
            </tr>
            <tr>
                <td>Description: </td>
                <td><input type="text" name="desc" value="${requestScope["product"].getDesc()}"></td>
            </tr>
            <tr>
                <td>Category: </td>
                <td><input type="text" name="categoryName" value="${requestScope["product"].getCategory().getCategoryName()}"></td>
            </tr>

        </table>
        <input type="submit" value="Update Product">
        </table>
    </fieldset>
</form>
<p>
    <a href="/productServlet">Back to Product list</a>
</p>
</body>
</html>
