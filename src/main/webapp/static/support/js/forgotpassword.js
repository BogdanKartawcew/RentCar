$(document).ready(function () {
    $('.forgot').click(function () {
        $('#forgot').addClass('show-page');
        $('#login').removeClass('show-page');

    });

    $('.sign').click(function () {
        $('#login').addClass('show-page');
        $('#forgot').removeClass('show-page');
    });
});