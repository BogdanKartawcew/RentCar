<%@include file="../commonpatches/topjsp.jsp" %>

<html>

<head>
    <title>Car</title>
    <%@include file="patches/head.jsp" %>
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
                        <option value="" hidden>choose fuel</option>
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
                        <option value="" hidden>choose gear type</option>
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
                        <option value="" hidden>choose body type</option>
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
                        <option value="" hidden>Yes/No</option>
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
                        <option value="" hidden>Yes/No</option>
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
                    <form:input type="text" path="vin" id="vin" class="form-control input-sm"/>
                    <div class="has-error">
                        <form:errors path="vin" class="help-inline"/>
                    </div>
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
            <div class="form-group col-md-12">
                <label class="col-md-3 control-lable" for="price">Price per day</label>
                <div class="col-md-7">
                    <form:input type="text" path="price" id="price" class="form-control input-sm"/>
                    <div class="has-error">
                        <form:errors path="price" class="help-inline"/>
                    </div>
                </div>
            </div>
        </div>

        <div class="row">
            <div class="form-group col-md-12">
                <label class="col-md-3 control-lable" for="city">Location</label>
                <div class="col-md-7">
                    <form:select class="form-control inputstl" path="city" id="city">
                        <option value="" hidden>Set vehicle actual location</option>
                        <option value="WROCLAW">Wroclaw</option>
                        <option value="CRACOW">Cracow</option>
                        <option value="WARSAW">Warsaw</option>
                    </form:select>
                    <div class="has-error">
                        <form:errors path="city" class="help-inline"/>
                    </div>
                </div>
            </div>
        </div>


        <div class="row">
            <div class="form-actions floatRight">
                <c:choose>
                    <c:when test="${edit}">
                        <input type="submit" value="Update" class="btn btn-primary btn-sm"/>
                    </c:when>
                    <c:otherwise>
                        <input type="submit" value="Register" class="btn btn-primary btn-sm"/>
                    </c:otherwise>
                </c:choose>
                or <a
                    href="<c:url value='${SUPPORT_CARS_PAGES}' />">Cancel</a>
            </div>
        </div>
    </form:form>
</div>
</body>
</html>