/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import Entity.Subject;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author phamg
 */
public class DAOSubject extends DBConnect{
    public int insert(Subject obj) {
    int n = 0;
    String sql = "INSERT INTO [dbo].[Subject]" +
            "([SubjectCode], [SubjectName]) "
            + "VALUES(?, ?)";
    try {
        PreparedStatement pre = connection.prepareStatement(sql);
        pre.setString(1, obj.getSubjectCode());
        pre.setString(2, obj.getSubjectName());
        n = pre.executeUpdate();
    } catch (SQLException ex) {
        Logger.getLogger(DAOSubject.class.getName()).log(Level.SEVERE, null, ex);
    }
    return n;
}
 
    public int update(Subject obj) {
        int n = 0;
        String sql = "UPDATE [dbo].[Subject] SET [SubjectName] = ? WHERE [SubjectCode] = ?";
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setString(1, obj.getSubjectName());
            pre.setString(2, obj.getSubjectCode());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOSubject.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public int delete(String subjectCode) {
        int n = 0;
        String sql = "DELETE FROM [dbo].[Subject] WHERE [SubjectCode] = ?";
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setString(1, subjectCode);
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOSubject.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }   
 public ArrayList<Subject> getData(String sql) {
    ArrayList<Subject> subjects = new ArrayList<>();
    try {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);

        while (resultSet.next()) {
            int SubjectID = resultSet.getInt("SubjectID");
            String SubjectCode = resultSet.getString("SubjectCode");
            String SubjectName = resultSet.getString("SubjectName");
            Subject subject = new Subject(SubjectID, SubjectCode, SubjectName);
            subjects.add(subject);
        }
    } catch (SQLException ex) {
        Logger.getLogger(DAOSubject.class.getName()).log(Level.SEVERE, null, ex);
    }
    return subjects;
}
    public static void main(String[] args) {
        Subject sub = new Subject( "SWT", "Software Testing");
        DAOSubject dao = new DAOSubject();
        
        dao.insert(sub);
        Subject sub1 = new Subject( "SWT", "Software ");
//        dao.delete("SWT");
    String sql = "SELECT * FROM Subject";
    System.out.println(dao.getData(sql));
        
    }
}
