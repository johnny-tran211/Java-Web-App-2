<%-- 
    Document   : cart
    Created on : Feb 27, 2020, 5:55:13 AM
    Author     : Dell
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
        <title>Hana Shop</title>
    </head>
    <c:set var="total" value="0" />
    <body>
        <c:if test="${sessionScope.USER.role != 'User'}">
            <jsp:forward page="login.jsp">
                <jsp:param name="denied" value="this page allow for user. Login by user account" />
            </jsp:forward>
        </c:if>
        <c:if test="${not empty requestScope.ERRORQUANTITY}">
            <p style="color: red">${requestScope.ERRORQUANTITY}</p>
        </c:if>
        <c:if test="${not empty requestScope.ERROR}">
            <p style="color: red">${requestScope.ERROR}</p>
        </c:if>
        <c:if test="${not empty sessionScope.CART.lists}">

            <table border="1" class="table table-striped" style="width: 80%; margin: 3% 0 0 4%">
                <thead>
                    <tr>
                        <th scope="col">Item Name</th>
                        <th scope="col">Quantity</th>
                        <th scope="col">Price</th>
                        <th scope="col">Total</th>
                        <th scope="col">Delete</th>
                        <th scope="col">Update</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="item" items="${sessionScope.CART.lists}">
                    <form action="deleteItemCart" method="POST">
                        <c:set var="total" value="${total + item.value.total}" />
                        <tr>
                            <td>${item.value.itemName}</td>
                            <input type="hidden" name="txId" value="${item.key}" />
                            <td><input name="txtQuantity" value="${item.value.quantity}"/></td>
                            <td>${item.value.price}</td>
                            <td>${item.value.total}</td> 
                            <td><input type="submit" value="Delete" name="action" /></td>   
                            <td><input type="submit" value="Update" name="action" /></td>   
                        </tr>
                    </form>
                </c:forEach>
            </tbody>
        </table>

        <p>Total: ${total}</p>
        <form action="saveHistory">
            <input type="hidden" name="txtTotal" value="${total}" /></br>
            <p>Payment Method</p>
            Cash<input type="radio" name="ckbMethod" value="Cash" checked="true"/></br>
            Paypal<input type="radio" name="ckbMethod" value="Momo" /></br></br></br>
            <input type="submit" value="Confirm" />
        </form>
        <a href="loadShoppingItems">Add more</a>
    </c:if>
    <c:if test="${empty sessionScope.CART.lists}">
        <p>Cart is empty</p>
        <a href="loadShoppingItems">Back to shopping store</a>
    </c:if>
</body>
</html>
