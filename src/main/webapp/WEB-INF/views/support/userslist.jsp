<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<html>

<head>
    <title>Users list</title>
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
                    <span class="floatLeft"><span class="lead">Users list</span></span>
                    <span class="floatRight">
                    <sec:authorize access="hasRole('ADMIN') or hasRole('SUPERUSER')">
                    <button type="button" class="btn btn-link btn-sm">
                    <a href="<c:url value='/support/admin/userslist/createuser'  />">
                    <span class="glyphicon glyphicon-plus"></span> Create new user</a>
                    </button>
                    </sec:authorize>
                    </span>
                </div>
            </div>
        </div>


        <button type="button" class="btn btn-outline-secondary btn-sm" data-toggle="collapse"
                data-target="#acception-but"><span class="glyphicon glyphicon-info-sign"></span> Status's description
        </button>

        <div id="acception-but" class="collapse">
            <div class="table-responsive">
                <table class="table table-hover">
                    <thead>
                    <tr>
                        <th>Id</th>
                        <th>Description</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${statuses}" var="status">
                        <tr>
                            <td>${status.statusId}</td>
                            <td>${status.statusDesc}</td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>

        <div class="table-responsive">
            <table class="table table-hover">
                <thead>
                <tr>
                    <th>Image</th>
                    <th>Firstname</th>
                    <th>Lastname</th>
                    <th>Email</th>
                    <th>Gender</th>
                    <th>Login</th>
                    <th width="100"></th>
                    <sec:authorize access="hasRole('ADMIN') or hasRole('SUPERUSER')">
                        <th width="100"></th>
                    </sec:authorize>
                    <sec:authorize access="hasRole('ADMIN') or hasRole('SUPERUSER')">
                        <th width="100"></th>
                    </sec:authorize>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${users}" var="user">
                    <%--<c:forEach items="${roles}" var="role">--%>
                    <tr>
                        <td><a href="<c:url value='/support/userimage-${user.login}' />"><img
                                src='/support/userimage-${user.login}' class="img-thumbnail" height="180"
                                width="180"/></a></td>
                        <td>${user.firstName}</td>
                        <td>${user.lastName}</td>
                        <td>${user.email}</td>
                        <td>${user.sex}</td>
                        <td>${user.login}</td>
                        <c:if test="${user.role == '4'}">
                            <td>
                                <a href="<c:url value='/support/admin/userslist/edituser-${user.login}' />"
                                   class="btn btn-warning custom-width">accept</a><%--warning--%>
                            </td>
                        </c:if>
                        <sec:authorize access="hasRole('ADMIN') or hasRole('SUPERUSER')">
                            <td><a href="<c:url value='/support/admin/userslist/edituser-${user.login}' />"
                                   class="btn btn-success custom-width">edit</a>
                            </td>
                        </sec:authorize>
                        <sec:authorize access="hasRole('ADMIN') or hasRole('SUPERUSER')">
                            <td><a href="<c:url value='/support/admin/userslist/deleteuser-${user.login}' />"
                                   onclick="return confirm('Please confirm deleting')"
                                   class="btn btn-danger custom-width">delete</a>
                            </td>
                        </sec:authorize>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>
</body>
</html>