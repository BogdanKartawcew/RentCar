<%@include file="../commonpatches/topjsp.jsp" %>

<html>

<head>
    <title>Client</title>
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
    <form:form method="POST" modelAttribute="client" class="form-horizontal">
        <form:input type="hidden" path="clientId" id="clientId"/>

        <div class="row">
            <div class="form-group col-md-12">
                <label class="col-md-3 control-lable" for="pesel">PESEL</label>
                <div class="col-md-7">
                    <c:choose>
                        <c:when test="${edit}">
                            <form:input type="text" path="pesel" id="pesel" class="form-control input-sm"
                                        disabled="true"/>
                        </c:when>
                        <c:otherwise>
                            <form:input type="text" path="pesel" id="pesel" class="form-control input-sm"/>
                            <div class="has-error">
                                <form:errors path="pesel" class="help-inline"/>
                            </div>
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>
        </div>


        <div class="row">
            <div class="form-group col-md-12">
                <label class="col-md-3 control-lable" for="clientFirstName">First name</label>
                <div class="col-md-7">
                    <form:input type="text" path="clientFirstName" id="clientFirstName" class="form-control input-sm"/>
                    <div class="has-error">
                        <form:errors path="clientFirstName" class="help-inline"/>
                    </div>
                </div>
            </div>
        </div>

        <div class="row">
            <div class="form-group col-md-12">
                <label class="col-md-3 control-lable" for="clientLastName">Last name</label>
                <div class="col-md-7">
                    <form:input type="text" path="clientLastName" id="clientLastName" class="form-control input-sm"/>
                    <div class="has-error">
                        <form:errors path="clientLastName" class="help-inline"/>
                    </div>
                </div>
            </div>
        </div>

        <div class="row">
            <div class="form-group col-md-12">
                <label class="col-md-3 control-lable" for="clientEmail">E-mail</label>
                <div class="col-md-7">
                    <form:input type="text" path="clientEmail" id="clientEmail" class="form-control input-sm"/>
                    <div class="has-error">
                        <form:errors path="clientEmail" class="help-inline"/>
                    </div>
                </div>
            </div>
        </div>

        <div class="row">
            <div class="form-group col-md-12">
                <label class="col-md-3 control-lable" for="clientCompanyName">Company name</label>
                <div class="col-md-7">
                    <form:input type="text" path="clientCompanyName" id="clientCompanyName"
                                class="form-control input-sm"/>
                    <div class="has-error">
                        <form:errors path="clientCompanyName" class="help-inline"/>
                    </div>
                </div>
            </div>
        </div>

        <div class="row">
            <div class="form-group col-md-12">
                <label class="col-md-3 control-lable" for="clientGender">Gender</label>
                <div class="col-md-7">
                    <form:select class="form-control inputstl" path="clientGender" id="clientGender">
                        <option value=""></option>
                        <option value="M">Male</option>
                        <option value="F">Female</option>
                    </form:select>
                    <div class="has-error">
                        <form:errors path="clientGender" class="help-inline"/>
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
                    href="<c:url value='${SUPPORT_CLIENTS_PAGES}' />">Cancel</a>
            </div>
        </div>
    </form:form>
</div>
</body>
</html>