<%--
  Created by IntelliJ IDEA.
  User: Tien Dung
  Date: 12/2/2021
  Time: 1:59 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<center>
    <h1> Sửa</h1>
    <form method="post">
        <input type="text" name="name" value="${product.getName()}">
        <input type="text" name="price" value="${product.getPrice()}">
        <button> Sửa</button>
    </form>
</center>
</body>
</html>
