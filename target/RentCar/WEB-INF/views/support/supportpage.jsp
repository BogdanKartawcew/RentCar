<%@include file="../commonpatches/topjsp.jsp" %>

<html>

<head>
    <title>Main page</title>
    <%@include file="patches/head.jsp" %>
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
    <button type="button" class="btn btn-link btn-sm"><a href="<c:url value="${COMMON_LOGOUT}" />"><span
            class="glyphicon glyphicon-off"></span> Logout</a></button>
    |
    <button type="button" class="btn btn-link btn-sm"><a href="<c:url value='${COMMON_WELCOME}' />"><span
            class="glyphicon glyphicon-home"></span> Customer page</a></button>
    <br>
    <br>
    <sec:authorize access="hasRole('ADMIN') or hasRole('SUPERUSER')">
        <div class="alert alert-danger">
            <a href="<c:url value='${SUPPORT_USERS_PAGES}'/>"> Users</a>
        </div>
    </sec:authorize>

    <div class="well">
        <a href="<c:url value='${SUPPORT_RESERVATIONS_ALL}'/>"> Reservation</a>
    </div>

    <div class="well">
        <a href="<c:url value='${SUPPORT_CLIENTS_PAGES}'/>"> Clients</a>
    </div>

    <div class="well">
        <a href="<c:url value='${SUPPORT_CARS_PAGES}'/>"> Cars</a>
    </div>

    <div class="well">
        <a href="<c:url value='${SUPPORT_LOG}'/>"> Web-log</a>
    </div>

    <sec:authorize access="hasRole('USER') or hasRole('SUPERUSER') or hasRole('TEMP')">
        <div class="well">
            <a href="<c:url value='${SUPPORT_MYPAGE}'/>"> My profile page</a>
        </div>
    </sec:authorize>
</div>
</body>

</html>
