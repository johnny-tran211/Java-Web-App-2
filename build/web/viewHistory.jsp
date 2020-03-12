<%-- 
    Document   : viewHistory
    Created on : Feb 29, 2020, 1:27:20 AM
    Author     : Dell
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Hana Shop</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
        <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
        <link rel="stylesheet" href="/resources/demos/style.css">
        <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
        <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
        <script>
            $(function () {
                $("#datepicker").datepicker();
            });
        </script>
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
        <a href="loadHistory">Reload</a></br>

        <h1 style="margin: 50px 0 10px 80px">History</h1>
        <c:if test="${not empty requestScope.SUCCESS}">
            <p style="color: green">${requestScope.SUCCESS}</p>
        </c:if>
        <form action="searchHistory" method="POST">
            Date: <input type="text" id="datepicker" name="txtDate" value="${param.txtDate}">
            <input type="submit" value="Search" />
        </form></br>
        <form action="searchNameHistory" method="POST">
            Item Name: <input type="text" name="txtItemName" value="${param.txtItemName}">
            <input type="submit" value="Search" />
        </form>
        <c:if test="${not empty requestScope.ERROR}">
            <p style="color: red">${requestScope.ERROR}</p>
        </c:if>
        <c:if test="${not empty requestScope.LISTHISTORIES}">

            <table border="1" class="table table-striped" style="width: 80%; margin: 3% 0 0 4%">
                <thead>
                    <tr>
                        <th scope="col">Email</th>
                        <th scope="col">Check Out Date</th>
                        <th scope="col">Payment Method</th>
                        <th scope="col">Total Price</th>
                        <th scope="col">Details</th>

                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="history" items="${requestScope.LISTHISTORIES}">
                        <tr>
                            <td>${history.email}</td>
                            <td>${history.checkOutTime}</td>
                            <td>${history.paymentMethod}</td>
                            <td>${history.total}</td>
                            <c:url var="urlRewriting" value="loadDetailHistory">
                                <c:param name="txtId" value="${history.id}" />
                            </c:url>
                            <td><a href="${urlRewriting}">View Details</a></td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>

        </c:if>

    </body>
</html>
