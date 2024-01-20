/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import Entity.ClassQuestionSet;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author admin
 */
public class DAOClassQuestionSet extends DBConnect {

    public int CreateClassQuestionSet(ClassQuestionSet obj) {
        int n = 0;
        String sql = "INSERT INTO [dbo].[ClassQuestionSet]\n"
                + "           ([ClassId]\n"
                + "           ,[SetId])\n"
                + "     VALUES(?,?)";

        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setInt(1, obj.getClassId());
            pre.setInt(2, obj.getSetId());
            n = pre.executeUpdate();
        } catch (Exception e) {
        }
        return n;
    }

    public int UpdateClassQuestionSet(ClassQuestionSet obj) {
        int n = 0;
        String sql = "UPDATE [dbo].[ClassQuestionSet]\n"
                + "   SET [ClassId] = ?\n"
                + "      ,[SetId] = ?\n"
                + " WHERE ClassSetId = ?";
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setInt(1, obj.getClassId());
            pre.setInt(2, obj.getSetId());
            pre.setInt(3, obj.getClassSetId());
            n = pre.executeUpdate();
        } catch (Exception e) {
        }
        return n;
    }

    public int DeleteClassQuestionSet(int id) {
        int n = 0;
        String sql = "delete from ClassQuestionSet where ClassSetId = ?";
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setInt(1, id);
            n = pre.executeUpdate();
        } catch (Exception e) {
        }
        return n;
    }

    public ClassQuestionSet ClassQuestionSet(int ClassSetId) {
        int n = 0;
        String sql = "select * from ClassQuestionSet where ClassSetId = ?";
        try {

            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setInt(1, ClassSetId);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                ClassQuestionSet obj = new ClassQuestionSet();
                obj.setClassSetId(rs.getInt(1));
                obj.setClassId(rs.getInt(2));
                obj.setSetId(rs.getInt(3));
                return obj;
            }
        } catch (Exception e) {
        }
        return null;
    }

    public Vector<ClassQuestionSet> getAll() {
        Vector<ClassQuestionSet> vector = new Vector<ClassQuestionSet>();
        String sql = "select * from ClassQuestionSet";
        Statement state;
        try {
            state = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = state.executeQuery(sql);
            while (rs.next()) {
                int ClassSetId = rs.getInt(1);
                int ClassId = rs.getInt(2);
                int SetId = rs.getInt(3);
                ClassQuestionSet obj = new ClassQuestionSet(ClassSetId, ClassId, SetId);
                vector.add(obj);
            }
        } catch (Exception e) {
        }
        return vector;
    }
        public static void main(String[] args) {
        DAOClassQuestionSet dao = new DAOClassQuestionSet();
        int n =dao.UpdateClassQuestionSet(new ClassQuestionSet(1002,1015,1002));
        if(n!= 0){
            System.out.println("ok");
        }
    }
}
