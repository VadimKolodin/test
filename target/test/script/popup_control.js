function PopUpShow(){
    $("#popup").show();
}
//Функция скрытия PopUp
function PopUpHide(){
    $("#popup").hide();
}

function PopUpConfirm(element){
    $("#popup").hide();
    if (element.className==='matrix_tr'){
        let list_inputs = element.getElementsByClassName('number_show');
        document.getElementById('task_select').getElementsByTagName('option')[0].selected = 'selected';
        document.getElementById('matrix_input').hidden=false;
        document.getElementById('matrix_output').hidden=false;
        document.getElementById('count_matrix').hidden=false;
        document.getElementById('string_input').hidden=true;
        document.getElementById('string_output').hidden=true;
        document.getElementById('count_string').hidden=true;
        $('#x11').val(list_inputs[0].value);
        $('#x12').val(list_inputs[1].value);
        $('#x13').val(list_inputs[2].value);
        $('#x21').val(list_inputs[3].value);
        $('#x22').val(list_inputs[4].value);
        $('#x23').val(list_inputs[5].value);
        $('#x31').val(list_inputs[6].value);
        $('#x32').val(list_inputs[7].value);
        $('#x33').val(list_inputs[8].value);
        document.getElementById('count_matrix').click();
    } else {
        document.getElementById('task_select').getElementsByTagName('option')[1].selected = 'selected';
        document.getElementById('matrix_input').hidden=true;
        document.getElementById('matrix_output').hidden=true;
        document.getElementById('count_matrix').hidden=true;
        document.getElementById('string_input').hidden=false;
        document.getElementById('string_output').hidden=false;
        document.getElementById('count_string').hidden=false;
        let list_inputs = element.getElementsByClassName('small_textarea');
        $('#val1').val(list_inputs[0].value);
        $('#val2').val(list_inputs[1].value);
        document.getElementById('count_string').click();
    }
}

document.getElementById('task_filter').addEventListener('input', function() {
    let select_filter = document.getElementById('task_filter');
    let list_matrix = document.getElementsByClassName("matrix_tr");
    let list_string = document.getElementsByClassName("string_tr");

    if (select_filter.options[select_filter.selectedIndex].value==='all'){

      for(let i=0; i<list_string.length;++i){
          list_string[i].hidden=false;
      }
      for(let i=0; i<list_matrix.length;++i){
          list_matrix[i].hidden=false;
      }
   } else if (select_filter.options[select_filter.selectedIndex].value==='matrix'){
        for(let i=0; i<list_string.length;++i){
            list_string[i].hidden=true;
        }
        for(let i=0; i<list_matrix.length;++i){
            list_matrix[i].hidden=false;
        }
   } else {
        for(let i=0; i<list_string.length;++i){
            list_string[i].hidden=false;
        }
        for(let i=0; i<list_matrix.length;++i){
            list_matrix[i].hidden=true;
        }
   }
});

function reloadPopupTable() {
    let fromdate =document.getElementById('date_filter_from').value;
    let todate = document.getElementById('date_filter_to').value;
    if (fromdate !== '' && todate !== '') {
        if (fromdate > todate){
            alert('Дата 1 должна быть раньше, чем дата 2');
        }
    }

    $.ajax({
        url: '/test/popup',
        method: 'GET',
        data: {
            from: fromdate,
            to: todate
        },
        success: function (response) {
            $('#popup_table').html(response);
        }
    });
}