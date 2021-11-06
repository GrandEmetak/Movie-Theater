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

function click() {
    // let idSelect = document.querySelectorAll('id');
    // let printBlock1 = document.getElementById("id");
    // var printBlock = document.getElementById("table");
    let getIdlink = document.getElementsByName('place');
    let printBlock1 = document.getElementsByName('value');
    var language = e.target.value; //someObject
    printBlock.textContent = "Вы выбрали: " + language;
    console.log(printBlock);
    console.log(printBlock1);
    console.log(getIdlink);
    console.info("My first car was a",  ". The object is:", );

}

// for (var i = 0; i < table.languages.length; i++) {
//     myForm.languages[i].addEventListener("click", onclick);
// }


