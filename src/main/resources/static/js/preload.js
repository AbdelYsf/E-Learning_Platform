
// Hide Sections
$('.section').hide();

setTimeout(function () {
    $(document).ready(function () {
        // Show sections
        $('.section').fadeIn();

        // Hide preloader
        $('.loader').fadeOut();

    });
}, 1000);

