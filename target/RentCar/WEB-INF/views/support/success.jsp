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
        ${success}
    </div>

    <span class="well floatRight">
        ${next}
    </span>
</div>
</body>
</html>