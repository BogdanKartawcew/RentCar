<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<html>

<head>
    <title>Clients</title>
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
            <div class="row">
                <div class="col-md-12">
                    <span class="floatLeft"><span class="lead">Clients list</span></span>
                    <span class="floatRight">
                    <button type="button" class="btn btn-link btn-sm">
                    <a href="<c:url value='/support/clients/createclient'  />">
                    <span class="glyphicon glyphicon-plus"></span> Create new client</a>
                    </button>
                    </span>
                </div>
            </div>
        </div>

        <div class="table-responsive">
            <table class="table table-hover">
                <thead>
                <tr>
                    <th>Id</th>
                    <th>Surname</th>
                    <th>Name</th>
                    <th>Surname</th>
                    <th>E-mail</th>
                    <th>Company name</th>
                    <th>Gender</th>
                    <th width="100"></th>
                    <th width="100"></th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${clients}" var="client">
                    <tr>
                        <td>${client.clientId}</td>
                        <td>${client.pesel}</td>
                        <td>${client.clientFirstName}</td>
                        <td>${client.clientLastName}</td>
                        <td>${client.clientEmail}</td>
                        <td>${client.clientCompanyName}</td>
                        <td>${client.clientGender}</td>
                        <td><a href="<c:url value='/support/clients/editclient-${client.pesel}' />"
                               class="btn btn-success custom-width">edit</a>
                        </td>
                        <td><a href="<c:url value='/support/clients/deleteclient-${client.pesel}' />"
                               onclick="return confirm('Please confirm deleting')" class="btn btn-danger custom-width">delete</a>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>

    </div>
</div>
</body>
</html>
