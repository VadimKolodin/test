<%@ page import="entity.TaskEntity" %>
<%@ page import="java.util.List" %>
<%@ page import="entity.MatrixTask" %>
<%@ page import="javax.naming.InitialContext" %>
<%@ page import="db.DAO" %>
<%@ page import="entity.StringTask" %>
<%@ page import="java.time.LocalDate" %><%--
  Created by IntelliJ IDEA.
  User: Яна
  Date: 19.05.2022
  Time: 14:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="styles.css" media="all"/>
</head>
<body>
<table id="choice_table">
    <tr>
        <th>Тип</th>
        <th>Дата</th>
        <th>Содержание</th>
    </tr>
    <%
        LocalDate from = null;
        if (request.getParameter("from")!=null){
            if (!request.getParameter("from").isEmpty()){
                from = LocalDate.parse(request.getParameter("from"));
            }
        }
        LocalDate to = null;
        if (request.getParameter("to")!=null){
            if (!request.getParameter("to").isEmpty()){
                to = LocalDate.parse(request.getParameter("to"));
            }
        }
        DAO dao = (DAO)(new InitialContext()).lookup("java:app/test/DAO");
        List<TaskEntity> tasks = dao.getAllTasks();
        for (TaskEntity task: tasks){
            if (to!=null&&task.getTdate().isAfter(to)){
                continue;
            }
            if(from!=null&&task.getTdate().isBefore(from)){
                continue;
            }
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
<script src="script/popup_control.js"></script>
</body>
</html>
