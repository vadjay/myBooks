<%--
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page session="false" %>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>
        <c:if test="${title}=='add'">
            Добавление книги
        </c:if>
        <c:if test="${title}=='edit'">
            Редактирование книги
        </c:if>
    </title>
</head>
<body>

    <form:form action="/submit" method="post" modelAttribute="book">
        <table>
                <form:hidden path="id"/>

                <form:hidden path="readAlready" value="false"/>

                <tr>
                    <td>Название</td>
                    <td><form:input path="title"/></td>
                </tr>
                <tr>
                    <td>Описание</td>
                    <td><form:textarea path="description"/></td>
                </tr>
                <c:if test="${empty book.title}">
                    <tr>
                        <td>Автор</td>
                        <td><form:input path="author"/></td>
                    </tr>
                </c:if>
                <c:if test="${!empty book.title}">
                    <form:hidden path="author"/>
                </c:if>
                <tr>
                    <td>ISBN</td>
                    <td><form:input path="isbn"/></td>
                </tr>
                <tr>
                    <td>Год издания</td>
                    <td><form:input path="printYear"/></td>
                </tr>
                <tr>
                    <td></td>
                </tr>

            </table>
        <input type="submit" value="Сохранить"/>
    </form:form>

</body>
</html>
