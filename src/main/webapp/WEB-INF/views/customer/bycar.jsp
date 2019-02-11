<%@include file="../commonpatches/topjsp.jsp" %>
<html>
<head>
    <title>By car</title>
    <%@include file="patches/head.jsp" %>
    <%@include file="patches/calendarheader.jsp" %>
</head>
<body>
<%@include file="patches/navbar.jsp" %>
<c:forEach items="${cars}" var="car">
    <div class="py-3 mx-3">
        <div class="row justify-content-center">
            <div class="col-lg-4 col-12 my-1">
                <div class="card bg-light">
                    <div class="card-body flex-column d-flex mx-2 my-0 p-2">
                        <div class="row">
                            <div class="col-8">
                                <h3 class="mb-0 py-2">${car.carBrand}</h3>
                                <h3 class="mb-0">${car.carModel}</h3>
                            </div>
                        </div>
                        <table class="border-primary my-3">
                            <tbody class="">
                            <tr>
                                <td>Engine:</td>
                                <td>&nbsp;${car.carHorsePower}&nbsp;PS&nbsp;${car.carFuel}</td>
                            </tr>
                            <tr>
                                <td>Gear type:</td>
                                <td>&nbsp;${car.carGearType}</td>
                            </tr>
                            <tr>
                                <td>Seats count:</td>
                                <td>&nbsp;${car.carSeatsNum}</td>
                            </tr>
                            <tr>
                                <td>Air conditioning:</td>
                                <td>&nbsp;<c:if test="${car.carAirCond == 'true'}">Yes</c:if>
                                    <c:if test="${car.carAirCond == 'false'}">No</c:if></td>
                            </tr>
                            <tr>
                                <td>Navigation:</td>
                                <td>&nbsp;<c:if test="${car.carNavigation == 'true'}">Yes</c:if>
                                    <c:if test="${car.carNavigation == 'false'}">No</c:if></td>
                            </tr>
                            </tbody>
                        </table>
                        <div class="row">
                            <div class="text-left d-flex flex-grow-1 align-items-start flex-row py-2 pb-0 col-6">
                                <a class="btn btn-primary flex-column d-flex justify-content-center align-items-center flex-grow-1"
                                   href="#">Book</a>
                            </div>
                            <div class="text-right col-6 my-2">
                                <h3><b>PLN${car.price}</b></h3>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="col-12 col-lg-4 flex-column justify-content-center align-items-center d-flex py-0 my-1">
                <div id="carousel${car.carId}" class="carousel slide" data-ride="carousel" data-interval="5000">
                    <div class="carousel-inner">
                        <div class="carousel-item active"><img src="${COMMON_CARIMAGE_READY}${car.carId}-1"
                                                               class="d-block w-100 d-flex flex-column justify-content-center flex-grow-1 align-items-center"
                                                               width="500"
                                                               height="335"></div>
                        <div class="carousel-item"><img src="${COMMON_CARIMAGE_READY}${car.carId}-2"
                                                        class="d-block w-100 d-flex flex-column justify-content-center flex-grow-1 align-items-center"
                                                        width="500"
                                                        height="335"></div>
                        <div class="carousel-item"><img src="${COMMON_CARIMAGE_READY}${car.carId}-3"
                                                        class="d-block w-100 d-flex flex-column justify-content-center flex-grow-1 align-items-center"
                                                        width="500"
                                                        height="335"></div>
                        <div class="carousel-item"><img src="${COMMON_CARIMAGE_READY}${car.carId}-4"
                                                        class="d-block w-100 d-flex flex-column justify-content-center flex-grow-1 align-items-center"
                                                        width="500"
                                                        height="335"></div>
                        <div class="carousel-item"><img src="${COMMON_CARIMAGE_READY}${car.carId}-5"
                                                        class="d-block w-100 d-flex flex-column justify-content-center flex-grow-1 align-items-center"
                                                        width="500"
                                                        height="335"></div>
                    </div>
                    <a class="carousel-control-prev" href="#carousel${car.carId}" role="button" data-slide="prev">
                        <span class="carousel-control-prev-icon" aria-hidden="true"></span></a>
                    <a class="carousel-control-next" href="#carousel${car.carId}" role="button" data-slide="next">
                        <span class="carousel-control-next-icon" aria-hidden="true"></span></a>
                </div>
            </div>
            <div class="col-lg-4 col-12 my-1">
                <div class="card px-2 pt-4 pb-3">
                    <div class="card-body m-0 p-0">
                        <%@include file="patches/calendar.jsp" %>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <nav class="navbar navbar-dark bg-dark">
        <div class="container d-flex justify-content-center"></div>
    </nav>
</c:forEach>
</body>
</html>