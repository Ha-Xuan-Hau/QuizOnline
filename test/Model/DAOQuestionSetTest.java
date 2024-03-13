/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package Model;

import Entity.QuestionSet;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author phamg
 */
public class DAOQuestionSetTest {
    public DAOQuestionSetTest() {
    }

    @Test
    public void testGetAllQuestionSet() {
        DAOQuestionSet dao = new DAOQuestionSet();
        List<QuestionSet> qs = new ArrayList<>();
        qs = dao.getAllQuestionSet();
        if (qs != null) {
            System.out.println("Test successfully.");
        } else {
            System.out.println("Test failed.");
        }
        assertNotNull(qs);
    }

    @Test
    public void testGetAllSubjectId() {
        DAOQuestionSet dao = new DAOQuestionSet();
        ArrayList<Integer> subjectIds = new ArrayList<>();
        subjectIds = dao.getAllSubjectId();
        if (subjectIds != null) {
            System.out.println("Test successfully.");
        } else {
            System.out.println("Test failed.");
        }
        assertNotNull(subjectIds);
    }

    @Test
    public void testGetQuestionSetById() {
        DAOQuestionSet dao = new DAOQuestionSet();
        int SetId = 1;
        QuestionSet sq = new QuestionSet();
        sq = dao.getQuestionSetById(SetId);
        if (sq != null) {
            System.out.println("Test successfully." + sq);
        } else {
            System.out.println("Test failed.");
        }
        assertNotNull(sq);   
    }

    @Test
    public void testCountQuest() {
        DAOQuestionSet dao = new DAOQuestionSet();
        String keyword = "Qu";
        int result;
        result = dao.countQuest(keyword);
        if (result > 0) {
            System.out.println("Test successfully." + result);
        } else {
            System.out.println("Test failed.");
        }
        assertTrue(result > 0);
    }

    @Test
    public void testGetData() {
        DAOQuestionSet dao = new DAOQuestionSet();
        ArrayList<QuestionSet> questionset = new ArrayList<>();
        String sql = "SELECT * FROM QuestionSet";
        questionset = dao.getData(sql);
        if (questionset != null) {
            System.out.println("Test successfully." + questionset);
        } else {
            System.out.println("Test failed.");
        }
        assertNotNull(questionset); 
    }

    @Test
    public void testGetQuestionSetWithPagging() {
        DAOQuestionSet dao = new DAOQuestionSet();
        List<QuestionSet> list = new ArrayList<>();
        int page = 4;
        int PAGE_SIZE = 4;
        list = dao.getQuestionSetWithPagging(page, PAGE_SIZE);
        if (list != null) {
            System.out.println("Test successfully." + list);
        } else {
            System.out.println("Test failed.");
        }
        assertNotNull(list); 

    }

    
}
