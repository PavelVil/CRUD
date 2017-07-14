
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Города</title>
    <link href="/resources/css/bootstrap.css" rel="stylesheet"/>
    <link href="/resources/css/bootstrap-responsive.css" rel="stylesheet"/>
</head>
<body>
<jsp:include page="jpsf/mainMenu.jsp"/>
<%--Zip table--%>
<table class="table">
    <tr>
        <th>Город</th>
        <th>Область</th>
    </tr>
    <c:if test="${!empty zipList}">
        <c:forEach items="${zipList}" var="zip">
            <tr>
                <td>${zip.city}</td>
                <td>${zip.state}</td>
                <td><a href="/zips?action=delete&zipId=${zip.id}" class="btn btn-danger">Удалить</a> </td>
                <td><a href="/zips?action=edit&zipId=${zip.id}" class="btn btn-info">Изменить</a> </td>
            </tr>
        </c:forEach>
    </c:if>
</table>
<%--Add/edite zip--%>
<br>
<c:set var="zipExist" value="${zip ne null}"/>
<form action="/zips?action=addZip" method="post">
<c:if test="${zipExist}">
    <input type="hidden" name="zipId" value="${zip.id}">
</c:if>
    <c:choose>
        <c:when test="${zipExist}">
            <h3>Редактировать</h3>
        </c:when>
        <c:otherwise>
            <h3>Добавить</h3>
        </c:otherwise>
    </c:choose>
    <table class="table table-condensed">
        <tr>
            <td>
    Город:<input type="text" name="city" <c:if test="${zipExist}">value="${zip.city}" </c:if>>
            </td>
        </tr>
        <tr>
            <td>
    Область:<input type="text" name="state" <c:if test="${zipExist}">value="${zip.state}" </c:if>>
            </td>
        </tr>
    </table>
    <input type="submit" class="btn btn-default"
    <c:choose>
            <c:when test="${zipExist}">value="Редактировать"</c:when>
            <c:otherwise>value="Добавить"</c:otherwise>
    </c:choose>>
</form>
</body>
</html>
