<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <title>Reservations history</title>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <link href="<c:url value='/static/css/bootstrap.css' />" rel="stylesheet"/>
    <link href="<c:url value='/static/css/app.css' />" rel="stylesheet"/>
    <link href="/static/css/filters.css" rel="stylesheet" type="text/css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script src="/static/js/filters.js" type="text/javascript"></script>
</head>
<body>
<div class="generic-container">
    <%@include file="patches/authheader.jsp" %>
    <div class="panel panel-default filterable"><%--filterable!!!--%>
        <div class="panel-heading">
            <div class="row">
                <div class="col-lg-3">
                    <span><span class="lead">     History     </span></span>
                    <%@include file="patches/statuses.jsp" %>
                </div>
                <span class="floatRight">
                <button type="button" class="btn btn-link btn-sm">
                    <a href="<c:url value='/support/reservation' />">
                <span class="glyphicon glyphicon-arrow-left"></span> Back to reservations</a></button>
                </span>
            </div>
        </div>

        <div class="panel-heading">
            <div class="row">
                <div class="col-lg-3">
                    <button class="btn btn-default btn-xs btn-filter"><span class="glyphicon glyphicon-search"></span>
                        Simple search - on/off
                    </button>
                </div>
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
    </div>
</div>
</body>
</html>