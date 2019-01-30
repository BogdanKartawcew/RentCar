<div class="authbar">
    <div class="row">
        <div class="col-md-12">

            <span class="floatLeft">

                <sec:authorize access="hasRole('USER')">
                    <span>Active moderator: <strong>${loggedinuser}</strong></span>
                </sec:authorize>
                <sec:authorize access="hasRole('ADMIN')">
                    <span>Active administrator: <strong>${loggedinuser}</strong></span>
                </sec:authorize>
                <sec:authorize access="hasRole('SUPERUSER')">
                    <span>Active superuser: <strong>${loggedinuser}</strong></span>
                </sec:authorize>
                <sec:authorize access="hasRole('TEMP')">
                    <span>Temporary user: <strong>${loggedinuser}</strong></span>
                </sec:authorize>
                |
                <button type="button" class="btn btn-link btn-sm"><a href="<c:url value='${COMMON_LOGOUT}' />"><span
                        class="glyphicon glyphicon-off"></span> Logout</a></button>
            </span>

            <span class="floatRight">
        <button type="button" class="btn btn-link btn-sm"><a href="<c:url value='${SUPPORT_MAIN}'/>">
               <span class="glyphicon glyphicon-home"></span>  Welcome page</a></button></span>

        </div>
    </div>
</div>
