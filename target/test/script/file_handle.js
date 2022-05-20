
$('#import').on('click', function(){
    document.getElementById('import_input').click();
});
function load_file(){

    let reader = new FileReader();

    reader.onloadend =function(e){
    try {
        let fileJSON = JSON.parse(e.target.result.toString());
        if (fileJSON.hasOwnProperty('MatrixTask')) {
            let matrixJson = fileJSON.MatrixTask.val;
            document.getElementById('task_select').getElementsByTagName('option')[0].selected = 'selected';
            document.getElementById('matrix_input').hidden = false;
            document.getElementById('matrix_output').hidden = false;
            document.getElementById('count_matrix').hidden = false;
            document.getElementById('string_input').hidden = true;
            document.getElementById('string_output').hidden = true;
            document.getElementById('count_string').hidden = true;
            $('#x11').val(matrixJson[0]);
            $('#x12').val(matrixJson[1]);
            $('#x13').val(matrixJson[2]);
            $('#x21').val(matrixJson[3]);
            $('#x22').val(matrixJson[4]);
            $('#x23').val(matrixJson[5]);
            $('#x31').val(matrixJson[6]);
            $('#x32').val(matrixJson[7]);
            $('#x33').val(matrixJson[8]);
            document.getElementById('count_matrix').click();
        } else if (fileJSON.hasOwnProperty('StringTask')) {
            let stringJSON = fileJSON.StringTask;
            let val1 = stringJSON.val1;
            let val2 = stringJSON.val2;
            document.getElementById('task_select').getElementsByTagName('option')[1].selected = 'selected';
            document.getElementById('matrix_input').hidden = true;
            document.getElementById('matrix_output').hidden = true;
            document.getElementById('count_matrix').hidden = true;
            document.getElementById('string_input').hidden = false;
            document.getElementById('string_output').hidden = false;
            document.getElementById('count_string').hidden = false;

            let temp1 = '';
            for (let i=0; i< val1.length;++i){
                temp1 = temp1 +'\"'+ val1[i]+'\", ';
            }
            temp1 = temp1.substring(0, temp1.length-2);

            let temp2 = '';
            for (let i=0; i< val2.length;++i){
                temp2 = temp2 +'\"'+ val2[i]+'\", ';
            }
            temp2 = temp2.substring(0, temp2.length-2);
            $('#val1').val(temp1);
            $('#val2').val(temp2);
            document.getElementById('count_string').click();
        } else {
            alert("Файл имеет неверный формат или был поврежден");
        }
    }catch (e){
        alert("Файл имеет неверный формат или был поврежден");
    }

    };
    reader.readAsText(document.getElementById('import_input').files[0], 'UTF-8');
}
$('#export').on('mousedown', function(){
   if (select.options[select.selectedIndex].value==='matrix') {
       let url = '/test/download?ttype=0&';
       let input = document.getElementById('matrix_input').getElementsByClassName('number_input');
       for (let i = 0; i < input.length; ++i) {
           url = url + input[i].id + '=' + input[i].value + '&';
       }
       $('#download_link').prop('href', url);
   } else {
       let url = '/test/download?ttype=1&val1='+$('#val1').val()+'&val2='+$('#val2').val();
       $('#download_link').prop('href', url);
   }

});