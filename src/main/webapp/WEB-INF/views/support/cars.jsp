<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<html>

<head>
    <title>Cars</title>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <link href="<c:url value='/static/css/bootstrap.css' />" rel="stylesheet"></link>
    <link href="<c:url value='/static/css/app.css' />" rel="stylesheet"></link>
    <script src="/static/js/rowsonpage.js" type="text/javascript"></script>
    <style>
        select {
            margin: 5px;
            width: 50px;
            height: 30px;
            padding: 3px 3px 3px;
            font-size: 14px;
            border: 1px solid #ccc;
        }
    </style>
</head>

<body>
<div class="generic-container">
    <%@include file="patches/authheader.jsp" %>
    <div class="panel panel-default">

        <div class="panel-heading">
            <div class="row">
                <div class="col-md-12">
                    <span class="floatLeft">
                    <div class="col-md-1">
                    <span class="lead">Cars list</span>
                    </div>
                    <div class="col-md-2">
                        <c:if test="${pagesAmount !=0}">
                            Rows per page:
                            <select id="dynamic_select">
                            <option value="" hidden>${rowsOnPage}</option>
                            <option value="<c:url value='/support/cars-1per15' />">15</option>
                            <option value="<c:url value='/support/cars-1per30' />">30</option>
                            <option value="<c:url value='/support/cars-1per60' />">60</option>
                        </select>
                        </c:if>
                        </div>
                    </span>

                    <span class="floatRight">
                    <button type="button" class="btn btn-link btn-sm">
                    <a href="<c:url value='/support/cars/createcar'  />">
                    <span class="glyphicon glyphicon-plus"></span> Create new car</a>
                    </button>
                    </span>
                </div>
            </div>
        </div>

        <div class="table-responsive">
            <table class="table table-hover">
                <thead>
                <tr>
                    <th>ID</th>
                    <th>Image</th>
                    <th>Brand</th>
                    <th>Model</th>
                    <th>Fuel</th>
                    <th>Gear type</th>
                    <th>HorsePower</th>
                    <th>Body type</th>
                    <th>Doors</th>
                    <th>Seats</th>
                    <th>Air conditioning</th>
                    <th>Navigation</th>
                    <th>VIN</th>
                    <th>Registration numb.</th>
                    <th>Production year</th>
                    <th>Mileage</th>
                    <th>Version</th>
                    <th width="100"></th>
                    <th width="100"></th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${cars}" var="car">
                    <tr>
                        <td>${car.carId}</td>
                        <td><a href="<c:url value='/support/cars/uploadcarimage-${car.vin}' />"><img
                                src='/support/cars/carimage-${car.vin}' class="img-rounded" height="180"
                                width="180" alt="Click to add new image" title="Add new image"/></a></td>
                        <td>${car.carBrand}</td>
                        <td>${car.carModel}</td>
                        <td>${car.carFuel}</td>
                        <td>${car.carGearType}</td>
                        <td>${car.carHorsePower}</td>
                        <td>${car.carBodyType}</td>
                        <td>${car.carDoorsNum}</td>
                        <td>${car.carSeatsNum}</td>
                        <c:if test="${car.carAirCond == 'true'}">
                            <td>
                                YES
                            </td>
                        </c:if>
                        <c:if test="${car.carAirCond == 'false'}">
                            <td>
                                NO
                            </td>
                        </c:if>

                        <c:if test="${car.carNavigation == 'true'}">
                            <td>
                                YES
                            </td>
                        </c:if>
                        <c:if test="${car.carNavigation == 'false'}">
                            <td>
                                NO
                            </td>
                        </c:if>
                        <td>${car.vin}</td>
                        <td>${car.regNum}</td>
                        <td>${car.carYear}</td>
                        <td>${car.carMileage}</td>
                        <td>${car.carVersion}</td>
                        <td><a href="<c:url value='/support/cars/editcar-${car.vin}' />"
                               class="btn btn-success custom-width">edit</a>
                        </td>
                        <td><a href="<c:url value='/support/cars/deletecar-${car.vin}' />"
                               onclick="return confirm('Please confirm deleting')"
                               class="btn btn-danger custom-width">delete</a>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
        <c:if test="${pagesAmount !=0}">
            <div class="row">
        <span class="floatleft">
            <div class="col-lg-3">
                <%@include file="patches/paginator.jsp" %>
            </div>
        </span>
            </div>
        </c:if>
    </div>
</div>
</body>
</html>
