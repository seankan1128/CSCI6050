$(function () {
    let user_info = undefined;
    let flag = 0;
    let sessionTemp = $.session.get('userInfo');
    let cookieTemp = $.cookie('userInfo');

    if (sessionTemp != undefined) {
        user_info = $.deparam(decodeURIComponent(sessionTemp));
        flag = 1;
    }
    else if (cookieTemp != undefined) {
        user_info = $.deparam(decodeURIComponent(cookieTemp));
        if (user_info.remember !== undefined) {
            flag = 1;
        }
    }

    if (flag) {
        let user_btn = $('#user');
        user_btn.empty();
        user_btn.append(
            $('<div class="dropdown">').append(
                $('<button class="btn btn-secondary dropdown-toggle" type="button" id=userMenuButton" data-toggle="dropdown" aria-expanded="false"></button>').html(user_info.ReturnUser.firstname),
                $('<div class="dropdown-menu" aria-labelledby="userMenuButton"></div>').append(
                    $('<a class="dropdown-item" href="#"></a>').html('Order'),
                    $('<a class="dropdown-item" href="edit_profile.html"></a>').html('Edit Profile'),
                    $('<a class="dropdown-item" type="button" id="log_out" href="#"></a>').html('Log out'),
                ),
            ),
        );
    }
    $('#log_out').click(() => {
        $.session.clear();
        $.removeCookie('userInfo', {path: '/'});
        location.href = "/";
    })
});
