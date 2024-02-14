/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import Entity.Student;
import Entity.Teacher;
import Entity.User;
import Model.DAOUser;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Hashtable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Asus
 */
@WebServlet(name = "UserController", urlPatterns = {"/UserController"})
public class UserController extends HttpServlet {

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
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet UserController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UserController at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
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
        DAOUser dao = new DAOUser();
        List<User> user = dao.getAllUserListData("select*from [User]");
        ResultSet rsAd = dao.getResultSet("select AccountId, Phone from Admin");
        ResultSet rsSt = dao.getResultSet("select AccountId, StudentName, Phone, DoB from Student");
        ResultSet rsTc = dao.getResultSet("select AccountId, TeacherName, Phone from Teacher ");
        request.setAttribute("rsTc", rsTc);
        request.setAttribute("rsSt", rsSt);
        request.setAttribute("rsAd", rsAd);
        Hashtable<Integer, String> AdminMap = new Hashtable<>();
        try {
            while (rsAd.next()) {
                AdminMap.put(rsAd.getInt(1), rsAd.getString(2));

            }
        } catch (SQLException ex) {
            Logger.getLogger(UpdateProfile.class.getName()).log(Level.SEVERE, null, ex);
        }

        Hashtable<Integer, Student> studentMap = new Hashtable<>();

        try {
            while (rsSt.next()) {
                int accountId = rsSt.getInt(1);
                String studentName = rsSt.getString(2);
                String phone = rsSt.getString(3);
                String dob = rsSt.getString(4);

                Student student = new Student(accountId, studentName, phone, dob);
                System.out.println(student.toString());
                studentMap.put(accountId, student);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UpdateProfile.class.getName()).log(Level.SEVERE, null, ex);
        }


        //teacher
        Hashtable<Integer, Teacher> teacherMap = new Hashtable<>();

        try {
            while (rsTc.next()) {
                int accountId = rsTc.getInt(1);
                String teacherName = rsTc.getString(2);
                String phone = rsTc.getString(3);

                Teacher teacher = new Teacher(accountId, teacherName, phone);
                System.out.println(teacher.toString());
                teacherMap.put(accountId, teacher);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UpdateProfile.class.getName()).log(Level.SEVERE, null, ex);
        }

        request.setAttribute("teacherMap", teacherMap);
        request.setAttribute("studentMap", studentMap);
        request.setAttribute("data", user);
        request.setAttribute("AdminMap", AdminMap);

        request.getRequestDispatcher("/Profile/listUser.jsp").forward(request, response);

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
