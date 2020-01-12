<%@include file="../commonpatches/topjsp.jsp" %>

<html>

<head>
    <title>Request access form</title>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <link href="<c:url value='/static/support/css/loginpage.css' />" rel="stylesheet"></link>
    <link href="<c:url value='/static/support/css/bootstrap.css' />" rel="stylesheet"></link>
    <link href="<c:url value='/static/support/css/app.css' />" rel="stylesheet"></link>
    <link rel="stylesheet" type="text/css"
          href="//cdnjs.cloudflare.com/ajax/libs/font-awesome/4.2.0/css/font-awesome.css"/>
</head>

<body>
<div id="mainWrapper">
    <section class="box-log">
        <div class="boxed show-page">
            <div class="content-box">
                <form:form method="POST" modelAttribute="user" class="form-horizontal">
                    <c:if test="${param.error != null}">
                        <div class="alert alert-danger">
                            <p>Such login already used. Please choose another one</p>
                        </div>
                    </c:if>

                    <div class="input-group input-sm">
                        <label class="input-group-addon" for="firstName"><i class="fa fa-user"></i></label>
                        <input type="text" id="firstName" class="form-control"
                               name="firstName"
                               placeholder="First name" required>
                    </div>

                    <div class="input-group input-sm">
                        <label class="input-group-addon" for="lastName"><i class="fa fa-user"></i></label>
                        <input type="text" id="lastName" class="form-control"
                               name="lastName"
                               placeholder="Last name" required>
                    </div>

                    <div class="input-group input-sm">
                        <label class="input-group-addon" for="login"><i class="fa fa-key"></i></label>
                        <input type="text" id="login" class="form-control" name="login"
                               placeholder="Login" required>
                    </div>
                    <div class="has-error">
                        <form:errors path="login" class="help-inline"/>
                    </div>

                    <div class="input-group input-sm">
                        <label class="input-group-addon" for="password"><i class="fa fa-lock"></i></label>
                        <input type="password" id="password" class="form-control"
                               name="password"
                               placeholder="Password" required>
                    </div>


                    <div class="input-group input-sm">
                        <label class="input-group-addon" for="email"><i class="fa fa-envelope"></i></label>
                        <input type="email" id="email" name="email" class="form-control"
                               placeholder="Email" required>
                    </div>


                    <div class="input-group input-sm">
                        <label class="input-group-addon" for="sex" id="sex"><i class="fa fa-male"></i><i
                                class="fa fa-female"></i></label>
                        <label for="soflow"></label>
                        <select required id="soflow" name="sex">
                            <option value="" hidden>Choose gender</option>
                            <option value="F">Female</option>
                            <option value="M">Male</option>
                        </select>
                    </div>
                    <br>

                    <form:input type="hidden" path="roles" id="type" value="4"/>

                    <div class="row">
                        <div class="form-actions floatRight">
                            <input type="submit" value="Register" class="btn btn-primary btn-sm"/> or <a
                                href="<c:url value='${COMMON_LOGIN}' />">Cancel</a>
                        </div>
                    </div>
                </form:form>
            </div>
        </div>
    </section>
</div>
</body>
</html>