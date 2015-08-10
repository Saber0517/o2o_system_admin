<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>ADMIN - update food</title>
    <link href="http://cdn.bootcss.com/bootstrap/3.3.5/css/bootstrap.min.css" rel="stylesheet">
    <style>
        nav.navbar i {
            width: 30px;
        }

    </style>
</head>

<body>
<nav class="navbar navbar-default">
    <div class="container">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#main-nav">
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="index.jsp">ADMIN</a>
        </div>
        <div class="collapse navbar-collapse" id="main-nav">
            <ul class="nav navbar-nav">
                <li class="dropdown active">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button">Food <span
                            class="caret"></span></a>
                    <ul class="dropdown-menu">
                        <li><a href="mainCourse.jsp"><i class="glyphicon glyphicon-cutlery"></i>Main course</a></li>
                        <li><a href="sideDishes.jsp"><i class="glyphicon glyphicon-leaf"></i>Side dishes</a></li>
                        <li><a href="drink.jsp"><i class="glyphicon glyphicon-glass"></i>Drink</a></li>
                        <li role="separator" class="divider"></li>
                        <li><a href="newFood.jsp"><i class="glyphicon glyphicon-plus"></i>New</a></li>
                    </ul>
                </li>
                <li><a href="../AuditServlet">AuditRequest</a></li>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button">{uname} <span
                            class="caret"></span></a>
                    <ul class="dropdown-menu">
                        <li><a href="../login.jsp"><i class="glyphicon glyphicon-log-out"></i>&emsp;Logout</a></li>
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
                <li><a href="index.jsp">Home</a></li>
                <li class="active">Update food</li>
            </ol>

            <form action="../UpdataFoodServlet?update=true" method="post" class="center-block" style="max-width: 500px"
                  enctype="multipart/form-data">
                <h1 class="page-header">Update food</h1>
                <span class="text-success"><h4>${successMessage}</h4></span>
                <span class="text-warning"><h4>${failMessage}</h4></span>

                <div class="form-group">
                    <label>Name</label>
                    <input type="hidden" name="id" value="${currentAuditFoodEntity.foodID}">
                    <input type="text" name="name" class="form-control" value="${currentAuditFoodEntity.foodName}" required readonly/>
                </div>
                <div class="form-group">
                    <label>Price</label>
                    <input type="number" name="price" class="form-control" value="${currentAuditFoodEntity.price}" step="0.001" required
                           readonly/>
                </div>
                <div class="form-group">
                    <label>Type</label>
                    <select class="form-control" name="type" required disabled="disabled" readonly>

                        <c:forEach items="${foodTypeEntityList}" var="foodTypeItem">
                            <c:choose>
                                <c:when test="${currentAuditFoodEntity.typeID eq foodTypeItem.foodTypeID}">
                                    <option value="${foodTypeItem.foodTypeID}">-${foodTypeItem.foodTypeName}-</option>
                                </c:when>
                                <c:otherwise>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>
                    </select>
                </div>
                <div class="form-group">
                    <label>Picture</label>
                    <input type="file" name="picture" class="form-control" required disabled="disabled"/>
                </div>
                <br/>

                <div class="form-group">
                    <label>Status</label>
                    <select class="form-control" name="stauts" required>
                        <c:forEach items="${statusEntityList}" var="statusItem">
                            <option value="${statusItem.statusID}">-${statusItem.statusName}-</option>
                        </c:forEach>
                    </select>
                </div>
                <button type="submit" class="btn btn-block btn-primary">Save</button>
            </form>
        </div>
    </div>
</div>
<script src="http://cdn.bootcss.com/jquery/2.1.4/jquery.min.js"></script>
<script src="http://cdn.bootcss.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
</body>

</html>
