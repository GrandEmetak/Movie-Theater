function sendGreeting() {
    $.ajax({
        type: 'POST', /* Куда пойдет запрос */
        url: 'http://localhost:8080/job4j_cinema/hall.do',
        cache: false,
        // data: 'name=' + $('#email').val(), /* Параметры передаваемые в запросе. */
        dataType: 'json '  /* Тип данных в ответе (xml, json, script, html). */
    }).done(function (response) { /* В переменной data содержится ответ от hall.do. */
        let str = '';
        for (let i = 0; i < response.length; i++) {
            str += '<tr> + <th> ' + (i + 1) + '</th>';
            for (let j = 0; j < response[i].length; j++) {
                console.log(response[i][j])
                if (response[i][j].status === false) {
                    str += '<td>' + '<label>' + '<input type="radio" name="place" value="1.1" disabled>' + 'Ряд ' + response[i][j].row
                        + ', Место ' + response[i][j].cell + '</label>' + '</td>';
                } else {
                    let ind = response[i][j].row + '.' + response[i][j].cell;
                    str += '<td>' + '<label>' + '<input type="radio" name="place"' + 'value="' + ind + '">' + 'Ряд ' + response[i][j].row
                        + ', Место ' + response[i][j].cell + '</label>' + '</td>';
                }
            }
            str += '<tr>';
            $('#table tr:last').after(str);
            str = '';
        }
        console.log(str)
    }).fail(function (response) {
        console.log(response);
        alert('Ошибка');
    });
}