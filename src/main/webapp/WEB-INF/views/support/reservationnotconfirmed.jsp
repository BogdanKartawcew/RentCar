<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<html>

<head>
    <title>Not confirmed</title>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <link href="<c:url value='/static/css/bootstrap.css' />" rel="stylesheet"></link>
    <link href="<c:url value='/static/css/app.css' />" rel="stylesheet"></link>
</head>

<body>
<div class="generic-container">
    <%@include file="patches/authheader.jsp" %>
    <div class="panel panel-default">

        <div class="panel-heading">
            <span class="floatLeft">
            <span class="lead">Not confirmed
                <a href="<c:url value='/support/reservation/recalculatestatuses' />"
                   class="btn btn-secondary btn-sm">
                <span class="glyphicon glyphicon-refresh"></span> Refresh</a>
            </span>
                <%@include file="patches/statuses.jsp" %>
            <span class="floatRight">
                <button type="button" class="btn btn-link btn-sm">
                    <a href="<c:url value='/support/reservation' />">
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
                    <%--<th>Confirmed</th>--%>
                    <th>Status</th>
                    <th width="100"></th>
                    <th width="100"></th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${notConfirmedReservations}" var="notConfirmedReservations">
                    <tr>
                    <%--<c:if test="${reservation.carId == 1}">
                        <tr class="alert alert-danger">
                    </c:if>--%>
                    <c:if test="${notConfirmedReservations.confirmed == 'false'}">
                        <tr class="alert alert-danger">
                    </c:if>
                    <td>${notConfirmedReservations.reservationId}</td>
                    <td>${notConfirmedReservations.clientId}</td>
                    <td>${notConfirmedReservations.carId}</td>
                    <td>${notConfirmedReservations.startDate}</td>
                    <td>${notConfirmedReservations.startTime}</td>
                    <td>${notConfirmedReservations.endDate}</td>
                    <td>${notConfirmedReservations.endTime}</td>
                    <%--<c:if test="${reservation.confirmed == 'true'}">
                        <td>
                            YES
                        </td>
                    </c:if>
                    <c:if test="${reservation.confirmed == 'false'}">
                        <td>
                            NO
                        </td>
                    </c:if>--%>
                    <td>${notConfirmedReservations.status}</td>

                    <%--BUTTONS--%>
                    <c:if test="${notConfirmedReservations.confirmed == 'false'}">
                        <td>
                            <a href="<c:url value='/support/reservation/editreservation-${notConfirmedReservations.reservationId}' />"
                               class="btn btn-primary custom-width btn-sm">edit</a><%--primary--%>
                        </td>
                        <td>
                            <a href="<c:url value='/support/reservation/confirmreservation-${notConfirmedReservations.reservationId}' />"
                               onclick="return confirm('Are you sure?')"
                               class="btn btn-success custom-width btn-sm">confirm</a>
                        </td>
                    </c:if>
                    <c:if test="${notConfirmedReservations.confirmed == 'true'}">
                        <td>
                            <a href="<c:url value='/support/reservation/editreservation-${notConfirmedReservations.reservationId}' />"
                               class="btn btn-warning custom-width btn-sm">edit</a><%--warning--%>
                        </td>
                        <td>
                            <a href="<c:url value='/support/reservation/confirmreservation-${notConfirmedReservations.reservationId}' />"
                               onclick="return confirm('Are you sure?')"
                               class="btn btn-success custom-width btn-sm disabled">confirm</a><%--disabled--%>
                        </td>
                    </c:if>


                    <td>
                        <a href="<c:url value='/support/reservation/cancelreservation-${notConfirmedReservations.reservationId}' />"
                           onclick="return confirm('Please confirm canceling.')"
                           class="btn btn-danger custom-width btn-sm">cancel</a>
                    </td>

                    <c:if test="${notConfirmedReservations.active == 'true'}">
                        <td>
                            <a href="<c:url value='/support/reservation/endreservation-${notConfirmedReservations.reservationId}' />"
                               onclick="return confirm('Please confirm ending.')"
                               class="btn btn-primary custom-width btn-sm">end</a>
                        </td>
                    </c:if>
                    <c:if test="${notConfirmedReservations.active == 'false'}">
                        <td>
                            <a href="<c:url value='/support/reservation/endreservation-${notConfirmedReservations.reservationId}' />"
                               onclick="return confirm('Please confirm ending.')"
                               class="btn btn-danger custom-width btn-sm disabled">end</a><%--disabled--%>
                        </td>
                    </c:if>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>
</div>
</body>
</html>
