<%@include file="../commonpatches/topjsp.jsp" %>

<html>

<head>
    <title>Users list</title>
    <%@include file="patches/head.jsp" %>
    <script src="/static/support/js/rowsonpage.js" type="text/javascript"></script>
    <style>
        select {
            margin: 5px;
            width: 50px;
            height: 30px;
            padding: 3px 3px 3px;
            font-size: 14px;
            border: 1px solid #ccc;
        }
    </style>
</head>

<body>
<div class="generic-container">
    <%@include file="patches/authheader.jsp" %>
    <div class="panel panel-default">

        <div class="panel-heading">
            <span class="floatLeft">
            <span class="lead">Users list
                <sec:authorize access="hasRole('ADMIN') or hasRole('SUPERUSER')">
                <a href="<c:url value='${SUPPORT_USER_CREATE}'  />"
                   class="btn btn-secondary btn-sm">
                <span class="glyphicon glyphicon-plus"></span> create new user</a>
                </sec:authorize>
            </span>
                <button type="button" class="btn btn-outline-secondary btn-sm" data-toggle="collapse"
                        data-target="#notConfirmed-but">
                    <span class="glyphicon glyphicon-check"></span> not-accepted users</button>
                    <div id="notConfirmed-but" class="collapse">
                        <div class="table-responsive">
                            <table class="table table-hover">
                                <thead>
                                    <tr>
                                        <th>Firstname</th>
                                        <th>Lastname</th>
                                        <th>Email</th>
                                        <th>Gender</th>
                                        <th>Login</th>
                                        <th width="100"></th>
                                        <sec:authorize access="hasRole('ADMIN') or hasRole('SUPERUSER')">
                                            <th width="100"></th>
                                            <th width="100"></th>
                                            <th width="100"></th>
                                        </sec:authorize>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <c:forEach items="${notConfirmed}" var="notConfirmed">
                                        <tr>
                                            <td>${notConfirmed.firstName}</td>
                                            <td>${notConfirmed.lastName}</td>
                                            <td>${notConfirmed.email}</td>
                                            <td>${notConfirmed.sex}</td>
                                            <td>${notConfirmed.login}</td>
                                            <sec:authorize access="hasRole('ADMIN') or hasRole('SUPERUSER')">
                                                <td>
                                                    <a href="<c:url value='${SUPPORT_USER_ACCEPT_READY}${notConfirmed.login}' />"
                                                       class="btn btn-warning custom-width">accept</a>
                                                </td>
                                                <td><a href="<c:url value='${SUPPORT_USER_EDIT_READY}${notConfirmed.login}' />"
                                                       class="btn btn-success custom-width">edit</a>
                                                </td>
                                                <td><a href="<c:url value='${SUPPORT_USER_DELETE_READY}${notConfirmed.login}' />"
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
                <c:if test="${pagesAmount !=0}">
                    Rows per page:
                    <select id="dynamic_select">
                            <option value="" hidden>${rowsOnPage}</option>
                            <option value="<c:url value='${SUPPORT_USERS_READY}1per15' />">15</option>
                            <option value="<c:url value='${SUPPORT_USERS_READY}1per30' />">30</option>
                            <option value="<c:url value='${SUPPORT_USERS_READY}1per60' />">60</option>
                        </select>
                </c:if>
            </span>
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
                        <th width="100"></th>
                    </sec:authorize>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${confirmed}" var="user">
                    <%--<c:forEach items="${roles}" var="role">--%>
                    <tr>
                        <td><a href="<c:url value='${SUPPORT_USERIMAGE_SHOW_READY}${user.login}' />"><img
                                src='${SUPPORT_USERIMAGE_SHOW_READY}${user.login}' class="img-thumbnail" height="180"
                                width="180"/></a></td>
                        <td>${user.firstName}</td>
                        <td>${user.lastName}</td>
                        <td>${user.email}</td>
                        <td>${user.sex}</td>
                        <td>${user.login}</td>
                        <sec:authorize access="hasRole('ADMIN') or hasRole('SUPERUSER')">
                            <td><a href="<c:url value='${SUPPORT_USER_EDIT_READY}${user.login}' />"
                                   class="btn btn-success custom-width">edit</a>
                            </td>
                            <td><a href="<c:url value='${SUPPORT_USER_DELETE_READY}${user.login}' />"
                                   onclick="return confirm('Please confirm deleting')"
                                   class="btn btn-danger custom-width">delete</a>
                            </td>
                        </sec:authorize>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
        <c:if test="${pagesAmount !=0}">
            <div class="row">
        <span class="floatleft">
            <div class="col-lg-3">
                <%@include file="patches/paginator.jsp" %>
            </div>
        </span>
            </div>
        </c:if>
    </div>
</div>
</body>
</html>