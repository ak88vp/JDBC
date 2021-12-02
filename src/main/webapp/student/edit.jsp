<%--
  Created by IntelliJ IDEA.
  User: Tien Dung
  Date: 12/1/2021
  Time: 2:48 PM
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
    <input type="text" name="name" value="${student.getName()}">
    <input type="text" name="address" value="${student.getAddress()}">
    <input type="text" name="phone" value="${student.getPhone()}">
    <input type="text" name="status" value="${student.getStatus()}">
    <input type="text" name="classId" value="${student.getClassId()}">
    <button> Sửa</button>
</form>
</center>
</body>
</html>
