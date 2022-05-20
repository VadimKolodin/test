package servlets;

import beans.TaskSolver;
import entity.MatrixTask;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@WebServlet("count_string")
public class ChooseSubStringServlet extends HttpServlet {
    @EJB
    TaskSolver solver;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       try{
            Pattern pattern = Pattern.compile("\"[a-zA-Zа-яА-Я0-9]+\"");
            Matcher matcher = pattern.matcher(req.getParameter("val1"));
            List<String> val1 = new LinkedList<>();
            String temp;
            while(matcher.find()){
                temp = matcher.group();
                val1.add(temp.substring(1,temp.length()-1));
            }
            matcher = pattern.matcher(req.getParameter("val2"));
            List<String> val2 = new LinkedList<>();
            while(matcher.find()){
                temp = matcher.group();
                val2.add(temp.substring(1,temp.length()-1));
            }

            List<String> val = solver.chooseSubString(val1,val2);

            resp.setContentType("plain/text");
            Writer out = resp.getWriter();
            for(int i=0;i<val.size()-1;++i){
                out.write("\""+val.get(i)+"\", ");
            }
            if(val.size()!=0) {
                out.write("\"" + val.get(val.size() - 1) + "\"");
            }
            out.close();

        }catch (Exception e){
           throw new RuntimeException("В процессе работы возникла ошибка: "+e.getMessage());
        }
    }
}
