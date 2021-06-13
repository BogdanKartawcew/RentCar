<%@include file="../commonpatches/topjsp.jsp" %>

<html>

<head>
    <title>Not confirmed</title>
    <%@include file="patches/head.jsp" %>
</head>

<body>
<div class="generic-container">
    <%@include file="patches/authheader.jsp" %>
    <div class="panel panel-default">

        <div class="panel-heading">
            <span class="floatLeft">
            <span class="lead">Not confirmed
                <a href="<c:url value='${SUPPORT_RECALCULATE}' />"
                   class="btn btn-secondary btn-sm">
                <span class="glyphicon glyphicon-refresh"></span> Refresh</a>
            </span>
                <%@include file="patches/statuses.jsp" %>
            <span class="floatRight">
                <button type="button" class="btn btn-link btn-sm">
                    <a href="<c:url value='${SUPPORT_RESERVATIONS_ALL}' />">
                <span class="glyphicon glyphicon-arrow-left"></span> Back to reservations</a>
                </button>
            </span>
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
                <c:forEach items="${notConfirmedReservations}" var="reservation">
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
</div>
</body>
</html>
