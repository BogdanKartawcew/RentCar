<button type="button" class="btn btn-outline-secondary btn-sm" data-toggle="collapse"
        data-target="#status-but"><span class="glyphicon glyphicon-info-sign"></span> Status's description
</button>

<div id="status-but" class="collapse">
    <div class="table-responsive">
        <table class="table table-hover">
            <thead>
            <tr>
                <th>Id</th>
                <th>Description</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${statuses}" var="status">
                <tr>
                    <td>${status.statusId}</td>
                    <td>${status.statusDesc}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
