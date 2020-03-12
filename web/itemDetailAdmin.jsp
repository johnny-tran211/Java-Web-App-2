<%-- 
    Document   : itemDetailAdmin
    Created on : Feb 13, 2020, 1:03:24 AM
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
        <c:if test="${sessionScope.USER.role != 'Admin'}">
            <jsp:forward page="login.jsp">
                <jsp:param name="denied" value="this page allow for admin. Login by admin account" />
            </jsp:forward>
        </c:if>
        <c:if test="${empty sessionScope.ITEM}">
            <jsp:forward page="LoadItemUpdateServlet">
                <jsp:param name="FAILED" value="click View Detail again" />
            </jsp:forward>
        </c:if>
        <a href="loadItemIndex">Home</a></br>
        <a href="loadItemAdmin">Update and Delete Product</a></br>
                <a href="createProduct.jsp">Create Product</a></br>
        <c:if test="${not empty sessionScope.USER}">
            Welcome, <span>${sessionScope.USER.fullname}</span>
            <a href="logout">Logout</a>
        </c:if></br>
        <c:set var="item" value="${sessionScope.ITEM}" />
        <form action="updateProduct" method="POST">
            Product Name: <input type="text" name="txtName" value="${item.itemName.trim()}" />
            <input type="hidden" name="txtId" value="${item.id}"/>
            <c:if test="${not empty requestScope.VALIDATION.productNameLengthError}">
                <span>${requestScope.VALIDATION.productNameLengthError}</span>
            </c:if>
            <c:if test="${not empty requestScope.VALIDATION.productNameDuplicateError}">
                <span>${requestScope.VALIDATION.productNameDuplicateError}</span>
            </c:if></br>
            Quantity (>= 0): <input type="text" name="txtQuantity" value="${item.quantity}" />
            <c:if test="${not empty requestScope.VALIDATION.quantityLengthError}">
                <span>${requestScope.VALIDATION.quantityLengthError}</span>
            </c:if>
            <c:if test="${not empty requestScope.VALIDATION.quantityValidationError}">
                <span>${requestScope.VALIDATION.quantityValidationError}</span>
            </c:if>
            </br>
            <img src="./image/${item.base64Image.trim()}" width="100" height="100"/>
            <input type="hidden" name="backupImage" value="${item.base64Image.trim()}" />
            <input type="file" name="image" accept="image/*"/>
            <c:if test="${not empty requestScope.VALIDATION.fileLengthError}">
                <span>${requestScope.VALIDATION.fileLengthError}</span>
            </c:if>
            <c:if test="${not empty requestScope.VALIDATION.fileError}">
                <span>${requestScope.VALIDATION.fileError}</span>
            </c:if></br>
            Description: <input type="text" name="txtDescription" value="${item.description.trim()}" />
            <c:if test="${not empty requestScope.VALIDATION.descriptionError}">
                <span>${requestScope.VALIDATION.descriptionError}</span>
            </c:if></br>
            Price: <input type="text" name="txtPrice" value="${item.price}" />
            <c:if test="${not empty requestScope.VALIDATION.priceLengthError}">
                <span>${requestScope.VALIDATION.priceLengthError}</span>
            </c:if>
            <c:if test="${not empty requestScope.VALIDATION.priceValidationError}">
                <span>${requestScope.VALIDATION.priceValidationError}</span>
            </c:if>
            </br>
            Category: <input type="text" name="txtCategory" value="${item.category.trim()}" />
            <c:if test="${not empty requestScope.VALIDATION.categoryError}">
                <span>${requestScope.VALIDATION.categoryError}</span>
            </c:if></br>
            <input type="submit" value="Submit" /></br>
                <c:url var="urlReload" value="loadItemUpdate">
                    <c:if test="${not empty item.id}">
                        <c:param name="txtId" value="${item.id}" />    
                    </c:if>
                </c:url>
            <a href="${urlReload}">reload</a>
        </form>
    </body>
</html>
