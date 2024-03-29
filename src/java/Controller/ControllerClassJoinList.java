/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import Model.DAOClass;
import Model.DAOTakeClass;
import Model.DAOTeacher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;

/**
 *
 * @author phamg
 */
@WebServlet(name = "ControllerClassJoinList", urlPatterns = {"/ClassJoinListURL"})
public class ControllerClassJoinList extends HttpServlet {

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
            DAOTakeClass daoTakeClass = new DAOTakeClass();
            DAOTeacher daoTeacher = new DAOTeacher();
            if (service == null) {

//                int acc = (int) session.getAttribute("acc");
                ArrayList<Integer> classid = daoTakeClass.getClassIDbyStudentID(2);
                ArrayList<Entity.Class> classList = new ArrayList<>();
                ArrayList<Entity.Teacher> teacherList = new ArrayList<>();
                for (Integer id : classid) {
                    Entity.Class classInfo = dao.getDataByClassID(id);
                    teacherList.add(daoTeacher.getTeacherByAccountId(classInfo.getTeacherAccountId()));
                    classList.add(classInfo);
                }
                request.setAttribute("teacherList", teacherList);
                request.setAttribute("data", classList);
                request.getRequestDispatcher("/Class/classJoinList.jsp").forward(request, response);
            } else {
                if (service.equals("Delete")) {
                    int ClassId = Integer.parseInt(request.getParameter("ClassId"));
                    dao.DeleteClass(ClassId);
                    response.sendRedirect("ClassJoinListURL");
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

}
