<%@include file="../commonpatches/topjsp.jsp" %>

<html>

<head>
    <title>Registration form</title>
    <%@include file="patches/head.jsp" %>
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

    <form:form method="POST" modelAttribute="user" class="form-horizontal">
        <form:input type="hidden" path="id" id="id"/>

        <div class="row">
            <div class="form-group col-md-12">
                <label class="col-md-3 control-lable" for="firstName">First name</label>
                <div class="col-md-7">
                    <form:input type="text" path="firstName" id="firstName" class="form-control input-sm"/>
                    <div class="has-error">
                        <form:errors path="firstName" class="help-inline"/>
                    </div>
                </div>
            </div>
        </div>

        <div class="row">
            <div class="form-group col-md-12">
                <label class="col-md-3 control-lable" for="lastName">Last name</label>
                <div class="col-md-7">
                    <form:input type="text" path="lastName" id="lastName" class="form-control input-sm"/>
                    <div class="has-error">
                        <form:errors path="lastName" class="help-inline"/>
                    </div>
                </div>
            </div>
        </div>

        <div class="row">
            <div class="form-group col-md-12">
                <label class="col-md-3 control-lable" for="login">Login</label>
                <div class="col-md-7">
                    <form:input type="text" path="login" id="login" class="form-control input-sm"/>
                    <div class="has-error">
                        <form:errors path="login" class="help-inline"/>
                    </div>
                </div>
            </div>
        </div>

        <div class="row">
            <div class="form-group col-md-12">
                <label class="col-md-3 control-lable" for="password">Password</label>
                <div class="col-md-7">
                    <form:input type="password" path="password" id="password" class="form-control input-sm"/>
                    <div class="has-error">
                        <form:errors path="password" class="help-inline"/>
                    </div>
                </div>
            </div>
        </div>

        <div class="row">
            <div class="form-group col-md-12">
                <label class="col-md-3 control-lable" for="email">Email</label>
                <div class="col-md-7">
                    <form:input type="text" path="email" id="email" class="form-control input-sm"/>
                    <div class="has-error">
                        <form:errors path="email" class="help-inline"/>
                    </div>
                </div>
            </div>
        </div>

        <div class="row">
            <div class="form-group col-md-12">
                <label class="col-md-3 control-lable" for="sex">Gender</label>
                <div class="col-md-7">
                    <form:select class="form-control inputstl" path="sex" id="sex">
                        <option value="" hidden>Male/Female</option>
                        <option value="M">Male</option>
                        <option value="F">Female</option>
                    </form:select>
                    <div class="has-error">
                        <form:errors path="sex" class="help-inline"/>
                    </div>
                </div>
            </div>
        </div>

        <div class="row">
            <div class="form-group col-md-12">
                <label class="col-md-3 control-lable" for="roles">Roles</label>
                <div class="col-md-7">
                    <form:select path="roles" items="${roles}" multiple="false" itemValue="id" itemLabel="type" class="form-control input-sm" />
                    <div class="has-error">
                        <form:errors path="roles" class="help-inline"/>
                    </div>
                </div>
            </div>
        </div>

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
                    href="<c:url value='${SUPPORT_USERS_PAGES}' />">Cancel</a>
            </div>
        </div>
    </form:form>

</div>
</body>
</html>