/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import Entity.Class;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import Model.DAOClass;
import java.sql.ResultSet;

/**
 *
 * @author admin
 */
public class classrequest extends HttpServlet {

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
            request.getRequestDispatcher("displayclass.jsp").forward(request, response);

        }
        if (go.equals("add")) {
            DAOClass dao = new DAOClass();

            String ms = "";
            int n = 0;

            String TeacherAccountId_raw = request.getParameter("TeacherAccountId");
            String ClassName = request.getParameter("ClassName");
            String CreateDate = request.getParameter("CreateDate");

            int TeacherAccountId = Integer.parseInt(TeacherAccountId_raw);

            Class obj = new Class(ClassName, TeacherAccountId, CreateDate);
            n = dao.CreateClass(obj);
            if (n != 0) {
                ms = "add success";
            } else {
                ms = "do not add";
            }

            request.setAttribute("ms", ms);
            request.getRequestDispatcher("displayclass.jsp").forward(request, response);
        }
        if (go.equals("delete")) {
            DAOClass dao = new DAOClass();
            String ClassId_raw = request.getParameter("ClassId");

            int ClassId = Integer.parseInt(ClassId_raw);
            int n = 0;
            n = dao.DeleteClass(ClassId);
            String ms = "";
            if (n != 0) {
                ms = "delete success";
            } else {
                ms = "don't delete";

            }
            request.setAttribute("ms", ms);
            request.getRequestDispatcher("displayclass.jsp").forward(request, response);
        }
        if (go.equals("update")) {
            DAOClass dao = new DAOClass();
            String ClassId_raw = request.getParameter("ClassId");

            int ClassId = Integer.parseInt(ClassId_raw);
            Class obj = dao.getClass(ClassId);
            ResultSet rs = dao.getResultSet("select TeacherName, AccountId from Teacher");
            request.setAttribute("rs", rs);
            request.setAttribute("data", obj);
            request.getRequestDispatcher("updateclass.jsp").forward(request, response);

        }
        if (go.equals("updateclass")) {
            DAOClass dao = new DAOClass();
            int n = 0;
            String ms = "";
            String ClassId_raw = request.getParameter("ClassId");
            String ClassName = request.getParameter("ClassName");
            String TeacherAccountId_raw = request.getParameter("TeacherAccountId");
            String CreateDate = request.getParameter("CreateDate");

            int ClassId = Integer.parseInt(ClassId_raw);
            int TeacherAccountId = Integer.parseInt(TeacherAccountId_raw);
            Class obj = new Class(ClassId, ClassName, TeacherAccountId, CreateDate);
            n = dao.UpdateClass(obj);
            if ( n!=0){
                ms="update success";
            } else{
                ms="do not update";
            }
            request.setAttribute("ms", ms);
            request.getRequestDispatcher("displayclass.jsp").forward(request, response);
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
