package entity;

import java.time.LocalDate;

public class TaskEntity {
    private int tid;
    private LocalDate tdate;
    private int ttype;
    private TaskValEntity task;

    public static final int MATRIX_TASK=0;
    public static final int STRING_TASK=1;

    public TaskEntity(int tid, LocalDate tdate, int ttype, TaskValEntity task) {
        this.tid = tid;
        this.tdate = tdate;
        this.ttype = ttype;
        this.task = task;
    }

    public int getTid() {
        return tid;
    }

    public void setTid(int tid) {
        this.tid = tid;
    }

    public LocalDate getTdate() {
        return tdate;
    }

    public void setTdate(LocalDate tdate) {
        this.tdate = tdate;
    }

    public int getTtype() {
        return ttype;
    }

    public void setTtype(int ttype) {
        this.ttype = ttype;
    }

    public TaskValEntity getTask() {
        return task;
    }

    public void setTask(TaskValEntity task) {
        this.task = task;
    }

    public int getMATRIX_TASK() {
        return MATRIX_TASK;
    }

    public int getSTRING_TASK() {
        return STRING_TASK;
    }
}
