<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<html>

<head>
    <title>Financial calculations</title>
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

        <div class="btn-group btn-group-justified">
            <a href="<c:url value='/support/reservation/createreservation' />"
               class="btn btn-default btn-sm"><span class="glyphicon glyphicon-pencil"></span> New order</a>
            <a href="<c:url value='/support/reservation/notconfirmed' />"
               class="btn btn-default btn-sm"><span class="glyphicon glyphicon-hourglass"></span> Waiting for confirming</a>
            <a href="<c:url value='/support/reservation/running' />"
               class="btn btn-default btn-sm"><span class="glyphicon glyphicon-flash"></span> Only running reservations</a>
            <a href="<c:url value='/support/reservation/finished' />"
               class="btn btn-default btn-sm"><span class="glyphicon glyphicon-check"></span> Finished - please confirm</a>
            <a href="<c:url value='/support/reservation/reservationhistory' />"
               class="btn btn-default btn-sm"><span class="glyphicon glyphicon-calendar"></span> History</a>
        </div>


        <div class="panel-heading">
            <span class="floatLeft">
            <span class="lead">Calculations
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
    </div>
</div>
</body>
</html>
