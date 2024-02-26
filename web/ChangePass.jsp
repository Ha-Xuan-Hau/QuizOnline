<%-- 
    Document   : ChangePass
    Created on : Feb 23, 2024, 5:43:20 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="Entity.User" %>
<%@page import="jakarta.servlet.http.HttpSession" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Change Password</h1>
        <%
            String us = (String) session.getAttribute("username");
        %>
        <form action="change" method="POST">
            <input type="hidden" name="username" value="<%=us%>">
        Old Password <input type="password" name="opass"><br>
        <% 
        String op = (String)request.getAttribute("op");
        if(op != null) {
            out.println("<p style='color:red;'>" + op + "</p>");
        }
    %>
        New Password <input type="password" name="npass"><br>
        <% 
        String np = (String)request.getAttribute("np");
        if(np != null) {
            out.println("<p style='color:red;'>" + np + "</p>");
        }
    %>
        Confirm New Password <input type="password" name="cnpass"><br>
        <% 
        String rp = (String)request.getAttribute("rp");
        if(rp != null) {
            out.println("<p style='color:red;'>" + rp + "</p>");
        }
    %>
        <input type="submit" value="Change Password">
    </form>
    <% 
        String errorMessage = (String)request.getAttribute("errorMessage");
        if(errorMessage != null) {
            out.println("<p style='color:red;'>" + errorMessage + "</p>");
        }
        String successMessage = (String)request.getAttribute("successMessage");
        if(successMessage != null) {
            out.println("<p style='color:green;'>" + successMessage + "</p>");
        }
    %>
       
    </body>
</html>
