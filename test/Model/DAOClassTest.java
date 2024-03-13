/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package Model;

import java.util.ArrayList;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author phamg
 */
public class DAOClassTest {
    DAOClass dao = new DAOClass();
    public DAOClassTest() {
    }

    @Test
    public void testCreateClassTrue() {
        String className = "HE173163";
        int teacherAccountId = 1;
        String createDate = "2024-03-10";
        Entity.Class newClass = new Entity.Class(className, teacherAccountId, createDate);
        int createSuccess = dao.CreateClass(newClass);
        if (createSuccess == 1) {
            System.out.println("Test createClass passed successfully.");
        }
        assertTrue(createSuccess == 1);
    }
    @Test
    public void testUpdateClass() {
        String className = "HE173163";
        int teacherAccountId = 4;
        String createDate = "2024-03-10";
        int ClassID = 4;
        Entity.Class newClass = new Entity.Class(ClassID, className, teacherAccountId, createDate);
        int Success = dao.UpdateClass(newClass);
        if (Success == 1) {
            System.out.println("Test updateClass passed successfully.");
        }
        assertTrue(Success == 1);
        
    }

    @Test
    public void testDeleteClass() {
        int ClassID = 30;
        int Success = dao.DeleteClass(ClassID);
        if (Success == 1) {
            System.out.println("Test deleteClass passed successfully.");
        } else {
            System.out.println("Test deleteClass failed.");
        }
        assertTrue(Success == 1);        
    }

    @Test
    public void testGetClass() {
        int ClassID = 1;
        Entity.Class GetClass = dao.getClass(ClassID);
        if (GetClass != null) {
            System.out.println("Test test Get Class passed successfully." + GetClass);
        } else {
            System.out.println("Test test Get Class failed.");
        }
        assertNotNull(GetClass);    
    }

    @Test
    public void testGetdata() {
        ArrayList<Entity.Class> arrayList = new ArrayList<>();
        arrayList = dao.getdata();
        if (arrayList != null) {
            System.out.println("Test test Get Class passed successfully./n" + arrayList);
        } else {
            System.out.println("Test test Get Class failed.");
        }
        assertNotNull(arrayList);      
    }

    @Test
    public void testGetDataByClassID() {
        int ClassID = 1;
        Entity.Class GetClass = dao.getDataByClassID(ClassID);
        if (GetClass != null) {
            System.out.println("Test test Get Class passed successfully." + GetClass);
        } else {
            System.out.println("Test test Get Class failed.");
        }
        assertNotNull(GetClass);    
    }

    @Test
    public void testGetDataByTeacherID() {
        int teacherAccountId = 1;
        ArrayList<Entity.Class> arrayList = new ArrayList<>();
        arrayList = dao.getDataByTeacherID(teacherAccountId);
        if (arrayList != null) {
            System.out.println("Test test Get Class passed successfully." + arrayList);
        } else {
            System.out.println("Test test Get Class failed.");
        }
        assertNotNull(arrayList);    
    }

    @Test
    public void testClassByClassID() {
        int ClassID = 1;
        Entity.Class GetClass = dao.getDataByClassID(ClassID);
        if (GetClass != null) {
            System.out.println("Test test Get Class passed successfully." + GetClass);
        }
        assertNotNull(GetClass);   
    }

    @Test
    public void testUpdateClassName() {
        String className = "HE173163_lopBaodz";
        int ClassID = 4;
        int Success = dao.updateClassName(ClassID, className);
        if (Success == 1) {
            System.out.println("Test updateClass passed successfully.");
        } else {
            System.out.println("Test updateClass failed.");
        }
        assertTrue(Success == 1);
    }

    @Test
    public void testGetClassByClassCode() {
        String ClassCode = "2133CF1";
        Entity.Class GetClass = dao.getClassByClassCode(ClassCode);
        if (GetClass != null) {
            System.out.println("Test test Get Class passed successfully." + GetClass);
        } else {
            System.out.println("Test test Get Class failed.");
        }
        assertNotNull(GetClass);  
    }
    
}
