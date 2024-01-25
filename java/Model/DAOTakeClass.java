/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import Entity.TakeClass;
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
public class DAOTakeClass extends DBConnect {

    public int CreateTakeClass(TakeClass obj) {
        int n = 0;
        String sql = "INSERT INTO [dbo].[TakeClass]\n"
                + "           ([StudentAccountId]\n"
                + "           ,[ClassId])\n"
                + "     VALUES(?,?)";
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setInt(1, obj.getStudentAccountId());
            pre.setInt(2, obj.getClassId());
            n = pre.executeUpdate();
        } catch (Exception e) {
        }
        return n;
    }

    public int UpdateTakeClass(TakeClass obj) {
        int n = 0;
        String sql = "UPDATE [dbo].[TakeClass]\n"
                + "   SET [StudentAccountId] = ?\n"
                + "      ,[ClassId] = ?\n"
                + " WHERE TakeClassId = ?";
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setInt(1, obj.getStudentAccountId());
            pre.setInt(2, obj.getClassId());
            pre.setInt(3, obj.getTakeClassId());
            n = pre.executeUpdate();
        } catch (Exception e) {
        }
        return n;
    }

    public int DeleteTakeClass(int id) {
        int n = 0;
        String sql = "delete from TakeClass where TakeClassId = ?";
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setInt(1, id);
            n = pre.executeUpdate();
        } catch (Exception e) {
        }
        return n;
    }

    public TakeClass getTakeClass(int TakeClassId) {
        String sql = "select * from TakeClass where TakeClassId = ?";
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setInt(1, TakeClassId);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                TakeClass obj = new TakeClass();
                obj.setTakeClassId(rs.getInt(1));
                obj.setStudentAccountId(rs.getInt(2));
                obj.setClassId(rs.getInt(3));
                return obj;
            }
        } catch (Exception e) {
        }
        return null;
    }

    public Vector<TakeClass> getAll() {
        Vector<TakeClass> vector = new Vector<TakeClass>();
        String sql = "select * from TakeClass";
        Statement state;
        try {
            state = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = state.executeQuery(sql);
            while (rs.next()) {
                int TakeClassId = rs.getInt(1);
                int StudentAccountId = rs.getInt(2);
                int ClassId = rs.getInt(3);
                TakeClass obj = new TakeClass(TakeClassId, StudentAccountId, ClassId);
                vector.add(obj);
            }
        } catch (Exception e) {
        }
        return vector;

    }
    

}
