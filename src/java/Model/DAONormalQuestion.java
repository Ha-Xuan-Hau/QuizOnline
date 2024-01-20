/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author phamg
 */
import Entity.NormalQuestion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
public class DAONormalQuestion extends DBConnect{
     public int insert(NormalQuestion obj) {
        int n = 0;
        String sql = "INSERT INTO [dbo].[NormalQuestion] ([Content]) VALUES(?)";
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setString(1, obj.getContent());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAONormalQuestion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public int update(NormalQuestion obj) {
        int n = 0;
        String sql = "UPDATE [dbo].[NormalQuestion] SET [Content] = ? WHERE [QuesId] = ?";
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setString(1, obj.getContent());
            pre.setInt(2, obj.getQuesId());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAONormalQuestion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public int delete(int quesId) {
        int n = 0;
        String sql = "DELETE FROM [dbo].[NormalQuestion] WHERE [QuesId] = ?";
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setInt(1, quesId);
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAONormalQuestion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public ArrayList<NormalQuestion> getData(String sql) {
        ArrayList<NormalQuestion> normalQuestions = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                int quesId = resultSet.getInt("QuesId");
                String content = resultSet.getString("Content");
                NormalQuestion normalQuestion = new NormalQuestion(quesId, content);
                normalQuestions.add(normalQuestion);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAONormalQuestion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return normalQuestions;
    }

    public static void main(String[] args) {
        NormalQuestion normalQuestion = new NormalQuestion("Sample Content");
        DAONormalQuestion dao = new DAONormalQuestion();
//
//        dao.insert(normalQuestion);
//        NormalQuestion updatedNormalQuestion = new NormalQuestion(1, "Updated Content");
//        dao.update(updatedNormalQuestion);
         dao.delete(1);
          dao.delete(3);
    String sql = "SELECT * FROM Subject";
    System.out.println(dao.getData(sql));
    }
}
