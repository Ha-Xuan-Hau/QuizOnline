<<<<<<< HEAD
=======
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
>>>>>>> trongquan
package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
<<<<<<< HEAD
=======

/**
 *
 * @author ACER
 */
>>>>>>> trongquan
public class DBConnect {
    Connection connection=null;
    public DBConnect(String url, String user, String pass) {
        try {
            //call drive
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            //connect
            connection=DriverManager.getConnection(url,user,pass);
            System.out.println("connected");
<<<<<<< HEAD
        } catch (ClassNotFoundException | SQLException ex) {
=======
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DBConnect.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
>>>>>>> trongquan
            Logger.getLogger(DBConnect.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public DBConnect(){
        this("jdbc:sqlserver://localhost:1433;databaseName=OnlineQuiz","sa","123456");
    }
<<<<<<< HEAD
        public ResultSet getResultSet(String sql) {
=======
    public ResultSet getResultSet(String sql) {
>>>>>>> trongquan
        ResultSet rs = null;
        Statement state;
        try {
            state = connection.createStatement(
                    ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            rs = state.executeQuery(sql);
        } catch (SQLException ex) {
            Logger.getLogger(DBConnect.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }
    public static void main(String[] args){
        new DBConnect();
    }
}
