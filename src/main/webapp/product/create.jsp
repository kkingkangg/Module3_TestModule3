<%--
  Created by IntelliJ IDEA.
  User: Nghia
  Date: 11/12/2020
  Time: 11:06 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Create new Product</title>
    <style>
        .message{
            color: green;
        }
    </style>
</head>
<body>

<h1>Create new Product</h1>
<p>
    <c:if test = '${requestScope["message"] != null}'>
        <span class = "message">${requestScope["message"]}</span>
    </c:if>
</p>
<form method="post">
    <fieldset>
        <legend>Product information</legend>
        <table>
            <tr>
                <td>Name: </td>
                <td><input type="text" name="name"></td>
            </tr>
            <tr>
                <td>Price: </td>
                <td><input type="text" name="price"></td>
            </tr>
            <tr>
                <td>Quantity: </td>
                <td><input type="text" name="quantity"></td>
            </tr>
            <tr>
                <td>Color: </td>
                <td><input type="text" name="color"></td>
            </tr>
            <tr>
                <td>Decscription: </td>
                <td><input type="text" name="desc"></td>
            </tr>
            <tr>
                <td>Category: </td>
                <td><input type="text" name="categoryName"></td>
            </tr>
        </table>
        <input type="submit" value="Create">
    </fieldset>
    <p>
        <a href="/productServlet">Back to Product list</a>
    </p>
</form>
</body>
</html>
