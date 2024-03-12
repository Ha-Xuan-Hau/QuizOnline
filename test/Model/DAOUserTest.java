/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package Model;

import Entity.User;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author hieul
 */
public class DAOUserTest {
    
    public DAOUserTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
    }
    
    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of getAllUser method, of class DAOUser.
     */
    @Test
    public void testGetAllUser() {
        System.out.println("getAllUser");
        DAOUser instance = new DAOUser();
        List<User> expResult = null;
        List<User> result = instance.getAllUser();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of insertUser method, of class DAOUser.
     */
    @Test
    public void testInsertUser_User() {
        System.out.println("insertUser");
        User user = null;
        DAOUser instance = new DAOUser();
        instance.insertUser(user);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of updateUserAndGetAccountId method, of class DAOUser.
     */
    @Test
    public void testUpdateUserAndGetAccountId_6args() {
        System.out.println("updateUserAndGetAccountId");
        int accountId = 0;
        String username = "";
        String email = "";
        String password = "";
        int roleId = 0;
        int isActive = 0;
        DAOUser instance = new DAOUser();
        int expResult = 0;
        int result = instance.updateUserAndGetAccountId(accountId, username, email, password, roleId, isActive);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of updateUserAndGetAccountId method, of class DAOUser.
     */
    @Test
    public void testUpdateUserAndGetAccountId_4args() {
        System.out.println("updateUserAndGetAccountId");
        int accountId = 0;
        String username = "";
        String email = "";
        String password = "";
        DAOUser instance = new DAOUser();
        int expResult = 0;
        int result = instance.updateUserAndGetAccountId(accountId, username, email, password);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of insertUserAndGetAccountId method, of class DAOUser.
     */
    @Test
    public void testInsertUserAndGetAccountId() {
        System.out.println("insertUserAndGetAccountId");
        String username = "";
        String email = "";
        String password = "";
        int roleId = 0;
        int isActive = 0;
        DAOUser instance = new DAOUser();
        int expResult = 0;
        int result = instance.insertUserAndGetAccountId(username, email, password, roleId, isActive);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of insertUser method, of class DAOUser.
     */
    @Test
    public void testInsertUser_5args() {
        System.out.println("insertUser");
        String username = "";
        String email = "";
        String password = "";
        int roleId = 0;
        boolean isActive = false;
        DAOUser instance = new DAOUser();
        instance.insertUser(username, email, password, roleId, isActive);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getUserById method, of class DAOUser.
     */
    @Test
    public void testGetUserById() {
        System.out.println("getUserById");
        int accountId = 0;
        DAOUser instance = new DAOUser();
        User expResult = null;
        User result = instance.getUserById(accountId);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getUser method, of class DAOUser.
     */
    @Test
    public void testGetUser_String() {
        System.out.println("getUser");
        String sql = "";
        DAOUser instance = new DAOUser();
        User expResult = null;
        User result = instance.getUser(sql);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of updateUser method, of class DAOUser.
     */
    @Test
    public void testUpdateUser() {
        System.out.println("updateUser");
        User user = null;
        DAOUser instance = new DAOUser();
        instance.updateUser(user);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getUser method, of class DAOUser.
     */
    @Test
    public void testGetUser_String_String() throws Exception {
        System.out.println("getUser");
        String username = "";
        String pass = "";
        DAOUser instance = new DAOUser();
        User expResult = null;
        User result = instance.getUser(username, pass);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of checkUser method, of class DAOUser.
     */
    @Test
    public void testCheckUser() {
        System.out.println("checkUser");
        String username = "";
        String passWord = "";
        DAOUser instance = new DAOUser();
        List<User> expResult = null;
        List<User> result = instance.checkUser(username, passWord);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of usernameExists method, of class DAOUser.
     */
    @Test
    public void testUsernameExists() {
        System.out.println("usernameExists");
        String username = "";
        DAOUser instance = new DAOUser();
        boolean expResult = false;
        boolean result = instance.usernameExists(username);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of emailCheck method, of class DAOUser.
     */
    @Test
    public void testEmailCheck() {
        System.out.println("emailCheck");
        String email = "";
        DAOUser instance = new DAOUser();
        boolean expResult = false;
        boolean result = instance.emailCheck(email);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of usernameCheck method, of class DAOUser.
     */
    @Test
    public void testUsernameCheck() {
        System.out.println("usernameCheck");
        String username = "";
        DAOUser instance = new DAOUser();
        boolean expResult = false;
        boolean result = instance.usernameCheck(username);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of fromResultSet method, of class DAOUser.
     */
    @Test
    public void testFromResultSet() throws Exception {
        System.out.println("fromResultSet");
        ResultSet rs = null;
        DAOUser instance = new DAOUser();
        User expResult = null;
        User result = instance.fromResultSet(rs);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of deleteUser method, of class DAOUser.
     */
    @Test
    public void testDeleteUser() {
        System.out.println("deleteUser");
        int AccountId = 0;
        DAOUser instance = new DAOUser();
        instance.deleteUser(AccountId);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAllUserListData method, of class DAOUser.
     */
    @Test
    public void testGetAllUserListData() {
        System.out.println("getAllUserListData");
        String sql = "";
        DAOUser instance = new DAOUser();
        List<User> expResult = null;
        List<User> result = instance.getAllUserListData(sql);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of searchUsers method, of class DAOUser.
     */
    @Test
    public void testSearchUsers() {
        System.out.println("searchUsers");
        String keyword = "";
        DAOUser instance = new DAOUser();
        List<Map<String, Object>> expResult = null;
        List<Map<String, Object>> result = instance.searchUsers(keyword);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAllUsers method, of class DAOUser.
     */
    @Test
    public void testGetAllUsers() {
        System.out.println("getAllUsers");
        DAOUser instance = new DAOUser();
        List<Map<String, Object>> expResult = null;
        List<Map<String, Object>> result = instance.getAllUsers();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAllActiveUsers method, of class DAOUser.
     */
    @Test
    public void testGetAllActiveUsers() {
        System.out.println("getAllActiveUsers");
        DAOUser instance = new DAOUser();
        List<Map<String, Object>> expResult = null;
        List<Map<String, Object>> result = instance.getAllActiveUsers();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAllSuspendedUsers method, of class DAOUser.
     */
    @Test
    public void testGetAllSuspendedUsers() {
        System.out.println("getAllSuspendedUsers");
        DAOUser instance = new DAOUser();
        List<Map<String, Object>> expResult = null;
        List<Map<String, Object>> result = instance.getAllSuspendedUsers();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAllActiveAdminUsers method, of class DAOUser.
     */
    @Test
    public void testGetAllActiveAdminUsers() {
        System.out.println("getAllActiveAdminUsers");
        DAOUser instance = new DAOUser();
        List<Map<String, Object>> expResult = null;
        List<Map<String, Object>> result = instance.getAllActiveAdminUsers();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAllActiveTeacherUsers method, of class DAOUser.
     */
    @Test
    public void testGetAllActiveTeacherUsers() {
        System.out.println("getAllActiveTeacherUsers");
        DAOUser instance = new DAOUser();
        List<Map<String, Object>> expResult = null;
        List<Map<String, Object>> result = instance.getAllActiveTeacherUsers();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAllSuspendedTeacherUsers method, of class DAOUser.
     */
    @Test
    public void testGetAllSuspendedTeacherUsers() {
        System.out.println("getAllSuspendedTeacherUsers");
        DAOUser instance = new DAOUser();
        List<Map<String, Object>> expResult = null;
        List<Map<String, Object>> result = instance.getAllSuspendedTeacherUsers();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAllActiveStudentUsers method, of class DAOUser.
     */
    @Test
    public void testGetAllActiveStudentUsers() {
        System.out.println("getAllActiveStudentUsers");
        DAOUser instance = new DAOUser();
        List<Map<String, Object>> expResult = null;
        List<Map<String, Object>> result = instance.getAllActiveStudentUsers();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAllSuspendedStudentUsers method, of class DAOUser.
     */
    @Test
    public void testGetAllSuspendedStudentUsers() {
        System.out.println("getAllSuspendedStudentUsers");
        DAOUser instance = new DAOUser();
        List<Map<String, Object>> expResult = null;
        List<Map<String, Object>> result = instance.getAllSuspendedStudentUsers();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAllBanAdminUsers method, of class DAOUser.
     */
    @Test
    public void testGetAllBanAdminUsers() {
        System.out.println("getAllBanAdminUsers");
        DAOUser instance = new DAOUser();
        List<Map<String, Object>> expResult = null;
        List<Map<String, Object>> result = instance.getAllBanAdminUsers();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAllTeacherUsers method, of class DAOUser.
     */
    @Test
    public void testGetAllTeacherUsers() {
        System.out.println("getAllTeacherUsers");
        DAOUser instance = new DAOUser();
        List<Map<String, Object>> expResult = null;
        List<Map<String, Object>> result = instance.getAllTeacherUsers();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAllStudentUsers method, of class DAOUser.
     */
    @Test
    public void testGetAllStudentUsers() {
        System.out.println("getAllStudentUsers");
        DAOUser instance = new DAOUser();
        List<Map<String, Object>> expResult = null;
        List<Map<String, Object>> result = instance.getAllStudentUsers();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAllAdminUsers method, of class DAOUser.
     */
    @Test
    public void testGetAllAdminUsers() {
        System.out.println("getAllAdminUsers");
        DAOUser instance = new DAOUser();
        List<Map<String, Object>> expResult = null;
        List<Map<String, Object>> result = instance.getAllAdminUsers();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of searchUsersWithPagination method, of class DAOUser.
     */
    @Test
    public void testSearchUsersWithPagination() {
        System.out.println("searchUsersWithPagination");
        String keyword = "";
        int page = 0;
        int pageSize = 0;
        DAOUser instance = new DAOUser();
        List<Map<String, Object>> expResult = null;
        List<Map<String, Object>> result = instance.searchUsersWithPagination(keyword, page, pageSize);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isUserSearchResultEmpty method, of class DAOUser.
     */
    @Test
    public void testIsUserSearchResultEmpty() {
        System.out.println("isUserSearchResultEmpty");
        String keyword = "";
        int page = 0;
        int pageSize = 0;
        DAOUser instance = new DAOUser();
        boolean expResult = false;
        boolean result = instance.isUserSearchResultEmpty(keyword, page, pageSize);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getTotalUser method, of class DAOUser.
     */
    @Test
    public void testGetTotalUser() {
        System.out.println("getTotalUser");
        DAOUser instance = new DAOUser();
        int expResult = 0;
        int result = instance.getTotalUser();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAllUserWithPaging method, of class DAOUser.
     */
    @Test
    public void testGetAllUserWithPaging() {
        System.out.println("getAllUserWithPaging");
        int page = 0;
        int PAGE_SIZE = 0;
        DAOUser instance = new DAOUser();
        List<Map<String, Object>> expResult = null;
        List<Map<String, Object>> result = instance.getAllUserWithPaging(page, PAGE_SIZE);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getTotalSearch method, of class DAOUser.
     */
    @Test
    public void testGetTotalSearch() {
        System.out.println("getTotalSearch");
        String keyword = "";
        DAOUser instance = new DAOUser();
        int expResult = 0;
        int result = instance.getTotalSearch(keyword);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getUserByIdd method, of class DAOUser.
     */
    @Test
    public void testGetUserByIdd() {
        System.out.println("getUserByIdd");
        int AccountId = 0;
        DAOUser instance = new DAOUser();
        HashMap<String, Object> expResult = null;
        HashMap<String, Object> result = instance.getUserByIdd(AccountId);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of checkAccount method, of class DAOUser.
     */
    @Test
    public void testCheckAccount() {
        System.out.println("checkAccount");
        String username = "";
        String password = "";
        DAOUser instance = new DAOUser();
        User expResult = null;
        User result = instance.checkAccount(username, password);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of ChangePass method, of class DAOUser.
     */
    @Test
    public void testChangePass() {
        System.out.println("ChangePass");
        User u = null;
        DAOUser instance = new DAOUser();
        instance.ChangePass(u);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getUserByEmail method, of class DAOUser.
     */
    @Test
    public void testGetUserByEmail() {
        System.out.println("getUserByEmail");
        String email = "";
        DAOUser instance = new DAOUser();
        User expResult = null;
        User result = instance.getUserByEmail(email);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of checkUserObj method, of class DAOUser.
     */
    @Test
    public void testCheckUserObj() {
        System.out.println("checkUserObj");
        String username = "hhh";
        String passWord = "12345678";
        DAOUser instance = new DAOUser();
        User expResult = null;
        User result = instance.checkUserObj(username, passWord);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("Not match");
    }

}
