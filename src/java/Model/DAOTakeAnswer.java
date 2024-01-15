package Model;

import Entity.takeAnswer;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DAOTakeAnswer extends DBConnect {

    public int insertTakeAnswer(takeAnswer obj) {
        int n = 0;
        String sql = "INSERT INTO [dbo].[TakeAnswer]\n"
                + "           ([TakeExamId]\n"
                + "           ,[QuesId]\n"
                + "           ,[AnswerId])\n"
                + "     VALUES(?,?,?)";

        System.out.println(sql);
        try {
            PreparedStatement pre = connection.prepareCall(sql);
            pre.setInt(1, obj.getTakeExamId());
            pre.setInt(2, obj.getQuesId());
            pre.setInt(3, obj.getAnswerId());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOTakeAnswer.class.getName()).log(Level.SEVERE, null, ex);
        }

        return n;
    }

    public int updateTakeAnswer(takeAnswer obj) {
        int n = 0;
        String sql = "UPDATE [dbo].[TakeAnswer]\n"
                + "   SET [TakeExamId] = ?\n"
                + "      ,[QuesId] = ?\n"
                + "      ,[AnswerId] = ?\n"
                + " WHERE [TakeAnswerId] = ?";
        try {
            PreparedStatement pre = connection.prepareCall(sql);
            pre.setInt(1, obj.getTakeExamId());
            pre.setInt(2, obj.getQuesId());
            pre.setInt(3, obj.getAnswerId());
            pre.setInt(4, obj.getTakeAnswerId());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOTakeAnswer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public int deleteTakeAnswer(String id) {
        int n = 0;
        String sql = "DELETE FROM [TakeAnswer] WHERE TakeAnswerId = '" + id + "'";
        try {
            Statement state = connection.createStatement();
            n = state.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(DAOTakeAnswer.class.getName()).log(Level.SEVERE, null, ex);
        }

        return n;
    }

    public ArrayList<takeAnswer> getData(String sql) {
        ArrayList<takeAnswer> List = new ArrayList<>();
        Statement state;
        try {
            state = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = state.executeQuery(sql);
            while (rs.next()) {
                int TakeAnswerId = rs.getInt(1);
                int TakeExamId = rs.getInt(2);
                int QuesId = rs.getInt(3);
                int AnswerId = rs.getInt(4);
                takeAnswer obj = new takeAnswer(TakeAnswerId, TakeExamId, QuesId, AnswerId);

                List.add(obj);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return List;
    }
}