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
        <div class="table-responsive">
            <div class="uploadcontainer">
                <table class="table table-hover">
                    <thead>
                    <tr>
                        <th>ID</th>
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
                        <th>Price</th>
                        <th>City</th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr>
                        <td>${car.carId}</td>
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
                        <td>${car.price}</td>
                        <td>${car.city}</td>
                    </tr>
                    </tbody>
                </table>
            </div>


            <div class="py-5"
                 style="background-position: right bottom;  background-size: cover; background-repeat: repeat; background-attachment: fixed;">
                <div class="container">
                    <div class="row">
                        <div class="col-md-6">
                            <form:form method="POST" modelAttribute="fileBucket" enctype="multipart/form-data"
                                       class="form-horizontal">
                                <div class="col-md-12">
                                    <div class="card"><a
                                            href="<c:url value='/carimage-${car.carId}-1' />"><img
                                            src='/carimage-${car.carId}-1' class="img-rounded"
                                            height="300"
                                            width="490"/></a>
                                        <div class="card-body">
                                            <h5 class="card-title"><b>
                                                <form:input type="file" path="file" id="file"
                                                            class="form-control input-sm"/></b>
                                            </h5>
                                            <p class="card-text">
                                            <div class="form-actions">
                                                <input type="hidden" name="imagenumber" value="1"/>
                                                <input type="submit" value="Upload"
                                                       onclick="return confirm('Are you sure you want to update image no.1? If no image uploaded, it will remove the image.')"
                                                       class="btn btn-primary btn-sm">
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </form:form>
                        </div>
                        <div class="col-md-3">
                            <form:form method="POST" modelAttribute="fileBucket" enctype="multipart/form-data"
                                       class="form-horizontal">
                                <div class="col-md-12">
                                    <div class="card"><a
                                            href="<c:url value='/carimage-${car.carId}-2' />"><img
                                            src='/carimage-${car.carId}-2' class="img-rounded" height="135"
                                            width="225"/></a>
                                        <div class="card-body">
                                            <h5 class="card-title"><b>
                                                <form:input type="file" path="file" id="file"
                                                            class="form-control input-sm"/></b>
                                            </h5>
                                            <p class="card-text">
                                            <div class="form-actions">
                                                <input type="hidden" name="imagenumber" value="2"/>
                                                <input type="submit" value="Upload"
                                                       onclick="return confirm('Are you sure you want to update image no.2? If no image uploaded, it will remove the image.')"
                                                       class="btn btn-primary btn-sm">
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </form:form>
                            <form:form method="POST" modelAttribute="fileBucket" enctype="multipart/form-data"
                                       class="form-horizontal">
                                <div class="col-md-12">
                                    <div class="card"><a
                                            href="<c:url value='/carimage-${car.carId}-3' />"><img
                                            src='/carimage-${car.carId}-3' class="img-rounded" height="135"
                                            width="225"/></a>
                                        <div class="card-body">
                                            <h5 class="card-title"><b><form:input type="file" path="file" id="file"
                                                                                  class="form-control input-sm"/></b>
                                            </h5>
                                            <p class="card-text">
                                            <div class="form-actions">
                                                <input type="hidden" name="imagenumber" value="3"/>
                                                <input type="submit" value="Upload"
                                                       onclick="return confirm('Are you sure you want to update image no.3? If no image uploaded, it will remove the image.')"
                                                       class="btn btn-primary btn-sm">
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </form:form>
                        </div>
                        <div class="col-md-3">
                            <form:form method="POST" modelAttribute="fileBucket" enctype="multipart/form-data"
                                       class="form-horizontal">
                                <div class="col-md-12">
                                    <div class="card"><a
                                            href="<c:url value='/carimage-${car.carId}-4' />"><img
                                            src='/carimage-${car.carId}-4' class="img-rounded" height="135"
                                            width="225"/></a>
                                        <div class="card-body">
                                            <h5 class="card-title"><b><form:input type="file" path="file" id="file"
                                                                                  class="form-control input-sm"/></b>
                                            </h5>
                                            <p class="card-text">
                                            <div class="form-actions">
                                                <input type="hidden" name="imagenumber" value="4"/>
                                                <input type="submit" value="Upload"
                                                       onclick="return confirm('Are you sure you want to update image no.4? If no image uploaded, it will remove the image.')"
                                                       class="btn btn-primary btn-sm">
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </form:form>
                            <form:form method="POST" modelAttribute="fileBucket" enctype="multipart/form-data"
                                       class="form-horizontal">
                                <div class="col-md-12">
                                    <div class="card"><a
                                            href="<c:url value='/carimage-${car.carId}-5' />"><img
                                            src='/carimage-${car.carId}-5' class="img-rounded" height="135"
                                            width="225"/></a>
                                        <div class="card-body">
                                            <h5 class="card-title"><b><form:input type="file" path="file" id="file"
                                                                                  class="form-control input-sm"/></b>
                                            </h5>
                                            <p class="card-text">
                                            <div class="form-actions">
                                                <input type="hidden" name="imagenumber" value="5"/>
                                                <input type="submit" value="Upload"
                                                       onclick="return confirm('Are you sure you want to update image no.5? If no image uploaded, it will remove the image.')"
                                                       class="btn btn-primary btn-sm">
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </form:form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>
</html>