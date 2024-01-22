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
        List<User> users = new ArrayList<>();

        try {
            String sql = "SELECT * FROM [User]";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                User user = fromResultSet(rs);
                users.add(user);
            }
        } catch (Exception e) {
            Logger.getLogger(DAOUser.class.getName()).log(Level.SEVERE, null, e);
        }
        return users;
    }

    public void insertUser(User user) {
        try {
            String sql = "INSERT INTO [User] ([AccountId], [Username], [Email], [Password], [RoleId], [IsActive])\n"
                    + "VALUES (?, ?, ?, ?, ?, 1);";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, user.getAccountId());
            stm.setString(2, user.getUsername());
            stm.setString(3, user.getEmail());
            stm.setString(4, user.getPassword());
            stm.setInt(5, user.getRoleId());

            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOUser.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public User getUserById(int AccountId) {
        try {
            String sql = "SELECT * FROM [User] WHERE AccountId = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, AccountId);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                return fromResultSet(rs);
            }
        } catch (Exception e) {
            Logger.getLogger(DAOUser.class.getName()).log(Level.SEVERE, null, e);
        }
        return null;
    }

    public void updateUser(User user) {
        try {
            String sql = "UPDATE [User]\n"
                    + "   SET\n"
                    + "		[Username] = ?\n"
                    + "      ,[Email] = ?\n"
                    + "      ,[Password] = ?\n"
                    + "		,[RoleId] = ?\n"
                    + "      ,[IsActive] = ?\n"
                    + " WHERE AccountId = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, user.getUsername());
            stm.setString(2, user.getEmail());
            stm.setString(3, user.getPassword());
            stm.setInt(4, user.getRoleId());
            stm.setBoolean(5, user.isActive());
            stm.setInt(6, user.getAccountId());
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOUser.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void deleteUser(int AccountId) {
        try {
            String sql = "DELETE FROM [User] WHERE AccountId = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, AccountId);
            stm.executeUpdate();
        } catch (Exception e) {
            Logger.getLogger(DAOUser.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public User fromResultSet(ResultSet rs) throws SQLException {
        int AccountId = rs.getInt(1);
        String Username = rs.getString(2);
        String Email = rs.getString(3);
        String Password = rs.getString(4);
        int RoleId = rs.getInt(5);
        boolean isActive = rs.getBoolean(6);

        return new User(AccountId, Username, Email, Password, RoleId, isActive);
    }

    public static void main(String[] args) {
        DAOUser dao = new DAOUser();
        List<User> users = dao.getAllUser();
        for (User user : users) {
            System.out.println(user);
        }
        User user = dao.getUserById(1);
        System.out.println(user);
    }
}