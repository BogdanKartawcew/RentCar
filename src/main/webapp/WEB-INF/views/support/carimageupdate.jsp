<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<html>

<head>
    <title>Car</title>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <link href="<c:url value='/static/css/bootstrap.css' />" rel="stylesheet"></link>
    <link href="<c:url value='/static/css/app.css' />" rel="stylesheet"></link>
</head>

<body>
<div class="generic-container">
    <%@include file="patches/authheader.jsp" %>

    <div class="panel panel-default">

        <div class="panel-heading"><span class="lead">Choose an image</span></div>
        <div class="uploadcontainer">
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
                <tr>
                    <td>${car.carId}</td>
                    <td><a href="<c:url value='/support/cars/carimage-${car.vin}' />"><img
                            src='/support/cars/carimage-${car.vin}' class="img-rounded" height="110" width="180"/></a></td>
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
                </tr>
                </tbody>
            </table>
            <form:form method="POST" modelAttribute="fileBucket" enctype="multipart/form-data" class="form-horizontal">

                <div class="row">
                    <div class="form-group col-md-12">
                        <label class="col-md-3 control-lable" for="file">Upload new image</label>
                        <div class="col-md-7">
                            <form:input type="file" path="file" id="file" class="form-control input-sm"/>
                            <div class="has-error">
                                <form:errors path="file" class="help-inline"/>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="row">
                    <div class="form-actions floatRight">
                        <input type="submit" value="Upload"
                               onclick="return confirm('Are you sure you want to update it? If no image uploaded, it will remove the image.')"
                               class="btn btn-primary btn-sm">
                    </div>
                </div>

            </form:form>
        </div>
    </div>
</div>
</div>
</body>
</html>