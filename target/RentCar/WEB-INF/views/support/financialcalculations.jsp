<%@include file="../commonpatches/topjsp.jsp" %>

<html>

<head>
    <title>Financial calculations</title>
    <%@include file="patches/head.jsp" %>
</head>

<body>
<div class="generic-container">
    <%@include file="patches/authheader.jsp" %>
    <div class="panel panel-default">

        <div class="btn-group btn-group-justified">
            <a href="<c:url value='${SUPPORT_RESERVATION_CREATE}' />"
               class="btn btn-default btn-sm"><span class="glyphicon glyphicon-pencil"></span> New order</a>
            <a href="<c:url value='${SUPPORT_RESERVATIONS_NOTCONFIRMED}' />"
               class="btn btn-default btn-sm"><span class="glyphicon glyphicon-hourglass"></span> Waiting for confirming</a>
            <a href="<c:url value='${SUPPORT_RESERVATIONS_RUNNING}' />"
               class="btn btn-default btn-sm"><span class="glyphicon glyphicon-flash"></span> Only running reservations</a>
            <a href="<c:url value='${SUPPORT_RESERVATIONS_FINISHED}' />"
               class="btn btn-default btn-sm"><span class="glyphicon glyphicon-check"></span> Finished - please confirm</a>
            <a href="<c:url value='${SUPPORT_RESERVATIONHISTORY_PAGES}' />"
               class="btn btn-default btn-sm"><span class="glyphicon glyphicon-calendar"></span> History</a>
        </div>


        <div class="panel-heading">
            <span class="floatLeft">
            <span class="lead">Calculations
                <a href="<c:url value='${SUPPORT_RECALCULATE}' />"
                   class="btn btn-secondary btn-sm">
                <span class="glyphicon glyphicon-refresh"></span> Refresh</a>
            </span>
                <%@include file="patches/statuses.jsp" %>
            <span class="floatRight">
                <button type="button" class="btn btn-link btn-sm">
                    <a href="<c:url value='${SUPPORT_RESERVATIONS_ALL}' />">
                <span class="glyphicon glyphicon-arrow-left"></span> Back to reservations</a>
                </button>
            </span>
            </span>
        </div>
    </div>
</div>
</body>
</html>
