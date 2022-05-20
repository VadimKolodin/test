package db;


import javax.ejb.Local;
import javax.ejb.Singleton;
import javax.ejb.Stateless;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Locale;

@Local
@Singleton
public class DbConnection implements Serializable {
    private static final String URL = "jdbc:oracle:thin:@localhost:1521:XE";
    private static final String USER = "kvk_6309";
    private static final String PASSWORD = "1234";

    private Connection connection;

    public DbConnection(){
        Locale.setDefault(Locale.ENGLISH);
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            connection.setAutoCommit(false);
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }
    public void setConnection(Connection connection){
        this.connection=connection;
    }
    public Connection getConnection(){
        return connection;
    }
}
