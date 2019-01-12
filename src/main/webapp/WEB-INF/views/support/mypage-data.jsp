<%@include file="../commonpatches/topjsp.jsp" %>

<html>
<head>
    <title>Edit my data</title>
    <%@include file="patches/head.jsp" %>
</head>

<body>
<div class="generic-container">
    <%@include file="patches/authheader.jsp" %>
    <form:form method="POST" modelAttribute="user" enctype="multipart/form-data" class="form-horizontal">

        <form:input type="hidden" path="id" id="id"/>
        <form:input type="hidden" path="sex" id="sex"/>
        <form:input type="hidden" path="firstName" id="firstName"/>
        <form:input type="hidden" path="lastName" id="lastName"/>
        <form:input type="hidden" path="role" id="role"/>

        <div class="row">
            <div class="form-group col-md-12">
                <label class="col-md-3 control-lable" for="firstName">First name</label>
                <div class="col-md-7">
                    <form:input type="text" path="firstName" id="firstName" class="form-control input-sm"
                                disabled="true" value="${user.firstName}"/>
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
                    <form:input type="text" path="lastName" id="lastName" class="form-control input-sm" disabled="true"
                                value="${user.lastName}"/>
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
                    <form:input type="text" path="sex" id="sex" class="form-control input-sm" disabled="true"/>
                    <div class="has-error">
                        <form:errors path="sex" class="help-inline"/>
                    </div>
                </div>
            </div>
        </div>

        <sec:authorize access="hasRole('USER')">
            <form:input type="hidden" path="roles" value="1"/>
        </sec:authorize>
        <sec:authorize access="hasRole('ADMIN')">
            <form:input type="hidden" path="roles" value="2"/>
        </sec:authorize>
        <sec:authorize access="hasRole('SUPERUSER')">
            <form:input type="hidden" path="roles" value="3"/>
        </sec:authorize>
        <sec:authorize access="hasRole('TEMP')">
            <form:input type="hidden" path="roles" value="4"/>
        </sec:authorize>

        <div class="row">
            <div class="form-actions floatRight">
                <input type="submit" name="Update data" value="Update data" class="btn btn-primary btn-sm"/> or <a
                    href="<c:url value='${SUPPORT_MYPAGE}' />">Cancel</a>
            </div>
        </div>

    </form:form>
</div>
</body>
</html>

