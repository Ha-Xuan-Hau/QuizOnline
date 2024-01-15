/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import entity.quetionExam;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ACER
 */
public class DAOQuetionExam extends DBConnect {

    public int insertQuetionExam(quetionExam obj) {
        int n = 0;
        String sql = "INSERT INTO [dbo].[QuestionExam]\n"
                + "           ([ExamId]\n"
                + "           ,[Content]\n"
                + "           ,[Score])\n"
                + "     VALUES(?,?,?)";
        
        System.out.println(sql);
        try {
            PreparedStatement pre = connnection.prepareCall(sql);
            pre.setInt(1, obj.getExamId());
            pre.setString(2, obj.getContent());
            pre.setDouble(3, obj.getScore());
            System.out.println(sql);
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOQuetionExam.class.getName()).log(Level.SEVERE, null, ex);
        }
        //run

        return n;
    }

    public int updateQuetionExam(quetionExam obj) {
        int n = 0;
        String sql = "UPDATE [dbo].[QuestionExam]\n"
                + "   SET [ExamId] = ?\n"
                + "      ,[Content] = ?\n"
                + "      ,[Score] = ?\n"
                + " WHERE [QuesId] = ?";
        try {
            PreparedStatement pre = connnection.prepareCall(sql);
            //pre.setDate(indexOf?, para);
            //DataType is datatype of field; indexOf? start is 1        
            pre.setInt(1, obj.getExamId());
            pre.setString(2, obj.getContent());
            pre.setDouble(3, obj.getScore());
            pre.setInt(4, obj.getQuesId());
            System.out.println(sql);
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOQuetionExam.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }
    
    public int deleteQuetionExam(String id) {
        int n = 0;
        String sql = "delete from [QuestionExam] where QuesId = '" + id + "'";
        try {
            Statement state = connnection.createStatement();
            n = state.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(DAOQuetionExam.class.getName()).log(Level.SEVERE, null, ex);
        }

        return n;
    }
    public ArrayList<quetionExam> getData(String sql) {
        ArrayList<quetionExam> List = new ArrayList<>();
        Statement state;
        try {
            state = connnection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = state.executeQuery(sql);
            while (rs.next()) {
                int ExamId = rs.getInt(1);
                int QuesId = rs.getInt(2);
                String Content = rs.getString(3);
                double Score = rs.getDouble(4);
                quetionExam obj = new quetionExam(ExamId, QuesId, Content, Score);

                List.add(obj);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return List;

    }

}
