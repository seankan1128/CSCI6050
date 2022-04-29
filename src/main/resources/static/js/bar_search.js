$(function () {
    $('#search_btn').click(() => {
        if ($('#search_text').val() != '') {
            $.ajax({
                type: "post",
                url: "search",
                contentType: false,
                processData: false,
                data: new FormData($('#search_form')[0]),
                success: (data) => {
                    console.log(data)
                    $.session.set('search', $.param(data));
                    $.session.set('search_for', $('#search_text').val());
                    location.href = "search.html"
                },
            });
        }
    })
})