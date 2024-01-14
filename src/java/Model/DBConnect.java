/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author hieul
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author hieul
 */
public class DBConnect {

    Connection conn = null;

    public DBConnect(String url, String user, String pass) {
        try {
            //call driver
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            //connect
            conn = DriverManager.getConnection(url, user, pass);
            System.out.println("Connected");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DBConnect.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(DBConnect.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public DBConnect() {
        this("jdbc:sqlserver://localhost:1433;databaseName=OnlineQuiz", "sa", "123456");
    }

    public ResultSet getDataDB(String sql) {
        ResultSet rs = null;
        Statement state;
        try {
            state = conn.createStatement(
                    ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            rs = state.executeQuery(sql);
        } catch (SQLException ex) {
            Logger.getLogger(DBConnect.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }

    public static void main(String[] args) {
        new DBConnect();
    }
}

