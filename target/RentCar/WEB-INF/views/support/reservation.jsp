<%@include file="../commonpatches/topjsp.jsp" %>

<html>

<head>
    <title>Reservations</title>
    <%@include file="patches/head.jsp" %>
</head>

<body>
<div class="generic-container">
    <%@include file="patches/authheader.jsp" %>
    <div class="panel panel-default">

        <div class="btn-group btn-group-justified">
            <a href="<c:url value='${SUPPORT_RESERVATION_CREATE}' />"
               class="btn btn-default btn-sm"><span class="glyphicon glyphicon-pencil"></span> New order</a>
            <a href="<c:url value='${SUPPORT_RESERVATIONS_NOTCONFIRMED}' />"
               class="btn btn-default btn-sm"><span class="glyphicon glyphicon-hourglass"></span> Waiting for confirming</a>
            <a href="<c:url value='${SUPPORT_RESERVATIONS_RUNNING}' />"
               class="btn btn-default btn-sm"><span class="glyphicon glyphicon-flash"></span> Only running reservations</a>
            <a href="<c:url value='${SUPPORT_RESERVATIONS_FINISHED}' />"
               class="btn btn-default btn-sm"><span class="glyphicon glyphicon-check"></span> Finished - please confirm</a>
            <a href="<c:url value='${SUPPORT_FINANCIALCALCULATIONS}' />"
               class="btn btn-default btn-sm"><span class="glyphicon glyphicon-piggy-bank"></span> Financial
                calculations</a>
            <a href="<c:url value='${SUPPORT_RESERVATIONHISTORY_PAGES}' />"
               class="btn btn-default btn-sm"><span class="glyphicon glyphicon-calendar"></span> History</a>
        </div>


        <div class="panel-heading">
            <span class="floatLeft">
            <span class="lead">All reservations
                <a href="<c:url value='${SUPPORT_RECALCULATE}' />"
                   class="btn btn-secondary btn-sm">
                <span class="glyphicon glyphicon-refresh"></span> Refresh</a>
            </span>
                <%@include file="patches/statuses.jsp" %>
            </span>
        </div>

        <div class="table-responsive">
            <table class="table table-hover">
                <thead>
                <tr>
                    <th>ReservationId</th>
                    <th>ClientId</th>
                    <th>CarId</th>
                    <th>Start date</th>
                    <th>Start time</th>
                    <th>End date</th>
                    <th>End time</th>
                    <th>Status</th>
                    <th width="100"></th>
                    <th width="100"></th>
                    <th width="100"></th>
                    <th width="100"></th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${reservations}" var="reservation">
                    <tr>
                    <c:if test="${reservation.confirmed == 'false'}">
                        <tr class="alert alert-danger">
                    </c:if>
                    <td>${reservation.reservationId}</td>
                    <td>${reservation.clientId}</td>
                    <td>${reservation.carId}</td>
                    <td>${reservation.startDate}</td>
                    <td>${reservation.startTime}</td>
                    <td>${reservation.endDate}</td>
                    <td>${reservation.endTime}</td>
                    <td>${reservation.status}</td>
                    <%--CRUD buttons--%>
                    <%@include file="patches/rescrudbuttons.jsp" %>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>

    <div class="panel panel-default">
        <div class="panel-heading"><span class="lead">Checking availibility</span></div>
        <div class="table-responsive">
            <table class="table table-hover">
                <thead>
                <tr>
                    <th>AvailabilityId</th>
                    <th>CarId</th>
                    <th>Start date</th>
                    <th>Start time</th>
                    <th>End date</th>
                    <th>End time</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${checkAll}" var="checkAll">
                    <tr>
                        <td>${checkAll.availabilityId}</td>
                        <td>${checkAll.carId}</td>
                        <td>${checkAll.startDate}</td>
                        <td>${checkAll.startTime}</td>
                        <td>${checkAll.endDate}</td>
                        <td>${checkAll.endTime}</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>
</body>
</html>
