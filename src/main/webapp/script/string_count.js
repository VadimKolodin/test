$('#count_string').on('click', function(){

    let regex = /^ *(\"[a-zа-я0-9]+\", *)* *\"[a-zа-я0-9]+\" *$/im;

    if (!regex.test($('#val1').val())||!regex.test($('#val2').val())){
        alert('Напишите строки так: \"раз\", \"два\", \"три\"');
    } else {
        $.ajax({
            url: '/test/count_string',
            method: 'POST',
            data: {
                val1: $('#val1').val(),
                val2: $('#val2').val()
            },
            success: function (response) {

                $('#val').val(response);
            },
            error: function(){
                alert('В процессе работы возникла ошибка');
            }
        });
    }
})