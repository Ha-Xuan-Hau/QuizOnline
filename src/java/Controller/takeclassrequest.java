/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import Entity.TakeClass;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import Model.DAOTakeClass;
import java.sql.ResultSet;

/**
 *
 * @author admin
 */
public class takeclassrequest extends HttpServlet {

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
            request.getRequestDispatcher("displaytakeclass.jsp").forward(request, response);

        }
        if (go.equals("add")) {
            DAOTakeClass dao = new DAOTakeClass();

            String ms = "";
            int n = 0;
          
            int StudentAccountId = Integer.parseInt(request.getParameter("StudentAccountId"));
            int ClassId = Integer.parseInt(request.getParameter("ClassId"));
            TakeClass obj = new TakeClass( StudentAccountId, ClassId);
            n = dao.CreateTakeClass(obj);
            if (n != 0) {
                ms = "add success";
            } else {
                ms = "do not add";
            }
            request.setAttribute("ms", ms);
            request.getRequestDispatcher("displaytakeclass.jsp").forward(request, response);
        }
        if (go.equals("delete")) {
            DAOTakeClass dao = new DAOTakeClass();
            int TakeClassId = Integer.parseInt(request.getParameter("TakeClassId"));
            int n = 0;
            n = dao.DeleteTakeClass(TakeClassId);
            String ms = "";
            if (n != 0) {
                ms = "delete success";
            } else {
                ms = "don't delete";

            }
            request.setAttribute("ms", ms);
            request.getRequestDispatcher("displaytakeclass.jsp").forward(request, response);

        }
        if (go.equals("update")) {
            DAOTakeClass dao = new DAOTakeClass();
            int TakeClassId = Integer.parseInt(request.getParameter("TakeClassId"));
            TakeClass obj = dao.getTakeClass(TakeClassId);
            ResultSet rs = dao.getResultSet("select ClassName, ClassId from Class ");
            ResultSet rs2 = dao.getResultSet("select StudentName, AccountId from Student ");
            request.setAttribute("rs", rs);
            request.setAttribute("rs2", rs2);
            request.setAttribute("data", obj);
            request.getRequestDispatcher("updatetakeclass.jsp").forward(request, response);
        }
        if (go.equals("updatetakeclass")) {
            DAOTakeClass dao = new DAOTakeClass();
            int n = 0;
            String ms = "";
int TakeClassId = Integer.parseInt(request.getParameter("TakeClassId"));
            int StudentAccountId = Integer.parseInt(request.getParameter("StudentAccountId"));
            int ClassId = Integer.parseInt(request.getParameter("ClassId"));
            TakeClass obj = new TakeClass(TakeClassId,StudentAccountId,ClassId);
            n=dao.UpdateTakeClass(obj);
             if ( n!=0){
                ms="update success";
            } else{
                ms="do not update";
            }
            request.setAttribute("ms", ms);
            request.getRequestDispatcher("displaytakeclass.jsp").forward(request, response);
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
