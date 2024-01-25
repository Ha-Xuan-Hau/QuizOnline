/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author phamg
 */
import Entity.NormalQuestionAnswer;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
public class DAONormalQuestionAnswer extends DBConnect {
    
    public int insert(NormalQuestionAnswer obj) {
        int n = 0;
        String sql = "INSERT INTO [dbo].[NormalQuestionAnswer] "
                + "([QuesId], [Content], [Correct], [Percent]) "
                + "VALUES(?, ?, ?, ?)";
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setInt(1, obj.getQuesId());
            pre.setString(2, obj.getContent());
            pre.setBoolean(3, obj.isCorrect());
            pre.setDouble(4, obj.getPercent());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAONormalQuestionAnswer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public int update(NormalQuestionAnswer obj) {
        int n = 0;
        String sql = "UPDATE [dbo].[NormalQuestionAnswer] "
                + "SET [QuesId] = ?, [Content] = ?, [Correct] = ?, [Percent] = ? "
                + "WHERE [AnswerId] = ?";
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setInt(1, obj.getQuesId());
            pre.setString(2, obj.getContent());
            pre.setBoolean(3, obj.isCorrect());
            pre.setDouble(4, obj.getPercent());
            pre.setInt(5, obj.getAnswerId());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAONormalQuestionAnswer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public int delete(int answerId) {
        int n = 0;
        String sql = "DELETE FROM [dbo].[NormalQuestionAnswer] WHERE [AnswerId] = ?";
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setInt(1, answerId);
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAONormalQuestionAnswer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public ArrayList<NormalQuestionAnswer> getData(String sql) {
        ArrayList<NormalQuestionAnswer> normalQuestionAnswers = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                int answerId = resultSet.getInt("AnswerId");
                int quesId = resultSet.getInt("QuesId");
                String content = resultSet.getString("Content");
                boolean correct = resultSet.getBoolean("Correct");
                double percent = resultSet.getDouble("Percent");

                NormalQuestionAnswer normalQuestionAnswer = new NormalQuestionAnswer(answerId, quesId, content, correct, percent);
                normalQuestionAnswers.add(normalQuestionAnswer);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAONormalQuestionAnswer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return normalQuestionAnswers;
    }

    public static void main(String[] args) {
        NormalQuestionAnswer nqa = new NormalQuestionAnswer(1, "Sample Content", true, 75.5);
        DAONormalQuestionAnswer dao = new DAONormalQuestionAnswer();
//        dao.insert(nqa);
//        NormalQuestionAnswer nqa1 = new NormalQuestionAnswer(5,1, "Updated Content", false, 50.0);
//        dao.update(nqa1);
         dao.delete(5);
    String sql = "SELECT * FROM Subject";
    System.out.println(dao.getData(sql));
    }
}
