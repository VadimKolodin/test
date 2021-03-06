DROP TABLE MAGIC_MATRIX;
DROP TABLE MATRIX_TASKS;
DROP TABLE STRING_TASKS;
DROP TABLE TASKS;
DROP SEQUENCE TASK_SEQ;
DROP SEQUENCE REFID_SEQ;
COMMIT;
CREATE SEQUENCE MID_SEQ START WITH 1 INCREMENT BY 1 NOCACHE NOCYCLE;
CREATE TABLE MAGIC_MATRIX(
    MID NUMBER(2) PRIMARY KEY,
    VAL VARCHAR2(9) NOT NULL UNIQUE
);
CREATE SEQUENCE TASK_SEQ START WITH 1 INCREMENT BY 1 NOCACHE NOCYCLE;
CREATE TABLE TASKS(
    TID NUMBER(4) PRIMARY KEY,
    TDATE DATE NOT NULL,
    TTYPE VARCHAR2(1) NOT NULL,
    REFID NUMBER(2) NOT NULL
);
CREATE SEQUENCE REFID_SEQ START WITH 1 INCREMENT BY 1 NOCACHE NOCYCLE;
CREATE TABLE MATRIX_TASKS(
    REFID NUMBER(4) PRIMARY KEY,
    VAL VARCHAR2(9) NOT NULL
);
CREATE TABLE STRING_TASKS(
    REFID NUMBER(4) PRIMARY KEY,
    VAL1 VARCHAR(200) NOT NULL,
    VAL2 VARCHAR(200) NOT NULL
);
COMMIT;