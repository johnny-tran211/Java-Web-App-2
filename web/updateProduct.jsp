<%-- 
    Document   : updateProduct
    Created on : Feb 12, 2020, 11:25:08 PM
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
        <a href="loadItemIndex">Home</a></br>
        <a href="createProduct.jsp">Create Product</a></br>
        <c:if test="${not empty sessionScope.USER}">
            Welcome, <span>${sessionScope.USER.fullname}</span>
            <a href="logout">Logout</a></br>
        </c:if>
        <c:if test="${sessionScope.USER.role != 'Admin'}">
            <jsp:forward page="login.jsp">
                <jsp:param name="denied" value="this page allow for admin. Login by admin account" />
            </jsp:forward>
        </c:if>
        <c:if test="${not empty requestScope.NAMEERROR}">
            <p>${requestScope.NAMEERROR}</p>
        </c:if>
        <c:if test="${not empty requestScope.SUCCESS}">
            <p  style="color: green">${requestScope.SUCCESS}</p>
        </c:if>
        <c:if test="${not empty requestScope.FAILED}">
            <p  style="color: red">${requestScope.FAILED}</p>
        </c:if>
        <c:if test="${not empty param.FAILED}">
            <p  style="color: red">${param.FAILED}</p>
        </c:if>
        <c:if test="${not empty requestScope.LISTITEMS}">
            <form action="deleteAndUpdate" method="POST">
                <table border="1" class="table table-striped" style="width: 80%; margin: 3% 0 0 4%">
                    <thead>
                        <tr>
                            <th scope="col">No.</th>
                            <th scope="col">Image</th>
                            <th scope="col">Name</th>
                            <th scope="col">Status</th>
                            <th scope="col">Quantity</th>
                            <th scope="col">Create Date</th>
                            <th scope="col">description</th>
                            <th scope="col">price</th>
                            <th scope="col">category</th>
                            <th scope="col"><input id="btnDelete" type="submit" value="Delete" name="action"/></th>
                            <th scope="col"><input id="btnRestore" type="submit" value="Restore" name="action"/></th>
                            <th scope="col">Update</th>
                            <th scope="col">View Update Date</th>

                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${requestScope.LISTITEMS}" var="item" varStatus="counter">
                            <tr>
                                <td>${counter.count}</td>
                                <td><img src="./image/${item.base64Image}" width="100" height="100"/></td>
                                <td>${item.itemName}</td>
                                <c:if test="${item.status == 'active'}">
                                    <td style="color: green">${item.status}</td>
                                </c:if>
                                <c:if test="${item.status == 'Inactive'}">
                                    <td style="color: red">${item.status}</td>
                                </c:if>
                                <td>${item.quantity}</td>
                                <td>${item.createDate}</td>
                                <td>${item.description}</td>
                                <td>${item.price}</td>
                                <td>${item.category}</td>
                                <td>
                                    <c:if test="${item.status == 'active'}">
                                        <select name="cboDelete">
                                            <option>active</option>
                                            <option  value="${item.itemName}">Inactive</option>
                                        </select>
                                    </c:if>
                                    <c:if test="${item.status == 'Inactive'}">
                                        <select disabled="true">
                                            <option selected>Inactive</option>
                                        </select>
                                    </c:if>
                                </td>
                                <td>
                                    <input type="checkbox" name="chbRestore" value="${item.itemName}"  <c:if test="${item.status == 'active'}"> disabled="true" </c:if>/>
                                </td>
                                <c:url var="urlWriting" value="loadItemUpdate">
                                    <c:param name="txtId" value="${item.id}" />
                                </c:url>
                                <td><a href="${urlWriting}">View Detail</a></td>
                                <c:url var="urlWriting2" value="loadUpdateDate">
                                    <c:param name="txtItemName" value="${item.itemName}" />
                                </c:url>
                                <td><a href="${urlWriting2}">View Update TIme</a></td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table> 
            </form>
        </c:if>
        <c:if test="${not empty requestScope.NUMBEROFADMINPAGE}">
            <c:forEach var="page" begin="1" end="${requestScope.NUMBEROFADMINPAGE}"> 
                <c:url var="urlRewriting" value="loadItemAdmin"> 
                    <c:param name="numberPage" value="${page}" />
                </c:url>
                <a href="${urlRewriting}">${page}</a>
            </c:forEach>
        </c:if>
        <script>
            document.getElementById("btnDelete").addEventListener("click", function (event) {
                const deleteBtn = confirm("Do you want to delete");
                if (!deleteBtn)
                    event.preventDefault();
            });
            document.getElementById("btnRestore").addEventListener("click", function (event) {
                const deleteBtn = confirm("Do you want to restore");
                if (!deleteBtn)
                    event.preventDefault();
            });
        </script>

    </body>
</html>
