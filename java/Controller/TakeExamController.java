/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;


import Entity.TakeExam;
import Model.DAOTakeExam;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;

/**
 *
 * @author hieul
 */
@WebServlet(name = "TakeExamController", urlPatterns = {"/TakeExamURL"})
public class TakeExamController extends HttpServlet {

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
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            DAOTakeExam dao = new DAOTakeExam();
            String service = request.getParameter("go");
            if (service == null){
                service = "listAllTakeExam";
            }
            String message = request.getParameter("message");
            if (message == null) {
                message = "";
            }
            if (service.equals("listAllAccount")) {
                //call dao
                ArrayList<TakeExam> list = dao.getTakeExam("select * from TakeExam");
                // create/call some parameters           
                String pageAccount = "TakeExams";
                String tableAccount = "List of TakeExams";
                //set data for view/jsp
                request.setAttribute("message", message);
                request.setAttribute("data", list);
                request.setAttribute("pageTakeExam", pageAccount);
                request.setAttribute("tableTakeExam", tableAccount);
                // select view/jsp
                RequestDispatcher dispath
                        = request.getRequestDispatcher("/jsp/display/DisplayTakeExam.jsp");
                // run
                dispath.forward(request, response);
            }
            if (service.equals("deleteAccount")) {
                String TakeExamId = request.getParameter("id");
                int n = dao.deleteTakeExam(TakeExamId);
                String st = "";
                if (n > 0) {
                    st = "delete success";
                } else {
                    st = "can't delete because...";
                }
                response.sendRedirect("TakeExamURL?message=" + st);
            }
            if (service.equals("insertTakeExam")) {
                String submit = request.getParameter("submit");
                if (submit == null) { //request by <a> --> show form
                    //step1: show insert form
                    //get data                    
                    RequestDispatcher dispath
                            = request.getRequestDispatcher("/jsp/insert/InsertTakeExam.jsp");
                    // run
                    dispath.forward(request, response);
                } else { //request by form --> insert data into DB
                    //step 2: insert into DB 
                
                    //convert
                     
                    TakeExam obj = new TakeExam();
                    int n = dao.insertTakeExam(obj);
                    String st = "";
                    if (n > 0) {
                        st = "insert success";
                    } else {
                        st = "can't insert because...";
                    }
                    response.sendRedirect("AccountURL?message=" + st);
                }
            }
            if (service.equals("updateTakeExam")) {
                String submit = request.getParameter("submit");
                if (submit == null) {
                    
                    RequestDispatcher dispath = request.getRequestDispatcher("/jsp/update/UpdateTakeExam.jsp");
                    dispath.forward(request, response);
                } else {
                    
                    
                     TakeExam obj = new TakeExam();
                    int n = dao.updateTakeExam(obj);
                    String st = "";
                    if (n > 0) {
                        st = "update success";
                    } else {
                        st = "can't update because...";
                    }
                    response.sendRedirect("AccountURL?message=" + st);
                }
            }
            if (service.equals("searchTakeExam")){
                String submit = request.getParameter("submit");
                if (submit == null) {
                    
                    // select view/jsp
                    RequestDispatcher dispath
                            = request.getRequestDispatcher("/jsp/display/DisplayTakeExam.jsp");
                    // run
                    dispath.forward(request, response);
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
