<%-- 
    Document   : login
    Created on : Oct 18, 2025, 11:09:21 AM
    Author     : sonnt
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
        <style>
            .alert {
                padding: 10px;
                margin-bottom: 15px;
                border-radius: 4px;
            }
            .alert-success {
                background-color: #d4edda;
                color: #155724;
                border: 1px solid #c3e6cb;
            }
            .alert-danger {
                background-color: #f8d7da;
                color: #721c24;
                border: 1px solid #f5c6cb;
            }
        </style>
    </head>
    <body>
        <c:if test="${not empty requestScope.message}">
            <div }">
                ${requestScope.message}
            </div>
        </c:if>
        
        <form action="login" method="POST">
            Username:<input type="text" name="username" id="txtUsername"/> <br/>
            Password:<input type="password" name="password" id="txtPassword"/><br/>
            <input type="submit" id="btnLogin" value="Login"/>
        </form>
    </body>
</html>
