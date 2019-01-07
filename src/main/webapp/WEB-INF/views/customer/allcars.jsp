<%@include file="patches/topjsp.jsp" %>
<head>
    <title>All cars</title>
    <%@include file="patches/head.jsp" %>
</head>
<body>
<%@include file="patches/navbar.jsp" %>

<div class="h-50 py-4 px-5">
    <div class="row">
        <c:forEach items="${cars}" var="car">

            <div class="p-3 h-100 col-lg-5">
                <div class="card bg-light w-140 h-100">
                    <div class="card-body w-140 h-100 p-3">
                        <div class="row">
                            <div class="col-8">
                                <h3 class="mb-0">${car.carBrand} ${car.carModel}</h3>
                            </div>
                            <div class="col-4 text-right">
                                <h2 class="mb-0"><b>PLN${car.price}</b></h2>
                            </div>
                        </div>
                        <p class="my-3">Engine: ${car.carFuel} ${car.carHorsePower}PS</p>
                        <table>
                            <ul class="pl-3">

                                <tr>
                                    <td>
                                        <li>Gear type:</li>
                                    </td>
                                    <td>${car.carGearType}
                                    </td>
                                </tr>

                                <tr>
                                    <td>
                                        <li>Seats count:</li>
                                    </td>
                                    <td>${car.carSeatsNum}
                                    </td>
                                </tr>

                                <tr>
                                    <td>
                                        <li>Air conditioning:</li>
                                    </td>
                                    <td>
                                        <c:if test="${car.carAirCond == 'true'}">
                                            Yes
                                        </c:if>
                                        <c:if test="${car.carAirCond == 'false'}">
                                            No
                                        </c:if>
                                    </td>
                                </tr>

                                <tr>
                                    <td>
                                        <li>Navigation:</li>
                                    </td>
                                    <td>
                                        <c:if test="${car.carNavigation == 'true'}">
                                            Yes
                                        </c:if>
                                        <c:if test="${car.carNavigation == 'false'}">
                                            No
                                        </c:if>
                                    </td>
                                </tr>
                            </ul>
                        </table>

                        <a class="btn btn-primary mt-3" href="#">Book</a>
                    </div>
                </div>
            </div>


            <div class="col-lg-4 p-3 w-140 h-100">
                <div id="carousel${car.carId}" class="carousel slide w-140 h-100" data-ride="carousel"
                     data-interval="5000">
                    <div class="carousel-inner">
                        <div class="carousel-item active w-140 h-100"><img
                                src='/carimage-${car.carId}-1'></div>
                        <div class="carousel-item"><img class="d-block w-140 h-100"
                                                        src='/carimage-${car.carId}-2'>
                        </div>
                        <div class="carousel-item"><img class="d-block w-140 h-100"
                                                        src='/carimage-${car.carId}-3'>
                        </div>
                        <div class="carousel-item"><img class="d-block w-140 h-100"
                                                        src='/carimage-${car.carId}-4'>
                        </div>
                        <div class="carousel-item"><img class="d-block w-140 h-100"
                                                        src='/carimage-${car.carId}-5'>
                        </div>
                    </div>
                    <a class="carousel-control-prev" href="#carousel${car.carId}" role="button" data-slide="prev">
                        <span class="carousel-control-prev-icon" aria-hidden="true"></span></a>
                    <a class="carousel-control-next" href="#carousel${car.carId}" role="button" data-slide="next">
                        <span class="carousel-control-next-icon" aria-hidden="true"></span></a>
                </div>
            </div>
        </c:forEach>
    </div>
</div>
</body>