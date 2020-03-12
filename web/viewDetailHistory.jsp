<%-- 
    Document   : viewDetailHistory
    Created on : Mar 1, 2020, 4:53:10 PM
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
        <c:if test="${sessionScope.USER.role != 'User'}">
            <jsp:forward page="login.jsp">
                <jsp:param name="denied" value="this page allow for user. Login by user account" />
            </jsp:forward>
        </c:if>
        <a href="loadItemIndex">Home</a></br>
        <c:if test="${not empty sessionScope.USER}">
            Welcome, <span>${sessionScope.USER.fullname}</span>
            <a href="logout">Logout</a></br>
        </c:if>
        <a href="cart.jsp">View Cart</a></br>
        <a href="loadShoppingItems">Shopping Store</a></br>
        <a href="loadHistory">View History</a></br>
        <h1 style="margin: 50px 0 10px 80px">History Details</h1>
        <c:if test="${not empty requestScope.ERROR}">
            <p style="color: red">${requestScope.ERROR}</p>
        </c:if>
        <c:if test="${not empty requestScope.HISTORYDETAIL}">
            <c:forEach var="detail" items="${requestScope.HISTORYDETAIL}">
                <table border="1" class="table table-striped" style="width: 80%; margin: 3% 0 0 4%">
                    <thead>
                        <tr>
                            <th scope="col">Product Name</th>
                            <th scope="col">Quantity</th>
                            <th scope="col">Price</th>
                            <th scope="col">Total</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td>${detail.itemName}</td>
                            <td>${detail.quantity}</td>
                            <td>${detail.price}</td>
                            <td>${detail.total}</td>
                        </tr>
                    </tbody>
                </table>
            </c:forEach>
        </c:if>
    </body>
</html>
