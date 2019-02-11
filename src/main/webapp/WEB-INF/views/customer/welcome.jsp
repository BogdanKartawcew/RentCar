<%@include file="../commonpatches/topjsp.jsp" %>
<html>
<head>
    <title>RentCar - Welcome!</title>
    <%@include file="patches/head.jsp" %>
</head>

<body>
<%@include file="patches/navbar.jsp" %>
<div class="py-1">
    <div class="container">
        <div class="row">
            <div class="text-center mx-auto col-md-12">
                <h1 class="my-4">Choose city</h1>
            </div>
        </div>
        <div class="row">
            <div class="col-md-4 p-3">
                <a href="<c:url value='/dateorcar-wroclaw' />"><img class="img-fluid d-block"
                                                                    title="Rent car in Wroclaw"
                                                                    src="static/customer/images/wroclaw.jpg"></a>
                <h2 class="mt-3 d-flex flex-row justify-content-center align-items-center flex-grow-1">
                    <b>Wroclaw</b>
                </h2>
            </div>
            <div class="col-md-4 p-3"><a href="<c:url value='/dateorcar-cracow' />"><img class="img-fluid d-block"
                                                                                         title="Rent car in Cracow"
                                                                                         src="static/customer/images/cracow.jpg"></a>
                <h2 class="mt-3 d-flex flex-row justify-content-center align-items-center flex-grow-1">
                    <b>Cracow</b>
                </h2>
            </div>
            <div class="col-md-4 p-3"><a href="<c:url value='/dateorcar-warsaw' />"><img class="img-fluid d-block"
                                                                                         title="Rent car in Warsaw"
                                                                                         src="static/customer/images/warsaw.jpg"></a>
                <h2 class="mt-3 d-flex flex-row justify-content-center align-items-center flex-grow-1">
                    <b>Warsaw</b>
                </h2>
            </div>

        </div>
    </div>
</div>
<div class="text-center py-4">
    <div class="container">
        <div class="row text-muted">
            <div class="col-md-2 col-3 p-2"><i class="fa fa-amazon fa-3x"></i></div>
            <div class="col-md-2 col-3 p-2"><i class="fa fa-code fa-3x"></i></div>
            <div class="col-md-2 col-3 p-2"><i class="fa fa-coffee fa-3x"></i></div>
            <div class="col-md-2 col-3 p-2"><i class="fa fa-database fa-3x"></i></div>
            <div class="col-md-2 col-3 p-2"><i class="fa fa-github fa-3x"></i></div>
            <div class="col-md-2 col-3 p-2"><i class="fa fa-google fa-3x"></i></div>
            <div class="col-md-2 col-3 p-2"><i class="fa fa-html5 fa-3x"></i></div>
            <div class="col-md-2 col-3 p-2"><i class="fa fa-css3 fa-3x"></i></div>
            <div class="col-md-2 col-3 p-2"><i class="fa fa-bug fa-3x"></i></div>
            <div class="col-md-2 col-3 p-2"><i class="fa fa-desktop fa-3x"></i></div>
            <div class="col-md-2 col-3 p-2"><i class="fa fa-comment fa-3x"></i></div>
            <div class="col-md-2 col-3 p-2"><i class="fa fa-android fa-3x"></i></div>
        </div>
    </div>
</div>


</body>
</html>