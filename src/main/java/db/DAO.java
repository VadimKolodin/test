package db;

import entity.MatrixTask;
import entity.StringTask;
import entity.TaskEntity;
import entity.TaskValEntity;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Singleton;
import javax.ejb.Stateless;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Local
@Stateless
public class DAO implements Serializable {
    @EJB
    DbConnection connection;

    private static final String GET_ALL_TASKS = "select * from tasks";
    private static final String INSERT_TASK = "insert into tasks values(task_seq.NEXTVAL,?,?,REFID_SEQ.currval)";
    private static final String GET_ALL_MAGIC_MATRICES = "select val from magic_matrix";
    private static final String INSERT_MAGIC_MATRIX="insert into magic_matrix values(MID_SEQ.NEXTVAL,?)";
    private static final String GET_MATRIX_TASK_BY_ID = "select * from matrix_tasks where refid=?";
    private static final String INSERT_MATRIX_TASK="insert into matrix_tasks values(refid_seq.NEXTVAL, ?)";
    private static final String GET_STRING_TASK_BY_ID="select * from string_tasks where refid=?";
    private static final String INSERT_STRING_TASK="insert into string_tasks values(refid_seq.NEXTVAL,?,?)";

    public DAO(){}

    public List<TaskEntity> getAllTasks() throws SQLException {
        try (Statement st = connection.getConnection().createStatement()) {
            ResultSet rs = st.executeQuery(GET_ALL_TASKS);
            List<TaskEntity> tasks = new ArrayList<>();
            TaskValEntity task = null;
            while (rs.next()) {
                if (rs.getInt("TTYPE") == TaskEntity.MATRIX_TASK) {
                    try(PreparedStatement pr = connection.getConnection().prepareStatement(GET_MATRIX_TASK_BY_ID)) {
                        pr.setInt(1, rs.getInt("REFID"));
                        ResultSet rsSub = pr.executeQuery();
                        if (rsSub.next()) {
                            task = new MatrixTask(rsSub.getString("VAL"));
                        }

                    }
                } else {
                    try(PreparedStatement pr = connection.getConnection().prepareStatement(GET_STRING_TASK_BY_ID)) {
                        pr.setInt(1, rs.getInt("REFID"));
                        ResultSet rsSub = pr.executeQuery();
                        if (rsSub.next()) {
                            task = new StringTask(rsSub.getString("VAL1"), rsSub.getString("VAL2"));
                        }
                    }
                }

                tasks.add(new TaskEntity(
                        rs.getInt("TID"),
                        rs.getDate("TDATE").toLocalDate(),
                        rs.getInt("TTYPE"),
                        task
                ));
            }

            return tasks;
        }
    }

    public TaskEntity getTaskById(int tid) throws SQLException{
        try (Statement st = connection.getConnection().createStatement()) {
            ResultSet rs = st.executeQuery(GET_ALL_TASKS);
            TaskValEntity task = null;
            if (rs.next()) {
                if (rs.getInt("TTYPE") == TaskEntity.MATRIX_TASK) {
                    try(PreparedStatement pr = connection.getConnection().prepareStatement(GET_MATRIX_TASK_BY_ID)) {
                        pr.setInt(1, rs.getInt("REFID"));
                        ResultSet rsSub = pr.executeQuery();
                        if (rsSub.next()) {
                            task = new MatrixTask(rsSub.getString("VAL"));
                        }

                    }
                } else {
                    try(PreparedStatement pr = connection.getConnection().prepareStatement(GET_STRING_TASK_BY_ID)) {
                        pr.setInt(1, rs.getInt("REFID"));
                        ResultSet rsSub = pr.executeQuery();
                        if (rsSub.next()) {
                            task = new StringTask(rsSub.getString("VAL1"), rsSub.getString("VAL2"));
                        }
                    }
                }

                return new TaskEntity(
                        rs.getInt("TID"),
                        rs.getDate("TDATE").toLocalDate(),
                        rs.getInt("TTYPE"),
                        task
                );
            }

        }
        return null;
    }

    public void createTask(TaskEntity task) throws SQLException{
        try(PreparedStatement pr = connection.getConnection().prepareStatement(INSERT_TASK)){
            if (task.getTtype()==TaskEntity.MATRIX_TASK){
                try (PreparedStatement prSub = connection.getConnection().prepareStatement(INSERT_MATRIX_TASK)){
                    prSub.setString(1, ((MatrixTask)task.getTask()).getVal());
                    prSub.executeUpdate();
                }
            } else {
                try (PreparedStatement prSub = connection.getConnection().prepareStatement(INSERT_STRING_TASK)){
                    prSub.setString(1, ((StringTask)task.getTask()).getVal1());
                    prSub.setString(2, ((StringTask)task.getTask()).getVal2());
                    prSub.executeUpdate();
                }
            }
            pr.setDate(1,  Date.valueOf(task.getTdate()));
            pr.setString(2, Integer.toString(task.getTtype()));
            pr.executeUpdate();
            connection.getConnection().commit();
        } catch (Exception e){
            connection.getConnection().rollback();
            throw e;
        }
    }

    public List<MatrixTask> getAllMagicMatrices() throws SQLException{
        try(Statement st = connection.getConnection().createStatement()) {
            List<MatrixTask> matrices = new ArrayList<>();
            ResultSet rs = st.executeQuery(GET_ALL_MAGIC_MATRICES);
            while (rs.next()) {
                matrices.add(new MatrixTask(rs.getString("VAL")));
            }
            return matrices;
        }
    }
    public void createMagicMatrix(MatrixTask m) throws SQLException{
        try(PreparedStatement pr = connection.getConnection().prepareStatement(INSERT_MAGIC_MATRIX)){
            pr.setString(1, m.getVal());
            connection.getConnection().commit();
        }
    }

}
