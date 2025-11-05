<%-- 
    Document   : list
    Created on : Nov 3, 2025, 9:31:36 PM
    Author     : ultsu
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <table border="1px">
            <tr>
                <th>Created By</th>
                <th>Created Time</th>
                <th>From</th>
                <th>To</th>
                <th>Reason</th>
                <th>Status</th>
                <th>Processed By</th>
                <th>Reviewed Time</th>
            </tr>
            <c:forEach items="${requestScope.requests}" var="rq">
                <tr onclick="window.location.href='../request/review?id=${rq.id}'">
                    <td>${rq.creator.displayname}</td>
                    <td>${rq.createdtime}</td>
                    <td>${rq.from}</td>
                    <td>${rq.to}</td>
                    <td>${rq.reason}</td>
                    <td>
                        <c:choose>
                            <c:when test="${rq.status == 0}">
                                <span style="color: orange;">In Progress</span>
                            </c:when>
                            <c:when test="${rq.status == 1}">
                                <span style="color: green;">Approved</span>
                            </c:when>
                            <c:when test="${rq.status == 2}">
                                <span style="color: red;">Rejected</span>
                            </c:when>
                        </c:choose>
                    </td>
                    <td>${rq.reviewer.displayname}</td>
                    <td>${rq.reviewedtime}</td>
                </tr>
            </c:forEach>
        </table>
    </body>
</html>
