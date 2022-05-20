$('#save').on('click', function(){
    if (select.options[select.selectedIndex].value==='matrix') {
        if (isNaN($('#x11').val())||isNaN($('#x12').val())||isNaN($('#x13').val())||
            isNaN($('#x21').val())||isNaN($('#x22').val())||isNaN($('#x23').val())||
            isNaN($('#x31').val())||isNaN($('#x32').val())||isNaN($('#x33').val())||
            $('#x11').val()===''||$('#x12').val()===''||$('#x13').val()===''||
            $('#x21').val()===''||$('#x22').val()===''||$('#x23').val()===''||
            $('#x31').val()===''||$('#x32').val()===''||$('#x33').val()==='') {
            alert('Введите числа');
        } else {
            $.ajax({
                url: '/test/save_task',
                method: 'POST',
                data: {
                    ttype: '0',
                    x11: $('#x11').val(),
                    x12: $('#x12').val(),
                    x13: $('#x13').val(),
                    x21: $('#x21').val(),
                    x22: $('#x22').val(),
                    x23: $('#x23').val(),
                    x31: $('#x31').val(),
                    x32: $('#x32').val(),
                    x33: $('#x33').val()
                },
                success: function (response) {
                    if (response === '1') {
                        $('#popup_content').load(document.URL + ' #popup_content');
                        reloadPopupTable();
                        alert("Сохранено");
                    } else {
                        alert("Возникла ошибка, задание не сохранено")
                    }
                }
            });
        }
    } else {
        let regex = /^ *(\"[a-zа-я0-9]+\", *)* *\"[a-zа-я0-9]+\" *$/im;

        if (!regex.test($('#val1').val())||!regex.test($('#val2').val())){
            alert('Напишите строки так: \"раз\", \"два\", \"три\"');
        } else {
            $.ajax({
                url: '/test/save_task',
                method: 'POST',
                data: {
                    ttype: '1',
                    val1: $('#val1').val(),
                    val2: $('#val2').val()
                },
                success: function (response) {
                    if (response === '1') {
                        $('#popup_content').load(document.URL + ' #popup_content');
                        reloadPopupTable();
                        alert("Сохранено");
                    } else {
                        alert("Возникла ошибка, задание не сохранено")
                    }
                }
            });
        }
    }
})

