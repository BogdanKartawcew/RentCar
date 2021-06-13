<%@include file="../commonpatches/topjsp.jsp" %>

<html>

<head>
    <title>Confirm ending</title>
    <%@include file="patches/head.jsp" %>
    <%--timepiker: --%>
    <link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap.min.css">
    <link href="//cdn.rawgit.com/Eonasdan/bootstrap-datetimepicker/e8bddc60e73c1ec2475f827be36e1957af72e2ea/build/css/bootstrap-datetimepicker.css"
          rel="stylesheet">
    <script type="text/javascript" src="//code.jquery.com/jquery-2.1.1.min.js"></script>
    <script type="text/javascript" src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.1/js/bootstrap.min.js"></script>
    <script src="//cdnjs.cloudflare.com/ajax/libs/moment.js/2.9.0/moment-with-locales.js"></script>
    <script src="//cdn.rawgit.com/Eonasdan/bootstrap-datetimepicker/e8bddc60e73c1ec2475f827be36e1957af72e2ea/src/js/bootstrap-datetimepicker.js"></script>
</head>

<body>
<div class="generic-container">
    <%@include file="patches/authheader.jsp" %>

    <div class="well lead">End reservation</div>
    <form:form method="POST" modelAttribute="reservationHistory" class="form-horizontal">
        <%--<form:input type="hidden" path="reservationId" id="reservationId"/>--%>


        <div class="row">
            <div class="form-actions floatRight">
                <input type="submit" value="Confirm ending" class="btn btn-primary btn-sm"/> or <a
                    href="<c:url value='${SUPPORT_RESERVATIONS_ALL}' />">Cancel</a>
            </div>
        </div>
    </form:form>
</div>
</body>
</html>

