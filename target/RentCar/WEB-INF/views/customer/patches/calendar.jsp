<div id="my-calendar"></div>
<script type="application/javascript">
    var eventData = [
        <c:forEach items="${rents}" var="item" varStatus="loop">
        {date: "${item.key}", title: "${item.value}"}
        ${not loop.last ? ',' : ''}
        </c:forEach>
    ];

    console.log(eventData);


    $(document).ready(function () {
        $("#my-calendar").car_calendar({
            /*language: "pl",*/
            year: 2019,
            month: 2,
            cell_border: true,
            show_previous: false,
            show_next: 4,
            data: eventData
        });
    });
</script>