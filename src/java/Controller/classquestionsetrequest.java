/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import Model.DAOClassQuestionSet;
import java.sql.ResultSet;
import Entity.ClassQuestionSet;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


/**
 *
 * @author admin
 */
public class classquestionsetrequest extends HttpServlet {

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
        String go = "";
        if (request.getParameter("go") != null) {
            go = request.getParameter("go");
        }
        if (go == null) {
            request.getRequestDispatcher("displayclassquestionset.jsp").forward(request, response);

        }
        if (go.equals("add")) {
            DAOClassQuestionSet dao = new DAOClassQuestionSet();

            String ms = "";
            int n = 0;
            int ClassId = Integer.parseInt(request.getParameter("ClassId"));

            int SetId = Integer.parseInt(request.getParameter("SetId"));
            ClassQuestionSet obj = new ClassQuestionSet(ClassId, SetId);
            n = dao.CreateClassQuestionSet(obj);
            if (n != 0) {
                ms = "add success";
            } else {
                ms = "do not add";
            }

            request.setAttribute("ms", ms);
            request.getRequestDispatcher("displayclassquestionset.jsp").forward(request, response);
        }
        if (go.equals("delete")) {
            DAOClassQuestionSet dao = new DAOClassQuestionSet();
            int ClassSetId = Integer.parseInt(request.getParameter("ClassSetId"));
            int n = 0;
            n = dao.DeleteClassQuestionSet(ClassSetId);
            String ms = "";
            if (n != 0) {
                ms = "delete success";
            } else {
                ms = "don't delete";

            }
            request.setAttribute("ms", ms);
            request.getRequestDispatcher("displayclassquestionset.jsp").forward(request, response);
        }
        if (go.equals("update")) {
            DAOClassQuestionSet dao = new DAOClassQuestionSet();
            int ClassSetId = Integer.parseInt(request.getParameter("ClassSetId"));
            ClassQuestionSet obj = dao.ClassQuestionSet(ClassSetId);
            ResultSet rs = dao.getResultSet("select ClassName, ClassId from Class");
            request.setAttribute("rs", rs);
            request.setAttribute("data", obj);
            request.getRequestDispatcher("updateclassquestionset.jsp").forward(request, response);
        }
        if (go.equals("updateclassquestionset")) {
            DAOClassQuestionSet dao = new DAOClassQuestionSet();
            int n = 0;
            String ms = "";
            int ClassSetId = Integer.parseInt(request.getParameter("ClassSetId"));
            int ClassId = Integer.parseInt(request.getParameter("ClassId"));
            int SetId = Integer.parseInt(request.getParameter("SetId"));
            ClassQuestionSet obj = new ClassQuestionSet( ClassSetId,ClassId, SetId);
            n = dao.UpdateClassQuestionSet(obj);
            if (n != 0) {
                ms = "update success";
            } else {
                ms = "do not update";
            }
            request.setAttribute("ms", ms);
            request.getRequestDispatcher("displayclassquestionset.jsp").forward(request, response);
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
