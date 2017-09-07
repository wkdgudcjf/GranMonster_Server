<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="ko">
<head></head>
<body>
    <ul>
    <c:forEach var="item" items="${result}">
        <li>${item.userID}</li>
    </c:forEach>
    </ul>
</body>
</html>