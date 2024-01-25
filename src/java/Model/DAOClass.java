/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import Entity.Class;
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
public class DAOClass extends DBConnect {

    public int CreateClass(Class obj) {
        int n = 0;
        String sql = "INSERT INTO [dbo].[Class]\n"                
                + "           ([ClassName]\n"
                + "           ,[TeacherAccountId]\n"
                + "           ,[CreateDate])\n"
                + "     VALUES(?,?,?)";
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            
            pre.setString(1, obj.getClassName());
            pre.setInt(2, obj.getTeacherAccountId());
            pre.setString(3, obj.getCreateDate());
            n = pre.executeUpdate();

        } catch (SQLException ex) {

        }

        return n;

    }

    public int UpdateClass(Class obj) {
        int n = 0;
        String sql = "UPDATE [dbo].[Class]\n"
                + "   SET [ClassName] = ?\n"
                + "      ,[TeacherAccountId] = ?\n"
                + "      ,[CreateDate] = ?\n"
                + " WHERE ClassId = ?";
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setString(1, obj.getClassName());
            pre.setInt(2, obj.getTeacherAccountId());
            pre.setString(3, obj.getCreateDate());
            pre.setInt(4, obj.getClassId());
            n = pre.executeUpdate();
        } catch (Exception e) {
        }
        return n;
    }

    public int DeleteClass(int id) {
        int n = 0;
        String sql = "delete from Class where ClassId = ?";
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setInt(1, id);
            n = pre.executeUpdate();
        } catch (Exception e) {
        }
        return n;
    }

    public Class getClass(int ClassId) {
        String sql = "select * from Class where ClassId = ?";
        try {

            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setInt(1, ClassId);
            ResultSet rs = pre.executeQuery();
            if (rs.next()) {
                Class obj = new Class();
                obj.setClassId(rs.getInt(1));
                obj.setClassName(rs.getString(2));
                obj.setTeacherAccountId(rs.getInt(3));
                obj.setCreateDate(rs.getString(4));
                return obj;
            }
        } catch (Exception e) {
        }
        return null;
    }

    public Vector<Class> getAll() {
        Vector<Class> vector = new Vector<Class>();
        String sql = "select * from Class";
        Statement state;
        try {
            state = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = state.executeQuery(sql);
            while (rs.next()) {
                int ClassId = rs.getInt(1);
                String ClassName = rs.getString(2);
                int TeacherAccountId = rs.getInt(3);
                String CreateDate = rs.getString(4);
                Class obj = new Class(ClassId, ClassName, TeacherAccountId, CreateDate);
                vector.add(obj);
            }
        } catch (Exception e) {
        }

        return vector;

    }
    public static void main(String[] args) {
        DAOClass dao = new DAOClass();
        int n =dao.UpdateClass(new Class(1012, "skjdh", 103, "2022-01-03"));
        if(n!= 0){
            System.out.println("ok");
        }
    }

}
