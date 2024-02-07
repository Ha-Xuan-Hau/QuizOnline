/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import Entity.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
                User us = fromResultSet(rs);
                user.add(us);

            }
        } catch (Exception e) {
            Logger.getLogger(DAOUser.class.getName()).log(Level.SEVERE, null, e);
        }
        return user;

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

    
    public User getUserById(int AccountId){
        try {
            String sql= "select *from [User] where AccountId = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, AccountId );
            ResultSet rs = stm.executeQuery();
            while(rs.next()){
                return new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5),rs.getBoolean(6));
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
    public User getUser(String username, String pass) throws SQLException {
        String sql = "select * from Users where [Username] = ? and [Password] = ?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, username);
        ps.setString(2, pass);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            return fromResultSet(rs);
        }
        return null;
    }
    
    public List<User> checkUser(String username, String passWord) {
        List<User> t = new ArrayList<>();
        try {
            String sql = "select * from Users where [Username] = ? and [Password] = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, passWord);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                t.add(fromResultSet(rs));
            }
            ps.execute();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return t;
    }
    public User fromResultSet(ResultSet rs) throws SQLException {
        return new User(
                rs.getInt(1),
                rs.getString(2),
                rs.getString(3),
                rs.getString(4),
                rs.getInt(5),
                rs.getBoolean(6)
        );
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
    
    public List<User> getAllUserListData(String sql) {
    List<User> userList = new ArrayList<>();
    Statement state;
    try {
        state = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                ResultSet.CONCUR_UPDATABLE);
        ResultSet rs = state.executeQuery(sql);

        while (rs.next()) {
            int accountId = rs.getInt("AccountId");
            String username = rs.getString("Username");
            String email = rs.getString("Email");
            String password = rs.getString("Password");
            int roleId = rs.getInt("RoleId");
            boolean isActive = rs.getBoolean("isActive");

            User user = new User(accountId, username, email, password, roleId, isActive);
            userList.add(user);
        }
    } catch (SQLException ex) {
        ex.printStackTrace();
    }

    return userList;
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
