<%-- 
    Document   : create
    Created on : Oct 29, 2025, 10:34:55 PM
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
        <h2>Create Leave Request</h2>
        <form action="request/create" method="POST">
            <label id="createdByLabel">Created by: ${sessionScope.auth.username}</label> <br>
            <label>Role: 
                <c:forEach items="${roles}" var="role" varStatus="status">
                    ${role.name}<c:if test="${!status.last}">, </c:if>
                </c:forEach>
            </label> <br>

            <label id="departmentLabel">Department: ${dname}</label> <br>
            
            <label id="fromDateLabel">From (date):</label> 
            <input type="date" name="fromDate" id="fromDate"> <br>
            
            <label id="toDateLabel">To (date):</label> 
            <input type="date" name="toDate" id="toDate"> <br>
            
            <label id="reasonLabel">Reason:</label> <br>
            <textarea id="reason" name="reason" rows="5" cols="50"></textarea> <br>
            <input type="submit" value="Send" id="btnCreate">
        </form>
    </body>
</html>
