<%-- 
    Document   : shoppingStore
    Created on : Feb 27, 2020, 5:10:16 AM
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
        <a href="loadHistory">View History</a></br>
        
        
        <h1>Shopping</h1>
        <c:if test="${not empty requestScope.ERROR}">
            <p style="color: red">${requestScope.ERROR}</p>
        </c:if>
        <c:if test="${not empty requestScope.VALIDATION}">
            <p style="color: red">${requestScope.VALIDATION}</p>
        </c:if>
        <c:if test="${not empty requestScope.LISTITEMS}">
            <c:forEach var="items" items="${requestScope.LISTITEMS}">
                <form action="addToCart" method="POST">
                    <input type="hidden" name="txtId" value="${items.id}" />
                    <img src="./image/${items.base64Image}" style="width: 80px; height: 80px" />
                    <input type="text" value="${items.itemName}"  disabled="true"/>
                    <input type="text" value="${items.price}" disabled="true"/>
                    <input type="hidden" name="txtName" value="${items.itemName}"/>
                    <input type="hidden" name="txtPrice" value="${items.price}"/>
                    <input type="hidden" name="txtRealQuantity" value="${items.quantity}"/>
                    <span>Quantity: ${items.quantity}</span>
                    <input style="width: 2%" type="text" name="txtQuantity" id="textInput" value="1">
                    <input type="submit" value="Add" />
                </form>
            </c:forEach>
        </c:if>
        <script>
            function updateTextInput(val) {
                document.getElementById('textInput').value = val;
            }
        </script>
    </body>
</html>
