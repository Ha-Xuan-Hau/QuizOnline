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
import Model.DAOQuestionSet;
import Model.DAOSubject;
import Model.DAOTeacher;
import Model.DAOUserSet;
import Utils.MyApplicationConstants;
import jakarta.servlet.ServletContext;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Properties;

/**
 *
 * @author hieul
 */
@WebServlet(name = "QuestionSetDetailController", urlPatterns = {"/QuestionSetDetailURL"})
public class QuestionSetDetailController extends HttpServlet {

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

        String url = siteMaps.getProperty(MyApplicationConstants.QuestionSetFeature.VIEW_MY_SET_DETAIL_PAGE);
        
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            //call dao
            DAOQuestionSet dao = new DAOQuestionSet();
            DAOTeacher daoT = new DAOTeacher();
            DAOSubject daoS = new DAOSubject();
            DAOUserSet daoUS = new DAOUserSet();
            //get user
            User user = (User) request.getSession().getAttribute("acc");
            int userId = -1;
            if (user != null) {
                userId = user.getAccountId();
            }
            
            int setId = Integer.parseInt(request.getParameter("SetId"));

            ArrayList<QuestionSet> allQuesSet = dao.getDataByUsId(userId);
            ArrayList<NormalQuestion> Ques = dao.getQues(setId);
            ArrayList<ArrayList<NormalQuestionAnswer>> QuesAnswers = dao.getAnswer(setId);
            QuestionSet name = dao.getQuestionSetById(setId);
            if (user != null) {
                UserSet userSet = new UserSet(user.getAccountId(), setId);
                ArrayList<UserSet> checkUS = (ArrayList<UserSet>) daoUS.getAllUserSets();
                boolean userSetExists = false;
                for (UserSet existingUserSet : checkUS) {
                    if (existingUserSet.getUserId() == userSet.getUserId() && existingUserSet.getSetId() == userSet.getSetId()) {
                        userSetExists = true;
                        break;
                    }
                }
                request.setAttribute("userSetExists", userSetExists);
            }
            Teacher teacher = daoT.getTeacherByAccountId(name.getUserAccountId());
            Subject subject = daoS.getSubject(name.getSubjectId());
            request.setAttribute("acc", user);
            request.setAttribute("SetId", setId);
            request.setAttribute("subject", subject);
            request.setAttribute("teacher", teacher);
            request.setAttribute("name", name);
            request.setAttribute("data", allQuesSet);
            request.setAttribute("question", Ques);
            request.setAttribute("content", QuesAnswers);
            request.getRequestDispatcher(url).forward(request, response);

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
