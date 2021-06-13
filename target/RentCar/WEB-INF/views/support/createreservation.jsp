<%@include file="../commonpatches/topjsp.jsp" %>

<html>

<head>
    <title>Order</title>
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

    <c:choose>
        <c:when test="${edit}">
            <div class="well lead">Updating form</div>
        </c:when>
        <c:otherwise>
            <div class="well lead">Registration form</div>
        </c:otherwise>
    </c:choose>
    <form:form method="POST" modelAttribute="reservation" class="form-horizontal">
        <div class="well small">Our booking hours are from 6:00 to 22:00</div>
        <form:input type="hidden" path="reservationId" id="reservationId"/>
        <form:input type="hidden" path="status" id="status"/>
        <form:input type="hidden" path="confirmed" id="confirmed"/>
        <form:input type="hidden" path="active" id="active"/>
        <form:input type="hidden" path="finished" id="finished"/>

        <div class="row">
            <div class="form-group col-md-12">
                <label class="col-md-3 control-lable" for="clientId">Client</label>
                <div class="col-md-7">
                    <div class='col-sm-4 input-group date' id='clientId'>
                        <c:choose>
                            <c:when test="${edit}">
                                <form:select items="${clientsMap}" path="clientId" class="form-control input-sm"
                                             disabled="true"/>
                                <span class="input-group-addon"><span class="glyphicon glyphicon-user"></span></span>
                            </c:when>
                            <c:otherwise>
                                <form:select items="${clientsMap}" path="clientId" class="form-control input-sm"/>
                                <span class="input-group-addon"><span class="glyphicon glyphicon-user"></span></span>
                                <div class="has-error">
                                    <form:errors path="clientId" class="help-inline"/>
                                </div>
                            </c:otherwise>
                        </c:choose>
                    </div>
                </div>
            </div>
        </div>

        <div class="row">
            <div class="form-group col-md-12">
                <label class="col-md-3 control-lable" for="carId">carId</label>
                <div class="col-md-7">
                    <div class='col-sm-4 input-group date' id='carId'>
                        <form:select items="${carsMap}" path="carId" class="form-control input-sm"/>
                        <span class="input-group-addon"><span class="glyphicon glyphicon-road"></span></span>
                        <div class="has-error">
                            <form:errors path="carId" class="help-inline"/>
                        </div>
                    </div>
                </div>
            </div>
        </div>


        <div class="form-group col-md-12">
            <label class="col-md-3 control-label" for="startDate">Start date</label>
            <div class="col-md-7">
                <div class='col-sm-4 input-group date' id='startDate'>
                    <form:input type="datetime" path="startDate" id="startDate" class="form-control input-sm"/>
                    <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
                    <div class="has-error">
                        <form:errors path="startDate" class="help-inline"/>
                    </div>
                </div>
            </div>
        </div>

        <div class="form-group col-md-12">
            <label class="col-md-3 control-label" for="startTime">Start time</label>
            <div class="col-md-7">
                <div class='col-sm-4 input-group date' id='startTime'>
                    <form:input type="text" path="startTime" id="startTime" class="form-control input-sm"/>
                    <span class="input-group-addon"><span class="glyphicon glyphicon-time"></span></span>
                    <div class="has-error">
                        <form:errors path="startTime" class="help-inline"/>
                    </div>
                </div>
            </div>
        </div>


        <script type="text/javascript">
            $(function () {
                $('#startDate').datetimepicker({
                    format: 'L',
                    format: 'YYYY-MM-DD',
                    minDate: '${tomorrow}',
                    useCurrent: false
                });
                $('#endDate').datetimepicker({
                    format: 'L',
                    format: 'YYYY-MM-DD',
                    minDate: '${tomorrow}',
                    useCurrent: false
                });
                $("#startDate").on("dp.change", function (e) {
                    $('#endDate').data("DateTimePicker").minDate(e.date);
                });
                $("#endDate").on("dp.change", function (e) {
                    $('#startDate').data("DateTimePicker").maxDate(e.date);
                });
            });
        </script>


        <script type="text/javascript">
            $(function () {
                $('#startTime').datetimepicker({
                    format: 'LT',
                    locale: 'pl',
                    format: 'HH:mm:ss',
                    stepping: 30,
                    enabledHours: [6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22],
                    useCurrent: false
                });
            });
        </script>


        <div class="form-group col-md-12">
            <label class="col-md-3 control-label" for="endDate">End date</label>
            <div class="col-md-7">
                <div class='col-sm-4 input-group date' id='endDate'>
                    <form:input type="datetime" path="endDate" id="endDate" class="form-control input-sm"/>
                    <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
                </div>
                <div class="has-error">
                    <form:errors path="endDate" class="help-inline"/>
                </div>
            </div>
        </div>


        <div class="form-group col-md-12">
            <label class="col-md-3 control-label" for="endTime">End time</label>
            <div class="col-md-7">
                <div class='col-sm-4 input-group date' id='endTime'>
                    <form:input type="text" path="endTime" id="endTime" class="form-control input-sm"/>
                    <span class="input-group-addon"><span class="glyphicon glyphicon-time"></span></span>
                    <div class="has-error">
                        <form:errors path="endTime" class="help-inline"/>
                    </div>
                </div>
            </div>
        </div>

        <script type="text/javascript">
            $(function () {
                $('#endTime').datetimepicker({
                    format: 'LT',
                    locale: 'pl',
                    format: 'HH:mm:ss',
                    stepping: 30,
                    enabledHours: [6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22],
                    useCurrent: false //to not show default. This one is showing the data from DB.
                });
            });
        </script>


        <%--'L' for date only
        'LT' for time only
        'L LT' for date and time--%>

        <div class="row">
            <div class="form-actions floatRight">
                <c:choose>
                    <c:when test="${edit}">
                        <input type="submit" value="Update" class="btn btn-primary btn-sm"/>
                    </c:when>
                    <c:otherwise>
                        <input type="submit" value="Register" class="btn btn-primary btn-sm"/>
                    </c:otherwise>
                </c:choose>
                or <a
                    href="<c:url value='${SUPPORT_RESERVATIONS_ALL}' />">Cancel</a>
            </div>
        </div>
    </form:form>
</div>
</body>
</html>
