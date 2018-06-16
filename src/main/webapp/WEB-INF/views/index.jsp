<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: 1
  Date: 07.06.2018
  Time: 20:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Библиотека</title>
</head>
<body>
    <div class="add">
        <h1>Моя библиотека</h1>
    </div>
    <div class="add">

        <form:form method="get" action="/" modelAttribute="searchAttribute" cssClass="add">
            <table class="books">
                <tr>
                    <td><label>Название </label><input name="title" type="text" value="${searchAttribute.title}"/></td>
                    <td><label>Автор </label><input name="author" type="text" value="${searchAttribute.author}"/></td>
                    <td><label>ISBN </label></label><input name="isbn" type="text" value="${searchAttribute.isbn}"/></td>

                    <td>
                        <input type="submit" name="action" value="Найти" width="200"/>
                        <input type="submit" name="action" value="Очистить"/>
                    </td>
                </tr>
                <tr></tr>
                <tr>

                    <td>

                    </td>
                </tr>
            </table>
        </form:form>
    </div>

    <div class="add">
        <br/>
        <form action="/add">
            <button type="submit">Добавить книгу</button>
        </form>
    </div>

    <c:set var="pageListHolder" value="${pagedListBook}" scope="session" />

    <table class="main-table">
        <tr>
            <td>№</td>
            <th>Название</th>
            <th>Описание</th>
            <th>Автор</th>
            <th>ISBN</th>
            <th>Год издания</th>
            <th colspan="2">Прочитана?</th>
            <th>Изменить</th>
            <th>Удалить</th>
        </tr>
        <c:set var="num" value="${0}"/>
        <c:set var="pagenum" value="${pageListHolder.page*pageListHolder.pageSize}"/>
        <c:forEach items="${pageListHolder.pageList}" var="book">
            <tr>
                <c:set var="num" value="${num+1}"/>
                <td>${num+pagenum}</td>
                <td>${book.title}</td>
                <td class="left-align">${book.description}</td>
                <td>${book.author}</td>
                <td>${book.isbn}</td>
                <td>${book.printYear}</td>
                <td>
                    <c:choose>
                        <c:when test="${book.readAlready}">
                            <p>Да</p>
                            <td></td>
                        </c:when>
                        <c:otherwise>
                            <p>Нет</p>
                            <td><a href="/read/${book.id}"/>Прочитать</td>
                        </c:otherwise>
                    </c:choose>
                </td>
                <td><a href="/edit/${book.id}"/>Новое издание</td>
                <td><a href="/remove/${book.id}"/>Удалить</td>
            </tr>
        </c:forEach>
    </table>

    <c:if test="${pageListHolder.pageCount>1}">
        <div>
            <span style="float:left;">
                <c:choose>
                    <c:when test="${pageListHolder.firstPage}">Prev</c:when>
                    <c:otherwise><a href="/prev">Prev</a></c:otherwise>
                </c:choose>
            </span>
            <span>
                <c:forEach begin="0" end="${pageListHolder.pageCount-1}" varStatus="loop">
                    &nbsp;&nbsp;
                    <c:choose>
                        <c:when test="${loop.index == pageListHolder.page}">${loop.index+1}</c:when>
                        <c:otherwise><a href="/${loop.index+1}">${loop.index+1}</a></c:otherwise>
                    </c:choose>
                    &nbsp;&nbsp;
                </c:forEach>
            </span>
            <span>
                <c:choose>
                    <c:when test="${pageListHolder.lastPage}">Next</c:when>
                    <c:otherwise><a href="/next">Next</a></c:otherwise>
                </c:choose>
            </span>
        </div>
    </c:if>



</body>

</html>
