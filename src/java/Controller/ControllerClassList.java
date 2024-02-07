/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import Model.DAOClass;
import Model.DAOTeacher;
import Entity.Class;
import jakarta.servlet.http.HttpSession;
import java.sql.Timestamp;

/**
 *
 * @author phamg
 */
@WebServlet(name = "ControllerClassList", urlPatterns = {"/ClassListURL"})
public class ControllerClassList extends HttpServlet {

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
        try ( PrintWriter out = response.getWriter()) {
            String service = request.getParameter("go");
            HttpSession session = request.getSession();
            DAOClass dao = new DAOClass();
            DAOTeacher daoT = new DAOTeacher();
            if (service == null) {
                int account = 1;
                session.setAttribute("acc", account);
                int acc = (int) session.getAttribute("acc");
                session.setAttribute("nameTeacher", daoT.getTeacherByAccountId(acc).getTeacherName());
                ArrayList<Entity.Class> classList = dao.getDataByTeacherID(acc);
                request.setAttribute("data", classList);
                request.getRequestDispatcher("/Class/classList.jsp").forward(request, response);
            } else {
                if (service.equals("Delete")) {
                    int ClassId = Integer.parseInt(request.getParameter("ClassId"));
                    dao.DeleteClass(ClassId);
                    response.sendRedirect("ClassListURL");
                }

                if (service.equals("addClass")) {
                    String className = request.getParameter("className");
                    String subject = request.getParameter("subject");
                    className = cleanAndFormatInput(className);
                    subject = cleanAndFormatInput(subject);
                    String FullClassName = className + " " + subject;
                    Timestamp currentDate = new Timestamp(System.currentTimeMillis());
                    Class create = new Class(FullClassName, 1, currentDate.toString());
                    dao.CreateClass(create);
                    response.sendRedirect("ClassListURL");

                }

            }
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

    private String cleanAndFormatInput(String input) {
        input = input.trim();
        input = input.replaceAll("\\s+", " ");
        return input;
    }

}
