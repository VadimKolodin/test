$('#count_matrix').on('click', function(){

    if (isNaN($('#x11').val())||isNaN($('#x12').val())||isNaN($('#x13').val())||
        isNaN($('#x21').val())||isNaN($('#x22').val())||isNaN($('#x23').val())||
        isNaN($('#x31').val())||isNaN($('#x32').val())||isNaN($('#x33').val())||
        $('#x11').val()===''||$('#x12').val()===''||$('#x13').val()===''||
        $('#x21').val()===''||$('#x22').val()===''||$('#x23').val()===''||
        $('#x31').val()===''||$('#x32').val()===''||$('#x33').val()===''){
        alert('Введите числа');
    } else {
        $.ajax({
            url: '/test/count_matrix',
            method: 'POST',
            data: {
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
                let matrixJson = JSON.parse(response);
                matrixJson=matrixJson.MatrixTask.val;
                $('#y11').val(matrixJson[0]);
                $('#y12').val(matrixJson[1]);
                $('#y13').val(matrixJson[2]);
                $('#y21').val(matrixJson[3]);
                $('#y22').val(matrixJson[4]);
                $('#y23').val(matrixJson[5]);
                $('#y31').val(matrixJson[6]);
                $('#y32').val(matrixJson[7]);
                $('#y33').val(matrixJson[8]);
            },
            error: function(response){
                alert(response);
            }
        });
    }
})