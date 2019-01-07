<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>


<html>
<head>
    <title>Success</title>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <link href="<c:url value='/static/css/bootstrap.css' />" rel="stylesheet">
    <link href="<c:url value='/static/css/app.css' />" rel="stylesheet">
</head>
<body>
<div class="generic-container">
    <%@include file="patches/authheader.jsp" %>

    <div class="alert alert-success lead">
        ${usersuccess}
        ${clientsuccess}
        ${carsuccess}
        ${passwordsuccess}
        ${reservationsuccess}
        ${confirmreservationsuccess}
    </div>

    <span class="well floatRight">
			${usergoto}
			${clientgoto}
                ${cargoto}
			${passwordgoto}
        ${reservationgoto}
        ${confirmreservationgoto}
        ${carimagegoto}
        ${userimagegoto}
		</span>
</div>
</body>
</html>