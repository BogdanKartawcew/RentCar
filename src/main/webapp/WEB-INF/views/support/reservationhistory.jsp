<%@include file="../commonpatches/topjsp.jsp" %>
<html>
<head>
    <title>Reservations history</title>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <link href="<c:url value='/static/support/css/bootstrap.css' />" rel="stylesheet"/>
    <link href="<c:url value='/static/support/css/app.css' />" rel="stylesheet"/>
    <link href="/static/support/css/filters.css" rel="stylesheet" type="text/css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script src="/static/support/js/filters.js" type="text/javascript"></script>
    <script src="/static/support/js/rowsonpage.js" type="text/javascript"></script>
    <style>
        select {
            margin: 5px;
            width: 50px;
            height: 30px;
            padding: 3px 3px 3px;
            font-size: 14px;
            border: 1px solid #ccc;
        }
    </style>
</head>
<body>
<div class="generic-container">
    <%@include file="patches/authheader.jsp" %>
    <div class="panel panel-default filterable"><%--filterable!!!--%>
        <div class="panel-heading">
            <div class="row">
                <div class="col-lg-3">
                    <span>
                        <span class="lead">     History     </span>
                    </span>
                    <%@include file="patches/statuses.jsp" %>
                    <c:if test="${pagesAmount !=0}">
                        Rows per page:
                        <select id="dynamic_select">
                            <option value="" hidden>${rowsOnPage}</option>
                            <option value="<c:url value='${SUPPORT_RESERVATIONHISTORY_READY}1per15' />">15</option>
                            <option value="<c:url value='${SUPPORT_RESERVATIONHISTORY_READY}1per30' />">30</option>
                            <option value="<c:url value='${SUPPORT_RESERVATIONHISTORY_READY}1per60' />">60</option>
                        </select>
                    </c:if>
                </div>
                <span class="floatRight">
                <button type="button" class="btn btn-link btn-sm">
                    <a href="<c:url value='${SUPPORT_RESERVATIONS_ALL}' />">
                <span class="glyphicon glyphicon-arrow-left"></span> Back to reservations</a></button>
                </span>
            </div>
        </div>

        <div class="panel-heading">
            <div class="row">
            <span class="floatLeft">
                <div class="col-lg-3">
                    <button class="btn btn-default btn-xs btn-filter"><span class="glyphicon glyphicon-search"></span>
                        Search on page - on/off
                    </button>
                </div>
            </span>
            </div>
        </div>

        <div class="table-responsive">
            <table class="table table-hover">
                <thead>
                <tr class="filters">
                    <th><input type="text" class="form-control" placeholder="id" disabled></th>
                    <th><input type="text" class="form-control" placeholder="status" disabled></th>
                    <th><input type="text" class="form-control" placeholder="reservationId" disabled></th>
                    <th><input type="text" class="form-control" placeholder="carId" disabled></th>
                    <th><input type="text" class="form-control" placeholder="clientId" disabled></th>
                    <th><input type="text" class="form-control" placeholder="startDate" disabled></th>
                    <th><input type="text" class="form-control" placeholder="startTime" disabled></th>
                    <th><input type="text" class="form-control" placeholder="endDate" disabled></th>
                    <th><input type="text" class="form-control" placeholder="endTime" disabled></th>
                    <th><input type="text" class="form-control" placeholder="mileage" disabled></th>
                    <th><input type="text" class="form-control" placeholder="freeText" disabled></th>
                    <th><input type="text" class="form-control" placeholder="userId" disabled></th>
                    <th><input type="text" class="form-control" placeholder="timestamp" disabled></th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${reservationHistories}" var="reservationHistories">
                    <tr>
                        <td>${reservationHistories.id}</td>
                        <td>${reservationHistories.status}</td>
                        <td>${reservationHistories.reservationId}</td>
                        <td>${reservationHistories.carId}</td>
                        <td>${reservationHistories.clientId}</td>
                        <td>${reservationHistories.startDate}</td>
                        <td>${reservationHistories.startTime}</td>
                        <td>${reservationHistories.endDate}</td>
                        <td>${reservationHistories.endTime}</td>
                        <td>${reservationHistories.mileage}</td>
                        <td>${reservationHistories.freeText}</td>
                        <td>${reservationHistories.userId}</td>
                        <td>${reservationHistories.timestamp}</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>

        <c:if test="${pagesAmount !=0}">
            <div class="row">
        <span class="floatleft">
            <div class="col-lg-3">
                <%@include file="patches/paginator.jsp" %>
            </div>
        </span>
            </div>
        </c:if>
    </div>
</div>
</body>
</html>