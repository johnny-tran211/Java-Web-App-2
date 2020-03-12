<%-- 
    Document   : login
    Created on : Feb 10, 2020, 9:22:06 PM
    Author     : Dell
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Hana Shop</title>
    </head>
    <body>
        <h1>Login Page</h1>
        <c:if test="${not empty requestScope.ERROR}">
            <p>${requestScope.ERROR}</p>
        </c:if>
        <c:if test="${not empty param.denied}">
            <p>${param.denied}</p>
        </c:if>
            
        <form action="login" method="POST">
            <input type="text" name="txtUsername" value="" />
            <input type="password" name="txtPassword" value="" />
            <input type="submit" value="Submit" />
        </form>
            <a href="loadItemIndex">Home Page</a>
    </body>
</html>
