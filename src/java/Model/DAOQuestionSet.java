/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import Entity.Content;
import Entity.QuestionSet;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Asus
 */
public class DAOQuestionSet extends DBConnect {

    public List<QuestionSet> getAllQuestionSet() {
        List<QuestionSet> qs = new ArrayList<>();

        try {
            String sql = "select *from QuestionSet";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                QuestionSet qts = new QuestionSet();

                qts.setSetId(rs.getInt(1));
                qts.setTitle(rs.getString(2));
                qts.setUserAccountId(rs.getInt(3));
                qts.setSubjectId(rs.getInt(4));
                qts.setQuesId(rs.getInt(5));
                qts.setSetVote(rs.getInt(6));

                qs.add(qts);

            }
        } catch (Exception e) {
        }
        return qs;

    }

    public void insertQuestionSet(QuestionSet qs) {
        try {
            String sql = "INSERT INTO [dbo].[QuestionSet]\n"
                    + "           ([Title]\n"
                    + "           ,[UserAccountId]\n"
                    + "           ,[SubjectId]\n"
                    + "           ,[QuesId]\n"
                    + "           ,[SetVote])\n"
                    + "     VALUES\n"
                    + "           (?,?,?,?,?)";
            PreparedStatement stm = connection.prepareStatement(sql);

            stm.setString(1, qs.getTitle());
            stm.setInt(2, qs.getUserAccountId());
            stm.setInt(3, qs.getSubjectId());
            stm.setInt(4, qs.getQuesId());
            stm.setInt(5, qs.getSetVote());

            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOQuestionSet.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public QuestionSet getquestionSetById(int SetId) {
        try {
            String sql = "select *from QuestionSet where SetId =?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, SetId);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                return new QuestionSet(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4), rs.getInt(5), rs.getInt(6));

            }

        } catch (Exception e) {
        }
        return null;
    }

    public void updateQuestionSet(QuestionSet SetId) {
        try {
            String sql = "UPDATE [dbo].[QuestionSet]\n"
                    + "   SET  [Title] = ?\n"
                    + ",[UserAccountId] = ?\n"
                    + "      ,[SubjectId] = ?\n"
                    + "      ,[QuesId] = ?\n"
                    + "      ,[SetVote] = ?\n"
                    + " WHERE SetID =?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, SetId.getTitle());
            stm.setInt(2, SetId.getUserAccountId());
            stm.setInt(3, SetId.getSubjectId());
            stm.setInt(4, SetId.getQuesId());
            stm.setInt(5, SetId.getSetVote());
            stm.setInt(6, SetId.getSetId());

            stm.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(DAOQuestionSet.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void deleteQuestionSet(int SetId) {
        try {
            String sql = "DELETE FROM [dbo].[QuestionSet]\n"
                    + "      WHERE SetId =?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, SetId);
            stm.executeUpdate();

        } catch (Exception e) {
        }
    }

    public ArrayList<Content> getQuizContent() throws SQLException {
        ArrayList<Content> quizList = new ArrayList<>();
        String sql = "SELECT NormalQuestion.Content AS QuestionContent, NormalQuestionAnswer.Content AS AnswerContent\n"
                + "FROM QuestionSet\n"
                + "JOIN NormalQuestion ON QuestionSet.QuesId = NormalQuestion.QuesId\n"
                + "JOIN NormalQuestionAnswer ON NormalQuestion.QuesId = NormalQuestionAnswer.QuesId";
        PreparedStatement stm = connection.prepareStatement(sql);
        ResultSet rs = stm.executeQuery();
        while (rs.next()) {
            String QuizContent = rs.getString("QuestionContent");
            String Answer = rs.getString("AnswerContent");
            Content obj = new Content(QuizContent, Answer);
            quizList.add(obj);
    }

        return quizList;
}

    public ArrayList<QuestionSet> getData(String sql) {
        ArrayList<QuestionSet> questionset = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                int SetId = resultSet.getInt("SetId");
                String Title = resultSet.getString("Title");
                int UserAccountId = resultSet.getInt("UserAccountId");
                int SubjectId = resultSet.getInt("SubjectId");
                int QuesId = resultSet.getInt("QuesId");
                int SetVote = resultSet.getInt("SetVote");
                QuestionSet sb = new QuestionSet(SetId, Title, UserAccountId, SubjectId, QuesId, SetVote);
                questionset.add(sb);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOSubject.class.getName()).log(Level.SEVERE, null, ex);
        }
        return questionset;
    }

    public ResultSet getResultSet(String sql) {
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
