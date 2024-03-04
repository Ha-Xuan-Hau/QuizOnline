/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package Controller;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import Model.DAOStudent;
import java.sql.ResultSet;
import Entity.Student;
/**
 *
 * @author admin
 */
public class studentrequest extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
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
            request.getRequestDispatcher("displaystudent.jsp").forward(request, response);

        }
        if(go.equals("add")){
            DAOStudent dao = new DAOStudent();
             int AccountId = Integer.parseInt(request.getParameter("AccountId"));
            String ms="";
            int n = 0;
           if(dao.getStudent(AccountId) !=null){
               ms="Duplicated ID!";
           } else{
            String StudentName = request.getParameter("StudentName");
            String Phone = request.getParameter("Phone");
            String DoB = request.getParameter("DoB");
            Student obj = new Student(AccountId, StudentName, Phone, DoB);
            
            n = dao.CreateStudent(obj);
            if(n != 0){
                ms = "add success";
            } else{
                ms = "do not add";
            }
            
        }
            request.setAttribute("ms", ms);
            request.getRequestDispatcher("displaystudent.jsp").forward(request, response);
        }
        if(go.equals("delete")){
            DAOStudent dao = new DAOStudent();
            int AccountId = Integer.parseInt(request.getParameter("AccountId"));
            int n = 0;
            n = dao.DeleteStudent(AccountId);
            String ms="";
            if(n != 0){
            ms="delete success";
                    } else{
                ms="don't delete";
            }
           request.setAttribute("ms", ms);
            request.getRequestDispatcher("displaystudent.jsp").forward(request, response);
        }
        if(go.equals("update")){
            DAOStudent dao = new DAOStudent();
             int AccountId = Integer.parseInt(request.getParameter("AccountId"));
             Student obj = dao.getStudent(AccountId);
             request.setAttribute("data", obj);
             request.getRequestDispatcher("updatestudent.jsp").forward(request, response);
        }
        if(go.equals("updatestudent")){
            DAOStudent dao = new DAOStudent();
            int n =0;
            String ms="";
              int AccountId = Integer.parseInt(request.getParameter("AccountId"));
         String StudentName = request.getParameter("StudentName");
            String Phone = request.getParameter("Phone");
            String DoB = request.getParameter("DoB");
            Student obj = new Student(AccountId, StudentName, Phone, DoB);
            n = dao.UpdateStudent(obj);
            if(n != 0){
                ms="update success";
            } else{
                ms="don't success";
            }
            request.setAttribute("data", obj);
             request.setAttribute("ms", ms);
            request.getRequestDispatcher("displaystudent.jsp").forward(request, response);
        }
    }
     

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
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
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        request.getRequestDispatcher("displaystudent.jsp").forward(request, response);
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
