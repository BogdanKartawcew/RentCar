<%@include file="../commonpatches/topjsp.jsp" %>

<html>

<head>
    <title>Clients</title>
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
            <div class="row">
                <div class="col-md-12">
                    <span class="floatLeft">
                    <div class="col-md-1">
                    <span class="lead">Clients list</span>
                    </div>
                    <div class="col-md-2">
                        <c:if test="${pagesAmount !=0}">
                            Rows per page:
                            <select id="dynamic_select">
                            <option value="" hidden>${rowsOnPage}</option>
                            <option value="<c:url value='${SUPPORT_CLIENTS_READY}1per15' />">15</option>
                            <option value="<c:url value='${SUPPORT_CLIENTS_READY}1per30' />">30</option>
                            <option value="<c:url value='${SUPPORT_CLIENTS_READY}1per60' />">60</option>
                        </select>
                        </c:if>
                        </div>
                    </span>

                    <span class="floatRight">
                    <button type="button" class="btn btn-link btn-sm">
                    <a href="<c:url value='${SUPPORT_CLIENT_CREATE}'  />">
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
                    <th>Pesel</th>
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
                        <td><a href="<c:url value='${SUPPORT_CLIENT_EDIT_READY}${client.pesel}' />"
                               class="btn btn-success custom-width">edit</a>
                        </td>
                        <td><a href="<c:url value='${SUPPORT_CLIENT_DELETE_READY}${client.pesel}' />"
                               onclick="return confirm('Please confirm deleting')" class="btn btn-danger custom-width">delete</a>
                        </td>
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
