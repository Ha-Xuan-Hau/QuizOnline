/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import Entity.User;

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
public class DAOUser extends DBConnect {

    public List<User> getAllUser() {
        List<User> user = new ArrayList<>();

        try {
            String sql = "select *from [User]";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                User us = new User();
                us.setAccountId(rs.getInt(1));
                us.setUsername(rs.getString(2));
                us.setPassword(rs.getString(3));
                us.setRoleId(rs.getInt(4));
                us.setIsActive(rs.getBoolean(5));
                user.add(us);

            }
        } catch (Exception e) {
        }
        return user;

    }

    public void insertUser(User user) {
        try {
            String sql = "INSERT INTO [dbo].[User] ([AccountId], [Username], [Password], [RoleId], [IsActive])\n"
                    + "VALUES (?, ?, ?, ?, 1);";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, user.getAccountId());
            stm.setString(2, user.getUsername());
            stm.setString(3, user.getPassword());
            stm.setInt(4, user.getRoleId());

            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOUser.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    
    public User getUserById(int AccountId){
        try {
            String sql= "select *from [User] where AccountId = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, AccountId );
            ResultSet rs = stm.executeQuery();
            while(rs.next()){
                return new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4),rs.getBoolean(5));
            }
            
        } catch (Exception e) {
        }
        return null;
    }

    public void updateUser(User u) {
        try {
            String sql = "UPDATE [dbo].[User]\n"
                    + "   SET\n"
                    + "		[Username] = ?\n"
                    + "      ,[Password] = ?,\n"
                    + "		[RoleId] = ?\n"
                    + "      ,[IsActive] = ?\n"
                    + " WHERE AccountId=?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, u.getUsername());
            stm.setString(2, u.getPassword());
            stm.setDouble(3, u.getRoleId());
            stm.setBoolean(4, u.isIsActive());
            stm.setInt(5, u.getAccountId());
            stm.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(DAOUser.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    public void deleteUser(int AccountId ){
          try {
            String sql= "delete from [User] where AccountId = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, AccountId );
            stm.executeUpdate();
           
            
        } catch (Exception e) {
        }
    
    }
    
    public static void main(String[] args) {
        DAOUser dao = new DAOUser();
        List<User> us = dao.getAllUser();
        for (User u : us) {
            System.out.println(u);

        }
        User u = dao.getUserById(1);
        System.out.println(u);
    }

}
