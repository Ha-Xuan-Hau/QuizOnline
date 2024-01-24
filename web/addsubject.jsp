<%-- 
    Document   : addsubject
    Created on : Jan 22, 2024, 9:51:33 PM
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
       <form action="subjectcontroller">
            <input type="hidden" name="go" value="add"> 
            Subject Code <input type="text" name="Subjectcode"><br>
            Subject Name <input type="text" name="Subjectname"><br>
            <input type="submit" value="ADD"><br>
       </form>
    </body>
</html>
