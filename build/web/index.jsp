<%-- 
    Document   : index
    Created on : Feb 10, 2020, 8:56:24 PM
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
        <c:if test="${empty sessionScope.USER}">
            <a href="login.jsp">Login</a>
        </c:if>
        <c:if test="${sessionScope.USER.role == 'Admin'}">
            <a href="createProduct.jsp">Create Product</a></br>
            <a href="loadItemAdmin">Update and Delete Product</a></br>
        </c:if></br>
        <c:if test="${sessionScope.USER.role == 'User'}">
            <a href="loadShoppingItems">Shopping</a></br>
        </c:if></br>
        <c:if test="${sessionScope.USER.role == 'User'}">

        </c:if> 

        <form action="searchItem">
            Search by name: <input type="text" name="txtSearchName" value="${param.txtSearchName.trim()}" />
            <input type="submit" value="Search" />
        </form>
        <form action="searchItem">
            Search by range of money ( >= 0): <input type="text" name="txtSearchRange" value="${param.txtSearchRange.trim()}" />
            <input type="submit" value="Search" />
        </form>
        <form action="searchItem">
            Search by Category: <input type="text" name="txtCategory" value="${param.txtCategory.trim()}" />
            <input type="submit" value="Search" />
        </form>
        <a href="loadItemIndex">Load</a>
        <c:if test="${not empty requestScope.ERROR}">
            <p style="color: red">${requestScope.ERROR}</p>
        </c:if>
        <c:if test="${not empty requestScope.INCORRECT}">
            <p style="color: red">${requestScope.INCORRECT}</p>
        </c:if>
        <c:if test="${not empty requestScope.INPUTNUMBER}">
            <p style="color: red">${requestScope.INPUTNUMBER}</p>
        </c:if>
        <c:if test="${not empty requestScope.LISTITEMS}">
            <table border="1" class="table table-striped" style="width: 80%; margin: 3% 0 0 4%">
                <thead>
                    <tr>
                        <th scope="col">No.</th>
                        <th scope="col">Image</th>
                        <th scope="col">Name</th>
                        <th scope="col">Status</th>
                        <th scope="col">Quantity</th>
                        <th scope="col">Date</th>
                        <th scope="col">description</th>
                        <th scope="col">price</th>
                        <th scope="col">category</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${requestScope.LISTITEMS}" var="item" varStatus="counter">
                        <tr>
                            <td>${counter.count}</td>
                            <td><img src="./image/${item.base64Image}" width="100" height="100"/></td>
                            <td>${item.itemName}</td>
                            <td>${item.status}</td>
                            <td>${item.quantity}</td>
                            <td>${item.createDate}</td>
                            <td>${item.description}</td>
                            <td>${item.price}</td>
                            <td>${item.category}</td>
                        </tr>
                    </c:forEach>
                </tbody>

            </table>   
        </c:if>
        <c:if test="${not empty requestScope.NUMBEROFINDEXPAGE}">
            <c:forEach var="page" begin="1" end="${requestScope.NUMBEROFINDEXPAGE}"> 
                <c:url var="urlRewriting" value="loadItemIndex"> 
                    <c:param name="numberPage" value="${page}" />
                </c:url>
                <a href="${urlRewriting}">${page}</a>
            </c:forEach>
        </c:if>
        <c:if test="${not empty requestScope.NUMBEROFSEARCHVALUE}">
            <c:forEach var="page" begin="1" end="${requestScope.NUMBEROFSEARCHVALUE}"> 
                <c:url var="urlRewriting" value="searchItem"> 
                    <c:param name="txtSearchName" value="${param.txtSearchName.trim()}" />
                    <c:param name="numberPage" value="${page}" />
                </c:url>
                <a href="${urlRewriting}">${page}</a>
            </c:forEach>
        </c:if>
        <c:if test="${not empty requestScope.NUMBEROFRANGEVALUE}">
            <c:forEach var="page" begin="1" end="${requestScope.NUMBEROFRANGEVALUE}"> 
                <c:url var="urlRewriting" value="searchItem"> 
                    <c:param name="txtSearchRange" value="${param.txtSearchRange.trim()}" />
                    <c:param name="numberPage" value="${page}" />
                </c:url>
                <a href="${urlRewriting}">${page}</a>
            </c:forEach>
        </c:if>
        <c:if test="${not empty requestScope.NUMBEROFSEARCHCATEGORY}">
            <c:forEach var="page" begin="1" end="${requestScope.NUMBEROFSEARCHCATEGORY}"> 
                <c:url var="urlRewriting" value="searchItem"> 
                    <c:param name="txtCategory" value="${param.txtCategory.trim()}" />
                    <c:param name="numberPage" value="${page}" />
                </c:url>
                <a href="${urlRewriting}">${page}</a>
            </c:forEach>
        </c:if>
    </body>

</html>
