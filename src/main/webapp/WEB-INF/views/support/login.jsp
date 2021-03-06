<%@include file="../commonpatches/topjsp.jsp" %>
<html>

<head>
    <title>Login page</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script src="/static/support/js/forgotpassword.js" type="text/javascript"></script>
    <link href="<c:url value='/static/support/css/loginpage.css' />" rel="stylesheet"></link>
    <link href="<c:url value='/static/support/css/bootstrap.css' />" rel="stylesheet"></link>
    <link href="<c:url value='/static/support/css/app.css' />" rel="stylesheet"></link>
    <link rel="stylesheet" type="text/css"
          href="//cdnjs.cloudflare.com/ajax/libs/font-awesome/4.2.0/css/font-awesome.css"/>
</head>

<body>
<div id="mainWrapper">
    <c:url var="loginUrl" value="${COMMON_LOGIN}"/>
    <section class="box-log">
        <div id="login" class="boxed show-page">
            <div class="content-box">
                <a href="<c:url value='${COMMON_WELCOME}' />"> <i class="fa fa-home fa-5x"></i></a>
                <form action="${loginUrl}" method="post" class="form-horizontal">
                    <c:if test="${param.error != null}">
                        <div class="alert alert-danger">
                            <p>Invalid credentials.</p>
                        </div>
                    </c:if>
                    <c:if test="${param.logout != null}">
                        <div class="alert alert-success">
                            <p>You have been logged out successfully.</p>
                        </div>
                    </c:if>
                    <div class="input-group input-sm">
                        <label class="input-group-addon" for="username"><i class="fa fa-user"></i></label>
                        <input type="text" class="form-control" id="username" name="login"
                               placeholder="Enter login"
                               required>
                    </div>
                    <div class="input-group input-sm">
                        <label class="input-group-addon" for="password"><i class="fa fa-lock"></i></label>
                        <input type="password" class="form-control" id="password" name="password"
                               placeholder="Enter password" required>
                    </div>
                    <span class="floatLeft">
                    <a href="<c:url value='${COMMON_RECRUITER}' />"
                       class="btn btn-link">Are you a recruiter? Click me!</a>
                        </span>
                    <span class="floatRight">

                        <button type="button" class="btn btn-link" data-toggle="modal" data-target="#myModal">
                        Haven't got an account?
                    </button>
                        </span>
                    <%@include file="patches/modal.jsp" %>
                    <br>

                    <div class="input-group input-sm">
                        <div class="checkbox">
                            <label><input type="checkbox" id="rememberme" name="remember-me"> Remember
                                Me</label>
                        </div>
                    </div>
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

                    <div class="form-actions">
                        <input type="submit"
                               class="btn btn-block btn-primary btn-default" value="Log in">
                    </div>

                    <input type="reset" value="Forgot password?" class="reset forgot"/>
                </form>
            </div>
        </div>
        <div id="forgot" class="boxed">
            <div class="content-box">
                <form action="">
                    <div class="group-form labels">
                        <h2>Password reset option is available only for customers. If you need to reset a password - to
                            get company's data secured - please contact your local administrator.</h2>
                    </div>
                    <%--<div class="input-group input-sm">
                        <label class="input-group-addon" for="username"><i class="fa fa-user"></i></label>
                        <input type="text" class="form-control" id="username" name="login"
                               placeholder="Enter login"
                               required>
                    </div>
                    <br>
                    <div class="form-actions">
                        <input type="submit"
                               class="btn btn-block btn-primary btn-default" value="Send">
                    </div>
                    <br>--%>
                    <input type="reset" value="Back to login" class="reset sign"/>
                </form>
            </div>
        </div>
    </section>
</div>
</body>
</html>