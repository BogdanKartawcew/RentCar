<c:url var="loginUrl" value="/login"/>
<nav class="navbar navbar-expand-md navbar-dark bg-dark">
    <div class="container justify-content-center">
        <button class="navbar-toggler navbar-toggler-right border-0" type="button" data-toggle="collapse"
                data-target="#navbar9">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse text-center justify-content-center" id="navbar9">
            <ul class="navbar-nav"><a class="nav-link navbar-brand mr-0 text-white" href="<c:url value='/welcome' />"><i
                    class="fa d-inline fa-lg fa-car"></i>
                <b>RentCarBrand</b></a>
                <li class="nav-item mx-2"><a class="nav-link" href="<c:url value='/allcars' />">All cars</a></li>
                <li class="nav-item mx-2"><a class="nav-link" href="<c:url value='/aboutus' />">About us</a></li>
                <li class="nav-item mx-2">
                    <sec:authorize access="isAnonymous()">
                        <button type="button" class="btn btn-link" data-toggle="modal" data-target="#myModal">
                            Log in
                        </button>
                        <div class="modal fade" id="myModal" role="dialog">
                            <div class="modal-dialog">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <h4 class="modal-title">Log in form</h4>
                                        <button type="button" class="close" data-dismiss="modal">&times;
                                        </button>
                                    </div>
                                    <div class="modal-body">
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
                                                <label class="input-group-addon" for="username"></label>
                                                <input type="text" class="form-control" id="username" name="login"
                                                       placeholder="Enter login"
                                                       required>
                                            </div>
                                            <br>
                                            <div class="input-group input-sm">
                                                <label class="input-group-addon" for="password"></label>
                                                <input type="password" class="form-control" id="password"
                                                       name="password"
                                                       placeholder="Enter password" required>
                                            </div>
                                            <br>
                                            <div class="input-group input-sm">
                                                <div class="checkbox">
                                                    <label><input type="checkbox" id="rememberme" name="remember-me">
                                                        Remember
                                                        Me</label>
                                                </div>
                                            </div>
                                            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

                                            <div class="form-actions">
                                                <input type="submit"
                                                       class="btn btn-block btn-primary btn-default" value="Log in">
                                            </div>
                                        </form>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </sec:authorize>

                    <sec:authorize access="hasAnyRole('USER', 'ADMIN', 'SUPERUSER', 'TEMP')">
                        <a href="<c:url value='/support' />"
                           class="btn btn-link">Support page</a>
                    </sec:authorize>
                    <sec:authorize access="hasRole('CUSTOMER')">
                        <a href="<c:url value='/mypanel' />"
                           class="btn btn-link">My panel</a>
                    </sec:authorize>
                    <sec:authorize access="isAuthenticated()">
                        <a href="<c:url value='/logout' />"
                           class="btn btn-link">Log out</a>
                    </sec:authorize>
                </li>
            </ul>
        </div>
    </div>
</nav>