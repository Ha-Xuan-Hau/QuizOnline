/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import Entity.Exam;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author hieul
 */
public class ExamDAO extends DBConnect {

    public int insertExam(Exam obj) {
        int n = 0;
        String sql = "INSERT INTO [dbo].[Exam]\n"
                + "           ([ClassId]\n"
                + "           ,[TeacherAccountId]\n"
                + "           ,[Title]\n"
                + "           ,[Summary]\n"
                + "           ,[Score]\n"
                + "           ,[StartDate]\n"
                + "           ,[EndDate]\n"
                + "           ,[Timer]\n"
                + "           ,[TakingTimes]\n"
                + "		   ,[Permission])\n"
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement pre = null;
        try {
            pre = connection.prepareStatement(sql);
            pre.setInt(1, obj.getClassId());
            pre.setInt(2, obj.getTeacherAccountId());
            pre.setString(3, obj.getTitle());
            pre.setString(4, obj.getSummary());
            pre.setDouble(5, obj.getScore());
            pre.setString(6, obj.getStartDate());
            pre.setString(7, obj.getEndDate());
            pre.setInt(8, obj.getTimer());
            pre.setInt(9, obj.getTakingTimes());
            pre.setBoolean(10, true);
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ExamDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (pre != null) {
                try {
                    pre.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }
        return n;
    }

    public int updateExam(Exam obj) {
        int n = 0;
        String sql = "UPDATE [dbo].[Exam]\n"
                + "   SET [ClassId] = ?\n"
                + "      ,[TeacherAccountId] = ?\n"
                + "      ,[Title] = ?\n"
                + "      ,[Summary] = ?\n"
                + "      ,[Score] = ?\n"
                + "      ,[StartDate] = ?\n"
                + "      ,[EndDate] = ?\n"
                + "      ,[Timer] = ?\n"
                + "      ,[TakingTimes] = ?\n"
                + "      ,[Permission] = ?\n"
                + " WHERE [ExamId] = ?";
        PreparedStatement pre = null;
        try {
            pre = connection.prepareStatement(sql);
            pre.setInt(1, obj.getClassId());
            pre.setInt(2, obj.getTeacherAccountId());
            pre.setString(3, obj.getTitle());
            pre.setString(4, obj.getSummary());
            pre.setDouble(5, obj.getScore());
            pre.setString(6, obj.getStartDate());
            pre.setString(7, obj.getEndDate());
            pre.setInt(8, obj.getTimer());
            pre.setInt(9, obj.getTakingTimes());
            pre.setBoolean(10, true);
            pre.setInt(11, obj.getExamId());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ExamDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (pre != null) {
                try {
                    pre.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }
        return n;
    }

    public int deleteExam(String examId) {
        int n = 0;
        PreparedStatement pre = null;
        String sql = "DELETE FROM [dbo].[Exam]\n"
                + "      WHERE [ExamId] = ?";
        try {
            pre = connection.prepareStatement(sql);
            pre.setString(1, examId);
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ExamDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (pre != null) {
                try {
                    pre.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ExamDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return n;
    }
    
    public ArrayList<Exam> getExam(String sql){
        ArrayList<Exam> list = new ArrayList<Exam>();
        Statement statement = null;
        ResultSet rs = null;
        try {
            statement = connection.createStatement(
                    ResultSet.TYPE_SCROLL_SENSITIVE, //hỗ trợ Thread Safe
                    ResultSet.CONCUR_UPDATABLE);
            rs = statement.executeQuery(sql);
            while (rs.next()) {
                int ExamId = rs.getInt("ExamId");
                int classId = rs.getInt("ClassId");
                int teacherAccountId = rs.getInt("TeacherAccountId");
                String title = rs.getString("Title");
                String summary = rs.getString("Summary");
                double score = rs.getDouble("Score");
                String startDate = rs.getString("StartDate");
                String endDate = rs.getString("EndDate");
                int timer = rs.getInt("Timer");
                int takingTimes = rs.getInt("TakingTimes");
                boolean permission = rs.getBoolean("Permission");
                Exam obj = new Exam(ExamId, classId, teacherAccountId, title, summary, score, startDate, endDate, timer, takingTimes, permission);
                list.add(obj);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }
        return list;
    }
    
        public ResultSet getExamDB(String sql) {
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
}
