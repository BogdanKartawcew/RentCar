<%@include file="../commonpatches/topjsp.jsp" %>


<html>
<head>
    <title>Success</title>
    <%@include file="patches/head.jsp" %>
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