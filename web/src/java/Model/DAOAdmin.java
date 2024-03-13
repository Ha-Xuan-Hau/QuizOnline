/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import Entity.Admin;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import java.util.List;

import java.util.List;

/**
 *
 * @author Asus
 */
public class DAOAdmin extends DBConnect {
//         public void insertAccount(String User, String pass) {
//        try {
//            String sql = "INSERT INTO [Account]\n"
//                    + "           ([User]\n"
//                    + "           ,[pass]\n"
//                    + "           ,[isSell]\n"
//                    + "           ,[isAdmin]\n"
//                    + "           ,[active])\n"
//                    + "     VALUES\n"
//                    + "           (?\n"
//                    + "           ,?\n"
//                    + "           ,0\n"
//                    + "           ,0\n"
//                    + "           ,1)";
//            PreparedStatement stm = connection.prepareStatement(sql);
//            stm.setString(1, User);
//            stm.setString(2, pass);
//            stm.executeUpdate();
//        } catch (SQLException ex) {
//            Logger.getLogger(AcountDBContext.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
//

    public List<Admin> getAllAdmin() {
        List<Admin> ad = new ArrayList<>();

        try {
            String sql = "select*from Admin";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Admin adm = new Admin();

                adm.setAccountId(rs.getInt(1));
                adm.setAdminName(rs.getString(2));
                adm.setPhone(rs.getString(3));

                ad.add(adm);

            }
        } catch (Exception e) {
        }
        return ad;

    }

    public void insertAdmin(int accountId, String adminName, String phone) {
        try {
            String sql = "INSERT INTO [dbo].[Admin] ([AccountId], [AdminName], [Phone]) VALUES (?, ?, ?)";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, accountId);
            stm.setString(2, adminName);
            stm.setString(3, phone);

            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOAdmin.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void insertAdmin(Admin ad) {
        try {
            String sql = "INSERT INTO [dbo].[Admin]\n"
                    + "([AccountId]\n"
                    + ",[AdminName]\n"
                    + ",[Phone])\n"
                    + "VALUES\n"
                    + "(?,?,?)";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, ad.getAccountId());
            stm.setString(2, ad.getAdminName());
            stm.setString(3, ad.getPhone());

            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOAdmin.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public Admin getAdminById(int AccountId) {
        try {
            String sql = "select *from Admin where AccountId = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, AccountId);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                return new Admin(rs.getInt(1), rs.getString(2), rs.getString(3));

            }

        } catch (Exception e) {
        }
        return null;
    }

    public void updateAdmin(Admin a) {
        try {
            String sql = "UPDATE [dbo].[Admin]\n"
                    + "SET \n"
                    + "    [AdminName] = ?,\n"
                    + "    [Phone] = ?\n"
                    + "WHERE AccountId = ?;";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, a.getAdminName());
            stm.setString(2, a.getPhone());
            stm.setInt(3, a.getAccountId());

            stm.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(DAOAdmin.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void deleteAdmin(int AccountId) {
        try {
            String sql = "delete from [admin] where AccountId = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, AccountId);
            stm.executeUpdate();

        } catch (Exception e) {
        }

    }

    public static void main(String[] args) {
        DAOAdmin dao = new DAOAdmin();
        List<Admin> a = dao.getAllAdmin();
        for (Admin object : a) {
            System.out.println(a);
        }

        Admin newAdmin = new Admin(3, "kienhghg", "0988488999");

        // Thực hiện thêm Admin mới vào cơ sở dữ liệu
        dao.insertAdmin(newAdmin);

        // Hiển thị danh sách Admin sau khi thêm mới
        System.out.println("\nDanh sách admin sau khi thêm mới:");
        List<Admin> adminsAfterInsert = dao.getAllAdmin();
        for (Admin admin : adminsAfterInsert) {
            System.out.println(admin);

        }
    }
}
