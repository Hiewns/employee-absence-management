<%-- 
    Document   : home
    Created on : Oct 29, 2025, 10:49:31 PM
    Author     : ultsu
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Request for leave home</title>
        <link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/home.css">
    </head>
    <body>
        <h1 style="text-align: center">Home</h1>
        <div class="user">
            <div>Welcome ${sessionScope.auth.displayname}</div>
            <a href="${pageContext.request.contextPath}/logout">Logout</a>
        </div>
        <div class="features">
            <c:forEach items="" >
                
            </c:forEach>
        </div>
        <div class="logout">
            
        </div>
    </body>
</html>
