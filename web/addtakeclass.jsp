<%-- 
    Document   : addtakeclass
    Created on : Jan 18, 2024, 4:14:55 PM
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
        <form action="takeclassrequest">
            <input type="hidden" name="go" value="add"> 

            StudentAccountId<input type="number" name="StudentAccountId"><br>
            ClassId<input type="number" name="ClassId"><br>
            <input type="submit" value="ADD">
        </form>
    </body>
</html>
