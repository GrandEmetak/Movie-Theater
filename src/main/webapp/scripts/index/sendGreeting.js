function sendGreeting() {
    $.ajax({
        type: 'POST',
        url: 'http://localhost:8080/job4j_cinema/hall.do',
        cache: false,
        dataType: 'json'
    }).done(function (response) {
        console.log(response)
        let str = '';
        let str2 = '';
        $.each(response, function (key, value) {
            let ind = value.row + '.' + value.cell;
            let ind1 = value.row + value.cell;

            str2 = '<tr> + <th> ' + (value.row) + '</th>';
            if (value.status === false) {
                str += '<td ' + 'id="r' + ind1 + '">' + '<label>' + '<input type="radio" name="place" value="1.1" disabled>' + 'Ряд ' + value.row
                    + ', Место ' + value.cell + '</label>' + '</td>';

            } else {
                str += '<td ' + 'id="r' + ind1 + '">' + '<label>' + '<input type="radio" name="place"' + 'value="' + ind + '">' + 'Ряд ' + value.row
                    + ', Место ' + value.cell + '</label>' + '</td>';
            }

            if (value.cell === 3) {
                $('#table tr:last').after(str2 + str + '<tr>');
                str = '';
            }
        });
    }).fail(function (err) {
        console.log(err);
        alert('Ошибка');
    });
}