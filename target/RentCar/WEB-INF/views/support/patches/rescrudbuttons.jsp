<%-- EDIT - CONFIRM - CANCEL - END --%>
<c:if test="${reservation.confirmed == 'false'}">
    <td>
        <a href="<c:url value='${SUPPORT_RESERVATION_EDIT_READY}${reservation.reservationId}' />"
           class="btn btn-primary custom-width btn-sm">edit</a><%--primary--%>
    </td>
    <td>
        <a href="<c:url value='${SUPPORT_RESERVATION_CONFIRM_READY}${reservation.reservationId}' />"
           onclick="return confirm('Are you sure?')"
           class="btn btn-success custom-width btn-sm">confirm</a>
    </td>
</c:if>
<c:if test="${reservation.confirmed == 'true'}">
    <td>
        <a href="<c:url value='${SUPPORT_RESERVATION_EDIT_READY}${reservation.reservationId}' />"
           class="btn btn-warning custom-width btn-sm">edit</a><%--warning--%>
    </td>
    <td>
        <a href="<c:url value='${SUPPORT_RESERVATION_CONFIRM_READY}${reservation.reservationId}' />"
           onclick="return confirm('Are you sure?')"
           class="btn btn-success custom-width btn-sm disabled">confirm</a><%--disabled--%>
    </td>
</c:if>


<td>
    <a href="<c:url value='${SUPPORT_RESERVATION_CANCEL_READY}${reservation.reservationId}' />"
       onclick="return confirm('Please confirm canceling.')"
       class="btn btn-danger custom-width btn-sm">cancel</a>
</td>

<c:if test="${reservation.active == 'true'}">
    <td>
        <a href="<c:url value='${SUPPORT_RESERVATION_END_READY}${reservation.reservationId}' />"
           onclick="return confirm('Please confirm ending.')"
           class="btn btn-primary custom-width btn-sm">end</a>
    </td>
</c:if>
<c:if test="${reservation.active == 'false'}">
    <td>
        <a href="<c:url value='${SUPPORT_RESERVATION_END_READY}${reservation.reservationId}' />"
           onclick="return confirm('Please confirm ending.')"
           class="btn btn-danger custom-width btn-sm disabled">end</a><%--disabled--%>
    </td>
</c:if>