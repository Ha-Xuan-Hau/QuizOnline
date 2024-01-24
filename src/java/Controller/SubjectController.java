/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import Entity.Subject;
import Model.DAOSubject;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;

@WebServlet(name = "SubjectController", urlPatterns = {"/subjectcontroller"})
public class SubjectController extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            DAOSubject dao = new DAOSubject();
            String service = request.getParameter("go");
            if (service == null) {
                service = "listAllSubject";
            }
            if (service.equals("listAllSubject")) {
                ArrayList<Subject> subject = dao.getData("SELECT * FROM Subject");
                request.setAttribute("data", subject);
                request.getRequestDispatcher("subjectlist.jsp").forward(request, response);
            }
            if (service.equals("delete")) {
                int SubjectId = Integer.parseInt(request.getParameter("SubjectId"));
                dao.delete(SubjectId);
                response.sendRedirect("subjectcontroller");
            }
            if (service.equals("add")) {
                String Subjectcode = request.getParameter("Subjectcode");
                String Subjectname = request.getParameter("Subjectname");
                Subject obj = new Subject(Subjectcode, Subjectname);
                dao.insert(obj);
                response.sendRedirect("subjectcontroller");
            }
            if (service.equals("updatesubject")) {
                String submit = request.getParameter("submit");
                if (submit == null) {
                    int SubjectId = Integer.parseInt(request.getParameter("SubjectId"));

                    Subject obj = dao.getSubject(SubjectId);
                    request.setAttribute("data", obj);
                    request.getRequestDispatcher("updatesubject.jsp").forward(request, response);
                } else {
                    int SubjectId = Integer.parseInt(request.getParameter("SubjectId"));
                    String Subjectcode = request.getParameter("Subjectcode");
                    String Subjectname = request.getParameter("Subjectname");
                    Subject obj = new Subject(SubjectId ,Subjectcode, Subjectname);
                    dao.update(obj);
                    response.sendRedirect("subjectcontroller");
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
//        request.getRequestDispatcher("subjectlist.jsp").forward(request, response);
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
