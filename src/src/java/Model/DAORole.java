/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import Entity.Role;
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
public class DAORole extends DBConnect {

    public List<Role> getAllRole() {
        List<Role> rl = new ArrayList<>();

        try {
            String sql = "select*from Role";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Role rle = new Role();

                rle.setRoleId(rs.getInt(1));
                rle.setRole(rs.getString(2));

                rl.add(rle);

            }
        } catch (Exception e) {
        }
        return rl;

    }

    public void insertRole(Role rl) {
        try {
            String sql = "INSERT INTO [dbo].[Role]\n"
                    + "           ([RoleId]\n"
                    + "           ,[Role])\n"
                    + "     VALUES\n"
                    + "           (?,?)";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, rl.getRoleId());
            stm.setString(2, rl.getRole());

            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAORole.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public Role getRoleById(int RoleId) {
        try {
            String sql = "select *from Role where RoleId = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, RoleId);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                return new Role(rs.getInt(1), rs.getString(2));

            }

        } catch (Exception e) {
        }
        return null;
    }

    public void updateRole(Role rl) {
        try {
            String sql = "UPDATE [dbo].[Role]\n"
                    + "SET [Role] = ?\n"
                    + "WHERE RoleId = ?;";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, rl.getRole());

            stm.setInt(2, rl.getRoleId());

            stm.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(DAOAdmin.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void deleteAdmin(int RoleId) {
        try {
            String sql = "DELETE FROM [dbo].[Role]\n"
                    + "      WHERE RoleId=?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, RoleId);
            stm.executeUpdate();

        } catch (Exception e) {
        }

    }
}
