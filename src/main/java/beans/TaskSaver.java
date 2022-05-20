package beans;

import db.DAO;
import entity.TaskEntity;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;
import java.io.Serializable;
import java.sql.SQLException;

@Local
@Stateless
public class TaskSaver implements Serializable {
    @EJB
    DAO dao;

    public boolean saveTask(TaskEntity task) {
        try {
            dao.createTask(task);
            return true;
        } catch (Exception e){
            return false;
        }
    }
}
