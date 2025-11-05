<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Review Leave Request</title>
    </head>
    <body>
        <h3>Review request</h3>
        <label id="reviewByLabel">Review by: ${sessionScope.auth.displayname}</label> <br>
        <label>Role: 
            <c:choose>
                <c:when test="${not empty reviewerRoles}">
                    <c:forEach items="${reviewerRoles}" var="role" varStatus="status">
                        ${role.name}<c:if test="${!status.last}">, </c:if>
                    </c:forEach>
                </c:when>
                <c:otherwise>No roles assigned</c:otherwise>
            </c:choose>
        </label>
        <br><br>

        <label>Created by: ${creator.displayname}</label> <br>
        <label>Role: 
            <c:choose>
                <c:when test="${not empty creatorRoles}">
                    <c:forEach items="${creatorRoles}" var="role" varStatus="status">
                        ${role.name}<c:if test="${!status.last}">, </c:if>
                    </c:forEach>
                </c:when>
                <c:otherwise>No roles assigned</c:otherwise>
            </c:choose>
        </label> <br>
        <label>Department: ${creatorDept.name}</label> <br>
        <label>From (date): ${request.from}</label> <br>
        <label>To (date): ${request.to}</label> <br><br>
        <label>Reason:</label> <br>
        <textarea readonly rows="5" cols="50">${request.reason}</textarea> <br>
        <label>Current Status: 
            <c:choose>
                <c:when test="${request.status == 0}">In progress</c:when>
                <c:when test="${request.status == 1}">Accepted</c:when>
                <c:when test="${request.status == 2}">Rejected</c:when>
            </c:choose>
        </label> <br>

            <form action="../request/review" method="POST" style="display:inline;">
                <input type="hidden" name="id" value="${request.id}">
                <input type="hidden" name="action" value="1">
                <input type="submit" value="Accept" id="btnAccept">
            </form>

            <form action="../request/review" method="POST" style="display:inline;">
                <input type="hidden" name="id" value="${request.id}">
                <input type="hidden" name="action" value="2">
                <input type="submit" value="Reject" id="btnReject">
            </form>
        <br><br>
        <a href="../request/list">Back to List</a>
    </body>
</html>
