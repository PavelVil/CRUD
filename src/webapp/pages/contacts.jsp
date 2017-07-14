
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Contacts</title>
    <link href="/resources/css/bootstrap.css" rel="stylesheet"/>
    <link href="/resources/css/bootstrap-responsive.css" rel="stylesheet"/>
</head>
<body>
<jsp:include page="jpsf/mainMenu.jsp"/>
<%--Searching form--%>
<form action="/contacts?action=search" method="post" class="form-inline">
    <div class="form-group">
    Поиск <input type="text" name="searching" class="form-control">
    </div>
    <div class="form-group">
    <select name="searchOn" class="form-control">
        <option value="name">По имени</option>
        <option value="surname">По фамилии</option>
    </select>
    </div>
    <div class="form-group">
    <input type="submit" value="Искать" class="btn btn-default">
    <br>
    <c:if test="${!empty emptySearch}">
        <p>${emptySearch}</p>
    </c:if>
    </div>
</form>
<%--Contact table--%>
<br>
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
    <c:if test="${!empty contacts}">
        <c:forEach items="${contacts}" var="contact">
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
                <td><a href="/contacts?action=delete&contactId=${contact.contactId}" class="btn btn-danger">Удалить</a> </td>
                <td><a href="/contacts?action=edit&contactId=${contact.contactId}" class="btn btn-info">Изменить</a> </td>
            </tr>
        </c:forEach>
    </c:if>
</table>
<%--Add/Edit form--%>
<c:set var="contactExist" value="${contact ne null}"/>
<form action="/contacts?action=add" method="post">
    <c:if test="${contactExist}">
        <input type="hidden" name="contactId" value="${contact.contactId}">
    </c:if>
    <c:choose>
        <c:when test="${contactExist}">
            <h3>Редактировать контакт</h3>
        </c:when>
        <c:otherwise>
            <h3>Добавить новый контакт</h3>
        </c:otherwise>
    </c:choose>
    <table class="table table-condensed">
        <tr>
            <td>
                Имя
            <input type="text" name="firstName" <c:if test="${contactExist}">value="${contact.firstName}" </c:if>
        </td>
        </tr>
        <tr>
            <td>
                Фамилия
                <input type="text" name="lastName" <c:if test="${contactExist}">value="${contact.lastName}" </c:if>
            </td>
        </tr>
        <tr>
            <td>
                Email
                <input type="email" name="email" <c:if test="${contactExist}">value="${contact.email}" </c:if>
            </td>
        </tr>
        <tr>
            <td>
                Пол
                <input type="text" name="gender" <c:if test="${contactExist}">value="${contact.gender}" </c:if>
            </td>
        </tr>
        <tr>
            <td>
                Телефон
                <input type="text" name="phone" <c:if test="${contactExist}">value="${contact.phone}" </c:if>
            </td>
        </tr>
        <tr>
            <td>
                День рождения
                <input type="date" name="date" <c:if test="${contactExist}">value="${contact.date}" </c:if>
            </td>
        </tr>
        <tr>
        <td>
                Профессия: <c:if test="${contactExist}">${contact.profession.profession}</c:if>
        <select name="prof">
            <c:forEach items="${professions}" var="profession">
                <option>${profession.profession}</option>
            </c:forEach>
        </select>
        </td>
        </tr>
        <tr>
            <td>
                Город: <c:if test="${contactExist}">${contact.zip.city}</c:if>
            <select name="city">
            <c:forEach items="${zips}" var="zip">
                <option>${zip.city}</option>
            </c:forEach>
            </select>
            </td>
        </tr>
    </table>
    <input type="submit" class="btn btn-default"
    <c:choose>
           <c:when test="${contactExist}">value="Редактировать" </c:when>
           <c:otherwise>value="Добавить" </c:otherwise>
    </c:choose>>
</form>
</body>
</html>
