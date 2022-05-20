package servlets;

import beans.TaskSaver;
import entity.MatrixTask;
import entity.StringTask;
import entity.TaskEntity;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@WebServlet("save_task")
public class SaveTaskServlet extends HttpServlet {
    @EJB
    TaskSaver saver;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int ttype = Integer.parseInt(req.getParameter("ttype"));
        boolean status = false;
        if (ttype == TaskEntity.MATRIX_TASK){
            int[] m = new int[9];
            m[0]=Integer.parseInt(req.getParameter("x11"));
            m[1]=Integer.parseInt(req.getParameter("x12"));
            m[2]=Integer.parseInt(req.getParameter("x13"));
            m[3]=Integer.parseInt(req.getParameter("x21"));
            m[4]=Integer.parseInt(req.getParameter("x22"));
            m[5]=Integer.parseInt(req.getParameter("x23"));
            m[6]=Integer.parseInt(req.getParameter("x31"));
            m[7]=Integer.parseInt(req.getParameter("x32"));
            m[8]=Integer.parseInt(req.getParameter("x33"));
            TaskEntity task = new TaskEntity(0, LocalDate.now(), TaskEntity.MATRIX_TASK, new MatrixTask(m));
            status = saver.saveTask(task);
        } else {
            Pattern pattern = Pattern.compile("\"[a-zA-Zа-яА-Я0-9]+\"");
            Matcher matcher = pattern.matcher(req.getParameter("val1"));
            List<String> val1 = new ArrayList<>();
            String temp;
            while(matcher.find()){
                temp = matcher.group();
                val1.add(temp.substring(1,temp.length()-1));
            }
            matcher = pattern.matcher(req.getParameter("val2"));
            List<String> val2 = new ArrayList<>();
            while(matcher.find()){
                temp = matcher.group();
                val2.add(temp.substring(1,temp.length()-1));
            }
            TaskEntity task = new TaskEntity(1, LocalDate.now(), TaskEntity.STRING_TASK, new StringTask(val1, val2));
            status = saver.saveTask(task);
        }
        resp.setContentType("text/plain");
        Writer out = resp.getWriter();
        out.write(status?"1":"0");
        out.close();
    }
}
