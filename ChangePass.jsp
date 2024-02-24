<%-- 
    Document   : ChangePass
    Created on : Feb 23, 2024, 5:43:20 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Change Password</h1>
        <form action="change">
            <input type="hidden" name="username" value="${sessionScope.account.Username}">
        Old Password <input type="password" name="opass"><br>
        New Password <input type="password" name="npass"><br>
        Confirm New Password <input type="password" name="cnpass"><br>
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
