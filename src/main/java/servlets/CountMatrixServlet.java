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

@WebServlet("count_matrix")
public class CountMatrixServlet extends HttpServlet {
    @EJB
    TaskSolver solver;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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

        try {
            MatrixTask matrixTask = new MatrixTask(m);
            matrixTask = solver.findMagicMatrix(matrixTask);

            resp.setContentType("plain/text");
            Writer out = resp.getWriter();
           out.write(matrixTask.toString());
            out.close();
        }catch (Exception e){
            throw new RuntimeException("В процессе работы возникла ошибка");
        }
    }
}
