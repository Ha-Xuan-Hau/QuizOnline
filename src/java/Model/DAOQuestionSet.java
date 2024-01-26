/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import Entity.QuestionSet;

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
            String sql = "select * from QuestionSet";
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

public List<QuestionSet> getTop3() {
    List<QuestionSet> list = new ArrayList<>();

    try {
        String query = "SELECT TOP 9 * FROM QuestionSet ORDER BY SetId DESC";
        try (PreparedStatement stm = connection.prepareStatement(query);
             ResultSet rs = stm.executeQuery()) {
            while (rs.next()) {
                QuestionSet questionSet = new QuestionSet();
                questionSet.setSetId(rs.getInt("SetId"));
                questionSet.setTitle(rs.getString("Title"));
                questionSet.setUserAccountId(rs.getInt("UserAccountId"));
                questionSet.setSubjectId(rs.getInt("SubjectId"));
                questionSet.setQuesId(rs.getInt("QuesId"));
                questionSet.setSetVote(rs.getInt("SetVote"));
                list.add(questionSet);
            }
        }
    } catch (SQLException e) {
    }
    return list;
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
    public int countQuest(String keyword){
        try {
            String sql ="select count(*)  from QuestionSet where Title like ?";    
               PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, "%" + keyword + "%");
           ResultSet rs = stm.executeQuery();
           while(rs.next()){
               return rs.getInt(1);
               
           }
        } catch (Exception e) {
        }
        return 0;
    }

    public List<QuestionSet> search(String keyword) {
        List<QuestionSet> list = new ArrayList<>();
        try {
            String sql = "select *  from QuestionSet where Title like ? ";

            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, "%" + keyword + "%");
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                QuestionSet qs = new QuestionSet();
                qs.setSetId(rs.getInt(1));
                qs.setTitle(rs.getString(2));
                qs.setUserAccountId(rs.getInt(3));
                qs.setSubjectId(rs.getInt(4));
                qs.setQuesId(rs.getInt(5));
                qs.setSetVote(rs.getInt(6));

                list.add(qs);

            }
        } catch (Exception ex) {
            Logger.getLogger(DAOSubject.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public List<QuestionSet> getQuestionSetWithPagging(int page, int PAGE_SIZE) {
        List<QuestionSet> list = new ArrayList<>();
        try {
            String sql = "select *  from QuestionSet order by SetId\n"
                    + "offset (?-1)*? row fetch next ? rows only";

            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, page);
            stm.setInt(2, PAGE_SIZE);
            stm.setInt(3, PAGE_SIZE);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                QuestionSet questionSet = new QuestionSet();
                questionSet.setSetId(rs.getInt(1));
                questionSet.setTitle(rs.getString(2));
                questionSet.setUserAccountId(rs.getInt(3));
                questionSet.setSubjectId(rs.getInt(4));
                questionSet.setQuesId(rs.getInt(5));
                questionSet.setSetVote(rs.getInt(6));

                list.add(questionSet);
            }
        } catch (Exception ex) {
            Logger.getLogger(DAOQuestionSet.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public int getTotalQuestionSet() {
        try {
            String sql = "select count(SetId)from QuestionSet ";

            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception ex) {
            Logger.getLogger(DAOQuestionSet.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    public static void main(String[] args) {
        DAOQuestionSet qs = new DAOQuestionSet();
        System.out.println(qs.getTop3());
        
    }

}
