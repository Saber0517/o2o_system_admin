<!DOCTYPE html>
<html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<head>
    <meta charset="UTF-8">
    <title>ADMIN - main course</title>
    <link href="http://cdn.bootcss.com/bootstrap/3.3.5/css/bootstrap.min.css" rel="stylesheet">
    <style>
        nav.navbar i {
            width: 30px;
        }

    </style>
</head>

<body>
<nav class="navbar navbar-default navbar-static-top">
    <div class="container">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#main-nav">
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="index.html">ADMIN</a>
        </div>
        <div class="collapse navbar-collapse" id="main-nav">
            <ul class="nav navbar-nav">
                <li class="dropdown active">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button">User <span
                            class="caret"></span></a>
                    <ul class="dropdown-menu">
                        <li><a href="../UserServlet?role=seller"><i class="glyphicon glyphicon-usd"></i>Seller</a></li>
                        <li><a href="../UserServlet?role=customer"><i class="glyphicon glyphicon-user"></i>Customer</a>
                        </li>
                    </ul>
                </li>
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button">Food <span
                            class="caret"></span></a>
                    <ul class="dropdown-menu">
                        <c:forEach items="${foodTypeEntityList}" var="foodTypeItem">
                            <li><a href="../FoodSerlvet?typeID=${foodTypeItem.foodTypeID}"><i
                                    class="glyphicon glyphicon-cutlery"></i>${foodTypeItem.foodTypeName}
                            </a></li>
                        </c:forEach>
                    </ul>
                </li>

            </ul>
            <ul class="nav navbar-nav navbar-right">
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown"
                       role="button">${currentUser.userName} <span
                            class="caret"></span></a>
                    <ul class="dropdown-menu">
                        <li><a href="../login.html"><i class="glyphicon glyphicon-log-out"></i>&emsp;Logout</a></li>
                    </ul>
                </li>
            </ul>
        </div>
    </div>
</nav>
<div class="container">
    <div class="row">
        <div class="col-xs-12">
            <ol class="breadcrumb">
                <li><a href="index.html">Home</a></li>
                <li class="active">Seller</li>
            </ol>
            <table class="table table-hover">
                <thead>
                <tr>
                    <th>Id</th>
                    <th>Name</th>
                    <th>ID Card</th>
                    <th>Tel</th>
                    <td>Picture</td>
                    <th>Status</th>
                    <th style="width:150px">Action</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${userEntityList}" var="userEntityItem">
                    <tr>
                        <td>${userEntityItem.userID}</td>
                        <td>${userEntityItem.userName}</td>
                        <td>${userEntityItem.idCard}</td>
                        <td>${userEntityItem.tel}</td>
                        <td><div class=""><img src="../File?filename=${userEntityItem.license}" style="width:60%" class="img-rounded img-responsive"></div></td>
                        <td>
                            <c:forEach items="${statusEntityList}" var="statusItem">
                                <c:choose>
                                    <c:when test="${statusItem.statusID eq userEntityItem.statusId}">
                                        ${statusItem.statusName}
                                    </c:when>
                                    <c:otherwise>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>
                        </td>
                        <td>
                            <a href="../UserServlet?userID=${userEntityItem.userID}">view</a>&emsp;
                            <a href="../UpdataUserServlet?userID=${userEntityItem.userID}">update</a>&emsp;
                            <a href="../DeleteUserSerlvet?userID=${userEntityItem.userID}"
                               onclick="return confirm('Delete this item?')">delete</a>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
            <nav class="text-right">
                <ul class="pagination">
                    <li>
                        <a href="#" aria-label="Previous">
                            <span aria-hidden="true">&laquo;</span>
                        </a>
                    </li>
                    <li><a href="#">1</a></li>
                    <li><a href="#">2</a></li>
                    <li><a href="#">3</a></li>
                    <li><a href="#">4</a></li>
                    <li><a href="#">5</a></li>
                    <li>
                        <a href="#" aria-label="Next">
                            <span aria-hidden="true">&raquo;</span>
                        </a>
                    </li>
                </ul>
            </nav>
        </div>
    </div>
</div>
<script src="http://cdn.bootcss.com/jquery/2.1.4/jquery.min.js"></script>
<script src="http://cdn.bootcss.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
</body>

</html>
