<%-- 
    Document   : addstudent
    Created on : Jan 17, 2024, 12:49:04 PM
    Author     : admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form action="studentrequest">
            <input type="hidden" name="go" value="add"> 
            AccountID<input type="number" name="AccountId"><br>
            StudentName<input type="text" name="StudentName"><br>
            Phone<input type="text" name="Phone"><br>
            DoB<input type="text" name="DoB"><br>
            <input type="submit" value="ADD">
            
        </form>
    </body>
</html>
