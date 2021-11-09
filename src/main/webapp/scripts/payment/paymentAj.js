function paymentAj() {
    const str2 = start();
    const s1 = $('#text').val();
    const s2 = $('#text2').val();
    $.ajax({
        type: 'Get',
        url: 'http://localhost:8080/job4j_cinema/shop.do',
        cache: false,
        data:
            'user=' + s1 + '&place=' + str2[0] + '.' + str2[1]
            + '&phone=' + s2,
        dataType: 'text'
    }).done(function () {
        document.location.href = "http://localhost:8080/job4j_cinema/";
    }).fail(function () {
        console.log('Ошибка');
    });
}