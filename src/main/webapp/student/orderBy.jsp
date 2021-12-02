<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Tien Dung
  Date: 12/1/2021
  Time: 9:37 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<style>
    table, th, td{
        border:1px solid pink;
    }
    table{
        border-collapse:collapse;
    }</style>
<body>
<center>
    <h1 style="color: darkorchid"> Danh Sách</h1>
    <a href="/students?action=create"> thêm học sinh</a>
    <form method="post">
        <input type="hidden" name="action" value="find">
        <input type="text" name="name" placeholder="Nhập tên tìm kiếm">
        <button style="background: aqua"> Tìm kiếm</button>
    </form>
    <form method="get">
        <input type="hidden" name="action" value="orderBy">
        <button style="background: red"> Sắp xếp</button>
    </form>

    <table >
        <tr>
            <td>ID</td>
            <td>Tên</td>
            <td>Địa chỉ</td>
            <td>Số điện thoại</td>
            <td>Trạng thái</td>
            <td> class id</td>
            <td> Xóa</td>
            <td> Sửa</td>
        </tr>
        <c:forEach items="${student}" var="student">
            <tr>
                <td>${student.id} </td>
                <td><a href="/students?action=view&id=${student.id}">${student.name}</a> </td>
                <td> ${student.address}</td>
                <td> ${student.phone}</td>
                <td> ${student.status}</td>
                <td>${student.classId} </td>
                <td><a href="/students?action=delete&id=${student.id}"> Xóa</a></td>
                <td><a href="/students?action=edit&id=${student.id}"> Sửa</a></td>

            </tr>
        </c:forEach>
    </table>
</center>
</body>
</html>
