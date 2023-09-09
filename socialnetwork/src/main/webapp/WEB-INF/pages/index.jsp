<%-- 
    Document   : index
    Created on : Aug 15, 2023, 8:26:16 PM
    Author     : admin
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
        <h1>Hello World!</h1>
    </body>
    <c:forEach items="${post}" var="p">
        <h1>${p.content}</h1>
    </c:forEach>
</html>
