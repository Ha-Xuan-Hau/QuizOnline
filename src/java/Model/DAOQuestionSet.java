/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import Entity.Content;
import Entity.NormalQuestion;
import Entity.NormalQuestionAnswer;
import Entity.QuestionSet;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
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

                qts.setSetVote(rs.getInt(5));

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
                    + "           ,[SetVote])\n"
                    + "     VALUES\n"
                    + "           (?,?,?,?)";
            PreparedStatement stm = connection.prepareStatement(sql);

            stm.setString(1, qs.getTitle());
            stm.setInt(2, qs.getUserAccountId());
            stm.setInt(3, qs.getSubjectId());
            stm.setInt(4, qs.getSetVote());
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOQuestionSet.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
        public void insertQuestion(QuestionSet qs) {
        try {
            String sql = "INSERT INTO [dbo].[QuestionSet]\n"
                    + "           ([Title])\n"                  
                    + "     VALUES\n"
                    + "           (?)";
            PreparedStatement stm = connection.prepareStatement(sql);

            stm.setString(1, qs.getTitle());
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
                return new QuestionSet(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4), rs.getInt(5));

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
                    + "      ,[SetVote] = ?\n"
                    + " WHERE SetID =?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, SetId.getTitle());
            stm.setInt(2, SetId.getUserAccountId());
            stm.setInt(3, SetId.getSubjectId());

            stm.setInt(4, SetId.getSetVote());
            stm.setInt(5, SetId.getSetId());

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
//
//    public ArrayList<Content> getQuizContent() throws SQLException {
//        ArrayList<Content> quizList = new ArrayList<>();
//        String sql = "SELECT NormalQuestion.Content AS QuestionContent, NormalQuestionAnswer.Content AS AnswerContent\n"
//                + "FROM QuestionSet\n"
//                + "JOIN NormalQuestion ON QuestionSet.QuesId = NormalQuestion.QuesId\n"
//                + "JOIN NormalQuestionAnswer ON NormalQuestion.QuesId = NormalQuestionAnswer.QuesId";
//        PreparedStatement stm = connection.prepareStatement(sql);
//        ResultSet rs = stm.executeQuery();
//        while (rs.next()) {
//            String QuizContent = rs.getString("QuestionContent");
//            String Answer = rs.getString("AnswerContent");
//            Content obj = new Content(QuizContent, Answer);
//            quizList.add(obj);
//    }
//
//        return quizList;
//}

    public int countQuest(String keyword) {
        try {
            String sql = "select count(*)  from QuestionSet where Title like ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, "%" + keyword + "%");
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);

            }
        } catch (Exception e) {
        }
        return 0;
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
                int SetVote = resultSet.getInt("SetVote");
                QuestionSet sb = new QuestionSet(SetId, Title, UserAccountId, SubjectId, SetVote);
                questionset.add(sb);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOSubject.class.getName()).log(Level.SEVERE, null, ex);
        }
        return questionset;
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
                qs.setSetVote(rs.getInt(5));

                list.add(qs);

            }
        } catch (Exception ex) {
            Logger.getLogger(DAOSubject.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
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
                questionSet.setSetVote(rs.getInt(5));

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

    public List<QuestionSet> getTop3() {
        List<QuestionSet> list = new ArrayList<>();
        try {
            String query = "SELECT TOP 3 * FROM QuestionSet ORDER BY SetId DESC";
            PreparedStatement stm = connection.prepareCall(query);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                list.add(new QuestionSet(rs.getInt(1),
                        rs.getString(2),
                        rs.getInt(3),
                        rs.getInt(4),
                        rs.getInt(5)));
            }
        } catch (Exception e) {
        }
        return list;
    }

    public ArrayList<NormalQuestion> getQues(int setId) {
        ArrayList<NormalQuestion> quesDetails = new ArrayList<>();
        try {
            String sql = "select NormalQuestion.*\n"
                    + "from QuestionSet join NormalQuestion on QuestionSet.SetId = NormalQuestion.SetId\n"
                    + "where QuestionSet.SetId = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, setId);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                int QuesId = rs.getInt("QuesId");
                String quesContent = rs.getString("Content");
                NormalQuestion obj = new NormalQuestion(QuesId, quesContent, setId);
                quesDetails.add(obj);
            }
        } catch (Exception e) {
        }
        return quesDetails;
    }

    public ArrayList<ArrayList<NormalQuestionAnswer>> getAnswer(int setId) {
        HashMap<Integer, ArrayList<NormalQuestionAnswer>> map = new HashMap<>();
        try {
            String sql = "select NormalQuestionAnswer.*\n"
                    + "from QuestionSet join NormalQuestion on QuestionSet.SetId = NormalQuestion.SetId\n"
                    + "join NormalQuestionAnswer on NormalQuestion.QuesId = NormalQuestionAnswer.QuesId\n"
                    + "where QuestionSet.SetId = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, setId);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                int answerId = rs.getInt("AnswerId");
                int quesId = rs.getInt("QuesId");
                String quesContent = rs.getString("Content");
                boolean correct = rs.getBoolean("Correct");
                double percent = rs.getDouble("Percent");
                NormalQuestionAnswer answer = new NormalQuestionAnswer(answerId, quesId, quesContent, correct, percent);

                // Kiểm tra quesId đã tồn tại trong danh sách chưa
                if (!map.containsKey(quesId)) {
                    map.put(quesId, new ArrayList<>());
                }
                map.get(quesId).add(answer);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Chuyển đổi HashMap thành ArrayList
        ArrayList<ArrayList<NormalQuestionAnswer>> allAnswer = new ArrayList<>(map.values());
        return allAnswer;
    }

//cho quizContent
//    public ArrayList<QuesContent> getAllQuesContent(int setId) {
//    ArrayList<QuesContent> allQuesAnswers = new ArrayList<>();
//    try {
//        String sql = "select NormalQuestionAnswer.QuesId, NormalQuestion.Content as QuesContent, NormalQuestionAnswer.Content as QuesAnswer, NormalQuestionAnswer.Correct\n"
//                + "from NormalQuestion join NormalQuestionAnswer on NormalQuestion.QuesId = NormalQuestionAnswer.QuesId\n"
//                + "where NormalQuestion.SetId = ?";
//        PreparedStatement statement = connection.prepareStatement(sql);
//        statement.setInt(1, setId);
//        ResultSet rs = statement.executeQuery();
//
//        while (rs.next()) {
//            int quesId = rs.getInt("QuesId");
//            String content = rs.getString("QuesContent");
//            String answer = rs.getString("QuesAnswer");
//            boolean isCorrect = rs.getBoolean("Correct");
//
//            // Kiểm tra xem quesId đã tồn tại trong danh sách chưa
//            QuesContent existingQuizContent = null;
//            for (QuesContent qa : allQuesAnswers) {
//                if (qa.getQuestId() == quesId) {
//                    existingQuizContent = qa;
//                    break;
//                }
//            }
//            // Nếu quesId chưa tồn tại trong danh sách, thêm nó với một ArrayList mới
//            if (existingQuizContent == null) {
//                existingQuizContent = new QuesContent(quesId, content, new ArrayList<>(), isCorrect);
//                allQuesAnswers.add(existingQuizContent);
//            }
//            // Thêm câu trả lời vào danh sách câu trả lời của quesId tương ứng
//            existingQuizContent.getQuestAnswer().add(answer);
//        }
//    } catch (SQLException e) {
//        e.printStackTrace();
//    }
//    return allQuesAnswers;
//}
    public static void main(String[] args) {
        DAOQuestionSet dao = new DAOQuestionSet();
        QuestionSet sub = new QuestionSet("abcde");
dao.insertQuestionSet(sub);
    }
}
