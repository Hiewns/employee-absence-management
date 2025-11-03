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
                <th>rid</th>
                <th>created_by</th>
                <th>created_time</th>
                <th>from</th>
                <th>to</th>
                <th>reason</th>
                <th>status</th>
            </tr>
            <c:forEach items="${requestScope.requests}" var="rq">
                <tr>
                    <td>${rq.id}</td>
                    <td>${rq.createdby}</td>
                    <td>${rq.createdtime}</td>
                    <td>${rq.from}</td>
                    <td>${rq.to}</td>
                    <td>${rq.reason}</td>
                    <td>${rq.status}</td>
                </tr>
            </c:forEach>
        </table>
    </body>
</html>
