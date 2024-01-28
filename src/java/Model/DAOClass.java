
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
import java.util.ArrayList;

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
        } catch (SQLException e) {
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
        } catch (SQLException e) {
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
        } catch (SQLException e) {
        }
        return null;
    }

    public ArrayList<Class> getdata() {
        ArrayList<Class> arrayList = new ArrayList<>();
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
                arrayList.add(obj);
            }
        } catch (SQLException e) {
        }

        return arrayList;
    }

    public Class getDataByClassID(int classId) {
        Class class1 = new Class();
        String sql = "SELECT * FROM Class WHERE ClassId = " + classId;
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
                class1 = obj;
            }
        } catch (SQLException e) {
        }

        return class1;
    }

    public ArrayList<Class> getDataByTeacherID(int teacherAccountId) {
        ArrayList<Class> arrayList = new ArrayList<>();
        String sql = "SELECT * FROM Class WHERE TeacherAccountId = " + teacherAccountId;
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
                arrayList.add(obj);
            }
        } catch (SQLException e) {
        }

        return arrayList;
    }

    public static void main(String[] args) {
        DAOClass dao = new DAOClass();
        System.out.println(dao.getDataByTeacherID(1));

    }

}
