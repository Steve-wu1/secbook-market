$(document).ready(function () {
    if (window.sessionStorage.getItem('name') !== undefined && window.sessionStorage.getItem('name') != null) {
        $("#user-info").html('Hi, ' + window.sessionStorage.getItem('name'))
        $("#nav-login-btn").html('登出');
    }
})

function suggestion() {
    $(location).attr('href', '/allSuggestion');
}

$(window).bind("load", function () {
    var footerHeight = 0, footerTop = 0, $footer = $("#footer");
    positionFooter();

    function positionFooter() {
        footerHeight = $footer.height();
        footerTop = ($(window).scrollTop() + $(window).height() - footerHeight) + "px";
        if (($(document.body).height() + footerHeight) < $(window).height()) {
            $footer.css({position: "absolute"}).stop().animate({top: footerTop});
        } else {
            $footer.css({position: "static"});


        }
    }

    $(window).scroll(positionFooter).resize(positionFooter);
});
