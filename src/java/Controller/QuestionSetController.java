/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import Entity.NormalQuestion;
import Entity.NormalQuestionAnswer;
import Entity.QuestionSet;
import Entity.Subject;
import Entity.Teacher;
import Entity.User;
import Entity.UserSet;
import Model.DAONormalQuestion;
import Model.DAONormalQuestionAnswer;
import Model.DAOQuestionSet;
import Model.DAOSubject;
import Model.DAOTeacher;
import Model.DAOUserSet;
import Utils.MyApplicationConstants;
import jakarta.servlet.ServletContext;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Properties;

/**
 *
 * @author admin
 */
@WebServlet(name = "QuestionSetController", urlPatterns = {"/QuestionSetURL"})
public class QuestionSetController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        ServletContext context = this.getServletContext();
        Properties siteMaps = (Properties) context.getAttribute("SITE_MAPS");

        String url = "";

        request.getSession().getAttribute("acc");
        User user = (User) request.getSession().getAttribute("acc");
        //call daos
        DAOQuestionSet dao = new DAOQuestionSet();
        DAONormalQuestion questionDAO = new DAONormalQuestion();
        DAONormalQuestionAnswer answerDAO = new DAONormalQuestionAnswer();
        //get user
        int userId = -1;
        if (user != null) {
            userId = user.getAccountId();
        }
        String service = request.getParameter("go");
        if (service == null) {
            service = "listAllSets";
        }
        if (service.equals("listAllSets")) {
            ArrayList<QuestionSet> allQuesSet = dao.getDataByUsId(userId);
            request.setAttribute("data", allQuesSet);
            url = siteMaps.getProperty(MyApplicationConstants.QuestionSetFeature.VIEW_MY_SET_PAGE);
            request.getRequestDispatcher(url).forward(request, response);
        }
        if (service.equals("addNewSet")) {
            QuestionSet set = new QuestionSet("New Set", userId, 1, 0);
            dao.insertQuestionSet(set);
            QuestionSet setObj = dao.getNewest();
            //    request.getRequestDispatcher("editSet.jsp").forward(request, response);
            url = siteMaps.getProperty(MyApplicationConstants.QuestionSetFeature.EDIT_SET_ACTION);
            response.sendRedirect(url + "?setId=" + setObj.getSetId());
        }
        if (service.equals("addNewSetDetails")) {
            String action = request.getParameter("submit");
            String mess="";
            if (action != null) {
                int setId = Integer.parseInt(request.getParameter("setId"));
                String title = request.getParameter("title");
                int subjectId = Integer.parseInt(request.getParameter("subjectId"));

                QuestionSet set = new QuestionSet(setId, title, userId, subjectId, 0);
                dao.updateQuestionSet(set);
                // lay cau hoi trong set
                int index1 = questionDAO.getTotalQuestionInSet(setId);
                for (int i = 0; i < index1; i++) {
                    String question = request.getParameter("QuestionDetail" + i);
                    int questionId = Integer.parseInt(request.getParameter("QuestionId" + i));

                    NormalQuestion ques = new NormalQuestion(questionId, question, setId);
                    questionDAO.update(ques);

                    int index2 = answerDAO.getTotalAnswerOfQuestion(questionId);
                    int correctCount = 0;
                    for (int j = 0; j < index2; j++) {
                        String isCorrect = request.getParameter("IsTrueAnswer" + i + "-" + j);
                        if ("true".equals(isCorrect)) {
                            correctCount++;
                        }
                    }
                    float percentage = correctCount > 0 ? 100.0f / correctCount : 0.0f;

                    for (int j = 0; j < index2; j++) {
                        String answer = request.getParameter("AnswerDetail" + i + "-" + j);
                        String isCorrect = request.getParameter("IsTrueAnswer" + i + "-" + j);
                        String answerId = request.getParameter("AnswerId" + i + "-" + j);

                        boolean correct = "true".equals(isCorrect);
                        int id = Integer.parseInt(answerId);

                        float percent = correct ? percentage : 0.0f;

                        NormalQuestionAnswer quesAnswer = new NormalQuestionAnswer(id, questionId, answer, correct, percent);
                        answerDAO.update(quesAnswer);
                    }
                }
                url = siteMaps.getProperty(MyApplicationConstants.QuestionSetFeature.EDIT_SET_ACTION);
                response.sendRedirect(url + "?setId=" + setId);
            }
        }
        if (service.equals("deleteSet")) {
            int setId = Integer.parseInt(request.getParameter("setId"));
            dao.deleteQuestionSet(setId);
            url=siteMaps.getProperty(MyApplicationConstants.ApplicationScope.QUESTION_SET_ACTION);
            response.sendRedirect(url);
        }
    }

// <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
