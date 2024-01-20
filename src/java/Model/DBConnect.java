package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class DBConnect {

    Connection connection = null;
    String connectionString = "jdbc:sqlserver://localhost:1433;databaseName=OnlineQuiz;user=sa;password=12345";
    public DBConnect(){
       
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            connection = DriverManager.getConnection(connectionString);
            
            if(connection != null){
                System.out.println("Connected");
            }
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println(ex.toString());
        }
    }
    public static void main(String[] args) {
        DBConnect a = new DBConnect();
 }
}
