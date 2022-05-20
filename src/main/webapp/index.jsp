<%@ page import="javax.naming.InitialContext" %>
<%@ page import="db.DAO" %>
<%@ page import="entity.TaskEntity" %>
<%@ page import="java.util.List" %>
<%@ page import="entity.MatrixTask" %>
<%@ page import="entity.StringTask" %><%--
  Created by IntelliJ IDEA.
  User: User
  Date: 18.05.2022
  Time: 2:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Test</title>
    <link rel="stylesheet" href="styles.css" media="all"/>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.0/jquery.min.js"></script>
</head>
<body>
<%
DAO dao = (DAO)(new InitialContext()).lookup("java:app/test/DAO");
%>

<div id="matrix_task">
    <div class="flex-container">
        <div class="flex-item">

            <div id="matrix_input">
                <h2>Введите матрицу:</h2>
                <input type="text" id="x11" name="x11" class="number_input" maxlength="1">
                <input type="text" id="x12" name="x12" class="number_input" maxlength="1">
                <input type="text" id="x13" name="x13" class="number_input" maxlength="1"><br>
                <input type="text" id="x21" name="x21" class="number_input" maxlength="1">
                <input type="text" id="x22" name="x22" class="number_input" maxlength="1">
                <input type="text" id="x23" name="x23" class="number_input" maxlength="1"><br>
                <input type="text" id="x31" name="x31" class="number_input" maxlength="1">
                <input type="text" id="x32" name="x32" class="number_input" maxlength="1">
                <input type="text" id="x33" name="x33" class="number_input" maxlength="1"><br>
            </div>
            <div id="string_input" hidden>

                <h3>Введите подстроки для поиска:</h3>
                <textarea id="val1" cols="30" rows="5"></textarea><br>
                <h3>Введите строки,<br>в которых будет вестись поиск:</h3>
                <textarea id="val2" cols="30" rows="5"></textarea><br>
            </div>
        </div>
        <div class="control_panel">
            <select id="task_select">
                <option value="matrix">Магическая матрица</option>
                <option value="string">Поиск подстрок</option>
            </select>
            <input type="button" id="count_matrix" value="&#8594;" class="arrow"/>
            <input type="button" id="count_string" value="&#8594;" class="arrow" hidden/>
            <input type="button" id="save" value="сохранить"/>
            <a id="download_link" href="#" download="task.json">
                <input type="button" class="export_btn" id="export" value="экспортировать"/>
            </a>
            <input type="button" id="load" value="загрузить" onclick="PopUpShow()"/>
            <input type="button" id="import" value="импортировать"/>
        </div>
        <div class="flex-item">
            <div id="matrix_output">

                <h2>Магическая матрица:</h2>
                <input type="text" id="y11" name="y11" class="number_input" maxlength="1" readonly>
                <input type="text" id="y12" name="y12" class="number_input" maxlength="1" readonly>
                <input type="text" id="y13" name="y13" class="number_input" maxlength="1" readonly><br>
                <input type="text" id="y21" name="y21" class="number_input" maxlength="1" readonly>
                <input type="text" id="y22" name="y22" class="number_input" maxlength="1" readonly>
                <input type="text" id="y23" name="y23" class="number_input" maxlength="1" readonly><br>
                <input type="text" id="y31" name="y31" class="number_input" maxlength="1" readonly>
                <input type="text" id="y32" name="y32" class="number_input" maxlength="1" readonly>
                <input type="text" id="y33" name="y33" class="number_input" maxlength="1" readonly><br>
            </div>
            <div id="string_output" hidden>

                <h2>Найденные подстроки:</h2>
                <textarea id="val" readonly cols="30" rows="5"></textarea><br>
            </div>
        </div>
    </div>
</div>

<div class="popup" id="popup"  hidden>
    <div class="popup_content" align="center">
        <div class="cross" onclick="PopUpHide()">X</div>


                <select id="task_filter" class="popup_select">
                    <option value="all">Все</option>
                    <option value="matrix">Матричные</option>
                    <option value="string">Строковые</option>
                </select>
                ; Дата: от <input type="date" id="date_filter_from" class="popup_date" onchange="reloadPopupTable()"> до <input type="date" id="date_filter_to" class="popup_date" onchange="reloadPopupTable()">
        <span id="popup_table">
        <table>
            <tr>
                <th>Тип</th>
                <th>Дата</th>
                <th>Содержание</th>
            </tr>
        <%
        List<TaskEntity> tasks = dao.getAllTasks();
        for (TaskEntity task: tasks){
            if (task.getTtype()==TaskEntity.MATRIX_TASK){
                MatrixTask temp = (MatrixTask)task.getTask();
            %>
            <tr onclick="PopUpConfirm(this)" class="matrix_tr">
                <td>Матричное</td>
                <td><input type="date" value="<%=task.getTdate()%>" class="popup_date" id="popup_content" readonly></td>
                <td>
                    <input type="text" class="number_show" maxlength="1" value="<%=temp.get(0)%>" readonly>
                    <input type="text" class="number_show" maxlength="1" value="<%=temp.get(1)%>" readonly>
                    <input type="text" class="number_show" maxlength="1" value="<%=temp.get(2)%>" readonly><br>
                    <input type="text" class="number_show" maxlength="1" value="<%=temp.get(3)%>" readonly>
                    <input type="text" class="number_show" maxlength="1" value="<%=temp.get(4)%>" readonly>
                    <input type="text" class="number_show" maxlength="1" value="<%=temp.get(5)%>" readonly><br>
                    <input type="text" class="number_show" maxlength="1" value="<%=temp.get(6)%>" readonly>
                    <input type="text" class="number_show" maxlength="1" value="<%=temp.get(7)%>" readonly>
                    <input type="text" class="number_show" maxlength="1" value="<%=temp.get(8)%>" readonly>
                </td>
            </tr>
            <%
            } else {
                StringTask temp = (StringTask) task.getTask();
            %>
            <tr onclick="PopUpConfirm(this)" class="string_tr">
                <td>Строковое</td>
                <td><input type="date" value="<%=task.getTdate()%>" class="popup_date" readonly></td>
                <td>
                    Подстроки:<br>
                    <textarea class="small_textarea" cols="30" rows="4" readonly><%=temp.getStringVal1()%></textarea><br>
                    Строки:<br>
                    <textarea class="small_textarea" cols="30" rows="4" readonly><%=temp.getStringVal2()%></textarea><br>
                </td>
            </tr>
            <%
            }
        }
        %>
        </table>
        </span>
    </div>
</div>

</div>
<input type="file" id="import_input" onchange="load_file()" hidden>
<script>
    let select=document.getElementById('task_select');
    select.addEventListener('input', function() {

        if (select.options[select.selectedIndex].value==='matrix'){

            document.getElementById('matrix_input').hidden=false;
            document.getElementById('matrix_output').hidden=false;
            document.getElementById('count_matrix').hidden=false;
            document.getElementById('string_input').hidden=true;
            document.getElementById('string_output').hidden=true;
            document.getElementById('count_string').hidden=true;
        } else {
            document.getElementById('matrix_input').hidden=true;
            document.getElementById('matrix_output').hidden=true;
            document.getElementById('count_matrix').hidden=true;
            document.getElementById('string_input').hidden=false;
            document.getElementById('string_output').hidden=false;
            document.getElementById('count_string').hidden=false;
        }
    });
</script>
<script src="script/matrix_count.js"></script>
<script src="script/string_count.js"></script>
<script src="script/task_save.js"></script>
<script src="script/popup_control.js"></script>
<script src="script/file_handle.js"></script>
</body>
</html>
