<%--
  Created by IntelliJ IDEA.
  User: Pavel
  Date: 27.06.2017
  Time: 20:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Подробнее</title>
    <link href="/resources/css/bootstrap.css" rel="stylesheet"/>
    <link href="/resources/css/bootstrap-responsive.css" rel="stylesheet"/>
</head>
<body>
<jsp:include page="jpsf/mainMenu.jsp"/>
<table class="table">
    <tr>
        <th>Фамилия</th>
        <th>Имя</th>
        <th>Email</th>
        <th>Пол</th>
        <th>День рождения</th>
        <th>Телефон</th>
        <th>Профессия</th>
        <th>Город</th>
        <th>Область</th>
    </tr>
    <tr>
        <td>${contact.lastName}</td>
        <td>${contact.firstName}</td>
        <td>${contact.email}</td>
        <td>${contact.gender}</td>
        <td>${contact.date}</td>
        <td>${contact.phone}</td>
        <td>${contact.profession.profession}</td>
        <td>${contact.zip.city}</td>
        <td>${contact.zip.state}</td>
    </tr>
</table>
<a href="/contacts?action=contactsList" class="btn">Перейти к списку контактов</a>
</body>
</html>
