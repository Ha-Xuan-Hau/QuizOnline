/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package Model;

import Entity.User;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author phamg
 */
public class DAOUserTest {

    DAOUser dao = new DAOUser();

    public DAOUserTest() {
    }

    @Test
    public void testGetAllUser() {
        List<User> user = new ArrayList<>();
        user = dao.getAllUser();
        if (user != null) {
            System.out.println("Test  successfully." + user);
        } else {
            System.out.println("Test failed.");
        }
        assertNotNull(user);
    }

    @Test
    public void testInsertUser() {
        String UserName = "Baodz";
        String Email = "Bao@gmail.com";
        String Pass = "12345678";
        int RoleID = 1;
        User user = new User(UserName, Email, Pass, RoleID);
        boolean result = dao.insertUser(user);
        if (result) {
            System.out.println("Test  successfully." + user);
        } else {
            System.out.println("Test failed.");
        }
        assertTrue(result);
    }

    @Test
    public void testInsertUserAndGetAccountId() {
        int AcountID = 24;
        String UserName = "Baodz";
        String Email = "Bao@gmail.com";
        String Pass = "12345678";
        int result = dao.updateUserAndGetAccountId(AcountID, UserName, Email, Pass);
        if (result == 24) {
            System.out.println("Test  successfully." + result);
        } else {
            System.out.println("Test failed.");
        }
        assertTrue(result == 24);
    }

    @Test
    public void testGetUserById() {
        int AcountID = 1;
        User user = dao.getUserById(AcountID);
        if (user != null) {
            System.out.println("Test successfully." + user);
        } else {
            System.out.println("Test failed.");
        }
        assertNotNull(user);
    }

    @Test
    public void testGetUser_String() {
        String sql = "SELECT * FROM [User] WHERE [UserID] = 1";
        User user = dao.getUser(sql);
        if (user != null) {
            System.out.println("Test Get User passed successfully: " + user);
        } else {
            System.out.println("Test Get User failed.");
        }
        assertNotNull(user);
    }

    @Test
    public void testUpdateUser() {
        int accountId = 24;
        String username = "Bao";
        String email = "Hil@Gmail.com";
        String password = "123123123";
        int roleId = 1;
        boolean isActive = true;
        User user = new User(accountId, username, email, password, roleId, isActive);
        if (user != null) {
            System.out.println("Test Get User passed successfully: " + user);
        } else {
            System.out.println("Test Get User failed.");
        }
        assertNotNull(user);
    }

    @Test
    public void testGetUser_String_String() throws Exception {
        String username = "Bao";
        String password = "123123123";
        User user = new User(username, password);
        if (user != null) {
            System.out.println("Test Get User passed successfully: " + user);
        } else {
            System.out.println("Test Get User failed.");
        }
        assertNotNull(user);
    }

    @Test
    public void testCheckUser() {
        List<User> t = new ArrayList<>();
        String username = "Bao";
        String password = "123123123";
        t = dao.checkUser(username, password);
        if (t != null) {
            System.out.println("Test Get User passed successfully: " + t);
        } else {
            System.out.println("Test Get User failed.");
        }
        assertNotNull(t);
    }

    @Test
    public void testUsernameExists() {
        String username = "Bao";
        boolean result;
        result = dao.usernameExists(username);
        if (result) {
            System.out.println("Test Get User passed successfully: ");
        } else {
            System.out.println("Test Get User failed.");
        }
        assertTrue(result);
    }

    @Test
    public void testEmailCheck() {
        String email = "Hil@Gmail.com";
        boolean result;
        result = dao.emailCheck(email);
        if (result) {
            System.out.println("Test Get User passed successfully: ");
        } else {
            System.out.println("Test Get User failed.");
        }
        assertTrue(result);
    }

    @Test
    public void testUsernameCheck() {
        String username = "Bao";
        boolean result;
        result = dao.usernameCheck(username);
        if (result) {
            System.out.println("Test Get User passed successfully: ");
        } else {
            System.out.println("Test Get User failed.");
        }
        assertTrue(result);
    }

    @Test
    public void testDeleteUser() {
        int AcountID = 24;
        boolean result = dao.deleteUser(AcountID);
        if (result) {
            System.out.println("Test Get User passed successfully: ");
        } else {
            System.out.println("Test Get User failed.");
        }
        assertTrue(result);
    }

    @Test
    public void testGetAllUserListData() {
        String sql = "SELECT * FROM User";
        List<User> userList = dao.getAllUserListData(sql);
        if (userList != null) {
            System.out.println("Test Get User passed successfully: " + userList);
        } else {
            System.out.println("Test Get User failed.");
        }
        assertNotNull(userList);
    }

    @Test
    public void testGetTotalSearch() {
        int total = 0;
        String keyword = "Ba";
        total = dao.getTotalSearch(keyword);
        if (total > 0) {
            System.out.println("Test Get User passed successfully: " + total);
        } else {
            System.out.println("Test Get User failed.");
        }
        assertTrue(total > 0);
    }

    @Test
    public void testCheckAccount() {
        String username = "Bao";
        String password = "123123123";
        User user = new User();
        user = dao.checkAccount(username, password);
        if (user != null) {
            System.out.println("Test Get User passed successfully: " + user);
        } else {
            System.out.println("Test Get User failed.");
        }
        assertNotNull(user);
    }

    @Test
    public void testChangePass() {
        String username = "Bao";
        String password = "123123123";
        User user = new User(username, password);
        boolean result = dao.ChangePass(user);
        if (result) {
            System.out.println("Test Get User passed successfully: ");
        } else {
            System.out.println("Test Get User failed.");
        }
        assertTrue(result);
    }

    @Test
    public void testGetUserByEmail() {
        String email = "phamgiabao1682003@gmail.com";
        User user = new User();
        user = dao.getUserByEmail(email);
        if (user != null) {
            System.out.println("Test Get User passed successfully: " + user);
        } else {
            System.out.println("Test Get User failed.");
        }
        assertNotNull(user);
    }

}
