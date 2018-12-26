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

    <c:choose>
        <c:when test="${edit}">
            <div class="well lead">Updating form</div>
        </c:when>
        <c:otherwise>
            <div class="well lead">Registration form</div>
        </c:otherwise>
    </c:choose>
    <form:form method="POST" modelAttribute="car" enctype="multipart/form-data" class="form-horizontal">
        <form:input type="hidden" path="carId" id="carId"/>

        <div class="row">
            <div class="form-group col-md-12">
                <label class="col-md-3 control-lable" for="carBrand">Brand</label>
                <div class="col-md-7">
                    <form:input type="text" path="carBrand" id="carBrand" class="form-control input-sm"/>
                    <div class="has-error">
                        <form:errors path="carBrand" class="help-inline"/>
                    </div>
                </div>
            </div>
        </div>

        <div class="row">
            <div class="form-group col-md-12">
                <label class="col-md-3 control-lable" for="carModel">Model</label>
                <div class="col-md-7">
                    <form:input type="text" path="carModel" id="carModel" class="form-control input-sm"/>
                    <div class="has-error">
                        <form:errors path="carModel" class="help-inline"/>
                    </div>
                </div>
            </div>
        </div>

        <div class="row">
            <div class="form-group col-md-12">
                <label class="col-md-3 control-lable" for="carFuel">Fuel</label>
                <div class="col-md-7">
                    <form:select class="form-control inputstl" path="carFuel" id="carFuel">
                        <option value=""></option>
                        <option>Petrol</option>
                        <option>Diesel</option>
                        <option>Petrol/Electric</option>
                        <option>Diesel/Electric</option>
                        <option>Full electric</option>
                        <option>LPG</option>
                        <option>CNG</option>
                        <option>Other</option>
                    </form:select>
                    <div class="has-error">
                        <form:errors path="carFuel" class="help-inline"/>
                    </div>
                </div>
            </div>
        </div>

        <div class="row">
            <div class="form-group col-md-12">
                <label class="col-md-3 control-lable" for="carGearType">Gear type</label>
                <div class="col-md-7">
                    <form:select class="form-control inputstl" path="carGearType" id="carGearType">
                        <option value=""></option>
                        <option>Manual</option>
                        <option>Automatic</option>
                        <option>Semi-automatic</option>
                    </form:select>
                    <div class="has-error">
                        <form:errors path="carGearType" class="help-inline"/>
                    </div>
                </div>
            </div>
        </div>

        <div class="row">
            <div class="form-group col-md-12">
                <label class="col-md-3 control-lable" for="carHorsePower">Power (PS)</label>
                <div class="col-md-7">
                    <form:input type="text" path="carHorsePower" id="carHorsePower"
                                class="form-control input-sm"/>
                    <div class="has-error">
                        <form:errors path="carHorsePower" class="help-inline"/>
                    </div>
                </div>
            </div>
        </div>

        <div class="row">
            <div class="form-group col-md-12">
                <label class="col-md-3 control-lable" for="carBodyType">Body type</label>
                <div class="col-md-7">
                    <form:select class="form-control inputstl" path="carBodyType" id="carBodyType">
                        <option value=""></option>
                        <option>Small Car</option>
                        <option>Saloon</option>
                        <option>Estate Car</option>
                        <option>Van/Minibus</option>
                        <option>Off-road Vehicle/Pickup Truck</option>
                        <option>Sports Car/Coupe</option>
                        <option>Cabriolet/Roadster</option>
                        <option>Bus</option>
                        <option>Other</option>
                    </form:select>
                    <div class="has-error">
                        <form:errors path="carBodyType" class="help-inline"/>
                    </div>
                </div>
            </div>
        </div>


        <div class="row">
            <div class="form-group col-md-12">
                <label class="col-md-3 control-lable" for="carDoorsNum">Doors number</label>
                <div class="col-md-7">
                    <form:input type="text" path="carDoorsNum" id="carDoorsNum"
                                class="form-control input-sm"/>
                    <div class="has-error">
                        <form:errors path="carDoorsNum" class="help-inline"/>
                    </div>
                </div>
            </div>
        </div>

        <div class="row">
            <div class="form-group col-md-12">
                <label class="col-md-3 control-lable" for="carSeatsNum">Seats number</label>
                <div class="col-md-7">
                    <form:input type="text" path="carSeatsNum" id="carSeatsNum"
                                class="form-control input-sm"/>
                    <div class="has-error">
                        <form:errors path="carSeatsNum" class="help-inline"/>
                    </div>
                </div>
            </div>
        </div>

        <div class="row">
            <div class="form-group col-md-12">
                <label class="col-md-3 control-lable" for="carAirCond">Air conditioning</label>
                <div class="col-md-7">
                    <form:select class="form-control inputstl" path="carAirCond" id="carAirCond">
                        <option value="0">No</option>
                        <option value="1">Yes</option>
                    </form:select>
                    <div class="has-error">
                        <form:errors path="carAirCond" class="help-inline"/>
                    </div>
                </div>
            </div>
        </div>

        <div class="row">
            <div class="form-group col-md-12">
                <label class="col-md-3 control-lable" for="carNavigation">Navigation</label>
                <div class="col-md-7">
                    <form:select class="form-control inputstl" path="carNavigation" id="carNavigation">
                        <option value="0">No</option>
                        <option value="1">Yes</option>
                    </form:select>
                    <div class="has-error">
                        <form:errors path="carNavigation" class="help-inline"/>
                    </div>
                </div>
            </div>
        </div>

        <div class="row">
            <div class="form-group col-md-12">
                <label class="col-md-3 control-lable" for="vin">VIN</label>
                <div class="col-md-7">
                        <%--<c:choose>
                            <c:when test="${edit}">
                                <form:input type="text" path="vin" id="vin" class="form-control input-sm" disabled="true"/>
                            </c:when>
                            <c:otherwise>--%>
                    <form:input type="text" path="vin" id="vin" class="form-control input-sm"/>
                    <div class="has-error">
                        <form:errors path="vin" class="help-inline"/>
                    </div>
                        <%--</c:otherwise>
                    </c:choose>--%>
                </div>
            </div>
        </div>

        <div class="row">
            <div class="form-group col-md-12">
                <label class="col-md-3 control-lable" for="regNum">Registration numb.</label>
                <div class="col-md-7">
                    <form:input type="text" path="regNum" id="regNum"
                                class="form-control input-sm"/>
                    <div class="has-error">
                        <form:errors path="regNum" class="help-inline"/>
                    </div>
                </div>
            </div>
        </div>

        <div class="row">
            <div class="form-group col-md-12">
                <label class="col-md-3 control-lable" for="carYear">Production year</label>
                <div class="col-md-7">
                    <form:input type="text" path="carYear" id="carYear"
                                class="form-control input-sm"/>
                    <div class="has-error">
                        <form:errors path="carYear" class="help-inline"/>
                    </div>
                </div>
            </div>
        </div>


        <div class="row">
            <div class="form-group col-md-12">
                <label class="col-md-3 control-lable" for="carMileage">Mileage</label>
                <div class="col-md-7">
                    <form:input type="text" path="carMileage" id="carMileage"
                                class="form-control input-sm"/>
                    <div class="has-error">
                        <form:errors path="carMileage" class="help-inline"/>
                    </div>
                </div>
            </div>
        </div>

        <div class="row">
            <div class="form-group col-md-12">
                <label class="col-md-3 control-lable" for="carVersion">Version (optional)</label>
                <div class="col-md-7">
                    <form:input type="text" path="carVersion" id="carVersion" class="form-control input-sm"/>
                    <div class="has-error">
                        <form:errors path="carVersion" class="help-inline"/>
                    </div>
                </div>
            </div>
        </div>


        <div class="row">
            <div class="form-actions floatRight">
                <c:choose>
                    <c:when test="${edit}">
                        <input type="submit" value="Update" class="btn btn-primary btn-sm"/> or <a
                            href="<c:url value='/support/cars-1per15/' />">Cancel</a>
                    </c:when>
                    <c:otherwise>
                        <input type="submit" value="Register" class="btn btn-primary btn-sm"/> or <a
                            href="<c:url value='/support/cars-1per15' />">Cancel</a>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
    </form:form>
</div>
</body>
</html>