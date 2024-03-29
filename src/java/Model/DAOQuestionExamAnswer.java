package Model;

import Entity.questionExamAnswer;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DAOQuestionExamAnswer extends DBConnect {

    public int insertQuetionExamAnswer(questionExamAnswer obj) {
        int n = 0;
        String sql = "INSERT INTO [dbo].[QuestionExamAnswer]\n"
                + "           ([QuesId]\n"
                + "           ,[Content]\n"
                + "           ,[Correct]\n"
                + "           ,[Percent])\n"
                + "     VALUES(?,?,?,?)";

        System.out.println(sql);
        try {
            PreparedStatement pre = connection.prepareCall(sql);
            pre.setInt(1, obj.getQuesId());
            pre.setString(2, obj.getContent());
            pre.setBoolean(3, obj.isCorrect());
            pre.setDouble(4, obj.getPercent());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOQuestionExamAnswer.class.getName()).log(Level.SEVERE, null, ex);
        }

        return n;
    }

    public int updateQuetionExamAnswer(questionExamAnswer obj) {
        int n = 0;
        String sql = "UPDATE [dbo].[QuestionExamAnswer]\n"
                + "   SET [QuesId] = ?\n"
                + "      ,[Content] = ?\n"
                + "      ,[Correct] = ?\n"
                + "      ,[Percent] = ?\n"
                + " WHERE [AnswerId] = ?";
        try {
            PreparedStatement pre = connection.prepareCall(sql);
            pre.setInt(1, obj.getQuesId());
            pre.setString(2, obj.getContent());
            pre.setBoolean(3, obj.isCorrect());
            pre.setDouble(4, obj.getPercent());
            pre.setInt(5, obj.getAnswerId());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOQuestionExamAnswer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public int deleteQuetionExamAnswer(String id) {
        int n = 0;
        String sql = "DELETE FROM [QuestionExamAnswer] WHERE AnswerId = '" + id + "'";
        try {
            Statement state = connection.createStatement();
            n = state.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(DAOQuestionExamAnswer.class.getName()).log(Level.SEVERE, null, ex);
        }

        return n;
    }

    public ArrayList<questionExamAnswer> getData(String sql) {
        ArrayList<questionExamAnswer> List = new ArrayList<>();
        Statement state;
        try {
            state = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = state.executeQuery(sql);
            while (rs.next()) {
                int AnswerId = rs.getInt(1);
                int QuesId = rs.getInt(2);
                String Content = rs.getString(3);
                boolean Correct = rs.getBoolean(4);
                double Percent = rs.getDouble(5);
                questionExamAnswer obj = new questionExamAnswer(AnswerId, QuesId, Content, Correct, Percent);

                List.add(obj);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return List;
    }
}