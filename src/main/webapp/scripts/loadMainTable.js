<!--скрипт, который будет выполнять http запрос. -->
function sendHall() {
    $.ajax({ //jQuery.
        type: 'GET',
        //url: 'http://localhost:8080/cinema/hall.do',
        url: 'http://localhost:8080/job4j_cinema_war_exploded/hall.do',
        data: 'name=' + $('#pls1_1').val(),
        dataType: 'text'
    }).done(function (data) {
        alert(data);//перейти на страницу payment.html. - https://developer.mozilla.org/ru/docs/Web/API/Window/location
        //в место Алерт Метода - location.assign("http://www.mozilla.org");
        //ЛИБО -  метод replace() для вставки значения location.pathname в хэш:
        //
        //     function reloadPageWithHash() {
        //         var initialPage = location.pathname;
        //         location.replace('http://example.com/#' + initialPage);
    }).fail(function (err) {
        alert(err);
    });
}

function onclick(e) {

    var printBlock = document.getElementById("table");
    var language = e.target.value;
    printBlock.textContent = "Вы выбрали: " + language;

}

// for (var i = 0; i < table.languages.length; i++) {
//     myForm.languages[i].addEventListener("click", onclick);
// }


