<%-- 
    Document   : updateTime
    Created on : Mar 1, 2020, 4:34:51 AM
    Author     : Dell
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
        <title>Hana Shop</title>
    </head>
    <body>
        <c:if test="${not empty sessionScope.USER}">
            Welcome, <span>${sessionScope.USER.fullname}</span>
            <a href="logout">Logout</a></br>
        </c:if>
        <c:if test="${sessionScope.USER.role == 'Admin'}">
            <a href="createProduct.jsp">Create Product</a></br>
            <a href="loadItemAdmin">Update and Delete Product</a></br>
        </c:if></br>
        <h1>Update Time</h1>
        <c:if test="${not empty requestScope.UPDATEDATE}">
            <table border="1" class="table table-striped" style="width: 80%; margin: 3% 0 0 4%">
                <thead>
                    <tr>
                        <th scope="col">Email</th>
                        <th scope="col">Item Name</th>
                        <th scope="col">Update Date</th>
                        <th scope="col">Status</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="result" items="${requestScope.UPDATEDATE}">
                        <tr>
                            <td>${result.email}</td>
                            <td>${result.itemName}</td>
                            <td>${result.updateDate}</td>
                            <td>${result.status}</td>
                        </tr>
                    </c:forEach>

                </tbody>
            </table>
        </c:if>
        <c:if test="${empty requestScope.UPDATEDATE}">
            <p style="color: red">No record !!!</p>
        </c:if>


    </body>
</html>
