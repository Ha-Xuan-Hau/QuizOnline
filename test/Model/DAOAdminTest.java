/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package Model;

import Entity.Admin;
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
public class DAOAdminTest {
    
    public DAOAdminTest() {
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
     * Test of getAllAdmin method, of class DAOAdmin.
     */
    @Test
    public void testGetAllAdmin() {
        System.out.println("getAllAdmin");
        DAOAdmin instance = new DAOAdmin();
        List<Admin> expResult = null;
        List<Admin> result = instance.getAllAdmin();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of insertAdmin method, of class DAOAdmin.
     */
    @Test
    public void testInsertAdmin_3args() {
        System.out.println("insertAdmin");
        int accountId = 0;
        String adminName = "";
        String phone = "";
        DAOAdmin instance = new DAOAdmin();
        instance.insertAdmin(accountId, adminName, phone);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of updateAdmin method, of class DAOAdmin.
     */
    @Test
    public void testUpdateAdmin_3args() {
        System.out.println("updateAdmin");
        int accountId = 0;
        String adminName = "";
        String phone = "";
        DAOAdmin instance = new DAOAdmin();
        instance.updateAdmin(accountId, adminName, phone);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of insertAdmin method, of class DAOAdmin.
     */
    @Test
    public void testInsertAdmin_Admin() {
        System.out.println("insertAdmin");
        Admin ad = null;
        DAOAdmin instance = new DAOAdmin();
        instance.insertAdmin(ad);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAdminById method, of class DAOAdmin.
     */
    @Test
    public void testGetAdminById() {
        System.out.println("getAdminById");
        int AccountId = 0;
        DAOAdmin instance = new DAOAdmin();
        Admin expResult = null;
        Admin result = instance.getAdminById(AccountId);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of updateAdmin method, of class DAOAdmin.
     */
    @Test
    public void testUpdateAdmin_Admin() {
        System.out.println("updateAdmin");
        Admin a = null;
        DAOAdmin instance = new DAOAdmin();
        instance.updateAdmin(a);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of deleteAdmin method, of class DAOAdmin.
     */
    @Test
    public void testDeleteAdmin() {
        System.out.println("deleteAdmin");
        int AccountId = 0;
        DAOAdmin instance = new DAOAdmin();
        instance.deleteAdmin(AccountId);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAllSetting method, of class DAOAdmin.
     */
    @Test
    public void testGetAllSetting_int_int() {
        System.out.println("getAllSetting");
        int page = 0;
        int PAGE_SIZE = 0;
        DAOAdmin instance = new DAOAdmin();
        List<Map<String, Object>> expResult = null;
        List<Map<String, Object>> result = instance.getAllSetting(page, PAGE_SIZE);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isSettingListEmpty method, of class DAOAdmin.
     */
    @Test
    public void testIsSettingListEmpty() {
        System.out.println("isSettingListEmpty");
        String keyword = "";
        int page = 0;
        int PAGE_SIZE = 0;
        DAOAdmin instance = new DAOAdmin();
        boolean expResult = false;
        boolean result = instance.isSettingListEmpty(keyword, page, PAGE_SIZE);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of searchSettingList method, of class DAOAdmin.
     */
    @Test
    public void testSearchSettingList() {
        System.out.println("searchSettingList");
        String keyword = "";
        int page = 0;
        int PAGE_SIZE = 0;
        DAOAdmin instance = new DAOAdmin();
        List<Map<String, Object>> expResult = null;
        List<Map<String, Object>> result = instance.searchSettingList(keyword, page, PAGE_SIZE);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getSettingsByRole method, of class DAOAdmin.
     */
    @Test
    public void testGetSettingsByRole() {
        System.out.println("getSettingsByRole");
        int page = 0;
        int PAGE_SIZE = 0;
        DAOAdmin instance = new DAOAdmin();
        List<Map<String, Object>> expResult = null;
        List<Map<String, Object>> result = instance.getSettingsByRole(page, PAGE_SIZE);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAllSetting method, of class DAOAdmin.
     */
    @Test
    public void testGetAllSetting_0args() {
        System.out.println("getAllSetting");
        DAOAdmin instance = new DAOAdmin();
        List<Map<String, Object>> expResult = null;
        List<Map<String, Object>> result = instance.getAllSetting();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getTotalRole method, of class DAOAdmin.
     */
    @Test
    public void testGetTotalRole() {
        System.out.println("getTotalRole");
        DAOAdmin instance = new DAOAdmin();
        int expResult = 0;
        int result = instance.getTotalRole();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getTotalSubject method, of class DAOAdmin.
     */
    @Test
    public void testGetTotalSubject() {
        System.out.println("getTotalSubject");
        DAOAdmin instance = new DAOAdmin();
        int expResult = 0;
        int result = instance.getTotalSubject();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getTotalSearchSetting method, of class DAOAdmin.
     */
    @Test
    public void testGetTotalSearchSetting() {
        System.out.println("getTotalSearchSetting");
        String keyword = "";
        DAOAdmin instance = new DAOAdmin();
        int expResult = 0;
        int result = instance.getTotalSearchSetting(keyword);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getTotalSetting method, of class DAOAdmin.
     */
    @Test
    public void testGetTotalSetting() {
        System.out.println("getTotalSetting");
        DAOAdmin instance = new DAOAdmin();
        int expResult = 0;
        int result = instance.getTotalSetting();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getDistinctTypes method, of class DAOAdmin.
     */
    @Test
    public void testGetDistinctTypes() {
        System.out.println("getDistinctTypes");
        DAOAdmin instance = new DAOAdmin();
        List<String> expResult = null;
        List<String> result = instance.getDistinctTypes();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getSettingsByType method, of class DAOAdmin.
     */
    @Test
    public void testGetSettingsByType() {
        System.out.println("getSettingsByType");
        String type = "";
        DAOAdmin instance = new DAOAdmin();
        List<Map<String, Object>> expResult = null;
        List<Map<String, Object>> result = instance.getSettingsByType(type);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getSettingsBySubject method, of class DAOAdmin.
     */
    @Test
    public void testGetSettingsBySubject() {
        System.out.println("getSettingsBySubject");
        int page = 0;
        int PAGE_SIZE = 0;
        DAOAdmin instance = new DAOAdmin();
        List<Map<String, Object>> expResult = null;
        List<Map<String, Object>> result = instance.getSettingsBySubject(page, PAGE_SIZE);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getSettingById method, of class DAOAdmin.
     */
    @Test
    public void testGetSettingById() {
        System.out.println("getSettingById");
        int id = 0;
        String type = "";
        DAOAdmin instance = new DAOAdmin();
        Map<String, Object> expResult = null;
        Map<String, Object> result = instance.getSettingById(id, type);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of updateSettingRole method, of class DAOAdmin.
     */
    @Test
    public void testUpdateSettingRole() {
        System.out.println("updateSettingRole");
        int id = 0;
        String newValue = "";
        DAOAdmin instance = new DAOAdmin();
        instance.updateSettingRole(id, newValue);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of checkSubjectCodeExistence method, of class DAOAdmin.
     */
    @Test
    public void testCheckSubjectCodeExistence() {
        System.out.println("checkSubjectCodeExistence");
        String subjectCode = "";
        DAOAdmin instance = new DAOAdmin();
        boolean expResult = false;
        boolean result = instance.checkSubjectCodeExistence(subjectCode);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of updateSubjectNameByCode method, of class DAOAdmin.
     */
    @Test
    public void testUpdateSubjectNameByCode() {
        System.out.println("updateSubjectNameByCode");
        String subjectCode = "";
        String newSubjectName = "";
        DAOAdmin instance = new DAOAdmin();
        int expResult = 0;
        int result = instance.updateSubjectNameByCode(subjectCode, newSubjectName);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of main method, of class DAOAdmin.
     */
    @Test
    public void testMain() {
        System.out.println("main");
        String[] args = null;
        DAOAdmin.main(args);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
