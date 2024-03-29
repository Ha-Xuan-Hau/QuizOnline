
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package Controller;

import Entity.Admin;
import Entity.BlogList;
import Entity.QuestionSet;
import Entity.User;
import Model.DAOAdmin;
import Model.DAOBlogList;
import Model.DAOQuestionSet;
import Model.DAOUser;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.Blob;
import java.util.List;

/**
 *
 * @author Asus
 */
@WebServlet(name="HomeController", urlPatterns={"/HomeController"})
public class HomeController extends HttpServlet {
   
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
        try (PrintWriter out = response.getWriter()) {
             
            DAOQuestionSet qset = new DAOQuestionSet();
            DAOBlogList blog = new DAOBlogList();
            
            List<QuestionSet> listS = qset.getTop3();

            
            final int PAGE_SIZE = 9;
             //phân trang
            int page = 1;
            String pageStr = request.getParameter("page");
            if (pageStr != null) {
                page = Integer.parseInt(pageStr);
            }
            if (page < 1) {
                page = 1;
            }
            int totalBlog = blog.getTotal();
            int totalPage = totalBlog/ PAGE_SIZE;
            if (totalBlog % PAGE_SIZE != 0) {
                totalPage += 1;
            }
            if (page > totalPage) {
                page = totalPage;
            }
            
            List<BlogList> listBlog = blog.getAllBlogListWithPagging(page, PAGE_SIZE);
             request.setAttribute("totalPage", totalPage);
            
            request.setAttribute("Blog", listBlog);
            request.setAttribute("listS", listS);
            request.getRequestDispatcher("/Home/Home.jsp").forward(request, response);
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
        processRequest(request, response);
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

