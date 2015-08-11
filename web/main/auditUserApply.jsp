<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>SMS - update seller</title>
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
                <li class="dropdown">
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
                <li><a href="../AuditServlet">AuditRequest</a></li>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown"
                       role="button">${currentUser.userName}<span
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
                <li><a href="seller.html">Seller</a></li>
                <li class="active">Update seller</li>
            </ol>

            <form action="../AuditUserServlet" method="post"
                  class="center-block" style="max-width: 500px"
                  enctype="multipart/form-data">
                <h1 class="page-header">Audit User Apply</h1>

                <div class="form-group">
                    <label>Name</label>
                    <input type="hidden" name="userID" class="form-control" value="${currentAuditUserEntity.userID}" hidden>
                    <input type="text" name="name" class="form-control" value="${currentAuditUserEntity.userName}"
                           required readonly/>
                </div>
                <div class="form-group">
                    <label>ID Card</label>
                    <input type="text" name="idCard" class="form-control" value="${currentAuditUserEntity.idCard}"
                           required readonly/>
                </div>
                <div class="form-group">
                    <label>Tel</label>
                    <input type="tel" name="tel" class="form-control" value="${currentAuditUserEntity.tel}" required
                           readonly/>
                </div>
                <div class="form-group">
                    <label>Lisence</label>
                    <input type="file" name="license" class="form-control" required readonly/>
                </div>
                <img src="../File?filename=${currentAuditUserEntity.license}" style="width:100%">

                <div class="form-group">
                    <label>Status</label>
                    <select class="form-control" name="type" required>
                        <c:forEach items="${statusEntityList}" var="statusItem">
                            <option value="${statusItem.statusID}">-${statusItem.statusName}-</option>
                        </c:forEach>
                    </select>
                </div>
                <br/>
                <button type="submit" class="btn btn-block btn-primary">Save</button>
            </form>
        </div>
    </div>
</div>
<script src="http://cdn.bootcss.com/jquery/2.1.4/jquery.min.js"></script>
<script src="http://cdn.bootcss.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
</body>

</html>
