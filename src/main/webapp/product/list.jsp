<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Tien Dung
  Date: 12/2/2021
  Time: 10:56 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<style>
    table, th, td{
        border:1px solid #d21d1d;
    }
    table{
        border-collapse:collapse;
        width: 400px;
    }

</style>
<body>
<center>
    <h1> List Product</h1>
    <br>
    <p><a href="/products?action=create"> Create product</a> </p>
    <br>
    <form action="/products">
        <input type="text" name="key" placeholder="Enter name you want find?">
        <button style="background: darkorchid"> find</button>
    </form>
    <form method="get">
        <input type="hidden" name="action" value="sort">
        <button style="background: darkorchid"> Sort</button>
    </form>
    <br>
    <table>
        <tr style="background: pink">
            <td > Name</td>
            <td> Price</td>
            <td> Edit</td>
            <td> Delete</td>
        </tr>
        <c:forEach items="${productList}" var="product">
            <tr>
                <td>  <input style="border: none" type="text" name="name" value="${product.getName()}"></td>
                <td>  <input style="border: none" type="text" name="price" value="${product.getPrice()}"></td>

               <td><a href="/products?action=edit&id=${product.getId()}"> Edit</a></td>

                <td> <a href="/products?action=delete&id=${product.id}" onclick="if (confirm('Delete selected item?')){return true;}else{event.stopPropagation(); event.preventDefault();};" title="Link Title">
                    delete
                </a> </td>
            </tr>
        </c:forEach>
    </table>
</center>
</body>
</html>
