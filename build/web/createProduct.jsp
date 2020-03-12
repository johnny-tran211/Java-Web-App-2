<%-- 
    Document   : createProduct
    Created on : Feb 12, 2020, 12:57:23 AM
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
        <a href="loadItemIndex">Home</a></br>
        <a href="loadItemAdmin">Update and Delete Product</a></br>
        <c:if test="${not empty sessionScope.USER}">
            Welcome, <span>${sessionScope.USER.fullname}</span>
            <a href="logout">Logout</a>
        </c:if>
        <c:if test="${sessionScope.USER.role != 'Admin'}">
            <jsp:forward page="login.jsp">
                <jsp:param name="denied" value="this page allow for admin. Login by admin account" />
            </jsp:forward>
        </c:if>
        <c:if test="${not empty requestScope.STATUS}">
            <p>${requestScope.STATUS}</p>
        </c:if>
        <form action="createProduct" method="POST">

            Product Name: <input type="text" name="txtName" value="${param.txtName.trim()}" />
            <c:if test="${not empty requestScope.VALIDATION.productNameLengthError}">
                <span>${requestScope.VALIDATION.productNameLengthError}</span>
            </c:if>
            <c:if test="${not empty requestScope.VALIDATION.productNameDuplicateError}">
                <span>${requestScope.VALIDATION.productNameDuplicateError}</span>
            </c:if></br>
            Quantity (>= 0): <input type="text" name="txtQuantity" value="${param.txtQuantity.trim()}" />
            <c:if test="${not empty requestScope.VALIDATION.quantityLengthError}">
                <span>${requestScope.VALIDATION.quantityLengthError}</span>
            </c:if>
            <c:if test="${not empty requestScope.VALIDATION.quantityValidationError}">
                <span>${requestScope.VALIDATION.quantityValidationError}</span>
            </c:if>
            </br>
            <input type="file" name="image" accept="image/*"/>
            <c:if test="${not empty requestScope.VALIDATION.fileLengthError}">
                <span>${requestScope.VALIDATION.fileLengthError}</span>
            </c:if>
            <c:if test="${not empty requestScope.VALIDATION.fileError}">
                <span>${requestScope.VALIDATION.fileError}</span>
            </c:if></br>
            Description: <input type="text" name="txtDescription" value="${param.txtDescription.trim()}" />
            <c:if test="${not empty requestScope.VALIDATION.descriptionError}">
                <span>${requestScope.VALIDATION.descriptionError}</span>
            </c:if></br>
            Price: <input type="text" name="txtPrice" value="${param.txtPrice.trim()}" />
            <c:if test="${not empty requestScope.VALIDATION.priceLengthError}">
                <span>${requestScope.VALIDATION.priceLengthError}</span>
            </c:if>
            <c:if test="${not empty requestScope.VALIDATION.priceValidationError}">
                <span>${requestScope.VALIDATION.priceValidationError}</span>
            </c:if>
            </br>
            Category: <input type="text" name="txtCategory" value="${param.txtCategory.trim()}" />
            <c:if test="${not empty requestScope.VALIDATION.categoryError}">
                <span>${requestScope.VALIDATION.categoryError}</span>
            </c:if></br>
            <input type="submit" value="Submit" /></br>
        </form>
    </body>
</html>
