<%@include file="../commonpatches/topjsp.jsp" %>

<head>
    <title>RentCar - Welcome!</title>
    <%@include file="patches/head.jsp" %>
</head>

<body>
<%@include file="patches/navbar.jsp" %>
<div class="py-5">
    <div class="container">
        <div class="row">
            <div class="text-center col-md-12">
                <h1>Choose City</h1>
            </div>
        </div>
        <br>
        <div class="row d-flex justify-content-center text-center ">

            <div class="p-3 col-lg-4 col-md-4">
                <div class="container">
                    <a href="<c:url value='/dateorcar-wroclaw' />"><img
                            src="static/customer/images/wroclaw.jpg" class="img-rounded" height="300"
                            width="300" title="Rent car in Wroclaw"/></a>
                    <div class="card border-0">
                        <div class="card-body p-4">
                            <h2 class="mt-3"><b>Wroclaw</b></h2>
                        </div>
                    </div>
                </div>
            </div>

            <div class="p-3 col-lg-4 col-md-4">
                <div class="container">
                    <a href="<c:url value='/dateorcar-warsaw' />"><img
                            src="static/customer/images/warsaw.jpg" class="img-rounded" height="300"
                            width="300" title="Rent car in Warsaw"/></a>
                    <div class="card border-0">
                        <div class="card-body p-4">
                            <h2 class="mt-3"><b>Warsaw</b></h2>
                        </div>
                    </div>
                </div>
            </div>

            <div class="p-3 col-lg-4 col-md-4">
                <div class="container">
                    <a href="<c:url value='/dateorcar-cracow' />"><img
                            src="static/customer/images/cracow.jpg" class="img-rounded" height="300"
                            width="300" title="Rent car in Cracow"/></a>
                    <div class="card border-0">
                        <div class="card-body p-4">
                            <h2 class="mt-3"><b>Cracow</b></h2>
                        </div>
                    </div>
                </div>
            </div>

        </div>
    </div>
</div>
<%--<div class="py-5 text-center text-white" style="position: relative; overflow: hidden;">
    <video autoplay="" loop="" muted="" plays-inline=""
           style="position: absolute; right: 0; top: 0; min-width:100%; z-index: -100;">
        <source src="https://r4---sn-5goeen7y.googlevideo.com/videoplayback?requiressl=yes&dur=341.880&id=o-AJ30zsyDfxEmWeqxDORcAyouB7m6Z3kQfS4c-ImoDSeD&pl=24&source=youtube&ei=OQspXMDJCLiRhwaAqbt4&c=WEB&txp=5532432&fvip=4&keepalive=yes&gir=yes&clen=522490404&aitags=133%2C134%2C135%2C136%2C137%2C160%2C242%2C243%2C244%2C247%2C248%2C271%2C278%2C313&itag=313&key=cms1&mime=video%2Fwebm&lmt=1541546329576406&sparams=aitags,clen,dur,ei,expire,gir,id,ip,ipbits,ipbypass,itag,keepalive,lmt,mime,mip,mm,mn,ms,mv,pl,requiressl,source&signature=62860DE1C4E82211D3CF1A6BC11539D9C750E800.048516FCECA2ADD54A4DB836BAC7C0D5A700CD4F&ip=2600%3A3c03%3A%3Af03c%3A91ff%3Afe13%3A8670&expire=1546215321&ipbits=0&ratebypass=yes&title=Ford+Mustang+BULLITT+roars+on+the+Isle+of+Man+TT%27s+Mountain+Road&redirect_counter=1&rm=sn-ab5ee77z&fexp=23763603&req_id=272187868d2ba3ee&cms_redirect=yes&ipbypass=yes&mip=199.247.35.126&mm=31&mn=sn-5goeen7y&ms=au&mt=1546193599&mv=m"
                type="video/mp4">
    </video>
    <div class="container py-5">
        <div class="row">
            <div class="col-lg-8 col-md-10 mx-auto">
                <p class="lead mb-5">You need a car. Do you?</p>
            </div>
        </div>
    </div>
</div>--%>
<div class="py-5 text-center">
    <div class="container">
        <div class="row text-muted">
            <div class="col-md-1 col-4 p-2"><i class="fa fa-amazon fa-3x"></i></div>
            <div class="col-md-1 col-3 p-2"><i class="fa fa-code fa-3x"></i></div>
            <div class="col-md-1 col-3 p-2"><i class="fa fa-coffee fa-3x"></i></div>
            <div class="col-md-1 col-3 p-2"><i class="fa fa-database fa-3x"></i></div>
            <div class="col-md-1 col-3 p-2"><i class="fa fa-github fa-3x"></i></div>
            <div class="col-md-1 col-3 p-2"><i class="fa fa-google fa-3x"></i></div>
            <div class="col-md-1 col-3 p-2"><i class="fa fa-html5 fa-3x"></i></div>
            <div class="col-md-1 col-3 p-2"><i class="fa fa-css3 fa-3x"></i></div>
            <div class="col-md-1 col-3 p-2"><i class="fa fa-bug fa-3x"></i></div>
            <div class="col-md-1 col-3 p-2"><i class="fa fa-desktop fa-3x"></i></div>
            <div class="col-md-1 col-3 p-2"><i class="fa fa-comment fa-3x"></i></div>
            <div class="col-md-1 col-3 p-2"><i class="fa fa-android fa-3x"></i></div>
        </div>
    </div>
</div>
</body>