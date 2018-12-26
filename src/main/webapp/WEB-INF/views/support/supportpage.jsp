<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<html>

<head>
    <title>Main page</title>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <link href="<c:url value='/static/css/bootstrap.css' />" rel="stylesheet"></link>
    <link href="<c:url value='/static/css/app.css' />" rel="stylesheet"></link>
</head>

<body>


<div class="generic-container">
    <sec:authorize access="hasRole('USER')">
        <span>Welcome, moderator <strong>${loggedinuser}</strong></span>
    </sec:authorize>
    <sec:authorize access="hasRole('ADMIN')">
        <span>Welcome, administrator <strong>${loggedinuser}</strong></span>
    </sec:authorize>
    <sec:authorize access="hasRole('SUPERUSER')">
        <span>Welcome, superuser <strong>${loggedinuser}</strong></span>
    </sec:authorize>
    <sec:authorize access="hasRole('TEMP')">
        <span>Welcome, temporary user <strong>${loggedinuser}</strong></span>
    </sec:authorize>
    |
    <button type="button" class="btn btn-link btn-sm"><a href="<c:url value="/logout" />"><span
            class="glyphicon glyphicon-off"></span> Logout</a></button>
    <br>
    <br>
    <sec:authorize access="hasRole('ADMIN') or hasRole('SUPERUSER')">
        <div class="alert alert-danger">
            <a href="<c:url value='/support/admin/userslist-1per15'/>"> Users</a>
        </div>
    </sec:authorize>

    <div class="well">
        <a href="<c:url value='/support/reservation'/>"> Reservation</a>
    </div>

    <div class="well">
        <a href="<c:url value='/support/clients-1per15'/>"> Clients</a>
    </div>

    <div class="well">
        <a href="<c:url value='/support/cars-1per15'/>"> Cars</a>
    </div>

    <div class="well">
        <a href="<c:url value='/support/log'/>"> Web-log</a>
    </div>

    <sec:authorize access="hasRole('USER') or hasRole('SUPERUSER') or hasRole('TEMP')">
        <div class="well">
            <a href="<c:url value='/support/mypage'/>"> My profile page</a>
        </div>
    </sec:authorize>
</div>
</body>

</html>
