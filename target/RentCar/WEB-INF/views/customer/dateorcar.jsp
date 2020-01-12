<%@include file="../commonpatches/topjsp.jsp" %>
<html>
<head>
    <title>By date or by car</title>
    <%@include file="patches/head.jsp" %>
</head>
<body>
<%@include file="patches/navbar.jsp" %>

<div class="py-5">
    <div class="container">
        <div class="row">
            <div class="text-center mx-auto col-md-12">
                <h1>Search by date or by car? Just click!</h1>
            </div>
        </div>
        <div class="row">
            <div class="col-lg-6 p-3">
                <div class="card bg-dark text-center text-light">
                    <div class="card-body p-4">
                        <h3>BY DATE</h3>
                        <p>Choose the date and time, and look through available cars.</p>
                        <a class="btn btn-primary mt-3" href="/bydate">Start now</a>
                    </div>
                </div>
            </div>
            <div class="col-lg-6 p-3">
                <div class="card bg-dark text-center text-light">
                    <div class="card-body p-4">
                        <h3 contenteditable="true">BY CAR</h3>
                        <p>Choose the car and check when it available.</p>
                        <a class="btn btn-primary mt-3" href="/bycar">Start now</a>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>