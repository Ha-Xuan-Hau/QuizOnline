/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import Entity.User;
import Model.DAOUser;
import Utils.EncryptionUtils;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.SQLException;

import java.util.List;

/**
 *
 * @author ACER
 */
@WebServlet(name = "LoginController", urlPatterns = {"/Login"})
public class LoginController extends HttpServlet {

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
            String service = request.getParameter("go");
            if (service == null) {
                service = "showLogin";
            }
            if (service.equals("showLogin")) {
                if (request.getSession().getAttribute("user") != null) {
                    User user = (User) request.getSession().getAttribute("user");
                    request.setAttribute("email", user.getUsername());
                    request.setAttribute("password", user.getPassword());
                }
                request.getRequestDispatcher("/login/login.jsp").forward(request, response);
                boolean inValid = "".equals(request.getSession().getAttribute("validate"));
                if (!inValid) {
                    request.getSession().setAttribute("validate", "");
                }
            }
            if (service.equals("login")) {
                String email = request.getParameter("email");
                String password = request.getParameter("password");
                boolean remember = "".equals(request.getParameter("remember-account"));
                String validate = "Email or password is incorrect.";
                DAOUser DAOUser = new DAOUser();
                EncryptionUtils eu = new EncryptionUtils();
                List<User> isUser = DAOUser.checkUser(email, eu.toMD5(password));
                if (!isUser.isEmpty()) {
                    try {
                        User user = DAOUser.getUser(email, eu.toMD5(password));
                        if (user.isActive()) {
                            request.getSession().setAttribute("validate", "You have been restricted to use our service!");
                            response.sendRedirect("/login");
                            return;
                        }

                        request.getSession().setAttribute("user", user);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    if (!remember) {
                        Cookie cEmail = new Cookie("email", email);
                        Cookie cPassword = new Cookie("password", password);
                        cEmail.setMaxAge(60 * 60 * 24);
                        cPassword.setMaxAge(60 * 60 * 24);
                        response.addCookie(cEmail);
                        response.addCookie(cPassword);
                    }
//                    if (DAOUser.checkAdmin(email, eu.toMD5(password))) {
//                        response.sendRedirect("/admin");
//                        return;
//                    }
                    request.getSession().setAttribute("validate", "");
                    response.sendRedirect("/");
                } else {
                    request.getSession().setAttribute("validate", validate);
                    response.sendRedirect("/login");
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
