<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Tien Dung
  Date: 12/1/2021
  Time: 3:35 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<c:forEach var="student1" items="${student}">
    <p>${student1.getName()}</p>
    <p>${student1.getAddress()}</p>
    <p>${student1.getPhone()}</p>
    <p>${student1.getStatus()}</p>
    <p>${student1.getClassId()}</p>
</c:forEach>

</body>
</html>
