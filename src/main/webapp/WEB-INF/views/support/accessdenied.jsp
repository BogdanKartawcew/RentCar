<%@include file="../commonpatches/topjsp.jsp" %>

<html>

<head>
    <title>Access denied</title>
    <%@include file="patches/head.jsp" %>
</head>

<body>
<div class="generic-container">
    <div class="authbar">
        <span>Dear <strong>${loggedinuser}</strong>, you are not authorized to access this page.</span>

        <span class="floatRight">
                <button type="button" class="btn btn-link btn-sm"><a href="<c:url value="${SUPPORT_MAIN}" />"><span
                        class="glyphicon glyphicon-home"></span>  Welcome page   </a>
        </button> | <button type="button" class="btn btn-link btn-sm"><a href="<c:url value="${COMMON_LOGOUT}" />"><span
                class="glyphicon glyphicon-off"></span> Logout</a></button>
        </span>
    </div>
</div>
</body>
</html>