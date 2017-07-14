<%--
  Created by IntelliJ IDEA.
  User: Pavel
  Date: 29.06.2017
  Time: 12:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Найденные контакты</title>
    <link href="/resources/css/bootstrap.css" rel="stylesheet"/>
    <link href="/resources/css/bootstrap-responsive.css" rel="stylesheet"/>
</head>
<jsp:include page="jpsf/mainMenu.jsp"/>
<body>
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
    <c:if test="${!empty contactsSearching}">
        <c:forEach items="${contactsSearching}" var="contact">
            <tr>
                <td><a href="/contacts?action=aboutContact&contactId=${contact.contactId}">${contact.lastName}</a></td>
                <td>${contact.firstName}</td>
                <td>${contact.email}</td>
                <td>${contact.gender}</td>
                <td>${contact.date}</td>
                <td>${contact.phone}</td>
                <td>${contact.profession.profession}</td>
                <td>${contact.zip.city}</td>
                <td>${contact.zip.state}</td>
            </tr>
        </c:forEach>
    </c:if>
</table>
</body>
</html>
