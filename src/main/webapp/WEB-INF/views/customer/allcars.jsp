<%@include file="../commonpatches/topjsp.jsp" %>
<html>
<head>
    <title>All cars</title>
    <%@include file="patches/head.jsp" %>
</head>
<body class="">
<%@include file="patches/navbar.jsp" %>
<c:forEach items="${cars}" var="car">
    <div class="">
        <div class="row m-2 d-flex flex-row align-items-center flex-grow-1 justify-content-center">
            <div class="lg-2 col-12 py-2 col-lg-5">
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
                                    <c:if test="${car.carAirCond == 'false'}">No</c:if>
                                </td>
                            </tr>
                            <tr>
                                <td>Navigation:</td>
                                <td>&nbsp;<c:if test="${car.carNavigation == 'true'}">Yes</c:if>
                                    <c:if test="${car.carNavigation == 'false'}">No</c:if>
                                </td>
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
            <div class="lg-1 col-12 p-2 col-lg-5" style="">
                <div class="carousel slide" data-ride="carousel" id="carousel${car.carId}" data-interval="5000">
                    <div class="carousel-inner">
                        <div class="carousel-item active"><img
                                class="d-block w-100 d-flex flex-column justify-content-center flex-grow-1 align-items-center"
                                src="${COMMON_CARIMAGE_READY}${car.carId}-1" height="345" width="500">
                        </div>
                        <div class="carousel-item"><img
                                class="d-block w-100 d-flex flex-column justify-content-center flex-grow-1 align-items-center"
                                src="${COMMON_CARIMAGE_READY}${car.carId}-2" height="345" width="500">
                        </div>
                        <div class="carousel-item"><img
                                class="d-block w-100 d-flex flex-column justify-content-center flex-grow-1 align-items-center"
                                src="${COMMON_CARIMAGE_READY}${car.carId}-3" height="345" width="500">
                        </div>
                        <div class="carousel-item"><img
                                class="d-block w-100 d-flex flex-column justify-content-center flex-grow-1 align-items-center"
                                src="${COMMON_CARIMAGE_READY}${car.carId}-4" height="345" width="500">
                        </div>
                        <div class="carousel-item"><img
                                class="d-block w-100 d-flex flex-column justify-content-center flex-grow-1 align-items-center"
                                src="${COMMON_CARIMAGE_READY}${car.carId}-5" height="345" width="500">
                        </div>
                    </div>
                    <a class="carousel-control-prev" href="#carousel${car.carId}" role="button"
                       data-slide="prev"> <span
                            class="carousel-control-prev-icon"></span> <span class="sr-only">Previous</span> </a> <a
                        class="carousel-control-next" href="#carousel${car.carId}" role="button"
                        data-slide="next"> <span
                        class="carousel-control-next-icon"></span> <span class="sr-only">Next</span> </a>
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
