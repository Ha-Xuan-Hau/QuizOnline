/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package Model;

import Entity.Student;
import java.util.Vector;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author phamg
 */
public class DAOStudentTest {

    DAOStudent dao = new DAOStudent();

    public DAOStudentTest() {
    }

    @Test
    public void testInsertStudent() {
        int accountId = 13;
        String studentName = "hau";
        String phone = "0982748237";
        String dob = "1999-08-05";
        boolean result;
        result = dao.insertStudent(accountId, studentName, phone, dob);
        if (result) {
            System.out.println("Test successfully.");
        } else {
            System.out.println("Test failed.");
        }
        assertTrue(result);
    }

    @Test
    public void testUpdateStudent_4args() {
        int accountId = 13;
        String studentName = "hau";
        String phone = "0982748237";
        String dob = "1999-08-05";
        boolean result;
        result = dao.updateStudent(accountId, studentName, phone, dob);
        if (result) {
            System.out.println("Test successfully.");
        } else {
            System.out.println("Test failed.");
        }
        assertTrue(result);
    }

    @Test
    public void testCreateStudent() {
        int accountId = 13;
        String studentName = "hau";
        String phone = "0982748237";
        String dob = "1999-08-05";
        Student newStudent = new Student(accountId, studentName, phone, dob);
        int result;
        result = dao.CreateStudent(newStudent);
        if (result > 0) {
            System.out.println("Test successfully." + newStudent);
        } else {
            System.out.println("Test failed.");
        }
        assertTrue(result > 0);
    }

    @Test
    public void testUpdateStudent_Student() {
        int accountId = 13;
        String studentName = "hau";
        String phone = "0982748237";
        String dob = "1999-08-05";
        Student oldStudent = new Student(accountId, studentName, phone, dob);
        int result;
        result = dao.UpdateStudent(oldStudent);
        if (result > 0) {
            System.out.println("Test successfully." + oldStudent);
        } else {
            System.out.println("Test failed.");
        }
        assertTrue(result > 0);
    }

    @Test
    public void testDeleteStudent() {
        int accountId = 24;
        int result;
        result = dao.DeleteStudent(accountId);
        if (result > 0) {
            System.out.println("Test successfully.");
        } else {
            System.out.println("Test failed.");
        }
        assertTrue(result > 0);
    }

    @Test
    public void testGetStudent() {
        int accountId = 24;
        Student obj = new Student();
        obj = dao.getStudent(accountId);
        if (obj != null) {
            System.out.println("Test successfully.");
        } else {
            System.out.println("Test failed.");
        }
        assertNotNull(obj);
    }

    @Test
    public void testGetAll() {
        Vector<Student> vector = new Vector<Student>();
        vector = dao.getAll();
        if (vector != null) {
            System.out.println("Test successfully." + vector);
        } else {
            System.out.println("Test failed.");
        }
        assertNotNull(vector);
    }

}
