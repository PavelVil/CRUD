
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Профессии</title>
    <link href="/resources/css/bootstrap.css" rel="stylesheet"/>
    <link href="/resources/css/bootstrap-responsive.css" rel="stylesheet"/>
</head>
<body>
<jsp:include page="jpsf/mainMenu.jsp"/>
<%--Prof table--%>
<table class="table">
    <tr>
        <th>Профессия</th>
    </tr>
    <c:if test="${!empty professionList}">
        <c:forEach items="${professionList}" var="profession">
            <tr>
                <td>${profession.profession}</td>
                <td><a href="/professions?action=delete&profId=${profession.id}" class="btn btn-danger">Удалить</a> </td>
                <td><a href="/professions?action=edit&profId=${profession.id}" class="btn btn-info">Изменить</a> </td>
            </tr>
        </c:forEach>
    </c:if>
</table>
<%--Add/edite prof--%>
<br>
<c:set var="professionExist" value="${profession ne null}"/>
<form action="/professions?action=addProf" method="post" class="form-inline">
    <c:choose>
        <c:when test="${professionExist}}"><h3>Редактировать</h3></c:when>
        <c:otherwise><h3>Добавить</h3></c:otherwise>
    </c:choose>
    <c:if test="${professionExist}">
        <input type="hidden" name="profId" value="${profession.id}">
    </c:if>
    <div class="form-group">
    Название: <input type="text" name="profession" <c:if test="${professionExist}">value="${profession.profession}"</c:if> >
    </div>
    <div class="form-group">
    <input type="submit" class="btn btn-default"
    <c:choose>
           <c:when test="${professionExist}">value="Редактировать"</c:when>
           <c:otherwise>value="Добавить"</c:otherwise>
    </c:choose>>
    </div>
</form>
</body>
</html>
