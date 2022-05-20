package servlets;

import entity.MatrixTask;
import entity.StringTask;
import entity.TaskEntity;
import entity.TaskValEntity;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@WebServlet("download")
public class DownloadTaskServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            int ttype = Integer.parseInt(req.getParameter("ttype"));
            TaskValEntity task = null;
            if (ttype == TaskEntity.MATRIX_TASK) {
                int[] m = new int[9];
                m[0] = Integer.parseInt(req.getParameter("x11"));
                m[1] = Integer.parseInt(req.getParameter("x12"));
                m[2] = Integer.parseInt(req.getParameter("x13"));
                m[3] = Integer.parseInt(req.getParameter("x21"));
                m[4] = Integer.parseInt(req.getParameter("x22"));
                m[5] = Integer.parseInt(req.getParameter("x23"));
                m[6] = Integer.parseInt(req.getParameter("x31"));
                m[7] = Integer.parseInt(req.getParameter("x32"));
                m[8] = Integer.parseInt(req.getParameter("x33"));
                task = new MatrixTask(m);
            } else {
                Pattern pattern = Pattern.compile("\"[a-zA-Zа-яА-Я0-9]+\"");
                Matcher matcher = pattern.matcher(req.getParameter("val1"));
                List<String> val1 = new ArrayList<>();
                String temp;
                while (matcher.find()) {
                    temp = matcher.group();
                    val1.add(temp.substring(1, temp.length() - 1));
                }
                matcher = pattern.matcher(req.getParameter("val2"));
                List<String> val2 = new ArrayList<>();
                while (matcher.find()) {
                    temp = matcher.group();
                    val2.add(temp.substring(1, temp.length() - 1));
                }
                task = new StringTask(val1, val2);
            }

            resp.setContentType("text/plain");
            Writer out = resp.getWriter();
            out.write(task.toString());
            out.close();

        } catch (Exception e) {
            resp.setContentType("text/plain");
            Writer out = resp.getWriter();
            out.write("Возникла ошибка");
            out.close();
        }
    }
}
